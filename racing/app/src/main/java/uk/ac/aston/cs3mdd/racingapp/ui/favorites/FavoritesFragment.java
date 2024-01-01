package uk.ac.aston.cs3mdd.racingapp.ui.favorites;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uk.ac.aston.cs3mdd.racingapp.R;
import uk.ac.aston.cs3mdd.racingapp.databinding.FragmentFavoritesBinding;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private EditText editFavouriteDriver;
    private EditText editFavouriteTrack;
    private EditText editVisitedTrack;
    private Button submitButton;
    private Button cancelButton;
    private List<String> favouriteDrivers;
    private List<String> favouriteTracks;
    private List<String> visitedTracks;
    private boolean isFragmentAttached = false;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        isFragmentAttached = true;
        loadFavourites();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isFragmentAttached = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editFavouriteDriver = root.findViewById(R.id.editFavouriteDriver);
        editFavouriteTrack = root.findViewById(R.id.editFavouriteTrack);
        editVisitedTrack = root.findViewById(R.id.editVisitedTrack);
        submitButton = root.findViewById(R.id.submitButton);
        cancelButton = root.findViewById(R.id.cancelButton);

        Button showFavoriteButton = root.findViewById(R.id.showFavesButton);
        showFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FavoritesFragment.this)
                        .navigate(R.id.action_navigation_favorites_to_navigation_home);
            }
        });

        submitButton.setOnClickListener(v -> onSubmitClicked());
        cancelButton.setOnClickListener(v -> onCancelClicked());

        favouriteDrivers = new ArrayList<>();
        favouriteTracks = new ArrayList<>();
        visitedTracks = new ArrayList<>();


        // Setting the visibility of the buttons
        // https://stackoverflow.com/questions/6173400/how-to-hide-a-button-programmatically
        submitButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);

        return root;
    }

    public void saveFavourites(List<String> favouriteDrivers, List<String> favouriteTracks, List<String> visitedTracks) {
        //Loads existing data
        loadFavourites();

        //Appends new data
        this.favouriteDrivers.addAll(favouriteDrivers);
        this.favouriteTracks.addAll(favouriteTracks);
        this.visitedTracks.addAll(visitedTracks);

        //Saves the updates data
        JSONObject favouritesObject = new JSONObject();

        try {
            favouritesObject.put("favouriteDrivers", new JSONArray(this.favouriteDrivers));
            favouritesObject.put("favouriteTracks", new JSONArray(this.favouriteTracks));
            favouritesObject.put("visitedTracks", new JSONArray(this.visitedTracks));

            String data = favouritesObject.toString();

            FileUtils.saveToFile(requireContext(), "favourites.json", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void loadFavourites() {
        if (isFragmentAttached) {
            String data = FileUtils.readFromFile(requireContext(), "favourites.json");

            try {
                JSONObject favouritesObject = new JSONObject(data);

                JSONArray favoriteTracksArray = favouritesObject.getJSONArray("favouriteTracks");
                JSONArray favoriteDriversArray = favouritesObject.getJSONArray("favouriteDrivers");
                JSONArray visitedTracksArray = favouritesObject.getJSONArray("visitedTracks");

                if (favouriteTracks == null) {
                    favouriteTracks = new ArrayList<>();
                }

                if (favouriteDrivers == null) {
                    favouriteDrivers = new ArrayList<>();
                }

                if (visitedTracks == null) {
                    visitedTracks = new ArrayList<>();
                }

                favouriteTracks.clear();
                favouriteDrivers.clear();
                visitedTracks.clear();

                for (int i = 0; i < favoriteTracksArray.length(); i++) {
                    favouriteTracks.add(favoriteTracksArray.getString(i));
                }

                for (int i = 0; i < favoriteDriversArray.length(); i++) {
                    favouriteDrivers.add(favoriteDriversArray.getString(i));
                }

                for (int i = 0; i < visitedTracksArray.length(); i++) {
                    visitedTracks.add(visitedTracksArray.getString(i));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    private void addFavouriteTrack(String trackName) {
        favouriteTracks.add(trackName);

        saveFavourites(favouriteDrivers, favouriteTracks, visitedTracks);
    }

    private void onCancelClicked() {
        clearInput();
    }

    private void clearInput() {
        editFavouriteDriver.getText().clear();
        editFavouriteTrack.getText().clear();
        editVisitedTrack.getText().clear();
    }

    private void onUpdateClicked() {
        updateFavourite();
    }

    private void updateFavourite() {
        List<String> favouriteDrivers = new ArrayList<>();
        favouriteDrivers.add(editFavouriteDriver.getText().toString());

        List<String> favouriteTracks = new ArrayList<>();
        favouriteTracks.add(editFavouriteTrack.getText().toString());

        List<String> visitedTracks = new ArrayList<>();
        visitedTracks.add(editVisitedTrack.getText().toString());

        saveFavourites(favouriteDrivers, favouriteTracks, visitedTracks);
    }

    private void onSubmitClicked() {
        saveFavourite();
    }

    private void saveFavourite() {
        List<String> favouriteDrivers = new ArrayList<>();
        favouriteDrivers.add(editFavouriteDriver.getText().toString());

        List<String> favouriteTracks = new ArrayList<>();
        favouriteTracks.add(editFavouriteTrack.getText().toString());

        List<String> visitedTracks = new ArrayList<>();
        visitedTracks.add(editVisitedTrack.getText().toString());

        favouriteDrivers.add(editFavouriteDriver.getText().toString());
        favouriteTracks.add(editFavouriteTrack.getText().toString());
        visitedTracks.add(editVisitedTrack.getText().toString());

        saveFavourites(favouriteDrivers, favouriteTracks, visitedTracks);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadFavourites();
    }
}

