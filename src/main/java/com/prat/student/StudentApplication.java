package com.prat.student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value={"validation.properties", "application.properties"})
public class StudentApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

}
