import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CRUDProcess {

    private File file = new File("database.txt");
    private static Scanner scam = new Scanner(System.in);

    public void create(ArrayList<Persona> personas) {
        try {
            System.out.println("Ingresa el nombre de la persona: ");
            String nombre = scam.nextLine();
            System.out.println("Ingresa el apellido de la persona: ");
            String apellido = scam.nextLine();
            System.out.println("Ingresa el telefono de la persona: ");
            long telefono = scam.nextLong();
            System.out.println("Ingresa la edad de la persona: ");
            int edad = scam.nextInt();

            personas.add(new Persona(nombre, apellido, telefono, edad));
            System.out.println("Persona creada exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error al crear la persona. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al crear la persona. " + e.getMessage());
        } finally {
            scam.nextLine();
        }


    }

    public void read(ArrayList<Persona> personas) {
        try {
            for (Persona persona : personas) {
                System.out.println("ID: " + persona.getPersonaID());
                System.out.println("Nombre: " + persona.getNombre());
                System.out.println("Apellido: " + persona.getApellido());
                System.out.println("Telefono: " + persona.getTelefono());
                System.out.println("Edad: " + persona.getEdad());
                System.out.println("====================================");
            }
        } catch (Exception e) {
            System.out.println("Error al leer las personas. " + e.getMessage());
        }

    }

    public void update(ArrayList<Persona> personas) {
        System.out.println("Ingresa el ID de la persona a actualizar: ");
        int id = scam.nextInt();
        scam.nextLine();

        boolean encontrado = false;

        try {
            int cont = 0;
            for (Persona persona : personas) {

                if (persona.getPersonaID() == id) {
                    System.out.println("Ingresa el nombre de la persona: ");
                    String nombre = scam.nextLine();
                    System.out.println("Ingresa el apellido de la persona: ");
                    String apellido = scam.nextLine();
                    System.out.println("Ingresa el telefono de la persona: ");
                    long telefono = scam.nextLong();
                    System.out.println("Ingresa la edad de la persona: ");
                    int edad = scam.nextInt();

                    persona.setNombre(nombre);
                    persona.setApellido(apellido);
                    persona.setTelefono(telefono);
                    persona.setEdad(edad);

                    encontrado = true;
                    System.out.println("Persona actualizada exitosamente.");
                } else {
                    if (personas.size() < id) {
                        System.out.println("No se encontro la persona con el ID: " + id);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la persona. " + e.getMessage());
        } finally {
            if (encontrado){
                scam.nextLine();
            }
        }
    }

    public void delete(ArrayList<Persona> personas) {
        System.out.println("Ingresa el ID de la persona a eliminar: ");
        int id = scam.nextInt();

        try {
            for (Persona persona : personas) {
                if (persona.getPersonaID() == id) {
                    personas.remove(persona);
                    System.out.println("Persona eliminada exitosamente.");
                    break;
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Error al eliminar la persona. " + e.getMessage());
        }
    }

    public void readDatabase(ArrayList<Persona> personas) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while (br.read() != -1) {
                String personaRegistrada = br.readLine();

                //System.out.println(personaRegistrada);

                String[] dataParsed = personaRegistrada.split(",");

                String[] nombres = null;
                long numeroTel = 0;
                int edad = 0;

                for (int i = 0; i < dataParsed.length; i++) {
                    //System.out.println(String.valueOf(dataParsed[i]));
                    nombres = dataParsed[i].contains(" ") && nombres == null ? dataParsed[i].split(" ") : nombres;
                    if (dataParsed[i].length() <= 3) {
                        edad = Integer.parseInt(dataParsed[i]);
                    } else if (dataParsed[i].length() == 10) {
                        //System.out.println(Long.parseLong(dataParsed[i]));
                        numeroTel = Long.parseLong(dataParsed[i]);
                    }
                }
                personas.add(new Persona(nombres[0], nombres[1], numeroTel, edad));
            }


        } catch (FileNotFoundException e) {
            System.out.println("Error al leer la base de datos. " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error al leer la base de datos. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer la base de datos. " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al leer la base de datos. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error general en la base de datos. " + e.getMessage());
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                System.out.println("OCURRIO UN ERROR INESPERADO: " + e.getMessage());
            }
        }
    }

    public void save(ArrayList<Persona> personas) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(file);
            for (Persona persona : personas) {
                fw.write(" " + persona.getPersonaID() + "," + persona.getNombre() + " " + persona.getApellido() + "," + persona.getEdad() + "," + persona.getTelefono() + "\n");
            }


        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error al guardar. " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Error al guardar las personas. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al guardar las personas. " + e.getMessage());
        } finally {
            try {
                fw.close();
                System.out.println("Personas guardadas exitosamente.");
            } catch (IOException e) {
                System.out.println("Ah ocurrido un error inesperado: " + e.getMessage());
            }
        }


    }

}


