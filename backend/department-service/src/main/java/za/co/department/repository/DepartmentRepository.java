package za.co.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
