/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

/**
 *
 * @author Reaper
 */
public class Menu extends JFrame implements ActionListener{
    
    JFrame fPrincipal;
    JLabel lblMensaje;
    JLabel lblImagen;
    JButton btnAgrega;
    JButton btnSE;
    JButton btnSalir;
    JMenu mnuArchivo;
    JMenuBar mnuPrincipal;
    
    public Menu()
    {
        fPrincipal = new JFrame();
        fPrincipal.setLocation(400, 100);
        fPrincipal.setSize(600, 400);
        fPrincipal.setLayout(null);
        fPrincipal.setTitle("SISTEMA EXPERTO ACERCA DE ANFIBIOS");
        lblMensaje = new JLabel();
        lblMensaje.setText("BIENVENIDOS");
        lblMensaje.setBounds(180, 30, 200, 50);
        lblMensaje.setFont(new Font("Arial",1,28));
        lblImagen = new JLabel(new ImageIcon("src/IA.jpg"));
        lblImagen.setBounds(125,55,300,250);
        btnAgrega = new JButton();
        btnAgrega.setText("Agregar nueva Regla");
        btnAgrega.setBounds(10,300,175,20);
        btnSE = new JButton();
        btnSE.setText("Sistema Experto");
        btnSE.setBounds(200,300,175,20);
        btnSalir = new JButton();
        btnSalir.setText("Salir del Sistema");
        btnSalir.setBounds(390,300,175,20);
        btnSalir.addActionListener(this);
        fPrincipal.add(lblMensaje);
        fPrincipal.add(lblImagen);
        fPrincipal.add(btnAgrega);
        fPrincipal.add(btnSE);
        fPrincipal.add(btnSalir);
        fPrincipal.setVisible(true);
        fPrincipal.setResizable(false);
        fPrincipal.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String txtAction  = e.getActionCommand();
        
        switch(txtAction)
        {
            case "Salir del Sistema":
            {
                System.exit(0);
            }break;
        }
    }
}
