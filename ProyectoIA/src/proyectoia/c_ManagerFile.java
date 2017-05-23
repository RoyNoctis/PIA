/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import Common.c_G;
import O.Tabla_A;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import proyectoia.A.c_Lista;

/**
 *
 * @author d13
 */
public class c_ManagerFile
{
    //DATOS MAESTRO
    //MAESTRO
    int Llave = 0; //Lave
    String Antecedente;  //Municipio Antecedente
    String Consecuente; //Municipio destino
    float Distancia;//Distancia
    float Costo;//Costo
    
    //INDEX 
    long Pointer=0;
    
    
    File Maestro = new File("Maestro");
    File Indice = new File("Indice");
    RandomAccessFile Master=null;
    RandomAccessFile Indx=null;
    
    private Scanner Scn= new Scanner(System.in);
    private c_G Grafo;
    private c_Lista ABIERTOS,CERRADOS;
    
    c_ManagerFile()//public void m_Open()
    {
        try{
            Master=new RandomAccessFile(Maestro,"rw");
            Indx=new RandomAccessFile(Indice,"rw");
        }catch(IOException IOE)
        {
            System.out.println("No es posible crear los archvos binario "+Maestro.getName()+" y "+Indice.getName());
        }
    }
    
    public void  escribir_Arch_Maestro() throws IOException
    {
        String Other;
        System.out.println("---PIDE DATOS DE MAESTRO---");
        
        do{
            //BOOLEAN; SI ES LEGIBLE
            Master.writeChar(' ');
            
            //ESCRIBE LLAVE
            Master.writeInt(Llave);

            //PIDE ORIGEN
            System.out.print("* Antecendente:");
            Antecedente=Scn.next();

            //ESCRIBE ORIGEN
            Master.writeChars(Antecedente);
            
            //PIDE DESTINO
            System.out.print("* Consecuente:");
            Consecuente=Scn.next();

            //ESCRIBE DESTINO
            Master.writeChar('>');
            Master.writeChars(Consecuente);
            
            //PIDE DISTANCIA
            System.out.print("* Distancia:");
            Distancia=Scn.nextFloat();
            
            //ESCRIBE DISTANCIA
            Master.writeFloat(Distancia);
            
            //PIDE COSTO
            System.out.print("* Costo:");
            Costo=Scn.nextFloat();
            
            //ESCRIBE COSTO
            Master.writeFloat(Costo);
            
            /********************************
                    ESCRIBE INDICE
            ********************************/
            //ESCRIBE CAMPO BOOLEANO DE CAMPO DE LEGIBILIDA DEL REGISTRO
            Indx.writeChar(' ');
            
            //ESCRIBE LLAVE
            Indx.writeInt(Llave);
            Llave++;
            
            //ESCRIBE DIRECCION LOGICA
            
            Indx.writeLong(Pointer);
            Pointer=Master.getFilePointer();
            
            /*********************************************
                        ESCRIBE CAMINO DE REGREOS 
            **********************************************/
            //BOOLEAN; SI ES LEGIBLE
            Master.writeChar(' ');
            
            //ESCRIBE LLAVE
            Master.writeInt(Llave);

            //ESCRIBE  ORIGEN DE REGRESO
            Master.writeChars(Consecuente);

            //ESCRIBE DESTINO DE REGRESO
            Master.writeChars(Antecedente);
            
            //ESCRIBE DISTANCIA
            Master.writeFloat(Distancia);
            
            //ESCRIBE COSTO
            Master.writeFloat(Costo);
            
            /********************************
                    ESCRIBE INDICE
            ********************************/
            //ESCRIBE CAMPO BOOLEANO DE CAMPO DE LEGIBILIDA DEL REGISTRO
            Indx.writeChar(' ');
            
            //ESCRIBE LLAVE
            Indx.writeInt(Llave);
            Llave++;
            
            //ESCRIBE DIRECCION LOGICA
            Indx.writeLong(Pointer);
            Pointer=Master.getFilePointer();
            //*********************************************
            //PEDIR OTRO REGISTRO
            System.out.println("¿OTRO REGISTRO? : SI=1, NO=O ");
            Other=Scn.next();
        }while (!Other.equals("NO"));
        Master.close();
        Indx.close();
    }
    
