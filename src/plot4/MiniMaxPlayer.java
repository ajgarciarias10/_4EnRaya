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
        Minimax(nodoPadre,-1);
        //Una vez creado el arbol, aplicamos nuestro metodo de evaluación
        //Para ir pasando los valores obtenidos hacia atras hasta llegar al nodo raiz (nodo min)
        evaluar(nodoPadre,false);
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
    public void Minimax(Nodo nodo, int jugador) {
        // Obtener el tablero del nodo actual
        Grid tableroactual = nodo.getTablero();
        // Comprobar si el juego  ha llegado a un estado final
        int estadoganador = tableroactual.checkWin();
        //Vemos si el estado final  es  o -1(gana min) o 1(gana max)
        if (estadoganador != 0) {
            // Asignar un valor de utilidad en función del resultado del juego
            nodo.setValor(estadoganador == 1 ? 1 : -1);
            //Nos salimos de la funcion min max
            return;
        }
        //Si el jugador es min
        if (jugador == -1) {
            //Aplicamos la recursividad para ir creando un arbol
            recursividad(nodo,jugador,tableroactual);
         //Si el jugador es Max
        } else {
            //Aplicamos la recursividad para ir creando un arbol
            recursividad(nodo,jugador,tableroactual);
        }
    }

    /**
     * Metodo que por cada columna libre, crea hijos en el arbol.
     * @param nodopadre
     * @param jugador
     * @param tableroactual
     */
    private void recursividad(Nodo nodopadre, int jugador,Grid tableroactual) {
        //Bucle que recorre todas las columnas
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
                Minimax(nodoHijo, 1);
            }
            //Si el jugado es max
            else{
                //ahora pasa a ser min
                Minimax(nodoHijo, -1);
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
        //Metodo que obtenemos todos los hijos hasta llegar al nodo hoja
        llegarANodoHoja(nodo,esMax);
        //Al llegar al nodo hoja volvemos a la funcion evaluar anterior que le llama es decir a su nodo padre
        //A partir de eso vemos
        // Si  el padre es max
        if (esMax) {
            int mejorValor  = Integer.MIN_VALUE;
            //Obtenemos sus hijos
            for (Nodo hijo : nodo.getHijos()) {
                int valorHijo = hijo.getValor();
                mejorValor = Math.max(mejorValor, valorHijo);
            }
            // Asignar el mejor valor obtenido al nodo actual
            nodo.setValor(mejorValor);
        } else { // Si  el padre es min
            int peorValor = Integer.MAX_VALUE;
            //Obtenemos sus hijos
            for (Nodo hijo : nodo.getHijos()) {
                int valorHijo = hijo.getValor();
                peorValor = Math.min(peorValor, valorHijo);
            }
            nodo.setValor(peorValor);
        }
    }

    /**
     * Metodo que obtenemos todos los hijos hasta llegar al nodo hoja
     * @param nodo
     * @param esMax
     */
    private void llegarANodoHoja(Nodo nodo,boolean esMax) {
        // Si el nodo es una hoja,es decir que no tienen hijos
        //Pasamos al metodo que llamo a evaluar pero ya obteniendo el siguiente hijo
        if (nodo.getHijos().size() == 0) {
            return;
        }

        // Recorrer los nodos hijos del nodo actual
        for (Nodo hijo : nodo.getHijos()) {
            evaluar(hijo, !esMax);
        }

    }

    /**
     * Metodo encontrar movimiento le pasamos el tablero padre y el tablero hijo y obtenemos el movimiento
     * @param tableropadre
     * @param tablerohijo
     * @return
     */
    public static int encontrarMovimiento(int[][] tableropadre, int[][] tablerohijo) {
        int filas = tableropadre.length;
        int columnas = tableropadre[0].length;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
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


}