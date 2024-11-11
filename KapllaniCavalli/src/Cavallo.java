import java.util.Random;

public class Cavallo extends Thread {

    private String name ;
    private int metriTot;
    private int metriStart=0;
    private static boolean raceOver = false;
    private int velocita;
    private int probabilita;
    private boolean infortunato= false ;
    Random rand = new Random();

    public Cavallo(String name, int metriTot, int velocita, int probabilita){
        this.name = name;
        this.metriTot=  metriTot;
        this.velocita= velocita;
        this.probabilita= probabilita;
    }

    @Override
    public void run() {
        // crea radomico numero tra 0 e 9
       while(metriStart<metriTot){
           if( raceOver || infortunato){  //se sono gia tutte e due veri o uno dei due
               System.out.println("il cavallo: "+ this.name + " si ferma perché la gara è già finita o è infortunato");
               return; // chiedere se andava bene anche il break
           }

           if (raceOver) {
               System.out.println("Il cavallo " + this.name + " si ferma perché la gara è già finita.");
               return;
           }
           metriStart += velocita; //costante

           if (rand.nextInt(100)+1 < probabilita) {
               infortunato = true;
               System.out.println("Il cavallo " + this.name + " si è infortunato e esce dalla gara.");
               return; // Ferma il thread del cavallo
           }


           // check del valore
           if (metriStart >= metriTot) {
               metriStart = metriTot;
               if (!raceOver) { // If race is not over, this horse is the winner
                   raceOver = true;
                   System.out.println("Il cavallo " + this.name + " è arrivato primo al traguardo!");
               }
               break;
           }


           System.out.println("il cavallo: "+ this.name+" ha percorso: "+ metriStart);
           try{
               Thread.sleep(300); // aspetta 3ms per rifare il ciclo while
           }catch (InterruptedException e){
               e.printStackTrace(); // mi dice  dove si trovera l'eventuale errore
           }


       }
    }
    public int getMetriPercorsi() {
        return metriStart;  // Restituisce i metri percorsi dal cavallo
    }

    // Metodo per verificare se il cavallo è infortunato
    public boolean isInfortunato() {
        return infortunato;
    }

    // Metodo per ottenere il nome del cavallo
    public String getCavalloName() {
        return name;
    }
}
