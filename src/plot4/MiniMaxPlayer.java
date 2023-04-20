/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plot4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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

    HashMap<Grid, Integer> celdaValorPlayer = new HashMap<>();
    Stack<Grid> piladeTableros = new Stack<>();

    ArrayList<Grid> ramademapas = new ArrayList<>();

    ArrayList<ArrayList> arbol = new ArrayList<>();

    HashMap<ArrayList,Integer> arbolMinMax = new HashMap<>();


    /**
     * @brief funcion que determina donde colocar la ficha este turno
     * @param tablero Tablero de juego
     * @param conecta Número de fichas consecutivas adyacentes necesarias para
     * ganar
     * @return Devuelve si ha ganado algun jugador
     */
    @Override
    public int turno(Grid tablero, int conecta) {

        //Echa primero siempre el jugador1 (Min)


        //Creacion del arbol min max
        crearArbol(tablero);



        return 1;



        // return posicion;

    } // turno

    private void crearArbol(Grid tablero) {

        int jugador = 1;
        ArrayList<Grid> casillasDisponibles;
        //Inicio
        if(celdaValorPlayer.isEmpty()){
            celdaValorPlayer.put(tablero,jugador);
        }else{
            if(celdaValorPlayer.get(tablero) == -1){
                jugador = 1;
            }else{
                jugador= -1;
            }
        }
        //Metodo que compruebe las casillas disponibles
        // Para jugador 1 en caso Min
       // Y para jugador2 caso MAX
        //Comprobamos donde podemos colocar la ficha:
        casillasDisponibles = getPosiblesCasillas(jugador,tablero);
        for (int i = 0; i < casillasDisponibles.size(); i++) {
            /*
                Un hashmap  en el que la clave
                sera el nuevotablero y el valor de si es min o max(jugador2)
             */
            celdaValorPlayer.put(tablero,jugador);
            /*
            y ademas  en una estructura que sea de tipo pila para asi
             tener almacenados todos los hijos los tableros
            */
            piladeTableros.push(tablero);
        }
        //Ahora sacamos de la pila, 1 de los tableros
        Grid  tableroapasar =  piladeTableros.pop();
        ramademapas.add(tableroapasar);
        //si el mapa del tablero esta lleno
        if (isFull(tableroapasar)){
            //si la pila esta vacia
            if(piladeTableros.isEmpty()){
                MinMAX();
                //caso de que no
            }else{
                //si tableroapasarcheckwin() =  1 | -1  y el tablero esta lleno
                if(tableroapasar.checkWin() == 1 | tableroapasar.checkWin() == -1){
                    arbol.add(ramademapas);
                    ramademapas.clear();
                    crearArbol(tableroapasar);
               //si tableroapasarcheckwin() =  igual a 0 empate
                }else{
                    arbol.add(ramademapas);
                    ramademapas.clear();
                    crearArbol(tableroapasar);
                }

            }
        }else{
            if(tableroapasar.checkWin() == 1 ) {
                arbolMinMax.put(ramademapas,1);
                ramademapas.clear();
                crearArbol(piladeTableros.pop());
            }else if(tableroapasar.checkWin() == -1){
                arbolMinMax.put(ramademapas,-1);
                ramademapas.clear();
                crearArbol(piladeTableros.pop());
            }
            else if(tableroapasar.checkWin() == 0){
                crearArbol(tableroapasar);
            }
        }




    }

    private void MinMAX() {
    }
    public boolean isFull(Grid tablero) {
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (tablero.get(i, j) == Main.VACIO) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Grid> getPosiblesCasillas(int jugador, Grid tablero) {
        ArrayList<Grid> posiblesCasillas = new ArrayList<>();
        int ficha = jugador; // o simplemente int ficha = jugador;
        for (int j = 0; j < tablero.getColumnas(); j++) {
            for (int i = tablero.getFilas() - 1; i >= 0; i--) {
                if (tablero.get(i, j) == Main.VACIO) {
                    Grid nuevoGrid = new Grid(tablero);
                    nuevoGrid.set(j, ficha);
                    posiblesCasillas.add(nuevoGrid);
                    break;
                }
            }
        }
        return posiblesCasillas;
    }







}

