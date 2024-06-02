package college.pb.productmanager.model.dto;

import java.io.Serializable;
import java.util.Set;

public record StudentDto(Long id, String name, Set<Long> courseIds) implements Serializable {

}
