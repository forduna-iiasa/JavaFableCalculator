package JFC.IIASA;

import JFC.EQUATIONS.LoadEquation;
import JFC.STRUCTS.NodeMetaCase;
import JFC.STRUCTS.NodeTable;
import JFC.STRUCTS.RowCols;
import org.apache.poi.openxml4j.util.ZipSecureFile;

import java.io.File;
import java.io.IOException;
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
            auxTablesRoot = auxTablesRoot.retrieveTable(auxTablesRoot,getTabl[1]);
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
            auxTablesRoot = TablesRoot.retrieveTable(TablesRoot,getTabl[1]);
            RowCols auxRows = auxTablesRoot.Rows;
            for(int idrow=0;idrow<Equations.size();idrow++){
                RowsRoot = RowsRoot.addRow(auxRows, (String) Equations.get(idrow),idrow+1);
            }
        }

        //endregion
        return TablesRoot;
    }
    private NodeMetaCase BuildMcs(NodeMetaCase linkerMc, ArrayList calcSheets) {
        for(int i=0;i<calcSheets.size();i++){
            linkerMc.addMcBrother(linkerMc,i,calcSheets.get(i).toString());
        }
        return linkerMc;
    }
    public void PrepareFiles() throws IOException {
        int step = 0;
        int totalFiles = 0;
        ZipSecureFile.setMinInflateRatio(0);
        String[] ids;
        String[] scen;
        String[] iteration;
        File[] listOfFiles;
        String[] files;
        //File folder = new File("H:\\LinkerMarckovModel\\calcs\\");
        //I:\javalera\Calculators\calcs P:\scnt\ScenathonExtractions061221,radeAdj_Sus

        File folder = new File("C:\\Calculators\\");
        listOfFiles = folder.listFiles();
        File file;

        for (int i = 0; i < listOfFiles.length; i++) {
            System.out.println(i);
            if (listOfFiles[i].isFile()) {
                if (listOfFiles[i].getName().endsWith(".xlsx")) {

                    String f = listOfFiles[i].getName();
                    ids = f.split("-");
//              System.out.println(f);
                    scen = ids[1].split("_");
                    iteration = ids[2].split("_");
                    System.out.println("totalFiles:" + totalFiles);
                    step = 0;
                    new LoadEquation().ReedCalcShTbl(listOfFiles[i].getAbsolutePath(), iteration[1]);
                }
                totalFiles++;
            }
        }
    }

    public void PrepareCountryFiles() throws IOException {
        int step = 0;
        int totalFiles = 0;
        ZipSecureFile.setMinInflateRatio(0);
        String[] ids;
        String[] scen;
        String[] iteration;
        File[] listOfFiles;
        String[] files;
        //File folder = new File("H:\\LinkerMarckovModel\\calcs\\");
        //I:\javalera\Calculators\calcs P:\scnt\ScenathonExtractions061221,radeAdj_Sus

        File folder = new File("H:\\Git\\JavaFableCalculator\\JavaFableCalculator\\files\\");
        listOfFiles = folder.listFiles();
        File file;

        for (int i = 0; i < listOfFiles.length; i++) {
            System.out.println(i);
            if (listOfFiles[i].isFile()) {
                if (listOfFiles[i].getName().endsWith(".xlsx")) {

                    String f = listOfFiles[i].getName();
                    ids = f.split("-");
//              System.out.println(f);
                    scen = ids[1].split("_");
                    iteration = ids[2].split("_");
                    System.out.println("totalFiles:" + totalFiles);
                    step = 0;
                    new LoadEquation().ReedCalcShTbl(listOfFiles[i].getAbsolutePath(), iteration[1]);
                }
                totalFiles++;
            }
        }
    }

}
