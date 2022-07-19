package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {

        System.out.println("***************************************");
        System.out.println("\t\tSELECT QUERY");
        System.out.println("***************************************");
        PersonaDAO personaDao = new PersonaDAO();
        List<Persona> personas = personaDao.seleccionar();

        // Show database information
        listarPersonas(personas);

        System.out.println("***************************************");
        System.out.println("\t\tINSERT QUERY");
        System.out.println("***************************************");

        // Creating object type Persona
        Persona persona2 = new Persona("Peter", "Juarez", "test4@test.com", "1234567890");
        // NOTE: the variable personaDAO was created at the last class 
        personaDao.insertar(persona2);

        // Show the new list of personas
        personas = personaDao.seleccionar();
        listarPersonas(personas);
        System.out.println("***************************************");
        System.out.println("\t\tDELETE QUERY");
        System.out.println("***************************************");

        Persona persona3 = new Persona(17);
        personaDao.eliminar(persona3);

        // Show the new list of personas
        personas = personaDao.seleccionar();
        listarPersonas(personas);

        System.out.println("***************************************");
        System.out.println("\t\tUPDATE QUERY");
        System.out.println("***************************************");

        Persona persona4 = new Persona(16, "saul hiram", "castilloCastro", "update@test.com", "77777777777");
        personaDao.actualizar(persona4);

        // Show the new list of personas
        personas = personaDao.seleccionar();
        listarPersonas(personas);
    }

    public static void listarPersonas(List<Persona> personas) {
        for (Persona persona : personas) {
            System.out.println("persona: " + persona);
        }
    }
}
