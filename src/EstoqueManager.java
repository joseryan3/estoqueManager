import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EstoqueManager {

    private final String nomeArquivo;


    public EstoqueManager(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        //Garante que o locale seja conssistente para o separador decimal (ponto)
        Locale.setDefault(Locale.US);
    }


    /**
     * Carrega a lista de produtos o arquivo de etxtp
     *
     * @return Uma lista de produtos. Retorna um lista vazia se o arquivo não existir
     */
    public List<Produto> carregarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            System.out.println("Arquivo de estoque ainda não existe. Será criado um novo. ");
            return produtos; //Retorna lista vazia se fpr a primeira execução
        }
            try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Formato da linha: codigo;nome;preco;quantidade
                    String[] partes = linha.split(";");

                    if (partes.length == 4) {
                        int codigo = Integer.parseInt(partes[0]);
                        String nome = partes[1];
                        double preco = Double.parseDouble(partes[2]);
                        int quantidade = Integer.parseInt(partes[3]);

                        produtos.add(new Produto(codigo, nome, preco, quantidade));
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo de estoque: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converte um numero do arquivo: " + e.getMessage());
            }

            return produtos;
        }
        /**
         * salva (persiste) a lista de produtos no arquivo de etxto
         * O arquivo antigo é sobrescrito com os novos dados
         * @param produtos A lista de produtos a ser salva
         */
        public void salvarProdutos(List< Produto > produtos) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                for (Produto produto : produtos) {
                    //criamos uma linha de tetxo com um separador (;)
                    String linha = String.format("%d;%s;%.2f;%d",
                            produto.getCodigo(),
                            produto.getNome(),
                            produto.getPreco(),
                            produto.getQuantidade());

                    writer.write(linha);
                    writer.newLine(); // Pula para a próxima linha

                }
            } catch (IOException e) {
                System.err.println("erro ao salvar o arquivo de estoque: " + e.getMessage());
            }
        }
    }

