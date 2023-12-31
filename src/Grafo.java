import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Grafo {
  List<Cidade> cidades;
  List<Cidade> visitadas;
  List<List<Cidade>> componentes;
  int tempoGlobal = 0;

  public Grafo() {
    cidades = new ArrayList<>();
    visitadas = new ArrayList<>();
    componentes = new ArrayList<>();
  }

  public List<Cidade> getCidades() {
    return cidades;
  }

  public Cidade addCidade(String nome) {
    Cidade nova = new Cidade(nome);
    if (!verificaCidade(nova)) {
      cidades.add(nova); // adiciona a cidade na lista se ela não existir
    }
    return nova; // retorna a cidade criada ou encontrada na lista
  }

  public Cidade getCidadeByNome(String nome) {
    for (Cidade cidade : cidades) {
      if (cidade.getNome().equals(nome)) {
        return cidade;
      }
    }
    return null; // retorna nulo se não existir a cidade na lista
  }

  private boolean verificaCidade(Cidade c) {
    for (Cidade cidade : cidades) {
      if (cidade.getNome().equals(c.getNome())) {
        return true;
      }
    }
    return false;
  }

  public void sugerirVisitacao() {
    executarBusca();
    for (Cidade cidade : visitadas) {
      System.out.println(cidade.getNome());
    }
  }

  private void executarBusca() {
    // settando variaveis
    tempoGlobal = 0;
    visitadas.clear();

    for (Cidade cidade : cidades) {
      cidade.setTempoDescoberta(0);
      cidade.setTempoTermino(0);
      cidade.setPai(null);
    }

    // chamando a funçao recursiva
    for (Cidade cidade : cidades) {
      if (cidade.getTempoDescoberta() == 0) {
        List<Cidade> componente = new ArrayList<>();
        buscaEmProfundidade(componente, cidade);
        componentes.add(componente);
      }
    }
  }

  public void isConexo() {
    if (tempoGlobal == 0) {
      executarBusca();
    }
    if (componentes.size() == 1)
      System.out.println("GRAFO CONEXO: Existem estradas de qualquer cidade para qualquer cidade");
    else
      System.out.println("GRAFO DESCONEXO: NÃO existem estradas de todas as cidades para todas as cidades");
  }

  private void buscaEmProfundidade(List<Cidade> componente, Cidade v) {
    visitadas.add(v);
    componente.add(v);
    v.setTempoDescoberta(++tempoGlobal);
    for (Estrada estrada : v.getEstradas()) {
      Cidade w = estrada.getDestino();
      if (w.getTempoDescoberta() == 0) {
        estrada.setArvore(true);
        w.setPai(v);
        buscaEmProfundidade(componente, w);
      } else {
        if (w.getTempoTermino() == 0) {
          estrada.setRetorno(true);
        } else {
          if (v.getTempoDescoberta() < w.getTempoDescoberta()) {
            estrada.setAvanco(true);
          } else {
            estrada.setCruzamento(true);
          }
        }
      }
    }
    v.setTempoTermino(++tempoGlobal);
  }

  public void exibeCidadesIsoladas() {
    for (int i = 0; i < componentes.size(); i++) {
      System.out.println("GRUPO DE CIDADES " + (i + 1));
      for (int j = 0; j < componentes.get(i).size(); j++) {
        System.out.println("\t" + componentes.get(i).get(j).getNome());
      }
    }
  }
}
