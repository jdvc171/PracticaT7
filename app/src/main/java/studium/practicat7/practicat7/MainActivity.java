package studium.practicat7.practicat7;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

        lista1 = findViewById(R.id.lista1);

        adapEstados =  ArrayAdapter.createFromResource(this,R.array.Categorias,
                android.R.layout.simple_spinner_item);

        lista1.setAdapter(adapEstados);



    }


}
