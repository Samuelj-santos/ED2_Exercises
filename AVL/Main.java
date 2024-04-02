package AVL;

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<Integer>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(15);
        tree.insert(30);
        tree.passeioPorNivel();
    }
}
