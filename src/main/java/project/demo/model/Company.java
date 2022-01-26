package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company extends AbstractClient {
    //region ATTRIBUTES
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_details", referencedColumnName = "id")
    private CompanyDetails companyDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_manager", referencedColumnName = "id")
    private Employee accountManager;

    private int registrationCodePinTerminal;
    //endregion

    //region CONSTRUCTOR
    public Company(Long id, Login login, ClientDetails contactDetails, CompanyDetails companyDetails) {
        super(id, login, contactDetails);
        // Check if CoC = unique, is done by DB through abstractclient class
            this.companyDetails = companyDetails;
            Account account = new Account(this, AccountType.SMALL_BUSINESS);
    }
    //endregion

    //region METHODS

    //endregion

    //region Helper Methods (private)
    private List<Long> getAllcoCNumber() {
        List<Long> numbers = new ArrayList<>();

        // Get all BSN from DAO/Repo
//        for (Integer coc : ?? ){
//            numbers.add(coc);
//        }
        //TODO

        return numbers;
    }
    //endregion

    //region GETTERS & SETTERS

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    public Employee getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(Employee accountManager) {
        this.accountManager = accountManager;
    }

    public int getRegistrationCodePinTerminal() {
        return registrationCodePinTerminal;
    }

    public void setRegistrationCodePinTerminal(int registrationCodePinTerminal) {
        this.registrationCodePinTerminal = registrationCodePinTerminal;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE

    @Override
    public String toString() {
        return super.toString() + "Company{" +
                "companyDetails=" + companyDetails +
                ", accountManager=" + accountManager +
                ", registrationCodePinTerminal=" + registrationCodePinTerminal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return registrationCodePinTerminal == company.registrationCodePinTerminal && Objects.equals(companyDetails, company.companyDetails) && Objects.equals(accountManager, company.accountManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyDetails, accountManager, registrationCodePinTerminal);
    }

    //endregion
}
