package com.safetynet.safetynetalerts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SafetynetalertsApplication {
	private static final Logger logger = LogManager.getLogger("SafetynetalertsApplication");
	public static void main(String[] args) {
		logger.info("SafetyNet Alert Server starts");
		SpringApplication.run(SafetynetalertsApplication.class, args);
	}
}
