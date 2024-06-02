package college.pb.productmanager.model.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import college.pb.productmanager.enums.Category;
import college.pb.productmanager.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Builder;

@Builder
@JsonInclude(NON_NULL)
public record ProductDto(Long id, @NotNull String name, @NotNull @Size(min = 5, max = 255) String description,
                         @NotNull Double price, @NotNull Integer quantity, @NotNull Category category,
                         @NotNull String brand, @NotNull String image, @NotNull Status status) implements
    Serializable {
}