package JFC.IIASA;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrixQ {
    public int [][] mat;
    public int rows, cols;

    public MatrixQ() {
        this.rows = 67;
        this.cols = 32;
        this.mat = new int[rows][cols];
    }

    public int getToken(int getRow, int getCol){
      //  System.out.println("r:"+getRow+",c:"+getCol);
        return(mat[getRow][getCol]);
    }
    public void MatrixLoadFile() throws FileNotFoundException{
        String aux="";
        String Matrix = "H:\\Git\\JavaFableCalculator\\JavaFableCalculator\\files\\LexMatrix.txt";
        try {
            FileReader readFile = new FileReader(new File(Matrix));
            BufferedReader readLines = new BufferedReader(readFile);
            String VecDatas[];
            int r=0;
            while((aux = readLines.readLine())!=null)//read a case, a case is in a line each line is a case
            {
                VecDatas = (aux.split(",")); //the case it is comma separated.
                for(int c=0; c<this.cols;c++){
                    this.mat[r][c] = Integer.parseInt(VecDatas[c]);
                    }
                r++;
            }
            readFile.close();
        } catch (IOException ex) {
            Logger.getLogger(MatrixQ.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public int getCol(char c)  {
        //0	     1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	    24	25	26	27	28	29	    30	31
        //a-z	0-9	_	,	(	)	[	]	>	<	=	+	-	*	/	.	e	#	 "	'	$	!	%	space	\t	:	\n	@	\	tilde~	ˆ	&
        int col=0;int tempascci=0;

        tempascci = (int) c;
        if (Character.isLetter(c)){
            col = 0;
        }
        if (Character.isDigit(c)){
            col = 1;
        }
        if(tempascci == 34){//"
            col = 18;
        }
        if(tempascci == 35){ //#
            col = 17;
        }
        if(tempascci == 39){ //'
            col = 19;
        }
        if(tempascci == 9){ //\t
            col = 24;
        }
        if(tempascci == 32){ //' '
            col = 23;
        }
        if(tempascci == 10){ //\n
            col = 26;
        }
        if(tempascci == 92){ //\
            col = 28;
        }
        if(tempascci == 126){ //tilde
            col = 29;
        }
        if(tempascci == 94){ //\n
            col = 30;
        }
        if(tempascci == 38){ //&
            col = 31;
        }

        switch(c)
        {
            case '_': //95
                col=2;
                break;
            case ',': //44
                col=3;
                break;
            case '('://40
                col=4;
                break;
            case ')'://41
                col=5;
                break;
            case '['://91
                col=6;
                break;
            case ']'://93
                col=7;
                break;
            case '>'://62
                col=8;
                break;
            case '<'://60
                col=9;
                break;
            case '='://61
                col=10;
                break;
            case '+'://43
                col=11;
                break;
            case '-': //45
                col=12;
                break;
            case '*': //42
                col=13;
                break;
            case '/': //47
                col=14;
                break;
            case '.': //46
                col=15;
                break;
            case '$': //36
                col=20;
                break;
            case '!': //33
                col=21;
                break;
            case '%': //37
                col=22;
                break;
            case ':': //58
                col=25;
                break;
                case '@': //
            col=27;
            break;
            case 'ˆ': //58
                col=30;
                break;

        }


        return col;

    }
}

