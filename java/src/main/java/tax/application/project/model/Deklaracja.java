package tax.application.project.model;

public class Deklaracja {

    private long id;
    private Naglowek naglowek;
    private Podmiot1 podmiot1;
    private PozycjeSzczegolowe pozycjeSzczegolowe;
    private int pouczenia;

    public Deklaracja(Naglowek naglowek, Podmiot1 podmiot1, PozycjeSzczegolowe pozycjeSzczegolowe, int pouczenia) {
        this.naglowek = naglowek;
        this.podmiot1 = podmiot1;
        this.pozycjeSzczegolowe = pozycjeSzczegolowe;
        this.pouczenia = pouczenia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Naglowek getNaglowek() {
        return naglowek;
    }

    public void setNaglowek(Naglowek naglowek) {
        this.naglowek = naglowek;
    }

    public Podmiot1 getPodmiot1() {
        return podmiot1;
    }

    public void setPodmiot1(Podmiot1 podmiot1) {
        this.podmiot1 = podmiot1;
    }

    public PozycjeSzczegolowe getPozycjeSzczegolowe() {
        return pozycjeSzczegolowe;
    }

    public void setPozycjeSzczegolowe(PozycjeSzczegolowe pozycjeSzczegolowe) {
        this.pozycjeSzczegolowe = pozycjeSzczegolowe;
    }

    public int getPouczenia() {
        return pouczenia;
    }

    public void setPouczenia(int pouczenia) {
        this.pouczenia = pouczenia;
    }
}
