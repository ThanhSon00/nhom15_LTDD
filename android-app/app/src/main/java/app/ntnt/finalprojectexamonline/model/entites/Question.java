package app.ntnt.finalprojectexamonline.model.entites;

import java.io.Serializable;

public class Question implements Serializable {
    private Long id;
    private String name;
    private String image;
    private Long idSubject;

    public Question() {
    }

    public Question(Long id, String name, String image, Long idSubject) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.idSubject = idSubject;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Long getIdSubject() {
        return idSubject;
    }
}
