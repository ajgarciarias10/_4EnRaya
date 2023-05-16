/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plot4;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author José María Serrano
 * @version 1.7 Departamento de Informática. Universidad de Jáen
 * Última revisión: 2023-03-30
 *
 * Inteligencia Artificial. 2º Curso. Grado en Ingeniería Informática
 *
 * Clase AlfaBetaPlayer para representar al jugador CPU que usa una técnica de IA
 *
 * Esta clase es en la que tenemos que implementar y completar
 * el algoritmo MiniMax con Poda AlfaBeta
 *
 */

public class AlfaBetaPlayer extends Player {
    private  int alpha = -1000;

    private  int beta=  1000;
    private  final int PROFUNDIDAD = 5;

    private  final int CONECTA = 4;
    private  final int Filas = 6;

    private  final int COLUMNAS = 7;

    /**
     * Metodos a utilizar en esta practica
     */


    /**
     * Metodo heurisitico para determinar el ganador toma el valor1 y lo resta a valor 2
     * En funcion de si es menor o mayor  de 0 sera 1 u otro
     * @param valor1
     * @param valor2
     * @return
     */
    private int determinarGanador(int valor1,int valor2) {
        int heuristica = (valor1*valor1)-(valor2*valor2);
        return heuristica;
    }

    /**
     * Metodo que verifica a partir del tablero de que color es la ficha que hay
     * @param tablero
     * @param  i
     * @param j
     * @param  vertical
     * @return
     */

