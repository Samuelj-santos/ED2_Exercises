package B;

public class BTree { // acertar  a inserção
    private NodeB root;
    private final int ordem;
    private final int nChaves;
    private final int minimo;

    public BTree(int m) {
        this.ordem = m;
        this.nChaves = m -1;
        this.minimo = m / 2;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(Integer info) {
        Integer infoRetorno;

        if (this.isEmpty() == true) {

            this.root = new NodeB(this.ordem);
            this.root.preencherRaiz(info, null, null);

        } else {

            Retorno result = new Retorno();
            insertB(this.root, info, result);
            boolean h = result.getH();
            if (h) { 
               // Aumetará a altura da árvore

                NodeB filhoDir = result.getFilhoDir();
                infoRetorno = result.getInfo();
                NodeB novaRaiz = new NodeB(this.ordem);
                novaRaiz.preencherRaiz(infoRetorno, this.root, filhoDir);
                this.root = novaRaiz;

            }

        }
    }

    private void insertB(NodeB raiz, Integer info, Retorno result) { 
    int i, pos;
    Integer  infoMediano;   
     //auxiliar para armazenar a chave que irá subir para o pai
         
    NodeB filhoDir;
               
     //referência para o filho à direita da chave
     if (raiz == null) {
     //O nó anterior (que é um nó folha) é o ideal para inserir a nova chave
            result.setH(true);
            result.setInfo(info);

        } else {

            pos = raiz.buscaBinaria(info);

            if (pos < raiz.getN() && raiz.getInfo(pos) == info) { // testa para saber se a chave ja esta raiz
                System.out.println("Chave já contida na árvore");
                result.setH(false);
                
            } else {    
                //desce na árvore até encontrar o nó folha para inserir a chave.    
                 this.insertB(raiz.getFilho(pos), info, result);

                  if (result.getH()) {  
                   //Se true, deve inserir a info_retorno no nó.
                          if (raiz.getN() < this.nChaves) {  

                              //Tem espaço na página
                              raiz.insereChave(result.getInfo(), result.getFilhoDir());
                              result.setH(false);

                            } else { 
                                   //Overflow. Precisa subdividir a página
                                   NodeB temp = new NodeB(this.ordem);
                                   
                                  //acho que precisa inserir o info antes de realizar a cisão
                                  
                                 //elemento mediano que vai subir para o pai
                                 infoMediano = raiz.getInfo(this.minimo );//informação do no que esta no meio 
                                 if (pos == this.minimo ) {
                                    infoMediano = info;
                                 }
                                 
                                 //insere metade do nó raiz no temp (efetua subdivisão)
                                 
                                  temp.setFilho(0, raiz.getFilho(this.minimo+1 ));
                                  
                                  
                                  for (i = this.minimo +1; i < this.nChaves; i++) {

                                    temp.insereChave(raiz.getInfo(i), raiz.getFilho(i+1 ));

                                  }
                                //atualiza nó raiz. 
                                 if (pos == this.minimo) {
                                    result.setInfo(raiz.getInfo(this.minimo));// tem que ficar dentro de um if 
                                 }
                                 

                                for (i = this.minimo ; i < this.nChaves; i++) {

                                    raiz.setInfo(i, null);
                                    raiz.setFilho(i +1, null);

                                }

                                raiz.setN(this.minimo);

                                //Verifica em qual nó será inserida a nova chave
                                 
                                if (pos < this.minimo) {

                                    raiz.insereChave(result.getInfo(), result.getFilhoDir());

                                } else {

                                    temp.insereChave(result.getInfo(), result.getFilhoDir());

                                }
                                //retorna o mediano para inserí-lo no nó pai e o temp como filho direito do mediano.
                                 result.setInfo(infoMediano);

                                 result.setFilhoDir(temp);

                                }
                            }
                        }
                    }
                }


                public void emOrdem() {
                 if (this.isEmpty() == false) {
                 root.percorrerEmOrdem();
                  } else {
                  System.out.println("Árvore vazia");
                  }
               }


               public int  calcularAltura (){
                NodeB aux;
                aux= this.root;
                int i =0;
                while (aux != null){
                    if (aux.getFilho(0) == null) {
                       return i ;    
                    }else {
                        aux = aux.getFilho(0);
                        i++;
                    }
                }

                return -1;
               }


               public NodeB menorNo (){ 
                
                if (this.isEmpty() == true ) {
                    System.out.println("Arvore vazia ");
                }
                  
                NodeB aux,result;
                aux= this.root;

                while (aux != null){
                    if (aux.getFilho(0) == null) {
                        result = aux;
                        return result;
                        
                    }else {
                        aux = aux.getFilho(0);
                    }
                }

                return null;
            
               }

               public NodeB maiorNo (){
                if (this.isEmpty() == true ) {
                    System.out.println("Arvore vazia ");
                }

                NodeB aux,result;
                aux= this.root;

                while (aux != null){
                    if (aux.getFilho(0) == null) {
                        result = aux;
                        return result;
                        
                    }else {
                        aux = aux.getFilho(UltimaPosicaoDoVetorPreenchidaFilhos(aux));
                    }
                }

                return null;
               }

               private    int UltimaPosicaoDoVetorPreenchida (NodeB aux ){ // tire o  private caso queira usar na main

                for(int i =aux.getLengthInfos()-1 ; i>0 ;i--){
                 if (aux.getInfo(i) != null ) {
                    return i; 
                 } 
                }

                return -1;

               }

               private  int UltimaPosicaoDoVetorPreenchidaFilhos (NodeB aux ){

                for(int i =aux.getLengthInfos() -1; i>0 ;i--){
                 if (aux.getFilho(i) != null ) {
                    return i; 
                 } 
                }

                return -1;

               }


               public Retorno busca (Integer valor ){
                Retorno result  = new Retorno();
                if (this.isEmpty() == true ) {
                    System.out.println("Arvore vazia ");
                }else {
                     
                    NodeB aux = this.root;
                    while (aux != null ){
                         
                        if (aux.getFilho(0) == null ) { // verifica se é folha 
                            if (aux.getInfo(aux.buscaBinaria(valor)) == valor ) {
                                result.setInfo(aux.buscaBinaria(valor));
                                result.setFilhoDir(aux);
                            }else {
                               result.setInfo(aux.buscaBinaria(valor));
                            result.setFilhoDir(null);  
                            }
                           
                            
                        }else {
                            aux = aux.getFilho(aux.buscaBinaria(valor));
                        }

                    }

                }

                return result ; 
               }

               

              
                
               }

