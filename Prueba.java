import java.util.Scanner;

public class Prueba {
        //El main comentado es para que juegue una persona vs IA.
        //El main que se ejecuta es para hacer pruebas IA vs IA.

    /*public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        Conecta4 a=new Conecta4();
        char jugador='X';
        a.profundidadMaxima=a.elegirDificultad(sc);
        a.inicializa();
        
        while(!a.tableroLleno(a.tablero)){
            a.imprimirTablero();
            boolean valida=false;
            if(jugador=='X'){
                while(!valida){
                    System.out.println("Turno del jugador, ingresa la columna para tirar: (0 para terminar el juego)");
                    int col=sc.nextInt()-1;
                    if(col==-1){
                        System.out.println("Juego terminado por el jugador");
                        return;
                    }
                    valida=a.tirar(col,jugador);
                }
                if(a.hayGanador(a.tablero,jugador)){
                    a.imprimirTablero();
                    System.out.println("El ganador es el jugador");
                    return;
                }
                jugador='O';
            }else{
                System.out.println("Turno de la IA");
                int mejorCol=a.mejorJugadaIA();
                //System.out.println("Mejor col de la ia es: "+mejorCol);
                valida=a.tirar(mejorCol, jugador);
                //System.out.println("Tirar de ia regresoa: "+valida);
                if(a.hayGanador(a.tablero,jugador)){
                    a.imprimirTablero();
                    System.out.println("Gana la IA");
                    return;
                }
                jugador='X';
            }
        }
        a.imprimirTablero();
        System.out.println("Empate!");
        sc.close();
    } */
    public static void main(String[] args){

        int partidas=20; //# de simulaciones

        int ganaIA1=0;
        int ganaIA2=0;
        int empates=0;

        for(int p=0;p<partidas;p++){

            Conecta4 a=new Conecta4();
            a.inicializa();

                //se pueden variar niveles de juego, mayor profundidad es mayor nivel
            int profundidadIA1=6;
            int profundidadIA2=6;

            char jugador='X'; // IA1 = X, IA2 = O

            while(!a.tableroLleno(a.tablero)){

                if(jugador=='X'){
                    a.profundidadMaxima=profundidadIA1;
                    int col=a.mejorJugadaIA();
                    a.tirar(col,'X');

                    if(a.hayGanador(a.tablero,'X')){
                        ganaIA1++;
                        break;
                    }

                    jugador='O';

                }else{
                    a.profundidadMaxima=profundidadIA2;
                    int col=a.mejorJugadaIA();
                    a.tirar(col, 'O');

                    if(a.hayGanador(a.tablero,'O')){
                        ganaIA2++;
                        break;
                    }

                    jugador='X';
                }
            }

            if(!a.hayGanador(a.tablero,'X')&&!a.hayGanador(a.tablero,'O')){
                empates++;
            }
        }

        System.out.println("===== RESULTADOS =====");
        System.out.println("IA1 (X) gana: " + ganaIA1);
        System.out.println("IA2 (O) gana: " + ganaIA2);
        System.out.println("Empates: " + empates);
    }
}