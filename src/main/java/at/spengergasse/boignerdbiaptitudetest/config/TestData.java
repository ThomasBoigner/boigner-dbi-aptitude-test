package at.spengergasse.boignerdbiaptitudetest.config;

import at.spengergasse.boignerdbiaptitudetest.domain.Department;
import at.spengergasse.boignerdbiaptitudetest.domain.Entry;
import at.spengergasse.boignerdbiaptitudetest.domain.Student;
import at.spengergasse.boignerdbiaptitudetest.persistence.DepartmentRepository;
import at.spengergasse.boignerdbiaptitudetest.persistence.EntryRepository;
import at.spengergasse.boignerdbiaptitudetest.persistence.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@Component
public class TestData {
    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;
    private final EntryRepository entryRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event){
        Student student1 = Student.builder()
                .firstname("Thomas")
                .lastname("Boigner")
                .email("BOI19349@spengergasse.at")
                .build();

        Student student2 = Student.builder()
                .firstname("Philipp")
                .lastname("Cserich")
                .email("CSE19583@spengergasse.at")
                .build();

        Entry eintrag1 = Entry.builder()
                .student(student1)
                .meetingLink("link")
                .uploads("link1")
                .time(LocalTime.of(0, 0))
                .build();

        Entry eintrag2 = Entry.builder()
                .student(student2)
                .meetingLink("link")
                .uploads("link1")
                .time(LocalTime.of(0, 0))
                .build();

        Department department = Department.builder()
                .name("Art und design - Interior- und surfacedesign")
                .date(LocalDate.of(2000,1, 1))
                .entries(new ArrayList<>(List.of(eintrag1, eintrag2)))
                .build();

        studentRepository.save(student1);
        studentRepository.save(student2);
        entryRepository.save(eintrag1);
        entryRepository.save(eintrag2);
        departmentRepository.save(department);
    }
}
