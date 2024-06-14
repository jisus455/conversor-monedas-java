package conversionmonedas.modelos;

import com.google.gson.internal.LinkedTreeMap;

public record Moneda(String result,
                     String base_code,
                     LinkedTreeMap conversion_rates) {
}
