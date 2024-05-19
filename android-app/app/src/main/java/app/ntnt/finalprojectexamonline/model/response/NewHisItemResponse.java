package app.ntnt.finalprojectexamonline.model.response;

import java.util.List;

public class NewHisItemResponse {
    private Long hisItemId;
    private NewQuestionHistoryResponse question;
    private NewAnswerResponse answer;

    public NewHisItemResponse(Long hisItemId, NewQuestionHistoryResponse question, NewAnswerResponse answer) {
        this.hisItemId = hisItemId;
        this.question = question;
        this.answer = answer;
    }

    public NewHisItemResponse() {
    }

    public Long getHisItemId() {
        return hisItemId;
    }

    public void setHisItemId(Long hisItemId) {
        this.hisItemId = hisItemId;
    }

    public NewQuestionHistoryResponse getQuestion() {
        return question;
    }

    public void setQuestion(NewQuestionHistoryResponse question) {
        this.question = question;
    }

    public NewAnswerResponse getAnswer() {
        return answer;
    }

    public void setAnswer(NewAnswerResponse answer) {
        this.answer = answer;
    }
}
