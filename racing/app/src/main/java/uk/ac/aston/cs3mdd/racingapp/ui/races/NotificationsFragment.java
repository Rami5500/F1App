package uk.ac.aston.cs3mdd.racingapp.ui.races;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
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
    private SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewModel = new ViewModelProvider(requireActivity()).get(CircuitViewModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Observer<List<Circuit>> circuitListObserver = new Observer<List<Circuit>>() {



            @Override
            public void onChanged(@Nullable final List<Circuit> userList) {
                /*Using a Toast to double check it works.
                Toast.makeText(getContext(),
                        "We got a list of " + userList.size() + " circuits",
                        Toast.LENGTH_LONG).show();

                 */

                for (Circuit circuit : mAdapter.mCircuitList) {
                    String name = circuit.getCircuitName();
                    int nameId = getNameId(name);
                    circuit.setImageResource(nameId);
                }

                mAdapter.updateData(userList);
            }
        };

        viewModel = new ViewModelProvider(requireActivity()).get(CircuitViewModel.class);
        viewModel.getAllCircuits().observe(getViewLifecycleOwner(), circuitListObserver);

        searchView = view.findViewById(R.id.searchViewCircuits);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCircuitNames(newText);
                return true;
            }
        });

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new NotificationsAdapter(getContext(), viewModel.getAllCircuits().getValue());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ergast.com")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CircuitInfo service = retrofit.create(CircuitInfo.class);

        viewModel.requestCircuits(new CircuitRepository(service));


    }

    private int getNameId(String name) {
        switch (name) {
            case "Albert Park Grand Prix Circuit":
                return R.drawable.albert_park_melbourne;
            case "Circuit of the Americas":
                return R.drawable.americas;
            case "Bahrain International Circuit":
                return R.drawable.bahrain;
            case "Baku City Circuit":
                return R.drawable.baku;
            case "Circuit de Barcelona-Catalunya":
                return R.drawable.catalunya;
            case "Hungaroring":
                return R.drawable.hungaroring;
            case "Autódromo José Carlos Pace":
                return R.drawable.sao_paulo;
            case "Jeddah Corniche Circuit":
                return R.drawable.jeddah;
            case "Losail International Circuit":
                return R.drawable.qatar;
            case "Marina Bay Street Circuit":
                return R.drawable.marina_bay;
            case "Miami International Autodrome":
                return R.drawable.miami;
            case "Circuit de Monaco":
                return R.drawable.monaco;
            case "Autodromo Nazionale di Monza":
                return R.drawable.monza;
            case "Red Bull Ring":
                return R.drawable.red_bull_ring;
            case "Autódromo Hermanos Rodríguez":
                return R.drawable.hermanos_rodriguez;
            case "Silverstone Circuit":
                return R.drawable.silverstone;
            case "Circuit de Spa-Francorchamps":
                return R.drawable.spa;
            case "Suzuka Circuit":
                return R.drawable.suzaka;
            case "Las Vegas Strip Street Circuit":
                return R.drawable.las_vegas;
            case "Circuit Gilles Villeneuve":
                return R.drawable.canadian;
            case "Yas Marina Circuit":
                return R.drawable.marina;
            case "Circuit Park Zandvoort":
                return R.drawable.zanvoort;
            default:
                return R.drawable.australia;
        }
    }

    private void filterCircuitNames(String text) {

        List<Circuit> filteredList = new ArrayList<>();

        for (Circuit circuit: mAdapter.mCircuitList) {
            if (circuit.getCircuitName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(circuit);
            }
        }

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