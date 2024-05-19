package app.ntnt.finalprojectexamonline.model.request;

import java.io.Serializable;

public class AnswerRequest implements Serializable {
    private String content;
    private Boolean correct;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public AnswerRequest() {
    }

    public AnswerRequest(String content, Boolean correct) {
        this.content = content;
        this.correct = correct;
    }
}
