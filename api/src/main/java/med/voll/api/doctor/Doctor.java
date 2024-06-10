package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.address.Address;
import med.voll.api.address.DataAddress;



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
    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;


    public Doctor(DataDoctor dataDoctor){
        this.name=dataDoctor.name();
        this.email=dataDoctor.email();
        this.phone=dataDoctor.phone();
        this.document=dataDoctor.document();
        this.specialty=dataDoctor.specialty();
        this.address=new Address(dataDoctor.address());
    }

}
