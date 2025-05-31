# 🖨 Sistema de Impressão

## Alunos:
- **Mateus Silva Pereira** - (24.00511-8)

## Descrição
Este sistema foi desenvolvido como parte de um trabalho da disciplina de Algoritmos, estruturas de dados e programação do Instituto Mauá de Tecnologia. O objetivo é simular um sistema de impressão, onde os usuários podem enviar documentos para serem impressos, e o sistema gerencia a fila de impressão.

## Funcionalidades

Fila de impressão comum (**FIFO**, _First In First Out_):
- **Adicionar Documento**: Usuários podem adicionar documentos à fila de impressão.
- **Imprimir Documento**: O sistema imprime documentos na ordem em que foram adicionados.
- **Visualizar Fila**: Usuários podem visualizar a fila de documentos pendentes para impressão.
- **Buscar Documento**: Usuários podem buscar um documento específico na fila de impressão.

Pilha de impressão emergencial (**LIFO**, _Last In First Out_):
- **Adicionar Documento Emergencial**: Usuários podem adicionar documentos à pilha de impressão emergencial.
- **Imprimir Documento Emergencial**: O sistema imprime documentos emergenciais, que são processados de maneira na ordem inversa em que foram adicionados.
- **Visualizar Pilha Emergencial**: Usuários podem visualizar a pilha de documentos emergenciais pendentes para impressão.
- **Buscar Documento Emergencial**: Usuários podem buscar um documento específico na pilha de impressão emergencial.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal utilizada para o desenvolvimento do sistema.

## Como Executar

1. Certifique-se de ter o Java instalado em sua máquina.

2. Clone o repositório:
   ```bash
   git clone https://github.com/Matelz/T2-CIC203.git
   ```

3. Navegue até o diretório do projeto:
   ```bash
    cd T2-CIC203
    ```

4. Compile o projeto:
   ```bash
   javac *.java
   ```

5. Execute o programa:
   ```bash
    java TesteFilaDeImpressao
    ```