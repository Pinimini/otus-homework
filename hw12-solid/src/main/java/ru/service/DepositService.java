package ru.service;

import ru.domain.Banknote;

public interface DepositService {
    void deposit(Banknote banknote, int count);
}
