package IIS.service;

import IIS.config.ProcessingIIISStatementScheduledImpTestConfig;
import IIS.entity.Statement;
import IIS.entity.StatementStatus;
import IIS.repositories.StatementRepositories;
import IIS.repositories.StatementStatusRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ProcessingIIISStatementScheduledImpTestConfig.class, loader = AnnotationConfigContextLoader.class)
class ProcessingStatementScheduledImpTest {
    @Autowired
    private ProcessingStatementScheduled processingStatementScheduled;

    //mock
    @Autowired
    private StatementRepositories statementRepositories;
    @Autowired
    private StatementStatusRepositories statementStatusRepositories;

    private static final StatementStatus status = mock(StatementStatus.class);

    @Test
    public void processingStatementEmptyStatus() {
        when(statementStatusRepositories.findByKey(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            processingStatementScheduled.processingStatement();
        } );
    }
}