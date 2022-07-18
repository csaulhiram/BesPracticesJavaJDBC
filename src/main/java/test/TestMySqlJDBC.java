package test;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMySqlJDBC {

    public static void main(String[] args) {
            var url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrival=true";
        // Password
        String password = "728542";
        // Usuario 
        String usuario = "root";
        
         try {

            Connection conexion = DriverManager.getConnection(url, usuario, password);

            // El tipo Statement nos permitirá realizar consultas a la base de datos.
             java.sql.Statement instruccion = conexion.createStatement();
            
            var sql = "SELECT id_persona, nombre, apellido, email, telefono FROM pesona";
            ResultSet resultado = instruccion.executeQuery(sql);

            // Procesamos el resultado
            // El método next() nos indicará si tenemos un elem,ento a iterar
            // por lo tanto regresa true si hay elementos a iterar
            while (resultado.next()) {
                System.out.println("****************************");
                System.out.println("Id Persona: " + resultado.getInt("id_persona"));
                System.out.println("Nombre: " + resultado.getString("nombre"));
                System.out.println("Apellido: " + resultado.getString("apellido"));
                System.out.println("email: " + resultado.getString("email"));
                System.out.println("telefono: " + resultado.getString("telefono"));
                System.out.println("****************************");
            }

            // Cerrar los objetos que hemos abierto
            // Se cierra en orden inverso a como los fuimos creando
            resultado.close();
            instruccion.close();
            conexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
