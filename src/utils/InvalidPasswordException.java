package utils;

public class InvalidPasswordException extends Exception{
	public InvalidPasswordException() {
		super("invalid password");
	}
	
	public InvalidPasswordException(char character) {
		super("El campo no puede contener: " + character);
	}
	
	public InvalidPasswordException(String mensaje) {
		super("mensaje");
	}
}
