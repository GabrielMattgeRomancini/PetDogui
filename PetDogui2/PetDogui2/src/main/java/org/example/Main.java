package org.example;

import models.Cachorro;
import models.Cor;
import models.Pelagem;
import models.Raca;
import service.CachorroService;
import service.CachorroServiceImpl;
import service.CorService;
import service.CorServiceImpl;
import service.PelagemService;
import service.PelagemServiceImpl;
import service.RacaService;
import service.RacaServiceImpl;
import exceptions.ExceptionNullEmpty;
import utils.ListUtils;
import utils.ValidationUtils;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final RacaService racaService = new RacaServiceImpl();
    private static final CorService corService = new CorServiceImpl();
    private static final PelagemService pelagemService = new PelagemServiceImpl();
    private static final CachorroService cachorroService = new CachorroServiceImpl();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            showMenu();

            String input = scanner.nextLine();  // Use scanner.nextLine() para capturar a linha inteira de entrada
            if (input == null || input.trim().isEmpty()) {
                System.out.println("Entrada inválida, não pode ser vazio");
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, por favor, insira um número válido");
                continue;
            }

            switch (choice) {
                case 1:
                    insertRaca();
                    break;
                case 2:
                    insertCor();
                    break;
                case 3:
                    insertPelagem();
                    break;
                case 4:
                    insertCachorro();
                    break;
                case 5:
                    listAllCachorros();
                    break;
                case 6:
                    listAllRacas();
                    break;
                case 7:
                    listAllCores();
                    break;
                case 8:
                    listAllPelagens();
                    break;
                case 9:
                    updateCachorro();
                    break;
                case 10:
                    deleteCachorro();
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("===== PetShop Menu =====");
        System.out.println("1. Inserir Raça");
        System.out.println("2. Inserir Cor");
        System.out.println("3. Inserir Pelagem");
        System.out.println("4. Inserir Cachorro");
        System.out.println("5. Listar Todos os Cachorros");
        System.out.println("6. Listar todas as Raças");
        System.out.println("7. Listar todas as Cores");
        System.out.println("8. Listar todas as pelagens");
        System.out.println("9. Atualizar Cachorro");
        System.out.println("10. Deletar Cachorro");
        System.out.println("11. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void insertRaca() {
        System.out.print("Digite o nome da raça: ");
        String nomeRaca = scanner.nextLine();

        try {
            ValidationUtils.validateString(nomeRaca, "Nome da raça");
            Raca raca = new Raca(null, nomeRaca);
            racaService.saveRaca(raca);
            System.out.println("Raça inserida com sucesso: " + raca);
        } catch (ExceptionNullEmpty e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void insertCor() {
        System.out.print("Digite o nome da cor: ");
        String nomeCor = scanner.nextLine();

        try {
            ValidationUtils.validateString(nomeCor, "Nome da cor");
            Cor cor = new Cor(null, nomeCor);
            corService.saveCor(cor);
            System.out.println("Cor inserida com sucesso: " + cor);
        } catch (ExceptionNullEmpty e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void insertPelagem() {
        System.out.print("Digite o tipo de pelagem: ");
        String tipoPelagem = scanner.nextLine();

        try {
            ValidationUtils.validateString(tipoPelagem, "Tipo de pelagem");
            Pelagem pelagem = new Pelagem(null, tipoPelagem);
            pelagemService.savePelagem(pelagem);
            System.out.println("Pelagem inserida com sucesso: " + pelagem);
        } catch (ExceptionNullEmpty e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void insertCachorro() {
        System.out.print("Digite o nome do cachorro: ");
        String nomeCachorro = scanner.nextLine();

        System.out.print("Digite o tamanho do cachorro: ");
        int tamanho = Integer.parseInt(scanner.nextLine());

        boolean temPerfume = false;
        System.out.print("O cachorro tem perfume? (Sim/Não): ");
        while (true) {
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("Sim")) {
                temPerfume = true;
                break;
            } else if (resposta.equalsIgnoreCase("Não")) {
                temPerfume = false;
                break;
            } else {
                System.out.println("Resposta inválida, digite (Sim/Não)");
            }
        }

        Date dataNascimento = null;
        while (dataNascimento == null){
            System.out.print("Digite a data de nascimento (yyyy-mm-dd): ");
            String dataNascimentoStr = scanner.nextLine();
            try{
                dataNascimento = java.sql.Date.valueOf(dataNascimentoStr);
            }catch (IllegalArgumentException e){
                System.out.println("Formato da data informado errado.");
            }
        }

        System.out.print("Digite o ID da raça: ");
        Long idRaca = Long.parseLong(scanner.nextLine());
        Raca raca = racaService.getRacaById(idRaca);

        System.out.print("Digite o ID da cor: ");
        Long idCor = Long.parseLong(scanner.nextLine());
        Cor cor = corService.getCorById(idCor);

        System.out.print("Digite o ID da pelagem: ");
        Long idPelagem = Long.parseLong(scanner.nextLine());
        Pelagem pelagem = pelagemService.getPelagemById(idPelagem);

        Cachorro cachorro = new Cachorro(null, nomeCachorro, tamanho, temPerfume, dataNascimento, raca, cor, pelagem);
        cachorroService.saveCachorro(cachorro);
        System.out.println("Cachorro inserido com sucesso: " + cachorro);
    }

    private static void listAllCachorros() {
        List<Cachorro> cachorros = cachorroService.getAllCachorros();
        ListUtils.printList(cachorros, "Nenhum cachorro encontrado.");
    }

    private static void listAllRacas(){
        List<Raca> racas = racaService.getAllRacas();
        ListUtils.printList(racas, "Não foi encontrada nenhuma raça.");
    }

    private static void listAllCores(){
        List<Cor> cores = corService.getAllCores();
        ListUtils.printList(cores, "Nenhuma cor encontrada.");
    }

    private static void listAllPelagens(){
        List<Pelagem> pelagens = pelagemService.getAllPelagens();
        ListUtils.printList(pelagens, "Nenhuma pelagem foi encontrada.");
    }

    private static void updateCachorro() {
        System.out.print("Digite o ID do cachorro a ser atualizado: ");
        Long id = Long.parseLong(scanner.nextLine());
        Cachorro cachorro = cachorroService.getCachorroById(id);

        if (cachorro == null) {
            System.out.println("Cachorro não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do cachorro: ");
        String nomeCachorro = scanner.nextLine();

        System.out.print("Digite o novo tamanho do cachorro: ");
        int tamanho = Integer.parseInt(scanner.nextLine());

        System.out.print("O cachorro tem perfume? (true/false): ");
        boolean temPerfume = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Digite a nova data de nascimento (yyyy-mm-dd): ");
        String dataNascimentoStr = scanner.nextLine();
        Date dataNascimento = java.sql.Date.valueOf(dataNascimentoStr);

        System.out.print("Digite o novo ID da raça: ");
        Long idRaca = Long.parseLong(scanner.nextLine());
        Raca raca = racaService.getRacaById(idRaca);

        System.out.print("Digite o novo ID da cor: ");
        Long idCor = Long.parseLong(scanner.nextLine());
        Cor cor = corService.getCorById(idCor);

        System.out.print("Digite o novo ID da pelagem: ");
        Long idPelagem = Long.parseLong(scanner.nextLine());
        Pelagem pelagem = pelagemService.getPelagemById(idPelagem);

        cachorro.setNomeCachorro(nomeCachorro);
        cachorro.setTamanho(tamanho);
        cachorro.setTemPerfume(temPerfume);
        cachorro.setDataNascimento(dataNascimento);
        cachorro.setRaca(raca);
        cachorro.setCor(cor);
        cachorro.setPelagem(pelagem);

        cachorroService.updateCachorro(cachorro);
        System.out.println("Cachorro atualizado com sucesso: " + cachorro);
    }

    private static void deleteCachorro() {
        System.out.print("Digite o ID do cachorro a ser deletado: ");
        Long id = Long.parseLong(scanner.nextLine());
        cachorroService.deleteCachorro(id);
        System.out.println("Cachorro deletado com sucesso.");
    }
}
