package uk.ac.aston.cs3mdd.racingapp.ui.drivers;

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

import org.w3c.dom.Text;

import java.util.List;

import model.Driver;
import uk.ac.aston.cs3mdd.racingapp.R;

public class DashboardAdapter extends
        RecyclerView.Adapter<DashboardAdapter.DriverViewHolder> {

    public List<Driver> mDriverList;
    private final LayoutInflater mInflater;
    public void setFilteredList(List<Driver> filteredList){
        this.mDriverList = filteredList;
        notifyDataSetChanged();
    }

    public DashboardAdapter(Context context,
                            List<Driver> driversList) {
        mInflater = LayoutInflater.from(context);
        this.mDriverList = driversList;
    }

    @NonNull
    @Override
    public DashboardAdapter.DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.driver_item,
                parent, false);
        return new DriverViewHolder(mItemView, this);
    }

    /*
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mUserList.get(position);
        holder.user = user;
        Name name = user.getName();
        String displayName = name.getTitle() + " " + name.getFirst() + " " + name.getLast();
        holder.usernameView.setText(displayName);
    }
     */
    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.DriverViewHolder holder, int position) {
        Driver driver = mDriverList.get(position);
        holder.driver = driver;
        String displayName = driver.getFamilyName() + " " + driver.getGivenName();
        holder.username.setText(displayName);
        holder.nationalityView.setText(driver.getNationality());
        holder.codeView.setText(driver.getCode());
        holder.permanentNumberView.setText(String.valueOf(driver.getPermanentNumber()));
    }

    @Override
    public int getItemCount() {
        return this.mDriverList.size();
    }

    public void updateData(List<Driver> list) {
        this.mDriverList = list;
        notifyDataSetChanged();
    }

    /*class UserViewHolder extends RecyclerView.ViewHolder {
        public final TextView usernameView;
        final UserListAdapter mAdapter;
        public User user;

        public UserViewHolder(@NonNull View itemView, UserListAdapter adapter) {
            super(itemView);
            usernameView = itemView.findViewById(R.id.username);
            this.mAdapter = adapter;
        }
    }

     */
    class DriverViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public final TextView codeView;
        public final TextView permanentNumberView;
        public final TextView username;
        public final TextView nationalityView;
        final DashboardAdapter mAdapter;
        public Driver driver;

        public DriverViewHolder(@NonNull View itemView, DashboardAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);
            username = itemView.findViewById(R.id.username);

            nationalityView = itemView.findViewById(R.id.nationality);
            permanentNumberView = itemView.findViewById(R.id.permanentNumber);
            codeView = itemView.findViewById(R.id.code);

            this.mAdapter = adapter;
        }

        @Override
        public void onClick(View v) {
            DashboardFragmentDirections.ActionNavigationDashboardToDriverFragment3 action =
                    DashboardFragmentDirections.actionNavigationDashboardToDriverFragment3(driver);
            Navigation.findNavController(v)
                    .navigate((NavDirections) action);


        }
    }


}

