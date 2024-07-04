package org.java.lessons.eventi;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.DecimalFormat;

public class Concerto extends Evento {
    private LocalTime ora;
    private double prezzo;

    public Concerto(String titolo, String data, int numeroPostiTotali, String ora, double prezzo) {
        super(titolo, data, numeroPostiTotali);
        this.ora = parseOra(ora);
        this.prezzo = prezzo;
    }

    public String getOra() {
        return ora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setOra(String ora) {
        this.ora = parseOra(ora);
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getDataOraFormattata() {
        return getData() + " " + getOra();
    }

    public String getPrezzoFormattato() {
        DecimalFormat df = new DecimalFormat("##,##€");
        return df.format(prezzo);
    }

    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }

    private LocalTime parseOra(String ora) {
        try {
            return LocalTime.parse(ora, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato dell'ora non valido. Usa HH:mm.");
        }
    }
}
