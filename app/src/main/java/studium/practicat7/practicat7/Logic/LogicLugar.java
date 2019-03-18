package studium.practicat7.practicat7.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import studium.practicat7.practicat7.baseDatos.DB_SQLite;
import studium.practicat7.practicat7.baseDatos.Esquema;
import studium.practicat7.practicat7.Model.Lugar;
import java.util.ArrayList;
import java.util.List;

public class LogicLugar {

    public static void insertarLugar(Context context, Lugar p) {
        ContentValues content = new ContentValues();
        content.put(Esquema.Lugar.COLUMN_NAME_NOMBRE, p.getNombre());
        content.put(Esquema.Lugar.COLUMN_NAME_COMENTARIOS, p.getComentarios());
        content.put(Esquema.Lugar.COLUMN_NAME_VALORACION, p.getValoracion());
        content.put(Esquema.Lugar.COLUMN_NAME_CATEGORIA, p.getCategoria());
        content.put(Esquema.Lugar.COLUMN_NAME_LATITUD, p.getLatitud());
        content.put(Esquema.Lugar.COLUMN_NAME_LONGITUD, p.getLongitud());
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        conn.insert(Esquema.Lugar.TABLE_NAME, null, content);
        DB_SQLite.desconectar(conn);
    }

    public static void eliminarLugar(Context context, Lugar p) {
        String sqlWhere = Esquema.Lugar.COLUMN_NAME_ID + " = " + p.getId();
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        conn.delete(Esquema.Lugar.TABLE_NAME, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static void editarLugar(Context context, Lugar p) {
        ContentValues content = new ContentValues();
        content.put(Esquema.Lugar.COLUMN_NAME_NOMBRE, p.getNombre());
        content.put(Esquema.Lugar.COLUMN_NAME_COMENTARIOS, p.getComentarios());
        content.put(Esquema.Lugar.COLUMN_NAME_VALORACION, p.getValoracion());
        content.put(Esquema.Lugar.COLUMN_NAME_CATEGORIA, p.getCategoria());
        content.put(Esquema.Lugar.COLUMN_NAME_LATITUD, p.getLatitud());
        content.put(Esquema.Lugar.COLUMN_NAME_LONGITUD, p.getLongitud());
        String sqlWhere = Esquema.Lugar.COLUMN_NAME_ID + " = " + p.getId();
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        conn.update(Esquema.Lugar.TABLE_NAME, content, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static List listaLugar(Context context) {
        List prod = new ArrayList<>();
        String[] sqlFields = {Esquema.Lugar.COLUMN_NAME_ID, Esquema.Lugar.COLUMN_NAME_NOMBRE, Esquema.Lugar.COLUMN_NAME_COMENTARIOS, Esquema.Lugar.COLUMN_NAME_VALORACION,Esquema.Lugar.COLUMN_NAME_CATEGORIA,Esquema.Lugar.COLUMN_NAME_LATITUD,Esquema.Lugar.COLUMN_NAME_LONGITUD };
        String sqlWhere = "";
        String sqlOrderBy = Esquema.Lugar.COLUMN_NAME_NOMBRE + " ASC";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Lugar.TABLE_NAME, sqlFields, sqlWhere, null, null, null, sqlOrderBy);
        if (cursor.getCount() == 0) {
            prod = null;
        } else {
            cursor.moveToFirst();
            do {
                Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_ID));
                String dataNombre = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_NOMBRE));
                String dataComentarios = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_COMENTARIOS));
                Float dataValoracion = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_VALORACION));
                String dataCategoria = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_CATEGORIA));
                Float dataLatitud = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LATITUD));
                Float dataLonguitud = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LONGITUD));

                prod.add(new Lugar(dataId, dataNombre, dataComentarios, dataValoracion,dataCategoria,dataLatitud,dataLonguitud));
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB_SQLite.desconectar(conn);
        return prod;
    }

    public static List listaLugarCat(Context context,String categoria) {
        List prod = new ArrayList<>();
        String[] sqlFields = {Esquema.Lugar.COLUMN_NAME_ID, Esquema.Lugar.COLUMN_NAME_NOMBRE, Esquema.Lugar.COLUMN_NAME_COMENTARIOS, Esquema.Lugar.COLUMN_NAME_VALORACION,Esquema.Lugar.COLUMN_NAME_CATEGORIA,Esquema.Lugar.COLUMN_NAME_LATITUD,Esquema.Lugar.COLUMN_NAME_LONGITUD };

        String sqlOrderBy = Esquema.Lugar.COLUMN_NAME_NOMBRE + " ASC";

        String selection = Esquema.Lugar.COLUMN_NAME_CATEGORIA + " LIKE ?"; // WHERE id LIKE ?
        String selectionArgs[] = new String[]{categoria};

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Lugar.TABLE_NAME, sqlFields, selection, selectionArgs, null, null, sqlOrderBy);
        if (cursor.getCount() == 0) {
            prod = null;
        } else {
            cursor.moveToFirst();
            do {
                Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_ID));
                String dataNombre = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_NOMBRE));
                String dataComentarios = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_COMENTARIOS));
                Float dataValoracion = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_VALORACION));
                String dataCategoria = cursor.getString(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_CATEGORIA));
                Float dataLatitud = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LATITUD));
                Float dataLonguitud = cursor.getFloat(cursor.getColumnIndex(Esquema.Lugar.COLUMN_NAME_LONGITUD));

                prod.add(new Lugar(dataId, dataNombre, dataComentarios, dataValoracion,dataCategoria,dataLatitud,dataLonguitud));
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB_SQLite.desconectar(conn);
        return prod;
    }

}
