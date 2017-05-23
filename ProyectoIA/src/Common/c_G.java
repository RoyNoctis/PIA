/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.util.LinkedList;

/**
 *
 * @author d13
 */
public class c_G
{
    
    private String A[][];
    private LinkedList V ;
    
    public void m_UpdateGrafo(String aristas[][],LinkedList vertices)
    {
        A=aristas;
        V=vertices;
    }
    
    public boolean m_searchV(String Vertice)
    {
        if (V.contains(Vertice)) {
            return true;
        }
        return false;
    }
    
    public boolean m_hasSons(String Padre)
    {
        int i = 0;
        for (int p = 0; p < A.length; p++)
        {
            if (A[p][0].equals(Padre))
            {
                return true;
            }
        }
        return true;
    }
    
    private int indexA=0;
    public String m_getSons(String Padre)
    {
        for (;indexA < A.length; indexA++)
        {
            String I=A[indexA][0];
            if (A[indexA][0].equals(Padre))
            {
                I=A[indexA][1];
                
                return I;
            }
        }
        return "Fin";
    }
    
    public void m_incIndexA()
    {
        indexA++;
    }
    
    public void m_resetIndexA()
    {
        indexA=0;
    }
    
    
    private LinkedList Sons;
    public void m_getListSons(String R)//R=raiz
    {
        Sons = new LinkedList();
        for (int i = 0; i < A.length; i++)
        {
            if (A[i][0].equals(R))
            {
                Sons.offer(A[i][1]);
            }
        }
    }


    public String m_getCoste(String n,String q)
    {
        for (int i = 0; i < A.length; i++)
        {
            if (A[i][0].equals(n) && A[i][1].equals(q))
            {
                return A[i][3].toString();
            }
        }
        return "0";
    }
    
    public String m_getDistance(String n,String q)
    {
        for (int i = 0; i < A.length; i++)
        {
            if (A[i][0].equals(n) && A[i][1].equals(q))
            {
                return A[i][2].toString();
            }
        }
        return "0";
    }
}
