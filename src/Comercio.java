import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Comercio {
    /** Para inclusão de novos produtos no vetor */
    static final int MAX_NOVOS_PRODUTOS = 10;
    /**
     * Nome do arquivo de dados. O arquivo deve estar localizado na raiz do projeto
     */
    static String nomeArquivoDados;
    /** Scanner para leitura do teclado */
    static Scanner teclado;
    /**
     * Vetor de produtos cadastrados. Sempre terá espaço para 10 novos produtos a
     * cada execução
     */
    static Produto[] produtosCadastrados;
    /** Quantidade produtos cadastrados atualmente no vetor */
    static int quantosProdutos;

    /** Gera um efeito de pausa na CLI. Espera por um enter para continuar */
    static void pausa() {
        System.out.println("Digite enter para continuar...");
        teclado.nextLine();
    }

    /** Cabeçalho principal da CLI do sistema */
    static void cabecalho() {
        System.out.println("AEDII COMÉRCIO DE COISINHAS");
        System.out.println("===========================");
    }

    /**
     * Imprime o menu principal, lê a opção do usuário e a retorna (int).
     * Perceba que poderia haver uma melhor modularização com a criação de uma
     * classe Menu.
     * 
     * @return Um inteiro com a opção do usuário.
     */
    static int menu() {
        cabecalho();
        System.out.println("1 - Listar todos os produtos");
        System.out.println("2 - Procurar e listar um produto");
        System.out.println("3 - Cadastrar novo produto");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * Lê os dados de um arquivo texto e retorna um vetor de produtos. Arquivo no
     * formato
     * N (quantiade de produtos) <br/>
     * tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade] <br/>
     * Deve haver uma linha para cada um dos produtos. Retorna um vetor vazio em
     * caso de problemas com o arquivo.
     * 
     * @param nomeArquivoDados Nome do arquivo de dados a ser aberto.
     * @return Um vetor com os produtos carregados, ou vazio em caso de problemas de
     *         leitura.
     */
    static Produto[] lerProdutos(String nomeArquivoDados) {
        Scanner arquivo = null;
        int i, numProdutos;
        String linha;
        Produto produto;
        Produto[] produtosCadastrados = new Produto[MAX_NOVOS_PRODUTOS];
        

        try{
        arquivo = new Scanner(new File(nomeArquivoDados), "UTF-8");
        numProdutos = Integer.parseInt(arquivo.nextLine());
        for(i = 0; i < numProdutos; i++){
            linha = arquivo.nextLine();
            produto = Produto.criarDoTexto(linha);
            produtosCadastrados[i] = produto;
        }
        quantosProdutos=i;
        catch (IOException excessoArquivo){
            produtosCadastrados = null;
            quantosProdutos = 0;
        }
        finally{
            
                arquivo.close();
            
        }
        
            
        }
        /*
         * Ler a primeira linha do arquivoDados contendo a quantidade de produtos
         * armazenados no arquivo.
         * Instanciar o vetorProdutos com o tamanho necessário para acomodar todos os
         * produtos do arquivo + o
         * espaço reserva MAX_NOVOS_PRODUTOS. Após isso, ler uma após a outra o restante
         * das linhas do arquivo,
         * convertendo, a cada leitura de linha, seus dados em objetos do tipo Produto
         * (utilizar o método
         * criarDoTexto()). Cada objeto Produto instanciado será armazenado no
         * vetorProdutos.
         */
        return vetorProdutos;
    }

    /** Lista todos os produtos cadastrados, numerados, um por linha */
    static void listarTodosOsProdutos() {
        /*
         * Percorrer o vetor de produtosCadastrados, escrevendo na tela os dados de cada
         * um (utilizar o método
         * toString() já implementado).
         */
    }

    /**
     * Localiza um produto no vetor de cadastrados, a partir do nome (descrição), e
     * imprime seus dados.
     * A busca não é sensível ao caso. Em caso de não encontrar o produto, imprime
     * mensagem padrão
     */
    static void localizarProdutos() {
        /*
         * Ler do teclado a descrição (nome) do produto que o usuário deseja localizar,
         * procurar no vetor de
         * produtosCadastrados o produto em questão (utilizar o método equals() já
         * implementado na classe Produto)
         * e imprimir na tela seus dados.
         */
    }

    /**
     * Rotina de cadastro de um novo produto: pergunta ao usuário o tipo do produto,
     * lê os dados correspondentes,
     * cria o objeto adequado de acordo com o tipo, inclui no vetor. Este método
     * pode ser feito com um nível muito
     * melhor de modularização. As diversas fases da lógica poderiam ser
     * encapsuladas em outros métodos.
     * Uma sugestão de melhoria mais significativa poderia ser o uso de padrão
     * Factory Method para criação dos
     * objetos.
     */
    static void cadastrarProduto() {
        /*
         * Implementar a sub-rotina de exibir o novo menu para cadastro de novo produto,
         * ler os dados necessários
         * conforme o tipo desejado, criar o objeto correspondente, salvando-o no vetor
         * de produtosCadastrados e
         * incrementando a variável de controle da quantidade de produtos.
         */
    }

    /**
     * Salva os dados dos produtos cadastrados no arquivo csv informado. Sobrescreve
     * todo o conteúdo do arquivo.
     * 
     * @param nomeArquivo Nome do arquivo a ser gravado.
     */
    public static void salvarProdutos(String nomeArquivo) {
        /*
         * Você deve implementar aqui a lógica que abrirá um arquivo para escrita com o
         * nome informado no
         * parâmetro, percorrerá um por um todos os produtos existentes no vetor de
         * produtosCadastrados, gerando
         * uma linha de texto com os dados de cada objeto Produto, escrevendo-a no
         * arquivo.
         */
    }

    public static void main(String[] args) throws Exception {
        teclado = new Scanner(System.in, Charset.forName("ISO-8859-2"));
        nomeArquivoDados = "dadosProdutos.csv";
        produtosCadastrados = lerProdutos(nomeArquivoDados);
        int opcao = -1;
        do {
            opcao = menu();
            switch (opcao) {
                case 1 -> listarTodosOsProdutos();
                case 2 -> localizarProdutos();
                case 3 -> cadastrarProduto();
            }
            pausa();
        } while (opcao != 0);
        salvarProdutos(nomeArquivoDados);
        teclado.close();
    }
}