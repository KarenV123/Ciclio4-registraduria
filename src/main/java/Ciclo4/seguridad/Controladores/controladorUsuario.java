package Ciclo4.seguridad.Controladores;
import Ciclo4.seguridad.Modelos.usuario;
import Ciclo4.seguridad.Repositorios.repositorioUsuario;
import Ciclo4.seguridad.Repositorios.repositorioRol;
import Ciclo4.seguridad.Modelos.rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class controladorUsuario {
    @Autowired
    private repositorioUsuario usuarioRepositorio;
    @Autowired
    private repositorioRol rolRespositorios;

    @GetMapping("")
    public List<usuario> index(){
        return this.usuarioRepositorio.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public usuario create(@RequestBody usuario infoUsuario){
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return this.usuarioRepositorio.save(infoUsuario);
    }

    @GetMapping("{id}")
    public usuario show(@PathVariable String id){
        usuario usuarioActual=this.usuarioRepositorio
                .findById(id)
                .orElse(null);
        return usuarioActual;
    }

    @PutMapping("{id}")
    public usuario update(@PathVariable String id, @RequestBody usuario
            infoUsuario){
        usuario usuarioActual=this.usuarioRepositorio
                .findById(id)
                .orElse(null);
        if (usuarioActual!=null){
            usuarioActual.setSeudonimo(infoUsuario.getSeudonimo());
            usuarioActual.setCorreo(infoUsuario.getCorreo());
            usuarioActual.setContrasena(convertirSHA256(infoUsuario.getContrasena()))
            ;
            return this.usuarioRepositorio.save(usuarioActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        usuario usuarioActual=this.usuarioRepositorio
                .findById(id)
                .orElse(null);
        if (usuarioActual!=null){
            this.usuarioRepositorio.delete(usuarioActual);
        }
    }
    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    /**
     * Relación (1 a n) entre rol y usuario
     * @param id
     * @param id_rol
     * @return
     */
    @PutMapping("{id}/rol/{id_rol}")
    public usuario asignarRolAUsuario (@PathVariable String id,@PathVariable String id_rol){
        usuario
                usuarioActual=this.usuarioRepositorio.findById(id).orElseThrow(RuntimeException::new);
        rol
                rolActual=this.rolRespositorios.findById(id_rol).orElseThrow(RuntimeException::new);
        usuarioActual.setRol(rolActual);
        return this.usuarioRepositorio.save(usuarioActual);
    }
}