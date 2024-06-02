package college.pb.productmanager.mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

import college.pb.productmanager.model.dto.StudentDto;
import college.pb.productmanager.model.entity.Course;
import college.pb.productmanager.model.entity.Student;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "courseIds", source = "courses")
    StudentDto toStudentDto(Student student);

    Student toStudent(StudentDto studentDto);

    default Set<Long> courseSetToCourseIdSet(Set<Course> courses) {
        if (courses == null) {
            return null;
        }
        return courses.stream().map(Course::getId).collect(Collectors.toSet());
    }
}
