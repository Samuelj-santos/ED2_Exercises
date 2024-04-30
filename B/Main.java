package B;

public class Main {
    public static void main(String[] args) {
        BTree a = new BTree(5);//
        Integer [ ] valores = new Integer[]{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200};

        for(int i =0 ;i < valores.length;i++){
            a.insert(valores[i]);
        }
        a.emOrdem();
    }
}
