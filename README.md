# Minefield Game

(EN) Minefield is a game inspired by the classic game "Minesweeper". <br />
(IT) Minefield è un gioco ispirato al classico "Campo Fiorito".




### Installation
(EN) To generate the jar file use the following command:<br />
(IT) Per generare il file jar è necessario l'uso del seguente comando:<br />
```bash
mvn clean package --file pom.xml
```

### Dockerfile
(EN) It is also possible to build and execute the game through the use of docker as follows:<br />
(IT) È inoltre possibile effettuare la build ed eseguire il gioco tramite l'uso di docker come di seguito:<br />
```bash
docker build -t minefield-game:v1.0-SNAPSHOT
docker run -it --rm --net=host -e DISPLAY=$DISPLAY --device=/dev/snd --name minefield-game minefield-game:v1.0-SNAPSHOT 
```

### Disclamer
(EN) All rights of resources used in this project belong to their respective owners. <br />
(IT) Tutti i diritti delle risorse utilizzate in questo progetto sono di proprietà dei rispettivi proprietari.


check the dockerfile


