package at.spengergasse.boignerdbiaptitudetest.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document
public class Entry {
    @Id
    private ObjectId _id;
    private LocalTime time;
    @DBRef
    private Student student;
    private String meetingLink;
    private String uploads;
    @DBRef
    private Department department;

    private String version;

    @Override
    public String toString() {
        return "Entry{" +
                "time=" + time +
                ", meetingLink='" + meetingLink + '\'' +
                ", uploads='" + uploads + '\'' +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry entry)) return false;
        return time.equals(entry.time) && meetingLink.equals(entry.meetingLink) && uploads.equals(entry.uploads);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, meetingLink, uploads);
    }
}
