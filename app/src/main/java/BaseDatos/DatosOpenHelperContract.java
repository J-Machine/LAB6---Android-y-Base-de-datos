package BaseDatos;

import android.provider.BaseColumns;

public final class DatosOpenHelperContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatosOpenHelperContract() {
    }

    /* Inner class that defines the table contents */
    public static class ClientEntry implements BaseColumns {
        public static final String TABLE_NAME = "CLIENTS";
        public static final String COL_NOMBRE = "NOMBRE";
        public static final String COL_DIRECCION = "DIRECCION";
        public static final String COL_EMAIL = "EMAIL";
        public static final String COL_TELEFONO = "TELEFONO";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ClientEntry.TABLE_NAME + " (" +
                    ClientEntry._ID + " INTEGER PRIMARY KEY," +
                    ClientEntry.COL_NOMBRE + " VARCHAR(250), " +
                    ClientEntry.COL_DIRECCION + " VARCHAR(250), " +
                    ClientEntry.COL_EMAIL + " VARCHAR(200), " +
                    ClientEntry.COL_TELEFONO + " VARCHAR(20)" + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ClientEntry.TABLE_NAME;
}
