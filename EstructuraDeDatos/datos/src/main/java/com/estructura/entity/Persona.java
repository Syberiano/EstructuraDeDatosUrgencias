import java.sql.Date;

public class Persona {

    private String nombre;
    private String apellido;
    private String id;
    private Date fechaNacimiento;

    public Persona(String nombre, String apellido, String identificacion, Date fechaNacimiento, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
