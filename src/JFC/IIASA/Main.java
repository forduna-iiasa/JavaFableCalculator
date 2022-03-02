package JFC.IIASA;

import JFC.DATA.DataTable;
import JFC.EQUATIONS.LoadEquation;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        new Main().PrepareFiles();


        // write your code here
    }
    public void PrepareFiles(){
        int step = 0;
        int totalFiles = 0;
        ZipSecureFile.setMinInflateRatio(0);
        String[] ids;
        String[] scen;
        String[] iteration;
        File[] listOfFiles;
        String[] files;
        //File folder = new File("H:\\LinkerMarckovModel\\calcs\\");
        //I:\javalera\Calculators\calcs P:\scnt\ScenathonExtractions061221\TradeAdj_Sus

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
}