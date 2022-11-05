package Ciclo4.seguridad.Modelos;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@Document()
public class usuario {

    @Id
    private String _id;
    private String seudonimo;
    private String correo;
    private String contrasena;
    @DBRef
    private rol Rol;

    public usuario(String seudonimo, String correo, String contrasena) {
        this.seudonimo = seudonimo;
        this.correo = correo;
        this.contrasena = contrasena;
    }
}
