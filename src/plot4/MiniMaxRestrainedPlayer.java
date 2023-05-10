/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plot4;
import java.util.ArrayList;

/**
 *
 * @author José María Serrano
 * @version 1.7 Departamento de Informática. Universidad de Jáen
 * Última revisión: 2023-03-30
 *
 * Inteligencia Artificial. 2º Curso. Grado en Ingeniería Informática
 *
 * Clase MiniMaxPlayer para representar al jugador CPU que usa una técnica de IA
 *
 * Esta clase es en la que tenemos que implementar y completar el algoritmo
 * MiniMax
 *
 */
public class MiniMaxRestrainedPlayer extends Player {

    private  final int PROFUNDIDAD = 4;

    private  final int CONECTA = 4;
    private  final int Filas = 6;

    private  final int COLUMNAS = 7;
    /**
     *  Funcion heuristica
     * @param tablero
     * @return
     */
    public  int Heuristica(int tablero[][]){


        /**
         *  Declaramos las variables
         * @ganar1 variable para jugador 1 utilizado en bucle para obtener fichas jugador 1
         * @ganar2 para el jugador 2
         */
        int  ganar1;
        int  ganar2;
        int vacio = 0;
        /**
         *  Declaramos las variables
         * @ganar1 Valor maximo de fichas jugador 1
         * @ganar2 Valor maximo de fichas jugador 2
         */
        int valor1 = 0;
        int valor2 = 0;


        /**
         * Comprobar horizontal
         */

        for (int i = 0; (i < Filas); i++) {
            vacio=0;
            ganar1 = 0;
            ganar2 = 0;
            for (int j = 0; (j < COLUMNAS); j++) {
                //Si el numero de fila es menor o igual que el conecta
                //Vemos si es posible alcanzar el conecta 4 en lo que queda
                if(COLUMNAS - j < CONECTA){
                    int casillasRestantes = COLUMNAS-j;
                    if(tablero[i][j] == Main.VACIO){
                        vacio++;
                        if(vacio + ganar1 >=  CONECTA){
                            if(ganar1>valor1){
                                valor1 = ganar1;
                            }

                        }
                        if(vacio + ganar2 >= CONECTA){
                            if(ganar2>valor2){
                                valor2 = ganar2;
                            }
                        }
                    }else{
                        //Miramos las siguientes fichas
                        if(deQueJugadorEs(tablero,i,j,false)){
                            int valor = mirarsiguientesFichas(vacio,casillasRestantes,tablero,i,j,ganar1,true,"H");
                            if(valor == 1){
                                return Main.PLAYER1;
                            } else if (valor >1) {
                                if(valor > valor1){
                                    valor1 = valor;
                                }
                            } else if (valor == 0) {
                                break;
                            }

                        }else{
                            int valor = mirarsiguientesFichas(vacio,casillasRestantes,tablero,i,j,ganar2,false,"H");
                            if(valor == -1){
                                return Main.PLAYER2;
                            } else if (valor >0) {
                                if(valor > valor2){
                                    valor2 = valor;
                                }
                            } else if (valor == 0) {
                                break;
                            }
                        }
                    }


                }
                else{

                    //Si el tablero no esta vacio
                    if (tablero[i][j] != Main.VACIO) {
                        //Comprobamos que jugador es
                        if(deQueJugadorEs(tablero,i,j,false)){
                            //Si es 1 sumamos una pieza
                            ganar1++;
                            ganar2 =0 ;
                            //Comprobamos si con el vacio puede ganar
                            if(ganar1 + vacio >= CONECTA && ganar1>valor1) {
                                valor1 = ganar1;
                            }
                            //Comprobamos si ha ganado ya el jugador
                            if(jugadorHaGanado(ganar1)){
                                return  Main.PLAYER1;
                            }
                        }else{
                            ganar2++;
                            ganar1 = 0;
                            //Comprobamos si con el vacio puede ganar
                            if(ganar2 + vacio >= CONECTA && ganar2>valor2) {
                                valor2 = ganar2;
                            }
                            //Comprobamos si ha ganado ya el jugador
                            if(jugadorHaGanado(ganar2)){
                                return  Main.PLAYER2;
                            }
                        }

                    } else {
                        vacio++;
                    }
                }
            }
        }

        /**
         * Comprobar Vertical
         */

        for (int i = 0; (i < COLUMNAS); i++) {
            vacio=0;
            ganar1 = 0;
            ganar2 = 0;
            for (int j = 0; (j < Filas); j++) {
                //Si el numero de fila es menor o igual que el conecta
                //Vemos si es posible alcanzar el conecta 4 en lo que queda
                if(Filas - j < CONECTA){
                    int casillasRestantes = Filas-j;
                    if(tablero[j][i] == Main.VACIO){
                        vacio++;
                        if(vacio + ganar1 >=  CONECTA){
                            if(ganar1>valor1){
                                valor1 = ganar1;
                            }

                        }
                        if(vacio + ganar2 >= CONECTA){
                            if(ganar2>valor2){
                                valor2 = ganar2;
                            }
                        }
                    }else{
                        //Miramos las siguientes fichas
                        if(deQueJugadorEs(tablero,i,j,true)){
                            int valor = mirarsiguientesFichas(vacio,casillasRestantes,tablero,i,j,ganar1,true,"V");
                            if(valor == 1){
                                return Main.PLAYER1;
                            } else if (valor >1) {
                                if(valor > valor1){
                                    valor1 = valor;
                                }
                            } else if (valor == 0) {
                                break;
                            }

                        }else{
                            int valor = mirarsiguientesFichas(vacio,casillasRestantes,tablero,i,j,ganar2,false,"V");
                            if(valor == -1){
                                return Main.PLAYER2;
                            } else if (valor >0) {
                                if(valor > valor2){
                                    valor2 = valor;
                                }
                            } else if (valor == 0) {
                                break;
                            }
                        }
                    }


                }
                else{
                    //Si el tablero no esta vacio
                    if (tablero[j][i] != Main.VACIO) {
                        //Comprobamos que jugador es
                        if(deQueJugadorEs(tablero,i,j,false)){
                            //Si es 1 sumamos una pieza
                            ganar1++;
                            ganar2 =0 ;
                            //Comprobamos si con el vacio puede ganar
                            if(ganar1 + vacio >= CONECTA && ganar1>valor1) {
                                valor1 = ganar1;
                            }
                            //Comprobamos si ha ganado ya el jugador
                            if(jugadorHaGanado(ganar1)){
                                return  Main.PLAYER1;
                            }
                        }else{
                            ganar2++;
                            ganar1 = 0;
                            //Comprobamos si con el vacio puede ganar
                            if(ganar2 + vacio >= CONECTA && ganar2>valor2) {
                                valor2 = ganar2;
                            }
                            //Comprobamos si ha ganado ya el jugador
                            if(jugadorHaGanado(ganar2)){
                                return  Main.PLAYER2;
                            }
                        }

                    } else {
                        vacio++;
                    }
                }
            }
        }


        return  determinarGanador(valor1,valor2);

    }

