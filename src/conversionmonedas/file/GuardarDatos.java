package conversionmonedas.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conversionmonedas.modelos.Historial;

import java.io.*;
import java.util.List;

public class GuardarDatos {

    public boolean guadarHistorial(List<Historial> historial) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try {
            FileWriter escritura = new FileWriter("historial.json");
            escritura.write(gson.toJson(historial));
            escritura.close();
            return true;
        } catch(IOException e) {
            return false;
        }
    }
}
