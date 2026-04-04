import java.util.Scanner;

public class Prueba {
    public static void main(String[]args){
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
    }
}