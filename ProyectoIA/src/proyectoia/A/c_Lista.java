/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.A;

import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author d13
 */
public class c_Lista {
    private LinkedList Nodo;
    private LinkedList Father;
    private LinkedList f;
    private LinkedList g;
    private LinkedList h;
    
    public c_Lista()
    {
        Nodo = new LinkedList();
        Father  = new LinkedList();
        f  = new LinkedList();
        g  = new LinkedList();
        h  = new LinkedList();
    }
    
    public void m_setFather(String N,String father)
    {
        int i = this.Nodo.indexOf(N);
        this.Father.set(i, father);
    }
    
    public void m_setF(String N,double f)
    {
        int i = this.Nodo.indexOf(N);
        this.f.set(i, f);
    }
    
    public void m_setG(String N,double g)
    {
        int i = this.Nodo.indexOf(N);
        this.g.set(i, g);
    }
    
    public void m_setH(String N,double h)
    {
        int i = this.Nodo.indexOf(N);
        this.h.set(i, h);
    }
    
    public void m_addReg(String Nodo,String Father,double f, double g, double h)
    {
        this.Nodo.add(Nodo);
        this.Father.add(Father);
        this.f.add(f);
        this.g.add(g);
        this.h.add(h);
    }
    
    public void m_addReg(LinkedList Reg)
    {
        this.Nodo.add(Reg.poll());
        this.Father.add(Reg.poll());
        this.f.add(Reg.poll());
        this.g.add(Reg.poll());
        this.h.add(Reg.poll());
    }
    
    public LinkedList m_delReg(String Nodo)
    {
        LinkedList RD = new LinkedList();//lista dedicada a almacenar los capos del registro a eliminar
        int i = this.Nodo.indexOf(Nodo);
        RD.offer(this.Nodo.remove(i));
        RD.offer(this.Father.remove(i));
        RD.offer(this.f.remove(i));
        RD.offer(this.g.remove(i));
        RD.offer(this.h.remove(i));
        
        return RD;
        
    }
    
    public int m_getSize()
    {
        return this.Nodo.size();
    }
    
    public String m_getEA()
    {
        return this.Nodo.peek().toString();
    }
    
    public char m_getFather(String Nodo)
    {
        int i = this.Nodo.indexOf(Nodo);
        char F = this.Father.get(i).toString().charAt(0);
        return F;
    }
    
    public double m_getG(String N)
    {
        int iN = this.Nodo.indexOf(N);
        double g =  Double.valueOf(this.g.get(iN).toString());
        return g;
    }
    
    public boolean m_exist(String Nodo)
    {
        return this.Nodo.contains(Nodo);
    }
    
    public void m_sort()
    {
        LinkedList Orden = new LinkedList();
        while (this.Nodo.size()>0)
        {
            String Reg="";
            Reg+=this.f.get(0).toString()+",";
            Reg+=this.Nodo.get(0).toString()+",";
            Reg+=this.Father.get(0).toString()+",";
            Reg+=this.g.get(0).toString()+",";
            Reg+=this.h.get(0).toString();
            Orden.add(Reg);
            
            //Remueve Registro completo de las listas (Todas a la Par)
            this.Nodo.poll();
            this.Father.poll();
            this.f.poll();
            this.g.poll();
            this.h.poll();
        }
        //Ordenar "Orden" por f en acendencia
        Orden.sort(Comparator.naturalOrder());

        //Restaurar registros de
        /*
        >NODO
        >FATHER
        >F
        >G
        >H
        */
        do {
            String Reg[] = Orden.poll().toString().split(",");
            
            this.f.add(Reg[0]);
            this.Nodo.add(Reg[1]);
            this.Father.add(Reg[2]);
            this.g.add(Reg[3]);
            this.h.add(Reg[4]);
        } while (!Orden.isEmpty());
    }
    
    public void m_getWay(String O,String D)
    {
        String Title[]="Nodo,Padre,f,g,h".split(",");
        LinkedList Table = new LinkedList();
        String Reg = "",F,Titles=""; 
        int i = this.Nodo.indexOf(D);
        do {
            Reg+=D+",";
            F=this.Father.get(i).toString();
            Reg+=F+",";
            Reg+=this.f.get(i)+",";
            Reg+=this.g.get(i)+",";
            Reg+=this.h.get(i)+",";
            Table.add(Reg);
            Reg="";
            i = this.Nodo.indexOf(F);
            D=F;
        } while (!D.equals(O));
        Reg+=D+",";
        F=this.Father.get(i).toString();
        Reg+=F+",";
        Reg+=this.f.get(i)+",";
        Reg+=this.g.get(i)+",";
        Reg+=this.h.get(i)+",";
        Table.add(Reg);
        Reg="";
        System.out.println("");
        
        //Mostrar Tabla (Titulo)
        for (int j = 0; j < 5; j++)
        {
            Titles+=this.m_formatTable(Title[j]);
        }
        System.out.println(Titles);
        
        //Mostrar Tabla (Contenido)
        do {
            String R[] = Table.pollLast().toString().split(",");
            for (int k = 0; k < R.length; k++)
            {
                Reg+= this.m_formatTable(R[k]);
            }
            System.out.println(Reg);
            Reg="";
        } while (Table.size()>0);
        
    }
    
    private String m_formatTable(String word)
    {
        int length = 12 - word.length();
        for (int i = 0; i < length; i++)
        {
            word+=" ";
        }
        return word;
    }
    
    
}
