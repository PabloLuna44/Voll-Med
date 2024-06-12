package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;

public record DataAddress(
        @NotBlank
        String street,
        @NotBlank
        String state,
        @NotBlank
        String city,
        @NotBlank
        String district,
        @NotBlank
        String number,
        @NotBlank
        String complement

) {


        public DataAddress(Address address){
                this(
                  address.getStreet(),
                  address.getState(),
                  address.getCity(),
                  address.getDistrict(),
                  address.getNumber(),
                  address.getComplement()
                );
        }
}
