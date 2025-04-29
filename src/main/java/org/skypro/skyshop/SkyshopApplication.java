package org.skypro.skyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.skypro.skyshop")
public class SkyshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyshopApplication.class, args);
	}


}
