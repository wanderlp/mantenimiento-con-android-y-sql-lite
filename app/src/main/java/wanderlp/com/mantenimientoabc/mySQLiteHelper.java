package wanderlp.com.mantenimientoabc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by wlopez on 14/07/2015.
 */
public class mySQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsuariosBD";

    public mySQLiteHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Usuarios (codigo INTEGER, nombres TEXT, apellidos TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("CREATE TABLE Usuarios (codigo INTEGER, nombres TEXT, apellidos TEXT, email TEXT)");
    }
}
