package at.spengergasse.boignerdbiaptitudetest.presentation.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class CreateEntryForm {
    private LocalTime time;
    @NotNull(message = "First name must not be null!")
    @NotBlank(message = "First name must not be blank!")
    private String firstname;
    @NotNull(message = "Last name must not be null!")
    @NotBlank(message = "Last name must not be blank!")
    private String lastname;
    @NotNull(message = "The meeting link must not be null!")
    @NotBlank(message = "The meeting link must not be blank!")
    private String meetingLink;
    @NotNull(message = "Email must not be null!")
    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Email is not valid!")
    private String email;
    @NotNull(message = "The upload link must not be null!")
    @NotBlank(message = "The upload link must not be blank!")
    private String uploads;
}