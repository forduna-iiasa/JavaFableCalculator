package linkerdist;

import java.io.Serializable;

/**
 *
 * @author mgtorres
 */
public class NodeKdTree implements Serializable {
    public boolean visitado;
    int id;
    public String[] caso;
    public NodeKdTree padre;
    public NodeKdTree der;
    public NodeKdTree izq;

    //Constructor que recibe todos los datos de iris
    public NodeKdTree(String[] caso, boolean bandera, int id) {
        this.id=id;
        this.caso = new String[24];
        this.visitado = bandera;
    }

    public NodeKdTree() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public String[] getAr() {
        return caso;
    }

    public void setAr(String[] caso) {
        this.caso = caso;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public void setDer(NodeKdTree der) {
        this.der = der;
    }

    public void setIzq(NodeKdTree izq) {
        this.izq = izq;
    }

    @Override
    public String toString() {
        return "Node{" + ", visitado=" + visitado + '}';
    }
}
