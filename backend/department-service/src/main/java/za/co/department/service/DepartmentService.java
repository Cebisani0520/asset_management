package za.co.department.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.common.dto.department.DepartmentCreateDto;
import za.co.common.dto.department.DepartmentDto;
import za.co.common.dto.department.DepartmentUpdateDto;
import za.co.common.dto.department.exception.DepartmentNotFoundException;
import za.co.common.enums.EventTypes;
import za.co.common.events.DepartmentEvent;
import za.co.department.entity.Department;
import za.co.department.kafka.DepartmentEventPublisher;
import za.co.department.mapper.DepartmentMapper;
import za.co.department.repository.DepartmentRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final DepartmentEventPublisher departmentEventPublisher;

    @Transactional
    public DepartmentDto createDepartment(DepartmentCreateDto departmentCreateDto) {

        Department department = departmentMapper.toEntity(departmentCreateDto);

        log.info("Saving department...");
        Department savedDepartment = departmentRepository.save(department);
        log.info("Successfully saved to department to database.");

        //Publish Kafka Event
        DepartmentEvent departmentEvent = departmentMapper.toEvent(department, EventTypes.CREATED);
        departmentEventPublisher.publish(departmentEvent);

        return departmentMapper.mapToDto(savedDepartment);

    }
    
    @Transactional(readOnly = true)
    public Page<DepartmentDto> getAllDepartments(Pageable pageable){
        log.info("Finding all departments...");
        return departmentRepository.findAll(pageable)
                .map(departmentMapper::mapToDto);
    }
    @Transactional(readOnly = true)
    public Page<DepartmentDto> searchDepartments(String name, Pageable pageable) {
        log.info("Searching department by {}...", name);
        return departmentRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(departmentMapper::mapToDto);
    }

    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Long id) {
        log.info("Getting department by ID...");
        return departmentRepository.findById(id)
                .map(departmentMapper::mapToDto)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
    }

    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentUpdateDto departmentUpdateDto) {
        log.info("Updating department...");
        Department department = departmentRepository.findById(id)
               .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));

        // only updates non-null fields
        departmentMapper.updateEntityFromDto(departmentUpdateDto, department);

        Department updatedDepartment =  departmentRepository.save(department);

        log.info("Successfully updated department in database.");

        //FIXME:
        //Publish Kafka Event
        DepartmentEvent departmentEvent = departmentMapper.toEvent(department, EventTypes.UPDATED);
        departmentEventPublisher.publish(departmentEvent);

        return departmentMapper.mapToDto(updatedDepartment);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        log.info("Deleting department by ID...");
        Department department = departmentRepository.findById(id)
               .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));

        // Create the event before deletion.
        DepartmentEvent departmentEvent = departmentMapper.toEvent(department, EventTypes.DELETED);

        departmentRepository.delete(department);

        log.info("Successfully deleted department in database.");

        // Publish Kafka Event
        departmentEventPublisher.publish(departmentEvent);

    }

}
