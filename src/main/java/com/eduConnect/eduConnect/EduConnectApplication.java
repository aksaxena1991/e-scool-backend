package com.eduConnect.eduConnect;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EduConnectApplication {

	private final static Logger logger = LoggerFactory.getLogger(EduConnectApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EduConnectApplication.class, args);
	}

}