    private int determinarGanador(int valor1,int valor2) {
        int heuristica = valor1-valor2;
        if(heuristica>0){
            return  1;
        }else if(heuristica<0){
            return  -1;
        }
        return 0;
    }

    /**
     * Metodo que mira desde la posicion Filas -j <= Conecta es decir
     * Si en un 5x6  estuvieramos en j = 1 mirariamos las siguientes filas desde esa fila
     * Devolvera true si se se logra conecta con la ficha del color en j=1
     * Y en caso contrario false
     * @return
     */
    private int mirarsiguientesFichas(int vacio, int casillasRestantes,int[][] tablero, int i , int j, int ganar, boolean jugador,String modo) {
        if(modo.equals("H")) {
            //region Modo horizontal
            //region Caso Jugador 1
            if (jugador) {
                //Realizamos un bucle hasta que se acaben las fichas restantes
                for (int k = 0; k < casillasRestantes; k++) {
                    //Miramos de que jugador es
                    if (deQueJugadorEs(tablero, i, j + k, false)) {
                        ganar++;
                        if (jugadorHaGanado(ganar)) {
                            return Main.PLAYER1;
                        }
                        if (vacio + ganar >= CONECTA) {
                            return ganar;
                        }
                    } else {
                        return 0;
                    }
                }
            } else {
                for (int k = 0; k < casillasRestantes; k++) {
                    //Miramos de que jugador es
                    int columna = j + k;
                    if (!deQueJugadorEs(tablero, i, columna, false)) {
                        ganar++;
                        if (jugadorHaGanado(ganar)) {
                            return Main.PLAYER2;
                        }
                        if (vacio + ganar >= CONECTA) {
                            return ganar;
                        }
                    } else {
                        return 0;
                    }

                }
            }
        } else if (modo.equals("V")) {
            if (jugador) {
                //Realizamos un bucle hasta que se acaben las fichas restantes
                for (int k = 0; k < casillasRestantes; k++) {
                    //Miramos de que jugador es
                    if (deQueJugadorEs(tablero, i, j + k, true)) {
                        ganar++;
                        if (jugadorHaGanado(ganar)) {
                            return Main.PLAYER1;
                        }
                        if (vacio + ganar >= CONECTA) {
                            return ganar;
                        }
                    } else {
                        return 0;
                    }
                }
            } else {
                for (int k = 0; k < casillasRestantes; k++) {
                    //Miramos de que jugador es
                    int columna = j + k;
                    if (!deQueJugadorEs(tablero, i, columna, true)) {
                        ganar++;
                        if (jugadorHaGanado(ganar)) {
                            return Main.PLAYER2;
                        }
                        if (vacio + ganar >= CONECTA) {
                            return ganar;
                        }
                    } else {
                        return 0;
                    }

                }
            }
        }
        //endregion
        //endregion
        return  0;

    }

