package JFC.STRUCTS;

import javafx.scene.control.Tab;

import java.util.ArrayList;

public class NodeTable {
    public int id;
    public String idTable;
    public String caso;
    public NodeTable der;
    public NodeTable izq;
    public NodeTable last;
    public NodeTable retrievedTable;
    public RowCols Rows;

    public NodeTable getTablesRoot(){
        NodeTable TablesRoot = new NodeTable();
        TablesRoot.setId(0);
        TablesRoot.setIdTable("TablesRoot");
        return TablesRoot;
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
    public NodeTable retrieve(NodeTable root, int id){
        while(root.getId() != id){
            if(root.der != null){
                root = root.der;
            }
        }
        return root;
    }
    public NodeTable retrieve(NodeTable root, String idTable){
        while(!root.getIdTable().equals(idTable)){
            if(root.der != null){
                root = root.der;
            }
        }
        return root;
    }
    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public NodeTable addTblBrother(NodeTable mainRoot, String Table,int idtable){
        String [] dat = Table.split(",");
        NodeTable root = mainRoot;
        NodeTable bro = new NodeTable();
        NodeTable last;
        bro.setId(idtable);
        bro.setIdTable(dat[1]);
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

    public NodeTable retrieveTable(NodeTable root, String id){
        if(root.getIdTable().equals(id)){
            retrievedTable = root;
        }else{
            if(root.der != null){
                retrieveTable(root.der,id);
            }else{
                retrievedTable = root;
            }
        }
        return retrievedTable;
    }

    public NodeTable LinkTables(NodeTable McTables, ArrayList Tabl){
        for(int i=0;i<Tabl.size();i++){
            McTables.addTblBrother(McTables,Tabl.get(i).toString(),i);
        }
        return McTables;
    }
}