package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/registrar")
public class RegistroUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chancagym", "root", "root1234");

            // Llamar al procedimiento almacenado
            CallableStatement cs = con.prepareCall("{CALL ValidarNuevoUsuario(?, ?, ?, ?, ?, ?, ?)}");

            cs.setString(1, nombre);
            cs.setString(2, apellido);
            cs.setString(3, dni);
            cs.setString(4, telefono);
            cs.setString(5, correo);
            cs.setString(6, contrasena);
            cs.registerOutParameter(7, java.sql.Types.INTEGER);

            cs.execute();

            int resultado = cs.getInt(7);

            if (resultado == 1) {
                response.sendRedirect("registro_exitoso.html");
            } else {
                response.sendRedirect("registro.html?error");
            }

            cs.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar: " + e.getMessage());
            request.getRequestDispatcher("registro.html").forward(request, response);
        }
    }
}
