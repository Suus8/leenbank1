package project.demo.dto;

public class ConnectionStatusCode {

    public static final int CODE_CONNECTION_PROCESSED = 905;
    public static final int CODE_IBAN_NOT_FOUND = 906;
    public static final int CODE_VALIDATION_CODE_NOT_CORRECT = 907;

    public ConnectionStatusCode() {
    }

    public static String getConnectionCode(int code) {
        switch (code) {
            case 905:
                return "Connection Successful";
            case 906:
                return "Account number not found";
            case 907:
                return "Validation code is incorrect";
            default:
                return "No connection";
        }
    }
}
