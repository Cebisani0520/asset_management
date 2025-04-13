package za.co.common.dto.department;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DepartmentUpdateDto(
        String name,
        String description
        ) { }
