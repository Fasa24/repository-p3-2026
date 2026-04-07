package utils;

public class InvalidUserException extends Exception{
	public InvalidUserException() {
		super("El campo contiene caracteres invalidos");
	}
	
	public InvalidUserException(char character) {
		super("El campo no puede contener: " + character);
	}
	
	public InvalidUserException(String mensaje) {
		super("mensaje");
	}
}
