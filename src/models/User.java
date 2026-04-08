package models;

public class User {
	private String name;
	private String email;
	private String password;
	private String address;
	private String postalCode;
	private String gender;
	
	private String[] data = new String[6];
	
	public User(String[] data) {
		this.name = data[0];
		this.email = data[1];
		this.password = data[2];
		this.address = data[3];
		this.postalCode = data[4];
		this.gender = data[5];
	}
}
