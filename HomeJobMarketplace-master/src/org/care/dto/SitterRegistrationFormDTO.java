package org.care.dto;

public class SitterRegistrationFormDTO extends MemberRegistrationFormDTO {
    private String experience;

    public SitterRegistrationFormDTO(String firstName, String lastName, String phoneNo, String emailId,
                                     String password, String type, String address, String pincode,
                                     String experience) {
        super(firstName, lastName, phoneNo, emailId, password, type, address, pincode);
        this.experience = experience;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public boolean validate() {
        super.validate();
        validateExperience();
        return errors.isEmpty();
    }

    private void validateExperience() {
        if (experience.isEmpty()) {
            errors.put("experience", "Can't be empty");
        } else if (!experience.matches("^[0-9]{1,4}$")) {
            errors.put("experience", "only 4 numbers are allowed!");
        } else if (Integer.parseInt(experience) > 120) {
            errors.put("experience", "experience can't be greater than 120");
        }
    }
}
