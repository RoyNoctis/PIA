/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package O;

import Common.c_G;
import proyectoia.*;
import java.util.LinkedList;

/**
 *
 * @author d13
 */
public class Tabla_A
{
    //DATOS DEL REGISTRO
    private LinkedList Nodo;//Nodo
    private LinkedList Father;//Padre
    private LinkedList CI;//Costo para el inicial
    private LinkedList DI;//Dsitancia para el inicial
    private LinkedList Sons;//Hijos
    
    public Tabla_A()
    {
        this.Nodo = new LinkedList();//Nodo
        this.Father = new LinkedList();//Padre
        this.CI = new LinkedList();//Costo para el inicial
        this.DI = new LinkedList();//Distancia para el inicial
        this.Sons = new LinkedList();//Hijos
    }
    
    public void newReg(String Nodo)
    {
        this.Nodo.offer(Nodo);
        this.Father.offer("-");
        this.CI.offer("0");//Costo para el inicial
        this.DI.offer("0");//Distacia para el inicial
        this.Sons.offer(-1);
    }
    
    public LinkedList getListNodo()
    {
        return this.Nodo;
    }
    
    public LinkedList getListFather()
    {
        return this.Father;
    }
    
    public LinkedList getListCI()
    {
        return this.CI;
    }
    
    public LinkedList getListDI()
    {
        return this.DI;
    }
    
    public LinkedList getListSons()
    {
        return this.Sons;
    }
    
    public String getFather()
    {
        String F= "";
        F=this.Father.get(Index).toString();
        return F;
    }

    /**
     * 
     * @SETS PARA UN NUEVO REGISTRO
     */
    public void setNodo(String Nodo)
    {
        this.Nodo.set(this.Nodo.size()-1, Nodo);
    }
    
    public void setFather(String Father)
    {
        this.Father.set(this.Father.size()-1, Father);
    }
    
    public void setCI(String CI)
    {
        this.CI.set(this.CI.size()-1, CI);
    }
    
    public void setDI(String DI)
    {
        this.DI.set(this.DI.size()-1, DI);
    }
    
    public void setSons(LinkedList Sons)
    {
        LinkedList temp = new LinkedList(Sons);
        this.Sons.set(Index, temp);
    }
    
    /**
     * @EXIST
     */
    private int Index=0;
    public boolean existInTable(String Nodo)
    {
        if (!this.Nodo.isEmpty())
        {
            if (this.Nodo.contains(Nodo))
            {
                Index=this.Nodo.indexOf(Nodo);
                return true;
            }
        }

        
        this.newReg(Nodo);
        Index=this.Nodo.size()-1;
        return false;
    }
    
    public boolean existInCerrados(String Nodo)
    {
        if (!this.Nodo.isEmpty())
        {
            if (this.Nodo.contains(Nodo))
            {
                Index=this.Nodo.indexOf(Nodo);
                return true;
            }
        }
        return false;
    }
    
    /**
     * @CostoNetoPAdre
     */
    public String getCosteFather(String n)
    {
        int Index=Nodo.indexOf(n);
        String Costo = (String)CI.get(Index);
        return Costo;
    }
    
    public String getCI(String n)
    {
        int Index=Nodo.indexOf(n);
        String Costo = (String)CI.get(Index);
        return Costo;
    }
    
    /**
     * @DistanceNetoPAdre
     */
    public String getDistanceFather(String n)
    {
        int Index=Nodo.indexOf(n);
        String Distancia = (String)DI.get(Index);
        return Distancia;
    }
    
    
    /**
     * @param p
     * @param n
     * @param costepn
     * @RECTIFICAR
     */
    //n=padre
    //p=hijo
    public void RectificarCosto(String n,String p,double costepn,c_G Grafo)
    {
        int in = Nodo.indexOf(n);
        double cn = Double.valueOf((String)CI.get(in));
        //OBTENER COSTE DE POSSIBLE MOD
        int ip = Nodo.indexOf(p);
        double cp = Double.valueOf((String)CI.get(ip));
        if ((cn+costepn)<cp)
        {
            this.Father.set(ip,n);
            this.CI.set(ip, String.valueOf(cn+costepn));
            this.RectificarListaC(p,Grafo);
        }
    }
    
    /**
     * @param p
     * @param n
     * @param distancepn
     * @RECTIFICAR
     */
    //n=padre
    //p=hijo
    public void RectificarDistancia(String n,String p,double distancepn,c_G Grafo)
    {
        int in = Nodo.indexOf(n);
        double cn = Double.valueOf((String)DI.get(in));
        //OBTENER COSTE DE POSSIBLE MOD
        int ip = Nodo.indexOf(p);
        double cp = Double.valueOf((String)DI.get(ip));
        if ((cn+distancepn)<cp)
        {
            this.Father.set(ip,n);
            this.DI.set(ip, String.valueOf(cn+distancepn));
            this.RectificarListaD(p,Grafo);
        }
    }
    
    public void RectificarListaC(String n,c_G Grafo)
    {
        LinkedList S = new LinkedList();
        String son=Grafo.m_getSons(n);
        int IF = Nodo.indexOf(n);
        String Father = this.Father.get(IF).toString();
        while(!son.equals("Fin"))
        {
            if (!Father.equals(son))
            {
                S.offer(son);
            }
            Grafo.m_incIndexA();
            son=Grafo.m_getSons(n);
        }
        Grafo.m_resetIndexA();
        while(S.size()>0)
        {
            String q = S.poll().toString();
            if (this.existTableA(q))
            {
                double cnp = Double.valueOf(Grafo.m_getCoste(n, q));
                this.RectificarCosto(n, q, cnp, Grafo);
            }          
        }
        
    }
    
