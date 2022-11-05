package Ciclo4.seguridad.Repositorios;
import Ciclo4.seguridad.Modelos.permisosRoles;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface repositorioPermisosRoles extends MongoRepository<permisosRoles,String> {
}
