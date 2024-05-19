package app.ntnt.finalprojectexamonline.model.response;

public class HistoryInUserResponse {
    private Long hisId;
    private Long testId;
    private String firstName;
    private String lastName;
    private String testName;
    private int timeInTest;
    private int status;
    private float score;
    private String time;

    public Long getHisId() {
        return hisId;
    }

    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTimeInTest() {
        return timeInTest;
    }

    public void setTimeInTest(int timeInTest) {
        this.timeInTest = timeInTest;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public HistoryInUserResponse() {
    }

    public HistoryInUserResponse(Long hisId, Long testId, String firstName, String lastName, String testName, int timeInTest, int status, float score, String time) {
        this.hisId = hisId;
        this.testId = testId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.testName = testName;
        this.timeInTest = timeInTest;
        this.status = status;
        this.score = score;
        this.time = time;
    }
}
