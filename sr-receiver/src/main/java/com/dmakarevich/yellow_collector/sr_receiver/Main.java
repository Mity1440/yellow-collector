package com.dmakarevich.yellow_collector.sr_receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ConfigurationPropertiesScan("com.dmakarevich.yellow_collector.sr_receiver.config")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
