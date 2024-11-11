import java.util.Scanner;
import java.util.ArrayList; // per una array di cavalli
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;



public class GaraCavalli {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("inserisci l'intero percorso per la gara dei cavalli: ");
        int percorsoTotale = input.nextInt();
        input.nextLine();

        List<Cavallo> cavalli = new ArrayList<>();
        System.out.print("Inserire il numero di cavalli in gara: ");
        int numeroCavalli = input.nextInt();
        input.nextLine();

        for(int i =0; i<numeroCavalli; i++){
            System.out.print("Inserisci il nome del cavallo numero " + (i + 1) + ": ");
            String nomeCavallo = input.nextLine();

            System.out.print("Inserisci la velocità (metri al secondo) per il cavallo " + nomeCavallo + ": ");
            int velocita = input.nextInt();

            input.nextLine(); // Consuma l'invio non preso dal input

            // Chiedi all'utente la probabilità di infortunio per ciascun cavallo (da 0 a 100)
            System.out.print("Inserisci la probabilità di infortunio (percentuale, da 0 a 100) per il cavallo " + nomeCavallo + ": ");
            int probabilitaInfortunio = input.nextInt();

            input.nextLine(); // Consuma l'invio

            // Crea il cavallo con le informazioni fornite
            cavalli.add(new Cavallo(nomeCavallo, percorsoTotale, velocita, probabilitaInfortunio));
        }

        input.close();

        System.out.println("LA GARA E INIZIATA");

        for(Cavallo cavallo: cavalli){   //start
            cavallo.start();
        }

        for(Cavallo cavallo: cavalli){
            try {
                cavallo.join(); //Attende il completamento del thread "cavallo"
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n LA GARA E TERMINATA");

        // Creazione classifica dei cavalli anocra in gioco
        List<Cavallo> classificati = new ArrayList<>();
        for (Cavallo cavallo : cavalli) {
            if (!cavallo.isInfortunato()) {
                classificati.add(cavallo);
            }
        }


        // Mostra classifica
        System.out.println("\nClassifica Finale:");
        for (int i = 0; i < Math.min(3, classificati.size()); i++) {
            Cavallo c = classificati.get(i);
            System.out.println((i + 1) + ". " + c.getCavalloName() + " con " + c.getMetriPercorsi() + " metri percorsi.");
        }

        // Salvataggio dei risultati su file
        System.out.print("Inserisci il nome del file dove salvare i risultati (es. risultati.txt): ");
        String nomeFile = input.nextLine();
        try (FileWriter writer = new FileWriter(nomeFile, true)) {
            writer.write("Risultati Gara Cavalli\n");
            for (int i = 0; i < Math.min(3, classificati.size()); i++) {
                Cavallo c = classificati.get(i);
                writer.write((i + 1) + ". " + c.getName() + " con " + c.getMetriPercorsi() + " metri percorsi.\n");
            }
            System.out.println("Risultati salvati nel file " + nomeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}