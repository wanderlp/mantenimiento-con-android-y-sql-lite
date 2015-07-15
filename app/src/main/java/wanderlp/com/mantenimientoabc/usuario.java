package wanderlp.com.mantenimientoabc;

/**
 * Created by wlopez on 14/07/2015.
 */
public class usuario {
    private int id;
    private String nombres;
    private String apellidos;
    private String email;

    public usuario() {

    }

    public usuario(String nombres, String apellidos, String email) {
        super();
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos +
                ", email=" + email + "]";
    }

    // Id
    public void setId(int value) {
        id = value;
    }
    public int getId() {
        return id;
    }

    // Nombres
    public void setNombres(String value) {
        nombres = value;
    }
    public String getNombres() {
        return nombres;
    }

    // Apellidos
    public void setApellidos(String value) {
        apellidos = value;
    }
    public String getApellidos() {
        return apellidos;
    }

    // Email
    public void setEmail(String value) {
        email = value;
    }
    public String getEmail() {
        return email;
    }
}
