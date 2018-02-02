package ro.ubb.cristian.examproject.net;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import ro.ubb.cristian.examproject.R;
import ro.ubb.cristian.examproject.controller.ItemController;
import ro.ubb.cristian.examproject.model.Project;

/**
 * Created by crist on 30-Jan-18.
 */

public class MyWebSocketClient extends WebSocketListener {
    private ItemController itemController;
    private final static String BASE_URL = "ws://192.168.100.2:4000";
    private WebSocket ws;
    private Context context;

    public MyWebSocketClient(ItemController itemController, Context context) {
        this.itemController = itemController;
        this.context = context;
    }

    public void connect() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();
        client.newWebSocket(request, this);

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher().executorService().shutdown();
        Log.i("WebSocket", "Client connected");
    }

    public void close(String reason){
        this.ws.close(1000, reason);
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        this.ws = webSocket;
        Log.d("WebSocket - onOpen:", response.message());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Project project = gson.fromJson(text, Project.class);
        int notificationId = new Random().nextInt(60000);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)//a resource for your custom small icon
                .setContentTitle("New car available") //the "title" value you sent in your notification
                .setContentText(project.toString()) //ditto
                .setAutoCancel(true) //dismisses the notification on click
                .setSound(defaultSoundUri);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
        itemController.notifyObservers();
        Log.d("WebSocket - onMessage", project.toString());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        Log.v("WebSocket - onClosing", "onClosing: " + code + " " + reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        Log.v("WebSocket - onClosed", "onClosed: " + code + " " + reason);
    }
}
