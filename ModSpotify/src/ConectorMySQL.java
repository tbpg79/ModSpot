
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Clase que conecta a la base de datos
 * @author PC GAMING
 */
public class ConectorMySQL {
// Variables de la clase
    private Connection _conexion;
    private String _host = "localhost";
    private String _bd = "spotifymod";
    private String _usuario = "root";
    private String _password = "";
/**
 * Constructor por el que según sus parámetros nos conectará a la base de datos
 * @param host Define el host para la conexión
 * @param bd    Define la base de datos que utilizamos
 * @param usuario Define el usuario que se va a conectar
 * @param password Define la contraseña del usuario que se va a conectar
 */
    public ConectorMySQL(String host, String bd, String usuario, String password) {
        _host = host;
        _bd = bd;
        _usuario = usuario;
        _password = password;
    }
/**
 * Método por el que se conectará a la base de datos
 * @throws  SQLException  Si la conexión da error
 */
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://" + _host + "/" + _bd;
        try {
            _conexion = DriverManager.getConnection(url, _usuario, _password);
            System.out.println("> Conexión a la base de datos exitosa.");
        } catch (SQLException ex) {
            System.out.println("> Error en la conexión a la base de datos: " + ex.getMessage());
            throw ex;
        }
    }
/**
 * Método por el que se desconectará a la base de datos
 * @throws  SQLException  Si la conexión da error al cerrar la base de datos
 */
    public void desconectar() throws SQLException {
        if (_conexion != null) {
            try {
                _conexion.close();
                System.out.println("> Conexión cerrada correctamente.");
            } catch (SQLException ex) {
                System.out.println("> Error al cerrar la conexión: " + ex.getMessage());
                throw ex;
            }
        }
    }
/**
 * Método por el que se realizará consultas a la base de datos
 * @throws  SQLException  Si la consulta da error
 */
    public void realizarConsulta(String consulta) throws SQLException {

        conectar();
        PreparedStatement statement = null;
        ResultSet resultado = null;

        try {
            statement = _conexion.prepareStatement(consulta);
            resultado = statement.executeQuery();

            if (resultado.next()) {
                String titulo = resultado.getString("Titulo");
                String descripcion = resultado.getString("Informacion");
                String cantantes = resultado.getString("Cantantes");

                // Almacena los datos en las variables de instancia
                _titulo = titulo;
                _descripcion = descripcion;
                _cantantes = cantantes;
            }
        } catch (SQLException ex) {
            System.out.println("> Error en la consulta a la base de datos: " + ex.getMessage());
            throw ex;
        } finally {
            if (resultado != null) {
                resultado.close();
            }
            if (statement != null) {
                statement.close();
            }
            desconectar();
        }
    }
/**
 * Método por el que se creará la base de datos limpia(vacía)
 * @throws  SQLException  Si la consulta da error
 */
    public void inicializacionLimpia() throws SQLException {
        conectar();

        // Crear la base de datos si no existe
        Statement statement = null;
        try {
            statement = _conexion.createStatement();
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS spotifymod";
            String deleteDatabaseSQL = "DROP DATABASE spotifymod";
            statement.execute(deleteDatabaseSQL);
            statement.execute(createDatabaseSQL);
        } catch (SQLException ex) {
            System.out.println("Error al crear la base de datos: " + ex.getMessage());
            throw ex;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

        // Seleccionar la base de datos recién creada
        String useDatabaseSQL = "USE spotifymod";
        statement = _conexion.createStatement();
        statement.execute(useDatabaseSQL);

        // Crear las tablas vacías
        try {
            String crearTablaSQL = "CREATE TABLE generosmusicales ("
                    + "Genero varchar(20) DEFAULT NULL, "
                    + "Titulo text DEFAULT NULL, "
                    + "Informacion text DEFAULT NULL, "
                    + "Cantantes varchar(50) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
            statement = _conexion.createStatement();
            statement.execute(crearTablaSQL);
        } catch (SQLException ex) {
            System.out.println("Error en la instalación limpia (creación de tablas): " + ex.getMessage());
            throw ex;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        desconectar();
    }

// Agrega estas variables de instancia para almacenar los datos recuperados
    private String _titulo;
    private String _descripcion;
    private String _cantantes;
/**
 * Método que devuelve el título
 * @return El nombre del título
 */
    public String getTitulo() {
        return _titulo;
    }
/**
 * Método que devuelve la descripción
 * @return La descripción de la canción
 */
    public String getDescripcion() {
        return _descripcion;
    }
/**
 * Método que devuelve el cantante
 * @return El nombre del cantante
 */
    public String getCantantes() {
        return _cantantes;
    }
/**
 * Método que devuelve la conexión
 * @return La conexion
 */
    public Connection getConexion() {
        return _conexion;
    }

}
