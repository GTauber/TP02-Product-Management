package college.pb.productmanager.mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

import college.pb.productmanager.model.dto.CourseDto;
import college.pb.productmanager.model.dto.StudentDto;
import college.pb.productmanager.model.entity.Course;
import college.pb.productmanager.model.entity.Student;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "students", source = "students")
    CourseDto toCourseDto(Course course);

    @Mapping(target = "students", ignore = true)
    Course toCourse(CourseDto courseDto);

    default Set<StudentDto> studentSetToStudentDTOSet(Set<Student> students) {
        return students.stream()
            .map(StudentMapper.INSTANCE::toStudentDto)
            .collect(Collectors.toSet());
    }
}