    public void leer_secuencial_maestro() throws IOException
    {
        long ap_actual, ap_final;
        while((ap_actual=Master.getFilePointer())!=(ap_final=Master.length()))
        {
            //LEER MAESTRO
            System.out.println("***MAESTRO***");
            //BOOLEAN REGISTRO LEGIBLE
            if (Master.readChar() == ' ')
            {
                System.out.println("√");
            }
            else
            {
                System.out.println("X");
            }
                
            //LEER CLAVE
            
            System.out.println("Ll:"+Master.readInt());

            //LEER ESTACION ORIGEN
            System.out.println("O:"+Master.readChar());

            //LEER ESTACION DESTINO
            System.out.println("D:"+Master.readChar());

            //LEER DISTANCIA
            System.out.println("Di:"+Master.readFloat());

            //LEER COSTO
            System.out.println("C:"+Master.readFloat());

            //LEER INDICE
            System.out.println("***INDICE***");
            if (Indx.readChar() == ' ')
            {
                System.out.println("√");
            }
            else
            {
                System.out.println("X");
            }
            //LEE CLAVE DE REGISTRO EN INDICE
            System.out.println("Clave:"+Indx.readInt());

            //LEER DIRECCION LOGICA
            //DIRECCION LOGICA
            System.out.println("DL:"+Indx.readLong());
            System.out.println("");
            
        }//Fin while
        Master.close();
        Indx.close();
    }
    
    private void m_sortFile() throws IOException
    {
        //LEER MAESTRO E INDICE GENERANDO G(V,A)
        //POSICIONA EL PUNTERO AL INICIO DEL ARCHIVO
        Master.seek(0);
        Indx.seek(0);
        
        //ORGANIZAR DATOS
        Vector<String> VT = new Vector<>();
        //LEE EL ARCHIVO MAESTRO 
        while(Master.getFilePointer()!=Master.length())
        {
            //LEER UNREGISTRO COMPLETO
            String Disp = String.valueOf(Master.readChar());
            String Ll = String.valueOf(Master.readInt());
            String O = String.valueOf(Master.readChar());
            String D = String.valueOf(Master.readChar());
            String Di = String.valueOf(Master.readFloat());
            String C = String.valueOf(Master.readFloat());
            if (Disp.equals(" "))//COMPRUBA DE QUE SEA LEGIBLE
            {
                VT.add(O+" "+D+" "+Di+" "+C);
            }
        }
        //ORDENA EL VECTOR
        //Ordena la lista especificada en orden ascendente, según el ordenamiento natural de sus elementos
        Collections.sort(VT);
        
        //CREA UN ARREGLO DE DEDICADO A LAS ARISTAS
        String A[][] = new String[VT.size()][4];
        //0.-ORIGEN
        //1.-DESTINO
        //2.-DISTANCIA
        //3.-COSTO
        
        //CREA UNA LISTA QUE contendra LOS vertices
        LinkedList V = new LinkedList();
        
        //RECORRE EL VECTOR, RECUPERANDO LOS PARES (X,Y) ORDENADOS E INSERTANDOLOS EN UN ARREGLO DE DOS CAMPOS
        int P=0;
        for(String ET: VT)
        {
            String Reg[];
            Reg =ET.split(" ");
            A[P][0] = Reg[0];
            A[P][1] = Reg[1];
            A[P][2] = Reg[2];
            A[P][3] = Reg[3];
            //SI EL PADRE A UN NO SE ENCUENTRA EN V(vertices), LO AGREGA
            if (!V.contains(A[P][0]))
            {
                V.offer(A[P][0]);
            }
            P++;
        }
        
        //INSTACIAR LA CLASE GRAFO
        Grafo = new c_G();
        
        //ACTULIZA ARISTAS(A) Y VERTICES(V) DE Grafo
        Grafo.m_UpdateGrafo(A,V);
    }
    
