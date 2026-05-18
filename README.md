# 📚 Questões BDD - Sistema de Gestão de Alunos

## 📋 Estrutura do Projeto

```
questoes_bdd/
├── build.gradle              # Configuração Gradle com dependências
├── mysql/
│   └── schema.sql           # Script SQL para banco de dados
├── src/main/java/com/exercicios/
│   ├── Menu.java            # Menu principal integrado
│   ├── Questao1.java        # Cadastro de alunos
│   └── Questao2.java        # Listagem e estatísticas
└── README.md                # Este arquivo
```

## 🚀 Como Executar

### 1️⃣ **Configurar o Banco de Dados MySQL**

```bash
mysql -u root < mysql/schema.sql
```

### 2️⃣ **Executar o Programa Principal (Menu)**

```bash
gradle run
```

### 3️⃣ **Ou Executar Questões Individualmente**

```bash
# Apenas Questão 1 (Cadastro)
gradle runQuestao1

# Apenas Questão 2 (Listagem)
gradle runQuestao2
```

## 📝 Descrição das Questões

### **Questão 1 - Cadastro de Alunos**
- Interface interativa para cadastrar alunos
- Armazena: número, nome, curso e 4 notas
- Validação de número único
- Loop para múltiplos cadastros
- Mensagens de sucesso/erro

### **Questão 2 - Listagem e Estatísticas**
- Lista todos os alunos cadastrados
- Exibe informações formatadas com média
- Define situação:
  - ✅ **Aprovado**: média ≥ 7
  - ⚠️ **Recuperação**: 3 ≤ média < 7
  - ❌ **Reprovado**: média < 3
- Mostra estatísticas da turma

## 🔧 Requisitos Técnicos

- **Java**: 11+
- **Gradle**: 6.0+
- **MySQL**: 5.7+
- **MySQL Connector/J**: 8.3.0

## 📦 Dependências

```gradle
implementation 'com.mysql:mysql-connector-j:8.3.0'
```

---

**Autor**: m4nuuu-xv  
**Data**: 2026-05-18
