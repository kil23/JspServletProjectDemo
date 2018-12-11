package org.care.model;

import org.care.utils.CommonUtil;

public class Member {
    private Status status;
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String emailId;
    private String password;
    private MemberType type;
    private String address;
    private int pincode;

    public Member(Member member) {
        this.id = member.id;
        this.firstName = member.firstName;
        this.lastName = member.lastName;
        this.phoneNo = member.phoneNo;
        this.emailId = member.emailId;
        this.password = member.password;
        this.type = member.type;
        this.address = member.address;
        this.pincode = member.pincode;
        this.status = member.status;
    }

    public Member(int id, String firstName, String lastName, String phoneNo, String emailId, String password,
                  MemberType type, String address, int pincode, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        setPassword(password);
        this.type = type;
        this.address = address;
        this.pincode = pincode;
        this.status = status;
    }

    public Member(String firstName, String lastName, String phoneNo, String emailId, String password, MemberType type,
                  String address, int pincode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        setPassword(password);
        this.type = type;
        this.address = address;
        this.pincode = pincode;
        this.status = Status.ACTIVE;
    }

    public Member(int id, String firstName, String lastName, String phoneNo, String emailId, String password,
                  MemberType type, String address, int pincode) {
        this(firstName, lastName, phoneNo, emailId, password, type, address, pincode);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = CommonUtil.getHashedPassword(password);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public enum MemberType {SEEKER, SITTER}

    public enum Status {ACTIVE, INACTIVE}
}
