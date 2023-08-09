package at.spengergasse.boignerdbiaptitudetest.presentation.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateDepartmentForm {

    @NotNull(message = "Name must not be null!")
    @NotBlank(message = "Name must not be blank!")
    private String name;
}
