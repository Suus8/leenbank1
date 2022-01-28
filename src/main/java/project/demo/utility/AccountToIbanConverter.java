package project.demo.utility;

public final class AccountToIbanConverter {

    private static final String IBAN_COUNTRY_CODE = "NL";
    private static final String IBAN_BANK_CODE = "LEEN";
    private static final long BANK_CODE_NUMERIC = 21141423;
    private static final int MAX_DIGITS_ACCOUNTNUMBER = 10;

    private AccountToIbanConverter() {
    }

    public static String convertAccountToIban(long accountNumber) {
        int verificationNumber = calculateVerificationNumber(accountNumber);
        String accountString = makeStringFromAccountNumber(accountNumber);
        String zerosBeforeAccountNumber = generateZeros(accountString);

        StringBuilder sb = new StringBuilder();
        sb.append(IBAN_COUNTRY_CODE);
        if (verificationNumber < 10) sb.append("0");
        sb.append(verificationNumber)
                .append(IBAN_BANK_CODE)
                .append(zerosBeforeAccountNumber)
                .append(accountString);
        return sb.toString();
    }

    private static String generateZeros(String accountNumber) {
        StringBuilder zeros = new StringBuilder();
        for (int i = accountNumber.length(); i < MAX_DIGITS_ACCOUNTNUMBER; i++) {
            zeros.append("0");
        }
        return zeros.toString();
    }

    private static String makeStringFromAccountNumber(long accountNumber) {
        return "" + accountNumber;
    }

    public static String convertAccountToIban(String accountNumber) {
        try {
            long account = Long.parseLong(accountNumber);
            return convertAccountToIban(account);
        } catch (IllegalArgumentException iae) {
            iae.getStackTrace();
        }
        return "INVALID ACCOUNTNUMBER";
    }

    private static int calculateVerificationNumber(long accountNumber) {
        // modified IBAN-verificationnumber calculation logic
        int number;
        long temp = BANK_CODE_NUMERIC * (long) Math.pow(10, 10);
        temp += accountNumber;
        int temp2 = (int) temp % 97;
        number = 98 - temp2;
        return number;
    }
}