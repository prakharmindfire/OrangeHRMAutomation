package com.qa.util;

import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.github.javafaker.Faker;
import com.qa.base.TestBase;

public class FakeDataProvider extends TestBase {
	private static Faker faker = new Faker(Locale.US);
	
	public static String getGender() {
		 Random random = new Random();
		 if(random.nextBoolean()) {
			 return "Male";
		 }else {
			 return "Female";
		 }
	}
	
	public static  String getFirstName() {
		return faker.name().firstName();
	}

	public static String getLastName() {
		return faker.name().lastName();
	}

	public static String getMRN() {
		return faker.bothify("MRN?????###");
	}

	public static String getACC() {
		return faker.bothify("ACC?????###");
	}
	
	public static String getSSN() {
		return faker.idNumber().ssnValid();
	}


	public static String getEmail() {
		return faker.bothify("????##@navihealth.com");
	}
	
	public static String getDispoCode() {
		return faker.bothify("Dispo????##");
	}

	public static String getPhoneNumber() {
		return RandomStringUtils.randomNumeric(10).replaceAll("0", "1");
	}
	
	public static String getStateCode() {
		return faker.address().stateAbbr();
	}
	public static String getCity() {
		return faker.address().city();
	}
	
	
	public static String getAddress1() {
		return faker.address().buildingNumber();
	}
	
	public static String getAddress2() {
		return faker.address().streetAddress();
	}
	
	public static String getZipCode(String stateCode) {
		return faker.address().zipCodeByState(stateCode);
	}
	
	public static String getDisease() {
		return faker.medical().diseaseName();
	}
	
	public static String getInsurer() {
		return faker.harryPotter().character();
	}
	
	public static String getPlanId() {
		return faker.numerify("Plan###");
	}
	
	
	
}
