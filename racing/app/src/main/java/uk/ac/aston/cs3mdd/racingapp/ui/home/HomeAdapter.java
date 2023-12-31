package uk.ac.aston.cs3mdd.racingapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.aston.cs3mdd.racingapp.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<String> drivers;

    public void setDrivers(List<String> drivers) {
        this.drivers = drivers;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        String driverName = drivers.get(position);

        holder.textFavouriteDrivers.setText(driverName);
    }

    @Override
    public int getItemCount() {
        return drivers == null ? 0 : drivers.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        public TextView textFavouriteDrivers;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            textFavouriteDrivers = itemView.findViewById(R.id.textFavouriteDrivers);
        }
    }
}
