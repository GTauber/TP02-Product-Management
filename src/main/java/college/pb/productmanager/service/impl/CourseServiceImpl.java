package college.pb.productmanager.service.impl;

import college.pb.productmanager.mappers.CourseMapper;
import college.pb.productmanager.model.dto.CourseDto;
import college.pb.productmanager.model.entity.Course;
import college.pb.productmanager.repository.CourseRepository;
import college.pb.productmanager.service.CourseService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseDto> getAllCourses() {
        log.info("Fetching all courses");
        return courseRepository.findAll()
            .stream()
            .map(CourseMapper.INSTANCE::toCourseDto)
            .toList();
    }

    @Override
    @CachePut(value = "courses", key = "#course.id")
    public CourseDto save(Course course) {
        log.info("Saving course: {}", course);
        return CourseMapper.INSTANCE.toCourseDto(courseRepository.save(course));
    }

    @Override
    @Cacheable(value = "courses", key = "#id")
    public Optional<Course> getById(Long id) {
        log.info("Fetching course by id: {}", id);
        return courseRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        log.info("Checking if course exists by id: {}", id);
        return courseRepository.existsById(id);
    }

    @Override
    @CacheEvict(value = "courses", key = "#id")
    public void deleteById(Long id) {
        log.info("Deleting course by id: {}", id);
        courseRepository.deleteById(id);
    }
}
