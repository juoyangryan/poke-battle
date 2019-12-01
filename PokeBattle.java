//I worked on the homework assignment alone, using only course materials.

/**
 * @author jchen824
 * @version 1.0
 */
//javac --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls PokeBattle.java
//java --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls PokeBattle
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;


public class PokeBattle extends Application {
	public void start (Stage stage) throws FileNotFoundException {
		//first pokemon
		Image elon = new Image(new FileInputStream("elon.jpg"));
		ImageView image = new ImageView(elon);
		image.setFitHeight(200);
		image.setPreserveRatio(true);

		//second pokemon
		Image elon2 = new Image(new FileInputStream("elon.jpg"));
		ImageView image2 = new ImageView(elon2);
		image2.setFitHeight(200);
		image2.setPreserveRatio(true);

		//first pokemon box items
		Label enemyName = new Label("Elon Musk");
		Label enemyLvl = new Label(String.format("lvl. %d", 12));
		ProgressBar enemyHealth = new ProgressBar();
		enemyHealth.setProgress(0.25);
		enemyHealth.setStyle("-fx-accent: green;");

		//enemy text
		HBox enemyText = new HBox();
		enemyText.setSpacing(10);
		enemyText.getChildren().addAll(enemyName, enemyLvl);

		//EnemyBox
		VBox enemyBox = new VBox();
		enemyBox.setSpacing(10);
		enemyBox.getChildren().addAll(enemyText, enemyHealth);

		//Enemy
		HBox enemy = new HBox();
		enemy.setSpacing(10);
		enemy.setAlignment(Pos.CENTER);
		enemy.getChildren().addAll(enemyBox, image);

		//first pokemon box items
		Label allyName = new Label("Elon Musk");
		Label allyLvl = new Label(String.format("lvl. %d", 12));
		ProgressBar allyHealth = new ProgressBar();
		allyHealth.setProgress(0.25);
		allyHealth.setStyle("-fx-accent: green;");

		//ally text
		HBox allyText = new HBox();
		allyText.setSpacing(10);
		allyText.getChildren().addAll(allyName, allyLvl);

		//allyBox
		VBox allyBox = new VBox();
		allyBox.setSpacing(10);
		allyBox.getChildren().addAll(allyText, allyHealth);

		//ally
		HBox ally = new HBox();
		ally.setAlignment(Pos.CENTER);
		ally.setSpacing(10);
		ally.getChildren().addAll(image2, allyBox);

		//pokemon vbox
		VBox pokeBox = new VBox();
		pokeBox.setSpacing(40);
		pokeBox.getChildren().addAll(enemy, ally);

		//buttons
		Button fight = new Button("FIGHT");
		//fight.setOnAction(e -> )
		Button bag = new Button("BAG");
		Button pokemon = new Button("POKEMON");
		Button run = new Button("RUN");

		//buttons array
		// HBox topMenu = new HBox();
		// topMenu.setSpacing(10);
		// topMenu.getChildren().addAll(fight, bag);
		// HBox botMenu = new HBox();
		// botMenu.setSpacing(10);
		// botMenu.getChildren().addAll(pokemon, run);
		// VBox menu = new VBox();
		// menu.setSpacing(10);
		// menu.getChildren().addAll(topMenu, botMenu);

		//Creating a Grid Pane 
      	GridPane gridPane = new GridPane();    

		//Setting size for the pane  
		gridPane.setMinSize(400, 200); 

		//Setting the padding  
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 

		//Setting the vertical and horizontal gaps between the columns 
		gridPane.setVgap(5); 
		gridPane.setHgap(5);       

		//Setting the Grid alignment 
		gridPane.setAlignment(Pos.CENTER); 

		//Arranging all the nodes in the grid 
		gridPane.add(fight, 0, 0);
		gridPane.add(bag, 1, 0);
		gridPane.add(pokemon, 0, 1);
		gridPane.add(run, 1, 1);

		VBox finalView = new VBox();
		finalView.setSpacing(30);
		finalView.getChildren().addAll(pokeBox, gridPane);

		//sets scene to stage
		Scene scene = new Scene(finalView, 800, 800);
		stage.setTitle("Pokemon Battle");
		stage.setScene(scene);
		stage.show();
	}
}