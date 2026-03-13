/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conexion;

/**
 *
 * @author ROSARIO
 */

import java.sql.Connection;
public class TestConexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Connection c = ConexionBD.conectar();
        if (c != null) {
            System.out.println("✅ Conexión exitosa a la base de datos");
        } else {
            System.out.println("❌ No se pudo conectar a la base de datos");
        }
    }
    
}
