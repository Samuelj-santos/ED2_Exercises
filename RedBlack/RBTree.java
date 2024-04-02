package RedBlack;
import java.util.LinkedList;
 
 public class RBTree   <T extends Comparable<T>>{ // mexer dnv nesse codigp , ele ainda esta estranho , implementar remoção de verdade  
    private RBNode<T> root;

    public boolean isEmpty(){
        if (this.root == null) {
            return true;
        }
        return false ;
    }
 

    private RBNode<T> reestruturar(RBNode<T> avo , RBNode<T> neto  ){//metodo para recolorir e rotacionar
     if ((avo.getLeft() == null ||avo.getLeft().getColor() == false ) && avo.getRight().getColor() == true) {

       // rotação á esquerda 
     
       RBNode<T> pai = avo.getRight();
       if (pai.getRight() == neto) {//simples esquerda

       
       boolean isRoot = false;
       if (avo == this.root) {
        isRoot = true;
       }
       avo.setRight(pai.getLeft());
       pai.setLeft(avo);
       pai.setDad(avo.getDad());
       if (avo.getDad() != null ) {
        if (avo.getDad().getLeft() == avo) {
            avo.getDad().setLeft(pai);
        }else if (avo.getDad().getRight() == avo) {
            avo.getDad().setRight(pai);
        }
       }
       avo.setDad(pai);
       avo = pai;
       if (isRoot == true) {
        avo.setDad(null);
       }
       avo.setColor(false);
       avo.getRight().setColor(true);
       avo.getLeft().setColor(true);
       return avo ; 

        
       }else if (pai.getLeft() == neto) {//dupla esquerda 

       
         boolean isRoot = false;
        if (avo == this.root) {
            isRoot = true;
        }
       neto = pai.getLeft();
       pai.setLeft(neto.getRight());
       neto.setRight(pai);
       avo.setRight(neto.getLeft());
       neto.setLeft(avo);
       neto.setDad(avo.getDad());
       if (avo.getDad() != null ) {
        if (avo.getDad().getLeft() == avo) {
            avo.getDad().setLeft(neto);
        }else if (avo.getDad().getRight() == avo) {
            avo.getDad().setRight(neto);
        }
       }
       avo = neto;
       if (isRoot == true) {
        avo.setDad(null);
       }
       avo.setColor(false);
       avo.getLeft().setColor(true);
       avo.getRight().setColor(true);
       return avo;
        
       }
        
       }else if (avo.getLeft().getColor() == true && (avo.getRight() == null ||avo.getRight().getColor() == false ) ) {// rotação à direita 

       
       RBNode<T> pai = avo.getLeft();
       if (pai.getLeft() == neto) { //direita simples

      
       boolean isRoot = false;
       if (avo == this.root) {
        isRoot = true;
       }

       avo.setLeft(pai.getRight());
       pai.setRight(avo);
       pai.setDad(avo.getDad());
       if (avo.getDad() != null ) {
        if (avo.getDad().getLeft() == avo) {
            avo.getDad().setLeft(pai);
        }else if (avo.getDad().getRight() == avo) {
            avo.getDad().setRight(pai);
        }
       }
       avo.setDad(pai);
       avo = pai;
       if (isRoot == true) {
        avo.setDad(null);
       }
       avo.setColor(false);
       avo.getRight().setColor(true);
       avo.getLeft().setColor(true);
       return avo ; 
        
       }else if (pai.getRight() == neto) {//direita dupla

       
        boolean isRoot = false;
        if (avo == this.root) {
            isRoot = true;
        }
       neto = pai.getRight();
       pai.setRight(neto.getLeft());
       neto.setLeft(pai);
       avo.setLeft(neto.getRight());
       neto.setRight(avo);
       neto.setDad(avo.getDad());
       if (avo.getDad() != null ) {
        if (avo.getDad().getLeft() == avo) {
            avo.getDad().setLeft(neto);
        }else if (avo.getDad().getRight() == avo) {
            avo.getDad().setRight(neto);
        }
       }
       avo = neto;
       if (isRoot == true) {
        avo.setDad(null);
       }
       avo.setColor(false);
       avo.getLeft().setColor(true);
       avo.getRight().setColor(true);
       return avo;
        
       }
        
       }  if (avo.getLeft().getColor() == true && avo.getRight().getColor() == true) {//recolorir

       
       if (avo != this.root) {
        avo.setColor(true);
       }
       
       avo.getRight().setColor(false);
       avo.getLeft().setColor(false);
       return avo;
       
        
       } 
        return null ;
    }
    

    public void insert(T info ){//metodo de inserção 
        RBNode<T> newNode = new RBNode<T>(info);

       if (this.isEmpty() == true ) {//aarvore vazia 

        this.root = newNode;

       }else {//arvore com pelo ao menos um nó 
         
        newNode.Swap();//troca a "cor" do nó 
        RBNode<T> aux = this.root;

         while (aux != null) {//desce a arvore procurando uma posição livre
            if (newNode.getInfo().compareTo(aux.getInfo()) == -1) {// se for menor 

                if (aux.getLeft() == null) {

                    aux.setLeft(newNode);
                    newNode.setDad(aux);

                    boolean isRoot = false;//saber se é a root  
                    if (newNode.getDad().getColor() == true) {//saber se o pai é vermelho
                        int aux1  = 1;//aux para loop 
                        RBNode<T> avo = newNode.getDad().getDad();//avo de k 
                        if (avo.getDad() == null) {//se o  avo não tiver pai ele é raiz 
                            isRoot = true;
                        }
                      do {
                        avo = reestruturar(avo, newNode);//metodo de reestruturação 
                        if ( avo.getDad() == null   || avo.getDad().getColor() == false || (avo.getDad().getColor() == true  && avo.getColor() == false) || (avo.getDad().getColor() == false && avo.getColor() == false)) {//possibilidades de pai do avo , serve para quebrar o loop 
                           
                            break;
                        }
                      } while (aux1 != 0);

                      if ( isRoot == true) {//testa se é raiz 
                      this.root = avo;
                      }
                      if (avo.getDad() != null && avo == avo.getDad().getRight()) { // quer saber em que posição em relação ao pai de avo ele esta , direita ou esquerda 
                        avo.getDad().setRight(avo);
                     }else if (avo.getDad() != null && avo == avo.getDad().getLeft()) {
                       avo.getDad().setLeft(avo);
                     }
                    }
                   
                    break;

                }else{

                     aux = aux.getLeft();//passa p proxima posição 
 
                }
               
            }else { // se for maior 
              
                if (aux.getRight() == null) {  //para não ser redundante o metodo é igual , mas ele é feito na direita 

                    aux.setRight(newNode);
                    newNode.setDad(aux);

                    boolean isRoot = false;//testa se é raiz 
                    if (newNode.getDad().getColor() == true) {
                        int aux1  = 1; // aux para loop 
                        RBNode<T> avo = newNode.getDad().getDad(); // avo
                        if (avo.getDad() == null) {//testa se é raiz  
                            isRoot = true;
                        }
                        do {
                        avo = reestruturar(avo, newNode);// metodo de reestruturação 
                        if (avo.getDad() == null   || avo.getDad().getColor() == false || (avo.getDad().getColor() == true  && avo.getColor() == false) || (avo.getDad().getColor() == false && avo.getColor() == false)) { // testa a possibilidades de pais do avo 
                           
                            break;
                        }
                      } while (aux1 != 0);

                      if ( isRoot == true) {
                        this.root = avo;
                        
                        }
                        if (avo.getDad() != null && avo == avo.getDad().getRight()) { // quer saber em que posição em relação ao pai de avo ele esta , direita ou esquerda
                             avo.getDad().setRight(avo);
                          }else if (avo.getDad() != null && avo == avo.getDad().getLeft()) {
                            avo.getDad().setLeft(avo);
                          }
                        
                    }
                    break;

                }else{

                     aux = aux.getRight();

                }
            }
         }

       }
       
    }

    
    public void passeioPorNivel () { // passeio comum , o mesmo que esta no slide 
        LinkedList<RBNode<T>> fila ;
         
        RBNode<T> aux;
        if (this.isEmpty() == false) {
            fila = new LinkedList<RBNode<T>>();
            fila.addLast(this.root); 

            while (fila.isEmpty() == false) {
                aux = fila.pop();

                if (aux.getLeft() != null) {
                    fila.addLast(aux.getLeft());

                }if (aux.getRight() != null) {
                    fila.addLast(aux.getRight());

                }
                if (aux.getStatus() != true ) {
                     System.out.println(aux.getInfo() + " " + aux.getColor() );
                }
                 
                }
                        }
    }

    public void emOrdem () { // passeio em ordem comum , o mesmo que esta no slide
        if (this.isEmpty() == true) {
            System.out.println("Árvore vazia");
        } else {
            this.percorrerEmOrdem (root);
        }
    }

    private void percorrerEmOrdem (RBNode<T> r) { // aux de emOrdem
        if (r != null) {
            percorrerEmOrdem (r.getLeft());
            if (r.getStatus() != true ) {
                System.out.println(r.getInfo() + " " + r.getColor());
            }
            
            percorrerEmOrdem (r.getRight());
        }
    }

    public void remover (T info){//remoção preguiçosa 
        RBNode<T> copia_do_no = new RBNode<T>(info);

        RBNode<T> aux = this.root;

        while (aux != null ) {
            if (copia_do_no.getInfo().compareTo(aux.getInfo()) == -1) {
                
            aux = aux.getLeft();

            }else if (copia_do_no.getInfo().compareTo(aux.getInfo()) == 1) {
                aux = aux.getRight();
            }else if (copia_do_no.getInfo().compareTo(aux.getInfo()) == 0){
                aux.setStatus(true);
            }
        }
    }
}
