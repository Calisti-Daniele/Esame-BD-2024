package database;

import java.util.ArrayList;

public class Query {

    private ArrayList<String> query = new ArrayList<>();
    private ArrayList<String[]> columns = new ArrayList<String[]>();

    private void loadQueries(){
        query.add("Select nome, quantita FROM farmaci WHERE (quantita > 2 OR prezzoVendita > 20) AND data_scadenza > CURDATE()");
        query.add("Select o.dataOrdine, f.nome, of.quantita From ordini o INNER JOIN ordini_effettuati of ON of.ksOrdine = o.idOrdine INNER JOIN farmaci f ON of.ksFarmaco = f.numeroAIC WHERE dataOrdine <= CURDATE();");
        query.add("SELECT SUM(quantita) AS farmaciInMagazzino FROM `farmaci` WHERE data_scadenza > CURDATE();");
        query.add("SELECT c.codiceFiscale, SUM(f.prezzoVendita * a.quantita) AS totale_speso FROM acquisti a INNER JOIN clienti c ON a.ksCliente = c.codiceFiscale INNER JOIN farmaci f ON a.ksFarmaco = f.numeroAIC GROUP BY c.codiceFiscale;");
        query.add("SELECT o.dataOrdine, o.importoLordo, SUM(quantita) AS quantita_totale FROM ordini_effettuati of INNER JOIN ordini o ON o.idOrdine = of.ksOrdine GROUP BY o.idOrdine HAVING quantita_totale > 10");
        query.add("SELECT c.codiceFiscale,c.nome,c.cognome FROM clienti c INNER JOIN acquisti a ON c.codiceFiscale = a.ksCliente GROUP BY c.codiceFiscale HAVING SUM(a.quantita) >= ALL ( SELECT SUM(quantita) FROM acquisti GROUP BY ksCliente );");
    }

    private void loadColumns(){
        columns.add(new String[]{"nome", "quantita"});
        columns.add(new String[]{"dataOrdine","nome","quantita"});
        columns.add(new String[]{"farmaciInMagazzino"});
        columns.add(new String[]{"codiceFiscale","totale_speso"});
        columns.add(new String[]{"dataOrdine","importoLordo","quantita_totale"});
        columns.add(new String[]{"codiceFiscale","nome","cognome"});
    }

    public Query() {
       loadQueries();
       loadColumns();
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
}