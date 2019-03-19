package studium.practicat7.practicat7;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

import studium.practicat7.practicat7.Logic.LogicLugar;

public class InformacionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        this.setTitle(R.string.PantallaInformacion);

        TextView infoNombre = findViewById(R.id.txtNombreInfo);
        Spinner CatInfo = findViewById(R.id.lstSeccionInfo);
        TextView LatitudInfo = findViewById(R.id.txtLatitudInfo);
        TextView LongitudInfo = findViewById(R.id.txtLongitudInfo);
        RatingBar valoracion = findViewById(R.id.ratingBarInfo);
        TextView InfoComent = findViewById(R.id.txtInfoComentarios);
        ArrayAdapter adapEstados;
        List secciones = Arrays.asList(getResources().getStringArray(R.array.Categorias));

        adapEstados =  ArrayAdapter.createFromResource(this,R.array.Categorias,
                android.R.layout.simple_spinner_item);

        CatInfo.setAdapter(adapEstados);

        infoNombre.setText(App.productoActivo.getNombre());
        CatInfo.setSelection(secciones.indexOf(App.productoActivo.getCategoria()));
        valoracion.setRating(App.productoActivo.getValoracion());
        LatitudInfo.setText(App.productoActivo.getLatitud().toString());
        LongitudInfo.setText(App.productoActivo.getLongitud().toString());
        InfoComent.setText(App.productoActivo.getComentarios());

        CatInfo.setEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcEditar: clicEditar(); break;
            case R.id.opcBorrar: clicBorrar(); break;
        }
        return true;
    }

    public void clicEditar() {
        App.accion = App.EDITAR;
        startActivity(new Intent(this, EdicionActivity.class));
        finish();
    }

    public void clicBorrar() {
        new AlertDialog.Builder(this)
                .setMessage("¿ Quieres borrar el Lugar " + App.productoActivo.getNombre() + " ?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LogicLugar.eliminarLugar(getApplicationContext(), App.productoActivo);
                        finish();
                        Intent i = new Intent(InformacionActivity.this   , MainActivity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }

}
