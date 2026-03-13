
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static Connection conectar() {
        Connection conexion = null;
        try {
            
            String url = "jdbc:mysql://localhost:3306/ChancaGym"; 
            String usuario = "root"; 
            String contraseña = "root1234"; 

            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}