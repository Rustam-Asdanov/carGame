package sample;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Rectangle wallLeftSmall;

    @FXML
    private AnchorPane menu;

    @FXML
    private Label infoGame;

    @FXML
    private Rectangle wallDown;

    @FXML
    private Rectangle wallUp;

    @FXML
    private Rectangle wallBottomSmall;

    @FXML
    private Rectangle wallRightSmall;

    @FXML
    private Circle player = new Circle();

    @FXML
    private Rectangle wallLeft;

    @FXML
    private Rectangle wallRight;

    @FXML
    private Rectangle home;

    @FXML
    private RadioButton redRadio;

    @FXML
    private RadioButton yellowRadio;

    @FXML
    private Button continueBut;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private CheckBox hardCheck;

    public void exitGameButton(ActionEvent actionEvent) {
        Stage stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    ObservableList<Rectangle> walls = FXCollections.observableArrayList();
    @FXML
    void initialize() {
        Collections.addAll(walls, wallUp,wallDown,wallLeft,wallRight,wallLeftSmall,wallBottomSmall,wallRightSmall);
        infoGame.setVisible(false);
        //radio button
        ToggleGroup color = new ToggleGroup();
        redRadio.setToggleGroup(color);
        yellowRadio.setToggleGroup(color);
    }

    public void check(){
        for(Rectangle elem : walls){
            if(Rectangle.intersect(player,elem).getBoundsInParent().getWidth() > 0){
                menu.setVisible(true);
                infoGame.setText("Game over!");
                infoGame.setVisible(true);
                continueBut.setDisable(true);
            } else if(Rectangle.intersect(player,home).getBoundsInParent().getWidth() > 40){
                menu.setVisible(true);
                infoGame.setText("You Win!");
                infoGame.setVisible(true);
                continueBut.setDisable(true);
            }
        }
    }

    public void playerStart(Boolean start){
        if(start){
            player.setLayoutX(195.0);
            player.setLayoutY(200.0);
        }
        menu.setVisible(false);
        player.requestFocus();

        player.setOnKeyPressed( (e) -> {
            if(e.getCode() == KeyCode.W && !menu.isVisible() ) {
                player.setLayoutY(player.getLayoutY() - 5);
            } else if(e.getCode() == KeyCode.S && !menu.isVisible()){
                player.setLayoutY(player.getLayoutY() + 5);
            } else if(e.getCode() == KeyCode.A && !menu.isVisible()){
                player.setLayoutX(player.getLayoutX() - 5);
            } else if(e.getCode() == KeyCode.D && !menu.isVisible()){
                player.setLayoutX(player.getLayoutX() + 5);
            } else if(e.getCode() == KeyCode.ESCAPE){
                menu.setVisible(true);
                infoGame.setText("Stop game.");
                infoGame.setVisible(true);
                continueBut.setDisable(false);
            }
            check();
        });
    }

    public void stratGame(ActionEvent actionEvent) {
        player.setRadius(27);
        if(hardCheck.isSelected()) player.setRadius(40);

        if(redRadio.isSelected()){
            player.setFill(Color.RED);
            playerStart(true);
        }
        else if(yellowRadio.isSelected()){
            player.setFill(Color.YELLOW);
            playerStart(true);
        } else {
            infoGame.setVisible(true);
            infoGame.setText("Choose color");
        }
    }

    public void continueGameButton(ActionEvent actionEvent) {
        playerStart(false);
    }
}
