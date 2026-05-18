package com.exercicios;

import java.sql.*;

public class Questao2 {
    private static final String URL = "jdbc:mysql://localhost:3306/exercicios_bd";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static void main(String[] args) {
        try {
            listarAlunos();
        } catch (SQLException e) {
            System.out.println("Erro de banco de dados: " + e.getMessage());
        }
    }

    private static void listarAlunos() throws SQLException {
        String sql = "SELECT numero, nome, curso, nota1, nota2, nota3, nota4 FROM alunos ORDER BY numero";
        
        int totalAlunos = 0;
        int aprovados = 0;
        int recuperacao = 0;
        int reprovados = 0;
        double somaMedias = 0;

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== RELATÓRIO DE ALUNOS ===\n");

            while (rs.next()) {
                int numero = rs.getInt("numero");
                String nome = rs.getString("nome");
                String curso = rs.getString("curso");
                double nota1 = rs.getDouble("nota1");
                double nota2 = rs.getDouble("nota2");
                double nota3 = rs.getDouble("nota3");
                double nota4 = rs.getDouble("nota4");

                // Calcular média
                double media = (nota1 + nota2 + nota3 + nota4) / 4.0;
                somaMedias += media;
                totalAlunos++;

                // Determinar situação
                String situacao;
                if (media >= 7) {
                    situacao = "Aprovado";
                    aprovados++;
                } else if (media < 3) {
                    situacao = "Reprovado";
                    reprovados++;
                } else {
                    situacao = "Recuperação";
                    recuperacao++;
                }

                // Exibir informações do aluno
                System.out.println("Aluno " + numero + ": " + nome);
                System.out.println("Curso: " + curso);
                System.out.println("Notas: " + nota1 + " " + nota2 + " " + nota3 + " " + nota4);
                System.out.printf("Situação: %s com média %.2f%n\n", situacao, media);
            }

            // Calcular média geral
            double mediaGeral = totalAlunos > 0 ? somaMedias / totalAlunos : 0;

            // Exibir estatísticas
            System.out.println("=== ESTATÍSTICAS DA TURMA ===");
            System.out.println("Número total de alunos: " + totalAlunos);
            System.out.println("Número de alunos aprovados: " + aprovados);
            System.out.println("Número de alunos em recuperação: " + recuperacao);
            System.out.println("Número de alunos reprovados: " + reprovados);
            System.out.printf("Média geral da turma: %.4f%n", mediaGeral);

        } catch (SQLException e) {
            System.out.println("Erro ao consultar alunos: " + e.getMessage());
        }
    }
}
