package com.mtsmda.password_generator;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tester {

	public static void test(Class<?> class1) {
		Method[] declaredMethods = class1.getDeclaredMethods();
		if (declaredMethods.length > 0) {
			for (Method method : declaredMethods) {
				if (method.getName().equals("main")) {
					continue;
				}
				try {
					System.out.print(method.getName() + "\t - ");
					method.setAccessible(true);
					method.invoke(class1.newInstance());
					method.setAccessible(false);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
	}

}