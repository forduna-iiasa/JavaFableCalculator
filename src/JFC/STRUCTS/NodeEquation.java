package JFC.STRUCTS;

public class NodeEquation {
    private int id;
    private String caso;
    public NodeEquation dad;
    public NodeEquation son;
    public NodeEquation retrievedNode;
    public NodeEquation lastNode;
    public NodeEquation der;
    public NodeEquation izq;
    public NodeEquation LinkerList;
    public NodeEquation listDown;


    public NodeEquation MainLastRoot(){
        NodeEquation mainRoot = new NodeEquation();
        return mainRoot;
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

    public NodeEquation add(NodeEquation mainRoot, int id, String casex){
        NodeEquation root = mainRoot;
        NodeEquation son = new NodeEquation();
        NodeEquation last;
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

    public NodeEquation retrieve(NodeEquation root, int id){
        while(root.getId() != id){
            if(root.son != null){
                root = root.son;
            }
        }
        return root;
    }

    public int Size(NodeEquation LinkerList){
        int y = 1;
        int ini = LinkerList.id;

        if(LinkerList.lastNode != null) {
            int fin = LinkerList.lastNode.id;
            for (int x = ini; x < fin; x++)
                y++;
        }
        return y;
    }

    public NodeEquation retrievePosition(NodeEquation linkerList, int splitPosition) {
        NodeEquation aux = linkerList;
        for(int x = 1; x<splitPosition; x++){
            aux = aux.son;
        }
        return aux;
    }
}
