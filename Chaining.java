import java.io.*;
import java.util.*;

// No collaborators

public class Chaining {
    
     public int m; // number of SLOTS 
     public int A; // the default random number
     int w;
     int r; // w > r; r=ceiling(w/2);
     public ArrayList<ArrayList<Integer>>  Table; // hash table

    // if A==-1, then a random A is generated. else, input A is used.
    protected Chaining(int w, int seed, int A){
         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);  // m = slots = 2^r
         this.Table = new ArrayList<ArrayList<Integer>>(m);
         for (int i=0; i<m; i++) {
             Table.add(new ArrayList<Integer>());
         }
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
         else{
             this.A = A;
         }

     }

    /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     //generate a random number in a range (for A)
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;     
    }




    /**Implements the hash function h(k)*/
    public int chain (int key) {
        // TODO: implement this and change the return statement
        int preShift = (A * key) % power2(w);
        int shAmt = w - r;
        int postShift = preShift >> shAmt;

        if (postShift >= 0 && postShift <= m) {
            return postShift;
        }
        return -1;
    }
        
    
    /**Inserts key k into hash table. Returns the number of collisions encountered*/
    public int insertKey(int key){
        //TODO: implement this and change the return statement
        int index = chain(key);  // use hash

        /** Do I need to test if the index is in table? **/

        int collisions = Table.get(index).size();  // get collisions

        Table.get(index).add(key); // insert key

        return collisions;

    }

    
    
    /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
    public int insertKeyArray (int[] keyArray){
        int collision = 0;
        for (int key: keyArray) {
            collision += insertKey(key);
        }
        return collision;
    }


}