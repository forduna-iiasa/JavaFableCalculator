package JFC.EQUATIONS;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadEquation {
    XSSFSheet sheet,hoja;
    XSSFCell cell;
    int numSheets;
    List<XSSFTable> tables;
    List<XSSFSheet> sheets;
    String Calculator="",country="";

    public void WriteShTb(String Calculator) throws IOException {
        ZipSecureFile.setMinInflateRatio(0);
        String sheetsFile,tablesFile;
        sheetsFile = "H:\\Git\\JavaFableCalculator\\JavaFableCalculator\\files\\Sheets.txt";
        tablesFile = "H:\\Git\\JavaFableCalculator\\JavaFableCalculator\\files\\Tables.txt";
        //Calculator = "C:\\Calculators\\";
        BufferedWriter wrSheet = new BufferedWriter(new FileWriter(sheetsFile,true));
        BufferedWriter wrTable = new BufferedWriter(new FileWriter(tablesFile,true));
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new File(Calculator));
        } catch (InvalidFormatException ex) {
            Logger.getLogger(LoadEquation.class.getName()).log(Level.SEVERE, null, ex);
        }
        numSheets = workbook.getNumberOfSheets();

        for(int i = 2; i< numSheets; i++){
            String colnames="";
            sheet = workbook.getSheetAt(i);
            String na = workbook.getSheetAt(i).getSheetName();
            wrSheet.write(na);
            wrSheet.newLine();
            tables = sheet.getTables();
            for (XSSFTable t : tables) {
                List <XSSFTableColumn> totcols = t.getColumns();
                for(int y=0;y<totcols.size();y++){
                    colnames += totcols.get(y).getName()+",";
                }
                String v = na+","+t.getName()+","+ colnames;
                colnames="";
                wrTable.write(v);
                wrTable.newLine();
            }
        }
        wrTable.flush();
        wrTable.close();
        wrSheet.flush();
        wrSheet.close();
    }

    public void WriteTables(String Calculator) throws IOException{
        ZipSecureFile.setMinInflateRatio(0);
        String chivo="",vals="";
        DataFormatter dataFormatter = new DataFormatter();

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new File(Calculator));

        numSheets = workbook.getNumberOfSheets();
        for(int i = 2; i< numSheets; i++){
            sheet = workbook.getSheetAt(i);
            String na = workbook.getSheetAt(i).getSheetName();
           // System.out.println("hoja " + na);
            if(na.equals("3_data_crops")){
                int a=0;
            }
            tables = sheet.getTables();
            //for (XSSFTable t : tables) {
            for(int ta=0;ta<tables.size();ta++){
                XSSFTable t = tables.get(ta);
                int maxRowindex = t.getEndRowIndex();
                int iniRowindex = t.getStartRowIndex()+1;
                int iniColindex = t.getStartColIndex();
                int maxColindex = t.getEndColIndex();
                int numCols = t.getColumnCount();
                int numRows = t.getRowCount();
                int totalRows = t.getTotalsRowCount();

                chivo = "C:\\Calculators\\formulas\\"+t.getName()+".txt";
                System.out.println(na + "." + t.getName());
                if(t.getName().equals("Fprodcount")){
                    int a=0;
                }
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(chivo,false));
                  //  System.out.println("terminorow " + maxRow);
                    for (int r = iniRowindex; r <= maxRowindex; r++) {
                        for (int j = iniColindex; j <= maxColindex; j++) {
                            if(r==315){
                                int a =0;
                            }
                   //         System.out.println("r " + r + "j "+ j);
                            cell = sheet.getRow(r).getCell(j);

                            CellType cel_Type;
                            try {
                                cel_Type = cell.getCellType();
                            } catch (NullPointerException e) {
                                cel_Type = CellType.ERROR;
                            }

                            switch (cel_Type){
                                case STRING:
//                            vals += cell.getStringCellValue()+"\t";
                                    vals += dataFormatter.formatCellValue(cell)+"$"+"\t";
                                    break;
                                case FORMULA:
                                    vals += cell.getCellFormula()+"$"+"\t";
                                    //vals += ","+cell.getRawValue();
                                    break;
                                case NUMERIC:
//                             vals += ""+(double)cell.getNumericCellValue()+"\t";
                                    vals += dataFormatter.formatCellValue(cell)+"$"+"\t";
                                    break;
                                case BLANK:
                                    vals += "NUL$"+"\t";
                                    break;
                                case ERROR:
                                    vals += "ERROR$"+"\t";
                                    break;
                            }
                        }
                        bw.write(vals);
                        bw.newLine();
                        bw.flush();
                        vals="";
                    }
                    bw.flush();
                    bw.close();
                } catch (IOException e) {}
            }
        }
        } catch (InvalidFormatException ex) {
            System.out.println(ex);
        }
    }
    public void SaveTable(XSSFTable t, XSSFSheet shet, String paisx2, String Tabla) {
        String chivo="",vals="";
        DataFormatter dataFormatter = new DataFormatter();
        //chivo = "H:\\Git\\JavaFableCalculator\\JavaFableCalculator\\files\\equations.txt";
        chivo = "C:\\Calculators\\formulas\\"+Tabla+".txt";

        int startRow = t.getStartCellReference().getRow();
        int endRow = t.getEndCellReference().getRow();
        int startColumn = t.getStartCellReference().getCol();
        int endColumn = t.getEndCellReference().getCol();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(chivo,true));
            //bw.write(Scenathon+"\t"+it+"\t"+lugar+"\t");
            // bw.newLine();
            startRow =2;

            List <XSSFTableColumn> totcols = t.getColumns();

           for (int i = (t.getStartCellReference().getRow())+1; i <= t.getEndCellReference().getRow(); i++) {
               // vals += Scenathon+","+it+","+lugar+",";
                // else vals += "Scenathon_id"+"\t"+"Iteration"+"\t"+"Country"+"\t";
                for (int j = t.getStartCellReference().getCol(); j <= t.getEndCellReference().getCol(); j++) {
                    cell = shet.getRow(i).getCell(j);
                    //  vals += cell.getCachedFormulaResultType() + "\t";
//                    vals += dataFormatter.formatCellValue(cell) + "\t";
                    // vals +=cell.getRawValue();
//                    CellType tipo = cell.getCellType();
                    CellType cel_Type;
                    try {
                        cel_Type = cell.getCellType();
                    } catch (NullPointerException e) {
                        cel_Type = CellType.ERROR;
                    }
//                   if(cell.getCellType() == CellType.FORMULA)

                    switch (cel_Type){
                        case STRING:
//                            vals += cell.getStringCellValue()+"\t";
                            vals += dataFormatter.formatCellValue(cell)+"$"+"\t";
                            break;
                        case FORMULA:
                            vals += cell.getCellFormula()+"$"+"\t";
                            //vals += ","+cell.getRawValue();
                            break;
                        case NUMERIC:
//                             vals += ""+(double)cell.getNumericCellValue()+"\t";
                            vals += dataFormatter.formatCellValue(cell)+"$"+"\t";
                            break;
                        case BLANK:
                            vals += "NUL$"+"\t";
                            break;
                        case ERROR:
                            vals += "ERROR$"+"\t";
                            break;


                    }
                }
                bw.write(vals);
                bw.newLine();
                //bw.newLine();
                // bw.newLine();
                vals="";
            }
            // bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {}
    }

    public void ReedCalc(String pais,String S,String It,String L,int paisx) throws IOException{
        this.country = pais;
        Calculator =  "\\LinkerMarckovModel\\calcs\\"+ pais;
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new File(Calculator));
        } catch (InvalidFormatException ex) {
            Logger.getLogger(LoadEquation.class.getName()).log(Level.SEVERE, null, ex);
        }
        sheet = workbook.getSheet("2_calc_livestock");
        tables = sheet.getTables();
        for (XSSFTable t : tables) {
            if(t.getName().equals("calc_livestocknb") == true){
                System.out.println("HOJA--" + sheet.getSheetName());
                SaveData(t,1,sheet,S,It,L,paisx);
            }
        }

        tables.clear();
        XSSFSheet shfood = workbook.getSheet("FOOD");
        tables = shfood.getTables();
        for (XSSFTable t : tables) {
            if(t.getName().equals("Total_results_diets") == true){
                System.out.println("HOJA--" + shfood.getSheetName());
                SaveData(t,2,shfood,S,It,L,paisx);
            }
            if(t.getName().equals("Results_Diets") == true){
                System.out.println("HOJA--" + shfood.getSheetName());
                SaveData(t,3,shfood,S,It,L,paisx);
            }
        }
        tables.clear();
        XSSFSheet sh7feas= workbook.getSheet("7_feas_consohum");
        tables = sh7feas.getTables();
        for (XSSFTable t : tables) {
            if(t.getName().equals("Calc_FeasConsoHum") == true){
                System.out.println("HOJA--" + sh7feas.getSheetName());
                SaveData(t,4,sh7feas,S,It,L,paisx);
            }
        }
        tables.clear();
        XSSFSheet sh3data = workbook.getSheet("3_data_crops");
        tables = sh3data.getTables();
        for (XSSFTable t : tables) {
            if(t.getName().equals("IrrigatedCropArea") == true){
                System.out.println("HOJA--" + sh3data.getSheetName());
                SaveData(t,5,sh3data,S,It,L,paisx);
            }
        }
        //  }
    }

    public void SaveData(XSSFTable t,int tab, XSSFSheet shet,String Scenathon,String it,String lugar,int paisx2) {
        String chivo="",vals="";
        DataFormatter dataFormatter = new DataFormatter();
        if(tab ==1) chivo = "H:\\scenathon2021\\clacs\\scenario.txt";
        if(tab ==2) chivo = "H:\\CalcsToExtract\\results\\food_Total_results_diets.txt";
        if(tab ==3) chivo = "H:\\CalcsToExtract\\results\\food_Results_Diets.txt";
        if(tab ==4) chivo = "H:\\CalcsToExtract\\results\\7_feas_consohum.txt";
        if(tab ==5) chivo = "H:\\CalcsToExtract\\results\\3_data_crops.txt";

        int startRow = t.getStartCellReference().getRow();
        int endRow = t.getEndCellReference().getRow();
        int startColumn = t.getStartCellReference().getCol();
        int endColumn = t.getEndCellReference().getCol();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(chivo,true));
            //bw.write(Scenathon+"\t"+it+"\t"+lugar+"\t");
            // bw.newLine();
            if(paisx2 > 0){
                startRow = startRow+1;

            }
            for (int i = startRow; i <= endRow; i++) {
                vals += Scenathon+"\t"+it+"\t"+lugar+"\t";
                // else vals += "Scenathon_id"+"\t"+"Iteration"+"\t"+"Country"+"\t";
                for (int j = startColumn; j <= endColumn; j++) {
                    cell = shet.getRow(i).getCell(j);
                    //  vals += cell.getCachedFormulaResultType() + "\t";
//                    vals += dataFormatter.formatCellValue(cell) + "\t";
                    // vals +=cell.getRawValue();
//                    CellType tipo = cell.getCellType();
                    CellType cel_Type;
                    try {
                        cel_Type = cell.getCellType();
                    } catch (NullPointerException e) {
                        cel_Type = CellType.ERROR;
                    }
//                   if(cell.getCellType() == CellType.FORMULA)

                    switch (cel_Type){
                        case STRING:
//                            vals += cell.getStringCellValue()+"\t";
                            vals += dataFormatter.formatCellValue(cell) + "\t";
                            break;
                        case FORMULA:
                            vals += cell.getRawValue()+"\t";
                            break;
                        case NUMERIC:
//                             vals += ""+(double)cell.getNumericCellValue()+"\t";
                            vals += dataFormatter.formatCellValue(cell) + "\t";
                            break;
                        case BLANK:
                            vals += "0"+"\t";
                            break;
                        case ERROR:
                            vals += dataFormatter.formatCellValue(cell) + "\t";
                            break;


                    }
                }
                bw.write(vals);
                bw.newLine();
                vals="";
            }
            // bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {}
    }
}
