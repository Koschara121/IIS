package IIS.repositories;

import IIS.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepositories extends JpaRepository<Department, Long> {
    Optional<Department> findByKey(String key);
}
