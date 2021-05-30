package IIS.repositories;

import IIS.entity.TypeOfStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfStatementRepositories extends JpaRepository<TypeOfStatement, Long> {
    Optional<TypeOfStatement> findByKey(String key);
}
