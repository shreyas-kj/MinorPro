
package minorpro;
import weka.core.*;

public class Individual 
{
    boolean[] val,fitness;
    //int fitness;
    int index;
    boolean isTrain,atRisk;
    //static int len = 16;
    
    public Individual(Instance ins,int i,boolean it, Fitness fit)
    {
        val = BitsUtility.generate(ins);
        index = i;
        isTrain = it;
        String cls = ins.stringValue(ins.classIndex());
        atRisk = !cls.equals("PASS");
        if(atRisk)
        {
            Instance f = fit.genNextPass(ins);
            fitness = BitsUtility.generate(f);
        }        
    }
    
    public boolean getGene(int ind)
    {
        return val[ind];
    }
    
    public void setGene(int ind,boolean gval)
    {
        val[ind] = gval;
    }
    
    public boolean isTrained()
    {
        return isTrain;
    }
    
    public boolean isAtRisk()
    {
        if(!atRisk)
        {
            return getFitness();
        }
        return atRisk;
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public boolean getFitness()
    {
        boolean isFit = true;
        int[] va = intVal(val);
        int[] fa = intVal(fitness);
        for(int i=0 ; i < va.length && isFit; i++)
        {
            if(va[i] < fa[i])
            {
                isFit = false;
            }
        }
        return isFit;
    }
    
    private int[] intVal(boolean[] a)
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
    
    

}
