/*
Class used to depict genotype of each instances for GA.
Roughly, it represents student's performance in each category and its overall performance in the form of tuples.
The value of each attribute are stored in 4 bit string(implemented using boolean array).
Since 4 attributes are used, therfore 16 bit string used to denote overall tuple's value
This class also stores information on whether tuple belongs to training set and,
also whether student's overall performance is at Risk or above Risk.
*/
package minorpro;
import weka.core.*;

public class Individual 
{
    boolean[] val,fitness;
    //int fitness;
    int index;
    boolean isTrain,atRisk;
    //static int len = 16;
    
    //Initial constructor
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
    
    //Returns state of individual genes.
    public boolean getGene(int ind)
    {
        return val[ind];
    }
    
    //Changes state of each gene
    public void setGene(int ind,boolean gval)
    {
        val[ind] = gval;
    }
    
    //Returns if tuple belongs to training set
    public boolean isTrained()
    {
        return isTrain;
    }
    
    //Returns if tuples belongs to atRisk
    public boolean isAtRisk()
    {
        if(!atRisk)
        {
            return getFitness();
        }
        return atRisk;
    }
    
    //Returns tuples index number
    public int getIndex()
    {
        return index;
    }
    
    //Determines if given chromosome haa attained required fitness 
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
