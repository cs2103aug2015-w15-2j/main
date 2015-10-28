package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SummaryViewController {

	// Used for initSummaryHeader
	private static Label summaryHeaderLabel;
	private static HBox summaryHeaderBox;
	
	// Used for initTaskTwoDays
	private static Label taskTwoDaysCount, taskTwoDaysLabel;
	private static VBox taskTwoDaysBox;
	
	// Used for initEventTwoDays
	private static Label eventTwoDaysCount, eventTwoDaysLabel;
	private static VBox eventTwoDaysBox;
	
	// Used for initTaskFloat
	private static Label taskFloatCount, taskFloatLabel;
	private static VBox taskFloatBox;
	
	// Used for initEventOngoing
	private static Label eventOngoingCount, eventOngoingLabel;
	private static VBox eventOngoingBox;
	
	// Used for initRowOne
	private static HBox rowOneBox;
	
	// Used for initRowTwo
	private static HBox rowTwoBox;
	
	// Used for initSummaryView
	private static VBox summaryBox;
	
	private static final String HEADER_SUMMARY = "Quick View";
	private static final String HEADER_TASK_TWO_DAYS = "tasks due within two days";
	private static final String HEADER_EVENT_TWO_DAYS = "events starting within two days";
	private static final String HEADER_TASK_FLOAT = "tasks with no deadline";
	private static final String HEADER_EVENT_ONGOING = "events ongoing";
	
	private static void initSummaryHeader() {
		
		summaryHeaderLabel = new Label(HEADER_SUMMARY);
		summaryHeaderBox = new HBox(summaryHeaderLabel);
		summaryHeaderBox.setAlignment(Pos.CENTER);
		
		// CSS
		summaryHeaderLabel.getStyleClass().add("summary-header");
		summaryHeaderLabel.setStyle("-fx-font-family: \"Myriad Pro Light\";");
	}
	
	private static void initTaskTwoDays(String count) {
		
		taskTwoDaysCount = new Label(count);
		taskTwoDaysLabel = new Label(HEADER_TASK_TWO_DAYS);
		taskTwoDaysBox = new VBox(taskTwoDaysCount, taskTwoDaysLabel);
		taskTwoDaysBox.setAlignment(Pos.BOTTOM_CENTER);
		
		taskTwoDaysBox.setMaxWidth(300);
		taskTwoDaysBox.setMinWidth(300);
		taskTwoDaysBox.setMaxHeight(200);
		taskTwoDaysBox.setMinHeight(200);
		
		VBox.setMargin(taskTwoDaysCount, new Insets(0, 0, 15, 0));
		VBox.setMargin(taskTwoDaysLabel, new Insets(0, 0, 15, 0));
		
		// CSS
		taskTwoDaysBox.getStyleClass().add("summary-box");
		taskTwoDaysCount.getStyleClass().add("summary-box-label-count");
		taskTwoDaysLabel.getStyleClass().add("summary-box-label");
		taskTwoDaysLabel.setStyle("-fx-font-family: \"Myriad Pro Light\";");
	}
	
	private static void initEventTwoDays(String count) {
		
		eventTwoDaysCount = new Label(count);
		eventTwoDaysLabel = new Label(HEADER_EVENT_TWO_DAYS);
		eventTwoDaysBox = new VBox(eventTwoDaysCount, eventTwoDaysLabel);
		eventTwoDaysBox.setAlignment(Pos.BOTTOM_CENTER);
		
		eventTwoDaysBox.setMaxWidth(300);
		eventTwoDaysBox.setMinWidth(300);
		eventTwoDaysBox.setMaxHeight(200);
		eventTwoDaysBox.setMinHeight(200);
		
		VBox.setMargin(eventTwoDaysCount, new Insets(0, 0, 15, 0));
		VBox.setMargin(eventTwoDaysLabel, new Insets(0, 0, 15, 0));
		
		// CSS
		eventTwoDaysBox.getStyleClass().add("summary-box");
		eventTwoDaysCount.getStyleClass().add("summary-box-label-count");
		eventTwoDaysLabel.getStyleClass().add("summary-box-label");
		eventTwoDaysLabel.setStyle("-fx-font-family: \"Myriad Pro Light\";");
	}
	
	private static void initTaskFloat(String count) {
		
		taskFloatCount = new Label(count);
		taskFloatLabel = new Label(HEADER_TASK_FLOAT);
		taskFloatBox = new VBox(taskFloatCount, taskFloatLabel);
		taskFloatBox.setAlignment(Pos.BOTTOM_CENTER);
		
		taskFloatBox.setMaxWidth(300);
		taskFloatBox.setMinWidth(300);
		taskFloatBox.setMaxHeight(200);
		taskFloatBox.setMinHeight(200);
		
		VBox.setMargin(taskFloatCount, new Insets(0, 0, 15, 0));
		VBox.setMargin(taskFloatLabel, new Insets(0, 0, 15, 0));
		
		// CSS
		taskFloatBox.getStyleClass().add("summary-box");
		taskFloatCount.getStyleClass().add("summary-box-label-count");
		taskFloatLabel.getStyleClass().add("summary-box-label");
		taskFloatLabel.setStyle("-fx-font-family: \"Myriad Pro Light\";");
	}
	
	private static void initEventOngoing(String count) {
		
		eventOngoingCount = new Label(count);
		eventOngoingLabel = new Label(HEADER_EVENT_ONGOING);
		eventOngoingBox = new VBox(eventOngoingCount, eventOngoingLabel);
		eventOngoingBox.setAlignment(Pos.BOTTOM_CENTER);
		
		eventOngoingBox.setMaxWidth(300);
		eventOngoingBox.setMinWidth(300);
		eventOngoingBox.setMaxHeight(200);
		eventOngoingBox.setMinHeight(200);
		
		VBox.setMargin(eventOngoingCount, new Insets(0, 0, 15, 0));
		VBox.setMargin(eventOngoingLabel, new Insets(0, 0, 15, 0));
		
		// CSS
		eventOngoingBox.getStyleClass().add("summary-box");
		eventOngoingCount.getStyleClass().add("summary-box-label-count");
		eventOngoingLabel.getStyleClass().add("summary-box-label");
		eventOngoingLabel.setStyle("-fx-font-family: \"Myriad Pro Light\";");
	}
	
	private static void initRowOne(String taskCount, String eventCount) {
		
		initTaskTwoDays(taskCount);
		initEventTwoDays(eventCount);
		
		Region spaceLeft = new Region();
		Region spaceCenter = new Region();
		Region spaceRight = new Region();
		
		rowOneBox = new HBox(spaceLeft, taskTwoDaysBox, spaceCenter, eventTwoDaysBox, spaceRight);
		rowOneBox.setAlignment(Pos.CENTER);
		
		HBox.setHgrow(spaceLeft, Priority.ALWAYS);
		HBox.setHgrow(spaceCenter, Priority.ALWAYS);
		HBox.setHgrow(spaceRight, Priority.ALWAYS);
	}
	
	private static void initRowTwo(String taskCount, String eventCount) {
		
		initTaskFloat(taskCount);
		initEventOngoing(eventCount);
		
		Region spaceLeft = new Region();
		Region spaceCenter = new Region();
		Region spaceRight = new Region();
		
		rowTwoBox = new HBox(spaceLeft, taskFloatBox, spaceCenter, eventOngoingBox, spaceRight);
		rowTwoBox.setAlignment(Pos.CENTER);
		
		HBox.setHgrow(spaceLeft, Priority.ALWAYS);
		HBox.setHgrow(spaceCenter, Priority.ALWAYS);
		HBox.setHgrow(spaceRight, Priority.ALWAYS);
	}
	
	public static void initSummaryView() {
		
		int[] summary = InterfaceController.logicControl.getSummaryCount();
		
		//initSummaryHeader();
		initRowOne(String.valueOf(summary[0]), String.valueOf(summary[1]));
		initRowTwo(String.valueOf(summary[2]), String.valueOf(summary[3]));
		
		//Region spaceHeader = new Region();
		Region spaceTop = new Region();
		Region spaceMid = new Region();
		Region spaceBot = new Region();
		
		summaryBox = new VBox(spaceTop, rowOneBox, spaceMid, rowTwoBox, spaceBot);
		InterfaceController.summaryBox = summaryBox;
		
		//VBox.setVgrow(spaceHeader, Priority.ALWAYS);
		VBox.setVgrow(spaceTop, Priority.ALWAYS);
		VBox.setVgrow(spaceMid, Priority.ALWAYS);
		VBox.setVgrow(spaceBot, Priority.ALWAYS);
	}
	
	public static void updateSummaryView() {
		
		// Clear the old data
		taskTwoDaysBox.getChildren().clear();
		eventTwoDaysBox.getChildren().clear();
		taskFloatBox.getChildren().clear();
		eventOngoingBox.getChildren().clear();
		
		// Create the new labels
		taskTwoDaysLabel = new Label(HEADER_TASK_TWO_DAYS);
		eventTwoDaysLabel = new Label(HEADER_EVENT_TWO_DAYS);
		taskFloatLabel = new Label(HEADER_TASK_FLOAT);
		eventOngoingLabel = new Label(HEADER_EVENT_ONGOING);
		
		// Get the updated data
		int[] summary = InterfaceController.logicControl.getSummaryCount();
		
		// Update the current labels
		taskTwoDaysCount = new Label(String.valueOf(summary[0]));
		eventTwoDaysCount = new Label(String.valueOf(summary[1]));
		taskFloatCount = new Label(String.valueOf(summary[2]));
		eventOngoingCount = new Label(String.valueOf(summary[3]));
		
		// Add the labels again
		taskTwoDaysBox.getChildren().addAll(taskTwoDaysCount, taskTwoDaysLabel);
		eventTwoDaysBox.getChildren().addAll(eventTwoDaysCount, eventTwoDaysLabel);
		taskFloatBox.getChildren().addAll(taskFloatCount, taskFloatLabel);
		eventOngoingBox.getChildren().addAll(eventOngoingCount, eventOngoingLabel);
		
		// Set the margins
		VBox.setMargin(taskTwoDaysCount, new Insets(0, 0, 15, 0));
		VBox.setMargin(taskTwoDaysLabel, new Insets(0, 0, 15, 0));
		
		VBox.setMargin(eventTwoDaysCount, new Insets(0, 0, 15, 0));
		VBox.setMargin(eventTwoDaysLabel, new Insets(0, 0, 15, 0));
		
		VBox.setMargin(taskFloatCount, new Insets(0, 0, 15, 0));
		VBox.setMargin(taskFloatLabel, new Insets(0, 0, 15, 0));
		
		VBox.setMargin(eventOngoingCount, new Insets(0, 0, 15, 0));
		VBox.setMargin(eventOngoingLabel, new Insets(0, 0, 15, 0));
		
		// CSS
		taskTwoDaysCount.getStyleClass().add("summary-box-label-count");
		taskTwoDaysLabel.getStyleClass().add("summary-box-label");
		taskTwoDaysLabel.setStyle("-fx-font-family: \"Myriad Pro Light\";");
		
		eventTwoDaysCount.getStyleClass().add("summary-box-label-count");
		eventTwoDaysLabel.getStyleClass().add("summary-box-label");
		eventTwoDaysLabel.setStyle("-fx-font-family: \"Myriad Pro Light\";");
		
		taskFloatCount.getStyleClass().add("summary-box-label-count");
		taskFloatLabel.getStyleClass().add("summary-box-label");
		taskFloatLabel.setStyle("-fx-font-family: \"Myriad Pro Light\";");
		
		eventOngoingCount.getStyleClass().add("summary-box-label-count");
		eventOngoingLabel.getStyleClass().add("summary-box-label");
		eventOngoingLabel.setStyle("-fx-font-family: \"Myriad Pro Light\";");
	}
}
