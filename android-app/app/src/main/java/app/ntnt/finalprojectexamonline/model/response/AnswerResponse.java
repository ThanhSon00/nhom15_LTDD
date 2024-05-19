package app.ntnt.finalprojectexamonline.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AnswerResponse implements Parcelable {
    Long answerId;
    String content;

    public AnswerResponse(Long answerId, String content) {
        this.answerId = answerId;
        this.content = content;
    }

    public AnswerResponse() {
    }

    protected AnswerResponse(Parcel in) {
        if (in.readByte() == 0) {
            answerId = null;
        } else {
            answerId = in.readLong();
        }
        content = in.readString();
    }

    public static final Creator<AnswerResponse> CREATOR = new Creator<AnswerResponse>() {
        @Override
        public AnswerResponse createFromParcel(Parcel in) {
            return new AnswerResponse(in);
        }

        @Override
        public AnswerResponse[] newArray(int size) {
            return new AnswerResponse[size];
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
    }
}
