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
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textFavouriteDrivers = root.findViewById(R.id.textFavouriteDrivers);
        textFavouriteTracks = root.findViewById(R.id.textFavouriteTracks);
        textVisitedTracks = root.findViewById(R.id.textVisitedTracks);

        Button addEntryButtom = root.findViewById(R.id.addEntry);
        addEntryButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigates to the favorite page
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_navigation_favorites);
            }
        });

        // Loads and displays favorites when the fragment is created
        loadAndDisplayFavorites();


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

            //Clears the existing lists
            favoriteDrivers.clear();
            favoriteTracks.clear();
            visitedTracks.clear();

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
            //Adds item text
            builder.append("- ").append(item).append(" ");

            //Adds delete button
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    //Handles delete button click
                    onDeleteButtonClick(deleteAction, item);
                }
            };
            builder.setSpan(clickableSpan, builder.length() - item.length() - 2, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        //Sets the text with the clickable spans
        textView.setText(builder);

        //Sets the movement method for the TextView to make clickable spans work
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
        //Removes the driver from the list
        favoriteDrivers.remove(driver);

        //Updates the displayed text
        formatListWithDeleteButton(textFavouriteDrivers, favoriteDrivers, "deleteFavoriteDriver");

        //Saves the updated favorites to the file
        saveFavoritesToFile();
    }

    private void saveFavoritesToFile() {
        //Saves the updated data
        JSONObject favoritesObject = new JSONObject();

        try {
            favoritesObject.put("favouriteDrivers", new JSONArray(favoriteDrivers));
            favoritesObject.put("favouriteTracks", new JSONArray(favoriteTracks));
            favoritesObject.put("visitedTracks", new JSONArray(visitedTracks));

            String data = favoritesObject.toString();

            FileUtils.saveToFile(requireContext(), "favourites.json", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void deleteFavoriteTrack(String track) {
        //Removes the track from the list
        favoriteTracks.remove(track);

        //Updates the displayed text
        formatListWithDeleteButton(textFavouriteTracks, favoriteTracks, "deleteFavoriteTrack");

        //Saves the updated favorites to the file
        saveFavoritesToFile();
    }

    private void deleteVisitedTrack(String track) {

        //Removes the visited track from the list
        visitedTracks.remove(track);
        //Updates the displayed text
        formatListWithDeleteButton(textVisitedTracks, visitedTracks, "deleteVisitedTrack");

        //Saves the updated favorites to the file
        saveFavoritesToFile();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

