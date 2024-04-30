package B;


public class Retorno  {

    private NodeB filhoDir;
    private boolean h;
    private Integer info;

    void setFilhoDir (NodeB f) {
        this.filhoDir = f;
    }
    NodeB getFilhoDir () {
        return this.filhoDir;
    }
    void setH (boolean h) {
        this.h = h;
    }
    boolean getH () {
        return this.h;
    }
    void setInfo (Integer info) {
        this.info = info;
    }
    Integer  getInfo () {
        return this.info;
    }
}