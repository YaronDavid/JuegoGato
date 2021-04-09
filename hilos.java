
package juegogato;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class hilos extends JFrame implements Runnable, ActionListener{
    DataInputStream entrada;
    DataOutputStream salida;
    int turno;
    int Jugador;
    JPanel JP, JC, JL1, JL2, JL3, JBB;
    JLabel JL, JT, JJ;
    JButton JB[];
    boolean b;
    public hilos(InputStream in, OutputStream out, int turno, int CirTac, int Juego){
        int i;
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
                JB[i]=new JButton("");
                JB[i].addActionListener(this);
                JBB.add(JB[(i)]);
        }
        JP.setLayout(new GridLayout(6,1));
        getContentPane().add(JP);
        setSize(400,500); setVisible(true);
        JL1.setSize(350, 100);
        JP.add("Center", JL1);
        JP.add("Center", JL2);
        JP.add("Center", JL3);
        JP.add("Center", JBB);
    }
    @Override
    public void run(){
        boolean bandera=true;
        int t, coordenada=0, i;
        b=bandera;
        while(b){
            try {
                coordenada=entrada.readInt();
                t=entrada.readInt();
                turno=t;
            } catch (IOException ex) {
                Logger.getLogger(hilos.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(i=0; i<9; i++){
                if(i==coordenada){
                    if(turno==1)
                        JB[i].setText("X");
                    else
                        JB[i].setText("O");
                }
            }
            if(turno==0){
                JT.setText("Turno Tache");
            }else if(turno==1){
                JT.setText("Turno Circulo");
            }
            if(verificarGanador()==1){
                JOptionPane.showMessageDialog(null, "Ganador X");
                bandera=false;
                setVisible(false);
            }
            if(verificarGanador()==2){
                JOptionPane.showMessageDialog(null, "Ganador O");
                bandera=false;
                setVisible(false);
            }
            if(verificarEmpate()==1){
                JOptionPane.showMessageDialog(null, "Empate");
                bandera=false;
                setVisible(false);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        JButton j=(JButton)e.getSource();
        int i;
        if(Jugador==turno && j.getText().equals("")){
            if(Jugador==0){
                j.setText("X");
                JT.setText("Turno Circulo");
                for(i=0; i<9; i++){
                    if(j.equals(JB[i])){
                        try {
                            salida.writeInt(i);
                            salida.flush();
                        } catch (IOException ex) {
                            Logger.getLogger(hilos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                try {
                    salida.writeInt(1);
                    salida.flush();
                    turno=1;
                } catch (IOException ex) {
                    Logger.getLogger(hilos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                j.setText("O");
                JT.setText("Turno Tache");
                for(i=0; i<9; i++){
                    if(j.equals(JB[i])){
                        try {
                            salida.writeInt(i);
                            salida.flush();
                        } catch (IOException ex) {
                            Logger.getLogger(hilos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                try {
                    salida.writeInt(0);
                    salida.flush();
                    turno=0;
                } catch (IOException ex) {
                    Logger.getLogger(hilos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(verificarGanador()==1){
                b=false;
                setVisible(false);
            }
            if(verificarGanador()==2){
                b=false;
                setVisible(false);
            }
                if(verificarEmpate()==1){
                b=false;
                setVisible(false);
            }
        }
    }
    public int verificarGanador(){
        String[] casillas= new String[9];
        int i;
        casillas=new String[9];
        for(i=0; i<9; i++){
            casillas[i]=JB[i].getText();
        }
        if(casillas[0].equals(casillas[3]) && casillas[0].equals(casillas[6])){
            if(casillas[0].equals("X")){
                return 1;
            }
            if(casillas[0].equals("O")){
                return 2;
            }
        }
        if(casillas[1].equals(casillas[4]) && casillas[1].equals(casillas[7])){
            if(casillas[1].equals("X")){
                return 1;
            }
            if(casillas[1].equals("O")){
                return 2;
            }
        }
        if(casillas[2].equals(casillas[5]) && casillas[2].equals(casillas[8])){
            if(casillas[2].equals("X")){
                return 1;
            }
            if(casillas[2].equals("O")){
                return 2;
            }
        }
        if(casillas[0].equals(casillas[1]) && casillas[0].equals(casillas[2])){
            if(casillas[0].equals("X")){
                return 1;
            }
            if( casillas[0].equals("O")){
                return 2;
            }
        }
        if(casillas[3].equals(casillas[4]) && casillas[3].equals(casillas[5])){
            if(casillas[3].equals("X")){
                return 1;
            }
            if(casillas[3].equals("O")){
                return 2;
            }
        }
        if(casillas[6].equals(casillas[7]) && casillas[6].equals(casillas[8])){
            if(casillas[6].equals("X")){
                return 1;
            }
            if(casillas[6].equals("O")){
                return 2;
            }
        }
        if(casillas[0].equals(casillas[4]) && casillas[0].equals(casillas[8])){
            if(casillas[0].equals("X")){
                return 1;
            }
            if(casillas[0].equals("O")){
                return 2;
            }
        }
        if(casillas[2].equals(casillas[4]) && casillas[2].equals(casillas[6])){
            if(casillas[2].equals("X")){
                return 1;
            }
            if(casillas[2].equals("O")){
                return 2;
            }
        }
        return 0;
    }
    
    public int verificarEmpate(){
        int i;
        for(i=0; i<9; i++){
            if(JB[i].getText().equals("")){
                return 0;
            }
        }
        return 1;
    }
}

