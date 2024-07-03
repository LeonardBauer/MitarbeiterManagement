//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       10:44 03.07.24 


import java.time.LocalDate;

public class Angestellter extends Mitarbeiter{

    private double StundenLohn;
    private double StundenAnzahl;
    private double UeberStundenLohn;
    private double UeberStundenAnzahl;



    public Angestellter(String nachname, String vorname, LocalDate geburtsdatum, char geschlecht) {
        super(nachname, vorname, geburtsdatum, geschlecht);
    }


    @Override
    public double LohnAusgeben() {
        return StundenLohn * StundenAnzahl + UeberStundenLohn * UeberStundenAnzahl;
    }

    @Override
    public void LohnErhoehen() {
        super.LohnErhoehen();
    }
}
