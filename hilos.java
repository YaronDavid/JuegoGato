
package juegogato;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;


public class hilos extends JFrame implements Runnable, ActionListener{
    DataInputStream entrada;
    DataOutputStream salida;
    int turno;
    int Jugador;
    JPanel JP, JC, JL1, JL2, JL3, JBB;
    JLabel JL, JT, JJ;
    JButton JB[];
    public hilos(InputStream in, OutputStream out, int turno, int CirTac, int Juego){
        int i=0, j=0;
        entrada=new DataInputStream(in);
        salida=new DataOutputStream(out);
        this.turno=turno;
        Jugador=CirTac;
        JP=new JPanel();
        JC=new JPanel();
        JL1=new JPanel();
        JL2=new JPanel();
        JL3=new JPanel();
        JBB=new JPanel();
        JBB.setLayout(new GridLayout(3,3));
        if(Jugador==0){
            JL=new JLabel("Tablero Tache");
        }
        else{
            JL=new JLabel("Tablero Circulo");
        }
        if(turno==0){
            JT=new JLabel("Turno Tache");
        }
        else{
            JT=new JLabel("Turno Circulo");
        }
        JJ=new JLabel("Juego "+ Juego);
        JB=new JButton[9];
        JL.setFont(new java.awt.Font("Times New Roman", 3, 24));
        JT.setFont(new java.awt.Font("Times New Roman", 3, 24));
        JJ.setFont(new java.awt.Font("Times New Roman", 3, 24));
        JL1.add(JL);
        JL2.add(JT);
        JL3.add(JJ);
        for(i=0; i<9; i++){
                JB[i]=new JButton("   ");
                JBB.add(JB[(i)]);
        }
        JP.setLayout(new GridLayout(5,1));
        getContentPane().add(JP);
        setSize(400,500); setVisible(true);
        JL1.setSize(350, 100);
        JP.add("Center", JL1);
        JP.add("Center", JL2);
        JP.add("Center", JL3);
        JP.add("Center", JBB);
    }
    public void run(){
        
    }
    
    public void actionPerformed(ActionEvent e){
        
    }
}

