import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Grafo grafo = new Grafo();
    lerArquivo(grafo);

    int op = -1;

    do {
      System.out.println("-------------------MENU PRINCIPAL-------------------");
      System.out.println("Digite a opção desejada: ");
      System.out.println("1 - receber uma recomendação de visitação em todas as cidades e todas as estradas");
      System.out.println("2 - verificar se existe estrada de qualquer cidade para qualquer cidade");
      System.out.println("3 - no caso de não ser possível chegar em alguma cidade via transporte terrestre," +
          " identificação das cidades que encontram-se nessas condições");
      System.out.println("4 - recomendação de uma rota para um passageiro que deseja partir da rodoviária, " +
          "percorrer todas as cidades conectadas e retornar à rodoviária, percorrendo a menor distância possível.");
      System.out.println("0 - Encerrar o programa");
      op = sc.nextInt();

      switch (op) {
        case 1:
          System.out.println("-------------RECOMENDAÇÃO DE VISITAÇÃO-------------");
          grafo.sugerirVisitacao();
          break;
        case 2:
          grafo.isConexo();
          break;
        case 3:
          grafo.identificaCidadesIsoladas();
        case -1:
        default:
          break;
      }
    } while (op != 0);

  }

  public static void lerArquivo(Grafo grafo) {
    try {
      File entrada = new File("entrada.txt");
      Scanner sc = new Scanner(entrada);

      // Cria as cidades primeiro
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        int indiceDoisPontos = line.indexOf(":");
        if (indiceDoisPontos != -1) {
          String cidadeOrigem = line.substring(0, indiceDoisPontos).trim();
          grafo.addCidade(cidadeOrigem);
        }
      }

      // Resetar o scanner para o início do arquivo
      sc.close();
      sc = new Scanner(entrada);

      // Ler e adicionar conexões
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        int indiceDoisPontos = line.indexOf(":");
        if (indiceDoisPontos != -1) {
          String cidadeOrigem = line.substring(0, indiceDoisPontos).trim();
          Cidade origem = grafo.getCidadeByNome(cidadeOrigem);

          String parteConexoes = line.substring(indiceDoisPontos + 1).trim();
          String conexoes[] = parteConexoes.split(",");

          for (String conexao : conexoes) {
            int indiceAbreParenteses = conexao.indexOf("(");
            int indiceFechaParenteses = conexao.indexOf(")");
            if (indiceAbreParenteses != -1 && indiceFechaParenteses != -1) {
              String cidadeDestino = conexao.substring(0, indiceAbreParenteses).trim();
              String distanciaStr = conexao.substring(indiceAbreParenteses + 1, indiceFechaParenteses);
              int distancia = Integer.parseInt(distanciaStr.replaceAll("[^0-9]", ""));

              Cidade destino = grafo.getCidadeByNome(cidadeDestino);
              origem.addDestino(destino, distancia);
            }
          }
        }
      }

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}