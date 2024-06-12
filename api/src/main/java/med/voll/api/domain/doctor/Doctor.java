package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;


@Entity
@Table(name="doctors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;


    public Doctor(DataDoctor dataDoctor){
        this.active=true;
        this.name=dataDoctor.name();
        this.email=dataDoctor.email();
        this.phone=dataDoctor.phone();
        this.document=dataDoctor.document();
        this.specialty=dataDoctor.specialty();
        this.address=new Address(dataDoctor.address());
    }


    public void update(DoctorUpdateDTO dataDoctor){

        if(dataDoctor.name() != null){
            this.name=dataDoctor.name();
        }
        if(dataDoctor.document()!=null){
            this.document=dataDoctor.document();
        }
        if(dataDoctor.address()!=null){
            this.address.update(dataDoctor.address());
        }

    }

    public void updateActive(){
        this.active=false;
    }




}
