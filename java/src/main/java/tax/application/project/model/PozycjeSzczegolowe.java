package tax.application.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pozycje_szczegolowe")
public class PozycjeSzczegolowe {
    private long id;
    private int P_7;
    private int P_20;
    private int P_21;
    private int P_22;
    private String P_23;
    private int P_24;
    private int P_25;
    private int P_46;
    private int P_53;
    private int P_62;

    public PozycjeSzczegolowe(int p_7, int p_20, int p_21, int p_22, String p_23, int p_24, int p_25, int p_46, int p_53, int p_62) {
        P_7 = p_7;
        P_20 = p_20;
        P_21 = p_21;
        P_22 = p_22;
        P_23 = p_23;
        P_24 = p_24;
        P_25 = p_25;
        P_46 = p_46;
        P_53 = p_53;
        P_62 = p_62;
    }

    public PozycjeSzczegolowe() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "p_7", nullable = false)
    public int getP_7() {
        return P_7;
    }

    public void setP_7(int p_7) {
        P_7 = p_7;
    }

    @Column(name = "p_20", nullable = false)
    public int getP_20() {
        return P_20;
    }

    public void setP_20(int p_20) {
        P_20 = p_20;
    }

    @Column(name = "p_21", nullable = false)
    public int getP_21() {
        return P_21;
    }

    public void setP_21(int p_21) {
        P_21 = p_21;
    }

    @Column(name = "p_22", nullable = false)
    public int getP_22() {
        return P_22;
    }

    public void setP_22(int p_22) {
        P_22 = p_22;
    }

    @Column(name = "p_23", nullable = false)
    public String getP_23() {
        return P_23;
    }

    public void setP_23(String p_23) {
        P_23 = p_23;
    }

    @Column(name = "p_24", nullable = false)
    public int getP_24() {
        return P_24;
    }

    public void setP_24(int p_24) {
        P_24 = p_24;
    }

    @Column(name = "p_25", nullable = false)
    public int getP_25() {
        return P_25;
    }

    public void setP_25(int p_25) {
        P_25 = p_25;
    }

    @Column(name = "p_46", nullable = false)
    public int getP_46() {
        return P_46;
    }

    public void setP_46(int p_46) {
        P_46 = p_46;
    }

    @Column(name = "p_53", nullable = false)
    public int getP_53() {
        return P_53;
    }

    public void setP_53(int p_53) {
        P_53 = p_53;
    }

    @Column(name = "p_62", nullable = false)
    public int getP_62() {
        return P_62;
    }

    public void setP_62(int p_62) {
        P_62 = p_62;
    }
}
