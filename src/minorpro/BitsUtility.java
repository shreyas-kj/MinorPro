/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minorpro;
import weka.core.Instance;

public class BitsUtility 
{

  public static String convertToBits(Instance ins)
    {
        String bits = "";
        for(int i = 0; i < ins.numAttributes() - 1; i++)
        {
            double d = 0, a = ins.value(i);
            switch(i)
            {
                case 0 : d = (a + 7)/2;
                    break;
                case 1 : d = a - 4;
                    break;
                case 2 :
                case 3 : d = a;
                    break;                    
            }
            
           bits = bits + intToBits(d);           
        }
        return bits;
    }
    
    public static String intToBits(double x)
    {
        int s = new Double(x).intValue();
        String b = Integer.toBinaryString(s);
        int len = b.length();
        if(len < 4)
        {
            for(int i = 0; i < 4 - len; i++)
            {
                b="0"+b;
            }
            
        }
        return b;
    }
    
    public static boolean[] generate(Instance ins)
    {
        String str = convertToBits(ins);
        boolean[] bs = new boolean[16];
        for(int i=0; i < str.length(); i++)
        {
            char a = str.charAt(i);
            bs[i] = a == '1';
        }
        return bs;
    }
    
    public static String prnBS(boolean[] bs)
    {
        int len = bs.length;
        char[] a = new char[len];
        for(int i=0; i < len; i++)
        {
            if(bs[i])
            {
                a[i] = '1';
            }
            else
            {
                a[i] = '0';
            }
        }
        String str = new String(a);
        return str;
    }
}
