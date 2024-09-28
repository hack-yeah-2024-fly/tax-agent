package tax.application.project.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "podmiot1")
public class Podmiot1 {
    private long id;
    private OsobaFizyczna osobaFizyczna;
    private AdresZamieszkaniaSiedziby adresZamieszkaniaSiedziby;
    private String rola;

    public Podmiot1(OsobaFizyczna osobaFizyczna, AdresZamieszkaniaSiedziby adresZamieszkaniaSiedziby, String rola) {
        this.osobaFizyczna = osobaFizyczna;
        this.adresZamieszkaniaSiedziby = adresZamieszkaniaSiedziby;
        this.rola = rola;
    }

    public Podmiot1() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "rola", nullable = false)
    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }
}
