package app.ntnt.finalprojectexamonline.model.response;

public class ResponseEntity {
    private boolean error;
    private Object data;

    public ResponseEntity(boolean error, Object data) {
        this.error = error;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
