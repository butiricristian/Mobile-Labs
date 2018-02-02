package ro.ubb.cristian.examskeletonimproved.section1.subSection1_1;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.ubb.cristian.examskeletonimproved.R;
import ro.ubb.cristian.examskeletonimproved.controller.ControllerProvider;
import ro.ubb.cristian.examskeletonimproved.controller.ItemController;
import ro.ubb.cristian.examskeletonimproved.model.Item;

/**
 * Created by crist on 31-Jan-18.
 */

public class ItemNewFragment extends Fragment {
    public static final String TITLE = "New Item";
    private ProgressBar progressBar;
    private ItemController itemController;

    public ItemNewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemController = ControllerProvider.getControllerInstance();
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ss1_1_item_new, container, false);

        final EditText etCarName, etCarQuantity, etCarType, etCarStatus;

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        etCarName = (EditText) rootView.findViewById(R.id.new_car_name);
        etCarQuantity = (EditText) rootView.findViewById(R.id.new_car_quantity);
        etCarType = (EditText) rootView.findViewById(R.id.new_car_type);
        etCarStatus = (EditText) rootView.findViewById(R.id.new_car_status);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item = new Item();
                item.setName(etCarName.getText().toString());
                item.setQuantity(Integer.valueOf(etCarQuantity.getText().toString()));
                item.setType(etCarType.getText().toString());
                item.setStatus(etCarStatus.getText().toString());
                progressBar.setVisibility(View.VISIBLE);

                itemController.addItem(item).enqueue(new Callback<Item>() {
                    @Override
                    public void onResponse(Call<Item> call, Response<Item> response) {
                        if (response.isSuccessful()) {
                            getActivity().finish();
                            Log.d(getTag(), "Success: " + response.body().toString());
                        } else {
                            String errMessage = "";
                            try {
                                errMessage = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Snackbar.make(getActivity().findViewById(R.id.item_detail_container), errMessage, Snackbar.LENGTH_LONG).show();
                            Log.d(getTag(), "Failed: " + errMessage);
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailure(Call<Item> call, Throwable t) {
                        Snackbar.make(getActivity().findViewById(R.id.item_detail_container), t.getMessage(), Snackbar.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d(getTag(), "" + t.getMessage());
                    }
                });
            }
        });
//
//        rootView.findViewById(R.id.remove_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Implement Delete Action", Snackbar.LENGTH_LONG).show();
//            }
//        });

        return rootView;
    }
}
