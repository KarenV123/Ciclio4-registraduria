package Ciclo4.seguridad.Repositorios;
import Ciclo4.seguridad.Modelos.usuario;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface repositorioUsuario extends MongoRepository<usuario,String> {
}

