
// This class creates Fitness value for each individual.
package minorpro;


import weka.core.*;

public class Fitness 
{
     Instances trainset;
     wknn w;
    
    public Fitness(Instances t1,wknn w1)
    {
        trainset = t1;
        w = w1;
    }
    
    public  Instance genNextPass(Instance ins)
    {
        Instance min = null;
        double dist = Double.MAX_VALUE;
        for(int i = 0; i < trainset.numInstances(); i++)
        {
            Instance nxt = trainset.instance(i);
            String ds = nxt.stringValue(nxt.numAttributes() - 1);
            double d = w.dist(nxt,ins);
            if(d < dist && ds.equals("PASS"))
            {
                dist = d;
                min = nxt;
            }
        }
        return min;
    }
    
    

}
