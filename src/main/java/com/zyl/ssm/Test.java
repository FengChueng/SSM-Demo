package com.zyl.ssm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static Logger LOGGER = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) {
//		for (int i = 0; i < 1000; i++) {
//			LOGGER.debug("{}","hello1");
//			LOGGER.info("{}","hello2");
//			LOGGER.warn("{}","hello3");
//			LOGGER.error("{}","hello4");
//			LOGGER.trace("{}","hello5");
//		}
		
		
	
		
		
		try {
			Test test = Test.class.newInstance();
			Method method = Test.class.getDeclaredMethod("testa");
			method.setAccessible(true);
			method.invoke(test);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void testa(){
		System.out.println("test.testA");
	}
	
	
}
