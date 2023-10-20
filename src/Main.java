import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Grafo grafo = new Grafo();
    lerArquivo(grafo);

    for (Cidade cidade : grafo.getCidades()) {
      System.out.println("origem: " + cidade.getNome());
      for (Cidade destino : cidade.getDestinos()) {
        System.out.println("\tdestino: " + destino.getNome() + "\tdistancia: ");
      }
    }
  }

  public static void lerArquivo(Grafo grafo) {
    try {
      File entrada = new File("entrada.txt");
      Scanner sc = new Scanner(entrada);

      // Read and store cities first
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        int indiceDoisPontos = line.indexOf(":");
        if (indiceDoisPontos != -1) {
          String cidadeOrigem = line.substring(0, indiceDoisPontos).trim();
          grafo.addCidade(cidadeOrigem);
        }
      }

      // Reset the scanner to the beginning of the file
      sc.close();
      sc = new Scanner(entrada);

      // Read connections and add them
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
              origem.addDestino(destino);
              grafo.addEstrada(origem, destino, distancia);
            }
          }
        }
      }

      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}