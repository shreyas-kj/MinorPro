/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minorpro;

import java.awt.*;
import java.io.*;
//import javax.swing.JFileChooser;
import weka.core.Instances;


public class MinorPro 
{
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
           wknn test = new wknn(trainset,testset,5);
           test.testAll();
       }    
        catch(Exception ex)
        {
            System.out.println("Exception caught: " + ex.toString());
        }
    }
    
}
