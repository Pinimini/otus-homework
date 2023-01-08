package ru.service;

import ru.domain.ATM;
import ru.domain.Banknote;
import ru.domain.Cell;
import ru.domain.Denomination;
import ru.exception.CellNotFoundException;
import ru.exception.NotEnoughMoneyException;
import ru.exception.NotMultiplyMinimumValue;

public class ATMServiceImpl implements ATMService {
    private final ATM atm;

    public ATMServiceImpl(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void deposit(Banknote banknote, int count) {
        Cell cell;
        try {
            cell = getCellByDenomination(banknote.getDenomination());
            putInCell(cell, count);
        } catch (CellNotFoundException e) {
            System.out.println("Банкнота возвращена вносителю средств");
        }
    }

    @Override
    public void withdrawal(int sum) {
        if (sum > getFullAmountATM()) {
            throw new NotEnoughMoneyException("В банкомате недостаточно средств");
        }

        int minimalAmount = Denomination.getMinimalAmount();
        if (sum < 50) {
            throw new NotMultiplyMinimumValue("Запрашиваемая сумма должна быть больше " + minimalAmount);
        }
        if (sum % minimalAmount != 0) {
            throw new NotMultiplyMinimumValue("Запрашиваемая сумма должна быть кратной " + minimalAmount);
        }

        takeBanknotesFomCell(sum);
    }

    @Override
    public void withdrawalBalance() {
        int sum = getFullAmountATM();
        takeBanknotesFomCell(sum);
    }

    private void takeBanknotesFomCell(int sum) {
        for (Cell cell : atm.getCells()) {
            int necessaryNumberOfBanknotes = sum / cell.getDenomination().getAmount();
            if (cell.getNumberBanknotes() >= necessaryNumberOfBanknotes && sum != 0) {
                cell.takeNumberBanknotes(necessaryNumberOfBanknotes);
                System.out.println("Выдано " + necessaryNumberOfBanknotes * cell.getDenomination().getAmount()
                        + " рублей " + necessaryNumberOfBanknotes + " банкнотами в " + cell.getDenomination().getAmount());
                sum -= necessaryNumberOfBanknotes * cell.getDenomination().getAmount();
                System.out.println("Оставшаяся сумма для выдачи " + sum + "\n");
            } else if (cell.getNumberBanknotes() < necessaryNumberOfBanknotes && sum != 0) {
                int estimateBanknotes = cell.getNumberBanknotes();
                cell.takeNumberBanknotes(estimateBanknotes);
                System.out.println("Выдано " + estimateBanknotes * cell.getDenomination().getAmount()
                        + " рублей " + estimateBanknotes + " банкнотами в " + cell.getDenomination().getAmount());
                sum -= estimateBanknotes * cell.getDenomination().getAmount();
                System.out.println("Оставшаяся сумма для выдачи " + sum + "\n");
            }
        }
    }

    @Override
    public Cell getCellByDenomination(Denomination denomination) throws CellNotFoundException {
        return atm.getCells().stream().filter(c -> c.getDenomination().equals(denomination)).findFirst()
                .orElseThrow(() -> new CellNotFoundException("Для данной банкноты отсутствует ячейка"));
    }

    @Override
    public void putInCell(Cell cell, int count) {
        cell.putNumberBanknotes(count);
    }

    @Override
    public int getFullAmountATM() {
        return atm.getCells().stream().map(Cell::getFullAmountCell).reduce(0, Integer::sum);
    }
}
