//Lots of imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.*;
import java.lang.Runtime;
import java.nio.file.Files;
import java.nio.charset.Charset;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.util.Base64;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.scene.shape.Rectangle;
import java.awt.Desktop; //is it bad to mix JavaD
import java.awt.Desktop.*;
import javafx.event.*;
import java.awt.Toolkit;


//Single-class application for simplicity
public class MainFX extends Application {
    //instance variables
    BorderPane bPane = new BorderPane();
    GridPane gPane = new GridPane();
    VBox box = new VBox();
    StackPane sPane = new StackPane();
    Pane confirmPane = new Pane();
    //Only pane objects need to be initialized here
    Button hideButton;
    Button aboutButton;
    Button retrieveButton;
    Label hostFileLabel;
    Label hiddenFileLabel;
    TextField hostTextField;
    TextField hiddenTextField;
    String hostFileName;
    String hiddenFileName;
    Label hiding;
    Label retrieving;
    Label retrieveFromLabel;
    Label retrieveSaveToLabel;
    TextField retrieveFromTextField;
    TextField retrieveSaveToTextField;
    FileChooser fileChooser;
    Image topImage;
    ImageView topImageView;
    Image bottomImage;
    ImageView bottomImageView;
    Button websiteButton;
    String retrieveFromFileName;
    String retrieveSaveToFileName;
    Pane aboutPane;
    Button closeAbout;
    Button aboutOpenReadMe;
    Image confirmSymbol;
    ImageView confirmImageView;
    Button confirmButtonYes;
    Button confirmButtonNo;
    public LambdaBool proceedOrNot = new LambdaBool(false); //weird workaround, I know //actually I gave up on this idea
    boolean ANGRY = false;
    boolean proceedWithIO = false;
    Label areYouSure;
    Label noHostFileName;
    Label noHiddenFileName;
    Label noHostFileExists;
    Label noHiddenFileExists;
    Button closeErrorHide;
    Label TaskSuccess;
    Image errorImage;
    ImageView errorImageView;
    Label noRetrieveFromFileName;
    Label noRetrieveSaveToFileName;
    Label noRetrieveFromFileExists;
    Label noRetrieveSaveToFileExists;
    Label noRetrieveSaveToFileExists2;
    Label fileType1;
    Label fileType2;
    
    public static void main(String[] args) {
        Application.launch(args);
        //entry point, but do stuff in the other methods
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        //this is where the window is built, don't do anything else here
        try {
        buildUI();
        customizeUI();
        registerEvents();
        sPane.getChildren().add(bPane); //putting the BorderPane in a stack pane
        Scene scene = new Scene(sPane); //to do pseudo-pop ups by pushing to top of stackpane
        primaryStage.setScene(scene);
        primaryStage.setTitle("Alan's FileHider");
        primaryStage.setResizable(false);
        primaryStage.show();
        } catch (Exception sfsdfgsdfg) {
            System.out.println(sfsdfgsdfg.getMessage());
        }
    }
    
