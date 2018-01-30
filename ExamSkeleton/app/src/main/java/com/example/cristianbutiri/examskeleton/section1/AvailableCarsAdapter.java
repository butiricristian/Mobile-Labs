package com.example.cristianbutiri.examskeleton.section1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cristianbutiri.examskeleton.R;
import com.example.cristianbutiri.examskeleton.controller.CarController;
import com.example.cristianbutiri.examskeleton.database.AppDatabase;
import com.example.cristianbutiri.examskeleton.database.CarDao;
import com.example.cristianbutiri.examskeleton.database.DatabaseProvider;
import com.example.cristianbutiri.examskeleton.model.Car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cristianbutiri on 30.01.2018.
 */

public class AvailableCarsAdapter extends RecyclerView.Adapter<AvailableCarsAdapter.CarViewHolder> {

    private Context mCtx;
    private List<Car> cars;
    private CarController carController;

    public AvailableCarsAdapter(Context mCtx, List<Car> cars, CarController carController) {
        this.mCtx = mCtx;
        this.cars = cars;
        this.carController = carController;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.mCtx);
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, final int position) {
        final Car car = cars.get(position);
        holder.textViewCarName.setText(car.getName());
        holder.textViewCarQuantity.setText("Quantity: " + car.getQuantity().toString());
        holder.textViewCarType.setText(car.getType());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, CarDetailActivity.class);
//                intent.putExtra(CarDetailFragment.CAR, car);
//
//                context.startActivity(intent);
//            }
//        });

        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                AlertDialog.Builder adBuilder = new AlertDialog.Builder(view.getContext());
//                adBuilder.setMessage("Please select quantity")
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Snackbar.make(view, "You made it", 1000).show();
//                            }
//                        })
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Snackbar.make(view, "You didn't make it", 1000).show();
//                            }
//                        });
//                adBuilder.create().show();
                Map<String, String> body = new HashMap<>();
                body.put("id", car.getId().toString());
                body.put("quantity", "1");
                carController.buyCar(body);
//                AppDatabase database = DatabaseProvider.getDatabaseInstance(mCtx);
//                CarDao carDao = database.getCarDao();
//                Car car2 = new Car();
//                car2.setId(car.getId());
//                car2.setQuantity(1);
//                car2.setName(car.getName());
//                car2.setStatus(car.getStatus());
//                car2.setType(car.getType());
//                carDao.save(car);
            }
        });


    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCarName, textViewCarType, textViewCarQuantity;
        Button buyButton;

        CarViewHolder(View itemView) {
            super(itemView);
            textViewCarName = itemView.findViewById(R.id.car_name);
            textViewCarType = itemView.findViewById(R.id.car_type);
            textViewCarQuantity = itemView.findViewById(R.id.car_quantity);
            buyButton = itemView.findViewById(R.id.buy_button);
        }
    }
}