    public void m_searchInO(char type) throws IOException
    {
        //METODO QUE LEE DEL ARCHIVO LOS REGISTROS Y LLOS ORDENA (NO REPLAZA EL ORDEN DE LOS REGISTROS EN EL FILE)
        this.m_sortFile();
        
        //INSTACIA ABIERTA Y SUCESORES
        LinkedList A = new LinkedList();//Abierta
        LinkedList S = new LinkedList();
        String n;
        String son;
        Tabla_A TablaA= new Tabla_A();
        
        //CREA VARIABLE Raiz UTILIZADA COMO PADRE
        String Raiz,Destino;
        
        //SOLICITA RAIZ
        do {
            System.out.print("Dame raiz:");
            Raiz = Scn.next();
        } while (!Grafo.m_searchV(Raiz));//PREGUNTA SI PERTECE A LOS VERTICES
        
        //SOLICITA DESTINO
        do {
            System.out.print("Dame destino:");
            Destino = Scn.next();
        } while (!Grafo.m_searchV(Destino));//PREGUNTA SI PERTECE A LOS VERTICES
        
        A.offer(Raiz);//ABIERTA = (inicial);
        while(A.size()>0)//mientras NoVacia(ABIERTA) hacer
        {
            //INICIA "n = ExtraePrimero(ABIERTA);"
            n = (String)A.peek();//RECUPERA EL PRIMER ELEMETO DE ABIERTA
            A.poll();//ELIMINA EL PRIMER ELEMENTO DE ABIERTA
            //FIN "n = ExtraePrimero(ABIERTA);"
            if (!TablaA.existInTable(n))//SI NO EXITE EN TABALA_A EL METODO "existInTable" crea un registro en ella y mustra un mensaje de que se a agregado
            {
                System.out.println("Agraga Registro de "+ n);//MESAJE DE AYUDA (SE VA A BORRAR JAJAJA)
            }
            if (n.equals(Destino))//si (EsObjetivo(n)) entonces
            {
                TablaA.getWay(Raiz,Destino,type);//devolver Camino(inicial,n);
                //break;
            }//finsi;
            //INICIO DE S = Sucesores(n); OBTIENE SECESORES O HIJOS DE n
            son=Grafo.m_getSons(n);
            String F;
            F = TablaA.getFather();//OBTINE EL PADRE DEL PADRE PARA EVITARLO COMO HIJO EN LA SELECCION DE SUCESORES DEBIDO A QUE NO ES UN GRAFO DIRIGIDO
            while(!son.equals("Fin"))
            {
                if (!F.equals(son))
                {
                    S.offer(son);//AGREGAG A SUCESORES
                }
                Grafo.m_incIndexA();
                son=Grafo.m_getSons(n);
            }
            Grafo.m_resetIndexA();
            //FIN DE S = Sucesores(n); OBTIENE SECESORES O HIJOS DE n
            TablaA.setSons(S);//Añadir S a la entrada de n en la TABLA_A;
            while(S.size()>0)//para cada q de S hacer
            {
                //obtener q
                String q = (String)S.peek();//-recupera el primer elemento de la lista
                S.poll();//elimina el primer elemeto de la lista previamente recuperado
                if (TablaA.existInTable(q))
                {
                    //n=padre
                    //q=hijo
                    if (type=='C')
                    {
                        TablaA.RectificarCosto(n,q,Double.valueOf(Grafo.m_getCoste(n, q)),Grafo);//Tabien se envia el objeto Grafo debido a que es el que contiene la info de las conexiones
                    }
                    if (type=='D')
                    {
                        TablaA.RectificarDistancia(n,q,Double.valueOf(Grafo.m_getDistance(n, q)),Grafo);//Tabien se envia el objeto Grafo debido a que es el que contiene la info de las conexiones
                    }
                }
                else
                {
                    TablaA.setFather(n);//Anterior(q) = n,
                    double CostoP = Double.valueOf(TablaA.getCosteFather(n));
                    double CostoA = Double.valueOf(Grafo.m_getCoste(n, q));
                    TablaA.setCI(String.valueOf(CostoP+CostoA));//Coste(incial,q) = Coste(inicial,n)+Coste(n,q);

                    double DistanceP = Double.valueOf(TablaA.getDistanceFather(n));
                    double DistanceA = Double.valueOf(Grafo.m_getDistance(n, q));
                    TablaA.setDI(String.valueOf(DistanceP+DistanceA));//Coste(incial,q) = Coste(inicial,n)+Coste(n,q);
                    
                    /**
                     * @Nota aun no ordena por mejor camino, Correso+pondiente a la instruccion de pseudocodigo---> "ABIERTA = Mezcla(q,ABIERTA);"
                     */
                    A.offer(q);//ABIERTA = Mezcla(q,ABIERTA) 
                }
            }
        }//finmientras;
        TablaA.m_showTableA(TablaA);
    }

//    public void m_showTableA(Tabla_A TablaA)
//    {
//        String Title[]="Nodo,Padre,DI,CI,Sucesores".split(",");
//        for (int i = 0; i < 5; i++)
//        {
//            int length = 12 -Title[i].length();
//            for (int j = 0; j < length; j++) {
//                Title[i]+=" ";
//            }
//            System.out.print(Title[i]);
//        }
//        System.out.println("");
//        
//        LinkedList Nodo = TablaA.getListNodo();
//        LinkedList Padre = TablaA.getListFather();
//        LinkedList DI = TablaA.getListDI();
//        LinkedList CI = TablaA.getListCI();
//        LinkedList Sucesores = TablaA.getListSons() ;
//        
//        int lengthList = Nodo.size();
//        String wordN,wordF,wordDI,wordCI,wordS;
//        LinkedList ListS;
//        for (int i = 0; i < lengthList; i++)
//        {
//            
//            wordN = Nodo.get(i).toString();
//            wordN = this.m_formatTable(wordN);
//            
//            wordF = Padre.get(i).toString();
//            wordF = this.m_formatTable(wordF);
//            
//            wordDI = DI.get(i).toString();
//            wordDI = this.m_formatTable(wordDI);
//            
//            wordCI = CI.get(i).toString();
//            wordCI = this.m_formatTable(wordCI);
//            
//            ListS = (LinkedList) Sucesores.get(i);
//            wordS = this.n(ListS);
//            
//            System.out.println(wordN+wordF+wordDI+wordCI+wordS);
//        }
//    }
//    
//    private String m_formatTable(String word)
//    {
//        int length = 12 - word.length();
//        for (int i = 0; i < length; i++)
//        {
//            word+=" ";
//        }
//        return word;
//    }
//    
//    public void m_showTableAs(Tabla_A TablaA)
//    {
//        String Title[]="Nodo,Padre,DI,CI,Sucesores".split(",");
//        for (int i = 0; i < 5; i++)
//        {
//            int length = 12 -Title[i].length();
//            for (int j = 0; j < length; j++) {
//                Title[i]+=" ";
//            }
//            System.out.print(Title[i]);
//        }
//        System.out.println("");
//        
//        LinkedList Nodo = TablaA.getListNodo();
//        LinkedList Padre = TablaA.getListFather();
//        LinkedList DI = TablaA.getListDI();
//        LinkedList CI = TablaA.getListCI();
//        LinkedList Sucesores = TablaA.getListSons() ;
//        
//        int lengthList = Nodo.size();
//        String wordN,wordF,wordDI,wordCI,wordS;
//        LinkedList ListS;
//        for (int i = 0; i < lengthList; i++)
//        {
//            
//            wordN = Nodo.get(i).toString();
//            wordN = this.m_formatTable(wordN);
//            
//            wordF = Padre.get(i).toString();
//            wordF = this.m_formatTable(wordF);
//            
//            
//            wordCI = CI.get(i).toString();
//            wordCI = this.m_formatTable(wordCI);
//            
//            System.out.println(wordN+wordF+wordCI);
//        }
//    }
//    
//    private String n(LinkedList L)
//    {
//        if (L.size()>0)
//        {
//            String W="";
//            while(L.size()>1)
//            {
//                W+=L.poll().toString()+",";
//            }
//            W+=L.poll().toString();
//            return W;
//        }
//        else{
//            return "0";
//        }
//    }
    

