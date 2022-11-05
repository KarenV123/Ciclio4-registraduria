package Ciclo4.seguridad.Controladores;
import Ciclo4.seguridad.Modelos.permisos;
import Ciclo4.seguridad.Repositorios.repositorioPermisos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permiso")
public class controladorPermisos {
    @Autowired
    private repositorioPermisos permisosRepositorio;

    @GetMapping("")
    public List<permisos> index(){
        return this.permisosRepositorio.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public permisos create(@RequestBody permisos infoPermisos){
        infoPermisos.setUrl((infoPermisos.getUrl()));
        return this.permisosRepositorio.save(infoPermisos);
    }
    @GetMapping("{id}")
    public permisos show(@PathVariable String id){
        permisos permisosActuales =this.permisosRepositorio
                .findById(id)
                .orElse(null);
        return permisosActuales;
    }
    @PutMapping("{id}")
    public permisos update(@PathVariable String id, @RequestBody permisos
            infoRol){
        permisos rolActual =this.permisosRepositorio
                .findById(id)
                .orElse(null);
        if (rolActual !=null){
            rolActual.setUrl(infoRol.getUrl());
            rolActual.setMetodo(infoRol.getMetodo());
            ;
            return this.permisosRepositorio.save(rolActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        permisos rolActual =this.permisosRepositorio
                .findById(id)
                .orElse(null);
        if (rolActual !=null){
            this.permisosRepositorio.delete(rolActual);
        }
    }
}