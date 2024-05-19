package app.ntnt.finalprojectexamonline.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class NewQuestionHistoryResponse implements Parcelable {
    private Long questionId;
    private String question;
    private String image;
    private int status;
    private List<NewAnswerResponse> answers;

    public NewQuestionHistoryResponse(Long questionId, String question, String image, int status, List<NewAnswerResponse> answers) {
        this.questionId = questionId;
        this.question = question;
        this.image = image;
        this.status = status;
        this.answers = answers;
    }

    public NewQuestionHistoryResponse() {
    }

    protected NewQuestionHistoryResponse(Parcel in) {
        if (in.readByte() == 0) {
            questionId = null;
        } else {
            questionId = in.readLong();
        }
        question = in.readString();
        image = in.readString();
        status = in.readInt();
    }

    public static final Creator<NewQuestionHistoryResponse> CREATOR = new Creator<NewQuestionHistoryResponse>() {
        @Override
        public NewQuestionHistoryResponse createFromParcel(Parcel in) {
            return new NewQuestionHistoryResponse(in);
        }

        @Override
        public NewQuestionHistoryResponse[] newArray(int size) {
            return new NewQuestionHistoryResponse[size];
        }
    };

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<NewAnswerResponse> getAnswers() {
        return answers;
    }

    public void setAnswers(List<NewAnswerResponse> answers) {
        this.answers = answers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (questionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(questionId);
        }
        dest.writeString(question);
        dest.writeString(image);
        dest.writeInt(status);
    }
}
