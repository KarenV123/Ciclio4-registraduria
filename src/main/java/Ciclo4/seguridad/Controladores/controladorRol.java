package Ciclo4.seguridad.Controladores;
import Ciclo4.seguridad.Modelos.rol;
import Ciclo4.seguridad.Repositorios.repositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rol")
public class controladorRol {
    @Autowired
    private repositorioRol rolRepositorio;

    @GetMapping("")
    public List<rol> index(){
        return this.rolRepositorio.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public rol create(@RequestBody rol infoRol){
        infoRol.setNombre((infoRol.getNombre()));
        return this.rolRepositorio.save(infoRol);
    }
    @GetMapping("{id}")
    public rol show(@PathVariable String id){
        rol rolActual =this.rolRepositorio
                .findById(id)
                .orElse(null);
        return rolActual;
    }
    @PutMapping("{id}")
    public rol update(@PathVariable String id, @RequestBody rol
            infoRol){
        rol rolActual =this.rolRepositorio
                .findById(id)
                .orElse(null);
        if (rolActual !=null){
            rolActual.setNombre(infoRol.getNombre());
            rolActual.setDescripcion(infoRol.getDescripcion());
            ;
            return this.rolRepositorio.save(rolActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        rol rolActual =this.rolRepositorio
                .findById(id)
                .orElse(null);
        if (rolActual !=null){
            this.rolRepositorio.delete(rolActual);
        }
    }






}
