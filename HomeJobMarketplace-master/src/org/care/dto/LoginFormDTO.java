package org.care.dto;

import java.util.HashMap;
import java.util.Map;

public class LoginFormDTO {
    private String emailId;
    private String password;
    private Map<String, String> errors;

    public LoginFormDTO(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public boolean validate() {
        errors = new HashMap<>();

        if (emailId.isEmpty()) {
            errors.put("emailId", "Can't be empty");
        } else if (!emailId.matches("^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$")) {
            errors.put("emailId", "Format incorrect!");
        }

        if (password.isEmpty()) {
            errors.put("password", "Can't be empty");
        }

        return errors.isEmpty();
    }
}
