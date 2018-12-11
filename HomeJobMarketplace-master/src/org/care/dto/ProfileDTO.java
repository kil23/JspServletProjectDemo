package org.care.dto;

import org.care.service.MemberService;

import java.util.HashMap;
import java.util.Map;

public class ProfileDTO {
    Map<String, String> errors;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String emailId;
    private String address;
    private String pincode;

    ProfileDTO(String firstName, String lastName, String phoneNo, String emailId, String address, String pincode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.address = address;
        this.pincode = pincode;
    }

    public Map<String, String> getErrors() {
        return errors;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public boolean validate() {
        this.errors = new HashMap<>();
        validateFirstName();
        validateLastName();
        validatePhoneNo();
//        validateEmailId();
        validateAddress();
        validatePincode();
        return this.errors.isEmpty();
    }

    private void validateFirstName() {
        if (firstName.isEmpty()) {
            errors.put("firstName", "First Name can't be empty!");
        } else if (!firstName.matches("^[\\p{L}]+$")) {
            errors.put("firstName", "First name can only have characters");
        }
    }

    private void validateLastName() {
        if (lastName.isEmpty()) {
            errors.put("lastName", "Last Name can't be empty!");
        } else if (!lastName.matches("^[\\p{L}]+$")) {
            errors.put("lastName", "Last Name can only have characters");
        }
    }

    private void validatePhoneNo() {
        if (phoneNo.isEmpty()) {
            errors.put("phoneNo", "Phone no. can't be empty!");
        } else if (!phoneNo.matches("^[0-9]{10}$")) {
            errors.put("phoneNo", "Phone no. should be of 10 digits only.");
        }
    }

    private void validateEmailId() {
        if (emailId.isEmpty()) {
            errors.put("emailId", "Email id can't be empty!");
        } else if (!emailId.matches("^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+.)+[a-z]{2,5}$")) {
            errors.put("emailId", "Incorrect email format");
        } else if (MemberService.isEmailIdRegistered(emailId)) {
            errors.put("emailId", "Account already exist with given emailId");
        }
    }

    private void validateAddress() {
        if (address.isEmpty()) {
            errors.put("address", "Address can't be empty!");
        }
    }

    private void validatePincode() {
        if (pincode.isEmpty()) {
            errors.put("pincode", "Pincode can't be empty!");
        } else if (!pincode.matches("^[0-9]{6}$")) {
            errors.put("pincode", "Pincode should be of 6 digit only.");
        }
    }
}
