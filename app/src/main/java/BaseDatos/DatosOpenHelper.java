package BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatosOpenHelper extends SQLiteOpenHelper {

    public DatosOpenHelper(Context context) {
        super(context, "DATOS", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS CLIENT(");
        sql.append("NOMBRE VARCHAR(250), ");
        sql.append("DIRECCION VARCHAR (250), ");
        sql.append("EMAIL VARCHAR(200), ");
        sql.append("TELEFONO VARCHAR(20))");

        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
