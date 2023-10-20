import java.util.ArrayList;
import java.util.List;

public class Cidade {
  private String nome;
  private List<Cidade> destinos;

  public Cidade(String nome) {
    this.nome = nome;
    this.destinos = new ArrayList<>();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void addDestino(Cidade cidade) {
    destinos.add(cidade);
  }

  public List<Cidade> getDestinos() {
    return destinos;
  }


}
