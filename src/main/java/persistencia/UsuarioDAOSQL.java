package persistencia;

import excepciones.CredencialesInvalidasException;
import excepciones.PersistenciaException;

import java.nio.file.Path;
import java.sql.*;

public class UsuarioDAOSQL implements UsuarioDAO {

    private static final String COLUMNAS = "username, password";

    private final String url;
    private boolean baseInicializada = false;

    /** @param archivo archivo de la base SQLite; si no existe, se crea. */
    public UsuarioDAOSQL(Path archivo) {
        this.url = "jdbc:sqlite:" + archivo;
    }

    private Connection conectar() throws SQLException {
        Connection con = DriverManager.getConnection(url);
        if (!baseInicializada) {
            inicializarBase(con);
            baseInicializada = true;
        }
        return con;
    }

    @Override
    public Usuario guardar(Usuario usuario) throws PersistenciaException {
        String sql = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.executeUpdate();
            return usuario;
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo guardar el usuario " + usuario.getUsername(), e);
        }
    }

    @Override
    public Usuario buscarPorUsername(String username) throws PersistenciaException {
        String sql = "SELECT " + COLUMNAS + " FROM usuarios WHERE username = ?";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo buscar el usuario " + username, e);
        }
    }

    // ---------- Creación del esquema ----------

    private void inicializarBase(Connection con) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                " username TEXT PRIMARY KEY NOT NULL, " +
                " password TEXT NOT NULL)";
        try (Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        }
    }

    // ---------- Mapeo fila <-> objeto ----------

    private Usuario mapear(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getString("username"),
                rs.getString("password"));
    }
}