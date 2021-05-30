package IIS.config;

import IIS.repositories.StatementRepositories;
import IIS.repositories.StatementStatusRepositories;
import IIS.service.ProcessingStatementScheduled;
import IIS.service.ProcessingStatementScheduledImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ProcessingIIISStatementScheduledImpTestConfig {

    @Bean
    public ProcessingStatementScheduled processingStatementScheduled() {
        return new ProcessingStatementScheduledImp();
    }

    @Bean
    public StatementRepositories statementRepositories() {
        return mock(StatementRepositories.class);
    }

    @Bean
    public StatementStatusRepositories statementStatusRepositories() {
        return mock(StatementStatusRepositories.class);
    }
}
