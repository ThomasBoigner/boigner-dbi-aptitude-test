package at.spengergasse.boignerdbiaptitudetest.persistence;

import at.spengergasse.boignerdbiaptitudetest.domain.Department;
import at.spengergasse.boignerdbiaptitudetest.domain.Entry;
import at.spengergasse.boignerdbiaptitudetest.domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntryRepository entryRepository;

    @BeforeEach
    void init(){
        departmentRepository.deleteAll();
        studentRepository.deleteAll();
        entryRepository.deleteAll();
    }

    @Test
    void ensureAddDepartmentWorksProperly(){
        //given
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

        //when
        studentRepository.save(student1);
        studentRepository.save(student2);
        departmentRepository.save(department);

        //then
        assertThat(department.get_id()).isNotNull();
        assertThat(departmentRepository.findById(department.get_id()).get()).isEqualTo(department);
    }

    @Test
    void ensureReadDepartmentWorksProperly(){
        //given
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

        //when
        studentRepository.save(student1);
        studentRepository.save(student2);
        departmentRepository.save(department);
        Department actual = departmentRepository.findById(department.get_id()).get();

        //then
        assertThat(department).isEqualTo(actual);
    }

    @Test
    void ensureDeleteDepartmentWorksProperly(){
        departmentRepository.deleteAll();
        //given
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

        //when
        studentRepository.save(student1);
        studentRepository.save(student2);
        departmentRepository.save(department);
        departmentRepository.deleteById(department.get_id());
        List<Department> departments = departmentRepository.findAll();

        assertThat(departments.size()).isEqualTo(0);
        assertThat(departments).doesNotContain(department);
    }

    @Test
    void ensureUpdateDepartmentWorksProperly(){
        //given
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

        //when
        studentRepository.save(student1);
        studentRepository.save(student2);
        departmentRepository.save(department);
        department.setName("New Name");
        departmentRepository.save(department);

        assertThat(departmentRepository.findById(department.get_id()).get().getName()).isEqualTo("New Name");
    }
}
