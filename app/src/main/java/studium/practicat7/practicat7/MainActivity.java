package studium.practicat7.practicat7;

import studium.practicat7.practicat7.Esquema.Informacion;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
    Spinner lista1;
    ArrayAdapter adapEstados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista1 = findViewById(R.id.listaCat);

        adapEstados =  ArrayAdapter.createFromResource(this,R.array.Categorias,
                android.R.layout.simple_spinner_dropdown_item);

        lista1.setAdapter(adapEstados);

        ContentValues content = new ContentValues();
        content.put(Informacion.COLUMN_NAME_NOMBRE,"McDonals");
        content.put(Informacion.COLUMN_NAME_COMENTARIOS,"Muy buen restaurante");
        content.put(Informacion.COLUMN_NAME_VALORACION,3);
        content.put(Informacion.COLUMN_NAME_CATEGORIA,"Comida");
        content.put(Informacion.COLUMN_NAME_LATITUD,37.39156145945036);
        content.put(Informacion.COLUMN_NAME_LONGITUD,-5.9769003057061525);

        DB_SQLite db = new DB_SQLite(this);
        SQLiteDatabase conn = db.getWritableDatabase();
        conn.insert(Informacion.TABLE_NAME,null,content);
        conn.close();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
