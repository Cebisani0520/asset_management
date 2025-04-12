package za.co.common.dto.department;

import jakarta.validation.constraints.NotBlank;

public record DepartmentRequest (
        @NotBlank(message = "Department name is required")
        String name,
        @NotBlank(message = "Department description is required")
        String description) {

}
