/****************************
*
* COMP251 template file
*
* Assignment 1, Question 2b
*
*****************************/

/* contructor: creates a partition of n elements. */
/* Each element is in a separate disjoint set */
public class DisjointSetsB {

    private int[] par;
    private int[] rank;

    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSetsB(int n) {
        if (n>0) {
            par = new int[n+1];
            rank = new int[n+1];
            for (int i=0; i<this.par.length; i++) { //changed this to match output from class
                par[i] = i;
            }
        }
    }

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

    /* find resentative of element i */
    public int find(int i) {
        if (par[i] == i) {
            return i;
        } else {
            par[i] = find(par[i]); //path compression: all nodes point to its top parent
            return par[i];
        }
    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {
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

    /* move i to the set containing j */
    public void move(int i, int j) {
        //nothing is moving: even moving when 2 representative with no children doesnt work

        int repI = find(i);
        int repJ = find(j);

        if (repI == repJ) {     // same set
            return;
        } else if (i != repI) {     // if a child
            par[i] = repJ;
            return;
        } // otherwise i is the representative
        int firstChild = -1;  // for the new rep

        // to update children to new node
        for (int node = 0; node < par.length; node++) { // for each number (node) 0 to n-1
            if (find(node) == repI && node != i) { // if current node has the same parent
                if (firstChild == -1) {
                    par[node] = node; // first child is new rep
                    firstChild = node;
                } else {
                    par[node] = firstChild; // want next nodes to point to first child / new rep
                }
            }
        }
        par[i] = repJ; // to update i
    }

    /* return the sum of elements in the set of i */
    public int sum_elements(int i) {
        int counter = 0;
        int repI = find(i);

        for (int node = 0; node < par.length; node ++) {
            if (find(node) == repI) {
                counter += node;
            }
        }
        /* Fill this method */
        return counter;
    }

    public static void main(String[] args) {
        /***
         *  Test Case 3:
         *  Predicted Problem:
         *  move(1,3) is acting like a union, either 3 is entering (1,2) or (1,2) is entering 3
         *
         *  - make sure i is the value being moved
         *  - check parents
         *  new DisjointSets(3):{0}, {1}, {2}, {3}
         * • move(2,1):{0}, {1, 2}, {3}
         * • union(2,1):{0}, {1, 2}, {3}
         * • move(1,3):{0}, {2}, {1, 3}
         * • sum_elements(3): 4
         ***/

        /*DisjointSetsB test = new DisjointSetsB(3);
        System.out.println(test);

        test.move(1,2); // both own representatives but no children (parents important)
        System.out.println("move 1 2");
        System.out.println(test);
        System.out.println(par[1]);
        System.out.println(par[2]);*/



        /*System.out.println("sum 1");
        System.out.println(test.sum_elements(1));
        System.out.println("sum 0");
        System.out.println(test.sum_elements(0));*/

        DisjointSetsB myset = new DisjointSetsB(6);
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
