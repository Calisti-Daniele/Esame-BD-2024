- Una selezione ordinata su un attributo di una tabella con condizioni AND e OR;

    Select nome, quantita FROM farmaci WHERE (quantita > 2 OR prezzoVendita > 20) AND data_scadenza > CURDATE()

- Una selezione su due o più tabelle con condizioni;

    //Prendo tutti i farmaci e le relative quantita e relative date degli ordini da oggi in giù
    Select o.dataOrdine, f.nome, of.quantita From ordini o INNER JOIN ordini_effettuati of ON of.ksOrdine = o.idOrdine INNER JOIN farmaci f ON of.ksFarmaco = f.numeroAIC WHERE dataOrdine <= CURDATE();

- Una selezione aggregata su tutti i valori (es. somma di tutti gli stipendi)

    //Prendo il numero di farmaci che ho in magazzino non scaduti
    SELECT SUM(quantita) AS farmaciInMagazzino FROM `farmaci` WHERE data_scadenza > CURDATE();

- Una selezione aggregata su raggruppamenti (es. somma stipendi per dipartimenti)

    //Per ogni cliente prendo quanto ha speso
    SELECT c.codiceFiscale, SUM(f.prezzoVendita * a.quantita) AS totale_speso FROM acquisti a INNER JOIN clienti c ON a.ksCliente = c.codiceFiscale INNER JOIN farmaci f ON a.ksFarmaco = f.numeroAIC GROUP BY c.codiceFiscale;

- Una selezione aggregata su raggruppamenti con condizioni (es. dipartimenti la cui somma degli stipendi dei dipendenti è > 100k)

    //Prendo tutti gli ordini nei quali ho ordinato complessivamente più di 10 farmaci
    SELECT o.dataOrdine, o.importoLordo, SUM(quantita) AS quantita_totale FROM ordini_effettuati of INNER JOIN ordini o ON o.idOrdine = of.ksOrdine GROUP BY o.idOrdine HAVING quantita_totale > 10

    - Una selezione aggregata su raggruppamenti con condizioni che includano un’altra funzione di raggruppamento (es. dipartimenti la cui somma degli stipendi è la più alta)

    //Prendo il cliente che ha comprato più farmaci di tutti (N.B. non quello che ha speso di più)
    SELECT c.codiceFiscale
    FROM clienti c
             INNER JOIN acquisti a ON c.codiceFiscale = a.ksCliente
    GROUP BY c.codiceFiscale
    HAVING SUM(a.quantita) >= ALL (
        SELECT SUM(quantita)
        FROM acquisti
        GROUP BY ksCliente
    );

    //Prendo il cliente che ha speso di più di tutti
    SELECT c.codiceFiscale
    FROM clienti c
             INNER JOIN acquisti a ON c.codiceFiscale = a.ksCliente
             INNER JOIN farmaci f ON a.ksFarmaco = f.numeroAIC
    GROUP BY c.codiceFiscale
    HAVING SUM(a.quantita * f.prezzoVendita) >= ALL (
        SELECT SUM(a.quantita * f.prezzoVendita)
        FROM acquisti a JOIN farmaci f ON a.ksFarmaco = f.numeroAIC
        GROUP BY a.ksCliente
    );

- Una selezione con operazioni insiemistiche
- Una selezione con l’uso appropriato della divisione.