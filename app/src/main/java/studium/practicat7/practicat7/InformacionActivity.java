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
        this.setTitle("INFORMACIÓN");

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

   

}
