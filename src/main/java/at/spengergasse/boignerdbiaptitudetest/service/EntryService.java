package at.spengergasse.boignerdbiaptitudetest.service;

import at.spengergasse.boignerdbiaptitudetest.domain.Department;
import at.spengergasse.boignerdbiaptitudetest.domain.Entry;
import at.spengergasse.boignerdbiaptitudetest.domain.Student;
import at.spengergasse.boignerdbiaptitudetest.persistence.DepartmentRepository;
import at.spengergasse.boignerdbiaptitudetest.persistence.EntryRepository;
import at.spengergasse.boignerdbiaptitudetest.persistence.StudentRepository;
import at.spengergasse.boignerdbiaptitudetest.presentation.web.CreateEntryForm;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Transactional(readOnly = true)
@Service
public class EntryService {

    private final EntryRepository entryRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;

    public Optional<Entry> getEntry(ObjectId id){
        return entryRepository.findById(id);
    }

    public List<Entry> getEntries(){
        return entryRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Entry createEntry(CreateEntryForm form, ObjectId departmentId){
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalArgumentException(String.format("Can't find department %s", departmentId)));

        Student student = Student.builder()
                .firstname(form.getFirstname())
                .lastname(form.getLastname())
                .email(form.getEmail())
                .version("Version1")
                .build();

        studentRepository.save(student);

        Entry entry = Entry.builder()
                .time(form.getTime())
                .student(student)
                .meetingLink(form.getMeetingLink())
                .uploads(form.getUploads())
                .department(department)
                .version("Version1")
            .build();
        entryRepository.save(entry);
        department.getEntries().add(entry);
        departmentRepository.save(department);
        return entry;
    }

    @Transactional(readOnly = false)
    public Entry updateEntry(ObjectId departmentId, ObjectId entryId, CreateEntryForm form){
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalArgumentException(String.format("Can't find department with id %s", departmentId)));
        Entry entry = department.getEntries().stream().filter(e -> e.get_id().equals(entryId)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format("Can't find entry with id %s", entryId)));

        entry.getStudent().setFirstname(form.getFirstname());
        entry.getStudent().setLastname(form.getLastname());
        entry.getStudent().setEmail(form.getEmail());
        entry.setTime(form.getTime());
        entry.setUploads(form.getUploads());
        entry.setMeetingLink(form.getMeetingLink());

        departmentRepository.save(department);
        entryRepository.save(entry);
        studentRepository.save(entry.getStudent());
        return entry;
    }

    @Transactional(readOnly = false)
    public void removeEntry(ObjectId departmentId, ObjectId entryId){
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalArgumentException(String.format("Can't find department with id %s", departmentId)));
        Entry entry = department.getEntries().stream().filter(e -> e.get_id().equals(entryId)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format("Can't find entry with id %s", entryId)));
        department.getEntries().remove(entry);
        studentRepository.delete(entry.getStudent());
        entryRepository.delete(entry);
        departmentRepository.save(department);
    }
}
