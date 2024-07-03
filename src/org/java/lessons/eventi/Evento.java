package org.java.lessons.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Evento {
    private String titolo;
    private LocalDate data;
    private int numeroPostiTotali;
    private int numeroPostiPrenotati;

    public Evento(String titolo, String data, int numeroPostiTotali) {
        this.titolo = titolo;
        this.data = parseData(data);
        this.numeroPostiTotali = numeroPostiTotali;
        this.numeroPostiPrenotati = 0;

        if (this.data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento è già passata.");
        }
        if (this.numeroPostiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti totali deve essere positivo.");
        }
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getData() {
        return data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setData(String data) {
        LocalDate newData = parseData(data);
        if (newData.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La nuova data dell'evento è già passata.");
        }
        this.data = newData;
    }

    public int getNumeroPostiTotali() {
        return numeroPostiTotali;
    }

    public int getNumeroPostiPrenotati() {
        return numeroPostiPrenotati;
    }

    public String prenota() {
        if (data.isBefore(LocalDate.now())) {
            return "L'evento è già passato.";
        }
        if (numeroPostiPrenotati >= numeroPostiTotali) {
            return "Non ci sono posti disponibili.";
        }
        numeroPostiPrenotati++;
        return "Prenotazione effettuata con successo.";
    }

    public String disdici() {
        if (data.isBefore(LocalDate.now())) {
            return "L'evento è già passato.";
        }
        if (numeroPostiPrenotati <= 0) {
            return "Non ci sono prenotazioni da disdire.";
        }
        numeroPostiPrenotati--;
        return "Prenotazione disdetta con successo.";
    }

    @Override
    public String toString() {
        return data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " - " + titolo;
    }

    private LocalDate parseData(String data) {
        try {
            return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato della data non valido. Usa dd-MM-yyyy.");
        }
    }
}
