package za.co.department.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.department.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Page<Department> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
