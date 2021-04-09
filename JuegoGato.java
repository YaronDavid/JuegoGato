
package juegogato;
import java.io.*;
import java.util.Scanner;

public class JuegoGato {
    private static void inicio(int juego) {
        PipedInputStream in = null;
        PipedOutputStream out=null;
        PipedInputStream in2=null;
        PipedOutputStream out2=null;
        hilos h1;
        hilos h2;
        Thread hilo1;
        Thread hilo2;
        int random;
        int aux=0;
        try{
        out=new PipedOutputStream();
        in=new PipedInputStream(out);
        out2=new PipedOutputStream();
        in2=new PipedInputStream(out2);
        }catch(IOException e){}
            try{
                    random=(int) ((Math.random()) * 20)%2;
                    h1=new hilos(in, out2, random, 1, juego);//Tablero de Circulo
                    aux=random;
                    h2=new hilos(in2, out, aux, 0, juego);//Tablero de Tache asociado al Circulo
                    hilo1=new Thread(h1);
                    hilo2=new Thread(h2);
                    hilo1.start();
                    hilo2.start();
            }catch(Exception e){}
        
    }
    
    public static void main(String args[]){
        String numero;
        int valor;
        int i;
        System.out.println("Ingresa el numero de partidas: ");
        Scanner teclado=new Scanner(System.in);
        numero=teclado.nextLine();
        valor=Integer.parseInt(numero);
        for(i=0; i<valor; i++){
            inicio(i+1);
        }
    }
}

