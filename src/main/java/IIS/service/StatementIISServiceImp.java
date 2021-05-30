package IIS.service;

import IIS.entity.Department;
import IIS.entity.Statement;
import IIS.entity.TypeOfStatement;
import IIS.exception.StatementAlreadyExistException;
import IIS.message.dto.StatementStatusDTO;
import IIS.message.meppers.StatementStatusMapper;
import IIS.repositories.DepartmentRepositories;
import IIS.repositories.StatementRepositories;
import IIS.repositories.StatementStatusRepositories;
import IIS.repositories.TypeOfStatementRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class StatementIISServiceImp implements StatementIISService{
    @Autowired
    private StatementRepositories statementRepositories;
    @Autowired
    private TypeOfStatementRepositories typeOfStatementRepositories;
    @Autowired
    private DepartmentRepositories departmentRepositories;
    @Autowired
    private StatementStatusRepositories statementStatusRepositories;
    @Autowired
    private StatementStatusMapper statementStatusMapper;

    @Override
    @Transactional
    public void registrationNewStatement(Statement requestStatement) throws StatementAlreadyExistException {

        if(statementRepositories.findByNumber(requestStatement.getNumber()) != null) {
            throw new StatementAlreadyExistException("Заявление уже зарегистрировано");
        }
        requestStatement.setDateTimeAdmission(LocalDateTime.now());
        requestStatement.setStatementStatus(statementStatusRepositories.findByKey("WAIT")
                .orElseThrow(() -> new EntityNotFoundException("Not found entity Statement.key = WAIT")));
        statementRepositories.save(requestStatement);
    }


    @Override
    public StatementStatusDTO getStatusStatementByDepartmentAndTypeAndNumber(String keyDepartment, String typeStatement, int numberStatement) {
        Department department = departmentRepositories.findByKey(keyDepartment)
                .orElseThrow(() -> new EntityNotFoundException("Not found entity Statement.key = " + keyDepartment));
        TypeOfStatement typeOfStatement = typeOfStatementRepositories.findByKey(typeStatement)
                .orElseThrow(() -> new EntityNotFoundException("Not found entity Statement.key = " + typeStatement));

        Statement statement = statementRepositories.findByDepartmentAndTypeOfStatementAndNumber(department, typeOfStatement, numberStatement);
        if(statement != null) {
            return statementStatusMapper.toDTO(statement);
        } else return null;
    }
}
