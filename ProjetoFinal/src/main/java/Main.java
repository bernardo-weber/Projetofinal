import dao.UsuarioDAO;
import model.Usuario;
import dao.ProdutoDAO;
import model.Produto;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n==============================");
            System.out.println("          SISTEMA");
            System.out.println("==============================");
            System.out.println("1 - Cadastrar Novo Usuário");
            System.out.println("2 - Efetuar Login - Usuário");
            System.out.println("3 - Efetuar Login - Administrativo");
            System.out.println("0 - Sair");
            System.out.println("==============================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    cadastrarUsuario(scanner);
                    break;

                case 2:
                    loginUsuario(scanner);
                    break;

                case 3:
                    loginAdministrativo(scanner);
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }


    public static void cadastrarUsuario(Scanner scanner) {

        System.out.println("\n===== CADASTRO DE USUÁRIO =====");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Confirme a senha: ");
        String confirmaSenha = scanner.nextLine();

        if (!senha.equals(confirmaSenha)) {
            System.out.println("As senhas não conferem.");
            return;
        }

        Usuario usuario = new Usuario(
                nome,
                sobrenome,
                login,
                senha
        );

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        boolean cadastrado = usuarioDAO.cadastrar(usuario);

        if (cadastrado) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar o usuário.");
        }
    }


    public static void loginUsuario(Scanner scanner) {

        System.out.println("\n===== LOGIN DO USUÁRIO =====");

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.login(login, senha);

        if (usuario == null) {

            System.out.println("Login ou senha incorretos.");
            return;
        }

        if (!"USUARIO".equals(usuario.getTipo())) {

            System.out.println("Este usuário não possui acesso à área de cliente.");
            return;
        }

        System.out.println("\nLogin realizado com sucesso!");
        System.out.println("Bem-vindo, " + usuario.getNome() + "!");

        telaCliente(scanner);
    }



    public static void loginAdministrativo(Scanner scanner) {

        System.out.println("\n===== LOGIN ADMINISTRATIVO =====");

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.login(login, senha);

        if (usuario == null) {

            System.out.println("Login ou senha incorretos.");
            return;
        }

        if (!"ADMIN".equals(usuario.getTipo())) {

            System.out.println("Este usuário não possui acesso administrativo.");
            return;
        }

        System.out.println("\nLogin administrativo realizado com sucesso!");
        System.out.println("Bem-vindo, " + usuario.getNome() + "!");

        telaAdministrativa(scanner);
    }



    public static void telaCliente(Scanner scanner) {

        int opcao;

        do {

            System.out.println("\n===== TELA DO CLIENTE =====");
            System.out.println("1 - Buscar produto");
            System.out.println("2 - Adicionar ao carrinho");
            System.out.println("3 - Retirar do carrinho");
            System.out.println("4 - Confirmar compra/reserva");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    buscarProduto(scanner);
                    break;

                case 2:
                    System.out.println("Adicionar ao carrinho será implementado.");
                    break;

                case 3:
                    System.out.println("Retirar do carrinho será implementado.");
                    break;

                case 4:
                    System.out.println("Confirmar compra/reserva será implementado.");
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // ==========================================
    // TELA ADMINISTRATIVA
    // ==========================================

    public static void telaAdministrativa(Scanner scanner) {

        int opcao;

        do {

            System.out.println("\n===== TELA ADMINISTRATIVA =====");
            System.out.println("1 - Cadastrar novo produto");
            System.out.println("2 - Buscar produto");
            System.out.println("3 - Remover produto");
            System.out.println("4 - Atualizar produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("Cadastro de produto será implementado.");
                    break;

                case 2:
                    System.out.println("Busca de produto será implementada.");
                    break;

                case 3:
                    System.out.println("Remoção de produto será implementada.");
                    break;

                case 4:
                    System.out.println("Atualização de produto será implementada.");
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

    }

    public static void buscarProduto(Scanner scanner) {

        ProdutoDAO produtoDAO = new ProdutoDAO();

        System.out.println("\n===== BUSCAR PRODUTO =====");
        System.out.println("1 - Buscar por ID");
        System.out.println("2 - Buscar por nome");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        Produto produto = null;

        switch (opcao) {

            case 1:

                System.out.print("Digite o ID do produto: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                produto = produtoDAO.buscarPorId(id);

                break;

            case 2:

                System.out.print("Digite o nome do produto: ");
                String nome = scanner.nextLine();

                produto = produtoDAO.buscarPorNome(nome);

                break;

            case 0:

                return;

            default:

                System.out.println("Opção inválida.");
                return;
        }

        if (produto == null) {

            System.out.println("Produto Não Encontrado");

        } else {

            System.out.println("\n===== PRODUTO ENCONTRADO =====");
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$ " + produto.getPreco());
        }
    }

}