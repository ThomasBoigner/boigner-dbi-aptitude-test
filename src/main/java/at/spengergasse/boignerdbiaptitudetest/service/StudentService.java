package at.spengergasse.boignerdbiaptitudetest.service;

import at.spengergasse.boignerdbiaptitudetest.domain.Student;
import at.spengergasse.boignerdbiaptitudetest.persistence.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Optional<Student> getStudent(ObjectId id){
       return studentRepository.findById(id);
    }
}
