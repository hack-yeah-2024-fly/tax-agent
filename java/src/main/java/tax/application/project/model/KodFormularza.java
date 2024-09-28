package tax.application.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kod_formularza")
public class KodFormularza {
    private long id;
    private String kodSystemowy;
    private String kodPodatku;
    private String rodzajZobowiazania;
    private String wersjaSchemy;

    public KodFormularza(String kodSystemowy, String kodPodatku, String rodzajZobowiazania, String wersjaSchemy) {
        this.kodSystemowy = kodSystemowy;
        this.kodPodatku = kodPodatku;
        this.rodzajZobowiazania = rodzajZobowiazania;
        this.wersjaSchemy = wersjaSchemy;
    }

    public KodFormularza() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "kod_systemowy", nullable = false)
    public String getKodSystemowy() {
        return kodSystemowy;
    }

    public void setKodSystemowy(String kodSystemowy) {
        this.kodSystemowy = kodSystemowy;
    }

    @Column(name = "kod_podatku", nullable = false)
    public String getKodPodatku() {
        return kodPodatku;
    }

    public void setKodPodatku(String kodPodatku) {
        this.kodPodatku = kodPodatku;
    }

    @Column(name = "rodzaj_zobowiazania", nullable = false)
    public String getRodzajZobowiazania() {
        return rodzajZobowiazania;
    }

    public void setRodzajZobowiazania(String rodzajZobowiazania) {
        this.rodzajZobowiazania = rodzajZobowiazania;
    }

    @Column(name = "wersja_schemy", nullable = false)
    public String getWersjaSchemy() {
        return wersjaSchemy;
    }

    public void setWersjaSchemy(String wersjaSchemy) {
        this.wersjaSchemy = wersjaSchemy;
    }
}
