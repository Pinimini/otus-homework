package ru;

import ru.domain.ATM;
import ru.domain.Banknote;
import ru.domain.Cell;
import ru.domain.Denomination;
import ru.service.ATMService;
import ru.service.ATMServiceImpl;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Cell> cells = Set.of(
                new Cell(Denomination.RUB_50), new Cell(Denomination.RUB_100), new Cell(Denomination.RUB_200),
                new Cell(Denomination.RUB_500), new Cell(Denomination.RUB_1000), new Cell(Denomination.RUB_2000),
                new Cell(Denomination.RUB_5000));
        ATM atm = new ATM(cells);

        ATMService atmService = new ATMServiceImpl(atm);
        atmService.deposit(new Banknote(Denomination.RUB_50), 564);
        atmService.deposit(new Banknote(Denomination.RUB_500), 200);
        atmService.deposit(new Banknote(Denomination.RUB_1000), 4);
        atmService.deposit(new Banknote(Denomination.RUB_5000), 2);
        System.out.println("Количество денег в банкомате " + atmService.getFullAmountATM());

        atmService.withdrawal(16350);
        System.out.println("Количество денег в банкомате " + atmService.getFullAmountATM());
        atmService.withdrawalBalance();
        System.out.println("Количество денег в банкомате " + atmService.getFullAmountATM());
    }
}
