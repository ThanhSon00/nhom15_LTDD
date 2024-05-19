package app.ntnt.finalprojectexamonline.model.response;

public class DeleteResponse {
    private String error;
    private MessageResponse data;

    public DeleteResponse(String error, MessageResponse data) {
        this.error = error;
        this.data = data;
    }

    public DeleteResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public MessageResponse getData() {
        return data;
    }

    public void setData(MessageResponse data) {
        this.data = data;
    }
}
