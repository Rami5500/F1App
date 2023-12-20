package uk.ac.aston.cs3mdd.racingapp.ui.races;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Circuit;
import model.Location;
import uk.ac.aston.cs3mdd.racingapp.R;
import uk.ac.aston.cs3mdd.racingapp.ui.drivers.DashboardFragmentDirections;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.CircuitViewHolder> {

    private RecyclerView mRecyclerView;
    private List<Circuit> mCircuitList;
    private final LayoutInflater mInflater;

    public NotificationsAdapter(Context context,
                                List<Circuit> circuitsList) {
        mInflater = LayoutInflater.from(context);
        this.mCircuitList = circuitsList;
    }

    @NonNull
    @Override
    public NotificationsAdapter.CircuitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.circuit_item,
                parent, false);
        return new CircuitViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.CircuitViewHolder holder, int position) {
        Circuit circuit = mCircuitList.get(position);
        holder.circuit = circuit;

        String circuitName = circuit.getCircuitName();
        holder.circuitName.setText(circuitName);

        Location location = circuit.getLocation();
        Log.d("CircuitAdapter", "Location: " + location);
        if (location != null) {
            String locality = location.getLocality() != null ? location.getLocality() : "N/A";
            String country = location.getCountry() != null ? location.getCountry() : "N/A";

            //Log.d("CircuitAdapter", "Locality: " + locality + ", Country: " + country);
            //Log.i("Map", "Latitude: " + lat + ", Longitude: " + lng);

            holder.locality.setText(locality);
            holder.country.setText(country);
        }
    }

    @Override
    public int getItemCount() {
        return this.mCircuitList.size();
    }

    public void updateData(List<Circuit> list) {
        this.mCircuitList = list;
        notifyDataSetChanged();
    }

    class CircuitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView circuitName;
        public final TextView locality;
        public final TextView country;


        final NotificationsAdapter mAdapter;
        public Circuit circuit;

        public CircuitViewHolder(@NonNull View itemView, NotificationsAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);
            circuitName = itemView.findViewById(R.id.circuitName);
            locality = itemView.findViewById(R.id.locality);
            country = itemView.findViewById(R.id.country);
            this.mAdapter = adapter;
        }

        @Override
        public void onClick(View v) {
            //Log.i("AJB", "You clicked " + circuit.toString());
            //action_navigation_notifications_to_circuitFragment
            NotificationsFragmentDirections.ActionNavigationNotificationsToCircuitFragment action =
                    NotificationsFragmentDirections.actionNavigationNotificationsToCircuitFragment(circuit);
            Navigation.findNavController(v)
                    .navigate((NavDirections) action);
        }
    }
}