    public void buildUI() {
        //only UI stuff here, nothing else
        hideButton = new Button("Hide");
        retrieveButton = new Button("Retrieve");
        hideButton.setMinWidth(180);
        retrieveButton.setMinWidth(180);
        //injectButton.setMinHeight(60);
        aboutButton = new Button("About");
        //aboutButton.setPadding(new Insets(0, 10, 0, 0));
        hostFileLabel = new Label("Host file (to put file inside of): ");
        hostFileLabel.setPadding(new Insets(0, 0, 0, 10));
        hostFileLabel.setTextFill(Color.web("#24305e"));
        hiddenFileLabel = new Label("File to hide within host file: ");
        hiddenFileLabel.setPadding(new Insets(0, 0, 0, 10));
        hiddenFileLabel.setTextFill(Color.web("#24305e"));
        hostTextField = new TextField();
        hiddenTextField = new TextField();
        hiding = new Label("Hiding");
        hiding.setPadding(new Insets(0, 0, 0, 10));
        hiding.setFont(new Font("Arial", 30));
        hiding.setTextFill(Color.web("#24305e"));
        retrieving = new Label("Retrieving");
        retrieving.setPadding(new Insets(0, 0, 0, 10));
        retrieving.setFont(new Font("Arial", 30));
        retrieving.setTextFill(Color.web("#24305e"));
        retrieveFromLabel = new Label("Host file to retrieve hidden file from: ");
        retrieveFromLabel.setPadding(new Insets(0, 0, 0, 10));
        retrieveFromLabel.setTextFill(Color.web("#24305e"));
        retrieveSaveToLabel = new Label("File name to save retrieved file to: ");
        retrieveSaveToLabel.setPadding(new Insets(0, 0, 0, 10));
        retrieveSaveToLabel.setTextFill(Color.web("#24305e"));
        retrieveFromTextField = new TextField();
        retrieveSaveToTextField = new TextField();
        topImage = new Image("/assets/banner.png");
        topImageView = new ImageView(topImage);
        bottomImage = new Image("/assets/footer2.png");
        bottomImageView = new ImageView(bottomImage);
        websiteButton = new Button("Visit Alan's website");
        //to-do: convert text stuff to file choosers later?
        //or maybe not
        //fileChooser = new FileChooser();
        //fileChooser.setTitle("Open Resource File");
        //fileChooser.showOpenDialog(primaryStage);
        //is this the wrong scope?
        
        gPane.add(hiding, 0, 0);
        gPane.add(hostFileLabel, 0, 1);
        gPane.add(hiddenFileLabel, 0, 2);
        gPane.add(hostTextField, 1, 1);
        gPane.add(hiddenTextField, 1,2);
        gPane.add(hideButton, 1, 3);
        fileType1 = new Label("Host files: PNG, GIF, or JPG/JPEG only");
        fileType2 = new Label("Hidden/retrieved files: any");
        fileType1.setFont(new Font("Arial", 12));
        fileType1.setTextFill(Color.web("#24305e"));
        fileType2.setFont(new Font("Arial", 12));
        fileType2.setTextFill(Color.web("#24305e"));
        fileType1.setPadding(new Insets(0, 0, 0, 10));
        fileType2.setPadding(new Insets(0, 0, 0, 10));
        
        gPane.add(fileType1, 0, 4);
        gPane.add(fileType2, 0, 5);
        gPane.add(retrieving, 0, 6);
        //Label notFinished = new Label("(In progress)");
        //notFinished.setTextFill(Color.RED);
        //notFinished.setFont(new Font("Arial", 30));
        //gPane.add(notFinished, 1, 5);
        gPane.add(aboutButton, 3, 0);
        gPane.add(retrieveFromLabel, 0, 7);
        gPane.add(retrieveSaveToLabel, 0, 8);
        gPane.add(retrieveFromTextField, 1, 7);
        gPane.add(retrieveSaveToTextField, 1, 8);
        gPane.add(retrieveButton, 1, 9);
        
        
        bPane.setCenter(gPane);
        bPane.setTop(topImageView);
        
        box.setAlignment(Pos.CENTER);
        websiteButton.setAlignment(Pos.CENTER);
        box.getChildren().add(bottomImageView);
        box.getChildren().add(websiteButton);
        bPane.setBottom(box);

    }
    
    public void customizeUI() {
        //not very useful, could probably merge with the buildUI
        gPane.setHgap(10);
        gPane.setVgap(10);
        box.setSpacing(10);
        bPane.setPadding(new Insets(0, 0, 10, 0));
        gPane.setPadding(new Insets(0, 10, 10, 0));
        
    }
    
