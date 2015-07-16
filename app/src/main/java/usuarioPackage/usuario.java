package usuarioPackage;

public class usuario {
    private int id;
    private String nombres;
    private String apellidos;
    private String email;

    public usuario() {
        super();
    }

    public usuario(int id) {
        super();
        this.id = id;
    }

    public usuario(int id, String nombres, String apellidos, String email) {
        super();
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
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
