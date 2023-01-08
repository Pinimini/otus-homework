package ru.domain;

public class Cell implements Comparable<Cell> {
    private final Denomination denomination;
    private int numberBanknotes;

    public Cell(Denomination denomination) {
        this.denomination = denomination;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public int getNumberBanknotes() {
        return numberBanknotes;
    }

    public void putNumberBanknotes(int numberBanknotes) {
        this.numberBanknotes += numberBanknotes;
    }

    public void takeNumberBanknotes(int numberBanknotes) {
        this.numberBanknotes -= numberBanknotes;
    }

    public int getFullAmountCell() {
        return denomination.getAmount() * numberBanknotes;
    }

    @Override
    public int compareTo(Cell cell) {
        return Integer.compare(cell.getDenomination().getAmount(), this.denomination.getAmount());
    }
}
