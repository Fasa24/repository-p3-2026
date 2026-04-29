package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private String name, email, password, address, postalCode, gender;
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

	public void setName(String name) { this.name = name; }
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }
	public void setAddress(String address) { this.address = address; }
	public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
	public void setGender(String gender) { this.gender = gender; }

	public String getName() { return name; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public String getAddress() { return address; }
	public String getPostalCode() { return postalCode; }
	public String getGender() { return gender; }
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
}
