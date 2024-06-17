

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <cmath>
#define _CRT_SECURE_NO_WARNINGS

#define TAM 101

using namespace std;

 // int* pointer , criando um ponteiro para uma varivel do tipo int 
// pointer = &var , pointer aponta para var
// *pointer ,   o valor para quem pointer esta apontando
// *pointer = 30 , muda o valor da variavel para quem pointer esta apontando
   
int metodoAlfaNumerico(char *aux) {
    int soma = 0;
    size_t n = strlen(aux);
    for (int i = 0; i < n; i++) {
        soma = soma + (((int)aux[i]) << (i % 8));
    }
    return std::abs(soma) % n;
 }
 typedef struct { // oq vai ser inserido no arquivo
    char codigo[101];
    char descricao[101];
    double preco;
    int qtdEstoque;
}Produto;

 typedef struct { // oq vai ser inserido na tabela de hashing 
    char codigo[101];
    int posicao;
    int status;
    int prox;
}Registro;

FILE* prepararArquivo(char nomeArq[]) {
    FILE* arq;
    arq = fopen(nomeArq, "r+b");
    if (arq == NULL) {
        arq = fopen(nomeArq, "w+b");
    }
    return arq;
}

void gravarDado(FILE* arq, Produto aux) {
    size_t retorno;

    fseek(arq, 0, SEEK_END);
    retorno = fwrite(&aux, sizeof(Produto), 1, arq);
    if (retorno == 1) {
        printf("Registro gravado com sucesso! \n");
    }
    else {
        printf("Erro de gravação! \n");
    }
}

void lerArquivo(FILE* arq) { // ler o texto do arquivo e exibir linha a linha
    Produto temp;
    size_t retorno;
    int cont = 0;
    fseek(arq, 0, SEEK_SET);
    
    while (1) {
        retorno = fread(&temp, sizeof(Produto), 1, arq);
        if (retorno == 0) {
            if (feof(arq) != 0) {
                if (cont == 0) {
                    printf("Arquivo vazio \n");
                }
            }
            else {
                printf("Erro de leitura \n");
            }
            break;
        }
        else {
            cont++;
            printf("%s",temp.codigo);
        }
    }
}

int busca(FILE* arq, char *codigo) {
    int posicaoTabela = metodoAlfaNumerico(codigo);
    Registro receptor;
    fseek(arq, 0, SEEK_SET);
    fseek(arq, posicaoTabela * sizeof(Registro), SEEK_SET);
    fread(&receptor, sizeof(Registro), 1, arq);

    
    
    while (receptor.status = 1) {

            if (strcmp(receptor.codigo, codigo) == 0) {
                return receptor.posicao;
            }
            if (receptor.prox == -1) {
                break;

            }
            fseek(arq, receptor.prox * sizeof(Registro), SEEK_SET);
            fread(&receptor, sizeof(Registro), 1, arq);
    }

    return -1;
}
int posicaoLivreZonaDeSinonimos(FILE* arq) { //caso um registro n tenha prox

    fseek(arq, 0, SEEK_SET);
    fseek(arq, 101 * sizeof(Registro), SEEK_SET);//move o ponteiro para a zona de sinonimos 
    Registro receptor;
    int contador = 101;
    while (true) {
        fread(&receptor, sizeof(Registro), 1, arq);

        if (receptor.status == 1){//posicao ocupada
            contador++;

        }
        else { //posicao livre

            return contador;

        }


    }
    


 }

void cadastrar(FILE* Tabela , FILE* Arquivo , int posicaoArquivo) {
    Produto novo;
    printf("Insira o Codigo do novo produto\n");
    scanf("%100s", novo.codigo);

    // Verifica se o código já existe
    int x = busca(Tabela, novo.codigo);
    if (x != -1) {
        printf("Esse codigo ja existe\n");
        return ;
    }

 
    while (getchar() != '\n');

    
    printf("Insira a Descricao do novo produto: ");
    fgets(novo.descricao, sizeof(novo.descricao), stdin);
   
    novo.descricao[strcspn(novo.descricao, "\n")] = 0;

   
    printf("Insira o Preco: ");
    if (scanf("%lf", &novo.preco) != 1) {
        fprintf(stderr, "Erro ao ler o preço.\n");
        exit(1);
    }

   
    while (getchar() != '\n');

    
    printf("Insira a Quantidade em Estoque: ");
    if (scanf("%d", &novo.qtdEstoque) != 1) {
        fprintf(stderr, "Erro ao ler a quantidade em estoque.\n");
        exit(1);
    }

    fseek(Arquivo, 0, SEEK_END);
    int pos = ftell(Arquivo) / sizeof(Produto);
    fwrite(&novo, sizeof(Produto), 1, Arquivo);

    int posicaotabela = metodoAlfaNumerico(novo.codigo);

    fseek(Tabela, posicaotabela * sizeof(Registro), SEEK_SET);
    Registro receptor;
    fread(&receptor, sizeof(Produto), 1, Tabela);

    if (receptor.status = 0) {

        receptor.posicao = pos;
        receptor.status = 1;
        receptor.prox = -1;
        strcpy(receptor.codigo, novo.codigo);
        fseek(Tabela,posicaotabela*sizeof(Registro),SEEK_SET);
        fwrite(&receptor, sizeof(Registro),1, SEEK_SET);

    }
    else {
        Registro receptorFor;

        while (receptor.prox != -1) {
            fseek(Tabela, receptor.prox * sizeof(Registro), SEEK_SET);
            fread(&receptor, sizeof(Registro), 1, Tabela);

        }

        for (int i = 0; i < 101;i++) {
            fseek(Tabela, i * sizeof(Registro), SEEK_SET);
            fread(&receptorFor, sizeof(Registro), 1, Tabela);

            if (receptorFor.status == 0) {

                strcpy(receptorFor.codigo, novo.codigo);
                receptorFor.posicao = pos;
                receptorFor.status = 1;
                receptorFor.prox = -1;

                fseek(Tabela, i * sizeof(Registro), SEEK_SET);
                fwrite(&receptorFor, sizeof(Registro),1, Tabela);
                receptorFor.prox = i;
                fseek(Tabela, pos * sizeof(Registro), SEEK_SET);
                fwrite(&receptor, sizeof(Registro), 1, Tabela);


                break;

            }
        }

    }

   
    


}

