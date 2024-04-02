 package AVL;
 import java.util.LinkedList;
 
 

public class AVLTree <T extends Comparable<T>>{ // Esse codigo ainda perde alguns filhos no processo de remoção ,  

   private AVLNode<T> root;
   public AVLNode<T> getRoot() {
    return root;
}

private boolean status;

   public boolean isEmpty(){
   return root == null;
    }

    public void insert(T value) { 
        if (this.isEmpty() == true) {
            this.root = new AVLNode<T> (value);
            this.status = true;
        } else {
            this.root = insertNode (this.root, value);
         }

        }

    
            private AVLNode<T> insertNode(AVLNode<T> r, T value) {
                if (r == null) {
                    r = new AVLNode<T> (value); 
                     this.status = true;
                    } else if (r.getInfo().compareTo(value) > 0) {
                        r.setLeft(insertNode (r.getLeft(),value));
                        if (this.status == true) {
                            switch (r.getFatBal()) {  
                                 case 1: r.setFatBal(0); 
                                 this.status = false; 
                                 break;
                                 case 0: r.setFatBal(-1);
                                  break;
                                  case -1 : r = this.rotateRight(r); 
                                  break;
                                } // fim switch
                            }  // fim if
                        } else {
                            r.setRight(insertNode (r.getRight(),value));
                            if (this.status == true) {
                                switch (r.getFatBal()) { 
                                      case -1: r.setFatBal(0); 
                                      this.status = false;
                                       break;
                                       case 0: r.setFatBal(1); break;
                                       case 1 : r = this.rotateLeft(r);
                                        break;
                                    } // fim switch
                                } // fim if
                            } // fim else
                            return r;
                        } // fim insertNode

                        // fim if


            private AVLNode<T> rotateRight (AVLNode<T> a){ // os rotates Right e Rotate Left foram modificados para a remoção 
                AVLNode<T> b = a.getLeft();
        // rotação dupla
        if (b.getFatBal() == 1) {
            AVLNode<T> c = b.getRight();
            b.setRight(c.getLeft());
            a.setLeft(c.getRight());
            c.setRight(a);
            c.setLeft(b);
            
            if (c.getFatBal() == -1) {
                a.setFatBal(1);
            } else {
                a.setFatBal(0);
            }
            if (c.getFatBal() == 1) {
                b.setFatBal(-1);
            } else {
                b.setFatBal(0);
            }
            c.setFatBal(0);
            this.status = false;
            return c;
        }
        // rotação simples
        else if (b.getFatBal() == 0) { // para o caso do filho a direita for zero 
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setFatBal(-1);
            b.setFatBal(1);
        }
        // rotação simples
        else { // 
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setFatBal(0);
            b.setFatBal(0);
        }
        this.status = false;
        return b;

            }        

            
         private AVLNode<T> rotateLeft (AVLNode<T> a){ // explicação no rotate Right 
                AVLNode<T> b = a.getRight();
        // rotação dupla
        if (b.getFatBal() == -1) {
            AVLNode<T> c = b.getLeft();
            a.setRight(c.getLeft());
            b.setLeft(c.getRight());
            c.setRight(b);
            c.setLeft(a);
            // fatBal
            if (c.getFatBal() == 1) {
                a.setFatBal(-1);
            } else {
                a.setFatBal(0);
            }
            if (c.getFatBal() == -1) {
                b.setFatBal(1);
            } else {
                b.setFatBal(0);
            }
            c.setFatBal(0);
            this.status = false;
            return c;
        } else if (b.getFatBal() == 0) {
            a.setRight(b.getLeft());
            b.setLeft(a);
            // fatball
            a.setFatBal(1);
            b.setFatBal(-1);
        } else {
            // rotação simples
            a.setRight(b.getLeft());
            b.setLeft(a);
            // fatball
            a.setFatBal(0);
            b.setFatBal(0);
        }
        this.status = false;
        return b;

            }

           

            public void emOrdem () {
                if (this.isEmpty() == true) {
                    System.out.println("Árvore vazia");
                } else {
                    this.percorrerEmOrdem (root);
                }
            }

            private void percorrerEmOrdem (AVLNode<T> r) {
                if (r != null) {
                    percorrerEmOrdem (r.getLeft());
                    System.out.println(r.getInfo() + " " + r.getFatBal());
                    percorrerEmOrdem (r.getRight());
                }
            }

