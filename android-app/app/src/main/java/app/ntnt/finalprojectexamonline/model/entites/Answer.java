package app.ntnt.finalprojectexamonline.model.entites;

public class Answer {
    private Long id;
    private Long idQuestion;
    private String name;
    private int status;

    public Answer(Long id, Long idQuestion, String name, int status) {
        this.id = id;
        this.idQuestion = idQuestion;
        this.name = name;
        this.status = status;
    }

    public Answer() {
    }

    public Long getId() {
        return id;
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }
}
