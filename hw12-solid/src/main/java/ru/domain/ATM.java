package ru.domain;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ATM {
    private final SortedSet<Cell> cells;

    public ATM(Set<Cell> cells) {
        this.cells = new TreeSet<>(cells);
    }

    public SortedSet<Cell> getCells() {
        return cells;
    }

}
