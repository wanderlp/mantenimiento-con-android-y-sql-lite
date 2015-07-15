package wanderlp.com.mantenimientoabc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wlopez on 15/07/2015.
 */
public class usuarioSQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsuariosBD";

    public usuarioSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, apellidos TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        this.onCreate(db);
    }

    public void agregarUsuario(usuario usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombres", usr.getNombres());
        values.put("apellidos", usr.getApellidos());
        values.put("email", usr.getEmail());

        db.insert("Usuarios", null, values);
        db.close();
    }

    public usuario obtenerUsuario(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columnas = {"id", "nombres", "apellidos", "email"};
        String[] restricciones = { String.valueOf(id) };
        Cursor cursor = db.query("Usuarios",
                columnas,
                "id = ?",
                restricciones,
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        usuario usr = new usuario();
        usr.setId(Integer.parseInt(cursor.getString(0)));
        usr.setNombres(cursor.getString(1));
        usr.setApellidos(cursor.getString(2));
        usr.setEmail(cursor.getString(3));

        return usr;
    }

    public List<usuario> obtenerUsuariosTodos() {
        List<usuario> usrs = new LinkedList<usuario>();

        String query = "SELECT id, nombres, apellidos, email FROM Usuarios";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                usuario usr = new usuario();
                usr.setId(Integer.parseInt(cursor.getString(0)));
                usr.setNombres(cursor.getString(1));
                usr.setApellidos(cursor.getString(2));
                usr.setEmail(cursor.getString(3));
                usrs.add(usr);
            } while (cursor.moveToNext());
        }

        return usrs;
    }

    public int actualizarUsuario(usuario usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombres", usr.getNombres());
        values.put("apellidos", usr.getApellidos());
        values.put("email", usr.getEmail());
        String[] restricciones = { String.valueOf(usr.getId()) };

        int i = db.update("Usuarios", values, "id = ?", restricciones);
        db.close();

        return i;
    }

    public void eliminarUsuario(usuario usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] restricciones = { String.valueOf(usr.getId()) };

        db.delete("Usuarios", "id = ?", restricciones);
        db.close();
    }
}
