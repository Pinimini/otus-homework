package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.reflection.ReflectionHelper;
import ru.otus.statistics.Statistics;
import ru.otus.test.TestClass;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        runTest(TestClass.class);
        Statistics.printStatistics();
    }

    private static void runTest(Class<TestClass> clazz) {

        for (Method method :
                getAnnotatedTestMethods(clazz)) {
            TestClass instance = ReflectionHelper.instantiate(clazz);
            List<Method> annotatedBeforeMethods = getAnnotatedBeforeMethods(clazz);
            List<Method> annotatedAfterMethods = getAnnotatedAfterMethods(clazz);

            if (!annotatedBeforeMethods.isEmpty()) {
                annotatedBeforeMethods.forEach(m -> ReflectionHelper.callMethod(instance, m.getName()));
            }
            try {
                Statistics.increasePassedTest();
                String methodName = method.getName();
                System.out.printf("Выполняется тест: %s\n", methodName);
                ReflectionHelper.callMethod(instance, methodName);
                Statistics.increaseSuccessfulTest();
                System.out.println("Тест пройден успешно");
            } catch (Throwable t) {
                Statistics.increaseFailedTest();
                System.out.println("Тест упал с ошибкой");
                t.printStackTrace();
            }
            if (!annotatedAfterMethods.isEmpty()) {
                annotatedAfterMethods.forEach(m -> ReflectionHelper.callMethod(instance, m.getName()));
            }
        }
    }

    private static List<Method> getAnnotatedTestMethods(Class<TestClass> clazz) {
        List<Method> testMethods = new ArrayList<>();
        for (Method method :
                clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }
        return testMethods;
    }

    private static List<Method> getAnnotatedBeforeMethods(Class<TestClass> clazz) {
        List<Method> methods = new ArrayList<>();
        for (Method method :
                clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                methods.add(method);
            }
        }
        return methods;
    }

    private static List<Method> getAnnotatedAfterMethods(Class<TestClass> clazz) {
        List<Method> methods = new ArrayList<>();
        for (Method method :
                clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(After.class)) {
                methods.add(method);
            }
        }
        return methods;
    }
}
