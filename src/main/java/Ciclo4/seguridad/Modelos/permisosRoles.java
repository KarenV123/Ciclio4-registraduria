package Ciclo4.seguridad.Modelos;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class permisosRoles {
    @Id
    private String _id;

    @DBRef
    private rol Rol;

    @DBRef
    private permisos Permiso;

    public permisosRoles() {
    }

    public String get_id() {
        return _id;
    }

    public rol getRol() {
        return Rol;
    }

    public permisos getPermiso() {
        return Permiso;
    }

    public void setRol(rol Rol) {
        this.Rol = Rol;
    }

    public void setPermiso(permisos permiso) {
        this.Permiso = permiso;
    }
}