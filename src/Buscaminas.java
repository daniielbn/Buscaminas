import java.util.Random;
import java.util.Scanner;

/**
 * Despejar todas las casillas que no oculten una mina.
 * Algunas casillas tienen un número, el cuál indica la cantidad de minas que hay en las casillas circundantes.
 * - Si en una casilla aparece el número 2, significa que en las 8 casillas de alrededor hay 3 minas y 5 sin minas. 
 * - Si se descubre una casilla sin número indica que ninguna de las casillas vecinas tiene mina y estás se descubren automáticamente.
 * - Si se toca una mina se acaba el juego.
 * - Se pueden marcar casillas donde crees que hay una mina.
 */

public class Buscaminas {
    // Atributos
    private final int MINAS = 30;
    private char[][] TABLERO = {{'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                            };

    // Constructores
    public Buscaminas() {

    }

    public Buscaminas(char[][] tablero) {
        this.TABLERO =  tablero;
    }

    // Getters
    public int getMINAS() {
        return this.MINAS;
    }

    public char[][] getTABLERO() {
        return this.TABLERO;
    }

    // Setters
    public void setTABLERO(char[][] tablero) {
        this.TABLERO = tablero;
    }

    // Métodos
    public char[][] generarTableroMinas() {
        char[][] tablero = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                tablero[i][j] = '*';
            }
        }
        Random random = new Random();
        int aleatorioI;
        int aleatorioJ;
        for (int i = 0; i < this.MINAS; i++) {
            aleatorioI = random.nextInt(20);
            aleatorioJ = random.nextInt(20);
            if (tablero[aleatorioI][aleatorioJ] == '*') {
                tablero[aleatorioI][aleatorioJ] = '^';
            } else {
                while (tablero[aleatorioI][aleatorioJ] != '*') {
                    aleatorioI = random.nextInt(20);
                    aleatorioJ = random.nextInt(20);
                }
            }
        }
        return tablero;
    }

    public void imprimirTablero(char[][] tablero) {
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                System.out.print(tablero[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public int pedirCoordenadaX() {
        Scanner in = new Scanner(System.in);
        System.out.print("Introduce coordenada X [1-20]: ");
        int coordenadaX = in.nextInt() - 1;
        while(coordenadaX < 0 || coordenadaX > 20) {
            System.out.print("Ha introducido una coordenada errónea. Porfavor, vuelva a intentarlo:");
            coordenadaX = in.nextInt();
        }
        return coordenadaX;
    }

    public int pedirCoordenadaY() {
        Scanner in = new Scanner(System.in);
        System.out.print("Introduce coordenada Y [1-20]: ");
        int coordenadaY = in.nextInt() - 1;
        while(coordenadaY < 0 || coordenadaY > 20) {
            System.out.print("Ha introducido una coordenada errónea. Porfavor, vuelva a intentarlo:");
            coordenadaY = in.nextInt();
        }
        return coordenadaY;
    }

    public boolean comprobarMinas(int coordenadaX, int coordenadaY, char[][] tableroMinas) {
        if (tableroMinas[coordenadaX][coordenadaY] == '^') {
            return true;
        } else {
            return false;
        }
    }

    public String pedirString() {
        Scanner in = new Scanner(System.in);
        String palabra = in.nextLine();
        return palabra;
    }

    public boolean tipoMovimiento() {
        System.out.print("Eligue el movimiento que quieras jugar: ");
        String opcion = pedirString();
        if (opcion.toLowerCase().equals("desactivar")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     *  *  *  * || x-1,y+1    x,y+1   x+1,y+1  || (x,y) x,y+1   ||  x-1,y x-1, y+1 || x,y-1 (x,y)    || x-1,y-1 x-1,y  || x,y-1 (x,y) x,y+1     || x-1,y x-1,y+1
     *  * (*) * || x-1,y      (x,y)     x+1,y  || x+1,y x+1,y-1 || (x,y)  x,y+1   || x+1,y-1 x+1,y  || x,y-1   (x,y)   || x+1,y-1 x+1,y x+1,y+1 || (x,y) x,y+1
     *  *  *  * || x-1,y-1    x,y-1   x+1, y-1 ||               ||                ||                ||                 ||                       || x+1,y x+1,y+1
     */
    public void comprobarMinasAdyacentes(char[][] tableroMinas, int coordenadaX, int coordenadaY) {
        if (tableroMinas[coordenadaX][coordenadaY] == '*') {
            int contadorMinas = 0;
            if (coordenadaX == 0 && coordenadaY == 0) {
                if (tableroMinas[coordenadaX + 1][coordenadaY] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX + 1][coordenadaY - 1] == '^') {
                    contadorMinas++;
                }
            } else if (coordenadaX == 0 && coordenadaY == 20) {
                if (tableroMinas[coordenadaX][coordenadaY - 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX - 1][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX ][coordenadaY + 1] == '^') {
                    contadorMinas++;
                }
            } else if (coordenadaX == 20 && coordenadaY == 0) {
                if (tableroMinas[coordenadaX - 1][coordenadaY] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX - 1][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX][coordenadaY + 1] == '^') {
                    contadorMinas++;
                }
            } else if (coordenadaX == 20 && coordenadaY == 20) {
                if (tableroMinas[coordenadaX - 1][coordenadaY - 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX - 1][coordenadaY] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX][coordenadaY - 1] == '^') {
                    contadorMinas++;
                }
            } else if (coordenadaX == 0 && coordenadaY != 0) {
                if (tableroMinas[coordenadaX + 1][coordenadaY - 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX + 1][coordenadaY] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX + 1][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX][coordenadaY - 1] == '^') {
                    contadorMinas++;
                }
            } else if (coordenadaX != 0 && coordenadaY == 0) {
                if (tableroMinas[coordenadaX + 1][coordenadaY] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX - 1][coordenadaY] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX + 1][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX - 1][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX + 1][coordenadaY] == '^') {
                    contadorMinas++;
                }
            } else {
                if (tableroMinas[coordenadaX - 1][coordenadaY - 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX - 1][coordenadaY] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX - 1][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX][coordenadaY - 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX + 1][coordenadaY + 1] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX + 1][coordenadaY] == '^') {
                    contadorMinas++;
                } else if (tableroMinas[coordenadaX + 1][coordenadaY - 1] == '^') {
                    contadorMinas++;
                }
            }
            char letra = (char) contadorMinas;
            this.TABLERO[coordenadaX][coordenadaY] = letra;
            imprimirTablero(getTABLERO());
        }
    }

    public void jugar() {
        boolean desactivar;
        boolean jugar = true;
        char[][] tableroMinas = generarTableroMinas();
        do {
            imprimirTablero(getTABLERO());
            System.out.println("==================================================");
            imprimirTablero(tableroMinas);
            System.out.println("==================================================");
            desactivar = tipoMovimiento();
            if (desactivar) {
                System.out.println("Has elegido 'Desactivar'."); 
                int coordenadaX = pedirCoordenadaX();
                int coordenadaY = pedirCoordenadaY();
            } else {
                System.out.println("Has elegido 'Descubrir'.");
                int coordenadaX = pedirCoordenadaX();
                int coordenadaY = pedirCoordenadaY();
                if (comprobarMinas(coordenadaX, coordenadaY, tableroMinas)) {
                    System.out.println("Has explotado una mina. Perdiste!");
                    jugar = false;
                    break;
                } else {
                    comprobarMinasAdyacentes(tableroMinas, coordenadaX, coordenadaY);
                    imprimirTablero(getTABLERO());
                }
            }
        } while (!jugar);
    }

}
