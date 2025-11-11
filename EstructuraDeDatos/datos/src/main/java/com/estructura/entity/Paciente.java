import java.sql.Date;

public class Paciente extends Persona {

    private String numeroHistoriaClinica;
    private int nivelTriage;
    private String diagnostico;
    private int camaAsignada;

    public Paciente(String nombre, String apellido, String identificacion, Date fechaNacimiento,
                    String numeroHistoriaClinica, int nivelTriage, String diagnostico, int camaAsignada) {
        super(nombre, apellido, identificacion, fechaNacimiento, diagnostico);
        this.numeroHistoriaClinica = numeroHistoriaClinica;
        this.nivelTriage = nivelTriage;
        this.diagnostico = diagnostico;
        this.camaAsignada = camaAsignada;
    }

    // Getters y Setters

    public String getNumeroHistoriaClinica() {
        return numeroHistoriaClinica;
    }

    public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
    }

    public int getNivelTriage() {
        return nivelTriage;
    }

    public void setNivelTriage(int nivelTriage) {
        this.nivelTriage = nivelTriage;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getCamaAsignada() {
        return camaAsignada;
    }

    public void setCamaAsignada(int camaAsignada) {
        this.camaAsignada = camaAsignada;
    }

    

}
