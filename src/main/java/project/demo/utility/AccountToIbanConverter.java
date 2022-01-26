package project.demo.utility;

public final class AccountToIbanConverter {

    private static final String IBAN_COUNTRY_CODE = "NL";
    private static final String IBAN_BANK_CODE = "LEEN";
    private static final long BANK_CODE_NUMERIC = 21141423;

    private AccountToIbanConverter(){
    }

    public static String convertAccountToIban(long accountNumber) {
       int verificationNumber = calculateVerificationNumber(accountNumber);
       String accountString = ""+accountNumber;

       StringBuilder sb = new StringBuilder();
       sb.append(IBAN_COUNTRY_CODE);
       if (verificationNumber<10) sb.append("0");
       sb.append(verificationNumber).append(IBAN_BANK_CODE);
        for (int i = 0; i < (10-accountString.length()) ; i++) {
            sb.append("0");
        }
        sb.append(accountString);
        return sb.toString();
    }

    public static String convertAccountToIban(String accountNumber) {
        long account = 0;
        try {
            account = Long.parseLong(accountNumber);
            return convertAccountToIban(account);
        } catch (IllegalArgumentException iae) {
            iae.getStackTrace();
        }
        return "INVALID ACCOUNTNUMBER";
    }

    private static int calculateVerificationNumber(long accountNumber) {
        // modified IBAN-verificationnumber calculation logic
        int number;
        long temp = BANK_CODE_NUMERIC*(long) Math.pow(10, 10);
        temp += accountNumber;
        int temp2 = (int) temp%97;
        number = 98-temp2;
        return number;
    }
}