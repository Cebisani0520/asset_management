package za.co.common.dto.department;

import jakarta.validation.constraints.NotBlank;

public record DepartmentDto (
        Long id,
        @NotBlank(message = "Department name is required")
        String name,
        String description)
        {}
