package persistencia;

import excepciones.CredencialesInvalidasException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    /**
     * post-cond: se inicializa el DAO y se asegura la existencia de la tabla de usuarios en la base de datos
     */
    public UsuarioDAO() {
        crearTablaSiNoExiste();
    }

    /**
     * post-cond: se crea la tabla 'usuarios' en SQLite con id autoincremental, username único y password obligatorio si no existía previamente
     */
    private void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +   //ID AUTOINCREMENTAL
                " username TEXT UNIQUE NOT NULL, " +          //USERNAME ÚNICO
                " password TEXT NOT NULL);";                  //PASSWORD OBLIGATORIO

        try (Connection conn = ConexionBD.conectar(); Statement stmt = conn.createStatement()) { //TRY WITH RESOURCES
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla de usuarios: " + e.getMessage());
        }
    }

    /**
     * pre-cond: usuarioDTO != null, usuarioDTO.getUsername() != null y usuarioDTO.getPassword() != null
     * post-cond: se inserta un nuevo registro con las credenciales del usuario en la base de datos
     * @param usuarioDTO el objeto DTO que contiene el nombre de usuario y la contraseña a registrar
     * @returns true si el registro fue exitoso, false si ocurrió un error o el usuario ya existe
     */
    public boolean registrarUsuario(UsuarioDTO usuarioDTO) {
        String sql = "INSERT INTO usuarios(username, password) VALUES(?, ?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuarioDTO.getUsername());
            pstmt.setString(2, usuarioDTO.getPassword());
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar (probablemente el usuario ya exista): " + e.getMessage());
            return false;
        }
    }

    /**
     * pre-cond: username != null y password != null
     * post-cond: consulta la base de datos para verificar credenciales o lanza excepcion si fallan
     * @param username el nombre de usuario ingresado
     * @param password la contraseña ingresada
     * @returns un UsuarioDTO mapeado si las credenciales son correctas
     * @throws CredencialesInvalidasException si el usuario no existe o la password es incorrecta
     */
    public UsuarioDTO validarCredenciales(String username, String password) throws CredencialesInvalidasException {
        String sql = "SELECT username, password FROM usuarios WHERE username = ? AND password = ?";
        UsuarioDTO usuarioEncontrado = null;

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuarioEncontrado = new UsuarioDTO(
                            rs.getString("username"),
                            rs.getString("password")
                    );
                } else {
                    throw new CredencialesInvalidasException("Usuario o contraseña incorrectos.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de base de datos al validar credenciales: " + e.getMessage());
        }

        return usuarioEncontrado;
    }
}