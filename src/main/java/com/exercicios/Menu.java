package com.exercicios;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcao = "";

        while (!opcao.equals("3")) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Questão 1 - Cadastrar Aluno");
            System.out.println("2. Questão 2 - Listar Alunos e Estatísticas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    Questao1.executar();
                    break;
                case "2":
                    Questao2.executar();
                    break;
                case "3":
                    System.out.println("\n✓ Programa encerrado.");
                    break;
                default:
                    System.out.println("✗ Opção inválida!");
            }
        }

        scanner.close();
    }
}
