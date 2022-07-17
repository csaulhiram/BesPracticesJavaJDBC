package datos;

import static datos.Conexion.getConnection;
import domain.Persona;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAO {

    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM  persona";

    public List<Persona> seleccionar() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = getConnection(); // We connect with Database
            stmt = conn.prepareStatement(SQL_SELECT); // We prepare SQL sentence

            rs = stmt.executeQuery();// We execute Query

            while (rs.next()) { // Get registers
                int idPersona = rs.getInt("id_pdersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");

                // Create object type Persona
                persona = new Persona(idPersona, nombre, apellido, email, telefono);
                // We add an element at the ArrayList
                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return personas;
    }
}
