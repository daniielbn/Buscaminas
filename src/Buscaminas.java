import java.util.Random;
import java.util.Scanner;
/*
 * Casillas vacías: #
 * Casillas con bombas: ^
 * Casillas sin descubrir: *
 */
/**
 * @author Daniel Brito Negrín
 * @version 1.0
 */

public class Buscaminas {
    private int MINAS = 20;
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
    private final int filas = TABLERO.length;
    private final int columnas = TABLERO[0].length;

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
    private int getMINAS() {
        return this.MINAS;
    }

    /**
     * Método para obtener el tablero.
     * @return Devuelve el tablero del juego.
     */
    private char[][] getTABLERO() {
        return this.TABLERO;
    }

    private int getFilas() {
        return this.filas;
    }

    private int getColumnas() {
        return this.columnas;
    }

    // Setters
    private void setMINAS(int minas) {
        this.MINAS = minas;
    }

    // Métodos
    /**
     * Método que genera un nuevo tablero con las minas.
     * @return Devuelve el tablero con las minas ya generadas.
     */
    private char[][] generarTableroMinas() {
        // Crear un nuevo tablero de minas
        char[][] tableroMinas = new char[getFilas()][getColumnas()];

        // Inicializar todas las casillas como vacías
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                tableroMinas[i][j] = '0';
            }
        }

        // Colocar minas aleatoriamente
        colocarMinasAleatoriamente(tableroMinas);

        // Calcular y colocar los números de minas cercanas
        calcularNumerosMinasCercanas(tableroMinas);

        return tableroMinas;
    }

    private void colocarMinasAleatoriamente(char[][] tableroMinas) {
        Random random = new Random();
        

        for (int i = 0; i < getMINAS(); i++) {
            int x = random.nextInt(getFilas());
            int y = random.nextInt(getColumnas());

            // Asegurarse de no colocar dos minas en la misma posición
            while (tableroMinas[x][y] == '^') {
                x = random.nextInt(getFilas());
                y = random.nextInt(getColumnas());
            }

            tableroMinas[x][y] = '^';
        }
    }

    private void calcularNumerosMinasCercanas(char[][] tableroMinas) {
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                if (tableroMinas[i][j] != '^') {
                    int contadorMinas = contarMinas(tableroMinas, i, j);
                    if (contadorMinas > 0) {
                        tableroMinas[i][j] = (char) (contadorMinas + '0');
                    }
                }
            }
        }
    }
    /**
     * Método para imprimir el tablero de juego.
     * @param tablero Tablero que se imprime.
     */
    private void imprimirTablero(char[][] tablero) {
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
    private int pedirCoordenadaX() {
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
    private int pedirCoordenadaY() {
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
     * Método que pide una cadena de texto.
     * @return Palabra introducida.
     */
    private String pedirString() {
        Scanner in = new Scanner(System.in);
        String palabra = in.nextLine();
        return palabra;
    }

    /**
     * Método que comprueba el tipo de movimiento por cada turno.
     * @return Indica que movimiento se realiza. 
     */
    private boolean tipoMovimiento() {
        System.out.print("Elige el movimiento que quieras jugar: ");
        String opcion = pedirString();
        if (opcion.toLowerCase().equals("marcar")) {
            return true;
        } else if (opcion.toLowerCase().equals("desactivar")) {
            return true;
        } else {
            return false;
        }
    }

    private int contarMinas(char[][] tableroMinas, int coordenadaX, int coordenadaY) {
        int contadorMinas = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaX = coordenadaX + i;
                int nuevaY = coordenadaY + j;

                if (nuevaX >= 0 && nuevaX < getFilas() && nuevaY >= 0 && nuevaY < getColumnas() && tableroMinas[nuevaX][nuevaY] == '^') {
                    contadorMinas++;
                }
            }
        }
        return contadorMinas;
    }

    private void modificarTablero(char[][] tableroMinas, int coordenadaX, int coordenadaY) {
        if (tableroMinas[coordenadaX][coordenadaY] !=  '0' && tableroMinas[coordenadaX][coordenadaY] != '^') {
            this.TABLERO[coordenadaX][coordenadaY] = (char) (contarMinas(tableroMinas, coordenadaX, coordenadaY) + '0');
            desvelarCasillasVacias(tableroMinas, coordenadaX, coordenadaY);
        } else {
            for (int i = 0; i < getFilas(); i++) {
                for (int j = 0; j < getColumnas(); j++) {
                    if (tableroMinas[i][j] == '0') {
                        this.TABLERO[i][j]  = '0';
                    }
                }
            }
        }
    }
    

    private void desvelarCasillasVacias(char[][] tableroMinas, int coordenadaX, int coordenadaY) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaX = coordenadaX + i;
                int nuevaY = coordenadaY + j;
    
                if (nuevaX >= 0 && nuevaX < getFilas() && nuevaY >= 0 && nuevaY < getColumnas() && this.TABLERO[nuevaX][nuevaY] == '*' && tableroMinas[nuevaX][nuevaY] != 'B') {
                    this.TABLERO[nuevaX][nuevaY] = (char) (contarMinas(tableroMinas, coordenadaX, coordenadaY) + '0');
                }
            }
        }
    }

    private void marcarCasilla(char[][] tableroMinas, int coordenadaX, int coordenadaY) {
        if (tableroMinas[coordenadaX][coordenadaY] == '^') {
            this.TABLERO[coordenadaX][coordenadaY] = 'B';
            desvelarCasillasVacias(tableroMinas, coordenadaX, coordenadaY);
            setMINAS(getMINAS() - 1);
            System.out.println("Has desactivado una mina. Le quedan " + getMINAS() + " minas.");
        } else {
            if (this.TABLERO[coordenadaX][coordenadaY] == '*') {
                this.TABLERO[coordenadaX][coordenadaY] = 'M';
            } else if (this.TABLERO[coordenadaX][coordenadaY] == 'M') {
                this.TABLERO[coordenadaX][coordenadaY] = '*';
            }
        }
    }

    /**
     * Método para iniciar el juego.
     */
    public void jugar() {
        boolean jugar = true;
        char[][] tableroMinas = generarTableroMinas();
        System.out.println("===========================================================");
        imprimirTablero(getTABLERO());
        System.out.println("===========================================================");
        do {
            boolean marcar = tipoMovimiento();
                if (marcar) {
                    System.out.println("Has elegido 'Marcar'.");
                    int coordenadaX = pedirCoordenadaX();
                    int coordenadaY = pedirCoordenadaY();
                    marcarCasilla(tableroMinas, coordenadaX, coordenadaY);
                    if (getMINAS() != 0) {
                        System.out.println("===========================================================");
                        imprimirTablero(getTABLERO());
                        System.out.println("===========================================================");
                    } else {
                        System.out.println("Ha ganado!");
                        jugar = verificarFinJuego();
                    }
                } else {
                    System.out.println("Has elegido 'Descubrir'.");
                    int coordenadaX = pedirCoordenadaX();
                    int coordenadaY = pedirCoordenadaY();
                    if (tableroMinas[coordenadaX][coordenadaY] == '^') {
                        System.out.println("Ha explotado una mina. Ha perdido!");
                        jugar = verificarFinJuego(tableroMinas, coordenadaX, coordenadaY);
                    } else {
                        modificarTablero(tableroMinas, coordenadaX, coordenadaY);
                        System.out.println("===========================================================");
                        imprimirTablero(getTABLERO());
                        System.out.println("===========================================================");
                    }
                }
        } while (jugar);
    }

    private boolean verificarFinJuego() {
        if (getMINAS() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean verificarFinJuego(char[][] tableroMinas, int coordenadaX, int coordenadaY) {
        if (tableroMinas[coordenadaX][coordenadaY] == '^') {
            return false;
        } else {
            return true;
        }
    }

}
