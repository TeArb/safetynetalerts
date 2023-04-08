package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.constants.DataLoader;
import com.safetynet.safetynetalerts.utils.ReadData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SafetynetalertsApplication {

	private static final Logger logger = LogManager.getLogger("SafetynetalertsApplication");
	public static void main(String[] args) {
		logger.info("SafetyNet Alert Server starts");
		SpringApplication.run(SafetynetalertsApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			ReadData readData = new ReadData();
			DataLoader.FIRE_STATIONS_LIST.addAll(readData.loadFireStations());
			DataLoader.MEDICAL_RECORDS_LIST.addAll(readData.loadMedicalRecords());
			DataLoader.PERSONS_LIST.addAll(readData.loadPersons());
			logger.info("Data loaded");
		};
	}
}
