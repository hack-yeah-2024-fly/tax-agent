package tax.application.project.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "naglowek")
public class Naglowek {
    private long id;
    private KodFormularza kodFormularza;
    private int wariantFormularza;
    private CelZlozenia celZlozenia;
    private Date data;
    private int kodUrzedu;

    public Naglowek(KodFormularza kodFormularza, int wariantFormularza, CelZlozenia celZlozenia, Date data, int kodUrzedu) {
        this.kodFormularza = kodFormularza;
        this.wariantFormularza = wariantFormularza;
        this.celZlozenia = celZlozenia;
        this.data = data;
        this.kodUrzedu = kodUrzedu;
    }

    public Naglowek() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "warianit_formularza", nullable = false)
    public int getWariantFormularza() {
        return wariantFormularza;
    }

    public void setWariantFormularza(int wariantFormularza) {
        this.wariantFormularza = wariantFormularza;
    }

    @Column(name = "data", nullable = false)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Column(name = "kod_urzedu", nullable = false)
    public int getKodUrzedu() {
        return kodUrzedu;
    }

    public void setKodUrzedu(int kodUrzedu) {
        this.kodUrzedu = kodUrzedu;
    }
}
