/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

package controller;



import DataModel.Card;
import DataModel.Doc;
import DataModel.Group;
import DataModel.Item;
import DataModel.LanguagePair;
import DataModel.Note;
import Logic.ActualAPI;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Pair;



/**

 * FXML Controller class

 *

 * @author Hyung Kang

 */

public class UIController implements Initializable {



    @FXML

    private MenuItem menuFileNewDoc;

    @FXML

    private MenuItem menuFileNewFlashcard;

    @FXML

    private MenuItem menuFileSave;

    @FXML

    private MenuItem menuFileLoad;

    @FXML

    private MenuItem menuFileCreateNewLangPair;

    @FXML

    private MenuItem menuFileImportDoc;

    @FXML

    private MenuItem menuFileExportDoc;

    @FXML

    private MenuItem menuFileSaveExit;

    @FXML

    private MenuItem menuEditText;

    @FXML

    private MenuItem menuEditFlashcard;

    @FXML

    private MenuItem menuEditNote;

    @FXML

    private MenuItem menuViewCurrentDeck;

    @FXML

    private MenuItem menuViewQuizSetting;

    @FXML

    private MenuItem menuViewQuiz;

    @FXML

    private MenuItem menuHelpAbout;

    @FXML

    private TextArea textAreaMain;

    @FXML

    private TreeView<Item> treeViewMain;

    @FXML

    private TableView<Card> cardsTableView;

    @FXML

    private TableView<Note> notesTableView;

    @FXML

    private TableColumn<TestItem, String> cardsWordColumn;

    @FXML

    private TableColumn<TestItem, String> cardsDefinitionColumn;

    @FXML

    private TableColumn<TestItem, String> notesIndexColumn;

    @FXML

    private TableColumn<TestItem, String> notesNoteColumn;


    // other variables

    private File openedFile;

    private File importedFile;
    
    private TreeItem<Item> root;


    /**

     * Initializes the controller class.

     */

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("INIT");

        textAreaMain.setWrapText(true);
        
        // don't let user edit the text-area
        textAreaMain.setEditable(false);

        buildTreeView();

