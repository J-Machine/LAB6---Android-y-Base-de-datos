package BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static BaseDatos.DatosOpenHelperContract.SQL_CREATE_ENTRIES;
import static BaseDatos.DatosOpenHelperContract.SQL_DELETE_ENTRIES;

public class DatosOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = DatosOpenHelperContract.ClientEntry.TABLE_NAME;

    public DatosOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        StringBuilder sql = new StringBuilder();
//        sql.append("CREATE TABLE IF NOT EXISTS CLIENT(");
//        sql.append("NOMBRE VARCHAR(250), ");
//        sql.append("DIRECCION VARCHAR (250), ");
//        sql.append("EMAIL VARCHAR(200), ");
//        sql.append("TELEFONO VARCHAR(20))");

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
