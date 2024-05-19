package app.ntnt.finalprojectexamonline.model.entites;

import java.time.LocalDate;

public class Test {
    private int id;
    private String name;
    private int score;
    //private LocalDate dateComplete;
    private String nameSubject;
    private int time;

    private int times;

    public Test(int id, String name, int score, String nameSubject, int time, int times) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.nameSubject = nameSubject;
        this.time = time;
        this.times = times;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Test() {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

//    public LocalDate getDateComplete() {
//        return dateComplete;
//    }
//
//    public void setDateComplete(LocalDate dateComplete) {
//        this.dateComplete = dateComplete;
//    }

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
}
