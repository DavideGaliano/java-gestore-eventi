package org.java.lessons.eventi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //form utente
        
        System.out.println("Inserisci il titolo dell'evento:");
        String titolo = scanner.nextLine();

        System.out.println("Inserisci la data dell'evento (dd-MM-yyyy):");
        String data = scanner.nextLine();

        System.out.println("Inserisci il numero totale di posti:");
        int numeroPostiTotali = scanner.nextInt();

        Evento evento = null;
        try {
            evento = new Evento(titolo, data, numeroPostiTotali); //Se il costruttore della classe Evento rileva un problema (come una data passata o un numero di posti totali non positivo), lancerà un'eccezione di tipo IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); //messaggio di errore passato dal costruttore
            System.exit(1);
        }

        System.out.println("Vuoi fare prenotazioni? (si/no)");
        scanner.nextLine(); 
        String risposta = scanner.nextLine();

        if (risposta.equalsIgnoreCase("si")) {
            System.out.println("Quante prenotazioni vuoi fare?");
            int prenotazioni = scanner.nextInt(); //legge un numero intero inserito dall'utente e lo assegna alla variabile prenotazioni
            for (int i = 0; i < prenotazioni; i++) {
                String result = evento.prenota();
                if (!result.equals("Prenotazione effettuata con successo.")) { //Se result non è uguale a "Prenotazione effettuata con successo.", significa che c'è stato un problema
                    System.out.println(result);
                    break;
                }
            }
        }

        System.out.println("Posti prenotati: " + evento.getNumeroPostiPrenotati());
        System.out.println("Posti disponibili: " + (evento.getNumeroPostiTotali() - evento.getNumeroPostiPrenotati()));

        System.out.println("Vuoi disdire prenotazioni? (si/no)");
        scanner.nextLine();
        risposta = scanner.nextLine();

        if (risposta.equalsIgnoreCase("si")) {
            System.out.println("Quante prenotazioni vuoi disdire?");
            int disdette = scanner.nextInt();
            for (int i = 0; i < disdette; i++) {
                String result = evento.disdici();
                if (!result.equals("Prenotazione disdetta con successo.")) {
                    System.out.println(result);
                    break;
                }
            }
        }

        System.out.println("Posti prenotati: " + evento.getNumeroPostiPrenotati());
        System.out.println("Posti disponibili: " + (evento.getNumeroPostiTotali() - evento.getNumeroPostiPrenotati()));
    }
}

