package JFC.IIASA;

import JFC.EQUATIONS.Lex;
import JFC.STRUCTS.NodeTable;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.*;
import java.util.Stack;

public class Main {
    private static JTree Tree;
    Stack<String> pila,pilaquita;
    static DefaultMutableTreeNode root;
    public static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        Main m = new Main();
        Lex Lexico = new Lex();
        NodeTable McTables = new BuildMcTables().BuildTables();
        m.pila = new Stack<String>();
        m.pilaquita = new Stack<String>();
        String Chivo;
        Chivo = "C:\\Calculators\\FormulaTracker2.xml";

        //NodeTable RetrievedTable;
        //asi recuperamos una tabla de la estructura
     //   RetrievedTable = McTables.retrieveTable(McTables,"Diet_scen");

        //int a = McTables.getColLocation(McTables,"Reporting_aggregate","kcal_hist");
        //String g = McTables.getCellValue(McTables,"Reporting_aggregate",2,a);
	//String g="=IF(DietScenDef[[#This Row],[FAO2015]]>0,DietScenDef[[#This Row],[FAO2015]]-DietScenDef[[#This Row],[current]],SUMIFS(DietTarget[diff],DietTarget[DietScen],DietScenDef[[#This Row],[DietScen]],DietTarget[PROD_GROUP],DietScenDef[[#This Row],[PROD_GROUP]]))$";
    //String g = "=SUMIFS(FAOFoodPerCapita[food_intake],FAOFoodPerCapita[Year],DietScenDef[[#This Row],[Year]],FAOFoodPerCapita[Food_group],DietScenDef[[#This Row],[PROD_GROUP]])$";
       String g= "=SUMIFS(calc_dmer_activitylevel[Total], calc_dmer_activitylevel[YEAR], Calc_min_daily_kcal[[#This Row],[Year]])/Calc_min_daily_kcal[[#This Row],[Population]]$";
        root = new DefaultMutableTreeNode(g);
        Lexico.getTokens(g);
        bw = new BufferedWriter(new FileWriter(Chivo,false));
        bw.write("<?xml version = \"1.0\"?>");
        bw.newLine();
        bw.write("<tracking>");bw.newLine(); bw.write("<eq>");bw.newLine();
        bw.write(g);bw.newLine();
        bw.write("</eq>");bw.newLine();

        bw.newLine();
        m.getPath(Lexico,McTables,"","",root);
        bw.write("</tracking>");

        bw.flush();
        bw.close();
        m.printPila();
        m.printPilaquita();
        try {
            JFrame frame = new JFrame("FABLE Calculator, Equations Tracker [IIASA-ASA-NODES].");
            frame.setSize(800,600);
            Image icon = Toolkit.getDefaultToolkit().getImage("JavaFableCalculator/files/fable_icon.png");
            frame.setIconImage(icon);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JPanel panel = new JPanel();
            JTree t = new JTree(root);
            panel.add(t);
            panel.setBackground(Color.WHITE);
// add the panel to a JScrollPane
            JScrollPane jScrollPane = new JScrollPane(panel);
// only a configuration to the jScrollPane...
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
// Then, add the jScrollPane to your frame
            frame.getContentPane().add(jScrollPane);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
        //Lexico.getTokens("fher,WaterUse_Scen,Scen_fodloss,soumif,sumifs,vlookup,3456$%^2345&*%&");



    }
    public void getPath(Lex lexico, NodeTable McTables, String tbltemp, String coltemp, DefaultMutableTreeNode root) throws IOException {
        boolean gotTable =false;
        boolean goVlookup = false;
        boolean gato = false;
        String TableName=tbltemp,ColName=coltemp;
        int col=0;
        Stack<String> pilaquita=new Stack<String>();
        int tok=0;

        for(int i=0;i<lexico.Toks.size();i++){
            tok = (int) lexico.Toks.get(i);
            if(tok == 103) {
                goVlookup = true;
            }
            if(tok == 300) {
                TableName = lexico.Cads.get(i).toString();
                gotTable = true;
            }
            if(tok == -20){
                gato = true;
            }
            if(tok == -17 && goVlookup == true){
                col = Integer.parseInt(String.valueOf(lexico.Cads.get(i)));
                ColName = McTables.getColName(McTables,TableName,col-1);//restamos uno por que las columnas inician en cero en la estructura
                goVlookup=false;
                gotTable = false;
                pila.push(TableName+"."+ColName);
                pilaquita.push(TableName+"."+ColName);
                String p = "<"+TableName+"."+ColName+">";
                bw.write(p);bw.newLine();
                System.out.println(TableName+"."+ColName+"-->");
                DefaultMutableTreeNode caso = new DefaultMutableTreeNode(TableName+"."+ColName);
                Lex Lexico = new Lex();
                int a = McTables.getColLocation(McTables, TableName,ColName);
                String ga = McTables.getCellValue(McTables,TableName,1,a);
                DefaultMutableTreeNode caso2 = new DefaultMutableTreeNode(ga);
                caso.add(caso2);
                root.add(caso);
                bw.write("<eq>");bw.newLine();
                bw.write(ga);bw.newLine();
                bw.write("</eq>");bw.newLine();
                Lexico.getTokens(ga);
                getPath(Lexico,McTables,TableName,ColName, caso);
            }
            if(tok == -1 && gotTable == true){
                if(gato == true){
                    gato = false;
                }else{
                    gotTable=false;
                    ColName = lexico.Cads.get(i).toString();
                    pila.push(TableName+"."+ColName);
                    pilaquita.push(TableName+"."+ColName);
                    String p = "<"+TableName+"."+ColName+">";
                    bw.write(p);bw.newLine();
                    System.out.println(TableName+"."+ColName+"-->");
                    DefaultMutableTreeNode caso = new DefaultMutableTreeNode(TableName+"."+ColName);
                    Lex Lexico = new Lex();
                    int a = McTables.getColLocation(McTables, TableName,ColName);
                    String ga = McTables.getCellValue(McTables,TableName,1,a);
                    DefaultMutableTreeNode caso2 = new DefaultMutableTreeNode(ga);
                    caso.add(caso2);
                    root.add(caso);
                    bw.write("<eq>");bw.newLine();
                    bw.write(ga);bw.newLine();
                    bw.write("</eq>");bw.newLine();

                    Lexico.getTokens(ga);
                    getPath(Lexico,McTables,TableName,ColName, caso);
                }

            }
            if(tok == -22){
                while(!pilaquita.empty()){
                    String v = "</"+pilaquita.peek()+">";
                    System.out.println(v);
                    pilaquita.pop();
                    bw.write(v);bw.newLine();
                }
            }

        }


    }

    public void printPila(){
        while(!pila.empty()){
            System.out.println(this.pila.peek());
            pila.pop();
        }
    }
    public void printPilaquita(){
        System.out.println("****************quitarl");
        while(!pilaquita.empty()){
            System.out.println(this.pilaquita.peek());
            pilaquita.pop();
        }
    }

}