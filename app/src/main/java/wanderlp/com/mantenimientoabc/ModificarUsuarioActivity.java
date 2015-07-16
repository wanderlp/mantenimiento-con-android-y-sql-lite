package wanderlp.com.mantenimientoabc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class ModificarUsuarioActivity extends ActionBarActivity {
    private int idUsuario = -1;
    private TextView txtNombres;
    private TextView txtApellidos;
    private TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario);

        txtNombres = (TextView)findViewById(R.id.txtNombres);
        txtApellidos = (TextView)findViewById(R.id.txtApellidos);
        txtEmail = (TextView)findViewById(R.id.txtEmail);

        Intent intent = getIntent();
        idUsuario = intent.getIntExtra("wanderlp.com.mantenimientoabc.IdUsuario", -1);

        if (idUsuario != -1) {
            usuarioSQLiteHelper db = new usuarioSQLiteHelper(this);
            usuario usr = db.obtenerUsuario(idUsuario);
            txtNombres.setText(usr.getNombres());
            txtApellidos.setText(usr.getApellidos());
            txtEmail.setText(usr.getEmail());
        }
    }

    public void modificarUsuario(View button) {
        if (validarCampos()) {
            usuarioSQLiteHelper db = new usuarioSQLiteHelper(this);
            db.actualizarUsuario(new usuario(
                    idUsuario,
                    txtNombres.getText().toString(),
                    txtApellidos.getText().toString(),
                    txtEmail.getText().toString()));
            Toast.makeText(getApplicationContext(), "Usuario modificado", Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    public void eliminarUsuario(View button) {
        AlertDialog dialog = confirmar();
        dialog.show();
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

    private AlertDialog confirmar() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage(Html.fromHtml("&iquest;Est&aacute; seguro que desea eliminar?"))
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        usuarioSQLiteHelper db = new usuarioSQLiteHelper(getApplicationContext());
                        db.eliminarUsuario(new usuario(idUsuario));
                        Toast.makeText(getApplicationContext(), "Usuario elimiando", Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        return dialog;
    }
}
