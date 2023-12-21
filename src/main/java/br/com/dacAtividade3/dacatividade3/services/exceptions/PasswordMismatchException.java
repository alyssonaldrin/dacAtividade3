package br.com.dacatividade3.dacatividade3.services.exceptions;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException() {
        super("Senhas não coincidem.");
    }
}
