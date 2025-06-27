import com.br.senai.crud.Cliente;
import com.br.senai.crud.dao.ClienteDAO;
import com.br.senai.crud.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClienteDAO clienteDAO = new ClienteDAO();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE CADASTRO DE CLIENTES ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Excluir Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        listarClientes();
                        break;
                    case 3:
                        atualizarCliente();
                        break;
                    case 4:
                        excluirCliente();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
                opcao = -1;
            }
        } while (opcao != 0);

        HibernateUtil.shutdown();
        scanner.close();
    }

    private static void cadastrarCliente() {
        System.out.println("\n=== CADASTRAR CLIENTE ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();


        Cliente cliente = new Cliente(nome, email, telefone);
        clienteDAO.salvar(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("=== LISTA DE CLIENTES ===");
        List<Cliente> clientes = clienteDAO.listarTodos();

        if (clientes == null || clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println("-----------------------------------------------");
            System.out.println("ID: " + cliente.getId() +
                    "| Nome: " + cliente.getNome() +
                    "| Email: " + cliente.getEmail() +
                    "| Telefone: " + cliente.getTelefone());
        }
    }

    private static void atualizarCliente() {
        System.out.println("=== ATUALIZAR CLIENTE ===");
        listarClientes();

        System.out.println("Digite o ID do cliente a ser atualizado: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Cliente cliente = clienteDAO.buscarPorId(id);

            if (cliente == null) {
                System.out.println("Cliente não encontrado!");
                return;
            }

            System.out.print("Novo nome (" + cliente.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                cliente.setNome(nome);
            }

            System.out.print("Novo email (" + cliente.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                cliente.setEmail(email);
            }

            System.out.print("Novo telefone (" + cliente.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) {
                cliente.setTelefone(telefone);
            }

            clienteDAO.atualizar(cliente);
            System.out.println("Cliente atualizado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }

    private static void excluirCliente() {
        System.out.println("=== EXCLUIR CLIENTE ===");
        listarClientes();

        System.out.println("Digite o ID do cliente a ser excluído: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Cliente cliente = clienteDAO.buscarPorId(id);

            if (cliente == null) {
                System.out.println("Cliente não encontrado!");
                return;
            }

            System.out.print("Tem certeza que deseja excluir o cliente " + cliente.getNome() + "? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                clienteDAO.deletar(id);
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
}