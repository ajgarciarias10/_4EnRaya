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
public class MiniMaxPlayer extends Player {

    private  final int PROFUNDIDAD = 4;

    private  final int CONECTA = 3;
    private  final int Filas = 3;

    private  final int COLUMNAS = 3;
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
        /**
         *  Declaramos las variables
         * @ganar1 Valor maximo de fichas jugador 1
         * @ganar2 Valor maximo de fichas jugador 2
         */
        int valor1 = 0;
        int valor2 = 0;

        /**
         * Bucle anidado para comprobar la horizontal
         */
        for (int i = 0; (i < Filas); i++) {
            //Inicializamos al fichas del jugador 1 y 2 a 0
            ganar1 = 0;
            ganar2 = 0;
            for (int j = 0; (j < COLUMNAS); j++) {
               //Miramos si el tablero no esta vacio
                if (tablero[i][j] != Main.VACIO) {
                    //Caso jugador 1
                    /**
                     * Comprobamos si hay fichas en la posicion horizontal:
                     * [i][j] del jugador 1
                     */
                    if (tablero[i][j] == Main.PLAYER1) {
                        ganar1++;
                    //En caso de que no sean del jugador1
                    }else{
                        //Reiniciamos el valor del conteo de fichas
                        ganar1 = 0;

                    }
                    //¿Ha ganado el jugador 1?
                    if(ganar1 >= CONECTA){
                        //Y Devolvemos el ganador
                        return Main.PLAYER1;
                    } else{
                        //Hay que ver si hay posibilidad de poner mas fichas a la izquierda como a la derecha
                        int diferenciaconectaFicha1 = CONECTA - ganar1;
                        //Miramos por la izquierda
                        //Volvemos al inicio
                        if(i-diferenciaconectaFicha1 >= 0){
                            int a = 0,b = 0;
                            /**
                             * Bucle que lo utilizo para volver a la posicon inciial
                             */
                            for (int k = 1; k < ganar1; k--) {
                                a = i-k;
                                b = j;
                            }
                            /**
                             * Tras llegar a la posicion incial comprobamos si para la izquierda hay fichas para poder seguir
                             */
                            //Variable para ver si es posible ver comparandolo con la diferenciadeconecta con la ficha 1 llegar a hacer el conecta
                            int contadorIzquierda = 0 ;
                            for (int l = 0; l < diferenciaconectaFicha1; l--) {
                                if(tablero[a-l][b] == Main.PLAYER1 || tablero[a-l][j] == Main.VACIO){
                                    contadorIzquierda++;
                                }

                            }
                            if(contadorIzquierda == diferenciaconectaFicha1){
                                if(ganar1 > valor1)valor1 = ganar1;
                            }
                        }
                        if(i+diferenciaconectaFicha1 <= Filas){
                            int contadorDerecha = 0 ;
                            //Miramos por la derecha
                            for (int k = 0; k < diferenciaconectaFicha1; k++) {
                                if(tablero[i+k][j] == Main.PLAYER1 || tablero[i+k][j] == Main.VACIO){
                                    contadorDerecha++;
                                }
                            }
                            if(contadorDerecha == diferenciaconectaFicha1){
                                if(ganar1 > valor1)valor1 = ganar1;
                            }
                        }

                    }
                    //En caso de que no haya ganado el jugador1 miramos el jugador 2
                    /**
                     * Comprobamos si hay fichas en la posicion horizontal:
                     * [i][j] del jugador 2
                     */
                    if(tablero[i][j] == Main.PLAYER2){
                        ganar2++;
                    }
                    //En caso de que no sean del jugador2
                    else{
                        ganar2 = 0;
                    }
                    //¿Ha ganado el jugador 2?
                    if(ganar2 >= CONECTA){
                        //Y Devolvemos el ganador
                        return Main.PLAYER2;
                    }else{
                        //Hay que ver si hay posibilidad de poner mas fichas a la izquierda como a la derecha
                        int diferenciaconectaFicha2 = CONECTA - ganar2;
                        //Miramos por la izquierda
                        //Volvemos al inicio
                        if(i-diferenciaconectaFicha2 >= 0){
                            int a = 0,b = 0;
                            /**
                             * Bucle que lo utilizo para volver a la posicon inciial
                             */
                            for (int k = 1; k < ganar2; k--) {
                                a = i-k;
                                b = j;
                            }
                            /**
                             * Tras llegar a la posicion incial comprobamos si para la izquierda hay fichas para poder seguir
                             */
                            //Variable para ver si es posible ver comparandolo con la diferenciadeconecta con la ficha 1 llegar a hacer el conecta
                            int contadorIzquierda = 0 ;
                            for (int l = 0; l < diferenciaconectaFicha2; l--) {
                                if(tablero[a-l][b] == Main.PLAYER2 || tablero[i-l][b] == Main.VACIO){
                                    contadorIzquierda++;
                                }

                            }
                            if(contadorIzquierda == diferenciaconectaFicha2){
                                if(ganar2 > valor2)valor2 = ganar2;
                            }
                        }
                        if(i+diferenciaconectaFicha2 <= Filas){
                            int contadorDerecha = 0 ;
                            //Miramos por la derecha
                            for (int k = 0; k < diferenciaconectaFicha2; k++) {
                                if(tablero[i+k][j] == Main.PLAYER2 || tablero[i+k][j] == Main.VACIO){
                                    contadorDerecha++;
                                }
                            }
                            if(contadorDerecha == diferenciaconectaFicha2){
                                if(ganar2 > valor2)valor2 = ganar2;
                            }
                        }
                    }
                } //Si lo está
                else {
                    //Reseteamos ganar
                    ganar1 = 0;
                    ganar2 = 0;
                }
            }
        }
        /**
         * Bucle anidado para comprobar la Vertical
         */
        for (int i = 0; (i < COLUMNAS) ; i++) {
            ganar1 = 0;
            ganar2 = 0;
            for (int j = 0; (j < Filas) ; j++) {
                //Miramos si el tablero no esta vacio
                if (tablero[j][i] != Main.VACIO) {
                    //Caso jugador 1
                    /**
                     * Comprobamos si hay fichas en la posicion horizontal:
                     * [i][j] del jugador 1
                     */
                    if (tablero[j][i] == Main.PLAYER1) {
                        ganar1++;
                        //En caso de que no sean del jugador1
                    }else{
                        //Reiniciamos el valor del conteo de fichas
                        ganar1 = 0;

                    }
                    //¿Ha ganado el jugador 1?
                    if(ganar1 >= CONECTA){
                        //Y Devolvemos el ganador
                        return Main.PLAYER1;
                    } else{
                        //Hay que ver si hay posibilidad de poner mas fichas a la izquierda como a la derecha
                        int diferenciaconectaFicha1 = CONECTA - ganar1;
                        //Miramos hacia abajo
                        //Volvemos al inicio
                        if(j-diferenciaconectaFicha1 >= 0){
                            int a = 0,b = 0;
                            /**
                             * Bucle que lo utilizo para volver a la posicon inciial
                             */
                            for (int k = 1; k < ganar1; k--) {
                                a = i;
                                b = j-k;
                            }
                            /**
                             * Tras llegar a la posicion incial comprobamos si para la izquierda hay fichas para poder seguir
                             */
                            //Variable para ver si es posible ver comparandolo con la diferenciadeconecta con la ficha 1 llegar a hacer el conecta
                            int contadorAbajo = 0 ;
                            for (int l = 0; l < diferenciaconectaFicha1; l--) {
                                if(tablero[a][b-l] == Main.PLAYER1 || tablero[i][b-l] == Main.VACIO){
                                    contadorAbajo++;
                                }

                            }
                            if(contadorAbajo == diferenciaconectaFicha1){
                                if(ganar1 > valor1)valor1 = ganar1;
                            }
                        }
                        if(j+diferenciaconectaFicha1 <= Filas){
                            int contadorArriba = 0 ;
                            //Miramos por  arriba
                            for (int k = 0; k < diferenciaconectaFicha1; k++) {
                                if(tablero[i][j+k] == Main.PLAYER1 || tablero[i][j+k] == Main.VACIO){
                                    contadorArriba++;
                                }
                            }
                            if(contadorArriba == diferenciaconectaFicha1){
                                if(ganar1 > valor1)valor1 = ganar1;
                            }
                        }

                    }
                    //En caso de que no haya ganado el jugador1 miramos el jugador 2
                    /**
                     * Comprobamos si hay fichas en la posicion horizontal:
                     * [i][j] del jugador 2
                     */
                    if(tablero[i][j] == Main.PLAYER2){
                        ganar2++;
                    }
                    //En caso de que no sean del jugador2
                    else{
                        ganar2 = 0;
                    }
                    //¿Ha ganado el jugador 2?
                    if(ganar2 >= CONECTA){
                        //Y Devolvemos el ganador
                        return Main.PLAYER2;
                    }else{
                        //Hay que ver si hay posibilidad de poner mas fichas a la izquierda como a la derecha
                        int diferenciaconectaFicha2 = CONECTA - ganar2;
                        //Miramos por la izquierda
                        //Volvemos al inicio
                        if(i-diferenciaconectaFicha2 >= 0){
                            int a = 0,b = 0;
                            /**
                             * Bucle que lo utilizo para volver a la posicon inciial
                             */
                            for (int k = 1; k < ganar2; k--) {
                                a = i;
                                b = j-k;
                            }
                            /**
                             * Tras llegar a la posicion incial comprobamos si para la izquierda hay fichas para poder seguir
                             */
                            //Variable para ver si es posible ver comparandolo con la diferenciadeconecta con la ficha 1 llegar a hacer el conecta
                            int contadorAbajo = 0 ;
                            for (int l = 0; l < diferenciaconectaFicha2; l--) {
                                if(tablero[a][b-l] == Main.PLAYER2 || tablero[i][b-l] == Main.VACIO){
                                    contadorAbajo++;
                                }

                            }
                            if(contadorAbajo == diferenciaconectaFicha2){
                                if(ganar2 > valor2)valor2 = ganar2;
                            }
                        }
                        if(j+diferenciaconectaFicha2 <= Filas){
                            int contadorArriba = 0 ;
                            //Miramos por  arriba
                            for (int k = 0; k < diferenciaconectaFicha2; k++) {
                                if(tablero[i][j+k] == Main.PLAYER1 || tablero[i][j+k] == Main.VACIO){
                                    contadorArriba++;
                                }
                            }
                            if(contadorArriba == diferenciaconectaFicha2){
                                if(ganar2 > valor2)valor2 = ganar2;
                            }
                        }
                    }



                } else {
                    ganar1 = 0;
                    ganar2 = 0;
                }
            }
        }

return 0;
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
            imprimirNodo(hijos.get(numHijos - 1), espacio + "    ");
    }
    }


}