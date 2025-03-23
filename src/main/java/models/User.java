package models;

import java.util.Date;

public class User {
    private Long id;
    private String name;
    private String mobile;
    private String email;
    private String city;
    private String gender;
    private Date dob;

    public User() {}

    public User(Long id, String name, String mobile, String email, String city, String gender, Date dob) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.city = city;
        this.gender = gender;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }
}