import java.util.ArrayList;
import java.util.List;

public class Cidade {
  private String nome;
  private int tempoDescoberta;
  private int tempoTermino;
  private Cidade pai;
  private List<Estrada> estradas;

  public Cidade(String nome) {
    this.nome = nome;
    this.tempoDescoberta = 0;
    this.tempoTermino = 0;
    this.pai = null;
    this.estradas = new ArrayList<>();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getTempoDescoberta() {
    return tempoDescoberta;
  }

  public void setTempoDescoberta(int tempoDescoberta) {
    this.tempoDescoberta = tempoDescoberta;
  }

  public int getTempoTermino() {
    return tempoTermino;
  }

  public void setTempoTermino(int tempoTermino) {
    this.tempoTermino = tempoTermino;
  }

  public Cidade getPai() {
    return pai;
  }

  public void setPai(Cidade pai) {
    this.pai = pai;
  }

  public List<Estrada> getEstradas() {
    return estradas;
  }

  public void addDestino(Cidade destino, int distancia) {
    estradas.add(new Estrada(destino, distancia ));
  }

  public void printDestinos() {
    System.out.println("Origem:" + this.getNome());
    for (Estrada estrada: estradas) {
      System.out.println("\tDestino: " + estrada.getDestino().getNome() + "\tDistância: " + estrada.getDistancia());
    }
  }

}
