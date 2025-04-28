package za.co.department.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.common.dto.department.DepartmentCreateDto;
import za.co.common.dto.department.DepartmentDto;
import za.co.common.dto.department.DepartmentUpdateDto;
import za.co.department.service.DepartmentService;

@RestController
@RequestMapping("api/v1/departments")
@Slf4j
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentCreateDto departmentCreateDto){
        return new ResponseEntity<>(departmentService.createDepartment(departmentCreateDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentDto>> getAllDepartments(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10) Pageable pageable) {

        // Search Department by name or getAll departments by default
        Page<DepartmentDto> departmentDtoPage = name != null && !name.isEmpty()
                ? departmentService.searchDepartments(name, pageable)
                : departmentService.getAllDepartments(pageable);

        return ResponseEntity.ok(departmentDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentUpdateDto departmentUpdateDto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, departmentUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
