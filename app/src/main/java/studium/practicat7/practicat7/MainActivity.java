package studium.practicat7.practicat7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.List;
import studium.practicat7.practicat7.Model.Lugar;
import studium.practicat7.practicat7.Logic.LogicLugar;


public class MainActivity extends AppCompatActivity {
    public Spinner lista1;
    public ArrayAdapter adapEstados;
    public List<Lugar> lstProd;
    String Seleccion;
    public ImageView imgGps;
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.PantallaPrincipal);

        lista1 = findViewById(R.id.lista1);
        listView = findViewById(R.id.card_listView);
        listView.addHeaderView(new View(this)); // añade espacio arriba de la primera card
        listView.addFooterView(new View(this)); // añade espacio debajo de la última card
        imgGps = findViewById(R.id.ImgGps);
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


                    case "Food":
                        Seleccion = "Food";
                        filtro1("Food");

                        break;

                    case "Leisure":
                        Seleccion = "Leisure";
                        filtro1("Leisure");

                        break;

                    case "Fun":
                        Seleccion = "Fun";
                        filtro1("Fun");

                        break;

                    case "Sports":
                        Seleccion = "Sports";
                        filtro1("Sports");

                        break;

                    case "Culture":
                        Seleccion = "Culture";
                        filtro1("Culture");

                        break;

                    case "All":
                        Seleccion = "All";
                        onResume();

                        break;



                    case "Repas":
                        Seleccion = "Repas";
                        filtro1("Repas");

                        break;

                    case "Loisir":
                        Seleccion = "Loisir";
                        filtro1("Loisir");

                        break;

                    case "Amusant":
                        Seleccion = "Amusant";
                        filtro1("Amusant");

                        break;

                    case "Sport":
                        Seleccion = "Sport";
                        filtro1("Sport");

                        break;

                    case "Tout":
                        Seleccion = "Tout";
                        onResume();

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                App.productoActivo = lstProd.get(position -1);
                App.accion = App.INFORMACION;
                startActivity(new Intent(MainActivity.this, InformacionActivity.class));
            }
        });

        imgGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!lista1.getSelectedItem().equals("Seleccionar Categoría") && lstProd!=null){
                    Intent i = new Intent(MainActivity.this,MapsActivity.class);
                    i.putExtra("categoria", Seleccion);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Esta categoría está vacía", Toast.LENGTH_LONG).show();
                }
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

    public void clicNuevo(View view) {
        App.productoActivo = new Lugar();
        App.accion = App.INSERTAR;
        startActivity(new Intent(this, EdicionActivity.class));
    }


}
