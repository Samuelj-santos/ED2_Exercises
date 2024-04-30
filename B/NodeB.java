package B;
 
class NodeB {
    private int n;
    private Integer [] infos;
    private NodeB[] filhos;

    NodeB (int m ){
        infos = new Integer [m];
        filhos = new NodeB[m];
    }
    void setN(int n) {
        this.n= n;
    }
    int getN() {
        return this.n;
    
    }

    Integer getInfo(int i ){
      return infos[i];
    }
    NodeB getFilho(int i ){
        return filhos[i];
    }

    void setFilho(int i, NodeB f) {
        this.filhos[i] = f;
    }

    

   
            public void percorrerEmOrdem() {
                int i;for (i = 0; i < this.n; i++) {
                    if (this.filhos[i] != null) {
                        this.filhos[i].percorrerEmOrdem();
                    }
                    System.out.println(this.infos[i]);
                }if (this.filhos[i] != null) {
                    this.filhos[i].percorrerEmOrdem();
                }
            }
        }