//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       10:28 03.07.24 


import java.time.LocalDate;

public abstract class Mitarbeiter {
    private final char geschlecht;
    private String nachname;
    private String vorname;
    private LocalDate geburtsdatum;
    private LocalDate eintrittsDatum;
    private LocalDate austrittsDatum;

    public Mitarbeiter(String nachname, String vorname, LocalDate geburtsdatum, char geschlecht) {
        this.geschlecht = geschlecht;
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.eintrittsDatum = LocalDate.now();
    }


    public char getGeschlecht() {
        return geschlecht;
    }
    public void LohnErhoehen(){

    }
    public double LohnAusgeben(){
        return 0;
    }
    public double GehaltBerechnen () {
        return 0;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public LocalDate getEintrittsDatum() {
        return eintrittsDatum;
    }

    public void setEintrittsDatum(LocalDate eintrittsDatum) {
        this.eintrittsDatum = eintrittsDatum;
    }

    public LocalDate getAustrittsDatum() {
        return austrittsDatum;
    }

    public void setAustrittsDatum(LocalDate austrittsDatum) {
        this.austrittsDatum = austrittsDatum;
    }
}
