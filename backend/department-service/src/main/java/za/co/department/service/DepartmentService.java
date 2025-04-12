package za.co.department.service;

import org.springframework.stereotype.Service;
import za.co.common.dto.department.DepartmentRequest;
import za.co.department.entity.Department;
import za.co.department.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void createDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .name(departmentRequest.name())
                .description(departmentRequest.description())
                .build();

        departmentRepository.save(department);
    }
}
