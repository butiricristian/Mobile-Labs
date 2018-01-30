package com.example.cristianbutiri.examskeleton.section2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cristianbutiri.examskeleton.R;
import com.example.cristianbutiri.examskeleton.controller.CarController;
import com.example.cristianbutiri.examskeleton.model.Car;

import java.util.List;

/**
 * Created by cristianbutiri on 30.01.2018.
 */

public class AllCarsAdapter extends RecyclerView.Adapter<AllCarsAdapter.CarViewHolder> {

    private Context mCtx;
    private List<Car> cars;
    private CarController carController;

    public AllCarsAdapter(Context mCtx, List<Car> cars, CarController carController) {
        this.mCtx = mCtx;
        this.cars = cars;
        this.carController = carController;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.mCtx);
        View view = inflater.inflate(R.layout.item_layout_2, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, final int position) {
        final Car car = cars.get(position);
        holder.textViewCarName.setText(car.getName());
        holder.textViewCarQuantity.setText("Quantity: " + car.getQuantity().toString());
        holder.textViewCarType.setText("Type: " + car.getType());
        holder.textViewCarStatus.setText("Status: " + car.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, CarDetailsActivity.class);
                intent.putExtra("repository", carController.getCarRepository());
                intent.putExtra("car", car);

                context.startActivity(intent);
            }
        });

//        holder.buyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
////                AlertDialog.Builder adBuilder = new AlertDialog.Builder(view.getContext());
////                adBuilder.setMessage("Please select quantity")
////                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                            @Override
////                            public void onClick(DialogInterface dialogInterface, int i) {
////                                Snackbar.make(view, "You made it", 1000).show();
////                            }
////                        })
////                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                            @Override
////                            public void onClick(DialogInterface dialogInterface, int i) {
////                                Snackbar.make(view, "You didn't make it", 1000).show();
////                            }
////                        });
////                adBuilder.create().show();
//                Snackbar.make(view, "Not yet implemented", 1000).show();
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder{
        TextView textViewCarName, textViewCarType, textViewCarQuantity, textViewCarStatus;
//        Button buyButton;

        CarViewHolder(View itemView) {
            super(itemView);
            textViewCarName = itemView.findViewById(R.id.car_name);
            textViewCarType = itemView.findViewById(R.id.car_type);
            textViewCarQuantity = itemView.findViewById(R.id.car_quantity);
            textViewCarStatus = itemView.findViewById(R.id.car_status);
//            buyButton = itemView.findViewById(R.id.buy_button);
        }
    }
}