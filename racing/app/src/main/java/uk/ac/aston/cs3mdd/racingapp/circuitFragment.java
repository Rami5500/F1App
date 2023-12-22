package uk.ac.aston.cs3mdd.racingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import model.Circuit;
import model.Location;
import uk.ac.aston.cs3mdd.racingapp.databinding.FragmentCircuitBinding;

public class circuitFragment extends Fragment implements OnMapReadyCallback {
    private Location location;

    private FragmentCircuitBinding binding;
   // private TextView tv;
    private GoogleMap mMap;
    public Circuit circuit;

    public circuitFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        location = circuitFragmentArgs.fromBundle(getArguments()).getCircuit().getLocation();
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_circuit, container, false);
       // tv=v.findViewById(R.id.textviewlocation);
        return v;
        //inflater.inflate(R.layout.fragment_circuit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*if (circuit != null ) {
            ImageView circuitImage = view.findViewById(R.id.imageView);
            String circuitName = circuit.getCircuitName();
            int circuitID = getCircuitId(circuitName);
            circuitImage.setImageResource(circuitID);
        } else {
            Log.e("circuitFragment", "Circuit object is null");
        }*/

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at the user's location and move the camera

        //Location l = location.getLocation().getCoordinates();
        LatLng loc = new LatLng(location.getLat(), location.getLon());
        //LatLng loc = new LatLng(52.0693, -1.0214);
        Log.d("MapFragment", "Original Lat: " + location.getLat() + ", Original Long: " + location.getLon());
        mMap.addMarker(new MarkerOptions()
                .position(loc)
                .title("Location of this circuit"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    /*private int getCircuitId(String circuitName) {
        switch (circuitName) {
            case "Albert Park Grand Prix Circuit":
                return R.drawable.albert_park_melbourne;
            case "Circuit of the Americas":
                return R.drawable.americas;
            case "Bahrain":
                return R.drawable.bahrain_flag;
            case "Azerbaijan":
                return R.drawable.azerbaijan;
            case "Spain":
                return R.drawable.spain;
            case "Hungary":
                return R.drawable.hungary;
            case "Brazil":
                return R.drawable.brazil;
            case "Saudi Arabia":
                return R.drawable.saudi;
            case "Qatar":
                return R.drawable.qatar_flag;
            case "Singapore":
                return R.drawable.singapore;
            case "Monaco":
                return R.drawable.monaco_flag;
            case "Italy":
                return R.drawable.italy;
            case "Austria":
                return R.drawable.austria;
            case "Mexico":
                return R.drawable.mexico;
            case "UK":
                return R.drawable.uk;
            case "Belgium":
                return R.drawable.belgium;
            case "Japan":
                return R.drawable.japan;
            case "United States":
                return R.drawable.usa;
            case "Canada":
                return R.drawable.canada;
            case "UAE":
                return R.drawable.uae;
            case "Netherlands":
                return R.drawable.netherlands;
            default:
                return R.drawable.australia;
        }
    }*/
}