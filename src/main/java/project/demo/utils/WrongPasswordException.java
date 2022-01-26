/**
 * Created by abuzer.alaca on 19/01/2022
 **/


package project.demo.utils;

public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super("Wrong password");
    }
}
