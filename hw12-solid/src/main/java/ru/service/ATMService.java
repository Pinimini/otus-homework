package ru.service;

import ru.domain.Cell;
import ru.domain.Denomination;
import ru.exception.CellNotFoundException;

public interface ATMService extends DepositService, WithdrawalService {
    Cell getCellByDenomination(Denomination denomination) throws CellNotFoundException;
    void putInCell(Cell cell, int count);
    int getFullAmountATM();
}
