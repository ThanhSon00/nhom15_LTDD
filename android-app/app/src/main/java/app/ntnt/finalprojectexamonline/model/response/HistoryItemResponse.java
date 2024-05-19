package app.ntnt.finalprojectexamonline.model.response;

import java.util.List;

public class HistoryItemResponse {
    private Long hisId;
    private Long userId;
    private Long testId;
    private Float score;
    private String totalCorrect;
    private String time;
    private List<NewHisItemResponse> hisItems;

    public HistoryItemResponse(Long hisId, Long userId, Long testId, Float score, String totalCorrect, String time, List<NewHisItemResponse> hisItems) {
        this.hisId = hisId;
        this.userId = userId;
        this.testId = testId;
        this.score = score;
        this.totalCorrect = totalCorrect;
        this.time = time;
        this.hisItems = hisItems;
    }

    public HistoryItemResponse() {
    }

    public Long getHisId() {
        return hisId;
    }

    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }

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

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalCorrect(String totalCorrect) {
        this.totalCorrect = totalCorrect;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<NewHisItemResponse> getHisItems() {
        return hisItems;
    }

    public void setHisItems(List<NewHisItemResponse> hisItems) {
        this.hisItems = hisItems;
    }
}
