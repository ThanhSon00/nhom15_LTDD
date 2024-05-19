package app.ntnt.finalprojectexamonline.model.response;

import java.util.List;

public class SubjectDataResponse {
    private boolean error;
    private List<SubjectResponse> data;

    public SubjectDataResponse(boolean error, List<SubjectResponse> data) {
        this.error = error;
        this.data = data;
    }

    public SubjectDataResponse() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<SubjectResponse> getData() {
        return data;
    }

    public void setData(List<SubjectResponse> data) {
        this.data = data;
    }
}
