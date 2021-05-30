package IIS.message.meppers;

import IIS.entity.Statement;
import IIS.message.dto.StatementStatusDTO;
import org.springframework.stereotype.Component;

@Component
public class StatementStatusMapper {
    public StatementStatusDTO toDTO(Statement statement) {
        StatementStatusDTO statementStatusDTO = new StatementStatusDTO();

        statementStatusDTO.setStatus(statement.getStatementStatus().getName());
        statementStatusDTO.setKey(statement.getStatementStatus().getKey());
        return statementStatusDTO;
    }
}