    /**
     * Metodo que verifica a partir numerodeFichas de un jugador si ha ganado o no
     * @param numerodeFichas
     * @return
     */
    private boolean jugadorHaGanado(int numerodeFichas) {
        boolean valor;
        if(numerodeFichas == CONECTA){
            valor = true;
        }
        else{
            valor = false;
        }
        return valor;
    }

    /**
     * Metodo que verifica a partir del tablero de que color es la ficha que hay
     * @param tablero
     * @param  i
     * @param j
     * @param  vertical
     * @return
     */

    private boolean deQueJugadorEs(int[][] tablero, int i , int j, boolean vertical) {
        //Modo vertical
        if(vertical) {
            if (tablero[j][i] == Main.PLAYER1) {
                return true;
            } else {
                return false;
            }
        }//Modo horizontal y oblicuo
        else {
            if (tablero[i][j] == Main.PLAYER1) {
                return  true;
            } else {
                return false;
            }
        }
    }


    /**
     * @brief funcion que determina donde colocar la ficha este turno
     * @param tablero Tablero de juego
     * @param conecta Número de fichas consecutivas adyacentes necesarias para
     * ganar
     * @return Devuelve si ha ganado algun jugador
     */
    @Override
    public int turno(Grid tablero, int conecta) {

        ///Creamos el nodo padre,con el nodo con el tablero actual
        Nodo nodoPadre = new Nodo(tablero);
        //Llamamos al metodo  minimax para que nos genere el arbol al ser la maquina min pasamos jugador min
        Minimax(nodoPadre,-1, PROFUNDIDAD);
        //Una vez creado el arbol, aplicamos nuestro metodo de evaluación
        //Para ir pasando los valores obtenidos hacia atras hasta llegar al nodo raiz (nodo min)
        evaluar(nodoPadre,false);
        //imprimirNodo(nodoPadre, ""); // Función que dibuja el arbol al completo
        //Sacamos todos los hijos del arraylist que tenemos
        ArrayList<Nodo> hijos = nodoPadre.getHijos();
        //Incializamos un nodo que va a ser el movimiento
        Nodo movimiento = null;
        for (Nodo hijo : hijos) {
            //Recoremos la lista de hijos
            if (nodoPadre.getValor() == hijo.getValor()) {
                //Obtenemos el movimiento
                movimiento = hijo;
            }
        }

        //Devolvemos la posicion del movimiento
        return encontrarMovimiento(nodoPadre.getTablero().getGrid(), movimiento.getTablero().getGrid());

    } // turno

    /**
     * Metodo que genera el arbol minimax.
     * @param nodo
     * @param jugador
     */
    public void Minimax(Nodo nodo, int jugador, int profundidad) {
        // Obtener el tablero del nodo actual
        Grid tableroactual = nodo.getTablero();
        // Comprobar si el juego  ha llegado a un estado final
        //Vemos si el estado final  es  o -1(gana min) o 1(gana max)
        if (profundidad == 0) {
            int estadoganador = Heuristica(tableroactual.getGrid());
            // Asignar un valor de utilidad en función del resultado del juego
            nodo.setValor(estadoganador == 1 ? 1 : -1);
            //Nos salimos de la funcion min max
            return;
        }
        //Si el jugador es min
        if (jugador == -1) {
            //Aplicamos la recursividad para ir creando un arbol
            recursividad(nodo,jugador,tableroactual, profundidad - 1);
            //Si el jugador es Max
        } else {
            //Aplicamos la recursividad para ir creando un arbol
            recursividad(nodo,jugador,tableroactual, profundidad -1);
        }
    }

