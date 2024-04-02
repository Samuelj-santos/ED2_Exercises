package AVL;


public class app {
    public static void main (String[] args) {
        AVLTree<Integer> arvore = new AVLTree<>();

        // Inserir alguns valores
        int[] inserir = {63,27,85,15,34,81,97,5,45,99};
        int[] remover = {85, 34,45};

        System.out.println("Inserir");
        for (int valor : inserir) {
            arvore.insert(valor);
            arvore.passeioPorNivel();
            System.out.println();
        }
        System.out.println("NivelL");
        arvore.passeioPorNivel();
        System.out.println("\nOrdemL");
        arvore.emOrdem();
        System.out.println();

        System.out.println("Remover:");
        for (int valor : remover) {
            arvore.removerNo(arvore.getRoot(), valor);
            arvore.passeioPorNivel();
            System.out.println();
        }

        // Exibir a árvore usando percurso por nível
        System.out.println("NivelR");
        arvore.passeioPorNivel();
        System.out.println("\nOrdemR");
        arvore.emOrdem();
      
    }
}
