/**
 * This class contains the main application that runs the entire program and GUI
 *
 * @author Adrian
 */

package gui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import struct.View;
import javafx.scene.*;

public class MainApp extends Application {

    /* ================================================================================
     * Variables used in scene construction
     * ================================================================================
     */
    protected static final double MIN_WINDOW_HEIGHT = 700;
    protected static final double MIN_WINDOW_WIDTH = 1000;
    
    protected static final double WIDTH_HELP_DIALOG = 600;
    protected static final double HEIGHT_HELP_DIALOG = 800;
    
    private static final String TITLE_STAGE = "WhatToDo";
    private static final String TITLE_HELP = "Help Dialog";

    /* ================================================================================
      * Scenes and stage used by the program
      * ================================================================================
      */
    protected static Scene scene, helpScene;
    protected static Stage stage, help;

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        // Initialize both scenes
        InterfaceController.initMainInterface();
        HelpController.initHelpScene();

        // Initialize the different stages
        initPrimaryStage();
        initHelpStage();

        // Run
        stage.show();
        
        // Set the first view to be the default view
        InterfaceController.updateMainInterface(View.DEFAULT);
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the default Scene, window title, window icon, default window
     * dimensions, and the minimum and maximum window sizes
     */
    public static void initPrimaryStage() {
    	
        // Set the scene to be displayed
        stage.setScene(scene);

        // Customize the stage
        stage.setTitle(TITLE_STAGE);
        stage.getIcons().add(new Image("gui/resources/icon.png"));

        stage.setWidth(MIN_WINDOW_WIDTH);
        stage.setHeight(MIN_WINDOW_HEIGHT);
        stage.setMinHeight(MIN_WINDOW_HEIGHT);
        stage.setMinWidth(MIN_WINDOW_WIDTH);
        
        // Event handling for summary view
        stage.addEventFilter(KeyEvent.KEY_PRESSED, 
        		InterfaceController.logicControl.getTabPressHandler());
        stage.addEventFilter(KeyEvent.KEY_RELEASED, 
        		InterfaceController.logicControl.getTabPressHandler());
    }
    
    public static void initHelpStage() {
    	
    	help = new Stage();
    	help.setScene(helpScene);
    	
    	help.setTitle(TITLE_HELP);
        help.setWidth(WIDTH_HELP_DIALOG);
        help.setHeight(HEIGHT_HELP_DIALOG);
        help.setResizable(false);
        
        help.showingProperty().addListener(
        		InterfaceController.logicControl.getCloseHelpHandler());
    }
}
