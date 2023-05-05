/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plot4;

/**
 *
 * @author José María Serrano
 * @version 1.7 Departamento de Informática. Universidad de Jáen 
 * Última revisión: 2023-03-30
 *
 * Inteligencia Artificial. 2º Curso. Grado en Ingeniería Informática
 *
 * Clase MiniMaxRestrainedPlayer para representar al jugador CPU que usa una
 * técnica de IA
 *
 * Esta clase es en la que tenemos que implementar y completar el algoritmo
 * MiniMax Restringido
 *
 */
public class MiniMaxRestrainedPlayer extends Player {

    /**
     * @brief funcion que determina donde colocar la ficha este turno
     * @param tablero Tablero de juego
     * @param conecta Número de fichas consecutivas adyacentes necesarias para
     * ganar
     * @return columna donde dejar caer la ficha
     */
    @Override
    public int turno(Grid tablero, int conecta) {
        int posicion = getRandomColumn(tablero);
        // to do

        // ...
        return posicion;

    } // turno
    private int heuristica(int filas, int columnas,int[][] tablero,int conecta){
        int ganador=0;
        int ganar1;
        int ganar2;
        boolean salir = false;
        //Mira check win
        //Ver horizontal
        // Comprobar horizontal
        for (int i = 0; (i < filas) && !salir; i++) {
            ganar1 = 0;
            ganar2 = 0;
            for (int j = 0; (j < columnas) && !salir; j++) {
                if (tablero[i][j] != Main.VACIO) {
                    //Conteo 1 y 2
                    if (tablero[i][j] == Main.PLAYER1) {
                        ganar1++;
                    } else {
                        ganar1 = 0;
                    }

                    if (tablero[i][j] == Main.PLAYER2) {
                        ganar2++;
                    } else {
                        ganar2 = 0;
                    }
                    //Comprobacion de si supera el limete establecido

                    //Comprueba fichas contiguas
                }
            }
        return 0;
        }
        /**
         // Gana el jugador 1
         if (ganar1 >= conecta) {
         ganador = Main.PLAYER1;
         // Ganador 1 en horizontal;
         salir = true;
         }
         **/
        // if (!salir) {

        /**
         // Gana el jugador 2
         if (ganar2 >= conecta) {
         ganador = Main.PLAYER2;
         // Ganador 2 en horizontal;
         salir = true;
         }

         // }
         } else {
         ganar1 = 0;
         ganar2 = 0;
         }
         **/

        return ganador;
    } // MiniMaxRestrainedPlayer
