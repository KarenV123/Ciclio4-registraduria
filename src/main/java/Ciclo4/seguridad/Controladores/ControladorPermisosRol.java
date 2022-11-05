package Ciclo4.seguridad.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import Ciclo4.seguridad.Modelos.permisos;
import Ciclo4.seguridad.Modelos.rol;
import Ciclo4.seguridad.Modelos.permisosRoles;
import Ciclo4.seguridad.Repositorios.repositorioPermisos;
import Ciclo4.seguridad.Repositorios.repositorioRol;
import Ciclo4.seguridad.Repositorios.repositorioPermisosRoles;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos_roles")

public class ControladorPermisosRol {
    @Autowired
    private repositorioPermisosRoles permisoRolesRepositorio;

    @Autowired
    private repositorioPermisos permisosRepositorio;

    @Autowired
    private repositorioRol rolRepositorio;

    @GetMapping("")
    public List<permisosRoles> index(){
        return this.permisoRolesRepositorio.findAll();
    }
/** Asignación rol y permiso
 * @param id_rol
 * @param id_permiso
 * @return
 */

@ResponseStatus(HttpStatus.CREATED)
@PostMapping("rol/{id_rol}/permiso/{id_permiso}")
public permisosRoles create(@PathVariable String id_rol,@PathVariable
String id_permiso){
    permisosRoles nuevo=new permisosRoles();
    rol elRol=this.rolRepositorio.findById(id_rol).get();
    permisos
            elPermiso=this.permisosRepositorio.findById(id_permiso).get();
    if (elRol!=null && elPermiso!=null){
        nuevo.setPermiso(elPermiso);
        nuevo.setRol(elRol);
        return this.permisoRolesRepositorio.save(nuevo);
    }else{
        return null;
    }
}
    @GetMapping("{id}")
    public permisosRoles show(@PathVariable String id){
        permisosRoles permisosRolesActual=this.permisoRolesRepositorio
                .findById(id)
                .orElse(null);
        return permisosRolesActual;
    }

    /**
     * Modificación Rol y Permiso
     * @param id
     * @param id_rol
     * @param id_permiso
     * @return
     */
    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public permisosRoles update(@PathVariable String id,@PathVariable
    String id_rol,@PathVariable String id_permiso){
        permisosRoles permisosRolesActual=this.permisoRolesRepositorio
                .findById(id)
                .orElse(null);
        rol elRol=this.rolRepositorio.findById(id_rol).get();
        permisos
                elPermiso=this.permisosRepositorio.findById(id_permiso).get();
        if(permisosRolesActual!=null && elPermiso!=null && elRol!=null){
            permisosRolesActual.setPermiso(elPermiso);
            permisosRolesActual.setRol(elRol);
            return
                    this.permisoRolesRepositorio.save(permisosRolesActual);
        }else{
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        permisosRoles permisosRolesActual=this.permisoRolesRepositorio
                .findById(id)
                .orElse(null);
        if (permisosRolesActual!=null){
            this.permisoRolesRepositorio.delete(permisosRolesActual);
        }
    }
}