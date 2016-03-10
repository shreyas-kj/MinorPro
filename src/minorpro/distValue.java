
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
}
