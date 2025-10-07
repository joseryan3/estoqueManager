import java.util.List;


public class Main {
    public static void main(String[] args) {
        // 1. Inicialização
        String nomeArquivoEstoque = "estoque.txt";
        EstoqueManager maneger = new EstoqueManager(nomeArquivoEstoque);

        // 2. Carregar os dados persistidos do arquivo (se existirem)
        System.out.println("---- Tentendo carregar o estoque do arquivo... ----");
        List<Produto> estoque = maneger.carregarProdutos();
        System.out.println("Estoque carregado com " + estoque.size() + "proutos(s)");
        estoque.forEach(System.out::println);

        // 3. Simular operações no sistema
        System.out.println("\n--- Realizando operações no sistema ----");

        //Se o estoque estiver vazio. vamos adicionar alguns produtos iniciais
        if (estoque.isEmpty()) {
            System.out.println("Adicionando produtos iniciais...");
            estoque.add(new Produto(101, "Teclado mecânico", 350.50, 10));
            estoque.add(new Produto(102, "Mouse ganer RGB", 150.75, 25));
        } else {
            // Se já produtos, vamos apenas adicionar um novo e atuliazaar outro
            System.out.println("Adicionando um novo produto e atulizar um existente...");
            estoque.add(new Produto(103, "Monitor 24 polegadas", 899.99, 8));
            System.out.println("\nEstoque após as operações");
            estoque.forEach(System.out::println);


            // 4. Persistir o novo estado da lista de produtos no arquivo
            System.out.println("\n salvando o estado atual do estoque no arquivo.. ---- ");
            maneger.salvarProdutos(estoque);
            System.out.println("Estoque salvo com sucesso em '" + nomeArquivoEstoque + "'!");

        }
    }
}