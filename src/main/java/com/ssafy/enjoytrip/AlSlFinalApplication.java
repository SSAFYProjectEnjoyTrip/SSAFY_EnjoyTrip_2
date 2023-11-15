package com.ssafy.enjoytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages={"com.ssafy.enjoytrip.*.dao", "com.ssafy.enjoytrip.config", "com.ssafy.enjoytrip.interceptor", "com.ssafy.enjoytrip.util"})
@SpringBootApplication
public class AlSlFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlSlFinalApplication.class, args);
	}

}
