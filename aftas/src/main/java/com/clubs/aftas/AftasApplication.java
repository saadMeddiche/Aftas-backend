package com.clubs.aftas;

import com.clubs.aftas.services.FishService;
import com.clubs.aftas.services.LevelService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@AllArgsConstructor
public class AftasApplication {

	private final LevelService levelService;

	private final FishService fishService;

	public static void main(String[] args) {
		SpringApplication.run(AftasApplication.class, args);
	}

	@Bean
	public ApplicationRunner createDefaultData() {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				levelService.createDefaultLevels();
				fishService.createDefaultFishes();
			}
		};
	}


}
