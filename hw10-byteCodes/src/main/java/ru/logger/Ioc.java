package ru.logger;

import ru.annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

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

        TestInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object invoke;
            Method methodInstance = object.getClass()
                    .getMethod(method.getName(), method.getParameterTypes());

            if (methodInstance.isAnnotationPresent(Log.class)) {
                invoke = method.invoke(object, args);
                printLog(method, args);
                return invoke;
            } else {
                invoke = method.invoke(object, args);
                return invoke;
            }
        }

        private void printLog(Method method, Object[] args) {
            String log = String.format("executed method: %s, param: %s",
                    method.getName(), args != null ? Arrays.toString(args) : "[]");
            System.out.println(log);
        }
    }
}
