package models;

public class User {
	private String name, email, password, address, postalCode, gender;
	private boolean termsAccepted;

	public User(String name, String email, String password,
	            String address, String postalCode,
	            String gender, boolean termsAccepted) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.postalCode = postalCode;
		this.gender = gender;
		this.termsAccepted = termsAccepted;
	}

	public String getName() { return name; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public String getAddress() { return address; }
	public String getPostalCode() { return postalCode; }
	public String getGender() { return gender; }
	public boolean isTermsAccepted() { return termsAccepted; }

	public String toCsv() {
		return name + "," + email + "," + password + "," +
				address + "," + postalCode + "," + gender + "," + termsAccepted;
	}

	public static User fromCsv(String csvLine) {
		String[] data = csvLine.split(",");
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
		return "User: " + name + " (" + email + ")\n" +
				"Address: " + address + ", PC: " + postalCode + "\n" +
				"Gender: " + gender;
	}
}
