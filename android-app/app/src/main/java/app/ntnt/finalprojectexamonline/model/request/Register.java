package app.ntnt.finalprojectexamonline.model.request;

import okhttp3.MultipartBody;

public class Register {
    private MultipartBody.Part email;
    private MultipartBody.Part firstName;
    private MultipartBody.Part lastName;
    private MultipartBody.Part phoneNumber;
    private MultipartBody.Part gender;
    private MultipartBody.Part username;
    private MultipartBody.Part password;
    private MultipartBody.Part avatar;

    public Register(MultipartBody.Part email,
                    MultipartBody.Part firstName,
                    MultipartBody.Part lastName,
                    MultipartBody.Part phoneNumber,
                    MultipartBody.Part gender,
                    MultipartBody.Part username,
                    MultipartBody.Part password,
                    MultipartBody.Part avatar) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public MultipartBody.Part getEmail() {
        return email;
    }

    public void setEmail(MultipartBody.Part email) {
        this.email = email;
    }

    public MultipartBody.Part getFirstName() {
        return firstName;
    }

    public void setFirstName(MultipartBody.Part firstName) {
        this.firstName = firstName;
    }

    public MultipartBody.Part getLastName() {
        return lastName;
    }

    public void setLastName(MultipartBody.Part lastName) {
        this.lastName = lastName;
    }

    public MultipartBody.Part getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(MultipartBody.Part phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MultipartBody.Part getGender() {
        return gender;
    }

    public void setGender(MultipartBody.Part gender) {
        this.gender = gender;
    }

    public MultipartBody.Part getUsername() {
        return username;
    }

    public void setUsername(MultipartBody.Part username) {
        this.username = username;
    }

    public MultipartBody.Part getPassword() {
        return password;
    }

    public void setPassword(MultipartBody.Part password) {
        this.password = password;
    }

    public MultipartBody.Part getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartBody.Part avatar) {
        this.avatar = avatar;
    }
}
