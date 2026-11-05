package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:sqlite:usuarios.db";

    /**
     * pre-cond: true (la URL de conexion es valida)
     * post-cond: se establece y retorna una conexion activa con la base de datos SQLite
     * @returns la instancia de Connection establecida, o null si ocurre un error de conexion
     */
    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos SQLite: " + e.getMessage());
        }

        return conexion;
    }
}