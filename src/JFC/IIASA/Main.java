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
import java.util.Stack;

public class Main {
    Stack<String> pila;

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        Lex Lexico = new Lex();
        NodeTable McTables = new BuildMcTables().BuildTables();
        //NodeTable RetrievedTable;
        //asi recuperamos una tabla de la estructura
     //   RetrievedTable = McTables.retrieveTable(McTables,"Diet_scen");

        for(int i=1;i<2;i++) {
            System.out.println("Row["+i+"]");
            ArrayList RowData = McTables.getRow(McTables, "Reporting_aggregate", i);
            for(int j=0; j<RowData.size();j++){
                String c = (String) RowData.get(j);
                String RowTokens = Lexico.getTokens(RowData.get(j).toString());
                m.getPath(Lexico);
                //int a = McTables.getColLocation(McTables,"Reporting_aggregate","kcal_hist");
                //System.out.println("-Rowlocation-"+a);
               //
            //    System.out.println("--"+RowData.get(j));
            }
        }

        //Lex Lexico = new Lex();
        //Lexico.getTokens("fher,WaterUse_Scen,Scen_foodloss,sumif,sumifs,vlookup,3456$%^2345&*%&");



    }

    public void getPath(Lex lexico) {
        pila = new Stack<String>();
        int tok=0;
        for(int i=0;i<lexico.Toks.size();i++){
            tok = (int) lexico.Toks.get(i);
            if(tok == 300){
                pila.push(lexico.Cads.get(i).toString());
            }

        }



        for(int h=0;h<pila.size();h++){
            System.out.println(pila.peek());
            pila.pop();
        }
    }


}