        treeViewMain.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        

    }    
    //TODO Actually implement any method with a println


    @FXML
    //TODO Implement Hyung Kang
    private void menuFileNewDocEvent(ActionEvent event) {
        
        TreeItem<Item> newItem = treeViewMain.getSelectionModel().getSelectedItem();
        
        // check if treeview is selected or not
        if (newItem == null || newItem.getValue() instanceof LanguagePair == false) {
            popUpDialog("Please select the language pair first from the tree view.");
        } else if (newItem.getValue() instanceof LanguagePair) {
            System.out.println("File -> New Document");
            // add new doc to the current language pair
            // New dialog
            // ....
            // pop-up window
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("New Document");
            dialog.setHeaderText("Create new Document");
            
            // button
            ButtonType buttonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            
            // text-field
            TextField newDocName = new TextField();
            
            grid.add(new Label("New Document Name:"), 0, 0);
            grid.add(newDocName, 1, 0);
            dialog.getDialogPane().setContent(grid);
            
            // request focus on the native field by default
            Platform.runLater(() -> newDocName.requestFocus());
            
            Optional<String> result = dialog.showAndWait();
            
            if (result.isPresent()) {
                TreeItem<Item> targetLanguagePair = treeViewMain.getSelectionModel().getSelectedItem();
                
            }
        }
    }



    @FXML
    //TODO Implement Ethan Swistak
    private void menuFileNewFlashcardEvent(ActionEvent event) {

        System.out.println("File -> New Falshcard");

    }



    @FXML
    //TODO Implement Matt Rieser
    private void menuFileSaveEvent(ActionEvent event) {

        System.out.println("File -> Save");

    }



    @FXML
    //TODO Implement Matt Rieser
    private void menuFileLoadEvent(ActionEvent event) {

        System.out.println("File -> Load");

        openedFile = openFileExplorer();

        

        System.out.println("LOAD: " + openedFile.getAbsolutePath());

    }

    

    @FXML
    //TODO Implement Hyung Kang
    private void menuFileCreateNewLangPairEvent(ActionEvent event) {
        LanguagePair newLangPair = new LanguagePair();
      
        // pop-up window
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("New Language Pair");
        dialog.setHeaderText("Create New Language Pair");
        
        // button
        ButtonType buttonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        // text-field for native and target variables
        TextField nativeTextField = new TextField();
        nativeTextField.setPromptText("Native");
        TextField targetTextField = new TextField();
        targetTextField.setPromptText("Target");
        
        grid.add(new Label("Native:"), 0, 0);
        grid.add(nativeTextField, 1, 0);
        grid.add(new Label("Target:"), 0, 1);
        grid.add(targetTextField, 1, 1);
        dialog.getDialogPane().setContent(grid);
        
        // request focus on the native field by defualt
        Platform.runLater(() -> nativeTextField.requestFocus());
        
        // convert the result to a native-target when the ok button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonType) {
                return new Pair<>(nativeTextField.getText(), targetTextField.getText());
            }
            return null;
        });
        
        Optional<Pair<String, String>> result = dialog.showAndWait();
        
        // check result
        result.ifPresent((Pair<String, String> pair) -> {
            // check if text-field is empty
            if (pair.getKey().isEmpty() || pair.getValue().isEmpty()) {
                System.out.println("Empty Field N/A!");
                popUpDialog("Please enter the native and target language.");
            } else {
                // set new language pair
                newLangPair.setNat(pair.getKey());
                newLangPair.setTarget(pair.getValue());
                
                TreeItem<Item> newPair = new TreeItem<>(newLangPair);
                root.getChildren().add(newPair);
   
                System.out.println("Native: " + pair.getKey() + "\nTarget: " + pair.getValue());
            }
        });
        
        System.out.println("File -> Create New Language Pair");
    }

    @FXML
    //TODO Implement Matt Rieser
    private void menuFileImportDocEvent(ActionEvent event) {

        System.out.println("File -> Import Document");

        importedFile = openFileExplorer();

        

        System.out.println("IMPORT: " + importedFile.getAbsolutePath());

    }

    

    @FXML
    //TODO Implement Matt Rieser
    private void menuFileExportDocEvent(ActionEvent event) {

        System.out.println("File -> Export Document");

    }



    @FXML
    //TODO Implement Matt Rieser
    private void menuFileSaveExitEvent(ActionEvent event) {

        System.out.println("File -> Save & Exit");

    }

    

    @FXML
    //TODO Implement Hyung Kang
    private void menuEditTextEvent(ActionEvent event) {
        if (textAreaMain.getText().isEmpty()) {
            popUpDialog("Please select the text first.");
            return;
        }
        
        // pop-up window
        Dialog<String> dialog = new Dialog();
        dialog.setTitle("Edit Text");
        dialog.setHeaderText("Edit Text");

        // button
        ButtonType buttonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // text-area
        TextArea newTextArea = new TextArea();
        newTextArea.setWrapText(true);
        newTextArea.setText(textAreaMain.getText());
        
        grid.add(newTextArea, 0, 0);
        dialog.getDialogPane().setContent(grid);

        // request focus on the native field by defualt
        Platform.runLater(() -> newTextArea.requestFocus());
        
        Optional<String> result = dialog.showAndWait();
        
        
        if (result.isPresent() && !newTextArea.getText().isEmpty()) {
            // update the current text document 
            Doc newDoc = (Doc) treeViewMain.getSelectionModel().getSelectedItem().getValue();
            newDoc.setText(newTextArea.getText());
            textAreaMain.setText(newDoc.getText());
            
            System.out.println("New Text:\n" + newTextArea.getText());
        } else {
            System.out.println("Edit text field is empty.");
            popUpDialog("Text field is empty");
            return;
        }

        System.out.println("Edit -> Text");
    }


    @FXML
    //TODO Implement Ethan Swistak
    private void menuEditFlashcardEvent(ActionEvent event) {

        System.out.println("Edit -> Edit Flashcard");

    }



    @FXML
    //TODO Implement Hyung Kang, we don't yet have a note edit screen, should build one
    private void menuEditNoteEvent(ActionEvent event) {

        System.out.println("Edit -> Note");
    }



    @FXML
    //TODO Implement Tim Waite
    private void menuViewCurrentDeckEvent(ActionEvent event) {

        System.out.println("View -> Current Deck");

    }



    @FXML

    private void menuViewQuizSettingEvent(ActionEvent event) {

        System.out.println("View -> Quiz Setting");

    }



    @FXML
    //TODO Tim
    private void menuViewQuizEvent(ActionEvent event) {

        System.out.println("View -> Quiz");
        //Get the parent of document being viewed or parent of group selected
        //pass to getallcards
        //pass result to quiz controller
    }



    @FXML

    private void menuHelpAboutEvent(ActionEvent event) {

        System.out.println("Help -> About");

    }

    

    @FXML

    private void treeViewMainSelectedEvent(MouseEvent event) {

        treeViewMain.getSelectionModel().selectedItemProperty().

            addListener(((observable, oldValue, newValue) -> handleTreeViewMain(newValue)));

    }
    
    // new notes tableview event
    @FXML
    
    private void notesTableViewSelectedEvent(MouseEvent event) {
        notesTableView.getSelectionModel().selectedItemProperty().
                
            addListener(((observable, oldValue, newValue) -> handleNotesTableView(newValue)));
    }

    
    // treeview handling

    private void handleTreeViewMain(TreeItem<Item> newValue) {
        
        if(newValue.getValue() instanceof Doc){
            
            Doc doc = (Doc)newValue.getValue();

            textAreaMain.setText(doc.getText());
            createCardTableView(doc);
            createNoteTableView(doc);
        }

    }
    
    // need to work on this method after Note-edit screen
    private void handleNotesTableView(Note newValue) {
        
    }

   // open file explorer

    private File openFileExplorer() {

        FileChooser fc = new FileChooser();

        File file = fc.showOpenDialog(null);

        

        if (file == null) {

            System.out.println("File N/A!");

            popUpDialog("Please select the file.");

            file = openFileExplorer();

        }

        

        return file;

    }

    




    private void buildTreeView() {
        
        /* I made a root private instance variable so that when
        "Create new Language Pair" is pressed, new language pair can be
        added into root treeview. -Hyung Kang-
        */
        root = new TreeItem(ActualAPI.getInstance().getUser());
        
        for(LanguagePair langPair : ActualAPI.getInstance().getLangPair()){
            TreeItem<Item> langItem = new TreeItem(langPair);
            root.getChildren().add(langItem);
            for(Group group : ActualAPI.getInstance().getGroups(langPair)){
                TreeItem<Item> groupItem = new TreeItem(group);
                langItem.getChildren().add(groupItem);
                for(Doc doc : ActualAPI.getInstance().getDocuments(group)){
                    TreeItem<Item> docItem = new TreeItem(doc);
                    groupItem.getChildren().add(docItem);
                }
            }
        }
        
        treeViewMain.setRoot(root);
        treeViewMain.setShowRoot(false);

    }





    // testing tableView

    private void createCardTableView(Doc doc) {

        cardsWordColumn.setCellValueFactory(new Callback(){
            @Override
            public Object call(Object obj){
                final Object dataObj = ((TableColumn.CellDataFeatures) obj).getValue();
                if(dataObj instanceof Card){
                    return new ReadOnlyStringWrapper(String.valueOf(((Card)dataObj).getWordAsAppears()));
                }
                return null;
            }
        });

        cardsDefinitionColumn.setCellValueFactory(new Callback(){
            @Override
            public Object call(Object obj){
                final Object dataObj = ((TableColumn.CellDataFeatures) obj).getValue();
                if(dataObj instanceof Card){
                    return new ReadOnlyStringWrapper(String.valueOf(((Card)dataObj).getTransInContext()));
                }
                return null;
                }
        });

        cardsTableView.setItems(getCardsData(doc));

    }

    

    private void createNoteTableView(Doc doc) {

        notesIndexColumn.setCellValueFactory(new Callback(){
            @Override
            public Object call(Object obj){
                final Object dataObj = ((TableColumn.CellDataFeatures) obj).getValue();
                if(dataObj instanceof Note){
                    return new ReadOnlyStringWrapper(String.valueOf(((Note)dataObj).getIndex()));
                }
                return null;
                }
        });

                        

        notesNoteColumn.setCellValueFactory(new Callback(){
            @Override
            public Object call(Object obj){
                final Object dataObj = ((TableColumn.CellDataFeatures) obj).getValue();
                if(dataObj instanceof Note){
                    return new ReadOnlyStringWrapper(String.valueOf(((Note)dataObj).getContent()));
                }
                return null;
                }
        });

                      

        notesTableView.setItems(getNotesData(doc));

    }

    

    private ObservableList<Card> getCardsData(Doc doc) {

        ObservableList<Card> data = FXCollections.observableArrayList();

        List<Card> cards = ActualAPI.getInstance().getCards(doc);
        
        data.addAll(cards);

        return data;

    }



    private ObservableList<Note> getNotesData(Doc doc) {

        ObservableList<Note> data = FXCollections.observableArrayList();

        List<Note> notes = ActualAPI.getInstance().getNote(doc);
        
        data.addAll(notes);

        return data;

    }

    private void popUpDialog(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Error");

            alert.setHeaderText(null);

            alert.setContentText(content);

            alert.showAndWait();
    }
}
