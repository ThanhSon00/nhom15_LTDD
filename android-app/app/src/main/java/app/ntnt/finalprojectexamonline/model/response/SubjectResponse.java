package app.ntnt.finalprojectexamonline.model.response;

import java.util.List;

import app.ntnt.finalprojectexamonline.model.entites.User;

public class SubjectResponse {

    private long subjectId;

    private String name;
    private int status;
    private String image;

    private List<User> users;

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }


    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public SubjectResponse() {
    }

    public SubjectResponse(long subjectId, String name, int status, String image, List<User> users) {
        this.subjectId = subjectId;
        this.name = name;
        this.status = status;
        this.image = image;
        this.users = users;
    }
}
