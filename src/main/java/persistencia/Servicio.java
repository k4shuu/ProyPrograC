package persistencia;

import excepciones.CredencialesInvalidasException;
import excepciones.PersistenciaException;
import excepciones.UsuarioYaExisteException;

public class Servicio {
    private UsuarioDAO usuarioDao;

    public Servicio(UsuarioDAO usuario){
        this.usuarioDao=usuario;
    }

    public Usuario validarCredenciales(String username, String password)
            throws CredencialesInvalidasException, PersistenciaException {
        Usuario usuario = usuarioDao.buscarPorUsername(username);
        if (usuario == null || !usuario.getPassword().equals(password)) {
            throw new CredencialesInvalidasException("Usuario o contraseña incorrectos.");
        }
        return usuario;
    }
    public Usuario registrarUsuario(Usuario usuario)
            throws UsuarioYaExisteException, PersistenciaException {
        String username=usuario.getUsername();
        String password=usuario.getPassword();
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        String userLimpio = username.trim();
        if (usuarioDao.buscarPorUsername(userLimpio) != null) {
            throw new UsuarioYaExisteException("El usuario '" + userLimpio + "' ya está registrado.");
        }
        Usuario nuevo = new Usuario(userLimpio, password);
        return usuarioDao.guardar(nuevo);
    }
}
