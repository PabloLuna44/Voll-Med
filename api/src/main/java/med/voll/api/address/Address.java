package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {

    private String street;
    private String state;
    private String city;
    private String district;
    private String number;
    private String complement;

    public Address(DataAddress dataAddress){
        this.street=dataAddress.street();
        this.state=dataAddress.state();
        this.city=dataAddress.city();
        this.district=dataAddress.district();
        this.number=dataAddress.number();
        this.complement=dataAddress.complement();

    }

}
