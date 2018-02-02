package ro.ubb.cristian.examskeletonimproved.controller;

import android.arch.persistence.room.Room;
import android.content.Context;

import ro.ubb.cristian.examskeletonimproved.database.AppDatabase;

/**
 * Created by crist on 31-Jan-18.
 */

public class ControllerProvider {
    private static ItemController itemController = null;

    public static ItemController getControllerInstance(){
        if(itemController != null){
            return itemController;
        }
        itemController = new ItemController();
        return itemController;
    }
}
