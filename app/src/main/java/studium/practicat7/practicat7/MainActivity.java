package studium.practicat7.practicat7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.List;
import studium.practicat7.practicat7.Model.Lugar;
import studium.practicat7.practicat7.Logic.LogicLugar;


public class MainActivity extends AppCompatActivity {
    Spinner lista1;
    ArrayAdapter adapEstados;
    public List<Lugar> lstProd;
    String Seleccion;
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("MIS LUGARES FAVORITOS");

        lista1 = findViewById(R.id.lista1);
        listView.addHeaderView(new View(this)); // añade espacio arriba de la primera card
        listView.addFooterView(new View(this)); // añade espacio debajo de la última card

        adapEstados =  ArrayAdapter.createFromResource(this,R.array.Categorias,
                android.R.layout.simple_spinner_item);

        lista1.setAdapter(adapEstados);

        lista1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (lista1.getSelectedItem().toString()){
                    case "Comida":
                        Seleccion = "Comida";
                        filtro1("Comida");

                        break;

                    case "Ocio":
                        Seleccion = "Ocio";
                        filtro1("OCio");

                        break;

                    case "Diversión":
                        Seleccion = "Diversion";
                        filtro1("Diversión");

                        break;

                    case "Deporte":
                        Seleccion = "Deporte";
                        filtro1("Deporte");

                        break;

                    case "Cultura":
                        Seleccion = "Cultura";
                        filtro1("Cultura");

                        break;

                    case "Todas":
                        Seleccion = "Todas";
                        onResume();

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



    }

    protected void onResume() {
        super.onResume();
        CardAdapter listadoDeCards1 = new CardAdapter(getApplicationContext(), R.layout.list_item_card);

        lstProd =  LogicLugar.listaLugar(this);
        if (lstProd == null) {
            Toast.makeText(this, "La base de datos está vacía.", Toast.LENGTH_LONG).show();
        } else {
            for (Lugar p : lstProd) {
                listadoDeCards1.add(p);

            }
            listView.setAdapter(listadoDeCards1);
        }

        lista1.setSelection(0);
    }


    public void filtro1(String categoria) {
        super.onResume();
        CardAdapter listadoDeCards = new CardAdapter(getApplicationContext(), R.layout.list_item_card);

        lstProd =  LogicLugar.listaLugarCat(this,categoria);
        if (lstProd == null) {
            Toast.makeText(this, "La base de datos está vacía.", Toast.LENGTH_LONG).show();
            listadoDeCards.clear();
            listView.setAdapter(listadoDeCards);

        } else {
            for (Lugar p : lstProd) {
                listadoDeCards.add(p);
            }
            listView.setAdapter(listadoDeCards);

        }

    }


}
