package app.ntnt.finalprojectexamonline.model.response;

import java.util.List;

import app.ntnt.finalprojectexamonline.model.entites.Topic;

public class TopicResponse {
    private boolean error;
    private List<Topic> data;

    public TopicResponse(boolean error, List<Topic> data) {
        this.error = error;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Topic> getData() {
        return data;
    }

    public void setData(List<Topic> data) {
        this.data = data;
    }
}
