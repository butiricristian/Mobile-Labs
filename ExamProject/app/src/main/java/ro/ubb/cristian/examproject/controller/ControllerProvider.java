package ro.ubb.cristian.examproject.controller;

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