    private int deQueJugadorEs(int[][] tablero, int i , int j, boolean vertical) {
        //Modo vertical
        if(vertical) {
            if (tablero[j][i] == Main.PLAYER1) {
                return 1;
            }
            else if(tablero[j][i] == Main.VACIO) {
                return 0;
            }
            else if(tablero[j][i] == Main.PLAYER2) {
                return -1;
            }
        }//Modo horizontal y oblicuo
        else{
            if (tablero[i][j] == Main.PLAYER1) {
                return 1;
            }
            else if(tablero[i][j] == Main.VACIO) {
                return 0;
            }
            else if(tablero[i][j] == Main.PLAYER2) {
                return -1;
            }
        }
        return 0;
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
        int vacio;
        /**
         *  Declaramos las variables
         * @ganar1 Valor maximo de fichas jugador 1
         * @ganar2 Valor maximo de fichas jugador 2
         */
        int valor1 = 0;
        int valor2 = 0;


        //Comprobamos horizontal
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
                        //Miramos las siguientes fichas
                        if(deQueJugadorEs(tablero,i,j,false) == 1){
                            int valor = mirarsiguientesFichas(vacio,casillasRestantes,tablero,i,j,ganar1,true,"H");
                            if(jugadorHaGanado(valor)){
                                return Main.PLAYER1;
                            } else if (valor >0) {
                                if(valor > valor1){
                                    valor1 = valor;
                                    break;
                                }
                            } else if (valor == 0) {
                                break;
                            }

                        }else{
                            int valor = mirarsiguientesFichas(vacio,casillasRestantes,tablero,i,j,ganar2,false,"H");
                            if(jugadorHaGanado(valor)){
                                return Main.PLAYER2;
                            } else if (valor >0) {
                                if(valor > valor2){
                                    valor2 = valor;
                                    break;
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
                        if(deQueJugadorEs(tablero,i,j,false) == 1){
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
                        }else if(deQueJugadorEs(tablero,i,j,false) == -1){
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
        // Comprobamos vertical
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
                        //Miramos las siguientes fichas
                        if(deQueJugadorEs(tablero,i,j,true) == 1){
                            int valor = mirarsiguientesFichas(vacio,casillasRestantes,tablero,i,j,ganar1,true,"V");
                            if(jugadorHaGanado(valor)){
                                return Main.PLAYER1;
                            } else if (valor >0) {
                                if(valor > valor1){
                                    valor1 = valor;
                                    break;
                                }
                            } else if (valor == 0) {
                                break;
                            }

                        }else if(deQueJugadorEs(tablero,i,j,true) == -1){
                            int valor = mirarsiguientesFichas(vacio,casillasRestantes,tablero,i,j,ganar2,false,"V");
                            if(jugadorHaGanado(valor)){
                                return Main.PLAYER2;
                            } else if (valor >0) {
                                if(valor > valor2){
                                    valor2 = valor;
                                    break;
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
                        if(deQueJugadorEs(tablero,i,j,true) == 1){
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
                        }else if(deQueJugadorEs(tablero,i,j,true) == -1){
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

        // Comprobamos oblicuo de izquierda a derecha
        for (int i = 0; i < Filas; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                int a = i;
                int b = j;
                ganar1 = 0;
                ganar2 = 0;
                while (a < Filas && b < COLUMNAS) {
                    if (tablero[a][b] != Main.VACIO) {
                        //Caso jugador 1
                        /**
                         * Comprobamos si hay fichas en la posicion oblicua:
                         * [a][b] del jugador 1
                         */
                        if (tablero[a][b] == Main.PLAYER1) {
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
                            boolean valido;
                            int contadorEspaciosDerAbajo = 0;
                            int contadorEspaciosIzqArriba = 0;
                            int c;
                            int d;
                            //Hay que ver si hay posibilidad de poner mas fichas a la izquierda como a la derecha
                            int diferenciaconectaFicha1 = CONECTA - ganar1;
                            //Miramos hacia izq Arriba  <---
                            if(a-(ganar1-1)-diferenciaconectaFicha1 >= 0 && b-(ganar1-1)-diferenciaconectaFicha1 >= 0 && tablero[a][b]==Main.PLAYER1){
                                c=a-ganar1+1;
                                d=b-ganar1+1;
                                int contadorIzqArriba = 0;// entramos en el if si la fila o la columna es mayor que 0 al restar importante el "||"
                                valido = true;
                                while (c >= a-diferenciaconectaFicha1 && d >= b-diferenciaconectaFicha1 ) {
                                    if(tablero[c][d] == Main.PLAYER1 || tablero[c][d] == Main.VACIO){
                                        contadorIzqArriba++;
                                        if (tablero[c][d] == Main.PLAYER1 || tablero[c][d] == Main.PLAYER2) {
                                            valido = false;
                                        }
                                        if(tablero[c][d] == Main.VACIO && valido){
                                            contadorEspaciosIzqArriba++;
                                        }
                                    }
                                    c--;
                                    d--;
                                }
                                if(contadorIzqArriba >= diferenciaconectaFicha1){
                                    if(ganar1 > valor1)valor1 = ganar1;
                                }
                            }

                            // Miramos hacia derecha Abajo
                            if(a+diferenciaconectaFicha1 < Filas && b+diferenciaconectaFicha1 < COLUMNAS && tablero[a][b]==Main.PLAYER1){
                                c=a+1;
                                d=b+1;
                                int contadorDerAbajo = 0;
                                valido = true;
                                while (c <= a+diferenciaconectaFicha1 && d <= b+diferenciaconectaFicha1 ) {

                                    if (tablero[c][d] == Main.PLAYER1 || tablero[c][d] == Main.VACIO) {
                                        contadorDerAbajo++;
                                        if (tablero[c][d] == Main.PLAYER1 || tablero[c][d] == Main.PLAYER2) {
                                            valido = false;
                                        }

                                        if (tablero[c][d] == Main.VACIO && valido) {
                                            contadorEspaciosDerAbajo++;
                                        }
                                    }
                                    c++;
                                    d++;
                                }
                                if(contadorDerAbajo == diferenciaconectaFicha1){
                                    if(ganar1 > valor1)valor1 = ganar1;
                                }
                            }

                            if(ganar1+contadorEspaciosDerAbajo+contadorEspaciosIzqArriba >= CONECTA && tablero[a][b]==Main.PLAYER1){
                                if(ganar1 > valor1)valor1 = ganar1;
                            }
                        }
                        //En caso de que no haya ganado el jugador1 miramos el jugador 2
                        /**
                         * Comprobamos si hay fichas en la posicion horizontal:
                         * [a][b] del jugador 2
                         */
                        if(tablero[a][b] == Main.PLAYER2){
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

                            boolean valido = true;
                            int contadorEspaciosDerAbajo = 0;
                            int contadorEspaciosIzqArriba = 0;
                            int c;
                            int d;
                            //Hay que ver si hay posibilidad de poner mas fichas a la izquierda como a la derecha
                            int diferenciaconectaFicha2 = CONECTA - ganar2;
                            //Miramos hacia izq Arriba  <---
                            if(a-(ganar2-1)-diferenciaconectaFicha2 >= 0 && b-(ganar2-1)-diferenciaconectaFicha2 >= 0 && tablero[a][b]==Main.PLAYER2){
                                c=a-ganar2+1;
                                d=b-ganar2+1;
                                int contadorIzqArriba = 0;// entramos en el if si la fila o la columna es mayor que 0 al restar importante el "||"
                                valido = true;
                                while (c >= a-diferenciaconectaFicha2 && d >= b-diferenciaconectaFicha2 ) {
                                    if(tablero[c][d] == Main.PLAYER2 || tablero[c][d] == Main.VACIO){
                                        contadorIzqArriba++;
                                        if (tablero[c][d] == Main.PLAYER1 || tablero[c][d] == Main.PLAYER2) {
                                            valido = false;
                                        }
                                        if(tablero[c][d] == Main.VACIO && valido){
                                            contadorEspaciosIzqArriba++;
                                        }
                                    }
                                    c--;
                                    d--;
                                }
                                if(contadorIzqArriba >= diferenciaconectaFicha2){
                                    if(ganar2 > valor2)valor2 = ganar2;
                                }
                            }

                            // Miramos hacia derecha Abajo
                            if(a+diferenciaconectaFicha2 < Filas && b+diferenciaconectaFicha2 < COLUMNAS && tablero[a][b]==Main.PLAYER2){
                                c=a+1;
                                d=b+1;
                                int contadorDerAbajo = 0;
                                valido = true;
                                while (c <= a+diferenciaconectaFicha2 && d <= b+diferenciaconectaFicha2 ) {

                                    if (tablero[c][d] == Main.PLAYER2 || tablero[c][d] == Main.VACIO) {
                                        contadorDerAbajo++;
                                        if (tablero[c][d] == Main.PLAYER1 || tablero[c][d] == Main.PLAYER2) {
                                            valido = false;
                                        }
                                        if (tablero[c][d] == Main.VACIO && valido) {
                                            contadorEspaciosDerAbajo++;
                                        }
                                    }
                                    c++;
                                    d++;
                                }
                                if(contadorDerAbajo == diferenciaconectaFicha2){
                                    if(ganar2 > valor2)valor2 = ganar2;
                                }
                            }

                            if(ganar2+contadorEspaciosDerAbajo+contadorEspaciosIzqArriba >= CONECTA && tablero[a][b]==Main.PLAYER2){
                                if(ganar2 > valor2)valor2 = ganar2;
                            }
                        }

                    }else {
                        ganar1 = 0;
                        ganar2 = 0;
                    }
                    a++;
                    b++;
                }
            }
        }

        // Comprobamos oblicuo de derecha a izquierda
        for (int i = Filas - 1; i >=0; i--) {
            for (int j = 0; j < COLUMNAS; j++) {
                int a = i;
                int b = j;
                vacio=0;
                ganar1 = 0;
                ganar2 = 0;
                while ((a >= 0) && (b < COLUMNAS)) {
                    //Si el numero de fila es menor o igual que el conecta
                    //Vemos si es posible alcanzar el conecta 4 en lo que queda
                    if(b <= CONECTA){
                        if(tablero[a][b] == Main.VACIO){
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
                            //Miramos las siguientes fichas
                            if(deQueJugadorEs(tablero,a,b,false) == 1){
                                int valor = mirarsiguientesFichas(vacio,b,tablero,a,b,ganar1,true,"OD");
                                if(jugadorHaGanado(valor)){
                                    return Main.PLAYER1;

                                }  if (valor >0) {
                                    if(valor > valor1){
                                        valor1 = valor;
                                        break;
                                    }

                                } else if (valor == 0) {
                                    break;
                                }

                            }else  if(deQueJugadorEs(tablero,a,b,false) == -1){
                                int valor = mirarsiguientesFichas(vacio,a,tablero,a,b,ganar2,false,"OD");
                                if(jugadorHaGanado(valor)){
                                    return Main.PLAYER2;
                                } else if (valor >0) {
                                    if(valor > valor2){
                                        valor2 = valor;
                                        break;
                                    }
                                } else if (valor == 0) {
                                    break;
                                }
                            }
                        }


                    }
                    else{
                        //Si el tablero no esta vacio
                        if (tablero[a][b] != Main.VACIO) {
                            //Comprobamos que jugador es
                            if(deQueJugadorEs(tablero,a,b,false) == 1){
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
                            }else  if(deQueJugadorEs(tablero,a,b,false) == -1){
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



                    a--;
                    b++;
                }
            }
        }

        return  determinarGanador(valor1,valor2);

    }
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
     * Metodo que mira desde la posicion Filas -j <= Conecta es decir
     * Si en un 5x6  estuvieramos en j = 1 mirariamos las siguientes filas desde esa fila
     * Devolvera true si se se logra conecta con la ficha del color en j=1
     * Y en caso contrario false
     * @return
     */
    private int mirarsiguientesFichas(int vacio, int casillasRestantes,int[][] tablero, int i , int j, int ganar, boolean jugador,String modo) {
        int valor = 0;
        if(modo.equals("H")) {
            //region Modo horizontal
            //region Caso Jugador 1
            if (jugador) {
                //Realizamos un bucle hasta que se acaben las fichas restantes
                for (int k = 0; k < casillasRestantes; k++) {
                    //Miramos de que jugador es
                    int columna = j + k;
                    if (deQueJugadorEs(tablero, i, columna, false) == 1) {
                        ganar++;
                        if (jugadorHaGanado(ganar)) {
                            return ganar;
                        }
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }

                    } else if(deQueJugadorEs(tablero, i, j + k, false) == -1) {
                        return 0;

                    }
                    else if(deQueJugadorEs(tablero, i, j + k, false) == 0) {
                        vacio++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }
                    }
                }
                return  valor;


            }
            else {
                for (int k = 0; k < casillasRestantes; k++) {
                    //Miramos de que jugador es
                    int columna = j + k;
                    if (deQueJugadorEs(tablero, i, columna, false) == -1) {
                        ganar++;
                        if (vacio + ganar >= CONECTA) {
                            valor = ganar;
                        }
                        if (jugadorHaGanado(ganar)) {
                            return ganar;
                        }
                    } else if(deQueJugadorEs(tablero, i, j + k, false) == 1) {
                        return 0;
                    }
                    else if(deQueJugadorEs(tablero, i, j + k, false) == 0) {
                        vacio++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }else{
                            return 0;
                        }
                    }

                }
                return  valor;


            }
        }
        else if (modo.equals("V")) {
            if (jugador) {
                //Realizamos un bucle hasta que se acaben las fichas restantes
                for (int k = 0; k < casillasRestantes; k++) {
                    //Miramos de que jugador es
                    if (deQueJugadorEs(tablero, i, j + k, true) == 1) {
                        ganar++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }
                        if (jugadorHaGanado(ganar)) {
                            return ganar;
                        }
                    } else if (deQueJugadorEs(tablero, i, j + k, true) == -1){
                        return 0;

                    }
                    else if (deQueJugadorEs(tablero, i, j + k, true) == 0) {
                        vacio++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }
                    }

                }
                return  valor;


            } else {
                for (int k = 0; k < casillasRestantes; k++) {
                    //Miramos de que jugador es
                    int columna = j + k;
                    if (deQueJugadorEs(tablero, i, columna, true) == -1) {
                        ganar++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }
                        if (jugadorHaGanado(ganar)) {
                            return ganar;
                        }
                    } else if (deQueJugadorEs(tablero, i, j + k, true) == 1) {
                        return 0;

                    }
                    else if (deQueJugadorEs(tablero, i, j + k, true) == 0) {
                        vacio++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }
                    }

                }
                return  valor;
            }
        }
        //Modo Oblicuo Derecha Izquierda
        else if (modo.equals("OD")) {
            return oblicuo(vacio,tablero,i ,j,ganar,jugador,true);
        }


        //endregion
        //endregion
        return  0;

    }
    /**
     * Metodo utilizado para calcular los oblicuos
     * @param vacio
     * @param tablero
     * @param i
     * @param j
     * @param ganar
     * @param jugador
     * @param dI
     * @return
     */
    private int oblicuo(int vacio, int[][] tablero, int i, int j, int ganar, boolean jugador, boolean dI) {
        int valor= 0;
        if(dI){
            if(jugador) {
                while ((i >= 0) && (j < COLUMNAS)) {
                    //Miramos de que jugador es
                    if (deQueJugadorEs(tablero, i, j, false) == 1) {
                        ganar++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }
                        if (jugadorHaGanado(ganar)) {
                            return ganar;
                        }
                    } else if (deQueJugadorEs(tablero, i, j, false) == -1) {
                        return 0;
                    }
                    else if (deQueJugadorEs(tablero, i, j, false) == 0) {
                        vacio++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }

                    }
                    i--;
                    j++;
                }
                return  valor;

            }
            else{
                while ((i >= 0) && (j < COLUMNAS)) {
                    if (deQueJugadorEs(tablero, i, j, false) == -1) {
                        ganar++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }
                        if (jugadorHaGanado(ganar)) {
                            return ganar;
                        }
                    }  if (deQueJugadorEs(tablero, i, j, false) == 1) {
                        return 0;
                    }
                    if (deQueJugadorEs(tablero, i, j, false) == 0) {
                        vacio++;
                        if (vacio + ganar >= CONECTA) {
                            valor =  ganar;
                        }
                    }
                    i--;
                    j++;
                }
                return  valor;

            }

        }
        return  0;
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

        podaAlphaBeta(nodoPadre,PROFUNDIDAD,alpha,beta,false);
        //imprimirNodo(nodoPadre, ""); // Función que dibuja el arbol al completo

        //Sacamos todos los hijos del arraylist que tenemos
        ArrayList<Nodo> hijos = nodoPadre.getHijos();
        //Incializamos un nodo que va a ser el movimiento
        Nodo movimiento = null;
        ArrayList<Nodo> posiblesMovimientos = new ArrayList<>();
        for (Nodo hijo : hijos) {
            //Recoremos la lista de hijos
            if (nodoPadre.getValor() == hijo.getValor()) {
                //Obtenemos el movimiento
                movimiento = hijo;
                posiblesMovimientos.add(movimiento);
            }
        }
        // Elegimos uno de los movimientos de manera aleatoria
        Random random = new Random();
        int numeroRandom = random.nextInt((posiblesMovimientos.size()-1) - 0 + 1) + 0;

        //Devolvemos la posicion del movimiento
        return encontrarMovimiento(nodoPadre.getTablero().getGrid(), posiblesMovimientos.get(numeroRandom).getTablero().getGrid());

    } // turno


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

