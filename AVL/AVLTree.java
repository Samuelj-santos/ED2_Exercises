 package AVL;

class AVLTree <T extends Comparable<T>>{

   private AVLNode<T> raiz;

   public boolean isEmpty(){
    if (this.raiz == null) {
         return true;
    }else{
         return  false;
    }
   }
    
    public void inserir(T value){
        if (this.isEmpty() == true) {
            this.raiz = new AVLNode<T>(value);
        }else{
            this.raiz.inserir(value);
        }
    }

    
}