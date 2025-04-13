package za.co.common.dto.department;

import jakarta.validation.constraints.NotBlank;

public record DepartmentCreateDto(
        @NotBlank(message = "Department name is required")
        String name,
        @NotBlank(message = "Department description is required")
        String description) { }
