/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package centrovotacion;

import static centrovotacion.CentroVotacion.cargarUsuarios;
import static centrovotacion.CentroVotacion.cargarVotantes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author Crist
 */
public class CentroVotacion {

    /**
     * @param args the command line arguments
     */
    File c = new File("BasededatosCandidatos.txt");
    File e = new File("BasededatosElecciones.txt");
    File fc = new File("BasededatosVotante.txt");
    File f = new File("BasededatosUsuarios.txt");
    Scanner scan = new Scanner(System.in);
    int opcion = 0;

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        int opcion = 0;

        System.out.print("Ingrese contrasenia del administrador:");
        String contrasenia = scan.next();

        if (contrasenia.compareTo("#GuatemalaVotos") == 0) {
            System.out.print("Bienvenido al sistema:\n");
            System.out.print("\nMenu de Inicio\n");
            System.out.println("1. Registro de Usuarios");
            System.out.print("Seleccione operacion a realizar:");
            opcion = scan.nextInt();
        } else {
            System.err.println("Contraseña Incorrecta");
        }

        switch (opcion) {
            case 1:
                centrovotacion.CentroVotacion registrarUsuario = new centrovotacion.CentroVotacion();
                registrarUsuario.registroUsuarios();
                break;
        }
    }

    public void registroUsuarios() {
        System.out.println("Sistema de Votaciones\n");
        System.out.println("Registro de Usuarios\n");
        System.out.println("1.Crear Usuario del sistema");
        System.out.println("2.Modificar Usuario del sistema");
        System.out.println("3.Reiniciar Contrasenia");
        System.out.println("4.Ver registro de Usuarios");
        System.out.println("5.Eliminar Usuarios");

        System.out.println("Ingrese una opcion:");
        opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                crearUsuario();
                break;
            case 2:
                centrovotacion.CentroVotacion modificarUsuario = new centrovotacion.CentroVotacion();
                modificarUsuario.modificarUsuario();
                break;
            case 3:
                centrovotacion.CentroVotacion reinicioContrasenia = new centrovotacion.CentroVotacion();
                reinicioContrasenia.reinicioContrasenia();
                break;
            case 4:
                centrovotacion.CentroVotacion cargarUsuarios = new centrovotacion.CentroVotacion();
                cargarUsuarios.cargarUsuarios();
                break;
            case 5:
                centrovotacion.CentroVotacion eliminarUsuario = new centrovotacion.CentroVotacion();
                eliminarUsuario.eliminarUsuario();
                break;

        }
    }

    public void reinicioContrasenia() {
        try {
            ArrayList<Usuarios> usuarios;

            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(f))) {
                usuarios = (ArrayList<Usuarios>) entrada.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("El archivo de usuarios no existe.");
                return;
            }

            System.out.println("Ingrese el correo del usuario que desea editar:");
            String emailBuscado = scan.next();

            // Busca el usuario por su nombre
            boolean encontrado = false;
            for (Usuarios usuario : usuarios) {
                if (usuario.getEmail().equals(emailBuscado)) {
                    System.out.println("Usuario encontrado. ¿Qué campo desea editar? (psw)");
                    String campo = scan.next().toLowerCase();

                    switch (campo) {
                        case "psw":
                            System.out.println("Ingrese la nueva contraseña:");
                            usuario.setPsw(scan.next());
                            break;
                    }
                    encontrado = true;
                    break;
                }
            }

            // Si el usuario no se encuentra, muestra un mensaje
            if (!encontrado) {
                System.out.println("Usuario no encontrado.");
            } else {
                try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(f))) {
                    salida.writeObject(usuarios);
                    System.out.println("Usuario editado correctamente.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (java.util.InputMismatchException | ClassNotFoundException ex) {
            System.out.println("Error al editar el usuario. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void crearUsuario() {
        try {

            ArrayList<Usuarios> usuarios = new ArrayList<>();
            // Verifica si el archivo existe y no está vacío
            if (f.exists() && f.length() > 0) {
                try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(f))) {
                    usuarios = (ArrayList<Usuarios>) entrada.readObject();
                } catch (EOFException e) {
                    System.out.println("El archivo está vacío o no contiene datos válidos.");
                    usuarios = new ArrayList<>();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                usuarios = new ArrayList<>();
            }

            System.out.println("Ingrese su nombre");
            String nombre = scan.next();
            System.out.println("Ingrese su apellido");
            String apellido = scan.next();
            System.out.println("Ingrese su edad");
            int edad = scan.nextInt();
            System.out.println("Ingrese su genero");
            String sexo = scan.next();
            System.out.println("Ingrese su correo");
            String email = scan.next();
            System.out.println("Ingrese su contraseña");
            String psw = scan.next();
            System.out.println("Ingrese su rol: RegistrarVotantes, Votante, Auditor, Administrador");
            String rol = scan.next();

            if (rol.compareTo("RegistrarVotantes") == 0) {
                centrovotacion.CentroVotacion registrarVotantes = new centrovotacion.CentroVotacion();
                registrarVotantes.registrarVotantes();
            } else {
                if (rol.compareTo("Votante") == 0) {
                centrovotacion.CentroVotacion accesoVotante = new centrovotacion.CentroVotacion();
                accesoVotante.accesoVotante();
                } else {
                    if (rol.compareTo("Auditor") == 0) {
                        centrovotacion.CentroVotacion conteoVotos = new centrovotacion.CentroVotacion();
                        conteoVotos.conteoVotos();
                    } else {
                        if (rol.compareTo("Administrador") == 0) {
                            centrovotacion.CentroVotacion administrar = new centrovotacion.CentroVotacion();
                            administrar.administracionElecciones();
                        }
                    }
                }
            }

            usuarios.add(new Usuarios(nombre, apellido, edad, sexo, email, psw, rol));

            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(f))) {
                salida.writeObject(usuarios);
                System.out.println("Usuario creado y guardado correctamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (java.util.InputMismatchException ex) {
            System.out.println("Error al ingresar los datos del usuario. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        }
    }

    public static ArrayList<Usuarios> cargarUsuarios() {
        ArrayList<Usuarios> usuarios = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("BasededatosUsuarios.txt"))) {
            usuarios = (ArrayList<Usuarios>) entrada.readObject();
            for (Usuarios usuario : usuarios) {
                System.out.println("Nombre: " + usuario.getNombre());
                System.out.println("Apellido: " + usuario.getApellido());
                System.out.println("Edad: " + usuario.getEdad());
                System.out.println("Sexo: " + usuario.getSexo());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("Psw: " + usuario.getPsw());
                System.out.println("Rol: " + usuario.getRol());
                System.out.println("---------------------------------");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar usuarios desde el archivo.");
        }
        return usuarios;
    }

    public void modificarUsuario() {
        try {
            ArrayList<Usuarios> usuarios;

            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(f))) {
                usuarios = (ArrayList<Usuarios>) entrada.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("El archivo de usuarios no existe.");
                return;
            }

            System.out.println("Ingrese el nombre del usuario que desea editar:");
            String nombreBuscado = scan.next();

            // Busca el usuario por su nombre
            boolean encontrado = false;
            for (Usuarios usuario : usuarios) {
                if (usuario.getNombre().equals(nombreBuscado)) {
                    System.out.println("Usuario encontrado. ¿Qué campo desea editar? (nombre, apellido, edad, sexo, email, psw, rol)");
                    String campo = scan.next().toLowerCase();

                    switch (campo) {
                        case "nombre":
                            System.out.println("Ingrese el nuevo nombre:");
                            usuario.setNombre(scan.next());
                            break;
                        case "apellido":
                            System.out.println("Ingrese el nuevo apellido:");
                            usuario.setApellido(scan.next());
                            break;
                        case "edad":
                            System.out.println("Ingrese la nueva edad:");
                            usuario.setEdad(scan.nextInt());
                            break;
                        case "sexo":
                            System.out.println("Ingrese el nuevo sexo:");
                            usuario.setSexo(scan.next());
                            break;
                        case "email":
                            System.out.println("Ingrese el nuevo correo:");
                            usuario.setEmail(scan.next());
                            break;
                        case "psw":
                            System.out.println("Ingrese la nueva contraseña:");
                            usuario.setPsw(scan.next());
                            break;
                        case "rol":
                            System.out.println("Ingrese el nuevo rol:");
                            usuario.setRol(scan.next());
                            break;
                        default:
                            System.out.println("Campo no válido.");
                            break;
                    }
                    encontrado = true;
                    break;
                }
            }

            // Si el usuario no se encuentra, muestra un mensaje
            if (!encontrado) {
                System.out.println("Usuario no encontrado.");
            } else {
                try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(f))) {
                    salida.writeObject(usuarios);
                    System.out.println("Usuario editado correctamente.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (java.util.InputMismatchException | ClassNotFoundException ex) {
            System.out.println("Error al editar el usuario. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarUsuario() {
        System.out.println("Ingrese el nombre del usuario a eliminar");
        String nombre = scan.next();
        ArrayList<Usuarios> usuarios = cargarUsuarios();
        Usuarios usuarioEliminar = null;
        for (Usuarios usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuarioEliminar = usuario;
                break;
            }
        }
        if (usuarioEliminar != null) {
            usuarios.remove(usuarioEliminar);
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("BasededatosUsuarios.txt"))) {
                salida.writeObject(usuarios);
                System.out.println("Usuario eliminado correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al eliminar el usuario.");
            }
        } else {
            System.out.println("No se encontró ningún usuario con este nombre.");
        }
    }

    public void registrarVotantes() {
        System.out.println("Sistema de Votaciones\n");
        System.out.println("Registro de Votantes\n");
        System.out.println("1.Crear Votante del sistema");
        System.out.println("2.Modificar Votante del sistema");
        System.out.println("3.Reiniciar Contrasenia");
        System.out.println("4.Leer registros de la Base de datos");
        System.out.println("5.Realizar el voto.");

        System.out.println("Ingrese una opcion:");
        opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                centrovotacion.CentroVotacion crearVotante = new centrovotacion.CentroVotacion();
                crearVotante.crearVotantes();
                break;
            case 2:
                centrovotacion.CentroVotacion modificarVotante = new centrovotacion.CentroVotacion();
                modificarVotante.modificarVotante();
                break;
            case 3:
                centrovotacion.CentroVotacion reiniciarcontrasenia = new centrovotacion.CentroVotacion();
            case 4:
                centrovotacion.CentroVotacion cargarVotantes = new centrovotacion.CentroVotacion();
                cargarVotantes.cargarVotantes();
                break;
            case 5:
                centrovotacion.CentroVotacion accesoVotante = new centrovotacion.CentroVotacion();
                accesoVotante.accesoVotante();
                break;   
        }
    }

    public void crearVotantes() {
        try {
            ArrayList<Votantes> votantes = new ArrayList<>();
            if (fc.exists() && fc.length() > 0) {
                try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fc))) {
                    votantes = (ArrayList<Votantes>) entrada.readObject();
                } catch (FileNotFoundException e) {
                    votantes = new ArrayList<>();
                }
            } else {
                votantes = new ArrayList<>();
            }
            Date date = new Date();
            System.out.println("Ingrese sus nombre completo ");
            String nombreCompleto = scan.next();
            System.out.println("Ingrese sus apellidos completos ");
            String apellidoCompleto = scan.next();
            System.out.println("Ingrese su CUI ");
            String cui = scan.next();
            System.out.println("Ingrese su genero ");
            String genero = scan.next();
            System.out.println("Ingrese su fecha de nacimiento ");
            String fecha = scan.next();
            DateFormat format = new SimpleDateFormat("dd/mm/yyyy"); // Creamos un formato de fecha
            Date fechaNacimiento = null;
            try {
                fechaNacimiento = format.parse(fecha);
            } catch (ParseException ex) {
                Logger.getLogger(CentroVotacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            int edadd = edadMayor(fechaNacimiento, date);
            if (edadd >= 18) {
                System.out.println("El votante es mayor de 18 años y es elegible para votar.");
            } else {
                System.out.println("El votante es menor de 18 años y no puede votar.");
                return;
            }
            System.out.println("Ingrese su direccion de  recidencia ");
            String direccionResidencia = scan.next();
            System.out.println("Ingrese su departamento ");
            String departamento = scan.next();
            System.out.println("Ingrese su municipio");
            String municipio = scan.next();
            System.out.println("Ingrese su Pais");
            String pais = scan.next();
            System.out.println("Su rol es: Votante");
            System.out.println("Ingrese su correo Electronico");
            String email = scan.next();
            System.out.println("Ingrese la contrasenia");
            String contrasenia = scan.next();
            System.out.println("El votante esta Activo o Inactivo");
            String activo = scan.next();
            if (activo.compareTo("Activo") == 0) {
                System.out.println("El Usuario todavia esta Activo");
            } else {
                System.out.println("El Usuario  esta Inactivo(Por Fallecimiento)");
            }

            String caracteresPermitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
            int longitudContrasena = 16;
            SecureRandom random = new SecureRandom();
            StringBuilder contrasena = new StringBuilder();

            for (int i = 0; i < longitudContrasena; i++) {
                int indice = random.nextInt(caracteresPermitidos.length());
                char caracter = caracteresPermitidos.charAt(indice);
                contrasena.append(caracter);
            }

            System.out.println("Contraseña generada, guardarla para poder Cambiarla: " + contrasena.toString());

            votantes.add(new Votantes(nombreCompleto, apellidoCompleto, cui, genero, fechaNacimiento, direccionResidencia, departamento, municipio, pais, email, contrasenia));

            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fc))) {
                salida.writeObject(votantes);
                System.out.println("Votante creado y guardado correctamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (java.util.InputMismatchException | ClassNotFoundException ex) {
            System.out.println("Error al ingresar los datos del votante. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static int edadMayor(Date fechaNacimiento, Date fechaActual) {
        int edad = 0;
        int diferenciaAnios = fechaActual.getYear() - fechaNacimiento.getYear();
        int diferenciaMeses = fechaActual.getMonth() - fechaNacimiento.getMonth();
        int diferenciaDias = fechaActual.getDay() - fechaNacimiento.getDay();

        if (diferenciaMeses < 0 || (diferenciaMeses == 0 && diferenciaDias < 0)) {
            edad = diferenciaAnios - 1;
        } else {
            edad = diferenciaAnios;
        }
        return edad;
    }

    public static ArrayList<Votantes> cargarVotantes() {
        ArrayList<Votantes> votantes = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("BasededatosVotante.txt"))) {
            votantes = (ArrayList<Votantes>) entrada.readObject();
            for (Votantes votante : votantes) {
                System.out.println("Nombre Completo: " + votante.getNombreCompleto());
                System.out.println("Apellido Completo: " + votante.getApellidoCompleto());
                System.out.println("CUI: " + votante.getCui());
                System.out.println("Genero: " + votante.getGenero());
                System.out.println("Fecha de Nacimiento: " + votante.getFechaNacimiento());
                System.out.println("Direccion: " + votante.getDireccionResidencia());
                System.out.println("Departamento: " + votante.getDepartamento());
                System.out.println("Municiopio:" + votante.getMunicipio());
                System.out.println("Pais:" + votante.getPais());
                System.out.println("Corrreo Electronico: " + votante.getEmail());
                System.out.println("Contrasenia: " + votante.getContrasenia());
                System.out.println("--------------------------------------------------------");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar votantes desde el archivo.");
        }
        return votantes;
    }

    public void modificarVotante() {
        try {
            ArrayList<Votantes> votantes;

            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fc))) {
                votantes = (ArrayList<Votantes>) entrada.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("El archivo de votantes no existe.");
                return;
            }

            System.out.println("Ingrese el nombre del votante que desea editar:");
            String nombreBuscado = scan.next();

            // Busca el usuario por su nombre
            boolean encontrado = false;
            for (Votantes votante : votantes) {
                if (votante.getNombreCompleto().equals(nombreBuscado)) {
                    System.out.println("Votante encontrado. ¿Qué campo desea editar? (nombre, apellido, cui, genero, fecha, direccion, departamento, municipio, pais, email, contrasenia)");
                    String campo = scan.next().toLowerCase();

                    switch (campo) {
                        case "nombre":
                            System.out.println("Ingrese el nuevo nombre:");
                            votante.setNombreCompleto(scan.next());
                            break;
                        case "apellido":
                            System.out.println("Ingrese el nuevo apellido:");
                            votante.setApellidoCompleto(scan.next());
                            break;
                        case "cui":
                            System.out.println("Ingrese su nuevo cui:");
                            votante.setCui(scan.next());
                            break;
                        case "genero":
                            System.out.println("Ingrese el nuevo genero:");
                            votante.setGenero(scan.next());
                            break;
                        case "fecha":
                            System.out.println("Ingrese la nueva fecha se nacimiento:");
                            String fecha = scan.next();
                            DateFormat format = new SimpleDateFormat("dd/mm/yyyy"); // Creamos un formato de fecha
                            Date fechaNacimiento = null;
                            try {
                                fechaNacimiento = format.parse(fecha);
                            } catch (ParseException ex) {
                                Logger.getLogger(CentroVotacion.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            votante.setFechaNacimiento(fechaNacimiento);
                            break;

                        case "direccion":
                            System.out.println("Ingrese la nueva direccion:");
                            votante.setDireccionResidencia(scan.next());
                            break;
                        case "departamento":
                            System.out.println("Ingrese el nuevo departamento:");
                            votante.setDepartamento(scan.next());
                            break;
                        case "municipio":
                            System.out.println("Ingrese el nuevo municipio:");
                            votante.setMunicipio(scan.next());
                            break;
                        case "pais":
                            System.out.println("Ingrese el nuevo pais:");
                            votante.setPais(scan.next());
                            break;
                        case "email":
                            System.out.println("Ingrese el nuevo correo electronico:");
                            votante.setEmail(scan.next());
                            break;
                        case "contrasenia":
                            System.out.println("Ingrese la nueva contrasenia:");
                            votante.setContrasenia(scan.next());
                            break;
                        default:
                            System.out.println("Campo no válido.");
                            break;
                    }
                    encontrado = true;
                    break;
                }
            }

            // Si el usuario no se encuentra, muestra un mensaje
            if (!encontrado) {
                System.out.println("Votante no encontrado.");
            } else {
                try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fc))) {
                    salida.writeObject(votantes);
                    System.out.println("Votante editado correctamente.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (java.util.InputMismatchException | ClassNotFoundException ex) {
            System.out.println("Error al editar el votante. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void administracionElecciones() {
        System.out.println("Sistema de Votaciones\n");
        System.out.println("Administracion de Elecciones\n");
        System.out.println("1.Gestionar Elecciones.");
        System.out.println("2.Gestionar Candidatos.");
        System.out.println("3.Configurar Opciones de Eleccion");
        System.out.println("4.Ver elecciones creadas.");
        System.out.println("5.Ver candidatos creados.");
        System.out.println("6.Modificar Candidatos.");
        System.out.println("7.Eliminar Candidatos.");
        System.out.println("8.Configurar Eleccion.");
        System.out.println("Ingrese una Opcion:");
        int opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                centrovotacion.CentroVotacion gestionElecciones = new centrovotacion.CentroVotacion();
                gestionElecciones.gestionElecciones();
                break;
            case 2:
                centrovotacion.CentroVotacion gestionCandidatos = new centrovotacion.CentroVotacion();
                gestionCandidatos.gestionCandidatos();
                break;
            case 3:
                centrovotacion.CentroVotacion configurarOpciones = new centrovotacion.CentroVotacion();
                break;
            case 4:
                centrovotacion.CentroVotacion verElecciones = new centrovotacion.CentroVotacion();
                verElecciones.verElecciones();
                break;
            case 5:
                centrovotacion.CentroVotacion verCandidatos = new centrovotacion.CentroVotacion();
                verCandidatos.verCandidatos();
                break;
            case 6:
                centrovotacion.CentroVotacion modificarCandidatos = new centrovotacion.CentroVotacion();
                modificarCandidatos.modificarCandidatos();
                break;
            case 7:
                centrovotacion.CentroVotacion eliminarCandidatos = new centrovotacion.CentroVotacion();
                eliminarCandidatos.eliminarCandidatos();
                break;
            case 8:
                centrovotacion.CentroVotacion configurar = new centrovotacion.CentroVotacion();
                configurar.configuracionElecciones();
                break;
        }
    }

    public void gestionElecciones() {
        try {
            ArrayList<Elecciones> elecciones = new ArrayList<>();
            if (e.exists() && e.length() > 0) {
                // Intenta leer usuarios existentes desde el archivo
                try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(e))) {
                    elecciones = (ArrayList<Elecciones>) entrada.readObject();
                } catch (FileNotFoundException e) {
                    // Si el archivo no existe, crea una nueva lista de usuarios
                }
            } else {
                elecciones = new ArrayList<>();
            }
            Date date = new Date();
            System.out.println("Ingrese el titulo de la Eleccion:");
            String tituloEleccion = scan.next();
            System.out.println("Ingrese el proposito de la eleccion:");
            String proposito = scan.next();
            System.out.println("Ingrese la descripcion de la eleccion:");
            String descripcion = scan.next();
            System.out.println("Ingrese el codigo de indentificacion:");
            String codigo = scan.next();
            System.out.println("Ingrese la fecha de inicio de la eleccion:");
            String fechaInicio = scan.next();
            System.out.println("Ingrese la hora de inicio de la eleccion:");
            String horaInicio = scan.next();
            System.out.println("Ingrese la fecha de finalizacion de la eleccion:");
            String fechaFinal = scan.next();
            System.out.println("Ingrese la hora de finalizacion de la eleccion:");
            String horaFinal = scan.next();

            elecciones.add(new Elecciones(tituloEleccion, proposito, descripcion, codigo, fechaInicio, horaInicio, fechaFinal, horaFinal));

            // Escribe la lista actualizada de usuarios al archivo
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(e))) {
                salida.writeObject(elecciones);
                System.out.println("Eleccion creada y guardada correctamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (java.util.InputMismatchException | ClassNotFoundException ex) {
            System.out.println("Error al ingresar los datos de la eleccion. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

    public static ArrayList<Elecciones> verElecciones() {
        ArrayList<Elecciones> elecciones = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("BasededatosElecciones.txt"))) {
            elecciones = (ArrayList<Elecciones>) entrada.readObject();
            for (Elecciones eleccion : elecciones) {
                System.out.println("Titulo de la Eleccion: " + eleccion.getTituloEleccion());
                System.out.println("Proposito de la Eleccion: " + eleccion.getProposito());
                System.out.println("Descripcion de la Eleccion: " + eleccion.getDescripcion());
                System.out.println("Codigo de Identificacion de la Eleccion: " + eleccion.getCodigo());
                System.out.println("Fecha de Inicio de la Eleccion: " + eleccion.getFechaInicio());
                System.out.println("Hora de Inicio de la Eleccion: " + eleccion.getHoraInicio());
                System.out.println("Fecha de Finalizacion de la Eleccion: " + eleccion.getFechaFinal());
                System.out.println("Hora de Finalizacion de la eleccion:" + eleccion.getHoraFinal());
                System.out.println("-------------------------------------------------");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar elecciones desde el archivo.");
        }
        return elecciones;
    }

    public void gestionCandidatos() {
        try {
            ArrayList<Candidatos> candidatos = new ArrayList<>();
            if (c.exists() && c.length() > 0) {
                try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(c))) {
                    candidatos = (ArrayList<Candidatos>) entrada.readObject();
                } catch (FileNotFoundException e) {
                }
            } else {
                candidatos = new ArrayList<>();
            }

            System.out.println("Ingrese el nombre del candidato:");
            String nombreCandidato = scan.next();
            System.out.println("Ingrese la formacion del candidato:");
            String formacion = scan.next();
            System.out.println("Ingrese la experiencia Profecional del candidato:");
            String experiencia = scan.next();
            System.out.println("Ingrese el codigo Unico de Identificacion del candidato:");
            String cuiCandidato = scan.next();

            candidatos.add(new Candidatos(nombreCandidato, formacion, experiencia, cuiCandidato));

            // Escribe la lista actualizada de usuarios al archivo
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(c))) {
                salida.writeObject(candidatos);
                System.out.println("Candidato creado y guardado correctamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (java.util.InputMismatchException | ClassNotFoundException ex) {
            System.out.println("Error al ingresar los datos del candidato. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Candidatos> verCandidatos() {
        ArrayList<Candidatos> candidatos = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("BasededatosCandidatos.txt"))) {
            candidatos = (ArrayList<Candidatos>) entrada.readObject();
            for (Candidatos candidato : candidatos) {
                System.out.println("Nombre del Candidato:" + candidato.getNombreCandidato());
                System.out.println("Formacion del candidato:" + candidato.getFormacion());
                System.out.println("Experiencia Profecional del candidato:" + candidato.getExperiencia());
                System.out.println("Codigo Unico de Identificacion del candidato:" + candidato.getCuiCandidato());
                System.out.println("-----------------------------------------------------------------------------");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al Cargar candidatos desde el archivo.");
        }
        return candidatos;
    }

    public void modificarCandidatos() {
        try {
            ArrayList<Candidatos> candidatos;

            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(c))) {
                candidatos = (ArrayList<Candidatos>) entrada.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("El archivo de candidatos no existe.");
                return;
            }

            System.out.println("Ingrese el cui del candidato que desea editar:");
            String cuiBuscado = scan.next();

            // Busca el usuario por su nombre
            boolean encontrado = false;
            for (Candidatos candidato : candidatos) {
                if (candidato.getCuiCandidato().equals(cuiBuscado)) {
                    System.out.println("Candidato encontrado. ¿Que campo desea editar? (nombre, formacion, cui, experiencia)");
                    String campo = scan.next().toLowerCase();

                    switch (campo) {
                        case "nombre":
                            System.out.println("Ingrese el nuevo nombre:");
                            candidato.setNombreCandidato(scan.next());
                            break;
                        case "formacion":
                            System.out.println("Ingrese la nueva formacion:");
                            candidato.setFormacion(scan.next());
                            break;
                        case "cui":
                            System.out.println("Ingrese su nuevo cui:");
                            candidato.setCuiCandidato(scan.next());
                            break;
                        case "experiencia":
                            System.out.println("Ingrese la nueva experiencia:");
                            candidato.setExperiencia(scan.next());
                            break;
                        default:
                            System.out.println("Campo no válido.");
                            break;
                    }
                    encontrado = true;
                    break;
                }
            }

            // Si el usuario no se encuentra, muestra un mensaje
            if (!encontrado) {
                System.out.println("Candidato no encontrado.");
            } else {
                try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(c))) {
                    salida.writeObject(candidatos);
                    System.out.println("Candidato editado correctamente.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (java.util.InputMismatchException | ClassNotFoundException ex) {
            System.out.println("Error al editar el Candidato. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void eliminarCandidatos(){
      System.out.println("Ingrese el codigo Unico de Identificacion del Candidato para eliminarlo");
        String cui = scan.next();
        ArrayList<Candidatos> candidatos = verCandidatos();
        Candidatos candidatoEliminar = null;
        for (Candidatos candidato : candidatos) {
            if (candidato.getCuiCandidato().equals(cui)) {
                candidatoEliminar = candidato;
                break;
            }
        }
        if (candidatoEliminar != null) {
            candidatos.remove(candidatoEliminar);
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("BasededatosCandidatos.txt"))) {
                salida.writeObject(candidatos);
                System.out.println("Usuario eliminado correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al eliminar el usuario.");
            }
        } else {
            System.out.println("No se encontró ningún usuario con este nombre.");
        }  
    }

    public void configuracionElecciones() {
        try {
            ArrayList<Elecciones> elecciones;

            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(e))) {
                elecciones = (ArrayList<Elecciones>) entrada.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("El archivo no existe.");
                return;
            }

            System.out.println("Ingrese el cui de la eleccion que desea configurar:");
            String cuiBuscado = scan.next();

            // Busca el usuario por su nombre
            boolean encontrado = false;
            for (Elecciones eleccion : elecciones) {
                if (eleccion.getCodigo().equals(cuiBuscado)) {
                    System.out.println("Mostrar la lista de Candidatos(si.)");
                    String campo = scan.next().toLowerCase();

                    switch (campo) {
                        case "si":
                            centrovotacion.CentroVotacion candidatosEleccion = new centrovotacion.CentroVotacion();
                            candidatosEleccion.verCandidatos();
                            break;
                        default:
                            System.out.println("Campo no válido.");
                            break;
                    }
                    encontrado = true;
                    break;
                }
            }

            // Si el usuario no se encuentra, muestra un mensaje
            if (!encontrado) {
                System.out.println("Eleccion no encontrada.");
            } else {
                try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(e))) {
                    salida.writeObject(elecciones);
                    System.out.println("Eleccion fue configurada correctamente.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (java.util.InputMismatchException | ClassNotFoundException ex) {
            System.out.println("Error al configurar la eleccion. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void accesoVotante() {
        try {
            ArrayList<Votantes> votantes;

            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fc))) {
                votantes = (ArrayList<Votantes>) entrada.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("El archivo de votantes no existe.");
                return;
            }

            System.out.println("Ingrese su Cui");
            String cuiBuscado = scan.next();

            // Busca el usuario por su nombre
            boolean encontrado = false;
            for (Votantes votante : votantes) {
                if (votante.getCui().equals(cuiBuscado)) {
                    System.out.println("Ingrese su correo Electronico.");
                    String correoBuscado = scan.next();
                    if (votante.getContrasenia().equals(correoBuscado)) {
                        System.out.println("Ingrese su contrasenia.");
                        String contraseniaBuscada = scan.next();
                    }
                    System.out.println("Bienvenido al Sistema de Votacion");
                    System.out.println("Quieres emitir tu voto? (si, no)");
                    String campo = scan.next();

                    switch (campo) {
                        case "si":
                            centrovotacion.CentroVotacion emitirVotos = new centrovotacion.CentroVotacion();
                            emitirVotos.emitirVotos();
                            break;
                        case "no":
                            System.out.println("Vuelve a emitir tu voto otro dia.");
                            break;
                        default:
                            System.out.println("Campo no válido.");
                            break;
                    }
                    encontrado = true;
                    break;
                }
            }

            // Si el usuario no se encuentra, muestra un mensaje
            if (!encontrado) {
                System.out.println("Votante no encontrado.");
            } else {
                try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fc))) {
                    salida.writeObject(votantes);
                    System.out.println("----------------------");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (java.util.InputMismatchException | ClassNotFoundException ex) {
            System.out.println("Error al acceder. Por favor, inténtelo de nuevo.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void emitirVotos() {
        // Luego, obtenemos la lista de elecciones activas desde el archivo basededatosElecciones.txt
        ArrayList<Elecciones> elecciones = cargarEleccionesDesdeArchivo("basededatosElecciones.txt");

        System.out.println("Ingrese el Cui de la eleecion:");
        int cuiBuscado = scan.nextInt();
        
        // Mostramos las elecciones activas al votante
        System.out.println("Elecciones activas:");
        for (int i = 0; i < elecciones.size(); i++) {
            System.out.println((i + 1) + ". " + elecciones.get(i).getTituloEleccion());
        }

        // El votante selecciona una elección
        int eleccionSeleccionada = obtenerSeleccionDeVotante(elecciones.size()) - 1;

        // Mostramos información de la elección seleccionada
        Elecciones eleccion = elecciones.get(eleccionSeleccionada);
        System.out.println("Información de la elección: " + eleccion.getTituloEleccion());
        System.out.println(eleccion.getDescripcion());

        // Preguntamos al votante si desea emitir su voto
        boolean deseaVotar = obtenerRespuestaSiNo("¿Desea emitir su voto? (Sí/No)");

        if (deseaVotar) {
            // Obtenemos la lista de candidatos para esta elección desde el archivo basededatosCandidatos.txt
            ArrayList<Candidatos> candidatos = cargarCandidatosDesdeArchivo("BasededatosCandidatos.txt");

            // Mostramos las opciones de voto
            System.out.println("Opciones de voto:");
            for (int i = 0; i < candidatos.size(); i++) {
                System.out.println((i + 1) + ". " + candidatos.get(i).getNombreCandidato());
            }

            // El votante selecciona una opción
            int opcionSeleccionada = obtenerSeleccionDeVotante(candidatos.size()) - 1;

            // Preguntamos al votante si está seguro de su elección
            boolean estaSeguro = obtenerRespuestaSiNo("¿Está seguro de su elección? (Sí/No)");

            if (estaSeguro) {
                // Registramos el voto en una base de datos o archivo de votos
                registrarVoto(cuiBuscado, eleccion, candidatos.get(opcionSeleccionada));

                // Mostramos un mensaje de agradecimiento y cerramos la sesión
                System.out.println("¡Gracias por emitir su voto!");
            } else {
                // El votante puede seleccionar una nueva opción
                // Implementa lógica adicional si es necesario
            }
        } else {
            // El votante no desea votar en esta elección
            // Implementa lógica adicional si es necesario
        }
    }
    
        private static ArrayList<Elecciones> cargarEleccionesDesdeArchivo(String archivo) {
             ArrayList<Elecciones> elecciones = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("BasededatosElecciones.txt"))) {
            elecciones = (ArrayList<Elecciones>) entrada.readObject();
            for (Elecciones eleccion : elecciones) {
                System.out.println("Titulo de la Eleccion: " + eleccion.getTituloEleccion());
                System.out.println("Proposito de la Eleccion: " + eleccion.getProposito());
                System.out.println("Descripcion de la Eleccion: " + eleccion.getDescripcion());
                System.out.println("Codigo de Identificacion de la Eleccion: " + eleccion.getCodigo());
                System.out.println("Fecha de Inicio de la Eleccion: " + eleccion.getFechaInicio());
                System.out.println("Hora de Inicio de la Eleccion: " + eleccion.getHoraInicio());
                System.out.println("Fecha de Finalizacion de la Eleccion: " + eleccion.getFechaFinal());
                System.out.println("Hora de Finalizacion de la eleccion:" + eleccion.getHoraFinal());
                System.out.println("-------------------------------------------------");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar elecciones desde el archivo.");
        }
        return elecciones;
    }

    private static ArrayList<Candidatos> cargarCandidatosDesdeArchivo(String archivo) {
        ArrayList<Candidatos> candidatos = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("BasededatosCandidatos.txt"))) {
            candidatos = (ArrayList<Candidatos>) entrada.readObject();
            for (Candidatos candidato : candidatos) {
                System.out.println("Nombre del Candidato:" + candidato.getNombreCandidato());
                System.out.println("Formacion del candidato:" + candidato.getFormacion());
                System.out.println("Experiencia Profecional del candidato:" + candidato.getExperiencia());
                System.out.println("Codigo Unico de Identificacion del candidato:" + candidato.getCuiCandidato());
                System.out.println("-----------------------------------------------------------------------------");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al Cargar candidatos desde el archivo.");
        }
        return candidatos;
    }

    private static int obtenerSeleccionDeVotante(int maxOpciones) {
        Scanner scan = new Scanner(System.in);
        int seleccion = 0;
        boolean seleccionValida = false;

        while (!seleccionValida) {
            try {
                System.out.print("Seleccione una opcion (1-" + maxOpciones + "): ");
                seleccion = scan.nextInt();

                if (seleccion >= 1 && seleccion <= maxOpciones) {
                    seleccionValida = true;
                } else {
                    System.out.println("Opcion no valida. Intente nuevamente.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no valida. Introduzca un numero valido.");
                scan.next(); // Limpia el búfer de entrada
            }
        }

        return seleccion;
    }
    
   
    
    private static boolean obtenerRespuestaSiNo(String pregunta) {
         Scanner scan = new Scanner(System.in);
         String respuesta = "";
        
        while (true) {
            System.out.print(pregunta + " ");
            respuesta = scan.next().trim().toLowerCase();

            if (respuesta.equals("si") || respuesta.equals("si")) {
                return true;
            } else if (respuesta.equals("no")) {
                return false;
            } else {
                System.out.println("Respuesta no valida. Por favor, responda con 'Si' o 'No'.");
            }
        }
    }
        // Implementa la lógica para obtener la respuesta "Sí" o "No" del votante
        // Puedes utilizar un objeto Scanner para obtener la entrada del usuario
    

    private static void registrarVoto(int cuiBuscado, Elecciones elecciones, Candidatos candidatos) {
        // Crea un objeto Voto para representar el voto
        Voto voto = new Voto(cuiBuscado, elecciones, candidatos);

        // Luego, guarda el voto en un archivo de votos
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("votos.txt", true)); // El 'true' indica que se añadirán datos al archivo
            writer.write(voto.toString()); // Suponemos que la clase Voto tiene un método toString para formatear el voto
            writer.newLine();
            writer.close();
            System.out.println("Voto registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar el voto: " + e.getMessage());
        }
    }
      public void conteoVotos(){
           ArrayList<Votantes> votantes = new ArrayList<>();
           ArrayList<Candidatos> candidatos = new ArrayList<>();

        // Leer votantes de un archivo TXT y almacenarlos en el ArrayList
        try {
            BufferedReader br = new BufferedReader(new FileReader("votos.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Votantes votante = new Votantes(data[0], data[1], data[2], data[3], data[4]);
                votantes.add(votante);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Realizar el conteo de votos
        HashMap<String, Integer> conteoGeneral = new HashMap<>();
        HashMap<String, Integer> conteoSexo = new HashMap<>();
        HashMap<String, Integer> conteoUbicacion = new HashMap<>();

        for (Votantes votante : votantes) {

            // Conteo de votos por sexo
            conteoSexo.put(votante.getGenero(), conteoSexo.getOrDefault(votante.getGenero(), 0) + 1);

            // Conteo de votos por ubicación geográfica
            String ubicacion = votante.getPais() + "-" + votante.getDepartamento() + "-" + votante.getMunicipio();
            conteoUbicacion.put(ubicacion, conteoUbicacion.getOrDefault(ubicacion, 0) + 1);
        }
        
        for(Candidatos candidato : candidatos){
            // Conteo general de votos
            conteoGeneral.put(candidato.getNombreCandidato(), conteoGeneral.getOrDefault(candidato.getNombreCandidato(), 0) + 1);
        }

        // Generar reportes
        generarReporteGeneral(conteoGeneral);
        generarReporteSexo(conteoSexo);
        generarReporteUbicacion(conteoUbicacion);
    }

    private static void generarReporteGeneral(HashMap<String, Integer> conteoGeneral) {
        // Generar y guardar el reporte general en un archivo
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("reporte_general.txt"));
            for (String candidato : conteoGeneral.keySet()) {
                int votos = conteoGeneral.get(candidato);
                bw.write(candidato + ": " + votos + " votos");
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generarReporteSexo(HashMap<String, Integer> conteoSexo) {
        // Generar y guardar el reporte de votos por sexo en un archivo
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("reporte_sexo.txt"));
            for (String sexo : conteoSexo.keySet()) {
                int votos = conteoSexo.get(sexo);
                bw.write(sexo + ": " + votos + " votos");
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generarReporteUbicacion(HashMap<String, Integer> conteoUbicacion) {
        // Generar y guardar el reporte de votos por ubicación geográfica en un archivo
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("reporte_ubicacion.txt"));
            for (String ubicacion : conteoUbicacion.keySet()) {
                int votos = conteoUbicacion.get(ubicacion);
                bw.write(ubicacion + ": " + votos + " votos");
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

