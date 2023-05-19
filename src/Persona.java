public class Persona {
    private int personaID;
    private String nombre;
    private String apellido;
    private long telefono;
    private int edad;

    private static int presonasCreadas = 0;

    public Persona() {
    }

    public Persona(String nombre, String apellido, long telefono, int edad) {
        this.personaID = presonasCreadas + 1;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.edad = edad;
        presonasCreadas++;
    }

    public int getPersonaID() {
        return personaID;
    }

    public void setPersonaID(int personaID) {
        this.personaID = personaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
