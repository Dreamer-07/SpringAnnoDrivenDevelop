package pers.dreamer07.springAoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class SpringAoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAoonApplication.class, args);
	}

}
