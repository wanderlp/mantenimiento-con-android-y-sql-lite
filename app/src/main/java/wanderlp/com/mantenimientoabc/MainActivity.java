package wanderlp.com.mantenimientoabc;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private ListView list;
    private String[] sistemas = {"prueba1", "prueba2", "prueba3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mySQLiteHelper db = new mySQLiteHelper(this);
        db.agregarUsuario(new usuario("Wanderson", "Lopez", "lopez.wanderson@gmail.com"));*/

        usuarioSQLiteHelper db = new usuarioSQLiteHelper(this);
        List<usuario> usrs = db.obtenerUsuariosTodos();

        list = (ListView)findViewById(R.id.listView);
        ArrayAdapter adaptador;
        adaptador = new usuarioArrayAdapter(this, usrs);
        list.setAdapter(adaptador);
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
            return true;
        }

        if (id == R.id.menu_informacion) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}