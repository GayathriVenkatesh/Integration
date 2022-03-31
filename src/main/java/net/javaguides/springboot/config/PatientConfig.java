package net.javaguides.springboot.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.javaguides.springboot.model.Patient;
import net.javaguides.springboot.repository.PatientRepository;

@Configuration
public class PatientConfig {
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository repo) {

        return args -> {
            Patient aruna = new Patient(
				1234L, 4566L, "Aruna",
				LocalDate.of(2000, 9, 5),
				"9846573842", 'F', true, "Banerghatta",
				"Hindu", "None", List.of(130.0, 32.0, 12.0, 0.0)
			);

            // Patient bob = new Patient(
			// 	1234L, 4566L, "Bob",
			// 	LocalDate.of(2010, 9, 5),
			// 	"7022029113",'M', false, "Indiranagar",
			// 	"Christian", "None", List.of(130.0, 32.0, 12.2, 0.0)
			// );

            repo.saveAll(List.of(aruna));
        };
    } 
}