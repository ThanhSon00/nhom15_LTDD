package app.ntnt.finalprojectexamonline.model;

public class TestInfor {
    private int id;
    private String name;
    //private LocalDate dateComplete;
    private String nameSubject;
    private int time;
    private String nameAuthor;
    private String dateCreated;

    public TestInfor(int id, String name, String nameSubject, int time, String nameAuthor, String dateCreated) {
        this.id = id;
        this.name = name;
        this.nameSubject = nameSubject;
        this.time = time;
        this.nameAuthor = nameAuthor;
        this.dateCreated = dateCreated;
    }

    public TestInfor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
