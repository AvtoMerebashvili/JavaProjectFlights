package Validations;

public class Validations {
    public static boolean checkPin(String pin) throws WrongPinExeption {
        boolean isOkay = pin.length() == 11 ? true : false;
        return isOkay;
    }
}
