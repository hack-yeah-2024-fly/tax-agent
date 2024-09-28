package tax.application.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adres_zamieszkania_siedziby")
public class AdresZamieszkaniaSiedziby {
    private long id;
    private AdresPol adresPol;
    private String rodzajAdresu;

    public AdresZamieszkaniaSiedziby() {
    }

    public AdresZamieszkaniaSiedziby(AdresPol adresPol, String rodzajAdresu) {
        this.adresPol = adresPol;
        this.rodzajAdresu = rodzajAdresu;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "rodzaj_adresu", nullable = false)
    public String getRodzajAdresu() {
        return rodzajAdresu;
    }

    public void setRodzajAdresu(String rodzajAdresu) {
        this.rodzajAdresu = rodzajAdresu;
    }
}
