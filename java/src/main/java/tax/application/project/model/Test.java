package tax.application.project.model;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test {
    private long id;
    private String text;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