    public void registerEvents() throws IOException {
        //setOnAction lambda expressions
        //this is where all the button functionality goes
        //however, the hiding and retrieving buttons call functions that are defined below
        try {
        
        hideButton.setOnAction(e -> { 
            
            //bottomOutput.appendText("\nmore text");
            System.out.println("This is just a test");
            hostFileName = hostTextField.getText();
            System.out.println("histTextField length is: " + hostTextField.getLength());
            hiddenFileName = hiddenTextField.getText();
            System.out.println("hiddenTextField length is: " + hiddenTextField.getLength());
            System.out.println("hostFileName: " + hostFileName);
            System.out.println("hiddenFileName: " + hiddenFileName);
            try {
                //to-do: confirmation screens before proceeding with IO for hiding
                
                //confirmFileOperation("hiding", hostFileName, hiddenFileName);
                //boolean proceedWithIO = ANGRY;
                //System.out.println("ANGRY" + ANGRY);
                
                System.out.println("got to confirmFileOperation with hidingOrRetrieving value of:" + "hiding");
                System.out.println("host: " + hostFileName);
                System.out.println("hiddenfilename" + hiddenFileName);    
                Rectangle confirmRectangle = new Rectangle();
                confirmRectangle.setX(0);
                confirmRectangle.setY(0);
                confirmRectangle.setWidth(600);
                confirmRectangle.setHeight(600);
                confirmRectangle.setFill(Color.web("#f4f4f4"));
                confirmSymbol = new Image("/assets/confirmation.png");
                confirmImageView = new ImageView(confirmSymbol);
                confirmImageView.setX(170);
                confirmImageView.setY(50);
                boolean hiding = false;
                
                
                
                areYouSure = new Label();
                
                System.out.println("this is where the hiding confirmation goes");
                areYouSure = new Label("Are you sure you want to hide \n" + 
                                                 hiddenFileName + "\nwithin\n" + 
                                                 hostFileName + "\n?\n");
                    
                
                areYouSure.setFont(new Font("Arial", 16));
                areYouSure.setTextFill(Color.RED);
                areYouSure.setLayoutY(250);
                areYouSure.setLayoutX(10);
                confirmButtonYes = new Button("Yes");
                confirmButtonNo = new Button("No (Cancel)");
                confirmButtonYes.setPrefHeight(50);
                confirmButtonYes.setPrefWidth(50);
                confirmButtonYes.setLayoutX(170);
                confirmButtonYes.setLayoutY(400);
                
                
                confirmButtonNo.setLayoutX(270);
                confirmButtonNo.setLayoutY(400);
                confirmButtonNo.setPrefHeight(50);
                
                confirmPane.getChildren().addAll(confirmRectangle, confirmImageView, areYouSure, confirmButtonYes, confirmButtonNo);
                
                confirmButtonYes.setOnAction(fff -> { 
                    System.out.println("user chose yes");
                    changeToTrue();
                    //they want to do the IO stuff, but now you need to check the filenames and files
                    //before proceeding with the fileIOfunction() call which has some exception handling
                    //and error printing but not a lot, and it's only to the console, not the GUI
                    //confirmPane.getChildren().remove(confirmImageView);
                    //confirmPane.getChildren().remove(areYouSure);
                    //confirmPane.getChildren().remove(confirmButtonYes);
                    //confirmPane.getChildren().remove(confirmButtonNo);
                    
                    sPane.getChildren().remove(confirmPane);
                    confirmPane = null; //deleting/clearing it out for new stuff
                    confirmPane = new Pane();
                    Rectangle errorRectangle = new Rectangle();
                    errorRectangle.setX(0);
                    errorRectangle.setY(0);
                    errorRectangle.setWidth(600);
                    errorRectangle.setHeight(600);
                    errorRectangle.setFill(Color.web("#f4f4f4"));
                    
                    confirmPane.getChildren().addAll(errorRectangle);
                    
                    
                    
                    sPane.getChildren().add(confirmPane);
                    System.out.println("Removed some of the confirmation graphical elements to make way for potential errors");
                    
                    
                    
                    
                    try {
                        //to-do: IO errors in sPane for hiding=================================================================================
                        boolean anyErrors = false; //means you need to put the error image in
                        boolean anyFileNameBlank = false;
                        
                        if (hostFileName.length() == 0) {
                            System.out.println("error: hostFileName is blank");
                            anyFileNameBlank = true;
                            anyErrors = true;
                            noHostFileName = new Label("Error: hostFileName is blank");
                            noHostFileName.setLayoutY(210);
                            noHostFileName.setLayoutX(10);
                            noHostFileName.setFont(new Font("Arial", 16));
                            noHostFileName.setTextFill(Color.RED);
                            confirmPane.getChildren().add(noHostFileName);
                        }
                        if (hiddenFileName.length() == 0) {
                            System.out.println("error: hiddenFileName is blank");
                            //separate if instead of if/else because I want multiple errors to be able to be displayed
                            anyFileNameBlank = true;
                            anyErrors = true;
                            noHiddenFileName = new Label("Error: hiddenFileName is blank");
                            noHiddenFileName.setLayoutY(250);
                            noHiddenFileName.setLayoutX(10);
                            noHiddenFileName.setFont(new Font("Arial", 16));
                            noHiddenFileName.setTextFill(Color.RED);
                            confirmPane.getChildren().add(noHiddenFileName);
                        } 
                        if (anyFileNameBlank) {
                            
                        }
                        
                        File checkHostFileTemp = new File(hostFileName);
                        boolean hostExists = checkHostFileTemp.exists();
                        File checkHiddenFileTemp = new File(hiddenFileName);
                        boolean hiddenExists = checkHiddenFileTemp.exists();
                        boolean anyFileDoesNotExist = false;
                        if (!anyFileNameBlank) {
                            if (!hostExists) {
                                System.out.println("error: host file does not exist");
                                anyFileDoesNotExist = true;
                                anyErrors = true;
                                noHostFileExists = new Label("error: host file does not exist");
                                
                                noHostFileExists.setLayoutY(210);
                                noHostFileExists.setLayoutX(10);
                                noHostFileExists.setFont(new Font("Arial", 16));
                                noHostFileExists.setTextFill(Color.RED);
                                confirmPane.getChildren().add(noHostFileExists);
                            }
                            if (!hiddenExists) {
                                System.out.println("error: file to hide does not exist");
                                anyFileDoesNotExist = true;
                                anyErrors = true;
                                noHiddenFileExists = new Label("error: file to hide does not exist");
                                
                                noHiddenFileExists.setLayoutY(250);
                                noHiddenFileExists.setLayoutX(10);
                                noHiddenFileExists.setFont(new Font("Arial", 16));
                                noHiddenFileExists.setTextFill(Color.RED);
                                confirmPane.getChildren().add(noHiddenFileExists);
                            }
                        } 
                        
                        if (anyErrors) {
                            System.out.println("there was at least one error");
                            errorImage = new Image("/assets/error.png");
                            errorImageView = new ImageView(errorImage);
                            errorImageView.setX(170);
                            errorImageView.setY(50);
                            confirmPane.getChildren().add(errorImageView);
                            Toolkit.getDefaultToolkit().beep(); //make system noise to alert user of problem
                        }
                        
                        
                        if (anyFileNameBlank == false && anyFileDoesNotExist == false) {
                            System.out.println("The filenames are not blank and the files do indeed exist");
                            TaskSuccess = new Label("Task completed successfuly.");
                            TaskSuccess.setLayoutY(50);
                            TaskSuccess.setLayoutX(10);
                            TaskSuccess.setFont(new Font("Arial", 16));
                            TaskSuccess.setTextFill(Color.GREEN);
                            confirmPane.getChildren().add(TaskSuccess);
                            //Put some sort of success screen and thread.wait thing here?
                            fileIOfunction(hostFileName, hiddenFileName);
                        }
                        
                        //to-do: button for getting rid of stuff
                        //actually in the name I wrote "error" but it's useful for all stuff, not just errors
                        
                        closeErrorHide = new Button("OK");
                        closeErrorHide.setPrefWidth(100);
                        closeErrorHide.setPrefHeight(50);
                        closeErrorHide.setLayoutX(210);
                        closeErrorHide.setLayoutY(400);
                        confirmPane.getChildren().add(closeErrorHide);
                        closeErrorHide.setOnAction(qwert -> {
                            System.out.println("close error button for hide was pressed");
                            sPane.getChildren().remove(confirmPane);
                        });
                        
                    } catch (Exception ex) {
                        System.out.println("I really messed this up, huh" + ex.getMessage()); //to-do: make all exception handlers print getMessage()
                    }
                    
                    
                });
    
                
                
                
                
                confirmButtonNo.setOnAction(ggg -> { 
                    System.out.println("user chose no");
                    changeToFalse(); //this is a relic from when I was doing this program differently
                                     //in fact, there are many things I need to clean up after I redid a lot of things
                    sPane.getChildren().remove(confirmPane);
                    
                });
                sPane.getChildren().add(confirmPane);
                
                
                System.out.println("proceedWithIO: " + proceedWithIO);
                if (proceedWithIO == true) {
                    System.out.println("got to right before fileIOfunction for hideButton");
                    //fileIOfunction(hostFileName, hiddenFileName);
                } else {
                    System.out.println("proceedWithIO is false for hide attempt");
                    System.out.println("proceedWithIO: " + proceedWithIO);
                }
                System.out.println("got here");
                //Runtime.getRuntime().exec("exif.sh");
                
                //clear the fields for another use
                hostTextField.setText("");
                hiddenTextField.setText("");
            } catch (Exception whatevs) {
                System.err.println("Exception occured: " + whatevs.getMessage());
            }
            
        });
        

        
        
        aboutButton.setOnAction(e -> { 
            
            Rectangle aboutRectangle = new Rectangle();
            aboutRectangle.setX(0);
            aboutRectangle.setY(0);
            aboutRectangle.setWidth(600);
            aboutRectangle.setHeight(600);
            aboutRectangle.setFill(Color.web("#f4f4f4"));

            System.out.println("about button test");
            aboutPane = new Pane();
            
            closeAbout = new Button("Close");
            closeAbout.setLayoutX(230);
            closeAbout.setLayoutY(440);
            
            aboutOpenReadMe = new Button("View README file");
            aboutOpenReadMe.setLayoutX(300);
            aboutOpenReadMe.setLayoutY(440);
            aboutPane.getChildren().addAll(aboutRectangle,closeAbout,aboutOpenReadMe);
            Label aboutText = new Label("                                                About\n\n" 
            +"This is Alan's FileHider program. It was written in Java and uses the \n" +
            "JavaFX GUI framework. It's multi-platform but was made on macOS.\n" +
            "It lets you hide files within image files, or even retrieve files that you \n" +
            "hid in other files. Please note that the retrieve feature does not delete\n" +
            "the hidden files. It's like copy and paste, not cut and paste.\n\n"+
            "For more technical information, please take a look at the\n" +
            "README.TXT file using the button below. It will open up your default \n"+
            "text editor. Also check out SaintLouisSoftware.com for more info.\n" +
            "There is a link to SaintLouisSoftware.com in the main menu.\n" +
            "If you encounter any bugs, or have any questions, comments, or\n" +
            "suggestions, please go to Alan's website and use the contact page\n" +
            "I will get back to you whenever I can, though sometimes I am busy.\n\n"+
            "This program is provided AS IS, without warranty. Use this program at\n" +
            "your own risk. By using this program, you are agreeing to not hold me\n"+
            "responsible for anything that happens as a result. It was created for\n"+
            "fun and learning and isn't necessarily intended for serious usage.");
            aboutText.setLayoutX(10);
            aboutText.setLayoutY(10);
            aboutText.setFont(new Font("Arial", 16));
            aboutText.setTextFill(Color.web("#24305e"));
            aboutPane.getChildren().add(aboutText);
            sPane.getChildren().add(aboutPane);
            
            
            closeAbout.setOnAction(x -> {
                sPane.getChildren().remove(aboutPane);
                System.out.println("test");
            });
            
            aboutOpenReadMe.setOnAction(lambda -> {
                File file = new File("README.TXT");
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException q) {
                    q.printStackTrace();
                }
            });
            
            
        }); 
        
        websiteButton.setOnAction( e -> {
            getHostServices().showDocument("https://saintlouissoftware.com");
        });
        
        retrieveButton.setOnAction(e -> { 
            System.out.println("work in progress");
            retrieveFromFileName = retrieveFromTextField.getText();
            retrieveSaveToFileName = retrieveSaveToTextField.getText();
            System.out.println("retrieveFromFileName: " + retrieveFromFileName);
            System.out.println("retrieveSaveToFileName: " + retrieveSaveToFileName);
            
            try {
                
                //confirmFileOperation("hiding", hostFileName, hiddenFileName);
                //boolean proceedWithIO = ANGRY;
                //System.out.println("ANGRY" + ANGRY);
                //the above was part of a failed attempt at salvaging some previous code before refactoring some things
                //but in a kind of lazy way
                
                //confirmation before proceeding with IO
                System.out.println("got to confirmFileOperation with hidingOrRetrieving value of:" + "retrieving");
                System.out.println("retrieveFromFileName: " + retrieveFromFileName);
                System.out.println("retrieveSaveToFileName: " + retrieveSaveToFileName);    
                Rectangle confirmRectangle = new Rectangle();
                confirmRectangle.setX(0);
                confirmRectangle.setY(0);
                confirmRectangle.setWidth(600);
                confirmRectangle.setHeight(600);
                confirmRectangle.setFill(Color.web("#f4f4f4"));
                confirmSymbol = new Image("/assets/confirmation.png");
                confirmImageView = new ImageView(confirmSymbol);
                confirmImageView.setX(170);
                confirmImageView.setY(50);
                boolean hiding = false;
                
                //had scoping issues with functions and lambdas and code reading and writing from different places
                //so that's why I'm commenting out or deleting some things and then just copying and pasting
                //due to problems with the lambdas in the one function I wanted to use for both hiding and retrieving
                //not an ideal solution but I spent forever trying to debug the previous way
                //and it just wasn't working no matter what I tried
                //this is not ideal, but it works
                //couldn't figure out the "final or effectively final" lambda expression problem
                
                areYouSure = new Label();
                
                System.out.println("this is where the retrieving confirmation goes");
                areYouSure = new Label("Are you sure you want to retrieve a file from \n" + 
                                                 retrieveFromFileName + "\nand save it to\n" + 
                                                 retrieveSaveToFileName + "\n?\n");
                    
                
                areYouSure.setFont(new Font("Arial", 16));
                areYouSure.setTextFill(Color.RED);
                areYouSure.setLayoutY(250);
                areYouSure.setLayoutX(10);
                confirmButtonYes = new Button("Yes");
                confirmButtonNo = new Button("No (Cancel)");
                confirmButtonYes.setLayoutX(170);
                confirmButtonYes.setLayoutY(400);
                confirmButtonYes.setPrefHeight(50);
                confirmButtonNo.setPrefHeight(50);
                
                confirmButtonNo.setLayoutX(270);
                confirmButtonNo.setLayoutY(400);
                
                confirmPane.getChildren().addAll(confirmRectangle, confirmImageView, areYouSure, confirmButtonYes, confirmButtonNo);
                
                confirmButtonYes.setOnAction(fff -> { 
                    System.out.println("user chose yes retrieve");
                    changeToTrue();
                    
                    //THIS IS WHERE I PUT STUFF FOR FINAL THINGS==================================================================
                    //work on stuff below this line
                    sPane.getChildren().remove(confirmPane);
                    confirmPane = null; //deleting/clearing it out for new stuff
                    confirmPane = new Pane();
                    Rectangle errorRectangle = new Rectangle();
                    errorRectangle.setX(0);
                    errorRectangle.setY(0);
                    errorRectangle.setWidth(600);
                    errorRectangle.setHeight(600);
                    errorRectangle.setFill(Color.web("#f4f4f4"));
                    
                    confirmPane.getChildren().addAll(errorRectangle);
                    
                    
                    
                    sPane.getChildren().add(confirmPane);
                    System.out.println("Removed some of the confirmation graphical elements to make way for potential errors [retrieve]");
                    
                    
                    
                    
                    try {
                        //to-do: IO errors in sPane for retrieving=================================================================================
                        boolean anyErrors = false; //means you need to put the error image in
                        boolean anyFileNameBlank = false;
                        //replace hostFileNme with retrieveFromFileName
                        
                        //replace hiddenFileName with retrieveSaveToFileName
                        if (retrieveFromFileName.length() == 0) {
                            System.out.println("error: retrieveFromFileName is blank");
                            anyFileNameBlank = true;
                            anyErrors = true;
                            //noRetrieveFromFileName;
                            //noRetrieveSaveToFileName;
                            noRetrieveFromFileName = new Label("Error: retrieveFromFileName is blank");
                            noRetrieveFromFileName.setLayoutY(210);
                            noRetrieveFromFileName.setLayoutX(10);
                            noRetrieveFromFileName.setFont(new Font("Arial", 16));
                            noRetrieveFromFileName.setTextFill(Color.RED);
                            confirmPane.getChildren().add(noRetrieveFromFileName);
                        }
                        if (retrieveSaveToFileName.length() == 0) {
                            System.out.println("error: retrieveSaveToFileName is blank");
                            //separate if instead of if/else because I want multiple errors to be able to be displayed
                            anyFileNameBlank = true;
                            anyErrors = true;
                            noRetrieveSaveToFileName = new Label("Error: retrieveSaveToFileName is blank");
                            noRetrieveSaveToFileName.setLayoutY(250);
                            noRetrieveSaveToFileName.setLayoutX(10);
                            noRetrieveSaveToFileName.setFont(new Font("Arial", 16));
                            noRetrieveSaveToFileName.setTextFill(Color.RED);
                            confirmPane.getChildren().add(noRetrieveSaveToFileName);
                        } 
                        if (anyFileNameBlank) {
                            
                        }
                        
                        File checkRetrieveFromFileTemp = new File(retrieveFromFileName);
                        boolean retrieveFromExists = checkRetrieveFromFileTemp.exists();
                        File checkRetrieveSaveToFileTemp = new File(retrieveSaveToFileName);
                        boolean saveToExists = checkRetrieveSaveToFileTemp.exists();
                        boolean anyFileDoesNotExist = false;
                        if (!anyFileNameBlank) {
                            if (!retrieveFromExists) {
                                System.out.println("error: retrieveFromFile does not exist");
                                anyFileDoesNotExist = true;
                                anyErrors = true;
                                noRetrieveFromFileExists = new Label("error: retrieveFromFile does not exist");
                                //Label noRetrieveFromFileExists; //instead of host
                                //Label noRetrieveSaveToFileExists; //instead of hidden
                                noRetrieveFromFileExists.setLayoutY(210);
                                noRetrieveFromFileExists.setLayoutX(10);
                                noRetrieveFromFileExists.setFont(new Font("Arial", 16));
                                noRetrieveFromFileExists.setTextFill(Color.RED);
                                confirmPane.getChildren().add(noRetrieveFromFileExists);
                            }
                            if (saveToExists) {
                                System.out.println("error: file to save to ALREADY exists (can't overwrite)");
                                anyFileDoesNotExist = true;//more accurately: any file existence errors, because
                                                           //in this case you don't want the file to save to to exist
                                                           //because writing over existing files is bad
                                anyErrors = true;
                                noRetrieveSaveToFileExists = new Label("error: file to save to ALREADY exists (can't overwrite)");
                                noRetrieveSaveToFileExists2 = new Label("What if it's an important file of yours?");
                                noRetrieveSaveToFileExists.setLayoutY(250);
                                noRetrieveSaveToFileExists.setLayoutX(10);
                                noRetrieveSaveToFileExists.setFont(new Font("Arial", 16));
                                noRetrieveSaveToFileExists.setTextFill(Color.RED);
                                
                                
                                noRetrieveSaveToFileExists2.setLayoutY(290);
                                noRetrieveSaveToFileExists2.setLayoutX(10);
                                noRetrieveSaveToFileExists2.setFont(new Font("Arial", 16));
                                noRetrieveSaveToFileExists2.setTextFill(Color.RED);
                                
                                confirmPane.getChildren().add(noRetrieveSaveToFileExists);
                                confirmPane.getChildren().add(noRetrieveSaveToFileExists2);
                            }
                        } 
                        
                        if (anyErrors) {
                            System.out.println("there was at least one error");
                            errorImage = new Image("/assets/error.png");
                            errorImageView = new ImageView(errorImage);
                            errorImageView.setX(170);
                            errorImageView.setY(50);
                            confirmPane.getChildren().add(errorImageView);
                            Toolkit.getDefaultToolkit().beep(); //make system noise to alert user of problem
                        }
                        
                        
                        if (anyFileNameBlank == false && anyFileDoesNotExist == false) {
                            System.out.println("The filenames are not blank and the files do indeed exist");
                            TaskSuccess = new Label("Task completed successfuly.");
                            TaskSuccess.setLayoutY(50);
                            TaskSuccess.setLayoutX(10);
                            TaskSuccess.setFont(new Font("Arial", 16));
                            TaskSuccess.setTextFill(Color.GREEN);
                            confirmPane.getChildren().add(TaskSuccess);
                            //Put some sort of success screen and thread.wait thing here?
                            retrieveFunction(retrieveFromFileName, retrieveSaveToFileName);
                        }
                        
                        //to-do: button for getting rid of stuff
                        //actually in the name I wrote "error" but it's useful for all stuff, not just errors
                        
                        closeErrorHide = new Button("OK");
                        closeErrorHide.setPrefWidth(100);
                        closeErrorHide.setPrefHeight(50);
                        closeErrorHide.setLayoutX(210);
                        closeErrorHide.setLayoutY(400);
                        confirmPane.getChildren().add(closeErrorHide);
                        
                        
                        closeErrorHide.setOnAction(qwert -> {
                            System.out.println("close error button for hide was pressed");
                            sPane.getChildren().remove(confirmPane);
                        });
                    //try {
                    //    //ONLY DO THE STUFF ABOVE THIS LINE================================================================
                    //    retrieveFunction(retrieveFromFileName, retrieveSaveToFileName);
                    } catch (Exception ex) {
                        System.out.println("I really messed this up, huh (retrieve edition)");
                    }
                    
                    //sPane.getChildren().remove(confirmPane); //I moved this to after the try/catch block
                                                             //because it might use the confirmPane (sans some elements) for 
                                                             //IO error messages
                });
    
                
                
                
                
                confirmButtonNo.setOnAction(ggg -> { 
                    System.out.println("user chose no retrieve");
                    changeToFalse();
                    sPane.getChildren().remove(confirmPane);
                    
                });
                sPane.getChildren().add(confirmPane);
                
                
                
                
                
                System.out.println("proceedWithIO: " + proceedWithIO);
                if (proceedWithIO == true) {
                    System.out.println("got to right before fileIOfunction for hideButton");
                    //retrieveFunction(retrieveFromFileName, retrieveSaveToFileName);
                    //this if/else thing is basically useless at this point but idk if I wanna get rid of it just yet
                } else {
                    System.out.println("proceedWithIO is false for hide attempt");
                    System.out.println("proceedWithIO: " + proceedWithIO);
                }
                System.out.println("got here retrieve section");
                
                //clear the fields for another use
                retrieveFromTextField.setText("");
                retrieveSaveToTextField.setText("");
            } catch (Exception whatevs) {
                System.err.println("Exception occured: " + whatevs.getMessage());
            }
            
            
        }); 
        
        

        } catch (Exception whocares) {
            System.out.println("durrrrrrr retrieve");
        }
    }
    //The below function appends text to the end of an image file
    //this is for hiding, not retrieving
    public void fileIOfunction(String hostFile, String hiddenFile) throws IOException{
        try {
            
            File tmpFile1 = new File(hostFile);
            boolean hostExists = tmpFile1.exists();
            File tmpFile2 = new File(hiddenFile);
            boolean hiddenExists = tmpFile2.exists();
            
            if (hostExists && hiddenExists) { 

                File file = new File(hiddenFile);
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();
                
                Writer output;
                output = new BufferedWriter(new FileWriter(hostFile, true));
                
                String encodedFile = Base64.getEncoder().encodeToString(data);
                
                output.append("\n\n");
                output.append(encodedFile);
                output.close();
                
            } else {
                System.err.println("Error with filenames" + hostFile + " and/or " + hiddenFile);
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
        
    }
    
    //function called by the retrieve button lambda in registerEvents()
    public void retrieveFunction(String retrieveFile, String saveToFile) throws IOException{
        try {
            File tmpFile1 = new File(retrieveFile);
            boolean retrieveExists = tmpFile1.exists();
            File tmpFile2 = new File(saveToFile);
            boolean saveDoesNotExist = !(tmpFile2.exists()); //don't want to write over something
            //currently this only gets the file out but doesn't delete it from the file that has it hidden in it
            //it's like copy and paste, not cut and paste
            if (retrieveExists && saveDoesNotExist) { 
                String checkString = null;
                System.out.println("retrieveFile:" + retrieveFile);
                
                BufferedReader input = new BufferedReader(new FileReader(retrieveFile));
                String last = null;
                String line = null;
                while ((line = input.readLine()) != null) { 
                    last = line;
                }
                System.out.println("last: " + last);
                //last contains the base64 string because it's the last line of the file
                
                
                byte[] decodedImageByteArray = Base64.getDecoder().decode(last);
                //^decoding the last line of the host file, storing in byte array
                
                OutputStream out = null;
                
                try {
                    out = new BufferedOutputStream(new FileOutputStream(saveToFile));
                    out.write(decodedImageByteArray);
                    System.out.println("image written to path: " + saveToFile);
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
                
                
            } else {
                System.err.println("Error with filenames" + retrieveFile + " and/or " + saveToFile);
            }
            
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
    
    
    public void changeToFalse() {//workaround for lambda/final issue
        proceedOrNot.setProceed(false);
        System.out.println("changeToFalse() has been called");
        ANGRY = false;
        proceedWithIO = false;
    }
    
    public void changeToTrue() {//workaround for lambda/final issue
        proceedOrNot.setProceed(true);
        System.out.println("changeToTrue() has been called");
        ANGRY = true;
        proceedWithIO = true;
        System.out.println("proceedWithIO: " + proceedWithIO);
    }
    
    //to-do: make it so the source and destination file can't be the same thing
    //to-do: enforce image formats only for hide -- PNG, GIF, JPG, JPEG
    //to-do: enforce image formats only for retrieve -- PNG, GIF, JPG, JPEG
    //to-do: make backup of file before adding other file into it

}