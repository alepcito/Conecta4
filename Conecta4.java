import java.util.*;
public class Conecta4{
    private final int FIL=7;
    private final int COL=7;
    int profundidadMaxima=6;
    char[][]tablero=new char[FIL][COL];
    private int nodosExplorados,profundidadMaxAlcanzada;
    private long tIni,tFin;
    private final int[] OrdCol={3,2,4,1,5,0,6};

    public int elegirDificultad(Scanner sc){
        System.out.println("Selecciona la dificultad:\n1.-Facil\n2.-Media\n3.-Dificil\n");
        int opcion;
        do{
            System.out.println("Ingresa tu eleccion: ");
            opcion=sc.nextInt();
        }while(opcion<1||opcion>3);
        switch(opcion){
            case 1: return 3;
            case 2: return 5;
            case 3: return 8;
        }
        return 6;
    }
    
    void inicializa(){
        for(int i=0;i<FIL;i++){
            for(int k=0;k<COL;k++){
                tablero[i][k]='.';
            }
        }
    }
    
    private int FilaDisponible(char[][]copia,int columna){
        for(int fila=FIL-1;fila>=0;fila--){
            if(copia[fila][columna]=='.'){
                return fila;
            }
        }
    return -1;//Columna llena
    }

    private char[][]copiarTablero(char[][]original){
        char[][]copia=new char[original.length][original[0].length];
        for(int i=0;i<original.length;i++){
            System.arraycopy(original[i],0,copia[i],0,original[0].length);
        }
        return copia;
    }
    
    private int negamax(char[][]copia,int profundidad,int alpha,int beta,int jugador){
        nodosExplorados++;
        profundidadMaxAlcanzada = Math.max(profundidadMaxAlcanzada, profundidad);

        int eval=evaluar(copia);
        if(Math.abs(eval)==1000||profundidad>=profundidadMaxima||tableroLleno(copia)){
            return jugador*eval;
        }

        int mejor=Integer.MIN_VALUE;
        for(int col : OrdCol){
            int fila=FilaDisponible(copia,col);
            if(fila!=-1){
                char[][]nCopia=copiarTablero(copia);
                nCopia[fila][col]=(jugador==1)?'O':'X';

                int valor=-negamax(nCopia,profundidad+1,-beta,-alpha,-jugador);

                mejor=Math.max(mejor, valor);
                alpha=Math.max(alpha, valor);

                if(alpha>=beta){
                    break;
                }
            }
        }
        return mejor;
    }
    
    public boolean tirar(int col, char jugador){
        if(col<0||col>=COL){
            System.out.println("Columna invalida, ingresa nuevamente");
            return false;
        }
        
        int fil=FilaDisponible(tablero,col);
        if(fil!=-1){
            tablero[fil][col]=jugador;
            return true;
        }
        System.out.println("La columna esta llena");
        return false;
    }
    
    boolean tableroLleno(char[][]copia){
            for(int k=0;k<COL;k++){
                if(copia[0][k]=='.') return false;
            }
        return true;
    }
            //agregar heuristica, bonus para tirar al centro
    private int evaluar(char[][]copia){
        if(hayGanador(copia,'O')) return 1000;
        if(hayGanador(copia,'X')) return -1000;

        int score = 0;
        int centro = COL / 2;

        for(int fil=0; fil<FIL; fil++){
            if(copia[fil][centro] == 'O') score += 6;
            if(copia[fil][centro] == 'X') score -= 6;
        }
        score += puntuacionH(copia,'O');
        score -= puntuacionH(copia,'X');

        return score;
    }
    
    private int puntuacionH(char[][]copia,char jugador){
        int score = 0;
        char rival = (jugador == 'O') ? 'X' : 'O';
            //horizontal
        for(int fil=0; fil<FIL; fil++){
            for(int col=0; col<COL-3; col++){
                score += evaluarVentana(copia, fil, col, 0, 1, jugador, rival);
            }
        }
            //vertical
        for(int fil=0; fil<FIL-3; fil++){
            for(int col=0; col<COL; col++){
                score += evaluarVentana(copia, fil, col, 1, 0, jugador, rival);
            }
        }
            //diagonal izq a der
        for(int fil=0; fil<FIL-3; fil++){
            for(int col=0; col<COL-3; col++){
                score += evaluarVentana(copia, fil, col, 1, 1, jugador, rival);
            }
        }
            //diagonal der a izq
        for(int fil=0; fil<FIL-3; fil++){
            for(int col=3; col<COL; col++){
                score += evaluarVentana(copia, fil, col, 1, -1, jugador, rival);
            }
        }
        return score;
    }
    
