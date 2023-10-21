public class Estrada {
  private Cidade destino;
  private int distancia;

  private boolean arvore;
  private boolean retorno;
  private boolean avanco;
  private boolean cruzamento;

  public Estrada(Cidade destino, int distancia) {
    this.destino = destino;
    this.distancia = distancia;
    this.arvore = false;
    this.retorno = false;
    this.avanco = false;
    this.cruzamento = false;
  }

  public Cidade getDestino() {
    return destino;
  }

  public void setDestino(Cidade destino) {
    this.destino = destino;
  }

  public double getDistancia() {
    return distancia;
  }

  public void setDistancia(int distancia) {
    this.distancia = distancia;
  }

  public boolean isArvore() {
    return arvore;
  }

  public void setArvore(boolean arvore) {
    this.arvore = arvore;
  }

  public boolean isRetorno() {
    return retorno;
  }

  public void setRetorno(boolean retorno) {
    this.retorno = retorno;
  }

  public boolean isAvanco() {
    return avanco;
  }

  public void setAvanco(boolean avanco) {
    this.avanco = avanco;
  }

  public boolean isCruzamento() {
    return cruzamento;
  }

  public void setCruzamento(boolean cruzamento) {
    this.cruzamento = cruzamento;
  }
}
