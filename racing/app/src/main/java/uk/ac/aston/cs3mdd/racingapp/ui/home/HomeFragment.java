package uk.ac.aston.cs3mdd.racingapp.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.aston.cs3mdd.racingapp.R;
import uk.ac.aston.cs3mdd.racingapp.databinding.FragmentHomeBinding;
import uk.ac.aston.cs3mdd.racingapp.ui.favorites.FavoritesFragment;
import uk.ac.aston.cs3mdd.racingapp.ui.favorites.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView textFavouriteDrivers;
    private TextView textFavouriteTracks;
    private TextView textVisitedTracks;

    FavoritesFragment favoritesFragment = new FavoritesFragment();
    private List<String> favoriteDrivers = new ArrayList<>();
    private List<String> favoriteTracks = new ArrayList<>();
    private List<String> visitedTracks = new ArrayList<>();

    private RecyclerView homeRecyclerView;
    private HomeAdapter homeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textFavouriteDrivers = root.findViewById(R.id.textFavouriteDrivers);
        textFavouriteTracks = root.findViewById(R.id.textFavouriteTracks);
        textVisitedTracks = root.findViewById(R.id.textVisitedTracks);

        FavoritesFragment favoritesFragment = new FavoritesFragment();

        // Load and display favorites when the fragment is created
        loadAndDisplayFavorites();

        homeRecyclerView = root.findViewById(R.id.recyclerview);
        homeAdapter = new HomeAdapter(); // Pass your data to the adapter when initializing

        homeRecyclerView.setAdapter(homeAdapter);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Observe the LiveData in the ViewModel
        homeViewModel.getDrivers().observe(getViewLifecycleOwner(), drivers -> {
            // Update the adapter when the list of drivers changes
            homeAdapter.setDrivers(drivers);
            homeAdapter.notifyDataSetChanged();
        });




        return root;
    }

    private void loadAndDisplayFavorites() {
        String data = FileUtils.readFromFile(requireContext(), "favourites.json");

        formatListWithDeleteButton(textFavouriteDrivers, favoriteDrivers, "deleteFavoriteDriver");
        formatListWithDeleteButton(textFavouriteTracks, favoriteTracks, "deleteFavoriteTrack");
        formatListWithDeleteButton(textVisitedTracks, visitedTracks, "deleteVisitedTrack");

        try {
            JSONObject favoritesObject = new JSONObject(data);

            JSONArray favoriteTracksArray = favoritesObject.getJSONArray("favouriteTracks");
            JSONArray favoriteDriversArray = favoritesObject.getJSONArray("favouriteDrivers");
            JSONArray visitedTracksArray = favoritesObject.getJSONArray("visitedTracks");

            // Clear the existing lists
            favoriteDrivers.clear();
            favoriteTracks.clear();
            visitedTracks.clear();

            // Populate the class-level lists
            for (int i = 0; i < favoriteDriversArray.length(); i++) {
                String driver = favoriteDriversArray.getString(i);
                if (!favoriteDrivers.contains(driver)) {
                    favoriteDrivers.add(driver);
                }
            }

            for (int i = 0; i < favoriteTracksArray.length(); i++) {
                String track = favoriteTracksArray.getString(i);
                if (!favoriteTracks.contains(track)) {
                    favoriteTracks.add(track);
                }
            }

            for (int i = 0; i < visitedTracksArray.length(); i++) {
                String visitedTrack = visitedTracksArray.getString(i);
                if (!visitedTracks.contains(visitedTrack)) {
                    visitedTracks.add(visitedTrack);
                }
            }

            formatListWithDeleteButton(textFavouriteDrivers, favoriteDrivers, "deleteFavoriteDriver");
            formatListWithDeleteButton(textFavouriteTracks, favoriteTracks, "deleteFavoriteTrack");
            formatListWithDeleteButton(textVisitedTracks, visitedTracks, "deleteVisitedTrack");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void formatListWithDeleteButton(TextView textView, List<String> list, String deleteAction) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (String item : list) {
            // Add item text
            builder.append("- ").append(item).append(" ");

            // Add delete button
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    // Handle delete button click
                    onDeleteButtonClick(deleteAction, item);
                }
            };
            builder.setSpan(clickableSpan, builder.length() - item.length() - 2, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // Set the text with the clickable spans
        textView.setText(builder);

        // Set the movement method for the TextView to make clickable spans work
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }


    private void onDeleteButtonClick(String deleteAction, String item) {
        switch (deleteAction) {
            case "deleteFavoriteDriver":
                deleteFavoriteDriver(item);
                break;
            case "deleteFavoriteTrack":
                deleteFavoriteTrack(item);
                break;
            case "deleteVisitedTrack":
                deleteVisitedTrack(item);
                break;
        }
    }


    private void deleteFavoriteDriver(String driver) {
        Log.d("Delete", "Before deletion - favoriteDrivers: " + favoriteDrivers);

        favoriteDrivers.remove(driver);

        Log.d("Delete", "After deletion - favoriteDrivers: " + favoriteDrivers);

        favoritesFragment.saveFavourites(favoriteDrivers, favoriteTracks, visitedTracks);
    }

    /*public void deleteFavoriteDriver(String driverName) {
        // Ensure favouriteDrivers is initialized
        if (favoriteDrivers == null) {
            favoriteDrivers = new ArrayList<>();
        }

        // Remove the driver from the list
        favoriteDrivers.remove(driverName);

        // Update the UI or perform any other necessary actions
        updateUI();

        // Save the updated list to preferences or file
        FavoritesFragment.saveFavourites(requireContext(), favoriteTracks, favoriteDrivers, visitedTracks);
    }*/

    private void deleteFavoriteTrack(String track) {
        favoriteTracks.remove(track);
        favoritesFragment.saveFavourites(favoriteDrivers, favoriteTracks, visitedTracks);
    }

    private void deleteVisitedTrack(String track) {
        visitedTracks.remove(track);
        favoritesFragment.saveFavourites(favoriteDrivers, favoriteTracks, visitedTracks);
    }


    private String formatListAsString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (String item : list) {
            builder.append("- ").append(item).append("\n");
        }
        return builder.toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
