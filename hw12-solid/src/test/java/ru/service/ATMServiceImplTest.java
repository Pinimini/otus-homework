package ru.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.domain.ATM;
import ru.domain.Banknote;
import ru.domain.Cell;
import ru.domain.Denomination;
import ru.exception.CellNotFoundException;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ATMServiceImplTest {
    private ATMService atmService;
    private final Banknote BANKNOTE_5000;
    private final Banknote BANKNOTE_2000;
    private final Banknote BANKNOTE_1000;
    private final Banknote BANKNOTE_500;
    private final Banknote BANKNOTE_200;
    private final Banknote BANKNOTE_100;
    private final Banknote BANKNOTE_50;

    public ATMServiceImplTest() {
        this.BANKNOTE_5000 = new Banknote(Denomination.RUB_5000);
        this.BANKNOTE_2000 = new Banknote(Denomination.RUB_2000);
        this.BANKNOTE_1000 = new Banknote(Denomination.RUB_1000);
        this.BANKNOTE_500 = new Banknote(Denomination.RUB_500);
        this.BANKNOTE_200 = new Banknote(Denomination.RUB_200);
        this.BANKNOTE_100 = new Banknote(Denomination.RUB_100);
        this.BANKNOTE_50 = new Banknote(Denomination.RUB_50);
    }

    @BeforeEach
    public void setUp() {
        Set<Cell> cells = Set.of(
                new Cell(Denomination.RUB_50), new Cell(Denomination.RUB_200),
                new Cell(Denomination.RUB_500), new Cell(Denomination.RUB_1000), new Cell(Denomination.RUB_2000),
                new Cell(Denomination.RUB_5000));
        ATM atm = new ATM(cells);

        atmService = new ATMServiceImpl(atm);
    }

    @Test
    void deposit() {
        atmService.deposit(BANKNOTE_5000, 2);
        atmService.deposit(BANKNOTE_2000, 7);
        atmService.deposit(BANKNOTE_1000, 16);
        atmService.deposit(BANKNOTE_5000, 4);
        atmService.deposit(BANKNOTE_50, 1);
        assertThat(atmService.getFullAmountATM()).isEqualTo(60050);
    }

    @Test
    void withdrawal() {
        depositTestSum();

        atmService.withdrawal(23350);
        assertThat(atmService.getFullAmountATM()).isEqualTo(22000);
    }

    @Test
    void withdrawalBalance() {
        depositTestSum();
        atmService.withdrawalBalance();
        assertThat(atmService.getFullAmountATM()).isZero();
    }

    @Test
    void getCellByDenominationError() {
        assertThatThrownBy(() -> atmService.getCellByDenomination(Denomination.RUB_100))
                .isInstanceOf(CellNotFoundException.class)
                .hasMessageContaining("Для данной банкноты отсутствует ячейка");
    }

    @Test
    void putInCell() {
        Cell cell = new Cell(Denomination.RUB_2000);
        atmService.putInCell(cell, 3);
        assertThat(cell.getFullAmountCell()).isEqualTo(6000);
    }

    @Test
    void getFullAmountATM() {
        atmService.deposit(BANKNOTE_5000, 2);
        atmService.deposit(BANKNOTE_5000, 54);
        atmService.deposit(BANKNOTE_200, 7);
        assertThat(atmService.getFullAmountATM()).isEqualTo(281400);
    }

    private void depositTestSum() {
        atmService.deposit(BANKNOTE_5000, 3);
        atmService.deposit(BANKNOTE_2000, 7);
        atmService.deposit(BANKNOTE_1000, 16);
        atmService.deposit(BANKNOTE_50, 1);
    }
}