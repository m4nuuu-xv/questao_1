package com.exercicios;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Questao1 {
    private static final String URL = "jdbc:mysql://localhost:3306/exercicios_bd";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static void main(String[] args) {
        executar();
    }

    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        String continuar = "S";

        while (continuar.equalsIgnoreCase("S")) {
            System.out.println("\n=== CADASTRO DE ALUNO ===");
            
            try {
                // Ler dados do aluno
                System.out.print("Digite o número do aluno: ");
                int numero = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                System.out.print("Digite o nome do aluno: ");
                String nome = scanner.nextLine();

                System.out.print("Digite o curso: ");
                String curso = scanner.nextLine();

                System.out.print("Digite a nota 1: ");
                double nota1 = scanner.nextDouble();

                System.out.print("Digite a nota 2: ");
                double nota2 = scanner.nextDouble();

                System.out.print("Digite a nota 3: ");
                double nota3 = scanner.nextDouble();

                System.out.print("Digite a nota 4: ");
                double nota4 = scanner.nextDouble();
                scanner.nextLine(); // Consumir a quebra de linha

                // Inserir aluno no banco de dados
                boolean inserido = inserirAluno(numero, nome, curso, nota1, nota2, nota3, nota4);

                if (inserido) {
                    System.out.println("✓ Aluno cadastrado com sucesso!");
                } else {
                    System.out.println("✗ Erro ao cadastrar aluno. Verifique se o número já existe.");
                }

            } catch (InputMismatchException e) {
                System.out.println("✗ Erro: Digite valores válidos!");
                scanner.nextLine(); // Limpar buffer
                continue;
            } catch (Exception e) {
                System.out.println("✗ Erro inesperado: " + e.getMessage());
            }

            // Perguntar se deseja continuar
            System.out.print("\nDeseja inserir outro aluno? (S/N): ");
            continuar = scanner.nextLine();
        }

        System.out.println("\n✓ Cadastro finalizado.\n");
    }

    private static boolean inserirAluno(int numero, String nome, String curso, 
                                        double nota1, double nota2, double nota3, double nota4) {
        String sql = "INSERT INTO alunos (numero, nome, curso, nota1, nota2, nota3, nota4) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, numero);
            pstmt.setString(2, nome);
            pstmt.setString(3, curso);
            pstmt.setDouble(4, nota1);
            pstmt.setDouble(5, nota2);
            pstmt.setDouble(6, nota3);
            pstmt.setDouble(7, nota4);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Código de erro para chave duplicada
                return false;
            }
            System.out.println("Erro de banco de dados: " + e.getMessage());
            return false;
        }
    }
}
