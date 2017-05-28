/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author d13
 */
public class c_ManagerFile
{
    //DATOS MAESTRO
    //MAESTRO
    int Llave = 0; //Lave
    char Antecedente;  //Municipio Antecedente
    char Consecuente; //Municipio destino
    float Distancia;//Distancia
    float Costo;//Costo
    
    //INDEX 
    long Pointer=0;
    
    
    File Maestro = new File("Maestro");
    File Indice = new File("Indice");
    RandomAccessFile Master=null;
    RandomAccessFile Indx=null;
    
    private Scanner Scn= new Scanner(System.in);
    
    c_ManagerFile()
    {
        try
        {
            Master=new RandomAccessFile(Maestro,"rw");
            Indx=new RandomAccessFile(Indice,"rw");
        }
        catch(IOException IOE)
        {
            System.out.println("No es posible crear los archvos binario "+Maestro.getName()+" y "+Indice.getName());
        }
    }
    
    public void m_writeMaster(char Antecedente,char Relacion,char Consecuente) throws IOException
    {
        //POSICIONA EL PUNTERO AL FINAL DE ULTIMO RECURSO
        Master.seek(Master.length());
        //BOOLEAN; SI ES LEGIBLE
        Master.writeChar(' ');

        //ESCRIBE LLAVE
        Master.writeInt(Llave);

        //ESCRIBE ANTECEDENTE
        Master.writeChar(Antecedente);

        //ESCRIBE RELACION
        Master.writeChar(Relacion);

        //ESCRIBE CONSECUENTE
        Master.writeChar(Consecuente);

        /********************************
                ESCRIBE INDICE
        ********************************/
        //ESCRIBE CAMPO BOOLEANO DE CAMPO DE LEGIBILIDA DEL REGISTRO
        Indx.seek(Indx.length());
        Indx.writeChar(' ');

        //ESCRIBE LLAVE
        Indx.writeInt(Llave);
        Llave++;

        //ESCRIBE DIRECCION LOGICA

        Indx.writeLong(Pointer);
        Pointer=Master.getFilePointer();
    }
    
    public void m_updateTable(JTable TR,JComboBox Cb)
    {
        try
        {
            //TR.removeRowSelectionInterval(0, TR.getRowCount());
            
            long ap_actual=0, ap_final;
            DefaultTableModel TMR = (DefaultTableModel)TR.getModel();
            m_clenaJTable(TMR);
            Cb.removeAllItems();
            ap_final=Master.length();
            Master.seek(0);
            while(ap_actual!=ap_final)
            {
                //LEER MAESTRO
                //BOOLEAN REGISTRO LEGIBLE
                if (Master.readChar() == ' ')
                {
                    int Llv=Master.readInt();
                    char Ant=Master.readChar();
                    char Rel=Master.readChar();
                    char Con=Master.readChar();
                    TMR.addRow(new Object[] {Llv,Ant,Rel,Con});
                    Cb.addItem(Ant+""+Rel+""+Con);
                }
                else
                {
                    Master.readInt();
                    Master.readChar();
                    Master.readChar();
                    Master.readChar();
                }
                Llave++;
                ap_actual=Master.getFilePointer();
            }//Fin while
        }catch (IOException IOE)
        {
            
        }
        
    }
    
    void m_clenaJTable(DefaultTableModel DTB)
    {
        int Rows =DTB.getRowCount();
        for(; Rows!=0;Rows--)
        {
            DTB.removeRow(Rows-1);
        }
    }
    
    
    public void m_deleteRule(int inxRule) throws IOException
    {
        int Llave = 0;
        LinkedList VD = new LinkedList();
        long ir,er,ii,ei;//inicio del registro;
        LinkedList Dis = new LinkedList();
        LinkedList Cos = new LinkedList();
        //LEER MAESTRO E INDICE
        //POSICIONA EL PUNTERO AL INICIO DEL ARCHIVO
        Master.seek(0);
        Indx.seek(0);
        
        while(Master.getFilePointer()!=Master.length())
        {
            //LEER UN REGISTRO COMPLETO MAESTRO
            ir =Master.getFilePointer();//Respaldo del puntero de donde inicia el booleano de si es legible el registro
            char DispM = Master.readChar();
            
            Llave = Master.readInt();
            
            char A = Master.readChar();
            
            char C = Master.readChar();
            
            er = Master.getFilePointer();
            
            //LLER INDICE 
            ii =Indx.getFilePointer();//Respaldo del puntero de donde inicia el booleano de si es legible el registro
            char DisPI = Indx.readChar();
            
            Llave = Indx.readInt();
            
            long DL = Indx.readLong();
            
            ei = Indx.getFilePointer();
            
            //OBTENER DATOS DE LOS REGISTROS MARCADOS COMO BORRADOS
            if (inxRule==Llave)
            {
                //Coloca al puntero en la posicion de inicio del registro para sobres escribir el booleano
                Master.seek(ir);
                Master.writeChar('#');
                
                Indx.seek(ii);
                Indx.writeChar('#');
            }
            Master.seek(er);
            Indx.seek(ei);
        }
    }
}
