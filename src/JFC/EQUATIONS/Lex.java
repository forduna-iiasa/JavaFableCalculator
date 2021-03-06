package JFC.EQUATIONS;

import JFC.IIASA.LoadSheetNames;
import JFC.IIASA.LoadTableNames;
import JFC.IIASA.MatrixQ;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Lex {
    MatrixQ MatLex;
    ArrayList CalcTables;
    ArrayList CalcSheets;
    public ArrayList Cads;
    public ArrayList Toks;
    public String CurrentString;
   public Lex() throws FileNotFoundException {
       MatLex = new MatrixQ();
       MatLex.MatrixLoadFile();
       CalcSheets = new LoadSheetNames().GetData();
       CalcTables = new LoadTableNames().GetData();
       Cads = new ArrayList();
       Toks = new ArrayList();
    }
    public String getTokens(String Cad) throws FileNotFoundException {
        int tope = 0, numCh = 0, col = 0, token = 0, ren = 0;
        int isStatement;
        CurrentString = Cad;
        boolean isHoja = false;
        boolean isTable = false;
        String toks = "", variable = "", cadena = "";
        tope = Cad.lastIndexOf("");
        char car = ' ';
        Cads.clear();
        Toks.clear();
        while (numCh < tope) {
            car = Cad.charAt(numCh);
            col = MatLex.getCol(car);
            token = MatLex.getToken(ren, col);
            if (token < 0) {
                if (token == -1 || token == -2 || token == -3 || token == -17) {
                    Cads.add(cadena);
                    numCh--;
                    if (token == -1) {
                        isStatement = FindStatement(cadena);
                        if (isStatement >= 100) {
                            //System.out.println("es comando->" + cadena + "[" + isStatement + "]");
                            //toks = toks + isStatement + ",";
                            token = isStatement;
                            cadena = "";
                        } else {
                            isHoja = FindSheet(CalcSheets, cadena);
                            if (isHoja == true) {
                               // System.out.println("es Hoja->" + cadena + "[" + 200 + "]");
                                //toks = toks + "200,";
                                token = 200;
                                cadena = "";

                            }
                            if (isHoja == false) {
                                isTable = FindTable(CalcTables, cadena);
                                if (isTable == true) {
                                  //  System.out.println("es Tabla->" + cadena + "[" + 300 + "]");
                                    //toks = toks + "300,";
                                    token = 300;
                                    cadena = "";
                                } else {
                                    cadena = "";
                                }
                            }
                        }
                    }
                    cadena = "";
                    variable = variable + cadena;
                    toks = toks + token + ",";
                    Toks.add(token);
                } else {
                    Cads.add(car);
                    variable = variable + car;
                    toks = toks + token + ",";
                    Toks.add(token);
                }
                ren = 0;

            } else {
                if (token > 500) {
                    System.out.println("error-->" + "[" + token + "]");
                    System.out.println("error-->" + "[" + toks + "]");
                    numCh--;
                    variable = variable + "," + car + ",";
                    ren = 0;
                } else {
                    variable = variable + "" + car;
                    token = token - 2; //esto es para mantener el control de la matrix lex
                    ren = token;
                    cadena = cadena + car;
                }

            }
            numCh++;

        }
        System.out.println(variable);
     //   System.out.println(toks);
return toks;
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
    public boolean FindTable(ArrayList LstTables, String name){
        for(int i =0; i<LstTables.size();i++) {
            String cad = LstTables.get(i).toString();
            String[] tlbs = cad.split(",");
            if (tlbs[1].equals(name)) {
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
}
