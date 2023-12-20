package uk.ac.aston.cs3mdd.racingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import model.Location;
import uk.ac.aston.cs3mdd.racingapp.databinding.FragmentCircuitBinding;

public class circuitFragment extends Fragment implements OnMapReadyCallback {
    private Location location;

    private FragmentCircuitBinding binding;
   // private TextView tv;
    private GoogleMap mMap;

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
        //tv.setText(location.toString());

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
}