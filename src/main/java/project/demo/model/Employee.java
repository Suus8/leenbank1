package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Person {
    //region ATTRIBUTES
    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;
    //endregion

    //region CONSTRUCTOR
    public Employee(Long id, Login login, ClientDetails contactDetails, PersonDetails personDetails, JobTitle jobTitle) {
        super(id, login, contactDetails, personDetails);
        this.jobTitle = jobTitle;
    }

    //endregion

    //region METHODS

    //endregion

    //region GETTERS & SETTERS

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public String toString() {
        return super.toString() + "Employee{" +
                "jobTitle=" + jobTitle +
                '}';
    }

    //endregion
}
