package B;



public class Main {
    public static void main(String[] args) {
        BTree a = new BTree(5);
        // Integer [ ] valores = new Integer[]{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200};
        // Integer [ ] valores = new Integer[]{10,20,50,80,30,40,70,60,90,200,150,75,65,62};
         Integer [ ] valores = new Integer[]{12,27,54,86,33,41,67,72,94,29,23,38,59,107,61,32,131}; 
           Integer [] valores_reversos = new Integer[valores.length];
           int k =0 ;
           for ( int i  = valores.length-1 ; i>= 0 ;i -- ){ // reverte o vetor de valores 
            valores_reversos[k] = valores[i];
            k++;
           }

        for(int i =0 ;i < valores.length;i++){
            a.insert(valores[i]);
        }
       
         Retorno  b = a.busca(132);
         NodeB c = b.getFilhoDir();

         
          a.passeioPorNivel();



       
      

        
    }
}
