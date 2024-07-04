package org.java.lessons.eventi;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il titolo del programma di eventi:");
        String titoloProgramma = scanner.nextLine();

        ProgrammaEventi programmaEventi = new ProgrammaEventi(titoloProgramma);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Aggiungi evento");
            System.out.println("2. Aggiungi concerto");
            System.out.println("3. Visualizza tutti gli eventi");
            System.out.println("4. Visualizza eventi per data");
            System.out.println("5. Visualizza numero di eventi");
            System.out.println("6. Svuota eventi");
            System.out.println("7. Visualizza eventi ordinati per data");
            System.out.println("8. Esci");
            System.out.print("Scegli un'opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    System.out.println("Inserisci il titolo dell'evento:");
                    String titoloEvento = scanner.nextLine();

                    System.out.println("Inserisci la data dell'evento (dd-MM-yyyy):");
                    String dataEvento = scanner.nextLine();

                    System.out.println("Inserisci il numero totale di posti:");
                    int numeroPostiTotali = scanner.nextInt();

                    try {
                        Evento evento = new Evento(titoloEvento, dataEvento, numeroPostiTotali);
                        programmaEventi.aggiungiEvento(evento);
                        System.out.println("Evento aggiunto con successo.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Inserisci il titolo del concerto:");
                    String titoloConcerto = scanner.nextLine();

                    System.out.println("Inserisci la data del concerto (dd-MM-yyyy):");
                    String dataConcerto = scanner.nextLine();

                    System.out.println("Inserisci l'ora del concerto (HH:mm):");
                    String oraConcerto = scanner.nextLine();

                    System.out.println("Inserisci il numero totale di posti:");
                    int numeroPostiConcerto = scanner.nextInt();

                    System.out.println("Inserisci il prezzo del concerto:");
                    double prezzoConcerto = scanner.nextDouble();

                    try {
                        Concerto concerto = new Concerto(titoloConcerto, dataConcerto, numeroPostiConcerto, oraConcerto, prezzoConcerto);
                        programmaEventi.aggiungiEvento(concerto);
                        System.out.println("Concerto aggiunto con successo.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println(programmaEventi);
                    break;

                case 4:
                    System.out.println("Inserisci la data (dd-MM-yyyy):");
                    String data = scanner.nextLine();
                    List<Evento> eventiPerData = programmaEventi.getEventiOrdinatiPerData().stream()
                                                                .filter(evento -> evento.getData().equals(data))
                                                                .collect(Collectors.toList());//raccoglie tutti gli eventi filtrati in una nuova lista e la restituisce
                    if (eventiPerData.isEmpty()) {
                        System.out.println("Nessun evento trovato per la data specificata.");
                    } else {
                        eventiPerData.forEach(System.out::println);
                    }
                    break;

                case 5:
                    System.out.println("Numero di eventi: " + programmaEventi.getNumeroEventi());
                    break;

                case 6:
                    programmaEventi.svuotaEventi();
                    System.out.println("Tutti gli eventi sono stati rimossi.");
                    break;

                case 7:
                    System.out.println("Eventi ordinati per data:");
                    List<Evento> eventiOrdinati = programmaEventi.getEventiOrdinatiPerData();
                    eventiOrdinati.forEach(System.out::println);
                    break;

                case 8:
                    System.out.println("Uscita dal programma.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }
}
