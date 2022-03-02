package JFC.STRUCTS;

import java.util.LinkedList;

public class ObjListaGlobal {
    String Nombre;
    LinkedList <objLista> Lista;

    public ObjListaGlobal() {
    }

    public ObjListaGlobal(String nombre, LinkedList<objLista> lista) {
        Nombre = nombre;
        Lista = lista;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public LinkedList<objLista> getLista() {
        return Lista;
    }

    public void setLista(LinkedList<objLista> lista) {
        Lista = new LinkedList <objLista>();
        objLista tempobj;
        String p1,p2;
        int ax,axgl;
        for(int i =0; i<lista.size(); i++){
            p1 = lista.get(i).pais1;
            p2 = lista.get(i).pais2;
            ax = lista.get(i).axion;
            axgl = lista.get(i).globalAxion;
            tempobj = new objLista(p1,p2,ax,axgl);
            Lista.add(tempobj);
        }
    }
}
