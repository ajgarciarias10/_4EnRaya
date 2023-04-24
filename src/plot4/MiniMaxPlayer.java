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

        evaluar(nodoPadre,false);
        ArrayList<Nodo> hijos = nodoPadre.getHijos();
        Nodo movimiento = null;
        for (int i = 0; i < hijos.size(); i++) {
            if(nodoPadre.getValor() == hijos.get(i).getValor()){
                movimiento = hijos.get(i);
            }
        }
        int posicion = encontrarMovimiento(nodoPadre.getTablero().getGrid(), movimiento.getTablero().getGrid());

        return posicion;
    } // turno


    public static int encontrarMovimiento(int[][] tablero1, int[][] tablero2) {
        int filas = tablero1.length;
        int columnas = tablero1[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero1[i][j] != tablero2[i][j]) {
                    return j; // devolver posición de la celda diferente
                }
            }
        }

        return 0; // si no hay celdas diferentes, devolver null
    }

    public void evaluar(Nodo nodo, boolean Max) {
        // Si el nodo es una hoja, retornar su valor de utilidad
        if (nodo.getHijos().size() == 0) {
            return;
        }

        // Si el nivel actual es un nivel de maximización
        if (Max) {
            int mejorValor = Integer.MIN_VALUE;

            // Recorrer los nodos hijos del nodo actual
            for (Nodo hijo : nodo.getHijos()) {
                evaluar(hijo, false);
                int valorHijo = hijo.getValor();
                mejorValor = Math.max(mejorValor, valorHijo);
            }

            // Asignar el mejor valor obtenido a este nodo
            nodo.setValor(mejorValor);
        } else { // Si el nivel actual es un nivel de minimización
            int mejorValor = Integer.MAX_VALUE;

            // Recorrer los nodos hijos del nodo actual
            for (Nodo hijo : nodo.getHijos()) {
                evaluar(hijo, true);
                int valorHijo = hijo.getValor();
                mejorValor = Math.min(mejorValor, valorHijo);
            }

            // Asignar el mejor valor obtenido a este nodo
            nodo.setValor(mejorValor);
        }
    }


    public void Minimax(Nodo nodo, int jugador) {
        // Obtener el tablero del nodo actual
        Grid tableroactual = nodo.getTablero();
        // Comprobar si el juego ha terminado en este estado
        int estadoganador = tableroactual.checkWin();
        //Vemos si el estado ganador es  o -1(gana min) o 1(gana max)
        if (estadoganador != 0) {
            // Asignar un valor de utilidad en función del resultado del juego
            nodo.setValor(estadoganador == 1 ? 1 : -1);
            return;
        }
        //Si el jugador es min
        if (jugador == -1) {
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
                nodo.addHijos(nodoHijo);
                Minimax(nodoHijo, 1);
            }
        } else {

            for (int col = 0; col < tableroactual.getColumnas(); col++) {
                // Comprobamos si la columna está llena
                if (tableroactual.fullColumn(col)) {
                    continue;
                }
                Grid tableroHijo = new Grid(tableroactual);
                tableroHijo.set(col,jugador);
                Nodo nodoHijo = new Nodo(tableroHijo);
                nodo.addHijos(nodoHijo);
                Minimax(nodoHijo, -1);
            }

        }
    }


    public class Nodo {
        private Grid tablero;
        private int valor;
        private ArrayList<Nodo> hijos;

        public Nodo(Grid tablero) {
            this.tablero = tablero;
            this.valor = 0;
            this.hijos = new ArrayList<Nodo>();
        }

        public Nodo(Grid tablero, int valor, ArrayList<Nodo> hijos) {
            this.tablero = tablero;
            this.valor = valor;
            this.hijos = new ArrayList<Nodo>();
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