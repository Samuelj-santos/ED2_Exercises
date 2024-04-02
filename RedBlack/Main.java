package RedBlack;

public class Main {
    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<Integer>();
        Integer [ ] inputs = new Integer[]{ 10,59,68,5,3,9,150,98};//150,98

        for (int i = 0; i < inputs.length; i++) {
         tree.insert(inputs[i]);  
          tree.passeioPorNivel();
          System.out.println("Interação "+ i );
          System.out.println();
        }
        
        
    }
}
