package com.example.doctorsappointment.Doctor;

public class Doctor {

    private String spec;
    private int exp;
    private String name;
    private String email;
    private String password;
    private String hospital;
    private String location;
    private String phone;
    private String fees;
    private Long current;

    public Doctor(String spec, int exp,String name, String email, String password, String hospital, String location, String phone, String fees,long current) {
        this.spec= spec;
        this.exp = exp;
        this.name = name;
        this.email = email;
        this.password = password;
        this.hospital = hospital;
        this.location = location;
        this.phone = phone;
        this.fees = fees;
        this.current=current;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getHospital() {
        return hospital;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getFees() {
        return fees;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }
}
