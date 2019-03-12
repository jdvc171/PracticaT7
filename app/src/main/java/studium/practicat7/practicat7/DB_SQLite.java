package studium.practicat7.practicat7;

import studium.practicat7.practicat7.Esquema.Informacion;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_SQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="informacion.sqlite";
    private static final int DATABASE_VERSION = 1;

    //Sentencia SQL para crear la tabla de LUGAR
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+ Informacion.TABLE_NAME +" ("+
            Informacion.COLUMN_NAME_ID+" "+Informacion.COLUMN_TYPE_ID +" PRIMARY KEY AUTOINCREMENT, "+
            Informacion.COLUMN_NAME_NOMBRE+" "+Informacion.COLUMN_TYPE_NOMBRE +","+
            Informacion.COLUMN_NAME_COMENTARIOS+" "+Informacion.COLUMN_TYPE_COMENTARIOS +","+
            Informacion.COLUMN_NAME_VALORACION+" "+Informacion.COLUMN_TYPE_VALORACION +","+
            Informacion.COLUMN_NAME_CATEGORIA+" "+Informacion.COLUMN_TYPE_CATEGORIA +","+
            Informacion.COLUMN_NAME_LATITUD+" "+Informacion.COLUMN_TYPE_LATITUD +","+
            Informacion.COLUMN_NAME_LONGITUD+" "+Informacion.COLUMN_TYPE_LONGITUD +")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Informacion.TABLE_NAME;

    public DB_SQLite(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

        //Se elimina la versión anterior de la tabla
        db.execSQL(SQL_DELETE_ENTRIES);

        //Se crea la nueva versión de la tabla
       onCreate(db);
    }
}
