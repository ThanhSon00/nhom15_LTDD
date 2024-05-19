package app.ntnt.finalprojectexamonline.model.response;

public class HighScoreResponse {
    private String fullName;
    private Float score;

    public HighScoreResponse(String fullName, Float score) {
        this.fullName = fullName;
        this.score = score;
    }

    public HighScoreResponse() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
