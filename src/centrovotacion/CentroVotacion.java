/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package centrovotacion;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    File fc = new File ("Base de datos Votante.txt");
    
    File f = new File ("Base de datos Usuarios.txt");
    Scanner scan = new Scanner (System.in);
    int opcion = 0;
    public static void main(String[] args) {
        // TODO code application logic here
    Scanner scan = new Scanner (System.in);
    int opcion = 0;
     System.out.print("Ingrese contrasenia del administrador:");
        String contrasenia = scan.next();
        
        if(contrasenia.compareTo("#GuatemalaVotos")==0){
        System.out.print("Bienvenido al sistema:\n");
        System.out.print("\nMenu de Inicio\n");
        System.out.println("1. Registro de Usuarios");
        System.out.println("2. Registro Votantes");
        System.out.print("Seleccione operacion a realizar:");
        opcion = scan.nextInt();
        }
        else{
           System.err.println("Contraseña Incorrecta");  
        }
        
        switch (opcion){
            case 1:
                centrovotacion.CentroVotacion registrarUsuario = new centrovotacion.CentroVotacion();
                registrarUsuario.registroUsuarios();
                break;
            case 2:
                centrovotacion.CentroVotacion registraVotante = new centrovotacion.CentroVotacion();
                registraVotante.registroVotantes();
        }
    }
    
    public void registroUsuarios(){
        System.out.println("Sistema de Votaciones\n");
        System.out.println("Registro de Usuarios\n");
        System.out.println("1.Crear Usuario del sistema");
        System.out.println("2.Modificar Usuario del sistema");
        System.out.println("3.Reiniciar Contrasenia");
        
        System.out.println("Ingrese una opcion:");
        opcion = scan.nextInt();
        
        switch (opcion){
            case 1:
            centrovotacion.CentroVotacion crearUsuario= new centrovotacion.CentroVotacion();
            crearUsuario.crearUsuario();
            break;
            case 2 :
            centrovotacion.CentroVotacion modificarUsuario = new centrovotacion.CentroVotacion();
            modificarUsuario.modificarUsuario();
            break;
            case 3:
            centrovotacion.CentroVotacion reiniciar = new centrovotacion.CentroVotacion();
            reiniciar.reinicio();
            break;
        }
}
    
    public void crearUsuario(){
       try{
                    FileWriter fw = new FileWriter(f,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    System.out.println("Ingrese su nombre");
                    String nombre = scan.next();
                    System.out.println("Ingrese su apellido");
                    String apellido = scan.next();
                    System.out.println("Ingrese su edad");
                    String edad = scan.next();
                    System.out.println("Ingrese su genero");
                    String sexo = scan.next();
                    System.out.println("Ingrese su correo");
                    String email = scan.next();
                    System.out.println("Ingrese su contraseña");
                    String psw = scan.next();
                    System.out.println("Ingrese su rol");
                    String rol = scan.next();
                    bw.write("|"+ nombre + "|" + apellido + "|" + edad + "|" + sexo + "|" + email + "|" + psw + "|" + rol + "|");
                    bw.newLine();
                    bw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(CentroVotacion.class.getName()).log(Level.SEVERE, null, ex);
                    }       
    }  
    
    public void modificarUsuario(){
        CentroVotacion centroVotacion = new CentroVotacion();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre de usuario a editar: ");
        String usuario = scanner.nextLine();

        System.out.println("Opciones de edición:");
        System.out.println("1. Editar nombre");
        System.out.println("2. Editar apellido");
        System.out.println("3. Editar edad");
        System.out.println("4. Editar género");
        System.out.println("5. Editar correo electrónico");
        System.out.println("6. Editar contraseña");
        System.out.println("7. Editar rol");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        scanner.nextLine(); // Consumir la nueva línea después de nextInt

        System.out.print("Nuevo valor: ");
        String nuevoValor = scanner.nextLine();

        centroVotacion.modificarUsuarios("Base de datos Usuarios.txt", usuario, opcion, nuevoValor);

        System.out.println("Usuario editado con éxito.");
    }
    
    public void modificarUsuarios(String archivo, String usuario, int opcion, String nuevoValor) {
        try {
            File f = new File("Base de datos Usuarios.txt");
            File tempFile = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(f));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("|");
                String username = parts[0];

                if (username.equals(usuario)) {
                    // Realiza la modificación según la opción seleccionada
                    switch (opcion) {
                        case 1:
                            parts[1] = nuevoValor; // Editar nombre
                            break;
                        case 2:
                            parts[2] = nuevoValor; // Editar apellido
                            break;
                        case 3:
                            parts[3] = nuevoValor; // Editar edad
                            break;
                        case 4:
                            parts[4] = nuevoValor; // Editar género
                            break;
                        case 5:
                            parts[5] = nuevoValor; // Editar correo electrónico
                            break;
                        case 6:
                            parts[6] = nuevoValor; // Editar contraseña
                            break;
                        case 7:
                            parts[7] = nuevoValor; // Editar rol
                            break;
                    }

                    String updatedLine = String.join("|", parts);
                    bw.write(updatedLine + "\n");
                } else {
                    bw.write(line + "\n");
                }
            }

            br.close();
            bw.close();

            // Renombrar el archivo temporal al archivo original
            if (f.delete()) {
                tempFile.renameTo(f);
            } else {
                System.out.println("No se pudo renombrar el archivo.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void reinicioContrasenia(String email, String nuevaContrasenia){
        try {
            File f = new File("Base de datos Usuarios.txt");
            File archivoTemporal = new File("Base de datos Usuarios Temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(f));
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal));

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("|");
                String correo = partes[0].trim();
                String contrasenia = partes[1].trim();

                if (correo.equals(email)) {
                    contrasenia = nuevaContrasenia;
                }

                bw.write(correo + "|" + contrasenia);
                bw.newLine();
            }

            br.close();
            bw.close();

            // Elimina el archivo original y renombra el temporal
            if (f.delete()) {
                if (archivoTemporal.renameTo(f)) {
                    System.out.println("Contraseña modificada exitosamente.");
                } else {
                    System.out.println("Error al renombrar el archivo temporal.");
                }
            } else {
                System.out.println("Error al eliminar el archivo original.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void reinicio(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el correo electrónico del usuario: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese la nueva contraseña: ");
        String nuevaContrasenia = scanner.nextLine();

        CentroVotacion centro = new CentroVotacion();
        centro.reinicioContrasenia(email, nuevaContrasenia);
    }
    
    
    
    
    public void registroVotantes(){
        System.out.println("Sistema de Votaciones\n");
        System.out.println("Registro de Votantes\n");
        System.out.println("1.Agregar Votantes");
        System.out.println("2.Modificar Votante");
        System.out.println("3.Reiniciar Contrasenia del Votante");
        System.out.println("4.Registrar Fallecimiento del votante");
        
        System.out.println("Ingrese una opcion:");
        opcion = scan.nextInt();
        
        switch (opcion){
            case 1:
           centrovotacion.CentroVotacion regisVotante = new centrovotacion.CentroVotacion();
           regisVotante.registrarVotantes();
        }
    }
    
    public void registrarVotantes(){
        try{
        FileWriter fw = new FileWriter(fc,true);
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.print("Nombre completo: ");
        String nombreCompleto = scan.nextLine();
        System.out.print("Apellidos completos: ");
        String apellidosCompleto = scan.nextLine();
        System.out.print("CUI: ");
        String dni = scan.nextLine();
        System.out.print("Sexo: ");
        String sexo2 = scan.nextLine();
        System.out.print("Fecha de nacimiento: ");
        String fechaNacimiento = scan.nextLine();
        System.out.print("Dirección de residencia: ");
        String direccion = scan.nextLine();
        System.out.print("Departamento de residencia: ");
        String departamento = scan.nextLine();
        System.out.print("Municipio de residencia: ");
        String municipio = scan.nextLine();
        System.out.print("País de residencia: ");
        String pais = scan.nextLine();
        bw.write("|" + nombreCompleto + "|" + apellidosCompleto + "|" + dni + "|" + sexo2 + "|" + fechaNacimiento + "|" + direccion + "|" + departamento + "|" + municipio + "|" + pais + "|");
        bw.newLine();
        bw.close();
        }
        catch (IOException ex) {
                        Logger.getLogger(CentroVotacion.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
    }
    
