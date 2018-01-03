package com.zyl.ssm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static Logger LOGGER = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			LOGGER.debug("{}","hello1");
			LOGGER.info("{}","hello2");
			LOGGER.warn("{}","hello3");
			LOGGER.error("{}","hello4");
			LOGGER.trace("{}","hello5");
		}
		
	}
}
