package ru.logger;

import ru.annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ioc {
    private Ioc() {
    }

    public static TestLoggingInterface createTestLoggingInterface(Object object) {

        InvocationHandler invocationHandler = new TestInvocationHandler(object);
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class[]{TestLoggingInterface.class}, invocationHandler);
    }

    static class TestInvocationHandler implements InvocationHandler {
        private final Object object;
        private final Set<Method> annotatedMethods;

        TestInvocationHandler(Object object) {
            this.object = object;
            annotatedMethods = getAnnotatedMethods(object);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object invoke;
            invoke = method.invoke(object, args);

            if (isAnnotatedMethod(method)) {
                printLog(method, args);
            }
            return invoke;
        }

        //Проверка, содержится ли вызываемый метод в списке методов, аннотированных @Log
        private boolean isAnnotatedMethod(Method method) {
            for (Method annotatedMethod :
                    annotatedMethods) {
                if (annotatedMethod.getName().equals(method.getName())
                        && Arrays.equals(annotatedMethod.getParameterTypes(), method.getParameterTypes())) {
                    return true;
                }
            }
            return false;
        }

        private Set<Method> getAnnotatedMethods(Object object) {
            return Stream.of(object.getClass().getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .collect(Collectors.toSet());
        }

        private void printLog(Method method, Object[] args) {
            String log = String.format("executed method: %s, param: %s",
                    method.getName(), args != null ? Arrays.toString(args) : "[]");
            System.out.println(log);
        }
    }
}
