/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minorpro;

import java.io.*;
import java.awt.*;
import weka.core.*;

/**
 *
 * @author jayan
 */
public class wttest {

   public static int[] intVal(boolean[] a)
    {
        int[] v = new int[4];
        String bs = BitsUtility.prnBS(a);
        for(int i = 0; i < v.length; i++)
        {
            int x = 4 * i;
            String bp = bs.substring(x, x+4);
            v[i] = Integer.parseInt(bp,2);
        }
        return v;
    }
   
   public static Instances getDataset(String title) throws Exception
   {
            Frame ab = null;  
            FileDialog fd = new FileDialog(ab,title,FileDialog.LOAD);
            fd.setVisible(true);
            String fname = fd.getDirectory() + fd.getFile();
            fd.dispose();
            File f1 = new File(fname);
            Instances dataset = new Instances(new BufferedReader(new FileReader(f1)));
            dataset.setClassIndex(dataset.numAttributes() - 1);
            return dataset;
   }
    
    public static void main(String[] args) 
    {
          try
          {         
                     
            Instances trainset = getDataset("Select training set");                        
            Instances testset = getDataset("Select testing set");
            wknn w = new wknn(trainset,testset,5);
            distValue[] near = w.knnArray(4, true);
            for(distValue n : near)
            {
                n.print();
            }
           
          }
          catch(Exception ex)
          {
              System.out.println("Exception caught: " + ex.getMessage());
          }
    }
    
}
