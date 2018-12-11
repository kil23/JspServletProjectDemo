package org.care.model;

public class Sitter extends Member {
    private int experience;

    public Sitter(String firstName, String lastName, String phoneNo, String emailId, String password, String address,
                  int pincode, int experience) {
        super(firstName, lastName, phoneNo, emailId, password, MemberType.SITTER, address, pincode);
        this.experience = experience;
    }

    public Sitter(Member member, int experience) {
        super(member);
        this.experience = experience;
    }

    public Sitter(String firstName, String lastName, String phoneNo, String emailId, String password, MemberType memberType, String address, int pincode, int experience) {
        super(firstName, lastName, phoneNo, emailId, password, memberType, address, pincode);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