            public void passeioPorNivel () {
                LinkedList<AVLNode<T>> fila ;
                 
                AVLNode<T> aux;
                if (this.isEmpty() == false) {
                    fila = new LinkedList<AVLNode<T>>();
                    fila.addLast(this.root); 

                    while (fila.isEmpty() == false) {
                        aux = fila.pop();

                        if (aux.getLeft() != null) {
                            fila.addLast(aux.getLeft());

                        }if (aux.getRight() != null) {
                            fila.addLast(aux.getRight());

                        }
                          System.out.println(aux.getInfo() + " " + aux.getFatBal() );
                        }
                                }
                            }


            public void remover (T info ){
             if(this.isEmpty() == true ) {
                System.out.println("Vazia");
             } else {
                root = this.removerNo(root,info );
             }
            }      
            
            private AVLNode<T> recurssaoMenorEsquerda (AVLNode<T> pai , AVLNode<T> filho){ // metodo para caso quiser usar recurssão na descida para encontrar o maior na esquerda 
                if (filho.getRight() == null) {
                    return filho;
                }else {
                    pai = filho;
                    filho = filho.getRight();
                    recurssaoMenorEsquerda(pai, filho);
                }
                return null ;
            }

            public AVLNode <T> removerNo( AVLNode<T> r,T value ){ // fazer remoção com dois filhos
                if (r != null) {
                    if (value == r.getInfo()){
                        AVLNode<T> pai, filho;
                        if (r.getLeft() == null && r.getRight() == null)  {// Não tem filhos
                               
                               r = null;
            
                            } else if (r.getLeft() == null)  {  // Não tem filho a esquerda
                                
                                 r = r.getRight();
            
                            }else if (r.getRight() == null)  { // Não tem filho a direita
                                     
                                 r = r.getLeft();
            
                            } else {   // Tem ambos os filhos
                                        
                                 pai = r;
                                 filho = pai.getLeft();
                                
                                  while (filho.getRight() != null){
                                      pai = filho;
                                     filho = filho.getRight();
                                   }
            
                                   pai.setLeft(filho.getLeft());
                                  
                                   r.setInfo(filho.getInfo());
                                  
            
                             } 
                        
                    }else if (value.compareTo(r.getInfo()) == -1 ) {

                    r.setLeft(removerNo (r.getLeft(), value));
                    //volta da remoção
                    
                    if (r.getLeft() == null && r.getRight() == null) { // vai testar todos os casos possiveis que os nos de r podem asssumir , incluindo null 
                        r.setFatBal(0);

                    }else if (r.getRight() == null) {

                        if (r.getLeft().getLeft() != null) {

                             
                             rotateRight(r);
                         

                        }else if (r.getLeft().getLeft() == null) {
                            
                            r.setFatBal(-1);

                        }else if (r.getFatBal() == 1) {

                            rotateRight(r);

                        }else if (r.getFatBal() == -1){

                            rotateRight(r);

                        }
                       
                    }else if (r.getLeft() == null) {
                        
                        if (r.getRight().getRight() != null) {
                            
                     
                          rotateLeft(r);
                          

                        }else if (r.getRight().getRight() == null) {
                            
                           r.setFatBal(1);

                        }else if (r.getRight().getFatBal() == 1) {
                            
                           rotateLeft(r);

                        }else if (r.getRight().getFatBal()== -1) {
                            
                           rotateLeft(r);

                        }

                    }else {

                        if (r.getLeft().getLeft() == null && r.getRight().getRight() == null) {

                            r.setFatBal(0);

                        }else if (r.getLeft().getLeft() == null && r.getRight().getRight() != null) {

                            r.setFatBal(1);
                            
                        }else if (r.getLeft().getLeft() == null && r.getRight().getFatBal() == 1) {

                            rotateLeft(r);
                            
                        }else if (r.getLeft().getLeft() == null && r.getRight().getFatBal() == -1) {

                            rotateLeft(r);
                            
                        }else  if (r.getLeft().getLeft() != null && r.getRight().getRight() == null) {

                            r.setFatBal(-1);
                            
                        }else if (r.getLeft().getLeft() != null && r.getRight().getRight() != null ) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getLeft() != null  && r.getRight().getFatBal() == 1) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getLeft() != null  && r.getRight().getFatBal() == -1) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getLeft() != null && r.getRight().getFatBal() == -1) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getFatBal() == 1 && r.getRight().getRight() == null) {

                            rotateRight(r);
                            
                        }else if (r.getLeft().getFatBal() == 1 && r.getRight().getRight() != null) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getFatBal() == 1 && r.getRight().getFatBal() == 1) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getFatBal() == 1 && r.getRight().getFatBal()  == -1) {

                            rotateRight(r);
                            rotateLeft(r.getRight());
                            
                            
                        }else if (r.getLeft().getFatBal() == -1 && r.getRight().getRight() == null ) {

                            r.setFatBal(-1);
                            
                        }else if (r.getLeft().getFatBal() == -1 && r.getRight().getRight() != null ) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getFatBal() == -1 && r.getRight().getFatBal() == 1)  {

                            rotateRight(r);
                            rotateLeft(r.getRight());
                           
                            
                        }else if(r.getLeft().getFatBal() == -1 && r.getRight().getFatBal() == -1){

                            rotateRight(r);
                            rotateLeft(r.getRight());
                           

                        }

                    }

                    }
                    else {

                    r.setRight(removerNo (r.getRight(), value));
                    // //volta da remoção
                    
                    if (r.getLeft() == null && r.getRight() == null) { // repetição de todos os nos possiveis de r , para calcular os fat bal e rotacionar 
                        r.setFatBal(0);

                    }else if (r.getRight() == null) {

                        if (r.getLeft().getLeft() == null) {

                           r.setFatBal(-1);

                        }else if (r.getLeft().getLeft() != null) {
                            
                            rotateRight(r);
                           

                        }else if (r.getFatBal() == 1) {

                            rotateRight(r);

                        }else if (r.getFatBal() == -1){

                            rotateRight(r);

                        }
                       
                    }else if (r.getLeft() == null) {
                        
                        if (r.getRight().getRight() != null) {
                            
                         
                          rotateLeft(r);
                       

                        }else if (r.getRight().getRight() == null) {
                            
                           r.setFatBal(1);

                        }else if (r.getRight().getFatBal() == 1) {
                            
                           rotateLeft(r);

                        }else if (r.getRight().getFatBal()== -1) {
                            
                           rotateLeft(r);

                        }

                    }else {

                        if (r.getLeft().getLeft() == null && r.getRight().getRight() == null) {

                            r.setFatBal(0);

                        }else if (r.getLeft().getLeft() == null && r.getRight().getRight() != null) {

                            r.setFatBal(1);
                            
                        }else if (r.getLeft().getLeft() == null && r.getRight().getFatBal() == 1) {

                            rotateLeft(r);
                            
                        }else if (r.getLeft().getLeft() == null && r.getRight().getFatBal() == -1) {

                            rotateLeft(r);
                            
                        }else  if (r.getLeft().getLeft() != null && r.getRight().getRight() == null) {

                            r.setFatBal(-1);
                            
                        }else if (r.getLeft().getLeft() != null && r.getRight().getRight() != null ) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getLeft() != null  && r.getRight().getFatBal() == 1) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getLeft() != null  && r.getRight().getFatBal() == -1) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getLeft() != null && r.getRight().getFatBal() == -1) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getFatBal() == 1 && r.getRight().getRight() == null) {

                            rotateRight(r);
                            
                        }else if (r.getLeft().getFatBal() == 1 && r.getRight().getRight() != null) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getFatBal() == 1 && r.getRight().getFatBal() == 1) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getFatBal() == 1 && r.getRight().getFatBal()  == -1) {

                            rotateRight(r);
                            rotateLeft(r.getRight());
                         
                            
                        }else if (r.getLeft().getFatBal() == -1 && r.getRight().getRight() == null ) {

                            r.setFatBal(-1);
                            
                        }else if (r.getLeft().getFatBal() == -1 && r.getRight().getRight() != null ) {

                            r.setFatBal(0);
                            
                        }else if (r.getLeft().getFatBal() == -1 && r.getRight().getFatBal() == 1)  {

                            rotateRight(r);
                            rotateLeft(r.getRight());
                          
                            
                        }else if(r.getLeft().getFatBal() == -1 && r.getRight().getFatBal() == -1){

                            rotateRight(r);
                            rotateLeft(r.getRight());
                       

                        }

                    }
                }
            
           
            } return r;
        }}
    

    
