package net.javaguides.springboot.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Followup;

@Repository

public interface FollowupRepository extends JpaRepository<Followup, Long> {
    
    Optional<Followup> findByFollowupId(Long followupId);

    // @Query("SELECT p FROM Followup p WHERE str(p.awwId) LIKE %?1% AND str(p.completed) LIKE %?2%")
    // public List<Followup> findByKeyword(String awwId, String completed);

    // @Query("SELECT p FROM Followup p WHERE str(p.patient) NOT NULL")
    // public List<Followup> findBySamId(Long samId);

    @Query("SELECT p.height, p.weight, p.muac, p.growthStatus FROM Followup p WHERE str(p.followupId) LIKE ?1")
    public Object findHealthRecordById(Long followupId);

}