    public void m_deleteNode() throws IOException
    {
        String Nodo;
        int Llave = 0;
        System.out.print("Dame raiz:");
        Nodo = Scn.next();
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
            
            char O = Master.readChar();
            
            char D = Master.readChar();
            
            float Di = Master.readFloat();
            
            float C = Master.readFloat();
            
            er = Master.getFilePointer();
            
            //LLER INDICE 
            ii =Indx.getFilePointer();//Respaldo del puntero de donde inicia el booleano de si es legible el registro
            char DisPI = Indx.readChar();
            
            Llave = Indx.readInt();
            
            long DL = Indx.readLong();
            
            ei = Indx.getFilePointer();
            
            //OBTENER DATOS DE LOS REGISTROS MARCADOS COMO BORRADOS
            if (Nodo.equals(O+""))
            {
                //Coloca al puntero en la posicion de inicio del registro para sobres escribir el booleano
                Master.seek(ir);
                Master.writeChar('#');
                
                Indx.seek(ii);
                Indx.writeChar('#');
                if (!VD.contains(D))//SI NO CONTIENE NINGUNA DE LAS VARIANTES DE NODO ORIGEN A NODO DESTINO
                {
                    VD.offer(D);
                    Dis.offer(Di);
                    Cos.offer(C);
                }
            }
            else
            {
                if (Nodo.equals(D+""))
                {
                    //Coloca al puntero en la posicion de inicio del registro para sobres escribir el booleano
                    Master.seek(ir);
                    Master.writeChar('#');
                    
                    Indx.seek(ii);
                    Indx.writeChar('#');
                    
                    if (!VD.contains(O))//SI NO CONTIENE NINGUNA DE LAS VARIANTES DE NODO ORIGEN A NODO DESTINO
                    {
                        VD.offer(O);
                        Dis.offer(Di);
                        Cos.offer(C);
                    }
                }
            }
            Master.seek(er);
            Indx.seek(ei);
        }
        LinkedList VA = new LinkedList();
        for (int i = 0; i < VD.size(); i++)
        {
            char NO = VD.poll().toString().charAt(0);
            float DO = Float.valueOf(Dis.poll().toString());
            float CO = Float.valueOf(Cos.poll().toString());
            //CREAR RELACIONES
            Llave++;
            for (int j = 0; j < VD.size(); j++)
            {
                /**
                 * @CAMINO_DE_IDA
                 */
                //BOOLEAN; SI ES LEGIBLE
                Pointer=Master.getFilePointer();
                Master.writeChar(' ');

                //ESCRIBE LLAVE
                Master.writeInt(Llave);
                
                //ESCRIBE ORIGEN
                Master.writeChar(NO);
                
                //ESCRIBE DESTINO
                Master.writeChar(VD.get(j).toString().charAt(0));

                //ESCRIBE DISTANCIA
                Master.writeFloat(DO+Float.valueOf(Dis.get(j).toString()));
                
                //ESCRIBE COSTO
                Master.writeFloat(CO+Float.valueOf(Cos.get(j).toString()));
                
                //ESCRIBE INDEX PARA PRIMER CAMINO (IDA)
                Indx.writeChar(' ');
            
                //ESCRIBE LLAVE
                Indx.writeInt(Llave);
                Llave++;
                
                //ESCRIBE DIRECCION LOGICA
                Indx.writeLong(Pointer);
                
                /**
                 * @CAMINO_DE_REGRESO
                 */
                //BOOLEAN; SI ES LEGIBLE
                Pointer=Master.getFilePointer();
                Master.writeChar(' ');

                //ESCRIBE LLAVE
                Master.writeInt(Llave);
                
                //ESCRIBE ORIGEN
                Master.writeChar(VD.get(j).toString().charAt(0));
                
                //ESCRIBE DESTINO
                Master.writeChar(NO);

                //ESCRIBE DISTANCIA
                Master.writeFloat(DO+Float.valueOf(Dis.get(j).toString()));
                
                //ESCRIBE COSTO
                Master.writeFloat(CO+Float.valueOf(Cos.get(j).toString()));
                
                //ESCRIBE INDEX PARA PRIMER CAMINO (IDA)
                Indx.writeChar(' ');
            
                //ESCRIBE LLAVE
                Indx.writeInt(Llave);
                Llave++;
                
                //ESCRIBE DIRECCION LOGICA
                Indx.writeLong(Pointer);
            }
        }
    }
    
    
    public void m_searchInAstar() throws IOException
    {
        LinkedList S = new LinkedList();//lista dedicada a  contener los decendentes de EA
        //LLENAR ABIERTAS
        this.m_sortFile();
        
        //LLENAR ABIERTOS
        this.ABIERTOS = new c_Lista();
        this.CERRADOS = new c_Lista();
        
        String EA;
        String son;
        
        
        //CREA VARIABLE No UTILIZADA COMO PADRE Y META COMO DESTINO
        String No,Meta;
        
        //SOLICITA RAIZ
        do {
            System.out.print("Dame Nodo⁰:");
            No = Scn.next();
        } while (!Grafo.m_searchV(No));//PREGUNTA SI PERTECE A LOS VERTICES
        
        //SOLICITA DESTINO
        do {
            System.out.print("Dame meta:");
            Meta = Scn.next();
        } while (!Grafo.m_searchV(Meta));//PREGUNTA SI PERTECE A LOS VERTICES
        
        /**
         * 1.ABIERTOS ← (n 0 );
         */
        this.ABIERTOS.m_addReg(No,"-",0,0,0);
        
        /**
         * 2. Si ABIERTOS es la lista vacía, fin con fallo.
         */
        while(ABIERTOS.m_getSize()>0)//2. Si ABIERTOS es la lista vacía, fin con fallo.
        {
            /**
             * 3. EA ← primer elemento de ABIERTOS.
             */
            EA = this.ABIERTOS.m_getEA();
            
            /**
             * 3.1 Eliminar EA de ABIERTOS
             */
            LinkedList RD =this.ABIERTOS.m_delReg(EA);
            
            /**
             * 3.2 llevarlo a CERRADOS
             */
            this.CERRADOS.m_addReg(RD);
            
            /**
             * 4. Si EA es una meta, fin con éxito. Devolver el camino hasta la meta.
             */
            if (EA.equals(Meta))//si (EsObjetivo(n)) entonces
            {
                this.CERRADOS.m_getWay(No,Meta);//devolver Camino(inicial,n);
                break;
            }//finsi;
            
            /**
             * 5. Expandir nodo EA, generando todos sus sucesores como hijos de EA.
             */
            son=Grafo.m_getSons(String.valueOf(EA));
            char F;
            F = CERRADOS.m_getFather(EA);//OBTINE EL PADRE DEL PADRE PARA EVITARLO COMO HIJO EN LA SELECCION DE SUCESORES DEBIDO A QUE NO ES UN GRAFO DIRIGIDO
            while(!son.equals("Fin"))
            {
                if (F!=son.charAt(0))
                {
                    S.offer(son);//AGREGAG A SUCESORES
                }
                Grafo.m_incIndexA();
                son=Grafo.m_getSons(EA);
            }
            Grafo.m_resetIndexA();
            
            /**
             * 6.Para cada sucesor q de EA:
             */
            while(S.size()>0)
            {
                /**
                 * a)Calcular g(q)=g(EA)+c(EA,q)
                 */
                String q=S.poll().toString();
                
                double gq;  //costo estimado
                double gEA; //g(EA)=Costo hasta el padre
                double cEAq;//c(EA,q)=Costo del padre al Hijo

                //CALCULAR gEA
                gEA = CERRADOS.m_getG(EA);
                
                //CALCULAR cAEq
                cEAq=Double.valueOf(Grafo.m_getCoste(EA, q));
                
                //CALCULAR cAEq
                gq=gEA+cEAq;
                /**
                 * b) Si q e ABIERTOS || q e CERRADOS
                 */
                if (this.ABIERTOS.m_exist(q) || this.CERRADOS.m_exist(q))
                {
                    double gAq;
                    if (this.ABIERTOS.m_exist(q))
                    {
                        gAq = this.ABIERTOS.m_getG(q);
                        if (gq<gAq)
                        {
                            //colocar EA como nuevo padre
                            this.ABIERTOS.m_setFather(q, EA);
                            
                            //Calcular f(q)
                            double fq;//Costo neto de de NodoInical a NodoActual
                            double hq;//h(q)=Distancia del Padre al Hijo 
                            hq = Double.valueOf(Grafo.m_getDistance(EA, q));
                            fq = gq+hq;
                            
                            //asignar los nuevos valores g(q) y f(q).
                            this.ABIERTOS.m_setG(q, gq);
                            this.ABIERTOS.m_setF(q, fq);

                        }
                    }
                    
                    if (this.CERRADOS.m_exist(q))
                    {
                        gAq = this.CERRADOS.m_getG(q);
                        if (gq<gAq)
                        {
                            //colocar EA como nuevo padre
                            this.CERRADOS.m_setFather(q, EA);
                            
                            //Calcular f(q)
                            double fq;//Costo neto de de NodoInical a NodoActual
                            double hq;//h(q)=Distancia del Padre al Hijo 
                            hq = Double.valueOf(Grafo.m_getDistance(EA, q));
                            fq = gq+hq;
                            
                            //asignar los nuevos valores g(q) y f(q).
                            this.CERRADOS.m_setG(q, gq);
                            this.CERRADOS.m_setF(q, fq);
                            /**
                             * d) Si se ha modificado el padre de q y estaba en CERRADOS, eliminarle de
                                CERRADOS y llevarle a ABIERTOS.
                             */
                            RD = this.CERRADOS.m_delReg(q);
                            this.ABIERTOS.m_addReg(RD);
                        }
                    }
                    
                    //System.out.println("Si existe en ABIERTOS o CERRADOS");
                }
                else
                {
                    /**
                     * c) calcular f(q)=g(q)+h(q)
                     */
                    double fq;//Costo neto de de NodoInical a NodoActual
                    double hq;//h(q)=Distancia del Padre al Hijo 
                    hq = Double.valueOf(Grafo.m_getDistance(EA+"", q+""));
                    fq = gq+hq;
                    //añadir q a ABIERTOS como hijo de EA
                    //asignando los valores f(q) y g(q).
                    this.ABIERTOS.m_addReg(q+"", EA+"", fq, gq, hq);
                }
                /**
                 * d) Si se ha modificado el padre de q y estaba en CERRADOS, eliminarle de
                    CERRADOS y llevarle a ABIERTOS.
                 */
            }
            /**
            * 7.Reordenar ABIERTOS según valores crecientes de f.
            */
            this.ABIERTOS.m_sort();
        }/**
         * 8. Ir a 2.
         */
        
         
        //this.m_showTableAs(CERRADOS);
    }
}