void Exibir(FILE* Tabela , FILE* Arquivo , char Codigo[]) {
    
    int x = busca(Tabela, Codigo);
    if (x == -1){
        printf("Codigo nao existe");

    }
    else {

        fseek(Arquivo, 0, SEEK_SET);
        fseek(Arquivo, x * sizeof(Produto), SEEK_SET);
        Produto a;
        fread(&a, sizeof(Produto), 1, Arquivo);

        printf("Codigo :  %s \n", a.codigo);
        printf("Descricao  : %s \n ", a.descricao);
        printf("Preço : %lf", a.preco);
        printf("Quantidade em Estqoue : %i", a.qtdEstoque);


    }



}

void alterar(FILE* Tabela, FILE* Arquivo,char Codigo[]) {

    int x = busca(Tabela,Codigo);
    if (x == -1) {
        printf("Codigo nao encontrado\n");
        return;
    }
    int novoQTD;
    printf("Coloque o novo valor de Quantidade em Estoque");
    scanf("%i", &novoQTD);
    double novoPreco;
    printf("Coloque o novo valor de Preco");
    scanf("%lf", &novoPreco);



   
    fseek(Arquivo, x * sizeof(Produto), SEEK_SET);
    Produto a;
    fread(&a, sizeof(Produto), 1, Arquivo);
    a.preco = novoPreco;
    a.qtdEstoque = novoQTD;
    fseek(Arquivo, x * sizeof(Produto), SEEK_SET);
    fwrite(&a,sizeof(Produto),1,Arquivo);



}

void Listar(FILE* Arquivo) {
    Produto receptor;
    fseek(Arquivo, 0, SEEK_SET);

    while (fread(&receptor, sizeof(Produto), 1, Arquivo)) {
        printf("Codigo :  %s \n", receptor.codigo);
        printf("Descricao  : %s \n ", receptor.descricao);
        printf("Preco : %lf", receptor.preco);
        printf("Quantidade em Estoque : %i", receptor.qtdEstoque);

    }

}
 
  
 
void exibir() {
    printf("\n");
    printf("1 - Cadastrar \n");
    printf("2 - Alterar\n");
    printf("3 - Exibir\n");
    printf("4 - Exibir Todos\n");
    printf("5 - Busca\n");
    printf("0 - Encerrar\n");
   
 }


int main(){
    FILE* arq;
    arq = fopen("Tabela.dat", "r+b");
    if (!arq) {
        arq = fopen("Tabela.dat", "w+b");
        if (!arq) {
            printf("Erro ao abrir arquivo de hashing!\n");
            fclose(arq);
            return 1;
        }
        Registro aux;
        aux.status = 0;
        aux.prox = -1;
        for (int i = 0; i < 101; i++) {
            fwrite(&aux, sizeof(Registro), 1, arq);
        }
    }
    FILE* arq2;
    
    arq2 = fopen("Arquivo.dat", "r+b");
    if (!arq2) {
        arq2 = fopen("Arquivo.dat", "w+b");
        if (!arq2) {
            printf("Erro ao abrir arquivo de produtos!\n");
            return 1;
        }
    }
    int opcao = 1000000;
    int i = 1;
    while (opcao != 0) {
        exibir();
        scanf("%d", &opcao);
        int c;
        while ((c = getchar()) != '\n' && c != EOF); // limpa o buffer

            switch (opcao) {

              case 1 :
                  cadastrar(arq, arq2, i);
                break;

              case 2: {
                  char codigo[101];
                  printf("Insira o Codigo do  produto: ");
                  fgets(codigo, sizeof(codigo), stdin);

                  codigo[strcspn(codigo, "\n")] = 0;
                  alterar(arq, arq2, codigo);
                  break;
              }

              case 3: {
                  char codigo[101];
                  printf("Insira o Codigo do  produto: ");
                  fgets(codigo, sizeof(codigo), stdin);

                  codigo[strcspn(codigo, "\n")] = 0;
                  Exibir(arq, arq2, codigo);
                  break;
               }

              case 4:
                  Listar(arq2);

                  break;
              case 5: {
                  char codigo[101];
                  printf("Insira a Descricao do novo produto: ");
                  fgets(codigo, sizeof(codigo), stdin);

                  codigo[strcspn(codigo, "\n")] = 0;

                  busca(arq, codigo);

                  break;
                }
              case 0:

                  break;
            }

            i++;
    }
    


    fclose(arq);
    fclose(arq2);
 return 0;  
}


