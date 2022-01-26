package project.demo.dto;

public final class TransActionStatusCode {

    public static final int CODE_TRANSACTION_PROCESSED = 900;
    public static final int CODE_IBAN_SENDER_NOT_FOUND = 901;
    public static final int CODE_PIN_NOT_CORRECT = 902;
    public static final int CODE_BALANCE_NOT_SUFFICIENT = 903;
    public static final int CODE_IBAN_RECEIVER_NOT_FOUND = 904;

    private TransActionStatusCode() {
    }

    public static String getDescriptionForStatusCode(int code) {
        switch (code) {
            case 900:
                return "Transaction Successful";
            case 901:
                return "Account number not found";
            case 902:
                return "Pin is incorrect";
            case 903:
                return "Balance is not sufficient";
            case 904:
                return "Iban receiver not found";
            default:
                return "Code does not exist";
        }
    }
}
