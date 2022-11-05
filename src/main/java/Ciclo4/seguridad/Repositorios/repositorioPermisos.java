package Ciclo4.seguridad.Repositorios;
import Ciclo4.seguridad.Modelos.permisos;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface repositorioPermisos extends MongoRepository<permisos,String> {

}
