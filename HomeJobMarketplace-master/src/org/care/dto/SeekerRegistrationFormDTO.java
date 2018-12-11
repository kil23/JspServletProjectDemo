package org.care.dto;

import java.util.Map;

public class SeekerRegistrationFormDTO extends MemberRegistrationFormDTO {
    private String totalChildren;
    private String spouseName;

    public SeekerRegistrationFormDTO(String firstName, String lastName, String phoneNo, String emailId,
                                     String password, String type, String address, String pincode,
                                     String totalChildren, String spouseName) {
        super(firstName, lastName, phoneNo, emailId, password, type, address, pincode);
        this.totalChildren = totalChildren;
        this.spouseName = spouseName;
    }

    @Override
    public boolean validate() {
        super.validate();
        validateTotalChildren();
        validateSpouseName();
        return errors.isEmpty();
    }

    private void validateTotalChildren() {
        if (totalChildren.isEmpty()) {
            errors.put("totalChildren", "Can't be empty!");
        } else if (!totalChildren.matches("^0$|^[1-9][0-9]*$")) {
            errors.put("totalChildren", "only numbers allowed with no preceding zeros!");
        }
    }

    private void validateSpouseName() {
        if (spouseName.isEmpty()) {
            errors.put("spouseName", "Can't be empty!");
        } else if (!spouseName.matches("^[\\p{L}]+$")) {
            errors.put("spouseName", "Only characters allowed!");
        }
    }

    public String getTotalChildren() {
        return totalChildren;
    }

    public String getSpouseName() {
        return spouseName;
    }
}
