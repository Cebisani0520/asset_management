package za.co.department.mapper;

import org.mapstruct.*;
import za.co.common.dto.department.DepartmentCreateDto;
import za.co.common.dto.department.DepartmentDto;
import za.co.common.dto.department.DepartmentUpdateDto;
import za.co.common.enums.EventTypes;
import za.co.common.events.DepartmentEvent;
import za.co.department.avro.DepartmentEventAvro;
import za.co.department.avro.EventType;
import za.co.department.entity.Department;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto mapToDto(Department department);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Department toEntity(DepartmentCreateDto departmentCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DepartmentUpdateDto updateDto, @MappingTarget Department department);

//    default DepartmentEvent toEvent(Department department, EventTypes eventType) {
//        return new DepartmentEvent(
//                eventType,
//                department.getId(),
//                department.getName(),
//                department.getDescription()
//        );
//    }
    default DepartmentEventAvro toEvent(Department department, EventType eventType) {
        return new DepartmentEventAvro(
                UUID.randomUUID().toString(),
                eventType,
                "DEPARTMENT_EVENT",
                LocalDateTime.now().toInstant(ZoneOffset.of("+02:00")),
                department.getId(),
                department.getName(),
                "This is coming from the DEPARTMENT_SERVICE"

        );
    }

}
