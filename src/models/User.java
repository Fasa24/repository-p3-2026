package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private int id;
	private String name, email, password, address, postalCode, gender, role;
	private boolean termsAccepted;

	@JsonCreator
	public User(
			@JsonProperty("name")          String name,
			@JsonProperty("email")         String email,
			@JsonProperty("password")      String password,
			@JsonProperty("address")       String address,
			@JsonProperty("postalCode")    String postalCode,
			@JsonProperty("gender")        String gender,
			@JsonProperty("termsAccepted") boolean termsAccepted) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.postalCode = postalCode;
		this.gender = gender;
		this.termsAccepted = termsAccepted;
	}

	public User(){

	}

	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public User(
			String name,
			String email,
			String password,
			String address,
			String postalCode,
			String gender,
			String role,
			boolean termsAccepted
	) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.postalCode = postalCode;
		this.gender = gender;
		this.role = role;
		this.termsAccepted = termsAccepted;
	}

	public User(
			int id,
			String name,
			String email,
			String password,
			String address,
			String postalCode,
			String gender,
			String role,
			boolean termsAccepted
	) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.postalCode = postalCode;
		this.gender = gender;
		this.role = role;
		this.termsAccepted = termsAccepted;
	}

	public void setName(String name) { this.name = name; }
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }
	public void setAddress(String address) { this.address = address; }
	public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
	public void setGender(String gender) { this.gender = gender; }
	public void setRole(String role) { this.role = role; }

	public String getName() { return name; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public String getAddress() { return address; }
	public String getPostalCode() { return postalCode; }
	public String getGender() { return gender; }
	public String getRole() { return role; }
	public boolean isTermsAccepted() { return termsAccepted; }

	public String toCsv() {
		return name + ";" + email + ";" + password + ";" +
				address + ";" + postalCode + ";" + gender + ";" + termsAccepted;
	}

	public static User fromCsv(String csvLine) {
		if (csvLine == null || csvLine.isBlank()) return null;

		String[] data = csvLine.split(";");

		if (data.length < 7) {
			System.err.println("Línea CSV inválida (faltan campos): " + csvLine);
			return null;
		}

		return new User(
				data[0], // name
				data[1], // email
				data[2], // password
				data[3], // address
				data[4], // postalCode
				data[5], // gender
				Boolean.parseBoolean(data[6]) // termsAccepted
		);
	}

	@Override
	public String toString() {
		return "Name: " + name + " (" + email + ")\n" +
				"Address: " + address + ", P.C.: " + postalCode + "\n" +
				"Gender: " + gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
