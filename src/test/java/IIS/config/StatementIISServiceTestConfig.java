package IIS.config;

import IIS.message.meppers.StatementStatusMapper;
import IIS.repositories.DepartmentRepositories;
import IIS.repositories.StatementRepositories;
import IIS.repositories.StatementStatusRepositories;
import IIS.repositories.TypeOfStatementRepositories;
import IIS.service.StatementIISService;
import IIS.service.StatementIISServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class StatementIISServiceTestConfig {

    @Bean
    public StatementIISServiceImp statementIISService() {
        return new StatementIISServiceImp();
    }

    @Bean
    public StatementRepositories statementRepositories() {
        return mock(StatementRepositories.class);
    }

    @Bean
    public TypeOfStatementRepositories typeOfStatementRepositories() {
        return mock(TypeOfStatementRepositories.class);
    }

    @Bean
    public DepartmentRepositories departmentRepositories() {
        return mock(DepartmentRepositories.class);
    }

    @Bean
    public StatementStatusRepositories statementStatusRepositories() {
        return mock(StatementStatusRepositories.class);
    }

    @Bean
    public StatementStatusMapper statementStatusMapper() {
        return mock(StatementStatusMapper.class);
    }
}
