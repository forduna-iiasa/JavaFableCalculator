package JFC;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrixQ {
    public double [][][][][][] mat;
    public int [][][][][][] matIds;
    //   ArrayList <KeyValue> ListofDistances = new ArrayList<KeyValue>();
    //   LinkedList <ArrayList <String>> ListDatas;
    int dim;int maxItId;

    public MatrixQ() {
        this.dim = 5;
        this.mat = new double[this.dim][this.dim][this.dim][this.dim][this.dim][this.dim];
        this.matIds = new int[this.dim][this.dim][this.dim][this.dim][this.dim][this.dim];
        maxItId = 0;
    }
    public int getMaxitId(){
        return maxItId;
    }
    public void setupMat(){
        //int dim = 10;
        for(int x1=0; x1<dim;x1++){
            for(int x2=0; x2<dim;x2++){
                for(int x3=0; x3<dim;x3++){
                    for(int x4=0; x4<dim;x4++){
                        for(int x5=0; x5<dim;x5++){
                            for(int x6=0; x6<dim;x6++){
                                mat[x1][x2][x3][x4][x5][x6] = 0;//Math.random();
                                matIds[x1][x2][x3][x4][x5][x6] = -1;
                            }
                        }
                    }
                }
            }
        }
        this.MatrixSaveFile("matrixq");
    }

    public void insert(int ar, int au, int br, int us, int cn, int axion,double value,int globalaxion){
        mat[ar][au][br][us][cn][axion] = value;
        matIds[ar][au][br][us][cn][axion] = globalaxion;

    }

    public double GetValue(int ar, int au, int br, int us, int cn, int axion){
        return(mat[ar][au][br][us][cn][axion]);
    }

    public void imprimeMat(){
        for(int x1=0; x1<2;x1++){
            for(int x2=0; x2<2;x2++){
                for(int x3=0; x3<2;x3++){
                    for(int x4=0; x4<2;x4++){
                        for(int x5=0; x5<2;x5++){
                            for(int x6=0; x6<2;x6++){
                                System.out.println("x1="+ x1 + "\tx2="+ x2 + "\tx3="+ x3 + "\tx4="+ x4 + "\tx5="+ x5 + "\tx6="+ x6);
                                System.out.println(mat[x1][x2][x3][x4][x5][x6]);
                            }
                        }
                    }
                }
            }
        }
    }

    public void MatrixLoadFile() throws FileNotFoundException{
        //   int dim=10;
        String aux="",aux2="";
        String Chivo = "H:\\Git\\JavaFableCalculator\\files\\matrixq.txt";
        String Chivo2 = "H:\\Git\\JavaFableCalculator\\files\\Id_matrixq.txt";
        try {
            FileReader readFile = new FileReader(new File(Chivo));
            BufferedReader readLines = new BufferedReader(readFile);
            int x1=0,totalDatos=0,countDatos=0;
            String VecDatas[];
            while((aux = readLines.readLine())!=null)//read a case, a case is in a line each line is a case
            {
                VecDatas = (aux.split(",")); //the case it is comma separated.
                for(int x2=0; x2<dim;x2++){
                    for(int x3=0; x3<dim;x3++){
                        for(int x4=0; x4<dim;x4++){
                            for(int x5=0; x5<dim;x5++){
                                for(int x6=0; x6<dim;x6++){
                                    this.mat[x1][x2][x3][x4][x5][x6] = Double.parseDouble(VecDatas[countDatos]);
                                    countDatos++;
                                }
                            }
                        }
                    }
                }
                x1++;
                countDatos = 0;
            }
            readFile.close();
        } catch (IOException ex) {
            Logger.getLogger(MatrixQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            FileReader readFile2 = new FileReader(new File(Chivo2));
            BufferedReader readLines2 = new BufferedReader(readFile2);
            int x1_2=0,totalDatos2=0,countDatos2=0;
            String VecDatas2[];
            while((aux2 = readLines2.readLine())!=null)//read a case, a case is in a line each line is a case
            {
                VecDatas2 = (aux2.split(",")); //the case it is comma separated.
                for(int x2=0; x2<dim;x2++){
                    for(int x3=0; x3<dim;x3++){
                        for(int x4=0; x4<dim;x4++){
                            for(int x5=0; x5<dim;x5++){
                                for(int x6=0; x6<dim;x6++){
                                    matIds[x1_2][x2][x3][x4][x5][x6] = Integer.parseInt(VecDatas2[countDatos2]);
                                    countDatos2++;
                                }
                            }
                        }
                    }
                }
                x1_2++;
                countDatos2 = 0;
            }
            readFile2.close();

        } catch (IOException ex) {
            Logger.getLogger(MatrixQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  writeMatrix2("matwritepva",mat);
    }


    int MaxMatQ(int AR, int AU, int BR, int US, int CN)
    {

        double MaxVal = 0;
        double MaxVals[] = new double[dim];
        int rowMaxVal[] = new int[dim];
        int move = 0, maximo=0,tempmaximo=0;
        for(int ax = 0; ax<dim; ax++){
            if(this.mat[AR][AU][BR][US][CN][ax] >= MaxVal)
            {
                if(this.mat[AR][AU][BR][US][CN][ax] > MaxVal){
                    maximo = ax;
                    MaxVal = this.mat[AR][AU][BR][US][CN][ax];
                    move = 0;
                }else{
                    if(this.mat[AR][AU][BR][US][CN][ax] == MaxVal){
                        MaxVal = this.mat[AR][AU][BR][US][CN][ax];
                        MaxVals[move] = MaxVal;
                        rowMaxVal[move] = ax;
                        move++;
                    }
                }
            }
            System.out.println("Axion-vals("+ax+")-[" + this.mat[AR][AU][BR][US][CN][ax] + "]");
        }
        if(move > 1){
            tempmaximo = ThreadLocalRandom.current().nextInt(1, move);
            maximo = rowMaxVal[tempmaximo];
            System.out.println("\n[" + maximo + "]::"+MaxVals[maximo]);
        }else{
            System.out.print("\n[" + maximo + "]::"+MaxVal);
        }

        maxItId = this.matIds[AR][AU][BR][US][CN][maximo];

        return maximo;
    }

    void MatrixSaveFile(String filename) {
        String chivo = "H:\\Git\\JavaFableCalculator\\files\\" + filename + ".txt";

        String chivo2 = "H:\\Git\\JavaFableCalculator\\files\\Id_" + filename + ".txt";
        //int dim = 10;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(chivo));
            for(int x1=0; x1<dim;x1++){
                for(int x2=0; x2<dim;x2++){
                    for(int x3=0; x3<dim;x3++){
                        for(int x4=0; x4<dim;x4++){
                            for(int x5=0; x5<dim;x5++){
                                for(int x6=0; x6<dim;x6++){
                                    bw.write(this.mat[x1][x2][x3][x4][x5][x6] + ((x6 == this.mat[x1].length) ? "" : ","));
                                }
                            }
                        }
                    }
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();

            BufferedWriter bwId = new BufferedWriter(new FileWriter(chivo2));
            for(int x1=0; x1<dim;x1++){
                for(int x2=0; x2<dim;x2++){
                    for(int x3=0; x3<dim;x3++){
                        for(int x4=0; x4<dim;x4++){
                            for(int x5=0; x5<dim;x5++){
                                for(int x6=0; x6<dim;x6++){
                                    bwId.write(this.matIds[x1][x2][x3][x4][x5][x6] + ((x6 == this.matIds[x1].length) ? "" : ","));
                                }
                            }
                        }
                    }
                }
                bwId.newLine();
            }
            bwId.flush();
            bwId.close();



        } catch (IOException e) {}
    }

}

