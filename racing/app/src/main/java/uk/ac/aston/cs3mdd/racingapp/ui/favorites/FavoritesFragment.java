package uk.ac.aston.cs3mdd.racingapp.ui.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uk.ac.aston.cs3mdd.racingapp.R;
import uk.ac.aston.cs3mdd.racingapp.databinding.FragmentDashboardBinding;
import uk.ac.aston.cs3mdd.racingapp.databinding.FragmentFavoritesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFavorites;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        textView.setText(R.string.hello_blank_fragment);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}