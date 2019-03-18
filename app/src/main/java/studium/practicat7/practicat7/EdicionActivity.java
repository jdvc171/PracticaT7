package studium.practicat7.practicat7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class EdicionActivity extends AppCompatActivity {

    private EditText txtNombre;
    private RatingBar RB2;
    private EditText txtLatitud;
    private EditText txtLongitud;
    private EditText txtComentarios;
    private Spinner lstSeccion;
    public ArrayAdapter adapEstados;
    private ImageView imgGps2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);
        this.setTitle("EDICIÓN");

        txtNombre = findViewById(R.id.txtNombre);
        txtComentarios = findViewById(R.id.txtComentarios);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        RB2 = findViewById(R.id.ratingBar);
        lstSeccion = findViewById(R.id.lstSeccion);
        imgGps2 = findViewById(R.id.imgGps2);

        List secciones = Arrays.asList(getResources().getStringArray(R.array.Categorias));

        adapEstados =  ArrayAdapter.createFromResource(this,R.array.Categorias,
                android.R.layout.simple_spinner_item);

        lstSeccion.setAdapter(adapEstados);

        txtNombre.setText(App.productoActivo.getNombre());
        txtComentarios.setText(App.productoActivo.getComentarios());
        lstSeccion.setSelection(secciones.indexOf(App.productoActivo.getCategoria()));
        RB2.setRating(App.productoActivo.getValoracion());
        txtLatitud.setText(App.productoActivo.getLatitud().toString());
        txtLongitud.setText(App.productoActivo.getLongitud().toString());

    }
}