        public  void  eliminarHijos(Nodo hijo){
            this.hijos.remove(hijo);
        }
        public void setValor(int valor) {
            this.valor = valor;
        }

        public void addHijos(Nodo hijo){
            this.hijos.add(hijo);
        }
    }


    public void generarHijos(Nodo nodo,int jugador) {
        Grid tablero = nodo.getTablero();

        // Generar hijos para todas las posibles columnas
        for (int col = 0; col < tablero.getColumnas(); col++) {
            // Comprobar si la columna está llena
            if (tablero.fullColumn(col)) {
                continue;
            }

            // Clonar el tablero actual
            Grid tableroHijo = new Grid(tablero);
            // Realizamos el movimiento
            tableroHijo.set(col,jugador);
            //Creamos el nodo Hijo
            Nodo nodoHijo = new Nodo(tableroHijo);
            //Añadimos el hijo al padre
            nodo.addHijos(nodoHijo);

        }
    }


    public int podaAlphaBeta(Nodo nodo, int profundidad, int alfa, int beta, boolean Max) {

        if (profundidad == 0 || nodo.getTablero().checkWin() != 0) {  // Si llegamos a la profundidad o hay algún ganador
            if (nodo.getTablero().checkWin() != 0) {  // Si hay ganador
                nodo.setValor(nodo.getTablero().checkWin());  // Guardamos el ganador y lo devolvemos
                return nodo.getValor();
            } else { // Sino calculamos valor heuristico
                nodo.setValor(Heuristica(nodo.getTablero().getGrid())); // Generamos el valor heuristico y lo devolvemos
                return nodo.getValor();
            }
        }

        if (Max) {  // Si es MAX
            generarHijos(nodo, 1);  //Generar hijos del nodo actual

            for (Nodo hijo : nodo.getHijos()) {
                int valor = podaAlphaBeta(hijo, profundidad - 1, alfa, beta, false);
                alfa = Math.max(alfa, valor);
                if (beta <= alfa) {
                    // Poda alfa-beta
                    for (int i = nodo.getHijos().indexOf(hijo) + 1; i < nodo.getHijos().size(); i++) {
                        Nodo hijoRestante = nodo.getHijos().get(i);
                        nodo.eliminarHijos(hijoRestante); // Eliminar los hijos restantes
                    }
                    break;
                }
            }

            nodo.setValor(alfa);  // Guardamos el valor de alfa
            return alfa;  // Devolvemos alfa
        } else {   // Si es MIN
            generarHijos(nodo, -1); // Generar los hijos del nodo actual

            for (Nodo hijo : nodo.getHijos()) {
                int valor = podaAlphaBeta(hijo, profundidad - 1, alfa, beta, true);
                beta = Math.min(beta, valor);

                if (alfa >= beta) {
                    // Realizamos la poda
                    for (int i = nodo.getHijos().indexOf(hijo) + 1; i < nodo.getHijos().size(); i++) {
                        Nodo hijoRestante = nodo.getHijos().get(i);
                        nodo.eliminarHijos(hijoRestante); // Eliminar los hijos restantes
                    }
                    break;
                }
            }
            nodo.setValor(beta); // Guardamos el valor de beta
            return beta; // Devolvemos Beta
        }
    }
}