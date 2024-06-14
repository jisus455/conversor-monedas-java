package conversionmonedas.historial;

import conversionmonedas.file.GuardarDatos;
import conversionmonedas.modelos.Historial;

import java.util.ArrayList;
import java.util.List;

public class HistorialConversiones {

    private List<Historial> historial;

    public HistorialConversiones(){
        this.historial = new ArrayList<>();
    }

    public void agregarConversion(String monedaOrg, String monedaDst, double resultado) {
        Historial historial = new Historial(monedaOrg, monedaDst, resultado);
        this.historial.add(historial);
    }

    public List<Historial> obtenerHistorial() {
        return this.historial;
    }

    public boolean guardarConversiones() {
        GuardarDatos guardarDatos = new GuardarDatos();
        return guardarDatos.guadarHistorial(this.historial);
    }
}
