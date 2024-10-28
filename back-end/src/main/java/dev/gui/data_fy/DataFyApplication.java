package dev.gui.data_fy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DataFyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataFyApplication.class, args);
	}

}
