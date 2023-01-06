package ru;

import ru.service.TestTestLogging;
import ru.logger.TestLoggingInterface;
import ru.logger.Ioc;

public class Main {
    public static void main(String[] args) {
        TestTestLogging testLogging = new TestTestLogging();
        TestLoggingInterface loggingInterface = Ioc.createTestLoggingInterface(testLogging);

        loggingInterface.calculation(5);
        loggingInterface.calculation(5, 10);
        loggingInterface.calculation(5, "String");
        loggingInterface.calculation();
    }
}
