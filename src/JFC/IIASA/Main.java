package JFC.IIASA;

import JFC.EQUATIONS.Lex;
import JFC.EQUATIONS.LoadEquation;
import JFC.STRUCTS.NodeMetaCase;
import JFC.STRUCTS.NodeTable;
import JFC.STRUCTS.RowCols;
import jade.domain.FIPANames;
import javafx.scene.control.Tab;
import org.apache.poi.openxml4j.util.ZipSecureFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    Stack<String> pila;


    public static void main(String[] args) throws IOException {
        Main m = new Main();
        Lex Lexico = new Lex();
        NodeTable McTables = new BuildMcTables().BuildTables();
        m.pila = new Stack<String>();
        //NodeTable RetrievedTable;
        //asi recuperamos una tabla de la estructura
     //   RetrievedTable = McTables.retrieveTable(McTables,"Diet_scen");

        int a = McTables.getColLocation(McTables,"Reporting_aggregate","kcal_hist");
        String g = McTables.getCellValue(McTables,"Reporting_aggregate",2,a);
        Lexico.getTokens(g);
        m.getPath(Lexico,McTables);
        m.printPila();




       /* for(int i=1;i<2;i++) {
            System.out.println("Row["+i+"]");
            ArrayList RowData = McTables.getRow(McTables, "Reporting_aggregate", i);
            for(int j=0; j<RowData.size();j++){
                String c = (String) RowData.get(j);
                String RowTokens = Lexico.getTokens(RowData.get(j).toString());
             //   m.getPath(Lexico);

               //
            //    System.out.println("--"+RowData.get(j));
            }
        }*/

        //Lex Lexico = new Lex();
        //Lexico.getTokens("fher,WaterUse_Scen,Scen_foodloss,sumif,sumifs,vlookup,3456$%^2345&*%&");



    }

    public void getPath(Lex lexico,NodeTable McTables) throws FileNotFoundException {
        boolean gotTable =false;
        String TableName="",ColName="";

        int tok=0;
        for(int i=0;i<lexico.Toks.size();i++){
            tok = (int) lexico.Toks.get(i);
            if(tok == 300) {
                TableName = lexico.Cads.get(i).toString();
                gotTable = true;
            }
            if(tok == -1 && gotTable == true){
                gotTable=false;
                ColName = lexico.Cads.get(i).toString();
                pila.push(TableName+"."+ColName);
                System.out.println(TableName+"."+ColName+"-->");
                Lex Lexico = new Lex();
                int a = McTables.getColLocation(McTables, TableName,ColName);
                String ga = McTables.getCellValue(McTables,TableName,2,a);
                Lexico.getTokens(ga);
                getPath(Lexico,McTables);
            }
        }
    }

    public void printPila(){
        while(!pila.empty()){
            System.out.println(this.pila.peek());
            pila.pop();
        }
    }

}