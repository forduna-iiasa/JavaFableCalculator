package JFC.IIASA;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrixQ {
    public double [][] mat;
    public int rows, cols;

    public MatrixQ() {
        this.rows = 66;
        this.cols = 31;
        this.mat = new double[rows][cols];
    }

    public double GetValue(int getRow, int getCol){
        return(mat[getRow][getCol]);
    }
    public void MatrixLoadFile() throws FileNotFoundException{
        String aux="";
        String Matrix = "H:\\Git\\JavaFableCalculator\\files\\LexMatrix.txt";
        try {
            FileReader readFile = new FileReader(new File(Matrix));
            BufferedReader readLines = new BufferedReader(readFile);
            int x1=0,countDatos=0;
            String VecDatas[];
            while((aux = readLines.readLine())!=null)//read a case, a case is in a line each line is a case
            {
                VecDatas = (aux.split(",")); //the case it is comma separated.
                for(int x2=0; x2<this.rows;x2++){
                    for(int x3=0; x3<this.cols;x3++){
                        this.mat[x2][x3] = Integer.parseInt(VecDatas[countDatos]);
                        countDatos++;
                    }
                }
                x1++;
                countDatos = 0;
            }
            readFile.close();
        } catch (IOException ex) {
            Logger.getLogger(MatrixQ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

