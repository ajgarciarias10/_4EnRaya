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
 * Clase MiniMaxRestrainedPlayer para representar al jugador CPU que usa una
 * técnica de IA
 *
 * Esta clase es en la que tenemos que implementar y completar el algoritmo
 * MiniMax Restringido
 *
 */
public class MiniMaxRestrainedPlayer extends Player {
    Grid tablero ;
    private  final int PROFUNDIDAD = 4;

    private  final int CONECTA = tablero.conecta;
    private  final int FILAS = tablero.filas;

    private  final int COLUMNAS = tablero.columnas;
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

    /**
     *  Funcion heuristica
     * @param tablero
     * @return
     */
    private  int heuristica(int tablero[][]){
        int  ganar1;
        int valor1 = 0;
        int  ganar2;
        int valor2 = 0;
        // Comprobar horizontal
        for (int i = 0; (i < FILAS); i++) {
            ganar1 = 0;
            ganar2 = 0;
            for (int j = 0; (j < COLUMNAS); j++) {
                //Comprueba sin esa parte del tablero las fichas son del jugador 1
                if (tablero[i][j] != Main.VACIO) {
                    //Caso jugador 1
                    if (tablero[i][j] == Main.PLAYER1) {
                        //Añadimos fichas  1
                        ganar1++;
                    } else {
                        //En el caso de que en esa posicion del tablero
                        //la ficha no sea del jugador 1 comprobamos

                        //Vemos si ganar 1 ha llegado a un estado ganador
                        if(ganar1 >= CONECTA){
                            //Y Devolvemos el ganador
                            return Main.PLAYER1;
                        }else{
                            //Si en la posicion en la que estabamos viendo de si hay del jugador 1
                            //Hay del jugador dos
                            if(tablero[i][j] == Main.PLAYER2){
                                //Ya no nos sirve esa ficha
                                ganar1 = 0;
                            }else{
                                if(ganar1 > valor1)valor1 = ganar1;
                                //Reseteamos el ganar 1
                                ganar1=0;
                            }
                        }
                    }
                    //Caso jugador 2
                    if (tablero[i][j] == Main.PLAYER2) {
                        ganar2++;
                    } else {
                        //Vemos si ganar 1 ha llegado a un estado ganador
                        if (ganar2 >= CONECTA) {
                            //Devolvemos el jugador 2
                            return Main.PLAYER2;
                            //se acaba
                        } else {
                            //En el caso de que en esa posicion del tablero
                            //la ficha no sea del jugador 1 comprobamos

                            //Si en la posicion en la que estabamos viendo de si hay del jugador 1
                            //Hay del jugador dos
                            if (tablero[i][j] == Main.PLAYER1) {
                                //Si hay ya la de ganar 1 ya no nos sirve
                                ganar2 = 0;
                            } else {
                                if (ganar2 > valor2) valor2 = ganar2;
                                ganar2 = 0;
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
        // Comprobar vertical
        for (int i = 0; (i < COLUMNAS); i++) {
            ganar1 = 0;
            ganar2 = 0;
            for (int j = 0; (j < FILAS) ; j++) {
                if (tablero[j][i] != Main.VACIO) {
                    //Caso jugador 1
                    if (tablero[j][i] == Main.PLAYER1) {
                        //Añadimos fichas  1
                        ganar1++;
                    } else {
                        //En el caso de que en esa posicion del tablero
                        //la ficha no sea del jugador 1 comprobamos

                        //Vemos si ganar 1 ha llegado a un estado ganador
                        if(ganar1 >= CONECTA){
                            //Y Devolvemos el ganador
                            return Main.PLAYER1;
                        }else{
                            //Si en la posicion en la que estabamos viendo de si hay del jugador 1
                            //Hay del jugador dos
                            if(tablero[j][i] == Main.PLAYER2){
                                //Ya no nos sirve esa ficha
                                ganar1 = 0;
                            }else{
                                if(ganar1 > valor1)valor1 = ganar1;
                                //Reseteamos el ganar 1
                                ganar1=0;
                            }
                        }
                    }
                    //Caso jugador 2
                    if (tablero[j][i] == Main.PLAYER2) {
                        ganar2++;
                    } else {
                        //Vemos si ganar 1 ha llegado a un estado ganador
                        if (ganar2 >= CONECTA) {
                            //Devolvemos el jugador 2
                            return Main.PLAYER2;
                            //se acaba
                        } else {
                            //En el caso de que en esa posicion del tablero
                            //la ficha no sea del jugador 1 comprobamos

                            //Si en la posicion en la que estabamos viendo de si hay del jugador 1
                            //Hay del jugador dos
                            if (tablero[j][i] == Main.PLAYER1) {
                                //Si hay ya la de ganar 1 ya no nos sirve
                                ganar2 = 0;
                            } else {
                                if (ganar2 > valor2) valor2 = ganar2;
                                ganar2 = 0;
                            }
                        }

                    }

                } else {
                    ganar1 = 0;
                    ganar2 = 0;
                }
            }
        }
        // Comprobar oblicuo. De izquierda a derecha
        for (int i = 0; i < FILAS ; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                int a = i;
                int b = j;
                ganar1 = 0;
                ganar2 = 0;
                while (a < FILAS && b < COLUMNAS) {
                    if (tablero[a][b] != Main.VACIO) {
                        //Caso jugador 1
                        if (tablero[a][b] == Main.PLAYER1) {
                            //Añadimos fichas  1
                            ganar1++;
                        } else {
                            //En el caso de que en esa posicion del tablero
                            //la ficha no sea del jugador 1 comprobamos

                            //Vemos si ganar 1 ha llegado a un estado ganador
                            if(ganar1 >= CONECTA){
                                //Y Devolvemos el ganador
                                return Main.PLAYER1;
                            }else{
                                //Si en la posicion en la que estabamos viendo de si hay del jugador 1
                                //Hay del jugador dos
                                if(tablero[a][b] == Main.PLAYER2){
                                    //Ya no nos sirve esa ficha
                                    ganar1 = 0;
                                }else{
                                    if(ganar1 > valor1)valor1 = ganar1;
                                    //Reseteamos el ganar 1
                                    ganar1=0;
                                }
                            }
                        }
                        //Caso jugador 2
                        if (tablero[a][b] == Main.PLAYER2) {
                            ganar2++;
                        } else {
                            //Vemos si ganar 1 ha llegado a un estado ganador
                            if (ganar2 >= CONECTA) {
                                //Devolvemos el jugador 2
                                return Main.PLAYER2;
                                //se acaba
                            } else {
                                //En el caso de que en esa posicion del tablero
                                //la ficha no sea del jugador 1 comprobamos

                                //Si en la posicion en la que estabamos viendo de si hay del jugador 1
                                //Hay del jugador dos
                                if (tablero[a][b] == Main.PLAYER1) {
                                    //Si hay ya la de ganar 1 ya no nos sirve
                                    ganar2 = 0;
                                } else {
                                    if (ganar2 > valor2) valor2 = ganar2;
                                    ganar2 = 0;
                                }
                            }

                        }

                    } else {
                        ganar1 = 0;
                        ganar2 = 0;
                    }
                    a++;
                    b++;
                }
            }
        }
        // Comprobar oblicuo de derecha a izquierda
        for (int i = FILAS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNAS; j++) {
                int a = i;
                int b = j;
                ganar1 = 0;
                ganar2 = 0;
                while (a >= 0 && b < COLUMNAS) {
                    if (tablero[a][b] != Main.VACIO) {
                        //Caso jugador 1
                        if (tablero[a][b] == Main.PLAYER1) {
                            //Añadimos fichas  1
                            ganar1++;
                        } else {
                            //En el caso de que en esa posicion del tablero
                            //la ficha no sea del jugador 1 comprobamos

                            //Vemos si ganar 1 ha llegado a un estado ganador
                            if (ganar1 >= CONECTA) {
                                //Y Devolvemos el ganador
                                return Main.PLAYER1;
                            } else {
                                //Si en la posicion en la que estabamos viendo de si hay del jugador 1
                                //Hay del jugador dos
                                if (tablero[a][b] == Main.PLAYER2) {
                                    //Ya no nos sirve esa ficha
                                    ganar1 = 0;
                                } else {
                                    if (ganar1 > valor1) valor1 = ganar1;
                                    //Reseteamos el ganar 1
                                    ganar1 = 0;
                                }
                            }
                        }
                        //Caso jugador 2
                        if (tablero[a][b] == Main.PLAYER2) {
                            ganar2++;
                        } else {
                            //Vemos si ganar 1 ha llegado a un estado ganador
                            if (ganar2 >= CONECTA) {
                                //Devolvemos el jugador 2
                                return Main.PLAYER2;
                                //se acaba
                            } else {
                                //En el caso de que en esa posicion del tablero
                                //la ficha no sea del jugador 1 comprobamos

                                //Si en la posicion en la que estabamos viendo de si hay del jugador 1
                                //Hay del jugador dos
                                if (tablero[a][b] == Main.PLAYER1) {
                                    //Si hay ya la de ganar 1 ya no nos sirve
                                    ganar2 = 0;
                                } else {
                                    if (ganar2 > valor2) valor2 = ganar2;
                                    ganar2 = 0;
                                }
                            }

                        }
                    } else {
                        ganar1 = 0;
                        ganar2 = 0;
                    }
                    a--;
                    b++;
                }
            }
        }

        //En caso de que no haya ganado nadie
         if(valor1 > valor2) {
             return valor1;
         }
         else if (valor1<=valor2) {
                return valor2;
         }
         return 0;

    }

} // MiniMaxRestrainedPlayer
