package gui;

import java.nio.file.FileSystemException;
import java.util.ArrayList;

import backend.Logic;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

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

            // Run the operation
            String returnMessage = logic.executeCommand(textFieldInput);
            // Add the returnMessage to the History pane
            InterfaceController.getFeedbackLabel().setText(returnMessage);

            // Update the Tasks and Events
            InterfaceController.updateDefView();
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

    		// Set the height of the sidebar separator to
    		// window height - height of filepath bar(35) - height of line(1)
    		InterfaceController.getSbLine().setEndY((Double)newValue - 36);

    		// Set the height of the scroll pane separator to
    		// window height - height of the filepath bar(35) -
    		// height of feedback bar(35) - height of text bar(45) - 
    		// 2 * height of line(1)
    		InterfaceController.getDefScrollLine().setEndY((Double)newValue - 117);
    	}
    }

    private static class WidthListener implements ChangeListener<Number> {

    	@Override
    	public void changed(ObservableValue<? extends Number> observable,
    			Number oldValue, Number newValue) {

    		// Set the width of the feedback and view box separators to
    		// window width - size of sidebar(50) - width of line(1)
    		InterfaceController.getFeedbackLine().setEndX((Double)newValue - 51);
    		InterfaceController.getDefLine().setEndX((Double)newValue - 51);

    		// Set the width of the filepath separator to window width
    		InterfaceController.getFilepathLine().setEndX((Double)newValue);
    	}
    }
}
