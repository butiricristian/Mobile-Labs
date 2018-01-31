package ro.ubb.cristian.examskeletonimproved.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import ro.ubb.cristian.examskeletonimproved.controller.ItemController;

/**
 * Created by crist on 30-Jan-18.
 */

public class MyWebSocketClient extends WebSocketListener {
    private ItemController itemController;
    private final static String BASE_URL = "ws://192.168.100.2:4000";
    private WebSocket ws;

    public MyWebSocketClient(ItemController itemController) {
        this.itemController = itemController;
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
        Log.i("WebSocker", "Client connected");
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
        Log.d("WebSocket - onMessage", text);
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
