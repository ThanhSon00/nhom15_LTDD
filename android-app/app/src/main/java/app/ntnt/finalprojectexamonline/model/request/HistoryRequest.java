package app.ntnt.finalprojectexamonline.model.request;

import java.util.List;

public class HistoryRequest {
    private Long userId;
    private Long testId;
    private List<Long> questionIds;
    private List<Long> answerIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public List<Long> getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(List<Long> answerIds) {
        this.answerIds = answerIds;
    }

    public HistoryRequest(Long userId, Long testId, List<Long> questionIds, List<Long> answerIds) {
        this.userId = userId;
        this.testId = testId;
        this.questionIds = questionIds;
        this.answerIds = answerIds;
    }

    public HistoryRequest() {
    }
}
