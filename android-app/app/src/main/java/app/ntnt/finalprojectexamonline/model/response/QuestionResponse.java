package app.ntnt.finalprojectexamonline.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

import app.ntnt.finalprojectexamonline.model.entites.Answer;

public class QuestionResponse implements Parcelable {
    Long questionId;
    String question;
    String image;
    List<AnswerResponse> answers;

    public QuestionResponse(Long questionId, String question, String image, List<AnswerResponse> answers) {
        this.questionId = questionId;
        this.question = question;
        this.image = image;
        this.answers = answers;
    }

    public QuestionResponse() {
    }

    protected QuestionResponse(Parcel in) {
        if (in.readByte() == 0) {
            questionId = null;
        } else {
            questionId = in.readLong();
        }
        question = in.readString();
        image = in.readString();
    }

    public static final Creator<QuestionResponse> CREATOR = new Creator<QuestionResponse>() {
        @Override
        public QuestionResponse createFromParcel(Parcel in) {
            return new QuestionResponse(in);
        }

        @Override
        public QuestionResponse[] newArray(int size) {
            return new QuestionResponse[size];
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

    public List<AnswerResponse> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerResponse> answers) {
        this.answers = answers;
    }

    @Override
    public String toString()
    {
        return question;
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
    }
}
