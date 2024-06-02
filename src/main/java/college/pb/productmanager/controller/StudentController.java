package college.pb.productmanager.controller;

import static org.springframework.http.HttpStatus.CREATED;

import college.pb.productmanager.mappers.StudentMapper;
import college.pb.productmanager.model.dto.StudentDto;
import college.pb.productmanager.service.CourseService;
import college.pb.productmanager.service.StudentService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return studentService.getById(id)
            .map(student -> ResponseEntity.ok(StudentMapper.INSTANCE.toStudentDto(student)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        var student = StudentMapper.INSTANCE.toStudent(studentDto);
        return ResponseEntity.status(CREATED).body(studentService.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        return studentService.getById(id)
            .map(student -> {
                student.setName(studentDto.name());
                student.setCourses(studentDto.courseIds()
                    .stream()
                    .map(courseId -> courseService.getById(courseId).orElseThrow())
                    .collect(Collectors.toSet()));
                return ResponseEntity.ok(studentService.save(student));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentService.existsById(id)) {
            studentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
