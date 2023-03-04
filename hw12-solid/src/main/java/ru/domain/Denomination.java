package ru.domain;

public enum Denomination {
    RUB_50(50),
    RUB_100(100),
    RUB_200(200),
    RUB_500(500),
    RUB_1000(1000),
    RUB_2000(2000),
    RUB_5000(5000);

    private final int amount;

    Denomination(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static int getMinimalAmount() {
        return Denomination.RUB_50.getAmount();
    }
}
