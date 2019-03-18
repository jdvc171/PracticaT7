package studium.practicat7.practicat7;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;
import studium.practicat7.practicat7.Logic.LogicLugar;
import studium.practicat7.practicat7.Model.Lugar;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    public GoogleMap mMap;
    public List<Lugar> listado = new ArrayList<>();
    String categoria;
    LatLng lugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        categoria = intent.getStringExtra("categoria");
        this.setTitle("LUGARES: "+categoria);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if(categoria.equals("Todas")){
            listado =  LogicLugar.listaLugar(this);
        }
        else{
            listado =  LogicLugar.listaLugarCat(this,categoria);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        Colores(categoria);

        LatLng Sevilla = new LatLng(37.382830, -5.973170);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Sevilla));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);

    }

    public void Colores (String categoria){
        if(categoria.equals("Comida")||categoria.equals("Food")|| categoria.equals("Repas")){
            for (Lugar p : listado) {
                lugar = new LatLng((double)p.getLatitud(),(double)p.getLongitud());
                mMap.addMarker(new MarkerOptions().position(lugar).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                mMap.setOnMarkerClickListener(this);
            }
        }

        if(categoria.equals("Ocio") ||categoria.equals("Leisure") ||categoria.equals("Loisir")){
            for (Lugar p : listado) {
                lugar = new LatLng((double)p.getLatitud(),(double)p.getLongitud());
                mMap.addMarker(new MarkerOptions().position(lugar).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                mMap.setOnMarkerClickListener(this);
            }
        }

        if(categoria.equals("Diversión") ||categoria.equals("Fun") ||categoria.equals("Amusant")){
            for (Lugar p : listado) {
                lugar = new LatLng((double)p.getLatitud(),(double)p.getLongitud());
                mMap.addMarker(new MarkerOptions().position(lugar).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                mMap.setOnMarkerClickListener(this);
            }
        }

        if(categoria.equals("Deporte") ||categoria.equals("Sports") ||categoria.equals("Sport")){
            for (Lugar p : listado) {
                lugar = new LatLng((double)p.getLatitud(),(double)p.getLongitud());
                mMap.addMarker(new MarkerOptions().position(lugar).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMap.setOnMarkerClickListener(this);
            }
        }

        if(categoria.equals("Cultura") ||categoria.equals("Culture") ||categoria.equals("Culture")){
            for (Lugar p : listado) {
                lugar = new LatLng((double)p.getLatitud(),(double)p.getLongitud());
                mMap.addMarker(new MarkerOptions().position(lugar).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.setOnMarkerClickListener(this);
            }
        }

        if(categoria.equals("Todas") ||categoria.equals("All") ||categoria.equals("Tout")){
            for (Lugar p : listado) {
                lugar = new LatLng((double)p.getLatitud(),(double)p.getLongitud());
                mMap.addMarker(new MarkerOptions().position(lugar).title(p.getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                mMap.setOnMarkerClickListener(this);
            }
        }

    }

    public boolean onMarkerClick(final Marker marker) {
        int i=0;
        for (Lugar p : listado) {
            if(p.getNombre().equals(marker.getTitle())){
                App.productoActivo = listado.get(i);
                App.accion = App.INFORMACION;
                startActivity(new Intent(MapsActivity.this, InformacionActivity.class));
            }else{i++;}
        }

        return false;
    }
}