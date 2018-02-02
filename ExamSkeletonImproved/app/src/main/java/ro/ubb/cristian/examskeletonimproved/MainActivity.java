package ro.ubb.cristian.examskeletonimproved;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ro.ubb.cristian.examskeletonimproved.controller.ControllerProvider;
import ro.ubb.cristian.examskeletonimproved.controller.ItemController;
import ro.ubb.cristian.examskeletonimproved.net.MyWebSocketClient;
import ro.ubb.cristian.examskeletonimproved.net.NetworkUtil;
import ro.ubb.cristian.examskeletonimproved.section1.SectionOneActivity;
import ro.ubb.cristian.examskeletonimproved.section2.SectionTwoActivity;

public class MainActivity extends AppCompatActivity {

    Button clientButton, employeeButton;
    ItemController itemController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientButton = findViewById(R.id.client_button);
        employeeButton = findViewById(R.id.employee_button);

        itemController = ControllerProvider.getControllerInstance();
        startWS();

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SectionOneActivity.class);
                startActivity(i);
            }
        });

        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkUtil.isNetworkAvailable(view.getContext())) {
                    Intent i = new Intent(view.getContext(), SectionTwoActivity.class);
                    startActivity(i);
                }
                else{
                    Snackbar.make(view, "This feature is available only online", Snackbar.LENGTH_LONG);
                }
            }
        });
    }

    private void startWS(){
        MyWebSocketClient ws = new MyWebSocketClient(itemController, this);
        ws.connect();
    }
}
