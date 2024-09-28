package tax.application.project.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "osoba_fizyczna")
public class OsobaFizyczna {
    private long id;
    private double pesel;
    private String imiePierwsze;
    private String nazwisko;
    private Date dataUrodzenia;

    public OsobaFizyczna(double pesel, String imiePierwsze, String nazwisko, Date dataUrodzenia) {
        this.pesel = pesel;
        this.imiePierwsze = imiePierwsze;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
    }

    public OsobaFizyczna() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "pesel", nullable = false)
    public double getPesel() {
        return pesel;
    }

    public void setPesel(double pesel) {
        this.pesel = pesel;
    }

    @Column(name = "imie_pierwsze", nullable = false)
    public String getImiePierwsze() {
        return imiePierwsze;
    }

    public void setImiePierwsze(String imiePierwsze) {
        this.imiePierwsze = imiePierwsze;
    }

    @Column(name = "nazwisko", nullable = false)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Column(name = "data_urodzenia", nullable = false)
    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
}
