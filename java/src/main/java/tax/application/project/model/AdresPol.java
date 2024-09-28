package tax.application.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adres_pol")
public class AdresPol {
    private long id;
    private String kodKraju;
    private String wojewodztwo;
    private String powiat;
    private String gmina;
    private String ulica;
    private int nrDomu;
    private int nrLokalu;
    private String miejscowosc;
    private String kodPocztowy;

    public AdresPol() {
    }

    public AdresPol(String kodKraju, String wojewodztwo, String powiat, String gmina, String ulica,
                    int nrDomu, int nrLokalu, String miejscowosc, String kodPocztowy) {
        this.kodKraju = kodKraju;
        this.wojewodztwo = wojewodztwo;
        this.powiat = powiat;
        this.gmina = gmina;
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.nrLokalu = nrLokalu;
        this.miejscowosc = miejscowosc;
        this.kodPocztowy = kodPocztowy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "kod_kraju", nullable = false)
    public String getKodKraju() {
        return kodKraju;
    }

    public void setKodKraju(String kodKraju) {
        this.kodKraju = kodKraju;
    }

    @Column(name = "wojewodztwo", nullable = false)
    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    @Column(name = "powiat", nullable = false)
    public String getPowiat() {
        return powiat;
    }

    public void setPowiat(String powiat) {
        this.powiat = powiat;
    }

    @Column(name = "gmina", nullable = false)
    public String getGmina() {
        return gmina;
    }

    public void setGmina(String gmina) {
        this.gmina = gmina;
    }

    @Column(name = "ulica", nullable = false)
    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    @Column(name = "nr_domu", nullable = false)
    public int getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(int nrDomu) {
        this.nrDomu = nrDomu;
    }

    @Column(name = "nr_lokalu", nullable = false)
    public int getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(int nrLokalu) {
        this.nrLokalu = nrLokalu;
    }

    @Column(name = "miejscowosc", nullable = false)
    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    @Column(name = "kod_pocztowy", nullable = false)
    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }
}
