package AVL;


public class AVLNode <T extends Comparable<T>> {

  private AVLNode <T> left;
  private AVLNode <T> right;
  private T info;
  private int FatBal;


  public AVLNode(T info) {
    this.info = info;
}

public AVLNode<T> getLeft() {
    return left;
}
public void setLeft(AVLNode<T> left) {
    this.left = left;
}
public AVLNode<T> getRight() {
    return right;
}
public void setRight(AVLNode<T> right) {
    this.right = right;
}
public T getInfo() {
    return info;
}
public void setInfo(T info) {
    this.info = info;
}
public int getFatBal() {
    return FatBal;
}
public void setFatBal(int fatBal) {
    FatBal = fatBal;
}


public void inserir (T value ){//incrementar o FatBal , criar a rotação simples e dupla 

   if (this.info.compareTo(value)>0) {

    if (this.left == null) {
        this.setLeft(new AVLNode<T>(value));
    }else{
        this.getLeft().inserir(value);
        this.setFatBal(this.FatBal-1);

        if (this.getFatBal() == -2 && this.getLeft().getFatBal() == -1) {
            //rotação simples a direita

            

        }else if (this.getFatBal() == -2 && this.getLeft().getFatBal() == 1) {
            //rotação dupla a direita

        }
    }

   }else{

    if(this.right ==null){
        this.setRight(new AVLNode<T>(value));
    }else{
        this.getRight().inserir(value);
        this.setFatBal(this.FatBal+1);
        
        if (this.getFatBal() == 2 && this.getRight().getFatBal() == -1) {
            //rotação simples a esquerda

        }else if (this.getFatBal() == 2 && this.getRight().getFatBal() == 1) {
            //rotação dupla a esquerda 

        }
    }

   }
}

   

}
