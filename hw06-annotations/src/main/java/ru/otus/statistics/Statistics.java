package ru.otus.statistics;

public class Statistics{
    private static int numberPassedTests;
    private static int numberSuccessfulTests;
    private static int numberFailedTests;

    public static void increasePassedTest() {
        numberPassedTests++;
    }

    public static void increaseSuccessfulTest() {
        numberSuccessfulTests++;
    }

    public static void increaseFailedTest() {
        numberFailedTests++;
    }

    public static void printStatistics() {
        System.out.printf("Всего тестов пройдено: %d\n", numberPassedTests);
        System.out.printf("Тестов пройдено успешно: %d\n", numberSuccessfulTests);
        System.out.printf("Тестов упало с ошибкой: %d\n", numberFailedTests);
    }
}
