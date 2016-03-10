
package minorpro;
import weka.core.*;
/**
 *
 * @author jayan
 */
public class distValue 
{
    int index;
    double val;
    //String res;
    Instance ins;
    
    public distValue(int i,double v, Instance in)
    {
        index = i;
        val = v;
        ins = in;
    }
    
    public void setIndex(int i)
    {
        index = i;
    }
    
    public void setValue(double v)
    {
        val = v;
    }
    
    public void setInstance(Instance in)
    {
        ins = in;
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public double getValue()
    {
        return val;
    }
    
    public Instance getInstance()
    {
        return ins;
    }
    
    public String getResult()
    {
        return ins.stringValue(ins.numAttributes() - 1);
    }
    
    public void print()
    {
        System.out.print("Index: " + index + " ");
        System.out.print("Value: " + val + " ");
        System.out.print("Instances: ");
        for(int i = 0; i < ins.numAttributes(); i++)
        {
            String a = ins.attribute(i).name();
            System.out.print(a + " = " + ins.value(i) + " ");
        }
        System.out.println();
        
    }
}
