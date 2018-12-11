package org.care.dto;

public class SeekerProfileDTO extends ProfileDTO {
    private String totalChildren;
    private String spouseName;

    public SeekerProfileDTO(String firstName, String lastName, String phoneNo, String emailId, String address,
                            String pincode, String totalChildren, String spouseName) {
        super(firstName, lastName, phoneNo, emailId, address, pincode);
        this.totalChildren = totalChildren;
        this.spouseName = spouseName;
    }

    public String getTotalChildren() {
        return totalChildren;
    }

    public void setTotalChildren(String totalChildren) {
        this.totalChildren = totalChildren;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

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
            errors.put("totalChildren", "No zeros before any number allowed!");
        }
    }

    private void validateSpouseName() {
        if (spouseName.isEmpty()) {
            errors.put("spouseName", "Can't be empty!");
        } else if (!spouseName.matches("^[\\p{L}]+$")) {
            errors.put("spouseName", "Only characters allowed!");
        }
    }
}
