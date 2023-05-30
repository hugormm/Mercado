import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Variaveis Globais
    public static Scanner input = new Scanner(System.in);
    public static DecimalFormat df = new DecimalFormat("#.00");
    public static ArrayList<String> produtos = new ArrayList<String>();
    public static ArrayList<Double> valores = new ArrayList<Double>();
    public static ArrayList<String> produtos_da_vendas = new ArrayList<String>();
    public static ArrayList<Double> valores_vendas = new ArrayList<Double>();
    public static ArrayList<Integer> qtds_vendas = new ArrayList<Integer>();
    public static String produto_digitado;
    public static double valor_digitado, saldo = 0;

    public static void main(String[] args) {

        //Variaveis Locais
        int opcao;

        //Executar
        do {
            System.out.println();
            exibirMenu();
            System.out.print("\nOpcao: ");
            opcao = input.nextInt();
            input.nextLine();
            limpa();
            switch (opcao) {
                case 1:
                    registarProduto();
                    break;
                case 2:
                    if (produtos.size() == 0){
                        System.out.println("Nao ha produtos registados");
                    }
                    else {
                        editarProduto();
                    }
                    break;
                case 3:
                    if (produtos.size() == 0){
                        System.out.println("Nao ha produtos registados");
                    }
                    else {
                        buscarProduto();
                    }
                    break;
                case 4:
                    if (produtos.size() == 0){
                        System.out.println("Nao ha produtos registados");
                    }
                    else {
                        listarProdutos();
                    }
                    break;
                case 5:
                    registarVenda();
                    break;
                case 6:
                    if (produtos_da_vendas.size() == 0){
                        System.out.println("Nao ha vendas registadas");
                    }
                    else {
                        listarVendas();
                    }
                    break;
                case 0:
                    System.out.println("A Sair...");
                    break;
                default:
                    System.out.println("Opcao Invalida!");
                    break;
            }
            System.out.println();
            enter();
        } while (opcao != 0);


    }

    //Funcoes
    public static void exibirMenu() {
        System.out.println("===== MERCADO =====");
        System.out.println();
        System.out.println("1 - Registar produto.");
        System.out.println("2 - Editar produto.");
        System.out.println("3 - Buscar produto por baliza de precos.");
        System.out.println("4 - Listar todos os produtos.");
        System.out.println();
        System.out.println("5 - Registar venda.");
        System.out.println("6 - Listar todas as vendas.");
        System.out.println();
        System.out.println("0 - Sair.");
    }

    public static void registarProduto() {
        System.out.print("Digite o nome do produto a ser registado: ");
        produto_digitado = input.nextLine();
        produtos.add(produto_digitado);
        System.out.print("Digite o preco deste produto: ");
        valor_digitado = input.nextDouble();
        input.nextLine();
        if (valor_digitado <= 0) {
            System.out.println("Valor Invalido!");
        } else {
            valores.add(valor_digitado);
            System.out.println("\nSUCESSO!");
        }
    }

    public static void editarProduto() {
        int codigo;
        String novo_produto;
        double novo_preco;

        for (int i = 0; i < produtos.size(); i++) {
            System.out.println("(" + i + ") - {" + produtos.get(i) + "} [" + df.format(valores.get(i)) + " €]");
        }
        System.out.println();
        System.out.print("Digite o codigo do produto a ser editado (0 ate " + (produtos.size()-1) + "): ");
        codigo = input.nextInt();
        input.nextLine();
        if(codigo < 0 || codigo > produtos.size()){
            System.out.println("Codigo Invalido!");
        }
        else {
            System.out.print("Digite o nome do produto a ser registado: ");
            novo_produto = input.nextLine();
            produtos.set(codigo, novo_produto);
            System.out.print("Digite o preco deste produto: ");
            novo_preco = input.nextDouble();
            input.nextLine();
            if (novo_preco <= 0) {
                System.out.println("Valor Invalido!");
            } else {
                valores.set(codigo, novo_preco);
                System.out.println("\nSUCESSO!");
            }
        }
    }

    public static void buscarProduto(){
        double valor_max, valor_min;

        System.out.print("Digite o valor minimo de preco para a busca: ");
        valor_min = input.nextDouble();
        input.nextLine();
        System.out.print("Digite o valor maximo de preco para a busca:");
        valor_max = input.nextDouble();
        input.nextLine();
        System.out.println();

        for (int i = 0; i < produtos.size(); i++) {
            if(valores.get(i) >= valor_min && valores.get(i) <= valor_max){
                System.out.println("(" + i + ") - {" + produtos.get(i) + "} [" + df.format(valores.get(i)) + " €]");
            }
        }
    }

    public static void listarProdutos(){
        System.out.println();
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println("(" + i + ") - {" + produtos.get(i) + "} [" + df.format(valores.get(i)) + " €]");
        }
    }

    public static void registarVenda(){
        int codigo_venda, qtd;
        double valor_venda;

        System.out.println();
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println("(" + i + ") - {" + produtos.get(i) + "} [" + df.format(valores.get(i)) + " €]");
        }
        System.out.println();
        System.out.print("Digite o codigo do produto a ser vendido (0 ate " + (produtos.size()-1) + "): ");
        codigo_venda = input.nextInt();
        input.nextLine();
        if(codigo_venda < 0 || codigo_venda > produtos.size()){
            System.out.println("Codigo Invalido!");
        }
        else{
            System.out.println("(" +codigo_venda+ ") - {"+ produtos.get(codigo_venda)+ "} [" +df.format(valores.get(codigo_venda))+ " €]");
            produtos_da_vendas.add(produtos.get(codigo_venda));
            System.out.print("\nDigite a quantidade deste produto a ser vendido: ");
            qtd = input.nextInt();
            input.nextLine();
            qtds_vendas.add(qtd);
            valor_venda = valores.get(codigo_venda) * qtd;
            valores_vendas.add(valor_venda);
            saldo += valor_venda;
            System.out.println("(" +produtos.get(codigo_venda)+ ") x " +qtd+ " = [" +df.format(valor_venda)+ " €]");
            System.out.println("\nSucesso!");
        }
    }

    public static void listarVendas(){
        for (int i=0; i<produtos_da_vendas.size(); i++){
            System.out.println("(" +produtos_da_vendas.get(i)+ ") x " +qtds_vendas.get(i)+ " = [" +df.format(valores_vendas.get(i))+ " €]");
        }
        System.out.println("\nSaldo total das vendas: (" +df.format(saldo)+ " €)");
    }

    public static void aguarde(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException erro) {
            erro.printStackTrace();
        }
    }

    public static void limpa() {
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
    }

    public static void enter(){
        String enter;

        System.out.println("Pressione ENTER para continuar...");
        enter = input.nextLine();
    }


}