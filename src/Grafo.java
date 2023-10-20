import java.util.ArrayList;
import java.util.List;

public class Grafo {
  List<Cidade> cidades;
  List<Estrada> estradas;

  public Grafo() {
    cidades = new ArrayList<>();
    estradas = new ArrayList<>();
  }

  public List<Cidade> getCidades() {
    return cidades;
  }

  public List<Estrada> getEstradas() {
    return estradas;
  }

  public Cidade addCidade(String nomeCidade) {
    Cidade nova = new Cidade(nomeCidade);
    if (!verificaCidade(nova)) {
      cidades.add(nova);
    }
    return nova;
  }

  public Cidade getCidadeByNome(String nome) {
    for (Cidade cidade : cidades) {
      if (cidade.getNome().equals(nome)) {
        return cidade;
      }
    }
    return null; // Return null if no matching city is found
  }

  public Estrada addEstrada(Cidade origem, Cidade destino, int distancia) {
    Estrada nova = new Estrada(origem, destino, distancia);
    if (!verificaEstrada(nova)) {
      estradas.add(nova);
    }
    return nova;
  }

  private boolean verificaCidade(Cidade c) {
    for (Cidade cidade : cidades) {
      if (cidade.getNome().equals(c.getNome())) {
        return true;
      }
    }
    return false;
  }

  private boolean verificaEstrada(Estrada e) {
    for (Estrada estrada : estradas) {
      if ((estrada.getOrigem().getNome().equals(e.getOrigem().getNome()) && estrada.getDestino().getNome().equals(e.getDestino().getNome()))
          || (estrada.getOrigem().getNome().equals(e.getDestino().getNome()) && estrada.getDestino().getNome().equals(e.getOrigem().getNome()))
      ) {
        return true;
      }
    }
    return false;
  }


  public Estrada getEstrada(Cidade origem, Cidade destino) {
    for (Estrada estrada : estradas) {
      if (estrada.getOrigem().getNome().equals(origem.getNome()) && estrada.getDestino().getNome().equals(destino.getNome())) {
        return estrada;
      }
    }
    return null;
  }

  public void printAllEstradas() {
    for (Estrada estrada : estradas) {
      System.out.println("origem: " + estrada.getOrigem().getNome() + ", destino: " + estrada.getDestino().getNome() + ", distancia: " + estrada.getDistancia()+ " km");
    }
  }
}
