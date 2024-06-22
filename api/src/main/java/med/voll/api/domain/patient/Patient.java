package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;


@Entity
@Table(name="patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Boolean active;
    @Enumerated(EnumType.STRING)

    @Embedded
    private Address address;


    public Patient(DataPatient dataPatient){
        this.active=true;
        this.name=dataPatient.name();
        this.email=dataPatient.email();
        this.phone=dataPatient.phone();
        this.address=new Address(dataPatient.address());
    }




    public void update(PatientUpdateDTO dataDoctor){

        if(dataDoctor.name() != null){
            this.name=dataDoctor.name();
        }
        if(dataDoctor.address()!=null){
            this.address.update(dataDoctor.address());
        }

    }

    public void updateActive(){
        this.active=false;
    }




}
