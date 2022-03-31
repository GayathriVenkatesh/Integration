package net.javaguides.springboot.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.javaguides.springboot.model.Followup;
import net.javaguides.springboot.repository.FollowupRepository;

@Configuration
public class FollowupConfig {
    @Bean
    CommandLineRunner commandLineRunner4(FollowupRepository repo) {

        return args -> {
            Followup f1 = new Followup(
				1254L, LocalDate.of(2000, 9, 5),
                "Indiranagar",
                LocalDate.of(2000, 9, 5),
                true
			);

            Followup f2 = new Followup(
				1274L, LocalDate.of(2010, 9, 5),
                "Koramangala",
                LocalDate.of(2010, 9, 5),
                false
			);

            repo.saveAll(List.of(f1, f2));
        };
    } 
}