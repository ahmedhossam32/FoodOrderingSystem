package MainClasses;

public abstract class User {
    private String name;
    private String gender;
    private String email;
    private String password;
    private int phoneNumber;

  
    public User() {
    }

    public User(String name, String gender, String email, String password, int phoneNumber) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

  
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid email or password!");
            return false;
            
        }
    }

    public void updateProfile(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        System.out.println("Profile updated successfully.");
    }

    public void receiveNotification(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}
