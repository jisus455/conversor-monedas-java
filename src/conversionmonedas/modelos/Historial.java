package conversionmonedas.modelos;

public class Historial {

    private String monedaOrigen;
    private String monedaDestino;
    private double resultado;

    public Historial(String monedaOrigen, String monedaDestino, double resultado) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return
                "monedaOrigen='" + this.monedaOrigen + '\'' +
                ", monedaDestino='" + this.monedaDestino + '\'' +
                ", resultado=" + this.resultado;
    }
}
