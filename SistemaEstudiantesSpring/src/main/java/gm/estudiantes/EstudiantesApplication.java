package gm.estudiantes;

import gm.estudiantes.modelo.Estudiante;
import gm.estudiantes.servicio.EstudianteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger =
			LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion...");
		// Levantar la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada!"+ System.lineSeparator());
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Ejecutando metodo run de Spring..." + nl);
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}// fin while
	}

	private void mostrarMenu(){
		logger.info(nl);
		logger.info("""
						*** Sistema de Estudiantes ***
						1. Listar Estudiantes
						2. Buscar Estudiante
						3. Agregar Estudiante
						4. Modificar Estudiante
						5. Eliminar Estudiante
						6. Salir
						Elige un opcion:""");
	}

	private boolean ejecutarOpciones(Scanner consola){
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion){
			case 1 -> { // Listar estudiantes
				logger.info(nl + "Listado de Estudiantes: " + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante -> logger.info(estudiante.getNombre() +" | "+estudiante.getApellido() +" | "+estudiante.getTelefono()+" | "+estudiante.getEmail() + nl)));
			}
			case 2->{//buscar
				logger.info("Introduce el id de estudiante a buscar: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante!=null)
					logger.info("Estudiante encontrado: "+ estudiante.getNombre() + nl);
				else
					logger.info("Estudiante no encontrado con id : "+ idEstudiante + nl);
			}
			case 3->{//Agregar estudiante
				logger.info("Agregar estudiante" + nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();

				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Telefono: ");
				var telefono = consola.nextLine();
				logger.info("Email: ");
				var email = consola.nextLine();
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);

				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado: "+estudiante + nl);
			}
			case 4->{//modificar estudiante
				logger.info("Modificar estudiante "+ nl);
				logger.info("ID estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				//buscamos el estudiante a modificar
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante!=null){
					logger.info("Nombre: ");
					var nombre = consola.nextLine() ;
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Telefono: ");
					var telefono = consola.nextLine();
					logger.info("Email: ");
					var email = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado correctamente");
				}else{
					logger.info("Estudiante no encontrado con el id "+idEstudiante);
				}
			}
			case 5->{//eliminar estudiante
				logger.info("Introduce el id del estudiante: ...");
				var idEstudiante =  Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante!=null){
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado correctamente :"+estudiante.getNombre());
				}else{
					logger.info("Estudiante no encontrado con el id: "+idEstudiante);
				}


			}
			case 6->{
				logger.info("Hasta pronto...");
				salir=true;

			}

		}// fin switch
		return salir;
	}
}
