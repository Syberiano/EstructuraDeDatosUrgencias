public class Turno {
    private String idTurno;
    private String tipo;
    private String semana;

    
    public Turno(String idTurno, String tipo, String semana) {
        this.idTurno = idTurno;
        this.tipo = tipo;
        this.semana = semana;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

}