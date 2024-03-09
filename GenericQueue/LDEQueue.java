package GenericQueue;



public class LDEQueue <T extends Comparable<T>>{
    private LDENode<T> primeiro;
    private LDENode<T> ultimo;
    private int qtd;
    



    public void enQueue(T value ){
        LDENode<T> aux = this.primeiro;
        LDENode<T> novo = new LDENode<T>(value);

        if (isEmpty() == true) {// isEmpty usado para identificar que é o primeiro item a ser colocado na lista não para verificação em si
            this.primeiro = novo;
            this.ultimo = novo;
            this.qtd++;
        }else{
          while (aux != null) {
             
            
              if (aux.getProx() == null) {
                aux.setProx(novo);
                aux.getProx().setAnt(aux);
                this.ultimo = novo;
                this.ultimo.setProx(null);
                this.qtd--;
                break;
              }else{
                aux = aux.getProx();
              }

          }
        }
    }


    public T deQueue(){
        
        T retorno ;
        retorno = this.primeiro.getInfo();
         this.primeiro = this.primeiro.getProx();
         this.primeiro.setAnt(null);
         this.qtd--;
       
         return retorno;
        
    }

    public T  head(){
        return this.primeiro.getInfo();
    }

    public boolean isFull(){
        return false;
    }
    public boolean isEmpty() { 
        if (this.primeiro == null && this.ultimo == null && this.qtd == 0) {
            return true;
        } else {
            return false;
        }
    }
}
