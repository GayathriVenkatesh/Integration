package net.javaguides.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Patient;

@Repository
// Long will be the data type of primary key (UHID)
// If you change UHID to string, make this also string
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    // @Query("SELECT p FROM Patient p WHERE p.UHID = ?1")
    Optional<Patient> findBySamId(Long samId);

    @Query("SELECT p FROM Patient p WHERE p.name LIKE %?1% AND p.address LIKE %?2% AND p.religion LIKE %?3% AND str(p.uhId) LIKE %?4% AND str(p.samId) LIKE %?5%")
    public List<Patient> findByKeyword(String name, String address, String religion, String uhId, String sam);
}
