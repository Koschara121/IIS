package IIS.service;

import IIS.config.StatementIISServiceTestConfig;
import IIS.entity.Department;
import IIS.entity.Statement;
import IIS.entity.StatementStatus;
import IIS.entity.TypeOfStatement;
import IIS.exception.StatementAlreadyExistException;
import IIS.message.dto.StatementStatusDTO;
import IIS.message.meppers.StatementStatusMapper;
import IIS.repositories.DepartmentRepositories;
import IIS.repositories.StatementRepositories;
import IIS.repositories.StatementStatusRepositories;
import IIS.repositories.TypeOfStatementRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StatementIISServiceTestConfig.class, loader = AnnotationConfigContextLoader.class)
class StatementIISServiceImpTest {
    @Autowired
    private StatementIISServiceImp statementIISServiceImp;
    //mock
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

    private static final Statement statement = mock(Statement.class);
    private static final StatementStatus status = mock(StatementStatus.class);
    private static final Department department = mock(Department.class);
    private static final TypeOfStatement type = mock(TypeOfStatement.class);
    private static final StatementStatusDTO statusDTO = mock(StatementStatusDTO.class);

    @Test
    void registrationNewStatement() throws StatementAlreadyExistException {
        when(statement.getNumber()).thenReturn(1);
        when(statementRepositories.findByNumber(1)).thenReturn(null);
        when(statementStatusRepositories.findByKey(anyString())).thenReturn(java.util.Optional.ofNullable(status));

        statementIISServiceImp.registrationNewStatement(statement);

        verify(statementRepositories, times(1)).save(any());
    }

    // Попытка зарегестрировать заявление которое уже есть в БД
    @Test
    void registrationDuplicateStatement() {

        when(statement.getNumber()).thenReturn(5);
        when(statementRepositories.findByNumber(5)).thenReturn(statement);
        when(statementStatusRepositories.findByKey(anyString())).thenReturn(java.util.Optional.ofNullable(status));

        assertThrows(StatementAlreadyExistException.class, () -> {
            statementIISServiceImp.registrationNewStatement(statement);
        } );
    }

    @BeforeEach
    public void startTest() {
        when(departmentRepositories.findByKey(anyString())).thenReturn(java.util.Optional.ofNullable(department));
        when(typeOfStatementRepositories.findByKey(anyString())).thenReturn(java.util.Optional.ofNullable(type));
        when(statementStatusMapper.toDTO(statement)).thenReturn(statusDTO);
    }

    // Попытка найти статус заявления которого не существует в БД, ожидаем получить null и не формировать DTO
    @Test
    void getStatusNonExistentStatementByDepartmentAndTypeAndNumber() {
        when(statementRepositories.findByDepartmentAndTypeOfStatementAndNumber(department, type, 1)).thenReturn(null);
        Assertions.assertEquals(null, statementIISServiceImp.getStatusStatementByDepartmentAndTypeAndNumber("KEY", "KEY", 1));
    }

    //Попытка найти статус заявления которое существует в БД
    @Test
    void getStatusStatementByDepartmentAndTypeAndNumber() {
        when(statementRepositories.findByDepartmentAndTypeOfStatementAndNumber(department, type, 2)).thenReturn(statement);
        Assertions.assertEquals(statusDTO, statementIISServiceImp.getStatusStatementByDepartmentAndTypeAndNumber("KEY", "KEY", 2));
    }
}