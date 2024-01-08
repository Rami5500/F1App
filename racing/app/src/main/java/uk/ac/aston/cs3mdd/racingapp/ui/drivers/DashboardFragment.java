package uk.ac.aston.cs3mdd.racingapp.ui.drivers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import model.DriverTable;
import model.DriverViewModel;
import model.Driver;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.DriverRepository;
import service.F1API;
import uk.ac.aston.cs3mdd.racingapp.R;
import uk.ac.aston.cs3mdd.racingapp.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DriverViewModel viewModel;
    private RecyclerView mRecyclerView;
    private DashboardAdapter mAdapter;
    private SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Observer<List<Driver>> driverListObserver = new Observer<List<Driver>>() {

            @Override
            public void onChanged(@Nullable final List<Driver> userList) {
                /* Update the UI, in this case, a Toast.
                Toast.makeText(getContext(),
                        "We got a list of " + userList.size() + " users",
                        Toast.LENGTH_LONG).show();
                mAdapter.updateData(userList);

                 */
            }
        };

        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        viewModel = new ViewModelProvider(requireActivity()).get(DriverViewModel.class);
        viewModel.getAllDrivers().observe(getViewLifecycleOwner(), driverListObserver);

        //Used the following link to figure out how to use Search view
        //https://developer.android.com/reference/android/widget/SearchView
        searchView = view.findViewById(R.id.searchViewDrivers);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterDriverNames(newText);
                return true;
            }
        });

        //Used the following links and lab code to figure out how to use a Recycler view
        //https://developer.android.com/develop/ui/views/layout/recyclerview#java

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new DashboardAdapter(getContext(), viewModel.getAllDrivers().getValue());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //API Base URL
        //Code from development labs
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ergast.com")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        F1API service = retrofit.create(F1API.class);

        viewModel.requestDrivers(new DriverRepository(service));


    }

    private void filterDriverNames(String text) {
        List<Driver> filteredList = new ArrayList<>();

        for (Driver driver: mAdapter.mDriverList) {
            if (driver.getGivenName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(driver);
            }
        }

        //Used the lab code to figure out how to use Toast messages to see what is retrieved from the API
        if (filteredList.isEmpty()){
            Toast.makeText(getContext(), "Nothing found", Toast.LENGTH_SHORT).show();
        } else{
            mAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}