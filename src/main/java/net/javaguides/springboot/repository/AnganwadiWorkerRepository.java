package net.javaguides.springboot.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.AnganwadiWorker;

@Repository

public interface AnganwadiWorkerRepository extends JpaRepository<AnganwadiWorker, Long> {
    
    Optional<AnganwadiWorker> findByAwwId(Long awwId);
    
    @Query("SELECT p FROM AnganwadiWorker p WHERE p.address LIKE %?1% AND p.locality LIKE %?2%")
    public List<AnganwadiWorker> findByKeyword(String address, String locality);
}
