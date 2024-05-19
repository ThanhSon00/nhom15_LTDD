package app.ntnt.finalprojectexamonline.model.request;

import java.util.List;

public class TestRequest {
    private String testName;
    private int quantity;
    private int time;
    private List<Long> questionIds;
    private List<Long> topicIds;

    public TestRequest() {
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public List<Long> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<Long> topicIds) {
        this.topicIds = topicIds;
    }

    public TestRequest(String testName, int quantity, int time, List<Long> questionIds, List<Long> topicIds) {
        this.testName = testName;
        this.quantity = quantity;
        this.time = time;
        this.questionIds = questionIds;
        this.topicIds = topicIds;
    }

}
