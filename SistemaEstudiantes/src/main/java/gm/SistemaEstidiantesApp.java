package gm;

import gm.datos.EstudianteDAO;
import gm.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstidiantesApp {
  public static void main(String[] args) {
    var salir = false;
    var consola = new Scanner(System.in);
    //Se crea una instancia de la clase de servicio
    var estudianteDao = new EstudianteDAO();
    while (!salir){
      try {
        mostrarMenu();
        salir = ejecutarOpciones(consola,estudianteDao);
      }catch (Exception e){
        System.out.println("Ocurrio un error al ejecutar operacion" + e.toString());
      }

    }//fin while

  }
  public static void mostrarMenu(){
    System.out.print("""
            *** Sistema de estudiantes ***
            1. Listar Estudiantes
            2. Buscar Estudiante
            3. Agregar Estudiante
            4. Modificar Estudiante
            5. Eliminar Estudiante
            6. Salir del sistema
            Elige una opcion:
            """);
  }
  public static  boolean ejecutarOpciones(Scanner consola,EstudianteDAO estudianteDAO) {
  var opcion = Integer.parseInt(consola.nextLine());
  var salir = false;
  switch (opcion){
    case 1->{//Listado de estudiantes
      System.out.print("Listado de Estudiantes");
      var estudiantes = estudianteDAO.listar();
      estudiantes.forEach(System.out::println);
      }
      case 2->{//buscar estudiante por id
        System.out.println("Introduce el id de estidiante");
        var idEstudiante = Integer.parseInt(consola.nextLine());
        var estudiante = new Estudiante(idEstudiante);
        var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
        if(encontrado)
          System.out.println("Estudiante encontrado "+ estudiante);
        else
          System.out.println("Estudiante no encontrado " + estudiante);

      }
      case 3->{
        System.out.println("Agregar Estudiante: ");
        System.out.print("Nombre: ");
        var nombre = consola.nextLine();
        System.out.print("Apellido: ");
        var apellido = consola.nextLine();
        System.out.print("Telefono: ");
        var telefono = consola.nextLine();
        System.out.print("email: ");
        var email = consola.nextLine();
        //Crear el objeto estudiante sin id
        var estudiante = new Estudiante(nombre,apellido,telefono,email);
        var agregado = estudianteDAO.agregarEstudiante(estudiante);
        if(agregado)
          System.out.println("Estudiante agregado: "+ estudiante);
        else
          System.out.println("EStudiante no agregado "+estudiante);
      }
      case 4->{//modificar estudiante
        System.out.print("Modificar estudiante");
        System.out.print("ID estudiante");
        var idEstudiante = Integer.parseInt(consola.nextLine());
        System.out.print("Nombre: ");
        var nombre = consola.nextLine();
        System.out.print("Apellido: ");
        var apellido = consola.nextLine();
        System.out.print("Telefono: ");
        var telefono = consola.nextLine();
        System.out.print("Email: ");
        var email = consola.nextLine();
        //Crear el objeto a modificar
        var estudiante = new Estudiante(idEstudiante,nombre,apellido,telefono,email);
        var modificado = estudianteDAO.modificarEstudiante(estudiante);
        if(modificado)
          System.out.println("Estudiante modificado: "+ estudiante);
        else
          System.out.println("Estudiante no modificado "+ estudiante);
        }
        case 5->{
      //eliminar estudiante
          System.out.print("Eliminar estudiante");
          System.out.print("ID Estudiante");
          var idEstudiante = Integer.parseInt(consola.nextLine());
          var estudiante = new Estudiante(idEstudiante);
          var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
          if(eliminado)
            System.out.println("Estudiante eliminado "+ estudiante);
          else
            System.out.println("Estudiante no eliminado "+ estudiante);
        }
        case 6->{//salir
          System.out.println("Hasta pronto");
          salir=true;

        }
    default -> System.out.println("Opcion no reconcido");

    }
    return salir;
  }//ejecutar opciones
    }