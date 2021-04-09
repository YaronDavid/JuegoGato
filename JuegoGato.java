
ackage juegogato;
import java.io.*;
import java.util.Scanner;

public class JuegoGato {
    private static void inicio() {
        PipedInputStream in[];
        PipedOutputStream out[];
        hilos h[];
        String numero;
        int valor;
        int random;
        int cont=1;
        int aux=0;
        System.out.println("Ingresa el numero de partidas: ");
        Scanner teclado=new Scanner(System.in);
        numero=teclado.nextLine();
        valor=Integer.parseInt(numero);
        out=new PipedOutputStream[valor*2];
        in=new PipedInputStream[valor*2];
        h=new hilos[valor*2];
        int i;
        for(i=0; i<valor*2; i++){
            try{
                out[i]=new PipedOutputStream();
                in[i]=new PipedInputStream(out[i]);
            }catch(IOException e){}
        }
        for(i=0; i<valor*2; i++){
            try{
                if(i%2==0){
                    random=(int) ((Math.random()) * 20)%2;
                    h[i]=new hilos(in[i], out[i+1], random, 1, cont);//Tablero de Circulo
                    aux=random;
                }
                else{
                    h[i]=new hilos(in[i], out[i-1], aux, 0, cont);//Tablero de Tache asociado al Circulo
                    cont++;
                }
            }catch(Exception e){}
        }
    }
    public static void main(String args[]){
        inicio();
    }
}
