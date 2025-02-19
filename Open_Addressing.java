import java.io.*;
import java.util.*;

// No collaborators

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
     /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }

     public static int generateRandom(int min, int max, int seed) {
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }

     /* h(k) */
    private int chain (int key) {
        // TODO: implement this and change the return statement
        int preShift = (A * key) % power2(w);
        return preShift >> (w - r);
    }

     /**Implements the hash function g(k)*/
     public int probe(int key, int i) {
         //TODO: implement this function and change the return statement.
         int preMod = chain(key) + i;
         int postMod = preMod % power2(r);

         if (postMod >= 0 && postMod <= m) {
             return postMod;
         }
         return -1;
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //TODO : implement this and change the return statement.
            int collisions = 0;
            int index = probe(key, collisions);

            while(Table[index] > -1 && collisions<m) {  // change to > -1
                index = probe(key, ++collisions);  // ADC
            } // probe again if bucket NOT empty

            Table[index] = key; // empty bucket! ADD key

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
            
         /**Removes key k from the hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            //TODO: implement this and change the return statement
            int slots = 0;
            int index = probe(key, slots);

            while((Table[index] != key) && slots < m) { // is the key in the bucket? or end?
                if (Table[index] == -1) {
                    return slots + 1;
                }
                index = probe(key, ++slots);
            }

            Table[index] = -2; // EMPTY the bucket (-2 = deleted)

            return slots;
        }
}
