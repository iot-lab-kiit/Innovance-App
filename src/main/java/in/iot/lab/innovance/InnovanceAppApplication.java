package in.iot.lab.innovance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class InnovanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnovanceAppApplication.class, args);
	}

}