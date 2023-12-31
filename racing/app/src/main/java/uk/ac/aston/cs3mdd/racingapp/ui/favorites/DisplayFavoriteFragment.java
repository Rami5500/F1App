package uk.ac.aston.cs3mdd.racingapp.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import uk.ac.aston.cs3mdd.racingapp.R;


public class DisplayFavoriteFragment extends Fragment {

    private TextView textFavoriteDrivers;
    private TextView textFavoriteTracks;
    private TextView textVisitedTracks;

    private List<String> favouriteDrivers = new ArrayList<>();
    private List<String> favouriteTracks = new ArrayList<>();
    private List<String> visitedTracks = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_favorite, container, false);
        textFavoriteDrivers = view.findViewById(R.id.textFavouriteDrivers);
        textFavoriteTracks = view.findViewById(R.id.textFavouriteTracks);
        textVisitedTracks = view.findViewById(R.id.textVisitedTracks);

        loadAndDisplayFavorites();

        return view;
    }

    private void loadAndDisplayFavorites() {
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        favoritesFragment.loadFavourites();

        // Display favorites in TextViews
        textFavoriteDrivers.setText(formatListAsString(favouriteDrivers));
        textFavoriteTracks.setText(formatListAsString(favouriteTracks));
        textVisitedTracks.setText(formatListAsString(visitedTracks));
    }

    private String formatListAsString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (String item : list) {
            builder.append("- ").append(item).append("\n");
        }
        return builder.toString();
    }
}
