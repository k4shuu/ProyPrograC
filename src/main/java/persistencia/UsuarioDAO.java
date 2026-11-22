package persistencia;

import excepciones.PersistenciaException;

public interface UsuarioDAO {
    /** Guarda un usuario nuevo y le asigna su id generado. */
    Usuario guardar(Usuario usuario) throws PersistenciaException;

    /** @return el usuario con ese username, o null si no existe. */
    Usuario buscarPorUsername(String username) throws PersistenciaException;
}
