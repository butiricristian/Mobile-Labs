package ro.ubb.cristian.examskeletonimproved.section1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.ubb.cristian.examskeletonimproved.R;
import ro.ubb.cristian.examskeletonimproved.controller.ItemController;
import ro.ubb.cristian.examskeletonimproved.model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListFragment extends Fragment {
    List<Item> items;
    SimpleItemRecyclerViewAdapter simpleItemRecyclerViewAdapter;
    ItemController itemController;
    RecyclerView recyclerView;
    private static final String TAG = "getAllItems";;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<>();
        simpleItemRecyclerViewAdapter = new SimpleItemRecyclerViewAdapter(items);
        itemController = new ItemController();
    }

    @Override
    public void onResume() {
        super.onResume();
        itemController.getAvailableCars().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful()) {
                    items = response.body();
                    simpleItemRecyclerViewAdapter.notifyDataSetChanged();
                    Log.d(TAG, "Success");
                }
                else{
                    String errMessage = "";
                    try {
                        errMessage = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Snackbar.make(getActivity().findViewById(R.id.parent_layout), errMessage, Snackbar.LENGTH_LONG).show();
                    Log.d(TAG, "Failed: " + errMessage);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Snackbar.make(getActivity().findViewById(R.id.parent_layout), t.getMessage(), Snackbar.LENGTH_LONG).show();
                Log.d(TAG, "Failed: " + t.getMessage());
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = view.findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.setAdapter(simpleItemRecyclerViewAdapter);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Item item = mValues.get(position);
            holder.textViewCarName.setText(item.getStringField1());
            holder.textViewCarQuantity.setText("IntField: " + item.getIntField1().toString());
            holder.textViewCarType.setText("StringField2: " + item.getStringField2());
            holder.textViewCarStatus.setText("StringField3: " + item.getStringField3());

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
