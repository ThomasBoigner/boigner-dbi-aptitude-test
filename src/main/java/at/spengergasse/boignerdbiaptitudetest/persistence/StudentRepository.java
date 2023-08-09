package at.spengergasse.boignerdbiaptitudetest.persistence;

import at.spengergasse.boignerdbiaptitudetest.domain.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {
}
