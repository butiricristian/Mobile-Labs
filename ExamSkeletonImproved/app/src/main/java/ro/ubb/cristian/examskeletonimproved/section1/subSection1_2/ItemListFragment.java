package ro.ubb.cristian.examskeletonimproved.section1.subSection1_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.ubb.cristian.examskeletonimproved.R;
import ro.ubb.cristian.examskeletonimproved.controller.ControllerProvider;
import ro.ubb.cristian.examskeletonimproved.controller.ItemController;
import ro.ubb.cristian.examskeletonimproved.database.CarDao;
import ro.ubb.cristian.examskeletonimproved.database.DatabaseProvider;
import ro.ubb.cristian.examskeletonimproved.model.Item;
import ro.ubb.cristian.examskeletonimproved.net.NetworkUtil;

public class ItemListFragment extends Fragment {
    List<Item> items;
    SimpleItemRecyclerViewAdapter simpleItemRecyclerViewAdapter;
    ItemController itemController;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView textViewNoConnection;
    Button retryButton;
    CarDao carDao;

    private static final String TAG = "getAllItems";;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ss1_2_fragment_item_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        carDao = DatabaseProvider.getDatabaseInstance(getContext()).getCarDao();
        items = carDao.findAll();
        simpleItemRecyclerViewAdapter = new SimpleItemRecyclerViewAdapter(items);
        itemController = ControllerProvider.getControllerInstance();
    }

    @Override
    public void onResume() {
        super.onResume();
//        checkConnection();
        getAvailableCars();
    }

    private void getAvailableCars() {
        progressBar.setVisibility(View.VISIBLE);
//        itemController.getAvailableCars().enqueue(new Callback<List<Item>>() {
//            @Override
//            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
//                if(response.isSuccessful()) {
//
//                }
//                else{
//                    String errMessage = "";
//                    try {
//                        errMessage = response.errorBody().string();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Snackbar.make(getActivity().findViewById(R.id.parent_layout), errMessage, Snackbar.LENGTH_LONG).show();
//                    Log.d(TAG, "Failed: " + errMessage);
//                }
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onFailure(Call<List<Item>> call, Throwable t) {
//                progressBar.setVisibility(View.INVISIBLE);
//                Snackbar.make(getActivity().findViewById(R.id.parent_layout), t.getMessage(), Snackbar.LENGTH_LONG).show();
//                Log.d(TAG, "Failed: " + t.getMessage());
//            }
//        });
        items.clear();
        items.addAll(carDao.findAll());
        simpleItemRecyclerViewAdapter.notifyDataSetChanged();
        Log.d(TAG, "Success");
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ItemNewActivity.class);
                startActivity(intent);
            }
        });

        progressBar = view.findViewById(R.id.progressBar);
        textViewNoConnection = view.findViewById(R.id.no_connection_text);
        retryButton = view.findViewById(R.id.retry_button);

//        retryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkConnection();
//            }
//        });

        recyclerView = view.findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.setAdapter(simpleItemRecyclerViewAdapter);
    }

//    private void checkConnection(){
//        progressBar.setVisibility(View.VISIBLE);
//        if(NetworkUtil.isNetworkAvailable(getContext())){
//            Log.d("Network", "Connection available");
//            recyclerView.setVisibility(View.VISIBLE);
//            textViewNoConnection.setVisibility(View.INVISIBLE);
//            retryButton.setVisibility(View.INVISIBLE);
//
//            getAvailableCars();
//        }
//        else{
//            Log.d("Network", "Connection not available");
//            recyclerView.setVisibility(View.INVISIBLE);
//            textViewNoConnection.setVisibility(View.VISIBLE);
//            retryButton.setVisibility(View.VISIBLE);
//            progressBar.setVisibility(View.INVISIBLE);
//        }
//    }

    /**
     * Simple adapter for the recycler view
     * Layout for each item in the ss1_2_item_list_content.xml
     */
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ss1_2_item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Item item = mValues.get(position);
            holder.textViewCarName.setText(item.getName());
            holder.textViewCarQuantity.setText("IntField: " + item.getQuantity().toString());
            holder.textViewCarType.setText("StringField2: " + item.getType());
            holder.textViewCarStatus.setText("StringField3: " + item.getStatus());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM, item);

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewCarName, textViewCarType, textViewCarQuantity, textViewCarStatus;

            ViewHolder(View view) {
                super(view);
                textViewCarName = itemView.findViewById(R.id.car_name);
                textViewCarType = itemView.findViewById(R.id.car_type);
                textViewCarQuantity = itemView.findViewById(R.id.car_quantity);
                textViewCarStatus = itemView.findViewById(R.id.car_status);
            }
        }
    }
}
