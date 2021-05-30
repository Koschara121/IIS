package IIS.repositories;

import IIS.entity.Department;
import IIS.entity.Statement;
import IIS.entity.StatementStatus;
import IIS.entity.TypeOfStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StatementRepositories extends JpaRepository<Statement, Long> {
    List<Statement> findAllByStatementStatus(StatementStatus statementStatus);
    Statement findByNumber(int number);
    Statement findByDepartmentAndTypeOfStatementAndNumber(Department deprecated, TypeOfStatement typeOfStatement, int number);

}
