package college.pb.productmanager.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import college.pb.productmanager.mappers.CourseMapper;
import college.pb.productmanager.model.dto.CourseDto;
import college.pb.productmanager.service.CourseService;
import college.pb.productmanager.service.StudentService;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/course")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;

    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        var course = CourseMapper.INSTANCE.toCourse(courseDto);
        return ResponseEntity.status(CREATED).body(courseService.save(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return courseService.getById(id)
            .map(course -> ResponseEntity.ok(CourseMapper.INSTANCE.toCourseDto(course)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        return courseService.getById(id)
            .map(course -> {
                course.setName(courseDto.name());
                var updatedCourse = courseService.save(course);
                return ResponseEntity.ok(updatedCourse);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (courseService.existsById(id)) {
            courseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Void> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        var courseOptional = courseService.getById(courseId);
        var studentOptional = studentService.getById(studentId);

        if (courseOptional.isPresent() && studentOptional.isPresent()) {
            var course = courseOptional.get();
            var student = studentOptional.get();
            course.getStudents().add(student);
            student.getCourses().add(course);
            courseService.save(course);
            studentService.save(student);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
