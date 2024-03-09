package GenericQueue;
 class LDENode <T extends Comparable<T>> {
    private LDENode<T> ant;
    private T info;
    private LDENode<T> prox;

    public LDENode (T valor) {
        this.info = valor;
    }
    public void setInfo (T valor) {
        this.info = valor;
    }
    public void setProx (LDENode<T> novoProx) {
        this.prox = novoProx;
    }
    public void setAnt (LDENode<T> novoAnt) {
        this.ant = novoAnt;
    }
    public T getInfo() {
        return this.info;
    }
    public LDENode<T> getProx () {
        return this.prox;
    }
    public LDENode<T> getAnt () {
        return this.ant;
    }
}
