package at.spengergasse.boignerdbiaptitudetest.service;

import at.spengergasse.boignerdbiaptitudetest.domain.Department;
import at.spengergasse.boignerdbiaptitudetest.domain.Entry;
import at.spengergasse.boignerdbiaptitudetest.persistence.DepartmentRepository;
import at.spengergasse.boignerdbiaptitudetest.persistence.EntryRepository;
import at.spengergasse.boignerdbiaptitudetest.persistence.StudentRepository;
import at.spengergasse.boignerdbiaptitudetest.presentation.web.CreateDepartmentForm;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor

@Transactional(readOnly = true)
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EntryRepository entryRepository;
    private final StudentRepository studentRepository;

    public Optional<Department> getDepartment(ObjectId id){
       return departmentRepository.findById(id);
   }

    public List<Department> getDepartments(){
       return departmentRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Department createDepartment(CreateDepartmentForm form){
        return departmentRepository.save(Department.builder()
                        .name(form.getName())
                        .entries(new ArrayList<>())
                        .version("Version1")
                        .date(LocalDate.now())
                .build());
    }

    @Transactional(readOnly = false)
    public Department updateDepartment(ObjectId id, CreateDepartmentForm form){
        Optional<Department> entity = getDepartment(id);

        if (entity.isPresent()){
            Department department = entity.get();
            department.setName(form.getName());
            return departmentRepository.save(department);
        }
        throw new IllegalArgumentException(String.format("Department with id %s can't be found", id));
    }

    @Transactional(readOnly = false)
    public void deleteDepartment(ObjectId id){
        Department department = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Can't find department %s", id)));
        department.getEntries().forEach(entry -> studentRepository.delete(entry.getStudent()));
        department.getEntries().forEach(entryRepository::delete);
        departmentRepository.deleteById(id);
    }
}
