/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFC.IIASA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author orduna
 */
public class LoadTableNames {
    ArrayList tablas;
     public ArrayList GetData() {
         tablas = new ArrayList();
      File file = null;
      FileReader fr = null;
      BufferedReader br = null;
      String Location = "H:\\Git\\JavaFableCalculator\\JavaFableCalculator\\files\\Tables.txt";
      try {
         // definition of the name of the file, using the buffer and read line by line
         file = new File (Location);
         fr = new FileReader (file);
         br = new BufferedReader(fr);
 
         // Reading line by line until the end of the file
         String linea;
         while((linea=br.readLine())!=null)
            tablas.add(linea);
      } 
      //if any error occurs it is showed.
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // finally the file is closed
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return tablas;
    }
}
