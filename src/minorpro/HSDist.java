
package minorpro;

/**
 *
 * @author jayan
 */
public class HSDist 
{
   
    private static distValue[] a;
    private static int n;
    private static int left;
    private static int right;
    private static int largest;

    
    public static void buildheap(distValue[] a){
       n=a.length-1;
        for(int i=n/2;i>=0;i--){
            maxheap(a,i);
        }
    }
    
    public static void maxheap(distValue[] a, int i){ 
        //n = a.length - 1;
        left=2*i;
        right=2*i+1;
        if(left <= n && a[left].getValue() > a[i].getValue()){
            largest=left;
        }
        else{
            largest=i;
        }
        
        if(right <= n && a[right].getValue() > a[largest].getValue()){
            largest=right;
        }
        if(largest!=i){
            exchange(i,largest);
            maxheap(a, largest);
        }
    }
    
    public static void exchange(int i, int j){
        distValue t=a[i];
        a[i]=a[j];
        a[j]=t; 
        }
    
    public static void sort(distValue []a0){
        a=a0;
        buildheap(a);
        
        for(int i=n;i>0;i--){
            exchange(0, i);
            n=n-1;
            maxheap(a, 0);
        }
    }
}
