package IIS.controllers;

import IIS.exception.StatementAlreadyExistException;
import IIS.message.dto.StatementDTO;
import IIS.message.dto.StatementStatusDTO;
import IIS.message.meppers.StatementMapper;
import IIS.service.StatementIISService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/IIS")
public class InterdepartmentalInteractionRestController {
    @Autowired
    private StatementIISService statementIISService;
    @Autowired
    private StatementMapper statementMapper;

    @PostMapping(value = "/registration")
    public ResponseEntity<?> registrationNewStatement(@RequestBody StatementDTO statementDTO) {
        try {
            statementIISService.registrationNewStatement(statementMapper.toEntity(statementDTO));
            return new ResponseEntity<>(HttpStatus.valueOf(200));
        } catch (StatementAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/status")
    public ResponseEntity<StatementStatusDTO> getStatusStatement(@RequestParam(name = "keyDepartment") String keyDepartment,
                                                                 @RequestParam(name = "typeStatement") String typeStatement,
                                                                 @RequestParam(name = "numberStatement") int numberStatement) {

        StatementStatusDTO statementStatusDTO = statementIISService.getStatusStatementByDepartmentAndTypeAndNumber(keyDepartment, typeStatement, (int) numberStatement);

        if(statementStatusDTO != null) return new ResponseEntity<>(statementStatusDTO, HttpStatus.valueOf(200));
        else return new ResponseEntity<>(HttpStatus.valueOf(404));
    }
}
