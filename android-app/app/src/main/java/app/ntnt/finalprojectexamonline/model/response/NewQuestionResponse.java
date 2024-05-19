package app.ntnt.finalprojectexamonline.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NewQuestionResponse implements Parcelable {
    private QuestionResponse questionResponse;
    private Long positionChecked;

    public NewQuestionResponse(QuestionResponse questionResponse, Long positionChecked) {
        this.questionResponse = questionResponse;
        this.positionChecked = positionChecked;
    }

    public NewQuestionResponse() {
    }

    public QuestionResponse getQuestionResponse() {
        return questionResponse;
    }

    public void setQuestionResponse(QuestionResponse questionResponse) {
        this.questionResponse = questionResponse;
    }

    public Long getPositionChecked() {
        return positionChecked;
    }

    public void setPositionChecked(Long positionChecked) {
        this.positionChecked = positionChecked;
    }

    protected NewQuestionResponse(Parcel in) {
        questionResponse = in.readParcelable(QuestionResponse.class.getClassLoader());
        positionChecked = in.readLong();
    }

    public static final Creator<NewQuestionResponse> CREATOR = new Creator<NewQuestionResponse>() {
        @Override
        public NewQuestionResponse createFromParcel(Parcel in) {
            return new NewQuestionResponse(in);
        }

        @Override
        public NewQuestionResponse[] newArray(int size) {
            return new NewQuestionResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(questionResponse, flags);
        dest.writeLong(positionChecked);
    }

    @Override
    public String toString()
    {
        return String.valueOf(positionChecked);
    }
}
