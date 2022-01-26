package project.demo.utility;

import project.demo.model.AbstractClient;
import project.demo.model.Account;
import project.demo.model.Company;
import project.demo.model.Person;

/**
 * Utility class with static methods for retrieving names of abstract clients
 */
public class ClientNameUtility {

    public static String getNameOfAbstractClient(AbstractClient client){
        String clientName;

        if (client instanceof Person){
            clientName = ((Person) client).getPersonDetails().getFullName().getFullNameAsString();
        } else if (client instanceof Company){
            clientName = ((Company) client).getCompanyDetails().getName();
        } else {
            clientName = "unknown user";
        }

        return clientName;
    }

    public static String getNameOfAbstractClientOfAccount(Account account){
        AbstractClient client = account.getAccountHolder();
        return getNameOfAbstractClient(client);
    }
}
