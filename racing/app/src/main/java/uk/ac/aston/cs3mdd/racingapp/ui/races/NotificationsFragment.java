package uk.ac.aston.cs3mdd.racingapp.ui.races;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Circuit;
import model.CircuitViewModel;
import model.Driver;
import model.DriverViewModel;
import model.Location;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.CircuitInfo;
import service.CircuitRepository;
import service.DriverRepository;
import service.F1API;
import uk.ac.aston.cs3mdd.racingapp.R;
import uk.ac.aston.cs3mdd.racingapp.databinding.FragmentNotificationsBinding;
import uk.ac.aston.cs3mdd.racingapp.ui.drivers.DashboardAdapter;
import uk.ac.aston.cs3mdd.racingapp.ui.drivers.DashboardViewModel;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private CircuitViewModel viewModel;
    private Location location;
    private RecyclerView mRecyclerView;
    private NotificationsAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewModel = new ViewModelProvider(requireActivity()).get(CircuitViewModel.class);

        //circuit = NotificationsFragment.fromBundle(getArguments()).getCircuit();

        //final TextView textView = binding.textNotifications;
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //final Observer<List<User>> userListObserver = new Observer<List<User>>() {
        final Observer<List<Circuit>> circuitListObserver = new Observer<List<Circuit>>() {



            @Override
            public void onChanged(@Nullable final List<Circuit> userList) {
                // Update the UI, in this case, a Toast.
                Toast.makeText(getContext(),
                        "We got a list of " + userList.size() + " circuits",
                        Toast.LENGTH_LONG).show();
                mAdapter.updateData(userList);
            }
        };

    /*
        //viewModel.getAllUsers().observe(getViewLifecycleOwner(), userListObserver);

        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        viewModel = new ViewModelProvider(requireActivity()).get(DriverViewModel.class);
        viewModel.getAllDrivers().observe(getViewLifecycleOwner(), driverListObserver);

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new DashboardAdapter(getContext(), viewModel.getAllDrivers().getValue());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


         */

        //NotificationsViewModel notificationsViewModel =
                //new ViewModelProvider(this).get(NotificationsViewModel.class);
        viewModel = new ViewModelProvider(requireActivity()).get(CircuitViewModel.class);
        viewModel.getAllCircuits().observe(getViewLifecycleOwner(), circuitListObserver);
        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new NotificationsAdapter(getContext(), viewModel.getAllCircuits().getValue());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        //final TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ergast.com")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CircuitInfo service = retrofit.create(CircuitInfo.class);

        viewModel.requestCircuits(new CircuitRepository(service));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}