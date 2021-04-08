
package juegogato;
import java.io.*;
import java.util.Scanner;

public class JuegoGato {
    PipedInputStream in[];
    PipedOutputStream out[];
    hilos h[];
    public void main(String[] args) {
        String numero;
        int valor;
        System.out.println("Ingresa el numero de partidas: ");
        Scanner teclado=new Scanner(System.in);
        numero=teclado.nextLine();
        valor=Integer.parseInt(numero);
        int i;
        for(i=0; i<valor*2; i++){
            try{
            out[i]=new PipedOutputStream();
            in[i]=new PipedInputStream(out[i]);
            for(i=0; i<valor*2; i++){
                if(i%2==0){
                h[i]=new hilos(in[i], out[i+1]);
            }
            else
                h[i]=new hilos(in[i], out[i-1]);
            }
            for(i=0; i<valor*2; i++){
                h[i].start();
            }
            }catch(Exception e){}
        }
    }
    
}
