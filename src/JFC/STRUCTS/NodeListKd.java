package linkerdist;

import sun.awt.image.ImageWatched;

public class NodeListKd {
    private int id;
    private String caso;
    public NodeListKd dad;
    public NodeListKd son;
    public NodeListKd retrievedNode;
    public NodeListKd lastNode;
    public NodeListKd der;
    public NodeListKd izq;
    public NodeListKd LinkerList;
    public NodeListKd listDown;


    public NodeListKd MainLastRoot(){
        NodeListKd mainLastRoot = new NodeListKd();
        return mainLastRoot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public NodeListKd add(NodeListKd mainRoot, int id, String casex){
        NodeListKd root = mainRoot;
        NodeListKd son = new NodeListKd();
        NodeListKd last;
        son.setId(id);
        son.setCaso(casex);
        if(root.son != null){
            last = root.lastNode;
            last.son = son;
            son.dad = last;
        }else{
            root.son = son;
            son.dad = root;
        }
        root.lastNode = son;
        return mainRoot;
    }

    public NodeListKd retrieve(NodeListKd root, int id){
        while(root.getId() != id){
            if(root.son != null){
                root = root.son;
            }
        }
        return root;
    }

    public int Size(NodeListKd LinkerList){
        int y = 1;
        int ini = LinkerList.id;

        if(LinkerList.lastNode != null) {
            int fin = LinkerList.lastNode.id;
            for (int x = ini; x < fin; x++)
                y++;
        }
        return y;
    }

    public NodeListKd retrievePosition(NodeListKd linkerList, int splitPosition) {
        NodeListKd aux = linkerList;
        for(int x = 1; x<splitPosition; x++){
            aux = aux.son;
        }
        return aux;
    }
}
