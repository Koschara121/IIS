package IIS.service;

import IIS.entity.Statement;
import IIS.exception.StatementAlreadyExistException;
import IIS.message.dto.StatementStatusDTO;

public interface StatementIISService {
    void registrationNewStatement(Statement statement) throws StatementAlreadyExistException;
    StatementStatusDTO getStatusStatementByDepartmentAndTypeAndNumber(String keyDepartment, String typeStatement, int numberStatement);
}
