package project.demo.dto;

import project.demo.model.AccountType;

import java.util.Objects;

public class EmployeeConnectionResultDTO {

    int validationCode;

    public EmployeeConnectionResultDTO(int validationCode) {
        this.validationCode = validationCode;
    }

    public int getValidationCode() {
        return validationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeConnectionResultDTO that = (EmployeeConnectionResultDTO) o;
        return validationCode == that.validationCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(validationCode);
    }
}
