package studium.practicat7.practicat7;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import studium.practicat7.practicat7.Logic.LogicLugar;

public class EdicionActivity extends AppCompatActivity implements LocationListener {

    private EditText txtNombre;
    private RatingBar RB2;
    private EditText txtLatitud;
    private EditText txtLongitud;
    private EditText txtComentarios;
    private Spinner lstSeccion;
    public ArrayAdapter adapEstados;
    private ImageView imgGps2;
    private LocationManager locationManager;
    private Location loc;


    final String TAG = "GPS";

    private final static int ALL_PERMISSIONS_RESULT = 101;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    ArrayList permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();

    boolean isGPS = false;
    boolean isNetwork = false;
    boolean canGetLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);
        this.setTitle(R.string.PantallaEdicion);

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


        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);

        imgGps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isGPS && !isNetwork) {
                    Log.i(TAG, "Conexión OFF");
                    showSettingsAlert();
                    getLastLocation();
                } else {
                    Log.i(TAG, "Conexión ON");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (permissionsToRequest.size() > 0) {
                            requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
                            canGetLocation = false;
                        }
                    }
                    getLocation();
                }
            }
        });

        
    }

    public void clicGuardar(View view) {
        String nombre = txtNombre.getText().toString();
        String comentarios = txtComentarios.getText().toString();
        String categoria = lstSeccion.getSelectedItem().toString();
        Float valoracion = RB2.getRating();
        Float latitud = Float.parseFloat(txtLatitud.getText().toString());
        Float longuitud =Float.parseFloat(txtLongitud.getText().toString());


        if (nombre.equals("") || latitud.equals("") || longuitud.equals("") || categoria.equals("Seleccionar Categoría") || categoria.equals("Todas")) {
            mostrarMensaje("Faltan datos obligatorios.");
        } else {
            App.productoActivo.setNombre(nombre);
            App.productoActivo.setComentarios(comentarios);
            App.productoActivo.setValoracion(valoracion);
            App.productoActivo.setCategoria(categoria);
            App.productoActivo.setLatitud(latitud);
            App.productoActivo.setLongitud(longuitud);
            switch (App.accion) {
                case App.INSERTAR:
                    LogicLugar.insertarLugar(this, App.productoActivo);
                    break;
                case App.EDITAR:
                    LogicLugar.editarLugar(this, App.productoActivo);
                    break;
            }
            mostrarMensaje("Lugar " + nombre + " ha sido almacenado.");
            finish();
        }
    }

    private void mostrarMensaje(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onLocationChanged(Location location) {
        updateUI(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
        getLocation();
    }

    @Override
    public void onProviderDisabled(String s) {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    private void getLocation() {
        try {
            if (canGetLocation) {
                if (isGPS) { // recibiendo señal desde GPS_PROVIDER
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, EdicionActivity.this);
                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else if (isNetwork) { // recibiendo señal desde NETWORK_PROVIDER
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, EdicionActivity.this);
                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else {
                    loc.setLatitude(0);
                    loc.setLongitude(0);
                    updateUI(loc);
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void getLastLocation() {
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();
        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }
        return result;
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }
                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("Se requiere permisos para ejecutar la aplicación.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                        }
                                    });
                            return;
                        }
                    }
                } else {
                    canGetLocation = true;
                    getLocation();
                }
                break;
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("El GPS no está activado");
        alertDialog.setMessage("¿Quieres activar el GPS?");
        alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void updateUI(Location loc) {
        txtLatitud.setText(Double.toString(loc.getLatitude()));
        txtLongitud.setText(Double.toString(loc.getLongitude()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }



}
