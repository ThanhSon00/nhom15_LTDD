package app.ntnt.finalprojectexamonline.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.List;

public class TestResponse implements Parcelable {
    private Long testId;
    private String dateCreate;
    private int quantity;
    private int status;
    private String testName;
    private int time;
    private List<QuestionResponse> questions;

    protected TestResponse(Parcel in) {
        if (in.readByte() == 0) {
            testId = null;
        } else {
            testId = in.readLong();
        }
        dateCreate = in.readString();
        quantity = in.readInt();
        status = in.readInt();
        testName = in.readString();
        time = in.readInt();
    }

    public TestResponse(Long testId, String dateCreate, String testName, int time) {
        this.testId = testId;
        this.dateCreate = dateCreate;
        this.testName = testName;
        this.time = time;
    }

    public static final Creator<TestResponse> CREATOR = new Creator<TestResponse>() {
        @Override
        public TestResponse createFromParcel(Parcel in) {
            return new TestResponse(in);
        }

        @Override
        public TestResponse[] newArray(int size) {
            return new TestResponse[size];
        }
    };

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<QuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResponse> questions) {
        this.questions = questions;
    }

    public TestResponse() {
    }

    public TestResponse(Long testId, String dateCreate, int quantity, int status, String testName, int time, List<QuestionResponse> questions) {
        this.testId = testId;
        this.dateCreate = dateCreate;
        this.quantity = quantity;
        this.status = status;
        this.testName = testName;
        this.time = time;
        this.questions = questions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (testId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(testId);
        }
        dest.writeString(dateCreate);
        dest.writeInt(quantity);
        dest.writeInt(status);
        dest.writeString(testName);
        dest.writeInt(time);
    }
}
