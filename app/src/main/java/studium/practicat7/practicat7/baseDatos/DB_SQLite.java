package studium.practicat7.practicat7.baseDatos;


import studium.practicat7.practicat7.baseDatos.Esquema.Lugar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_SQLite extends SQLiteOpenHelper {

    public static final int OPEN_MODE_READ = 1;
    public static final int OPEN_MODE_WRITE = 2;

    private static final String DATABASE_NAME = "informacionLugares.sqlite";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Lugar.TABLE_NAME + " (" +
                    Lugar.COLUMN_NAME_ID + " " + Lugar.COLUMN_TYPE_ID + " PRIMARY KEY AUTOINCREMENT, " +
                    Lugar.COLUMN_NAME_NOMBRE + " " + Lugar.COLUMN_TYPE_NOMBRE + "," +
                    Lugar.COLUMN_NAME_COMENTARIOS + " " + Lugar.COLUMN_TYPE_COMENTARIOS +  "," +
                    Lugar.COLUMN_NAME_VALORACION + " " + Lugar.COLUMN_TYPE_VALORACION + "," +
                    Lugar.COLUMN_NAME_CATEGORIA + " " + Lugar.COLUMN_TYPE_CATEGORIA +  "," +
                    Lugar.COLUMN_NAME_LATITUD + " " + Lugar.COLUMN_TYPE_LATITUD +  "," +
                    Lugar.COLUMN_NAME_LONGITUD + " " + Lugar.COLUMN_TYPE_LONGITUD +  ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Lugar.TABLE_NAME;

    public DB_SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public static SQLiteDatabase conectar(Context context, int open_mode) {
        DB_SQLite db = new DB_SQLite(context);
        SQLiteDatabase conn;
        switch (open_mode) {
            case OPEN_MODE_READ:  conn = db.getReadableDatabase(); break;
            case OPEN_MODE_WRITE: conn = db.getWritableDatabase(); break;
            default:              conn = db.getReadableDatabase(); break;
        }
        return conn;
    }

    public static void desconectar(SQLiteDatabase conn) {
        conn.close();
    }

}