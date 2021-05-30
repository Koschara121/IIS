package IIS.message.meppers;

import IIS.entity.Department;
import IIS.entity.Statement;
import IIS.entity.TypeOfStatement;
import IIS.message.dto.StatementDTO;
import IIS.repositories.DepartmentRepositories;
import IIS.repositories.TypeOfStatementRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class StatementMapper {
    @Autowired
    private TypeOfStatementRepositories typeOfStatementRepositories;
    @Autowired
    private DepartmentRepositories departmentRepositories;

    public Statement toEntity(StatementDTO statementDTO) {
        Statement statement = new Statement();

        statement.setTypeOfStatement(typeOfStatementRepositories.findByKey(statementDTO.getTypeOfStatement())
                .orElseThrow(() -> new EntityNotFoundException("Not found entity Statement.key = " + statementDTO.getTypeOfStatement())));
        statement.setDepartment(departmentRepositories.findByKey(statementDTO.getDepartment())
                .orElseThrow(() -> new EntityNotFoundException("Not found entity Department.key = " + statementDTO.getDepartment())));

        statement.setNumber(statementDTO.getNumber());
        statement.setSurName(statementDTO.getSurname());
        statement.setName(statementDTO.getName());
        statement.setMiddleName(statementDTO.getMiddleName());
        statement.setPassportNumber(statementDTO.getPassportNumber());
        return statement;
    }
}
