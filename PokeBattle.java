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
		Label enemyLvl = new Label(String.format("lvl. %d", 50));
		ProgressBar enemyHealth = new ProgressBar();
		enemyHealth.setProgress(1);
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
		Label allyLvl = new Label(String.format("lvl. %d", 50));
		ProgressBar allyHealth = new ProgressBar();
		allyHealth.setProgress(1);
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

		//moves buttons
		Button move1 = new Button("Metal Ball");
		Button move2 = new Button("fat");
		Button move3 = new Button("fatty");
		Button move4 = new Button("fatcakes");

		//menu gridPane
      	GridPane menu = new GridPane();
		menu.setMinSize(400, 200);
		menu.setPadding(new Insets(10, 10, 10, 10));
		menu.setVgap(5); 
		menu.setHgap(5);
		menu.setAlignment(Pos.CENTER);

		//buttons
		Button fight = new Button("FIGHT");
		fight.setOnAction(e -> {
			menu.getChildren().clear();
			menu.add(move1, 0, 0);
			menu.add(move2, 1, 0);
			menu.add(move3, 0, 1);
			menu.add(move4, 1, 1);
		});
		Button bag = new Button("BAG");
		Button pokemon = new Button("POKEMON");
		Button run = new Button("RUN");

		menu.add(fight, 0, 0);
		menu.add(bag, 1, 0);
		menu.add(pokemon, 0, 1);
		menu.add(run, 1, 1);

		//options view
		VBox optionsView = new VBox();
		optionsView.setSpacing(30);
		optionsView.getChildren().addAll(pokeBox, menu);

		//sets scene to stage
		Scene scene1 = new Scene(optionsView, 800, 800);
		stage.setTitle("Pokemon Battle");
		stage.setScene(scene1);
		stage.show();
	}
}