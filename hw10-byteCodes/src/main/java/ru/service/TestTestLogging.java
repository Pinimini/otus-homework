package ru.service;

import ru.annotation.Log;
import ru.logger.TestLoggingInterface;

public class TestTestLogging implements TestLoggingInterface {

    @Override
    @Log
    public void calculation(int param) {
        //doSomething
    }

    @Override
    public void calculation(int param1, int param2) {
        //doSomething
    }

    @Override
    @Log
    public void calculation(int param1, String param2) {
        //doSomething
    }

    @Override
    @Log
    public void calculation() {
        //doSomething
    }

}
