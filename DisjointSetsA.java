import java.io.*;
import java.util.*;


/****************************
 *
 * COMP251 template file
 *
 * Assignment 1, Question 2a
 * No collaborators
 *
 *****************************/


public class DisjointSetsA {

    private int[] par;  // par[i] = its parent
    private int[] rank;  // rank[i] = its rank

    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSetsA(int n) {
        if (n>0) {   // n is total nodes
            par = new int[n];
            rank = new int[n];  // no rank yet because all individual nodes (0)
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;   // create nodes (all roots)
            }
        }
    }

    /* For printing purpose */
    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }

    /* find representative of element i */
    public int find(int i) {  // time complexity?
        if (par[i] == i) {
            return i;
        } else {
            par[i] = find(par[i]); //path compression: all nodes point to its top parent
            return par[i];
        }

        /* Fill this method (The statement return 0 is here only to compile) */
        // implement path compression?
        //return 0;

    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {
        /*** placeholder: need to do based on rank ***/
        // root of small becomes child of large root; return representative (large root)
        // if identical rank; merge i into j
        // edge case = i, j same set
        // update rank when updating trees of same height
        int small = find(i); // representative of i and j
        int big = find(j);

        if (rank[big] < rank[small]) {
            int temp = small;
            small = big; // swap
            big = temp;
        }

        if (small != big) {   // different representatives
            par[small] = big;  // small representative points to big representative
            if (rank[small] == rank[big]) {
                rank[big]++;
            }
        }

        return big;
    }

    public static void main(String[] args) {

        DisjointSetsA myset = new DisjointSetsA(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);

    }

}