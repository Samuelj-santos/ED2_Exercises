package B;

public class Main {
    public static void main(String[] args) {
        BTree a = new BTree(5);//
        Integer [ ] valores = new Integer[]{10,20,50,80,30,40,70,60,90,200,150,75,65,62};

        for(int i =0 ;i < valores.length;i++){
            a.insert(valores[i]);
        }
        a.emOrdem();
    }
}
