package at.spengergasse.boignerdbiaptitudetest.domain;

import at.spengergasse.boignerdbiaptitudetest.service.EntryService;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document
public class Student {
    @Id
    private ObjectId _id;

    private String firstname;
    private String lastname;
    private String email;
    private String version;
    @DBRef
    private Entry entry;

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return firstname.equals(student.firstname) && lastname.equals(student.lastname) && email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, email);
    }
}
