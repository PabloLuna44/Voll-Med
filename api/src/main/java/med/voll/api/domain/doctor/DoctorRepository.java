package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    Page<Doctor> findByActiveTrue(Pageable pageable);

    Optional<Doctor> findByIdAndActiveIsTrue(Long id);

    @Query("""
        SELECT d FROM Doctor d 
        WHERE 
        d.active=true 
        AND d.specialty=:specialty
        AND d.id NOT IN(
        SELECT c.doctor.id FROM Consultation c
        WHERE c.consultationDate=:date
        AND c.active=true
        )
        ORDER BY RAND()
        LIMIT 1
""")
    Optional<Doctor> findDoctorWithSpecialtyInDate(Specialty specialty, LocalDateTime date);

}
