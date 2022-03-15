package JFC.STRUCTS;

import com.mysql.cj.result.Row;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RowCols {
    public int Terminal;
    public int idrow;
    public String Name;
    public String value;
    RowCols NextRow,NextCol,PrevRow,PrevCol,LastRow,LastCol;



    public int getTerminal() {
        return Terminal;
    }

    public void setTerminal(int terminal) {
        Terminal = terminal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public NodeTable iniRow(NodeTable rooTable, String rowdata,int idrow){
        String[] data = rowdata.split(",");
        RowCols MainRootRow = new RowCols();
        MainRootRow.setIdrow(idrow);
        // se agregara el primer renglon solo para los nombres de las columnas y si es terminal o no
        MainRootRow.setValue(data[2]);
        rooTable.Rows = MainRootRow;
        //se agregan las demas columnas, incia en 2 ya que ahi inician los nombres de las columnas.
        for(int o=3;o<data.length;o++){
            MainRootRow = MainRootRow.addCol(MainRootRow,data[o],o);
        }
        return rooTable;
    }
    public RowCols addRow(RowCols MainRootRow, String rowdata,int idrow){
        RowCols root = MainRootRow;
        String[] data = rowdata.split("\t");
        RowCols last;
        RowCols aux = new RowCols();
        aux.setIdrow(idrow);
        // se agregara el renglon
        aux.setValue(data[1]);
        if(root.NextRow != null){
            last = root.LastRow;
            last.NextRow = aux;
            aux.PrevRow = last;
        }else{
            root.NextRow = aux;
            aux.PrevRow = root;
        }
        root.LastRow = aux;
//se agregan las columnas en 1 ya que ahi inician las formulas
        for(int o=2;o<data.length;o++){
            aux = aux.addCol(aux,data[o],o);
        }
        return MainRootRow;
    }

    public RowCols addCol(RowCols MainRootRow, String value, int idrow){
        RowCols root = MainRootRow;
        RowCols last;
        RowCols aux = new RowCols();
        aux.setIdrow(idrow);
        aux.setValue(value);
        if(root.NextCol != null){
            last = root.LastCol;
            last.NextCol= aux;
            aux.PrevCol = last;
        }else{
            root.NextCol = aux;
            aux.PrevCol= root;
        }
        root.LastCol = aux;
        return MainRootRow;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIdrow() {
        return idrow;
    }

    public void setIdrow(int idrow) {
        this.idrow = idrow;
    }

    /*public RowCols addRowNames(RowCols rowsRoot, String s,int idrow) {
        RowCols root = rowsRoot;
        String[] data = s.split(",");
        RowCols last;
        RowCols aux = new RowCols();
        aux.setIdrow(idrow);
        // se agregara el primer renglon solo para los nombres de las columnas y si es terminal o no
        aux.setValue(data[2]);
        if(root.NextRow != null){
            last = root.LastRow;
            last.NextRow = aux;
            aux.PrevRow = last;
        }else{
            root.NextRow = aux;
            aux.PrevRow = root;
        }
        root.LastRow = aux;
        //se agregan las demas columnas, incia en 2 ya que ahi inician los nombres de las columnas.
        for(int o=3;o<data.length;o++){
            root = root.addCol(root,data[o]);
        }
        return root;
    }*/
}
