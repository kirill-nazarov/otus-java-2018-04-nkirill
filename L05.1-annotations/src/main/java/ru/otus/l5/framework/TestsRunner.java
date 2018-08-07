package ru.otus.l5.framework;

import ru.otus.l5.framework.annotations.After;
import ru.otus.l5.framework.annotations.Before;
import ru.otus.l5.framework.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.ClassLoader.getSystemClassLoader;

public class TestsRunner {

    public static void runTestsInClass(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(className);
        ClassLoader classLoader = getSystemClassLoader();
        Class clazz = classLoader.loadClass(className);
        executeMethodsInClass(clazz);
    }

    public static void runTestsInPackage(String packageName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(packageName);
        List<Class> classes;
        String packageNameRepl = packageName.replaceAll("\\.", "/");
        URL packageURL = Thread.currentThread().getContextClassLoader().getResource(packageNameRepl);
        classes = getClassesByURL(packageURL, packageName);
        for (Class clazz : classes) {
            executeMethodsInClass(clazz);
        }
    }

    private static List<Class> getClassesByURL(URL packageURL, String packageName) {
        List<Class> classes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) packageURL.getContent()));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.endsWith(".class")) {
                    classes.add(Class.forName(packageName + "." + line.substring(0, line.lastIndexOf('.'))));
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Cannot read from package " + packageName);
            e.printStackTrace();
        }
        return classes;
    }

    private static void executeMethodsInClass(Class clazz) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method[] methods = clazz.getMethods();
        List<Method> list = Arrays.asList(methods);
        List<Method> beforeList = new ArrayList<>();
        List<Method> testList = new ArrayList<>();
        List<Method> afterList = new ArrayList<>();

        for (Method m : list) {
            if (m.isAnnotationPresent(Before.class))
                beforeList.add(m);
            if (m.isAnnotationPresent(Test.class))
                testList.add(m);
            if (m.isAnnotationPresent(After.class))
                afterList.add(m);
        }


        try {
            Object instance1 = ReflectionHelper.instantiate(clazz);
            for (Method m : beforeList) {
                ReflectionHelper.callMethod(instance1, m.getName());
            }

            Object instance2 = ReflectionHelper.instantiate(clazz);
            for (Method m : testList) {
                ReflectionHelper.callMethod(instance2, m.getName());
            }

            Object instance3 = ReflectionHelper.instantiate(clazz);
            for (Method m : afterList) {
                ReflectionHelper.callMethod(instance3, m.getName());
            }
        } catch (Exception ex) {
            System.out.println("Exception caught during invocation of a test method. Exception details:" + ex.getCause().getLocalizedMessage());
        }


    }

}
