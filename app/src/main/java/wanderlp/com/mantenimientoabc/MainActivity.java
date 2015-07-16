package wanderlp.com.mantenimientoabc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import usuarioPackage.usuario;
import usuarioPackage.usuarioArrayAdapter;
import usuarioPackage.usuarioSQLiteHelper;

public class MainActivity extends ActionBarActivity {
    private ListView list;
    private TextView lblListaVacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.listView);
        lblListaVacia = (TextView)findViewById(R.id.lblListaVacia);

        cargarLista();
    }

    private void cargarLista() {
        usuarioSQLiteHelper db = new usuarioSQLiteHelper(this);
        List<usuario> usrs = db.obtenerUsuariosTodos();

        final ArrayAdapter adaptador;
        adaptador = new usuarioArrayAdapter(this, usrs);
        list.setAdapter(adaptador);
        list.setEmptyView(lblListaVacia);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usuario usr = (usuario) adaptador.getItem(position);
                Intent intent = new Intent(getApplicationContext(), ModificarUsuarioActivity.class);
                intent.putExtra("wanderlp.com.mantenimientoabc.IdUsuario", usr.getId());
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_agregar_usuario) {
            Intent intent = new Intent(this, AgregarUsuarioActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        if (id == R.id.menu_informacion) {
            Intent intent = new Intent(this, InformacionActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cargarLista();
    }
}