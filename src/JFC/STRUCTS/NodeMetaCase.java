package linkerdist;

public class NodeMetaCase {
    NodeListKd KdTreeRoot;

    {
        KdTreeRoot = new NodeListKd();
    }

    NodeMetaCase der;
    NodeMetaCase izq;
    NodeMetaCase last;
    public int id;
    public NodeMetaCase retrievedNode;
    public NodeListKd retrivedcase;
    public NodeMetaCase MainMcRoot(){
        NodeMetaCase Mc = new NodeMetaCase();
        Mc.setId(1);
        return Mc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodeMetaCase addMcBrother(NodeMetaCase mainRoot, int id){
        NodeMetaCase root = mainRoot;
        NodeMetaCase bro = new NodeMetaCase();
        NodeMetaCase last;
        bro.setId(id);
        if(root.der != null){
            last = root.last;
            last.der = bro;
            bro.izq = last;
        }else{
            root.der = bro;
            bro.izq = root;
        }
        root.last = bro;
        return mainRoot;
    }

    public NodeMetaCase retrieveMc(NodeMetaCase root, int id){
        if(root.getId() == id){
            retrievedNode = root;
        }else{
            if(root.der != null){
                retrieveMc(root.der,id);
            }else{
                retrievedNode = root;
            }
        }
        return retrievedNode;
    }

    public NodeMetaCase LinkerKdTree(NodeMetaCase Mc, NodeListKd LinkerList){

        Mc.KdTreeRoot = addKdTree(LinkerList, Mc.KdTreeRoot,0);
        return Mc;
    }

    private NodeListKd addKdTree(NodeListKd LinkerList, NodeListKd root,int side) {

        NodeListKd curCase = null;
        NodeListKd listDown = new NodeListKd();
        int ListSize = LinkerList.Size(LinkerList);
        double splitPosition = 0.0;
        splitPosition = ((double) ListSize / 2);
        if(splitPosition<0){
            splitPosition = 0;
        }
        if(splitPosition % 2 != 0){
            splitPosition = Math.round(splitPosition);
        }

        if(ListSize > 0) curCase = LinkerList.retrievePosition(LinkerList,(int)splitPosition);
        else return curCase;
      //  System.out.println("splid val>  " +curCase.getId());
        if(ListSize==1){
            curCase.lastNode=null;
            curCase.dad = null;
            curCase.son = null;
            LinkerList.son = null;
            linkNewRoot(root,curCase,side);
        }
        if(ListSize>=2){
            if(ListSize == 2){
                listDown = curCase.son;
                curCase.son = null;
                LinkerList = curCase;
                curCase.dad = null;
                curCase.listDown = listDown;
                curCase.LinkerList = LinkerList;
                linkNewRoot(root,curCase,side);
            }else{
                listDown = curCase.son;
                listDown.dad = null;
                curCase.son = null;
                listDown.lastNode = LinkerList.lastNode;

                if(listDown.lastNode.getId() == curCase.getId())
                    listDown.lastNode = null;

                LinkerList.lastNode = curCase.dad;

                if(LinkerList.lastNode.getId() == curCase.getId())
                    LinkerList.lastNode = null;

                if(LinkerList.lastNode!=null)
                    if(LinkerList.lastNode.son != null)
                        LinkerList.lastNode.son = null;
                curCase.dad = null;
                curCase.LinkerList = LinkerList;
                curCase.listDown = listDown;
                linkNewRoot(root,curCase,side);
            }

            if(curCase.LinkerList != null)
                addKdTree(curCase.LinkerList,curCase,1);
            if(curCase.listDown != null)
                addKdTree(curCase.listDown,curCase,2);
        }
       return curCase;
    }

    public NodeListKd retriveCase(NodeMetaCase mc,int id) {
        NodeListKd root = mc.KdTreeRoot;
        NodeListKd aux=retriveCaseizq(root,id);
        return aux;
    }

    private NodeListKd retriveCaseizq(NodeListKd root,int id) {
            if(root.getId()>id){
                if(root.izq!=null){
                    retriveCaseizq(root.izq,id);
                }
            }else if (root.getId()<id){
                if(root.der!=null){
                    retriveCaseizq(root.der,id);
                }
            }else if(root.getId()==id){
                retrivedcase=root;
            }
            return retrivedcase;
    }

    private NodeListKd linkNewRoot(NodeListKd root, NodeListKd curCase, int side) {
        if(curCase.getId() == 1) {
            curCase.setId(curCase.getId());
        }

        if(side == 0){
            root.setId(curCase.getId());
            root.setCaso(curCase.getCaso());
        }else{
        if(curCase.getId() < root.getId()) {
            if (root.izq == null) {
                root.izq=curCase;
                if(curCase.listDown != null && curCase.getId() == curCase.listDown.getId())
                    curCase.listDown = null;
                if(curCase.LinkerList != null && curCase.getId() == curCase.LinkerList.getId())
                    curCase.LinkerList = null;
            }else{
                linkNewRoot(root.izq,curCase,1);
            }
        }else{
            if (root.der == null) {
                root.der = curCase;
                if(curCase.LinkerList != null && curCase.getId() == curCase.LinkerList.getId())
                    curCase.LinkerList = null;
                if(curCase.listDown != null && curCase.getId() == curCase.listDown.getId())
                    curCase.listDown = null;
            }else{
                linkNewRoot(root.der,curCase,2);
            }
        }
        }
return root;
    }

}
