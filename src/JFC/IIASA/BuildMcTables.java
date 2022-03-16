package JFC.IIASA;

import JFC.STRUCTS.NodeTable;
import JFC.STRUCTS.RowCols;

import java.util.ArrayList;

public class BuildMcTables {
    public NodeTable BuildTables(){
        ArrayList CalcTables;
        ArrayList CalcSheets,Equations;
        //new Main().PrepareCountryFiles();
        CalcTables = new LoadTableNames().GetData();
        //region build mcs LA hoja es el mc y sus hijos son las tablas que tiene cada hoja
        /* NodeMetaCase LinkerMc;
        NodeMetaCase Mcs = new NodeMetaCase();
        LinkerMc = Mcs.MainMcRoot();
        LinkerMc = m.BuildMcs(LinkerMc,CalcSheets);
        LinkerMc.LinkTables(LinkerMc,CalcTables);
        */
        //endregion

        //region agregamos todas las tablas como Mc como lo majena excel ya que no se usa SheetName.TableName solo se usa TableName en excel
        NodeTable TablesRoot,auxTablesRoot;
        RowCols RowsRoot = new RowCols();
        NodeTable McTables = new NodeTable();
        TablesRoot = McTables.getTablesRoot();
        TablesRoot = McTables.LinkTables(TablesRoot,CalcTables);
        //endregion
        //region agregamos primer renglon con nombres de las columnas
        auxTablesRoot = TablesRoot;
        for(int init=0;init<CalcTables.size();init++){
            String columns = (String) CalcTables.get(init);
            String[] getTabl = columns.split(",");
            auxTablesRoot = auxTablesRoot.retrieve(auxTablesRoot,getTabl[1]);
            //agregamos renglon inicial en mc tabla recuperada y sus columnas correspondientes con su nombre
            auxTablesRoot = RowsRoot.iniRow(auxTablesRoot,columns,init);
        }
        //endregion
        //region recuperamos tabla a agregar renglones y agregamos sus renglones incluyendo las columnas del mismo
        for(int init=0;init<CalcTables.size();init++){
            String columns = (String) CalcTables.get(init);
            String[] getTabl = columns.split(",");
            //System.out.println("En Tabla> "+getTabl[1]);

            Equations = new LoadFileEquations().GetData(getTabl[1]);
            auxTablesRoot = TablesRoot.retrieve(TablesRoot,getTabl[1]);
            RowCols auxRows = auxTablesRoot.Rows;
            for(int idrow=0;idrow<Equations.size();idrow++){
                RowsRoot = RowsRoot.addRow(auxRows, (String) Equations.get(idrow),idrow+1);
            }
        }

        //endregion
        return TablesRoot;
    }
}
