package org.java.lessons.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammaEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }

    public int getNumeroEventi() {
        return eventi.size();
    }

    public void svuotaEventi() {
        eventi.clear();
    }

    public List<Evento> getEventiOrdinatiPerData() {
        List<Evento> eventiOrdinati = new ArrayList<>(eventi);
        bubbleSort(eventiOrdinati);
        return eventiOrdinati;
    }

    private void bubbleSort(List<Evento> eventi) {
        int n = eventi.size();
        boolean swapped;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//converte le stringhe nel formato dd-MM-yyyy

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                LocalDate data1 = LocalDate.parse(eventi.get(i).getData(), formatter);
                LocalDate data2 = LocalDate.parse(eventi.get(i + 1).getData(), formatter);
                if (data1.isAfter(data2)) {
                    Evento temp = eventi.get(i);
                    eventi.set(i, eventi.get(i + 1));
                    eventi.set(i + 1, temp);
                    swapped = true;
                }//ciclo: Se data1 è successiva a data2, i due eventi vengono scambiati.
                //L'evento alla posizione i viene salvato in una variabile temporanea temp.
                //L'evento alla posizione i + 1 viene spostato alla posizione i.
                //L'evento salvato in temp viene spostato alla posizione i + 1.
                //swapped è impostata a true per indicare che è avvenuto uno scambio.
            }
            n--; // Riduce il range di confronto perché l'ultimo elemento è già ordinato
        } while (swapped);
    }

    @Override
    public String toString() {
        return titolo + "\n" + getEventiOrdinatiPerData().stream()
                                     .map(Evento::toString)
                                     .collect(Collectors.joining("\n"));
    }
}
