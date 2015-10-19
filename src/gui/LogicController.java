package gui;

import java.nio.file.FileSystemException;
import java.util.ArrayList;

import backend.Logic;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LogicController {

	private static final String MESSAGE_ERROR_FILESYSTEM = "Failed to create the file.";
	
	private static Logic logic;
	private static CommandHistory commandHistory;
	
	public LogicController() {
		
		try {
			logic = new Logic();
		} catch (FileSystemException e) {
			logic = null;
		}
		
		// Initialize the command history object
		commandHistory = new CommandHistory();
	}
	
	public String getFilePath() {
		
		if (logic != null) {
			return logic.getFilepath();
		} else {
			return MESSAGE_ERROR_FILESYSTEM;
		}
	}
	
	public ArrayList<String> getDefTasks() {
		
		ArrayList<String> temp = new ArrayList<String>();
		
		// Get the string from logic
		String defTasks = logic.taskDefaultView();
		
		// Split the string by newline
		String[] defTasksSplit = defTasks.split("\n");
		
		// Traverse the split string array and ignore the empty lines
		for (int i = 0; i < defTasksSplit.length; i++) {
			if (!defTasksSplit[i].equals("")) {
				// Add it to the ArrayList				
				temp.add(defTasksSplit[i]);
			}
		}
		
		return temp;
	}
	
	public ArrayList<String> getDefEvents() {
		
		ArrayList<String> temp = new ArrayList<String>();
		
		// Get the string from logic
		String defEvents = logic.eventDefaultView();
		
		// Split the string by newline
		String[] defEventsSplit = defEvents.split("\n");
		
		// Traverse the split string array and ignore the empty lines
		for (int i = 0; i < defEventsSplit.length; i++) {
			if (!defEventsSplit[i].equals("")) {
				// Add it to the ArrayList
				temp.add(defEventsSplit[i]);
			}
		}
		
		return temp;
	}
	
	/* ================================================================================
     * Getters to allow InterfaceController to access the private handling classes
     * ================================================================================
     */
	
	public TextInputHandler getTextInputHandler() {
		return new TextInputHandler();
	}
	
	public UpKeyHandler getUpKeyHandler() {
		return new UpKeyHandler();
	}
	
	public DownKeyHandler getDownKeyHandler() {
		return new DownKeyHandler();
	}
	
	public HeightListener getHeightListener() {
		return new HeightListener();
	}
	
	public WidthListener getWidthListener() {
		return new WidthListener();
	}
	
	/* ================================================================================
     * Private event handlers for InterfaceController
     * ================================================================================
     */	
	
	private static class TextInputHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

        	// Get the text field from InterfaceController
        	TextField textField = InterfaceController.getTextField();
        	
            String textFieldInput = textField.getText();

            // Add the input into command history
            commandHistory.add(textFieldInput);
            commandHistory.resetIndex();

            // Clear the textField
            textField.setText("");

            /*
             * Perform different operations depending on the currentView and the command
             * ================================================================================
             */
            
            switch(InterfaceController.currentView) {
            /* ================================================================================
             * Default view
             * ================================================================================
             */
            case InterfaceController.VIEW_DEFAULT:
            	switch (textFieldInput) {
            	case "all":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_ALL);
            		break;
            	case "hist":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_HIST);
            		break;
            	case "done":
            		// TODO
            		//InterfaceController.updateMainInterface(DoneViewController.initDoneView());
            		break;
            	case "search":
            		// TODO
            		//InterfaceController.updateMainInterface(InterfaceController.VIEW_SEARCH);
            		break;
            	default:
            		// Run the operation
                	String returnMessage = logic.executeCommand(textFieldInput);
                	// Add the returnMessage to the feedback bar and history view
                	InterfaceController.getFeedbackLabel().setText(returnMessage);
                	HistoryViewController.updateHistView(returnMessage);

                	// Update the Tasks and Events
                	DefaultViewController.updateDefView();
                	break;
            	}
            	break;
            /* ================================================================================
             * All view
             * ================================================================================
             */
            case InterfaceController.VIEW_ALL:
            	switch (textFieldInput) {
            	case "def":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_DEFAULT);
            		break;
            	case "hist":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_HIST);
            		break;
            	case "done":
            		// TODO
            		//InterfaceController.updateMainInterface(DoneViewController.initDoneView());
            		break;
            	case "search":
            		// TODO
            		//InterfaceController.updateMainInterface(InterfaceController.VIEW_SEARCH);
            		break;
            	default:
            		// Run the operation
                	String returnMessage = logic.executeCommand(textFieldInput);
                	// Add the returnMessage to the feedback bar and history view
                	InterfaceController.getFeedbackLabel().setText(returnMessage);
                	HistoryViewController.updateHistView(returnMessage);

                	// Update the Tasks and Events
                	AllViewController.updateAllView();
                	break;
            	}
            	break;
            /* ================================================================================
             * History view
             * ================================================================================
             */
            case InterfaceController.VIEW_HIST:
            	switch (textFieldInput) {
            	case "def":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_DEFAULT);
            		break;
            	case "all":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_ALL);
            		break;
            	case "done":
            		// TODO
            		//InterfaceController.updateMainInterface(DoneViewController.initDoneView());
            		break;
            	case "search":
            		// TODO
            		//InterfaceController.updateMainInterface(InterfaceController.VIEW_SEARCH);
            		break;
            	default:
            		// Run the operation
                	String returnMessage = logic.executeCommand(textFieldInput);
                	// Add the returnMessage to the feedback bar and history view
                	InterfaceController.getFeedbackLabel().setText(returnMessage);
                	HistoryViewController.updateHistView(returnMessage);
                	break;
            	}
            	break;
            /* ================================================================================
             * Done view
             * ================================================================================
             */
            case InterfaceController.VIEW_DONE:
            	switch (textFieldInput) {
            	case "def":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_DEFAULT);
            		break;
            	case "all":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_ALL);
            		break;
            	case "hist":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_HIST);
            		break;
            	case "search":
            		// TODO
            		//InterfaceController.updateMainInterface(InterfaceController.VIEW_SEARCH);
            		break;
            	default:
            		// Run the operation
                	String returnMessage = logic.executeCommand(textFieldInput);
                	// Add the returnMessage to the feedback bar and history view
                	InterfaceController.getFeedbackLabel().setText(returnMessage);
                	HistoryViewController.updateHistView(returnMessage);

                	// TODO: Update the completed tasks view
                	//DoneViewController.updateDoneView();
                	break;
            	}
            	break;
            /* ================================================================================
             * Search view
             * ================================================================================
             */
            case InterfaceController.VIEW_SEARCH:
            	switch (textFieldInput) {
            	case "def":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_DEFAULT);
            	case "all":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_ALL);
            		break;
            	case "hist":
            		InterfaceController.updateMainInterface(InterfaceController.VIEW_HIST);
            		break;
            	case "done":
            		// TODO
            		//InterfaceController.updateMainInterface(DoneViewController.initDoneView());
            		break;
            	default:
            		// Run the operation
            		String returnMessage = logic.executeCommand(textFieldInput);
            		// Add the returnMessage to the feedback bar and history view
            		InterfaceController.getFeedbackLabel().setText(returnMessage);
            		HistoryViewController.updateHistView(returnMessage);
            		break;
            	}
            	break;
            	
            default: // do nothing, should not enter
            	break;
            }
        }
    }
	
	private static class UpKeyHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.UP) {
                InterfaceController.getTextField().setText(commandHistory.getPrevious());
            }
        }
    }

    private static class DownKeyHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.DOWN) {
                InterfaceController.getTextField().setText(commandHistory.getNext());
            }
        }
    }
    
    private static class HeightListener implements ChangeListener<Number> {

    	@Override
    	public void changed(ObservableValue<? extends Number> observable,
    			Number oldValue, Number newValue) {

    		// Set the height of the sidebar separator to window height
    		InterfaceController.getSbLine().setEndY((Double)newValue);

    		// Set the height of the scroll pane separator to
    		// window height - height of the filepath bar(31) -
    		// height of feedback bar(31) - height of text bar(40) - 
    		// height of viewLine(1)
    		DefaultViewController.getDefScrollLine().setEndY((Double)newValue - 
    				InterfaceController.HEIGHT_FILEPATH - 
    				InterfaceController.HEIGHT_FEEDBACK - 
    				InterfaceController.HEIGHT_TEXTFIELD - 
    				InterfaceController.HEIGHT_HORIZ_LINE);
    	}
    }

    private static class WidthListener implements ChangeListener<Number> {

    	@Override
    	public void changed(ObservableValue<? extends Number> observable,
    			Number oldValue, Number newValue) {

    		// Set the width of the feedback, filepath and view box separators to
    		// window width - size of sidebar(50) - width of line(1)
    		InterfaceController.getFeedbackLine().setEndX(
    				(Double)newValue - InterfaceController.WIDTH_SIDEBAR);
    		InterfaceController.getViewLine().setEndX(
    				(Double)newValue - InterfaceController.WIDTH_SIDEBAR);
    		InterfaceController.getFilepathLine().setEndX(
    				(Double)newValue - InterfaceController.WIDTH_SIDEBAR);
    	}
    }
}
