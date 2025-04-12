package za.co.department.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.common.dto.department.DepartmentRequest;
import za.co.department.service.DepartmentService;

@RestController
@RequestMapping("api/v1/departments")
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public void createDepartment(@RequestBody DepartmentRequest departmentRequest){
        log.info("New department creation {}", departmentRequest);
        departmentService.createDepartment(departmentRequest);

    }
}
