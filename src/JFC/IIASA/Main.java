package JFC.IIASA;

import JFC.EQUATIONS.LoadEquation;
import JFC.STRUCTS.NodeMetaCase;
import JFC.STRUCTS.NodeTable;
import JFC.STRUCTS.RowCols;
import org.apache.poi.openxml4j.util.ZipSecureFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
        Main m = new Main();

        MatrixQ MatLex = new MatrixQ();
        MatLex.MatrixLoadFile();
        int tope = 0, numCh=0,col=0,token=0,ren=0;
        int isStatement;
        boolean isHoja=false;
        boolean isTable=false;
        String cad = "=IF(sumif(vlookup(an[@BiofuelType]=Livedens_scen,WaterUse_Scen,ClimateChange_Scen,PA_Scen,PostHarvestLoss_Scen,Biofuel_Scen,Pop_Scen,GDP_Scen,Diet_scen,Live_scen,Land_Scen,Crop_scen,Scen_foodloss,ImportDef,Product_ImpScen,product_Exports,ExpScenTarget,\"biodiesel\",[@[Biofuel_CO2]]-[@IPCCDieselOil],[@[Biofuel_CO2]]-[@IPCCGasoline])";
        String toks="",variable="",cadena="";
        ArrayList CalcTables;
        ArrayList CalcSheets,Equations;
        tope = cad.lastIndexOf("");
        char car = ' ';
        //new Main().PrepareCountryFiles();
        CalcSheets = new LoadSheetNames().GetData();
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
                RowsRoot = RowsRoot.addRow(auxRows, (String) Equations.get(idrow),idrow);
            }
        }

      //endregion
        //asi recuperamos una tabla de la estructura
        auxTablesRoot = TablesRoot.retrieve(TablesRoot,"Diet_scen");

        while (numCh < tope) {
            car = cad.charAt(numCh);
            col = MatLex.getCol(car);
            token = MatLex.getToken(ren, col);
            if(token < 0) {
             //   toks = toks +  token + ",";
               if(token == -17 || token == -1 || token == -2 || token == -3) {
                   numCh--;
                   if(token == -1){
                       isStatement = m.FindStatement(cadena);
                       if(isStatement >= 100)
                       {
                           System.out.println("es comando->"+cadena+"["+isStatement+"]");
                           toks = toks + isStatement + ",";
                           cadena = "";
                       }else{
                           isHoja = m.FindSheet(CalcSheets,cadena);
                           if(isHoja==true){
                               System.out.println("es Hoja->"+cadena+"["+200+"]");
                               toks = toks + "200,";
                               cadena = "";

                           }
                           if(isHoja==false){
                               isTable = m.FindTable(CalcTables,cadena);
                               if(isTable==true){
                                   System.out.println("es Tabla->"+cadena+"["+300+"]");
                                   toks = toks + "300,";
                                   cadena = "";
                               }else{
                                   toks = toks + "-1,";
                                   cadena = "";
                               }
                           }
                       }
                   }
                   cadena = "";
                   variable = variable + cadena;
               }else{
                   variable = variable + car;
                   toks = toks + token + ",";
               }
                ren = 0;

            }else{
                if(token > 500){
                    System.out.println("error-->"+"[" + token + "]");
                    System.out.println("error-->"+"[" + toks + "]");
                    numCh--;
                    variable = variable +","+ car+",";
                    ren = 0;
                }else{
                    variable = variable +""+car;
                    token = token -2; //esto es para mantener el control de la matrix lex
                    ren = token;
                    cadena = cadena+car;
                }

            }
            numCh++;

        }
        System.out.println(variable);
        System.out.println(toks);



        // write your code here
    }

    private NodeMetaCase BuildMcs(NodeMetaCase linkerMc, ArrayList calcSheets) {
        for(int i=0;i<calcSheets.size();i++){
            linkerMc.addMcBrother(linkerMc,i,calcSheets.get(i).toString());
        }
        return linkerMc;
    }

    public boolean FindTable(ArrayList LstTables, String name){
        for(int i =0; i<LstTables.size();i++) {
            if (LstTables.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean FindSheet(ArrayList LstSheets, String name){
        for(int i =0; i<LstSheets.size();i++) {
            if (LstSheets.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }
    public int FindStatement(String cadena) {
        int token=-1;
        cadena = cadena.toUpperCase();
        switch(cadena){
            case "SUM":
                token = 100;
                break;
            case "IFERROR":
                token = 101;
                break;
            case "SUMIFS":
                token = 102;
                break;
            case "VLOOKUP":
                token = 103;
                break;
            case "AND":
                token = 104;
                break;
            case "IF":
                token = 105;
                break;
                case "SUMIF":
                token = 106;
                break;
        }
        return token;
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