package com.mevenk.typicalwebapp.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnnotationInvocationHandler implements InvocationHandler {
	private Annotation orig;
	private String attrName;
	private Object newValue;

	private AnnotationInvocationHandler(Annotation orig, String attrName, Object newValue) throws Exception {
		this.orig = orig;
		this.attrName = attrName;
		this.newValue = newValue;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// "override" the return value for the property we want
		if (method.getName().equals(attrName) && args == null)
			return newValue;

		// keep other properties and methods we want like equals() and hashCode()
		else {
			Class<?>[] paramTypes = toClassArray(args);
			return orig.getClass().getMethod(method.getName(), paramTypes).invoke(orig, args);
		}
	}

	private static Class<?>[] toClassArray(Object[] arr) {
		if (arr == null)
			return null;
		Class<?>[] classArr = new Class[arr.length];
		for (int i = 0; i < arr.length; i++)
			classArr[i] = arr[i].getClass();
		return classArr;
	}

	public static Annotation setAttrValue(Annotation anno, Class<? extends Annotation> type, String attrName,
			Object newValue) throws Exception {
		InvocationHandler handler = new AnnotationInvocationHandler(anno, attrName, newValue);
		return (Annotation) Proxy.newProxyInstance(anno.getClass().getClassLoader(), new Class[] { type }, handler);
	}

}