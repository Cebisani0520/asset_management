package za.co.common.dto.department;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DepartmentDto (
        Long id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt)
        { }

