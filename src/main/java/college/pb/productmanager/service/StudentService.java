package college.pb.productmanager.service;

import college.pb.productmanager.model.dto.StudentDto;
import college.pb.productmanager.model.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto save(Student student);

    Optional<Student> getById(Long studentId);

    boolean existsById(Long id);

    void deleteById(Long id);
}
