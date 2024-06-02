package college.pb.productmanager.service.impl;

import college.pb.productmanager.mappers.StudentMapper;
import college.pb.productmanager.model.dto.StudentDto;
import college.pb.productmanager.model.entity.Student;
import college.pb.productmanager.repository.StudentRepository;
import college.pb.productmanager.service.StudentService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll()
            .stream()
            .map(StudentMapper.INSTANCE::toStudentDto)
            .toList();
    }

    @Override
    public StudentDto save(Student student) {
        log.info("Saving student: {}", student);
        return StudentMapper.INSTANCE.toStudentDto(studentRepository.save(student));
    }

    @Override
    public Optional<Student> getById(Long studentId) {
        log.info("Fetching student by id: {}", studentId);
        return studentRepository.findById(studentId);
    }

    @Override
    public boolean existsById(Long id) {
        log.info("Checking if student exists by id: {}", id);
        return studentRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting student by id: {}", id);
        studentRepository.deleteById(id);
    }
}
