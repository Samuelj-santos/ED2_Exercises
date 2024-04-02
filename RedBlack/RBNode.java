package RedBlack;

 class RBNode  <T extends Comparable<T>> {
    private T info;
   
    private RBNode<T> left;
    private RBNode<T> right;
    private RBNode<T> dad;
    private boolean status ; // se for true , ele foi "removido"
    private boolean color ; // se for false é preto se for true é vermelho
     
      public RBNode(T info ){
      this.info = info;
      }
     
    public RBNode<T> getLeft() {
        return left;
    }
    public void setLeft(RBNode<T> left) {
        this.left = left;
    }
    public RBNode<T> getRight() {
        return right;
    }
    public void setRight(RBNode<T> right) {
        this.right = right;
    }
    public RBNode<T> getDad() {
        return dad;
    }
    public void setDad(RBNode<T> pai) {
        this.dad = pai;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getColor() {
        return color;
    }
    public void setColor(boolean cor) {
        this.color = cor;
    }
     public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
    public void Swap(){
        if (this.color == false) {
            this.color = true;
            
        }else {
            this.color = false;
        }
    }
}
