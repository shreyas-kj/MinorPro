
package minorpro;
//import java.io.*;
import java.util.Arrays;
import weka.core.*;

public class wknn 
{
    Instances trainset,testset;
    int k;
    public wknn(Instances t1,Instances t2,int n) throws Exception
    {
        trainset = t1;        
        testset = t2;
        k = n;
    }
    
    public void printDist(distValue[] a,int test_att)
    {
        Instance test = testset.instance(test_att);
        assignDist(test,a);
        HSDist.sort(a);
        
        for (distValue a1 : a) 
        {
            double val = (double)Math.round(a1.getValue() * 100000 )/ 100000;
            System.out.print((a1.getIndex() + 1) + "," + val + "," + a1.getResult().charAt(0) + " | ");
        }
    }
    
    public void printAll()
    {
        int max = trainset.numInstances();
        distValue[] dv = new distValue[max];
        int test_tup = testset.numInstances();
        System.out.print("Testing/training by dist  ");
        for(int i = 1; i <= max; i++)
        {
            System.out.print(i + "\t");
        }
        System.out.println();
        for(int i=0; i < test_tup; i++)
        {
            int x = i + 1;
            System.out.print( x + " ");
            printDist(dv,i);
            System.out.println();
        }
    }
    
    public double dist(Instance train,Instance test)
    {
        double sq = 0.0;
        for(int j = 0; j < test.numAttributes()-1 ; j++)
        {
            double a = test.value(j);
            double b = train.value(j);
            double di = Math.pow(a-b,2) * weight(a,j) ;
            sq = sq +  di ;
        }
        double ans = Math.sqrt(sq);
        return ans;
    }
    
    double weight(double x,int i)
    {
        double max1 = minOrMax(trainset,i,true);
        double max2 = minOrMax(testset,i,true);
        double min1 = minOrMax(trainset,i,false);
        double min2 = minOrMax(testset,i,false);
        double max = (max1 >= max2) ? max1 : max2 ;
        double min = (min1 <= min2) ? min1 : min2 ;
        double wt = Math.abs((x-min)/(max - min));
        return wt;
    }
    
    double minOrMax(Instances ins,int num,boolean isMax)
    {
        int size = ins.numInstances();
        double[] rng = new double[size];
        for(int i=0; i < size; i++)
        {
            Instance a = ins.instance(i);
            rng[i] = a.value(num);
        }
        Arrays.sort(rng);
        if(isMax)
        {
            return rng[size - 1];
        }
        return rng[0];
    }
    
    void assignDist(Instance test,distValue[] a)
    {
        //Instance test = testset.instance(i);
        for(int j=0 ; j < trainset.numInstances(); j++)
        {
            Instance train = trainset.instance(j);
            a[j] = new distValue(j,dist(train,test),train);
        }
    }
    
    void predictClass(distValue[] a,int test_att)
    {
        int numPass = 0, numFail = 0;
        Instance test = testset.instance(test_att);
        assignDist(test,a);
        HSDist.sort(a);
        for(int i=0; i < k; i++)
        {
            String cls = a[i].getResult();
            switch (cls) {
                case "PASS":
                    numPass++;
                    break;
                case "SEPLI":
                case "FAIL":
                    numFail++;
                    break;
            }
        }
        System.out.print("Instance number " + (test_att + 1) + " : ");
        for(int j=0 ; j < test.numAttributes() - 1; j++)
        {
            String attName = test.attribute(j).name();
            double value = test.value(j);
            System.out.print(attName + "=" + value + " " );
        }
        System.out.print(test.classAttribute().name() + "=" + test.stringValue(test.classIndex()) + " ");
        if(numPass > numFail)
        {
            System.out.println("Result = Pass");
        }
        else if(numPass < numFail)
        {
            System.out.println("Result = Fail");        
        }
        else
        {
            System.out.println("Error");
        }
    }
    
    public distValue[] knnArray(int index,boolean isTrained)
    {
        distValue[] near = new distValue[k];
        int max = trainset.numInstances();
        distValue[] tset = new distValue[max];        
        if(isTrained)
        {
           Instance ins = trainset.instance(index);
           assignDist(ins,tset);
           HSDist.sort(tset);
           int num = 0, i = 0;
           while(num < k)
           {
               if(tset[i].getIndex() != index)
               {
                   near[num] = tset[i];
               }
               i++;
           }
        }
        else
        {            
           Instance ins = testset.instance(index);
           assignDist(ins,tset);
           HSDist.sort(tset);
           System.arraycopy(tset, 0, near, 0, k);
        }
        
        return near;        
    }
    
    public boolean allPassed(int index,boolean isTrained)
    {
        distValue[] near = knnArray(index,isTrained);
        boolean noFail = true;
        for(int i=0 ; i < k && noFail; i++)
        {
            String str = near[i].getResult();
            if(!str.equals("PASS"))
            {
                noFail = false;
            }
        }
        return noFail;
    }
    
    public boolean predictResult(int ins)
    {
        int numPass = 0, numFail = 0;
        distValue[] a = new distValue[trainset.numInstances()];
        Instance test = testset.instance(ins);
        assignDist(test,a);
        HSDist.sort(a);
        for(int i=0; i < k; i++)
        {
            String cls = a[i].getResult();
            switch (cls) {
                case "PASS":
                    numPass++;
                    break;
                case "SEPLI":
                case "FAIL":
                    numFail++;
                    break;
            }
        }
        return (numPass > numFail);
    }
    public void testAll()
    {
        int max = trainset.numInstances();
        distValue[] dv = new distValue[max];
        int test_tup = testset.numInstances();
        for(int i=0; i < test_tup; i++)
        {
            predictClass(dv,i);
        }
    }
}
