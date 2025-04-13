package za.co.department.mapper;

import org.mapstruct.*;
import za.co.common.dto.department.DepartmentCreateDto;
import za.co.common.dto.department.DepartmentDto;
import za.co.common.dto.department.DepartmentUpdateDto;
import za.co.department.entity.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto mapToDto(Department department);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Department toEntity(DepartmentCreateDto departmentCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DepartmentUpdateDto updateDto, @MappingTarget Department department);

}
