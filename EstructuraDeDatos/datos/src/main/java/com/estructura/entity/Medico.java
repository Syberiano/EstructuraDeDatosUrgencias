import java.sql.Date;

public class Medico extends Persona {

    private boolean disponible;
    private String especialidad;

    public Medico(String nombre, String apellido, String identificacion, Date fechaNacimiento,
                  boolean disponible, String especialidad) {
        super(nombre, apellido, identificacion, fechaNacimiento, especialidad);
        this.disponible = disponible;
        this.especialidad = especialidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    

}
