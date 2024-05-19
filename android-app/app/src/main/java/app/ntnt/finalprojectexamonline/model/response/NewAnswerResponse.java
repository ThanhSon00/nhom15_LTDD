package app.ntnt.finalprojectexamonline.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NewAnswerResponse implements Parcelable {
    private Long answerId;
    private String content;
    private boolean correct;

    public NewAnswerResponse(Long answerId, String content, boolean correct) {
        this.answerId = answerId;
        this.content = content;
        this.correct = correct;
    }

    public NewAnswerResponse() {
    }

    protected NewAnswerResponse(Parcel in) {
        if (in.readByte() == 0) {
            answerId = null;
        } else {
            answerId = in.readLong();
        }
        content = in.readString();
        correct = in.readByte() != 0;
    }

    public static final Creator<NewAnswerResponse> CREATOR = new Creator<NewAnswerResponse>() {
        @Override
        public NewAnswerResponse createFromParcel(Parcel in) {
            return new NewAnswerResponse(in);
        }

        @Override
        public NewAnswerResponse[] newArray(int size) {
            return new NewAnswerResponse[size];
        }
    };

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (answerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(answerId);
        }
        dest.writeString(content);
        dest.writeByte((byte) (correct ? 1 : 0));
    }
}
