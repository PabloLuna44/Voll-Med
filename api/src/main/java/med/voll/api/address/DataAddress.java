package med.voll.api.address;

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
}
