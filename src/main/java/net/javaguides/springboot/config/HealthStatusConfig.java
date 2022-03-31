package net.javaguides.springboot.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.javaguides.springboot.model.HealthStatus;
import net.javaguides.springboot.repository.HealthStatusRepository;

@Configuration
public class HealthStatusConfig {
    @Bean
    CommandLineRunner commandLineRunner5(HealthStatusRepository repo) {

        return args -> {
            HealthStatus hs1 = new HealthStatus(
				LocalDate.of(2010, 9, 4),
                145.0, 32.0,
                12.0, "Status 1",
                "cough"
			);

            HealthStatus hs2 = new HealthStatus(
				LocalDate.of(2010, 8, 6),
                147.0, 22.0, 
                12.0, "Status 2",
                "nil"
			);

            repo.saveAll(List.of(hs1, hs2));
        };
    } 
}