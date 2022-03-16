package JFC.IIASA;

import JFC.EQUATIONS.Lex;
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
        //Main m = new Main();
        NodeTable McTables = new BuildMcTables().BuildTables();
        //NodeTable RetrievedTable;
        //asi recuperamos una tabla de la estructura
     //   RetrievedTable = McTables.retrieveTable(McTables,"Diet_scen");
        for(int i=0;i<7;i++) {
            System.out.println("Row["+i+"]");
            ArrayList RowData = McTables.getRow(McTables, "Diet_scen", i);
            for(int j=0; j<RowData.size();j++){
                System.out.println("--"+RowData.get(j));
            }
        }

        //Lex Lexico = new Lex();
        //Lexico.getTokens("fher,WaterUse_Scen,Scen_foodloss,sumif,sumifs,vlookup,3456$%^2345&*%&");



    }



}