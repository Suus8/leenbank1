package project.demo.utility;

import project.demo.model.Account;
import project.demo.model.Company;

public class CompanySectorRetriever {

    /**
     * Retrieve sector name for an Account. Set to "n.v.t." if the account is not a Company.
     *
     * @param account an Account object
     * @return sector name if accountHolder is a Company, else "n.v.t."
     */
    public static String retrieveCompanySector(Account account) {
        String sector;
        if (account.getAccountHolder() instanceof Company) {
            sector = ((Company) (account.getAccountHolder())).getCompanyDetails().getSector().toString();
        } else {
            sector = "n.v.t.";
        }
        return sector;
    }

}
