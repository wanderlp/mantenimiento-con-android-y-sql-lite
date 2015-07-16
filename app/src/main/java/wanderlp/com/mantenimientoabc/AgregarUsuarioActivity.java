package wanderlp.com.mantenimientoabc;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usuarioPackage.usuario;
import usuarioPackage.usuarioSQLiteHelper;

public class AgregarUsuarioActivity extends ActionBarActivity {
    TextView txtNombres;
    TextView txtApellidos;
    TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        txtNombres = (TextView)findViewById(R.id.txtNombres);
        txtApellidos = (TextView)findViewById(R.id.txtApellidos);
        txtEmail = (TextView)findViewById(R.id.txtEmail);
    }

    public void agregarUsuario(View button) {
        if (validarCampos()) {
            usuarioSQLiteHelper db = new usuarioSQLiteHelper(this);
            db.agregarUsuario(new usuario(
                    txtNombres.getText().toString(),
                    txtApellidos.getText().toString(),
                    txtEmail.getText().toString()));
            Toast.makeText(getApplicationContext(), "Usuario agregado", Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    public boolean validarCampos() {
        int errores = 0;
        if (txtNombres.getText().toString().equals(""))
        {
            txtNombres.setError(Html.fromHtml("El campo requiere informaci&oacute;n"));
            if (errores == 0) txtNombres.requestFocus();
            errores++;
        }

        if (txtApellidos.getText().toString().equals("")) {
            txtApellidos.setError(Html.fromHtml("El campo requiere informaci&oacute;n"));
            if (errores == 0) txtApellidos.requestFocus();
            errores++;
        }

        if (txtEmail.getText().toString().equals("")) {
            txtEmail.setError(Html.fromHtml("El campo requiere informaci&oacute;n"));
            if (errores == 0) txtEmail.requestFocus();
            errores++;
        }

        Pattern pat = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$");
        Matcher mat = pat.matcher(txtEmail.getText());
        if (!txtEmail.getText().toString().equals("") && !mat.matches()) {
            txtEmail.setError(Html.fromHtml("El correo electr&oacute;nico ingresado no es v&aacute;lido."));
            if (errores == 0) txtEmail.requestFocus();
            errores++;
        }

        if (errores > 0)
            return false;
        else
            return true;
    }
}
