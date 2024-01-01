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
    private GoogleMap mMap;
    public Circuit circuit;

    public circuitFragment() {
        //Empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        location = circuitFragmentArgs.fromBundle(getArguments()).getCircuit().getLocation();

        View v=inflater.inflate(R.layout.fragment_circuit, container, false);
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng loc = new LatLng(location.getLat(), location.getLon());
        Log.d("MapFragment", "Original Lat: " + location.getLat() + ", Original Long: " + location.getLon());
        mMap.addMarker(new MarkerOptions()
                .position(loc)
                .title("Location of this circuit"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

}