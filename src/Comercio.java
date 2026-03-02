import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        int i = 0; // Inicializa i
        String linha;
        Produto produto;
        Produto[] produtosLidos = new Produto[MAX_NOVOS_PRODUTOS]; // Usar um array local para leitura

        try {
            arquivo = new Scanner(new File(nomeArquivoDados), "UTF-8");
            int numProdutos = Integer.parseInt(arquivo.nextLine());
            for (i = 0; i < numProdutos; i++) {
                linha = arquivo.nextLine();
                produto = Produto.criarDoTexto(linha);
                produtosLidos[i] = produto;
            }
            quantosProdutos = i; // Atualiza a quantidade de produtos lidos
        } catch (IOException excessoArquivo) {
            System.err.println("Erro ao ler o arquivo de dados: " + excessoArquivo.getMessage());
            produtosLidos = new Produto[MAX_NOVOS_PRODUTOS]; // Retorna um array vazio em caso de erro
            quantosProdutos = 0;
        } finally {
            if (arquivo != null) { // Verifica se o scanner foi inicializado antes de fechar
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
        return produtosLidos; // Retorna o array local
    }

    /** Lista todos os produtos cadastrados, numerados, um por linha */
    static void listarTodosOsProdutos() {

        cabecalho();
        System.out.println("LISTA DE PRODUTOS:");
        System.out.println("==================");
        for (int i = 0; i < quantosProdutos; i++) {
            System.out.println((i + 1) + "." + produtosCadastrados[i].toString());
        }
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
        cabecalho();
        System.out.println("LOCALIZAR PRODUTO:");
        System.out.println("==================");
        System.out.print("Digite o nome do produto: ");
        String nomeBuscado = teclado.nextLine();

        ProdutoNaoPerecivel produtoBuscado = new ProdutoNaoPerecivel(nomeBuscado, 1.0, 0.1);
        boolean encontrado = false;
        for (int i = 0; i < quantosProdutos; i++) {
            if (produtosCadastrados[i].equals(produtoBuscado)) {
                System.out.println((i + 1) + "." + produtosCadastrados[i].toString());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto " + nomeBuscado + " não encontrado.");
        }
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
        cabecalho();
        if (quantosProdutos >= produtosCadastrados.length) {
            System.out.println("Não há espaço para mais produtos!");
            return;
        }

        System.out.println("CADASTRAR NOVO PRODUTO:");
        System.out.println("1 - Produto Não Perecível");
        System.out.println("2 - Produto Perecível");
        System.out.print("Escolha o tipo: ");
        int tipo = Integer.parseInt(teclado.nextLine());

        System.out.print("Descrição: ");
        String descricao = teclado.nextLine();

        System.out.print("Preço de custo: ");
        double precoCusto = Double.parseDouble(teclado.nextLine());

        System.out.print("Margem de lucro (ex: 0.30 para 30%): ");
        double margemLucro = Double.parseDouble(teclado.nextLine());

        Produto novoProduto = null;

        if (tipo == 1) {
            novoProduto = new ProdutoNaoPerecivel(descricao, precoCusto, margemLucro);
        } else if (tipo == 2) {
            System.out.print("Data de validade (dd/MM/yyyy): ");
            String dataTexto = teclado.nextLine();
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate validade = LocalDate.parse(dataTexto, formatador);
            novoProduto = new ProdutoPerecivel(descricao, precoCusto, margemLucro, validade);
        } else {
            System.out.println("Tipo inválido!");
            return;
        }

        produtosCadastrados[quantosProdutos] = novoProduto;
        quantosProdutos++;
        System.out.println("Produto cadastrado com sucesso!");
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

        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter(nomeArquivo, "UTF-8");
            escritor.println(quantosProdutos);
            for (int i = 0; i < quantosProdutos; i++) {
                escritor.println(produtosCadastrados[i].gerarDadosTexto());
            }
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }
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