package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import domain.Persona;
import java.sql.*;
import java.util.*;

public class PersonaDAO {

    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM  persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE  FROM persona WHERE id_persona = ?";
    private static final String SQL_UPDATE = "UPDATE  persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";

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
                int idPersona = rs.getInt("id_persona");
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
        } finally {// Close conections
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        System.out.println(personas);
        return personas;
    }

    public int insertar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            // The first parameter corresponds to the firs value in our SQL query
            //  VALUES(first, second, thirt, fourth)
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());

            // Execute Query
            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {// Close all connections
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registros;
    }

    public void eliminar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            // The first parameter corresponds to the firs value in our SQL query
            //  idpersona = first
            stmt.setInt(1, persona.getIdPersona());
            System.out.println(stmt);
            // Execute Query
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {// Close all connections
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int actualizar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            // The first parameter corresponds to the firs value in our SQL query
            //  SET nombre = first, apellido = second, email = thirt, telefono = fourth WHERE id_persona = fifth
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());

            // Execute Query
            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {// Close all connections
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return registros;
    }
}
