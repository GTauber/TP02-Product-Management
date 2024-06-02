package college.pb.productmanager.service;

import college.pb.productmanager.model.dto.CourseDto;
import college.pb.productmanager.model.entity.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<CourseDto> getAllCourses();

    CourseDto save(Course course);

    Optional<Course> getById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
