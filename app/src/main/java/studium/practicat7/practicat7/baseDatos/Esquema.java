package studium.practicat7.practicat7.baseDatos;

import android.provider.BaseColumns;

public class Esquema {

    public static abstract  class  Lugar implements BaseColumns{
        public static  final String TABLE_NAME = "LUGAR";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NOMBRE = "NombreLugar";
        public static final String COLUMN_NAME_COMENTARIOS = "Comentarios";
        public static final String COLUMN_NAME_VALORACION = "Valoracion";
        public static final String COLUMN_NAME_CATEGORIA = "Categoria";
        public static final String COLUMN_NAME_LATITUD = "Latitud";
        public static final String COLUMN_NAME_LONGITUD = "Longitud";

        public static final String COLUMN_TYPE_ID = "INTEGER";
        public static final String COLUMN_TYPE_NOMBRE = "TEXT";
        public static final String COLUMN_TYPE_COMENTARIOS = "TEXT";
        public static final String COLUMN_TYPE_VALORACION = "FLOAT";
        public static final String COLUMN_TYPE_CATEGORIA = "TEXT";
        public static final String COLUMN_TYPE_LATITUD = "FLOAT";
        public static final String COLUMN_TYPE_LONGITUD = "FLOAT";
    }
}