    private int evaluarVentana(char[][]copia,int fil,int col,int df,int dc,char jugador,char rival){
        int mias=0,vacias=0,enemigas=0;

        for(int i=0;i<4;i++){
            char c=copia[fil+i*df][col+i*dc];
            if(c==jugador)mias++;
            else if(c==rival)enemigas++;
            else vacias++;
        }
        return puntajeParcial(mias, vacias, enemigas);
    }

    private int puntajeParcial(int mias, int vacias, int rival){
        if(mias == 4) return 100000;
        if(mias == 3 && vacias == 1) return 100;
        if(mias == 2 && vacias == 2) return 10;

        if(rival == 3 && vacias == 1) return -80; //bloquea al rival

        return 0;
    }
    
    public boolean hayGanador(char[][]copia,char jugador){
        for(int fil=0;fil<FIL;fil++){
            for(int col=0;col<COL;col++){
                if(copia[fil][col]!=jugador)continue;
                if(fil+3<FIL){//primera direccion
                    int limite=fil+3;
                    while(limite>=fil){
                        if(copia[limite][col]!=jugador){
                            break;
                        }
                        limite--;
                    }
                    if (limite<fil)return true;
                }
                
                if(col+3<COL){//segunda direccion
                    int limite=col+3;
                    while(limite>=col){
                        if(copia[fil][limite]!=jugador){
                            break;
                        }
                        limite--;
                    }
                    if (limite<col)return true;
                }
                
                if(fil+3<FIL&&col+3<COL){//diagonales derecha abajo
                    int limFil=fil+3;
                    int limCol=col+3;
                    while(limFil>=fil){
                        if(copia[limFil][limCol]!=jugador){
                            break;
                        }
                        limFil--;
                        limCol--;
                    }
                    if(limFil<fil)return true;
                }
                
                if(fil+3<FIL&&col-3>=0){
                    int limFil=fil+3;
                    int limCol=col-3;
                    while(limFil>=fil){
                        if(copia[limFil][limCol]!=jugador){
                            break;
                        }
                        limFil--;
                        limCol++;
                    }
                    if(limFil<fil)return true;
                }
            }
        }
        return false;
    }
    
    void imprimirTablero(){
        for(int fila=0;fila<FIL;fila++){
            System.out.println("");
            for(int col=0;col<COL;col++){
                System.out.print(" "+tablero[fila][col]+" ");
            }   
        }
        System.out.println("");
        System.out.println(" -  -  -  -  -  -  -  \n 1  2  3  4  5  6  7  ");
    }
    
    public int mejorJugadaIA(){
        nodosExplorados = 0;
        profundidadMaxAlcanzada = 0;
        tIni = System.nanoTime();

        int mejorValor=Integer.MIN_VALUE;
        int mejorColumna=-1;
    
        for(int col : OrdCol){
            char[][]copia=copiarTablero(tablero);
            int fila=FilaDisponible(copia,col);

            if(fila!=-1){
                copia[fila][col]='O';
                int valor=-negamax(copia,1,Integer.MIN_VALUE,Integer.MAX_VALUE,-1);
                copia[fila][col]=' ';
                if(valor>mejorValor||(valor==mejorValor&&Math.random()<0.3)){ //aleatoridad para las simulaciones
                    mejorValor=valor;
                    mejorColumna=col;
                }
            }
        }
        tFin = System.nanoTime();
        System.out.println("Movimiento IA: columna " + mejorColumna);
        System.out.println("Nodos explorados: " + nodosExplorados);
        System.out.println("Profundidad alcanzada: " + profundidadMaxAlcanzada);
        System.out.println("Tiempo: " + ((tFin - tIni) / 1_000_000.0) + " ms");

        return mejorColumna;
    }
    
}
