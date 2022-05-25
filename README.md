# Simplicio

**Simplicio** è un programma che semplifica espressioni matematiche mostrando tutti i passaggi fatti.

Per esempio passandogli in input l'espressione `3/(4+2)+2*(3:(4+5)+2^(6*(3/2-1)))`, l'output sarà:

<!-- <p align=center>
    <img src="img/example.png" width=250>
</p> -->

$$\frac{3}{4+2}+2\times\left(3:{\color{blue}{\left({\color{red}{\boxed{4+5}}}\right)}}+{2}^{6\times\left(\frac{3}{2}-1\right)}\right)$$

$$\frac{3}{4+2}+2\times\left(3:{\color{green}{\boxed{9}}}+{2}^{6\times{\color{blue}{\left({\color{red}{\boxed{\frac{3}{2}-1}}}\right)}}}\right)$$

$$\frac{3}{4+2}+2\times{\color{blue}{\left(3:9+{2}^{{\color{red}{\boxed{6\times{\color{green}{\boxed{\frac{1}{2}}}}}}}}\right)}}$$

$$\frac{3}{4+2}+2\times{\color{blue}{\left(3:9+{\color{red}{\boxed{{2}^{{\color{green}{\boxed{3}}}}}}}\right)}}$$

$$\frac{3}{4+2}+2\times{\color{blue}{\left({\color{red}{\boxed{3:9}}}+{\color{green}{\boxed{8}}}\right)}}$$

$$\frac{3}{4+2}+2\times{\color{blue}{\left({\color{red}{\boxed{{\color{green}{\boxed{\frac{1}{3}}}}+8}}}\right)}}$$

$$\frac{3}{{\color{red}{\boxed{4+2}}}}+2\times{\color{green}{\boxed{\frac{25}{3}}}}$$

$${\color{red}{\boxed{\frac{3}{{\color{green}{\boxed{6}}}}}}}+2\times\frac{25}{3}$$

$${\color{green}{\boxed{\frac{1}{2}}}}+{\color{red}{\boxed{2\times\frac{25}{3}}}}$$

$${\color{red}{\boxed{\frac{1}{2}+{\color{green}{\boxed{\frac{50}{3}}}}}}}$$

$${\color{green}{\boxed{\frac{103}{6}}}}$$

Da notare che in ogni espressione:
- la sottoespressione tra parentesi oggetto della semplificazione (se presente) è colorata in blu,
- la sottoespressione che sta per essere semplificata è riquadrata in rosso,
- il risultato della semplificazione del passo precedente (se presente) è riquadrato in verde.

Come si può inoltre vedere dall'esempio, ad ogni passaggio viene svolta una semplificazione all'interno della parentesi più a sinistra tra quelle che che hanno il grado di annidamento massimo.

All'interno di questa parentesi le operazioni vengono svolte nel seguente ordine:
- potenze: prima l'esponente e poi la base
- frazioni: prima il numeratore e poi il denominatore
- moltiplicazioni e divisioni
- somme e sottrazioni
- operatori unari

Da notare inoltre che quando sono presenti più somme e differenze (o moltiplicazioni e divisioni) consecutivamente, vengono svolte in un solo passaggio.

## Dipendenze

Questo progetto utilizza **ANTLR4** come *parser generator*, la cui versione 4.3.9 è scaricabile dal [seguente link](https://www.antlr.org/download/antlr-4.9.3-complete.jar).

Successivamente bisogna allocare la variabile d'ambiente `ANTLR4_JAR` che punti al file jar scaricato.

Su Linux e MacOS digitare:
```bash
$ export ANTLR4_JAR=path
```
dove `path` è il *percorso assoluto* del file appena scaricato.

## Compilazione (solo Linux)

Per compilare basta eseguire lo script `compile.sh`.

Digitare:
```bash
./compile.sh
```

I file generati dalla compilazione sono nella cartella `simplicio/out/`.

## Esecuzione (solo Linux)

Per eseguire il progetto basta lanciare lo script `run.sh` con l'espressione da risolvere come parametro:
```bash
$ ./run.sh "1+2*3"
```

Lo script genererà i file sorgenti latex ed il file compilato nella directory `simplicio/output/` ed aprirà il file `dvi` con il programma predefinito di sistema.
