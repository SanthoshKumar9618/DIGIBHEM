

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phone;

    // Constructor, Getters, and Setters
    public User(int userId, String username, String password, String email, String phone) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	public String getPhone() {
		// TODO Auto-generated method stub
		return phone;
	}

    // Getters and Setters
}
