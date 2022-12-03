package ru.otus.statistics;

public class Statistics{
    private static int numberPassedTests;
    private static int numberSuccessfulTests;
    private static int numberFailedTests;

    public void increasePassedTest() {
        numberPassedTests++;
    }

    public void increaseSuccessfulTest() {
        numberSuccessfulTests++;
    }

    public void increaseFailedTest() {
        numberFailedTests++;
    }

    public void printStatistics() {
        System.out.printf("Всего тестов пройдено: %d\n", numberPassedTests);
        System.out.printf("Тестов пройдено успешно: %d\n", numberSuccessfulTests);
        System.out.printf("Тестов упало с ошибкой: %d\n", numberFailedTests);
    }
}
