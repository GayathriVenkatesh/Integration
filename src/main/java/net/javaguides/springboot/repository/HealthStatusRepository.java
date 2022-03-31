package net.javaguides.springboot.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.HealthStatus;

@Repository

public interface HealthStatusRepository extends JpaRepository<HealthStatus, Long> {
    
    Optional<HealthStatus> findByHsId(Long hsId);

    @Query("SELECT p FROM HealthStatus p WHERE str(p.hsId) LIKE %?1%")
    public List<HealthStatus> findByKeyword(String hsId);
}
