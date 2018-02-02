package ro.ubb.cristian.examproject.section2.subSection2_1;

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
import ro.ubb.cristian.examproject.R;
import ro.ubb.cristian.examproject.controller.ControllerProvider;
import ro.ubb.cristian.examproject.controller.ItemController;
import ro.ubb.cristian.examproject.model.Project;

/**
 * A fragment representing a single Project detail screen.
 * This fragment is either contained in a {@link ItemListFragment}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    public static final String ARG_ITEM = "item";
    private Project mProject;
    private ProgressBar progressBar;
    ItemController itemController;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM)) {
            itemController = ControllerProvider.getControllerInstance();
            mProject = (Project) getArguments().getSerializable(ARG_ITEM);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mProject.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ss2_1_item_detail, container, false);

        final EditText etCarName, etCarQuantity, etCarType, etCarStatus;
//        final TextView textViewCarName, textViewCarQuantity, textViewCarType, textViewCarStatus;

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        etCarName = (EditText) rootView.findViewById(R.id.details_car_name);
        etCarQuantity = (EditText) rootView.findViewById(R.id.details_car_quantity);
        etCarType = (EditText) rootView.findViewById(R.id.details_car_type);
        etCarStatus = (EditText) rootView.findViewById(R.id.details_car_status);


//        textViewCarName = (EditText) rootView.findViewById(R.id.details_car_name_text);
//        textViewCarQuantity = (EditText) rootView.findViewById(R.id.details_car_quantity_text);
//        textViewCarType = (EditText) rootView.findViewById(R.id.details_car_type_text);
//        textViewCarStatus = (EditText) rootView.findViewById(R.id.details_car_status_text);

        // Show the dummy content as text in a TextView.
        if (mProject != null) {
            etCarName.setText(mProject.getName());
            etCarQuantity.setText(mProject.getBudget().toString());
            etCarType.setText(mProject.getType());
            etCarStatus.setText(mProject.getStatus());

//            textViewCarName.setText(mProject.getName());
//            textViewCarQuantity.setText(mProject.getBudget().toString());
//            textViewCarType.setText(mProject.getType());
//            textViewCarStatus.setText(mProject.getStatus());
        }

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProject.setName(etCarName.getText().toString());
                mProject.setBudget(Integer.valueOf(etCarQuantity.getText().toString()));
                mProject.setType(etCarType.getText().toString());
                mProject.setStatus(etCarStatus.getText().toString());

                Snackbar.make(view, mProject.toString(), Snackbar.LENGTH_LONG).show();
            }
        });

        rootView.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemController.deleteProject(mProject).enqueue(new Callback<Project>() {
                    @Override
                    public void onResponse(Call<Project> call, Response<Project> response) {
                        if (response.isSuccessful()) {
                            Log.d("delete car: ", response.body().toString());
                            getActivity().finish();
                        } else {
                            String errMessage = "";
                            try {
                                errMessage = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Snackbar.make(getActivity().findViewById(R.id.item_detail_container), errMessage, Snackbar.LENGTH_LONG).show();
                            Log.d("delete car", "Failed: " + errMessage);
                        }
                    }

                    @Override
                    public void onFailure(Call<Project> call, Throwable t) {
                        Snackbar.make(getActivity().findViewById(R.id.item_detail_container), t.getMessage(), Snackbar.LENGTH_LONG).show();
                        Log.d("delete car", "Failed: " + t.getMessage());
                    }
                });
            }
        });

        rootView.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemController.approveProject(mProject).enqueue(new Callback<Project>() {
                    @Override
                    public void onResponse(Call<Project> call, Response<Project> response) {
                        if (response.isSuccessful()) {
                            Log.d("approve project: ", response.body().toString());
                            getActivity().finish();
                        } else {
                            String errMessage = "";
                            try {
                                errMessage = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Snackbar.make(getActivity().findViewById(R.id.item_detail_container), errMessage, Snackbar.LENGTH_LONG).show();
                            Log.d("approve project: ", "Failed: " + errMessage);
                        }
                    }

                    @Override
                    public void onFailure(Call<Project> call, Throwable t) {
                        Snackbar.make(getActivity().findViewById(R.id.item_detail_container), t.getMessage(), Snackbar.LENGTH_LONG).show();
                        Log.d("approve project: ", "Failed: " + t.getMessage());
                    }
                });
            }
        });

        rootView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemController.discardProject(mProject).enqueue(new Callback<Project>() {
                    @Override
                    public void onResponse(Call<Project> call, Response<Project> response) {
                        if (response.isSuccessful()) {
                            Log.d("discard project: ", response.body().toString());
                            getActivity().finish();
                        } else {
                            String errMessage = "";
                            try {
                                errMessage = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Snackbar.make(getActivity().findViewById(R.id.item_detail_container), errMessage, Snackbar.LENGTH_LONG).show();
                            Log.d("discard project: ", "Failed: " + errMessage);
                        }
                    }

                    @Override
                    public void onFailure(Call<Project> call, Throwable t) {
                        Snackbar.make(getActivity().findViewById(R.id.item_detail_container), t.getMessage(), Snackbar.LENGTH_LONG).show();
                        Log.d("discard project: ", "Failed: " + t.getMessage());
                    }
                });
            }
        });

        return rootView;
    }
}
