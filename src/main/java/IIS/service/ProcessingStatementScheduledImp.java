package IIS.service;

import IIS.entity.Statement;
import IIS.entity.StatementStatus;
import IIS.repositories.StatementRepositories;
import IIS.repositories.StatementStatusRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProcessingStatementScheduledImp implements ProcessingStatementScheduled {

    @Autowired
    private StatementRepositories statementRepositories;
    @Autowired
    private StatementStatusRepositories statementStatusRepositories;

    @Override
    @Scheduled(cron = "0 * * ? * *")
    public void processingStatement() {
        StatementStatus status = statementStatusRepositories.findByKey("WAIT")
                .orElseThrow(() -> new EntityNotFoundException("Not found entity Statement.key = WAIT"));

        Set<Statement> statementsWait = new HashSet<>(statementRepositories.findAllByStatementStatus(status));

        if(!statementsWait.isEmpty()) statementsWait.forEach(this::equalsTime);
    }

    private void equalsTime(Statement statement) {
        LocalDateTime statementDateTime = statement.getDateTimeAdmission();
        LocalDateTime nowDateTime = LocalDateTime.now();

        long min = ChronoUnit.MINUTES.between(statementDateTime, nowDateTime);

        if(min > 2L) {
            StatementStatus status = statementStatusRepositories.findByKey("COMPLETED")
                    .orElseThrow(() -> new EntityNotFoundException("Not found entity Statement.key = COMPLETED"));

            statement.setStatementStatus(status);
            statementRepositories.save(statement);
        }
    }


}
