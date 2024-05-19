package app.ntnt.finalprojectexamonline.model.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuestionRequest implements Serializable {
    private String question;
    private Long topicId;
    @SerializedName("answers")
    private List<AnswerRequest> answerRequestList;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public List<AnswerRequest> getAnswerRequestList() {
        return answerRequestList;
    }

    public void setAnswerRequestList(List<AnswerRequest> answerRequestList) {
        this.answerRequestList = answerRequestList;
    }

    public QuestionRequest() {
    }

    public QuestionRequest(String question, Long topicId, List<AnswerRequest> answerRequestList) {
        this.question = question;
        this.topicId = topicId;
        this.answerRequestList = answerRequestList;
    }
}
