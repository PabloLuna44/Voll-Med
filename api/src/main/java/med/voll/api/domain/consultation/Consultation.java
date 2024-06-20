package med.voll.api.domain.consultation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Entity
@Table(name="consultations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Reason reason;

    private LocalDateTime consultationDate;


    public Consultation(Doctor doctor, Patient patient, LocalDateTime localDateTime) {
        this.active=true;
        this.doctor=doctor;
        this.patient=patient;
        this.consultationDate=localDateTime;
    }


    public void cancel(Boolean active,Reason reason){
        this.active=active;
        this.reason=reason;
    }
}
