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
    public NodeTable retrieveTable(NodeTable root, int id){
        while(root.getId() != id){
            if(root.der != null){
                root = root.der;
            }
        }
        return root;
    }
    public NodeTable retrieveTable(NodeTable root, String idTable){
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

    public NodeTable retrieveTableMc(NodeTable root, String id){
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

    public ArrayList getRow(NodeTable TablesRoot, String TableName, int RowId){
        ArrayList RowData = new ArrayList();
        NodeTable RetrievedTable;
        RowCols row,col;
        //asi recuperamos una tabla de la estructura
        RetrievedTable = TablesRoot.retrieveTable(TablesRoot,TableName);
        row = RetrievedTable.Rows;
        while(row.getIdrow()!= RowId){
            row = row.NextRow;
        }
        while(row.NextCol != null){
            RowData.add(row.getValue());
            row = row.NextCol;
            if(row.NextCol == null){
                RowData.add(row.getValue());
            }
        }
        return RowData;
    }
    public int getColLocation(NodeTable TablesRoot, String TableName, String ColName){
        NodeTable RetrievedTable;
        RowCols row;
        int found=0;
        //asi recuperamos una tabla de la estructura
        RetrievedTable = TablesRoot.retrieveTable(TablesRoot,TableName);
        row = RetrievedTable.Rows;
        while(row.getIdrow()!= 0){
            row = row.NextRow;
        }
        while(row.NextCol != null){
            if(row.getValue().equals(ColName)){
               found = row.getIdCol();
               return found;
            }else {
                row = row.NextCol;
            }
            if(row.NextCol == null){
                found = row.getIdCol();
                return  found;
            }

        }
        return found;
    }

    public String getColName(NodeTable TablesRoot, String TableName, int Col){
        NodeTable RetrievedTable;
        RowCols row;
        String found="NOT FOUND";

        //asi recuperamos una tabla de la estructura
        RetrievedTable = TablesRoot.retrieveTable(TablesRoot,TableName);
        row = RetrievedTable.Rows;

        while(row.getIdCol()!= Col){
            row = row.NextCol;
            if(row.getIdCol() == Col){
                return row.getValue();
            }
        }
        return found;
    }

    public String getCellValue(NodeTable TablesRoot, String TableName, int RowId, int ColId){
        String RowData = "";
        NodeTable RetrievedTable;
        RowCols row,col;
        //asi recuperamos una tabla de la estructura
        RetrievedTable = TablesRoot.retrieveTable(TablesRoot,TableName);
        row = RetrievedTable.Rows;
        while(row.getIdrow()!= RowId){
            row = row.NextRow;
        }
        while(row.NextCol != null){
            if(row.getIdCol() == ColId){
               RowData = row.getValue();
               return RowData;
            }else{
                row = row.NextCol;
            }
            if(row.NextCol == null){
                if(row.getIdCol() == ColId){
                    RowData = row.getValue();
                    return RowData;
                }
            }

        }
        return RowData;
    }
}