    /**
     * Metodo que por cada columna libre, crea hijos en el arbol.
     * @param nodopadre
     * @param jugador
     * @param tableroactual
     */
    private void recursividad(Nodo nodopadre, int jugador,Grid tableroactual, int profundidad) {
        //Bucle que recorre todas las COLUMNAS
        for (int col = 0; col < tableroactual.getColumnas(); col++) {
            // Comprobamos si la columna está llena
            if (tableroactual.fullColumn(col)) {
                continue;
            }
            //Almacenamos un tableroHijo a partir del tablero actual
            Grid tableroHijo = new Grid(tableroactual);
            //Utilizamos el metodo set para que coloque la columna libre en el tablero
            tableroHijo.set(col,jugador);
            //Creamos el nodo Hijo
            Nodo nodoHijo = new Nodo(tableroHijo);
            //Lo añadimos a lista de hijo
            nodopadre.addHijos(nodoHijo);
            //Si el jugador es min
            if(jugador==-1){
                //ahora pasa a ser max
                Minimax(nodoHijo, 1, profundidad);
            }
            //Si el jugado es max
            else{
                //ahora pasa a ser min
                Minimax(nodoHijo, -1, profundidad);
            }

        }

    }

    /**
     * Metodo para en funcion de donde te encuentres en el arbol
     * Max o min pues pasamos el valor de los hijos a los padres
     * @param nodo
     * @param esMax
     *
     */
    public void evaluar(Nodo nodo, boolean esMax) {
        // Si el nodo es una hoja, asignar su valor de utilidad
        if (nodo.getHijos().size() == 0) {
            return;
        }

        // Recorrer los nodos hijos del nodo actual
        for (Nodo hijo : nodo.getHijos()) {
            evaluar(hijo, !esMax);
        }

        // Si el nivel actual es de maximización
        if (esMax) {
            int mejorValor = Integer.MIN_VALUE;

            for (Nodo hijo : nodo.getHijos()) {
                int valorHijo = hijo.getValor();
                mejorValor = Math.max(mejorValor, valorHijo);
            }

            // Asignar el mejor valor obtenido al nodo actual
            nodo.setValor(mejorValor);
        } else { // Si el nivel actual es de minimización
            int mejorValor = Integer.MAX_VALUE;

            for (Nodo hijo : nodo.getHijos()) {
                int valorHijo = hijo.getValor();
                mejorValor = Math.min(mejorValor, valorHijo);
            }

            // Asignar el mejor valor obtenido al nodo actual
            nodo.setValor(mejorValor);
        }
    }

    /**
     * Metodo que obtenemos todos los hijos hasta llegar al nodo hoja
     * @param nodo
     * @param esMax
     */
    private void llegarANodoHoja(Nodo nodo,boolean esMax) {


    }

    /**
     * Metodo encontrar movimiento le pasamos el tablero padre y el tablero hijo y obtenemos el movimiento
     * @param tableropadre
     * @param tablerohijo
     * @return
     */
    public static int encontrarMovimiento(int[][] tableropadre, int[][] tablerohijo) {
        int Filas = tableropadre.length;
        int COLUMNAS = tableropadre[0].length;
        for (int i = 0; i < Filas; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tableropadre[i][j] != tablerohijo[i][j]) {
                    return j; // devolver posición de la celda diferente
                }
            }
        }
        return 0; // si no hay celdas diferentes, devolver null
    }

    public class Nodo {

        private Grid tablero;
        private int valor;
        private ArrayList<Nodo> hijos;


        public Nodo(Grid tablero) {
            this.tablero = tablero;
            this.valor = 0;
            this.hijos = new ArrayList<>();
        }
        public Grid getTablero() {
            return tablero;
        }

        public int getValor() {
            return valor;
        }

        public ArrayList<Nodo> getHijos() {
            return hijos;
        }


        public void setValor(int valor) {
            this.valor = valor;
        }

        public void addHijos(Nodo hijo){
            this.hijos.add(hijo);
        }
    }

    /**
     * Funcion que devuleve una String con el tablero en horizontal
     * @param Tablero
     * @return
     */
    public String pintaTableroHorizontal(Grid Tablero) {
        String tablero = "[";
        for (int i = 0; i < Tablero.filas; i++) {
            for (int j = 0; j < Tablero.columnas; j++) {
                tablero += Tablero.getGrid()[i][j] + ",";
            }
        }
        tablero += "]";
        return tablero;
    }

    /**
     * Función que dibuja el arbol minimax al completo
     * @param nodo
     * @param espacio
     */
    private void imprimirNodo(Nodo nodo, String espacio) {
        String tablero = pintaTableroHorizontal(nodo.getTablero()); // Obtenemos el tablero en horizontal

        System.out.println(espacio + "├─> Tablero: " + tablero + " Valor: " + nodo.getValor());

        ArrayList<Nodo> hijos = nodo.getHijos();
        int numHijos = hijos.size();

        for (int i = 0; i < numHijos - 1; i++) {
            imprimirNodo(hijos.get(i), espacio + "│   ");
        }

        if (numHijos > 0) {
            imprimirNodo(hijos.get(numHijos - 1), espacio + "    ");
        }
    }


}