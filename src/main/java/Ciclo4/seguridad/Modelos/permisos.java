package Ciclo4.seguridad.Modelos;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document()
public class permisos {

    @Id
    private String _id;
    private String url;
    private String metodo;

    public permisos(String url, String metodo) {
        this.url = url;
        this.metodo = metodo;
    }
}
