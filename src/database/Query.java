package database;

import java.util.ArrayList;

public class Query {

    private ArrayList<String> query = new ArrayList<>();
    private ArrayList<String[]> columns = new ArrayList<String[]>();
    private ArrayList<String> descrizioni = new ArrayList<>();

    private void loadQueries(){
        query.add("Select nome, quantita, prezzoVendita, data_scadenza FROM farmaci WHERE (quantita > 3 OR prezzoVendita > 15) AND (data_scadenza > CURRENT_DATE) ORDER BY quantita DESC");
        query.add("Select o.dataOrdine, f.nome, of.quantita From ordini o INNER JOIN ordini_effettuati of ON of.ksOrdine = o.idOrdine INNER JOIN farmaci f ON of.ksFarmaco = f.numeroAIC WHERE dataOrdine <= CURDATE();");
        query.add("SELECT SUM(quantita) AS farmaciInMagazzino FROM `farmaci` WHERE data_scadenza > CURDATE();");
        query.add("SELECT c.codiceFiscale, SUM(f.prezzoVendita * a.quantita) AS totale_speso FROM acquisti a INNER JOIN clienti c ON a.ksCliente = c.codiceFiscale INNER JOIN farmaci f ON a.ksFarmaco = f.numeroAIC GROUP BY c.codiceFiscale;");
        query.add("SELECT o.dataOrdine,o.importoNetto, o.importoLordo, SUM(quantita) AS quantita_totale FROM ordini_effettuati of INNER JOIN ordini o ON o.idOrdine = of.ksOrdine GROUP BY o.dataOrdine, o.importoNetto, o.importoLordo HAVING quantita_totale > 10");
        query.add("SELECT c.codiceFiscale,c.nome,c.cognome FROM clienti c INNER JOIN acquisti a ON c.codiceFiscale = a.ksCliente GROUP BY c.codiceFiscale,c.nome,c.cognome HAVING SUM(a.quantita) >= ALL ( SELECT SUM(quantita) FROM acquisti GROUP BY ksCliente );");
        query.add("SELECT fa.numeroAIC,fa.nome,fa.tipo FROM farmaci fa EXCEPT SELECT f.numeroAIC,f.nome,f.tipo FROM acquisti a INNER JOIN farmaci f ON a.ksFarmaco = f.numeroAIC;");
    }

    private void loadColumns(){
        columns.add(new String[]{"nome", "quantita", "prezzoVendita", "data_scadenza"});
        columns.add(new String[]{"dataOrdine","nome","quantita"});
        columns.add(new String[]{"farmaciInMagazzino"});
        columns.add(new String[]{"codiceFiscale","totale_speso"});
        columns.add(new String[]{"dataOrdine","importoNetto","importoLordo","quantita_totale"});
        columns.add(new String[]{"codiceFiscale","nome","cognome"});
        columns.add(new String[]{"numeroAIC","nome","tipo"});
    }

    private void loadDesc(){
        descrizioni.add("<html>Una selezione ordinata su un attributo di una tabella con condizioni<br><b>Elencare nome, quantita, prezzo di vendita e scadenza dei farmaci presenti in magazzino che non sono scaduti e che hanno un prezzo di vendita maggiore di 15€ oppure che siano presenti almeno 3 unità di quel farmaco ordinati in base alla quantità in ordine decrescente</b>");
        descrizioni.add("<html>Una selezione su due o più tabelle con condizioni<br><b>Elencare tutti i farmaci, le relative quantita e le relative date degli ordini fatti fino ad oggi.</b>");
        descrizioni.add("<html>Una selezione aggregata su tutti i valori<br><b>Elencare il numero di farmaci presenti in magazzino non scaduti.</b>");
        descrizioni.add("<html>Una selezione aggregata su raggruppamenti<br><b>Per ogni cliente, elencare quanto ha speso.</b>");
        descrizioni.add("<html>Una selezione aggregata su raggruppamenti con condizioni<br><b>Elencare tutti gli ordini che includono complessivamente più di 10 farmaci.</b>");
        descrizioni.add("<html>Una selezione aggregata su raggruppamenti con condizioni che includano un’altra funzione di raggruppamento<br><b>Individuare il cliente che ha acquistato più farmaci (N.B. non quello che ha speso di più) </b>");
        descrizioni.add("<html>Una selezione con operazioni insiemistiche<br><b>Elencare tutti i farmaci che non sono mai stati acquistati.</b>");
    }

    public Query() {
       loadQueries();
       loadColumns();
       loadDesc();
    }

    public int getNumQueries(){
        return query.size();
    }

    public String getQuery(int index){
        return query.get(index);
    }

    public String[] getColumns(int index){
        return columns.get(index);
    }

    public String getDesc(int index){
        return descrizioni.get(index);
    }

}