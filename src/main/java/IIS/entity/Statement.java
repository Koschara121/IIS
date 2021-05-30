package IIS.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "statement")
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private int number;

    @ManyToOne(cascade = CascadeType.REMOVE , optional = false)
    @JoinColumn(name = "typeOfStatement_id")
    private TypeOfStatement typeOfStatement;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade = CascadeType.REMOVE , optional = false)
    @JoinColumn(name = "statementStatus_id")
    private StatementStatus statementStatus;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "sure_name")
    private String surName;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "date_time_admission")
    private LocalDateTime dateTimeAdmission;

    public Statement() {
    }

    public Statement(int number, TypeOfStatement typeOfStatement, Department department, StatementStatus statementStatus, String passportNumber, String surName, String name, String middleName, LocalDateTime dateTimeAdmission) {
        this.number = number;
        this.typeOfStatement = typeOfStatement;
        this.department = department;
        this.statementStatus = statementStatus;
        this.passportNumber = passportNumber;
        this.surName = surName;
        this.name = name;
        this.middleName = middleName;
        this.dateTimeAdmission = dateTimeAdmission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TypeOfStatement getTypeOfStatement() {
        return typeOfStatement;
    }

    public void setTypeOfStatement(TypeOfStatement typeOfStatement) {
        this.typeOfStatement = typeOfStatement;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public StatementStatus getStatementStatus() {
        return statementStatus;
    }

    public void setStatementStatus(StatementStatus statementStatus) {
        this.statementStatus = statementStatus;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDateTime getDateTimeAdmission() {
        return dateTimeAdmission;
    }

    public void setDateTimeAdmission(LocalDateTime dateTimeAdmission) {
        this.dateTimeAdmission = dateTimeAdmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Statement statement = (Statement) o;

        return id != null && id.equals(statement.id);
    }

    @Override
    public int hashCode() {
        return 1731764188;
    }
}
