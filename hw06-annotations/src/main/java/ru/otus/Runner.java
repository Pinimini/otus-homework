package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.reflection.ReflectionHelper;
import ru.otus.statistics.Statistics;
import ru.otus.test.TestClass;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static final List<Method> testMethods = new ArrayList<>();
    private static final List<Method> beforeMethods = new ArrayList<>();
    private static final List<Method> afterMethods = new ArrayList<>();
    private static final Statistics statistics = new Statistics();

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.runTest(TestClass.class);
        statistics.printStatistics();
    }

    private void runTest(Class<TestClass> clazz) {
        getAnnotatedMethods(clazz);
        for (Method method :
                testMethods) {
            TestClass instance = ReflectionHelper.instantiate(clazz);

            if (!beforeMethods.isEmpty()) {
                try {
                    beforeMethods.forEach(m -> ReflectionHelper.callMethod(instance, m.getName()));
                } catch (Throwable t) {
                    throw new RuntimeException("Ошибка при выполнении метода с аннотацией @Before");
                }
            }
            try {
                statistics.increasePassedTest();
                String methodName = method.getName();
                System.out.printf("Выполняется тест: %s\n", methodName);
                ReflectionHelper.callMethod(instance, methodName);
                statistics.increaseSuccessfulTest();
                System.out.println("Тест пройден успешно");
            } catch (Throwable t) {
                statistics.increaseFailedTest();
                System.out.println("Тест упал с ошибкой");
                t.printStackTrace();
            }
            if (!afterMethods.isEmpty()) {
                try {
                    afterMethods.forEach(m -> ReflectionHelper.callMethod(instance, m.getName()));
                } catch (Throwable t) {
                    throw new RuntimeException("Ошибка при выполнении метода с аннотацией @After");
                }
            }
        }
    }

    private void getAnnotatedMethods(Class<TestClass> clazz) {
        for (Method method :
                clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }
    }
}
