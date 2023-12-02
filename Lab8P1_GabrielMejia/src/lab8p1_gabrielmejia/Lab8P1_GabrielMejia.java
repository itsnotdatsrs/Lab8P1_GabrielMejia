package lab8p1_gabrielmejia;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab8P1_GabrielMejia { // Fila 3 Asiento 12
    /* Memo deberia de stream otra vez digo yo
    Apex Legends o algo
    Ponganlo a jugar la nueva temporada de fortnite */
    
    
    private int[][] currentGeneration;
    private int[][] nextGeneration;
    private ArrayList<String> liveCellCoordinates;
    private int rounds;

    // Constructor
    public Lab8P1_GabrielMejia() {
        this.currentGeneration = new int[10][10];
        this.nextGeneration = new int[10][10];
        this.liveCellCoordinates = new ArrayList<>();
        this.rounds = 0;
    }

    // Getters y setters para la generacion 
    public int[][] getCurrentGeneration() {
        return currentGeneration;
    }

    public void setCurrentGeneration(int[][] currentGeneration) {
        this.currentGeneration = currentGeneration;
    }

    // Getters y setters para la siguiente generacion
    public int[][] getNextGeneration() {
        return nextGeneration;
    }

    public void setNextGeneration(int[][] nextGeneration) {
        this.nextGeneration = nextGeneration;
    }

    // obtener las coordenas de las celdas vivas
    public ArrayList<String> getLiveCellCoordinates() {
        return liveCellCoordinates;
    }

    public void setLiveCellCoordinates(ArrayList<String> liveCellCoordinates) {
        this.liveCellCoordinates = liveCellCoordinates;
    }

    // metodo para rondas
    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    // Metodo para llenar el tablero de forma random y actualizar la generacion
    public void initializeBoard() {
        Random random = new Random();

        for (int i = 1; i < currentGeneration.length - 1; i++) {
            for (int j = 1; j < currentGeneration[i].length - 1; j++) {
                int randomValue = random.nextInt(2);
                currentGeneration[i][j] = randomValue;
                if (randomValue == 1) {
                    liveCellCoordinates.add(i + ":" + j);
                }
            }
        }
    }

    // metodo principal
    public void play(int rounds) {
        this.rounds = rounds;
        print(liveCellCoordinates); // Imprimir el tablero inicial
        for (int i = 1; i <= rounds; i++) {
            System.out.println("Ronda " + i + ":");
            nextGen();
            print(liveCellCoordinates);
        }
    }

    // metodo para la siguiente generacion
    public void nextGen() {
        liveCellCoordinates.clear(); // Limpiar las coordenadas de celulas vivas para la siguiente ronda

        for (int i = 1; i < currentGeneration.length - 1; i++) {
            for (int j = 1; j < currentGeneration[i].length - 1; j++) {
                int neighbors = countNeighbors(i, j);

                if (currentGeneration[i][j] == 1) {
                    // Celulas vivas
                    if (neighbors < 2 || neighbors > 3) {
                        // Reglas para Celulas
                        nextGeneration[i][j] = 0;
                    } else {
                        // if la Celula sigue viva
                        nextGeneration[i][j] = 1;
                        liveCellCoordinates.add(i + ":" + j);
                    }
                } else {
                    // Celula vacia
                    if (neighbors == 3) {
                        // Regla para Celula vacia
                        nextGeneration[i][j] = 1;
                        liveCellCoordinates.add(i + ":" + j);
                    } else {
                        // if Celula sigue vacia
                        nextGeneration[i][j] = 0;
                    }
                }
            }
        }

        // Convertir currentGeneration a nextGeneration
        int[][] temp = currentGeneration;
        currentGeneration = nextGeneration;
        nextGeneration = temp;
    }

    // metodo para contar vecinos
    private int countNeighbors(int x, int y) {
        int count = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                count += currentGeneration[i][j];
            }
        }

        count -= currentGeneration[x][y];
        return count;
    }

    // imprimir el tablero
    public void print(ArrayList<String> liveCells) {
        System.out.println("Coordenadas de celdas vivas:");
        System.out.println(liveCells);

        for (int i = 0; i < currentGeneration.length; i++) {
            for (int j = 0; j < currentGeneration[i].length; j++) {
                System.out.print("[" + currentGeneration[i][j] + "]");
            }
            System.out.println();
        }
    }
    
    public static void main (String[]args){
        Scanner menu = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 2) {
            System.out.println("Menu:");
            System.out.println("1. Game of Life");
            System.out.println("4. Salir");

            System.out.print("Selecciona una opcion: ");
            opcion = menu.nextInt();

            switch (opcion) {
                case 1 -> {
                    
                    Scanner scanner = new Scanner(System.in);
                    Lab8P1_GabrielMejia game = new Lab8P1_GabrielMejia();

                    System.out.print("numero de rondas: ");
                    int rounds = scanner.nextInt();

                    game.initializeBoard();
                    game.play(rounds);
                        }
                
                case 2 -> {
                    
                        }
                default -> System.out.println("Opcion no valida.");
            }
        }
    }
}
    