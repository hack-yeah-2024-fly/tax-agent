package tax.application.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cel_zlozenia")
public class CelZlozenia {
    private long id;
    private String poz;

    public CelZlozenia(String poz) {
        this.poz = poz;
    }

    public CelZlozenia() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "poz", nullable = false)
    public String getPoz() {
        return poz;
    }

    public void setPoz(String poz) {
        this.poz = poz;
    }
}
