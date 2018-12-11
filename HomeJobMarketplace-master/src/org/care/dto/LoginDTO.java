package org.care.dto;

import org.care.model.Member;

public class LoginDTO {
    private Member.MemberType mType;
    private Member.Status status;
    private int userId;
    private String emailId;
    private String password;

    public LoginDTO(int userId, String emailId, String password, Member.MemberType mType, Member.Status status) {
        this.userId = userId;
        this.emailId = emailId;
        this.password = password;
        this.mType = mType;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        this.password = password;
    }

    public Member.MemberType getmType() {
        return mType;
    }

    public void setmType(Member.MemberType mType) {
        this.mType = mType;
    }

    public Member.Status getStatus() {
        return status;
    }

    public void setStatus(Member.Status status) {
        this.status = status;
    }
}
