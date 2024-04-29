package B;


public class Retorno <T extends Comparable <T>> {

    private NodeB<T> filhoDir;
    private boolean h;
    private T info;

    void setFilhoDir (NodeB<T> f) {
        this.filhoDir = f;
    }
    NodeB<T> getFilhoDir () {
        return this.filhoDir;
    }
    void setH (boolean h) {
        this.h = h;
    }boolean getH () {
        return this.h;
    }
    void setInfo (T info) {
        this.info = info;
    }
    T getInfo () {
        return this.info;
    }
}