    public void RectificarListaD(String n,c_G Grafo)
    {
        LinkedList S = new LinkedList();
        String son=Grafo.m_getSons(n);
        int IF = Nodo.indexOf(n);
        String Father = this.Father.get(IF).toString();
        while(!son.equals("Fin"))
        {
            if (!Father.equals(son))
            {
                S.offer(son);
            }
            Grafo.m_incIndexA();
            son=Grafo.m_getSons(n);
        }
        Grafo.m_resetIndexA();
        while(S.size()>0)
        {
            String q = S.poll().toString();
            if (this.existTableA(q))
            {
                double dnp = Double.valueOf(Grafo.m_getCoste(n, q));
                this.RectificarDistancia(n, q, dnp, Grafo);
            }          
        }
        
    }
    /**
     * Existe_en_la_tabla_A_el_nodo
     * @param N
     * @return 
     */
    public boolean existTableA(String N)
    {
        if (Nodo.contains(N))
        {
            return true;
        }
        return false;
    }

    public void getWay(String O, String D,char type)
    {
        LinkedList Way = new LinkedList();
        String S;
        String F;
        int i = Nodo.indexOf(D);//Obtiene INDICE de padres
        double peso=0;
        if (type == 'C') {
            peso= Double.valueOf((String)CI.get(i));
        }
        if (type == 'D') {
            peso = Double.valueOf((String)DI.get(i));
        }
        do {
            S = (String) this.Nodo.get(i);
            F = (String) this.Father.get(i);
            Way.offer("("+F+","+S+")");
            D=F;
            i = Nodo.indexOf(D);//Obtiene INDICE depadres
        } while (!S.equals(O));
        if (type=='C') {
            System.out.println("Costo:"+peso);
        }
        if (type=='D') {
            System.out.println("Distancia:"+peso);
        }
        System.out.println("Camino:");
        while(Way.peek()!=null)
        {
            System.out.println(Way.pollLast()); //IMPRIME PARES ORDENADOS
        }
    }
    
        public void m_showTableA(Tabla_A TablaA)
    {
        String Title[]="Nodo,Padre,DI,CI,Sucesores".split(",");
        for (int i = 0; i < 5; i++)
        {
            int length = 12 -Title[i].length();
            for (int j = 0; j < length; j++) {
                Title[i]+=" ";
            }
            System.out.print(Title[i]);
        }
        System.out.println("");
        
        LinkedList Nodo = TablaA.getListNodo();
        LinkedList Padre = TablaA.getListFather();
        LinkedList DI = TablaA.getListDI();
        LinkedList CI = TablaA.getListCI();
        LinkedList Sucesores = TablaA.getListSons() ;
        
        int lengthList = Nodo.size();
        String wordN,wordF,wordDI,wordCI,wordS;
        LinkedList ListS;
        for (int i = 0; i < lengthList; i++)
        {
            
            wordN = Nodo.get(i).toString();
            wordN = this.m_formatTable(wordN);
            
            wordF = Padre.get(i).toString();
            wordF = this.m_formatTable(wordF);
            
            wordDI = DI.get(i).toString();
            wordDI = this.m_formatTable(wordDI);
            
            wordCI = CI.get(i).toString();
            wordCI = this.m_formatTable(wordCI);
            
            ListS = (LinkedList) Sucesores.get(i);
            wordS = this.m_formatSons(ListS);
            
            System.out.println(wordN+wordF+wordDI+wordCI+wordS);
        }
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
    
    public void m_showTableAs(Tabla_A TablaA)
    {
        String Title[]="Nodo,Padre,DI,CI,Sucesores".split(",");
        for (int i = 0; i < 5; i++)
        {
            int length = 12 -Title[i].length();
            for (int j = 0; j < length; j++) {
                Title[i]+=" ";
            }
            System.out.print(Title[i]);
        }
        System.out.println("");
        
        LinkedList Nodo = TablaA.getListNodo();
        LinkedList Padre = TablaA.getListFather();
        LinkedList DI = TablaA.getListDI();
        LinkedList CI = TablaA.getListCI();
        LinkedList Sucesores = TablaA.getListSons() ;
        
        int lengthList = Nodo.size();
        String wordN,wordF,wordDI,wordCI,wordS;
        LinkedList ListS;
        for (int i = 0; i < lengthList; i++)
        {
            
            wordN = Nodo.get(i).toString();
            wordN = this.m_formatTable(wordN);
            
            wordF = Padre.get(i).toString();
            wordF = this.m_formatTable(wordF);
            
            
            wordCI = CI.get(i).toString();
            wordCI = this.m_formatTable(wordCI);
            
            System.out.println(wordN+wordF+wordCI);
        }
    }
    
    private String m_formatSons(LinkedList L)
    {
        if (L.size()>0)
        {
            String W="";
            while(L.size()>1)
            {
                W+=L.poll().toString()+",";
            }
            W+=L.poll().toString();
            return W;
        }
        else{
            return "0";
        }
    }
}




