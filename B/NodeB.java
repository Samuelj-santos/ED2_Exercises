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
    void setInfo(int i, Integer  value) {
        this.infos[i] = value;
    }

    void setFilho(int i, NodeB f) {
        this.filhos[i] = f;
    }
    void preencherRaiz(Integer  value, NodeB r, NodeB filhoDir) {
        this.infos[0] = value;
        this.filhos[0] = r;
        this.filhos[1] = filhoDir;
        this.n = 1;
    }

    int buscaBinaria(Integer  value) {
        int meio, i, f, compara;
        i = 0;
        f = this.n -1;
        while (i <= f) {
            meio = (i + f) / 2;
            compara = value.compareTo(this.infos[meio]);
            if (compara == 0) {
                return meio;
                  // Encontrou. Retorna a posiçãoem que a chave está.
                } else if (compara < 0) {
                    f = meio -1;
                } else {
                    i = meio + 1;
                }
            }return i; 
            //Não encontrou. Retorna a posição do ponteiro para o filho.
        }

        void insereChave(Integer value, NodeB filhoDir) {
            int pos;
            int k = this.n;
            //busca para obter a posição ideal para inserir a nova chave
            pos = buscaBinaria(value);
            //realiza o remanejamento para manter as chaves ordenadas
            while (k > pos && value.compareTo(this.infos[k-1]) < 0) {   
                     this.filhos[k + 1] = this.filhos[k];
                     this.infos[k] = this.infos[k-1];
                     k--;
                    }//insere a chave na posição ideal
                    this.infos[pos] = value;
                    this.filhos[pos + 1] = filhoDir;
                    this.n++;
                }

    

   
            public void percorrerEmOrdem() {
                int i;
                 for (i = 0; i < this.n; i++) {
                    if (this.filhos[i] != null) {
                        this.filhos[i].percorrerEmOrdem();
                    }
                    System.out.println(this.infos[i]);
                }if (this.filhos[i] != null) {
                    this.filhos[i].percorrerEmOrdem();
                }
            }
        }