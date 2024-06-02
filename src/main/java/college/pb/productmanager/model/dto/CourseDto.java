package college.pb.productmanager.model.dto;

import java.io.Serializable;
import java.util.Set;

public record CourseDto(Long id, String name, Set<StudentDto> students) implements Serializable {

}
