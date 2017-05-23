/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author d13
 */
public class ProyectoIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        Menu m = new Menu();
        c_ManagerFile Manager = new c_ManagerFile();
        String Opcion;
        Scanner Scn =new Scanner(System.in);
        System.out.println(
                          "1.-Crear nueva Regla\n"
                        + "2.-Modificar Regla(s)\n"
                        + "3.-Eliminar Regla(s)\n");
        System.out.print("Dame Opcion:");
        Opcion=Scn.next();
        switch (Opcion)
        {
            case "0":
                System.out.println("\n<<<0.-LECTURA SECUENCIAL>>>");
                Manager.leer_secuencial_maestro();
                break;
            
            case "1":
                System.out.println("\n<<<1.-ESCRITURA DE ARCHIVOS>>>");
                Manager.escribir_Arch_Maestro();
                break;
            
            case "3":
                System.out.println("\n<<<1.-ESCRITURA DE ARCHIVOS>>>");
                Manager.m_deleteNode();
                break;
        }
    }
    
    
}
