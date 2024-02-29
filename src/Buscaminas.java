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

/**
 * @author Daniel Brito Negrín
 * @version 1.0
 */

public class Buscaminas {
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

    /**
     * Método constructor por defecto.
     */
    public Buscaminas() {

    }

    /**
     * Método constructor parametrizado.
     * @param tablero Tablero de juego.
     */
    public Buscaminas(char[][] tablero) {
        this.TABLERO =  tablero;
    }

    // Getters
    /**
     * Método para obtener el número de minas;
     * @return Devuelve el número de minas.
     */
    public int getMINAS() {
        return this.MINAS;
    }

    /**
     * Método para obtener el tablero.
     * @return Devuelve el tablero del juego.
     */
    public char[][] getTABLERO() {
        return this.TABLERO;
    }

    // Setters
    /**
     * Método para modificar el tablero.
     * @param tablero Nuevo tablero del juego.
     */
    public void setTABLERO(char[][] tablero) {
        this.TABLERO = tablero;
    }

    // Métodos
    /**
     * Método que genera un nuevo tablero con las minas.
     * @return Devuelve el tablero con las minas ya generadas.
     */
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

    /**
     * Método para imprimir el tablero de juego.
     * @param tablero Tablero que se imprime.
     */
    public void imprimirTablero(char[][] tablero) {
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                System.out.print(tablero[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    /**
     * Método que pide una coordenada (X), comprobando que esté en el rango [1,20].
     * @return Devuelve la coordenada (X).
     */
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

    /**
     * Método que pide una coordenada (Y), comprobando que esté en el rango [1,20].
     * @return Devuelve la coordenada (Y).
     */
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

    /**
     * Método que comprueba si el punto introducido por el usuario es una mina o no.
     * @param coordenadaX Coordenada X introducida por el usuario.
     * @param coordenadaY Coordenada Y introducida por el usuario.
     * @param tableroMinas Tablero donde están las minas.
     * @return Indica si es una mina o no.
     */
    public boolean comprobarMinas(int coordenadaX, int coordenadaY, char[][] tableroMinas) {
        if (tableroMinas[coordenadaX][coordenadaY] == '^') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que pide una cadena de texto.
     * @return Palabra introducida.
     */
    public String pedirString() {
        Scanner in = new Scanner(System.in);
        String palabra = in.nextLine();
        return palabra;
    }

    /**
     * Método que comprueba el tipo de movimiento por cada turno.
     * @return Indica que movimiento se realiza. 
     */
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
    /**
     * Método que comprueba las casillas de los alrededores de la escogida por el usuario, contando así la cantidad de minas adyacentes a cada casilla.
     * @param tableroMinas Tablero donde estén las minas.
     * @param coordenadaX Coordenada X introducida por el usuario.
     * @param coordenadaY Coordenada Y introducida por el usuario.
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

    /**
     * Método para iniciar el juego.
     */
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
