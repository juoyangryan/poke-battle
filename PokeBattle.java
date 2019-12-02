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
import java.util.Random;
import javafx.util.Duration;
import javafx.animation.PauseTransition;


public class PokeBattle extends Application {
	GridPane menu = new GridPane();
	ProgressBar enemyHealth = new ProgressBar();
	ProgressBar allyHealth = new ProgressBar();
	Move tackle = new Move("Tackle", 20, "normal");
	Move ember = new Move("Ember", 20, "fire");
	Move fireBlast = new Move("Fire blast", 70, "fire");
	Move sleep = new Move("Sleep", 0, "normal");
	Move[] allymoves = new Move[]{tackle, ember, fireBlast, sleep};
	Pokemon allyPokemon = new Pokemon("Charizard", 50, 100, 80, "fire", allymoves);
	Pokemon currentAlly = allyPokemon;
	Pokemon enemyPokemon = new Pokemon("Charizard", 50, 100, 80, "fire", allymoves);
	Pokemon currentEnemy = enemyPokemon;
	Button fight;
	Button bag;
	Button pokemon;
	Button run;
	Label status;

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

		//enemy pokemon box items
		Label enemyName = new Label(currentEnemy.getName());
		Label enemyLvl = new Label(String.format("lvl. %d", currentEnemy.getLevel()));
		enemyHealth.setProgress(currentEnemy.getCurrentHP() / currentEnemy.getMaxHP());
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

		//ally pokemon box items
		Label allyName = new Label(currentAlly.getName());
		Label allyLvl = new Label(String.format("lvl. %d", currentAlly.getLevel()));
		allyHealth.setProgress(currentAlly.getCurrentHP() / currentAlly.getMaxHP());
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

		//menu gridPane
		menu.setMinSize(400, 200);
		menu.setPadding(new Insets(10, 10, 10, 10));
		menu.setVgap(5); 
		menu.setHgap(5);
		menu.setAlignment(Pos.CENTER);

		//Textbox
		status = new Label(String.format("What will %s do?", currentAlly.getName()));
		HBox bottomRow = new HBox();
		bottomRow.setSpacing(30);
		bottomRow.getChildren().addAll(status, menu);

		//buttons
		fight = new Button("FIGHT");
		fight.setOnAction(e -> {
			goToMoves();
			changeStatus(2);
		});
		bag = new Button("BAG");
		pokemon = new Button("POKEMON");
		run = new Button("RUN");

		menu.add(fight, 0, 0);
		menu.add(bag, 1, 0);
		menu.add(pokemon, 0, 1);
		menu.add(run, 1, 1);

		//options view
		VBox optionsView = new VBox();
		optionsView.setSpacing(30);
		optionsView.getChildren().addAll(pokeBox, bottomRow);

		//sets scene to stage
		Scene scene1 = new Scene(optionsView, 800, 800);
		stage.setTitle("Pokemon Battle");
		stage.setScene(scene1);
		stage.show();
	}

	public void goToMoves() {
		//moves buttons
		Move move1 = currentAlly.getMoves()[0];
		Move move2 = currentAlly.getMoves()[1];
		Move move3 = currentAlly.getMoves()[2];
		Move move4 = currentAlly.getMoves()[3];

		Button move1Button = new Button(move1.getName());
		move1Button.setOnAction(e -> {
			allyAttack(move1);
		});
		Button move2Button = new Button(move2.getName());
		move2Button.setOnAction(e -> {
			allyAttack(move2);
		});
		Button move3Button = new Button(move3.getName());
		move3Button.setOnAction(e -> {
			allyAttack(move3);
		});
		Button move4Button = new Button(move4.getName());
		move4Button.setOnAction(e -> {
			allyAttack(move4);
		});

		menu.getChildren().clear();
		menu.add(move1Button, 0, 0);
		menu.add(move2Button, 1, 0);
		menu.add(move3Button, 0, 1);
		menu.add(move4Button, 1, 1);
	}

	public void allyAttack(Move move) {
		int power = move.getPower();
		double dmg = currentEnemy.compareType(move) * power * (currentAlly.getLevel() / 100.0);
		currentEnemy.setCurrentHP((currentEnemy.getCurrentHP() - dmg));
		enemyHealth.setProgress(currentEnemy.getCurrentHP() / currentEnemy.getMaxHP());
		changeStatus(3, move);
		changeStatus(5, move);
		enemyAttack();
		mainMenu();
	}

	public void mainMenu() {
		menu.getChildren().clear();
		menu.add(fight, 0, 0);
		menu.add(bag, 1, 0);
		menu.add(pokemon, 0, 1);
		menu.add(run, 1, 1);
		changeStatus(1);
	}

	public void enemyAttack() {
		Random rand = new Random();
		int randInt = rand.nextInt(4);
		Move move = currentEnemy.getMoves()[randInt];
		int power = move.getPower();
		double dmg = currentAlly.compareType(move) * power * (currentEnemy.getLevel() / 100.0);
		currentAlly.setCurrentHP((currentAlly.getCurrentHP() - dmg));
		allyHealth.setProgress(currentAlly.getCurrentHP() / currentAlly.getMaxHP());
		changeStatus(4, move);
		changeStatus(6, move);
		if (currentAlly.isFainted()) {
			status.setText(String.format("%s fainted!", currentAlly.getName()));
		}
	}

	public void changeStatus(int option) {
		if (option == 1)
			status.setText(String.format("What will %s do?", currentAlly.getName()));
		else if (option == 2)
			status.setText("Choose a move.");
	}

	public void changeStatus(int option, Move move) {
		if (option == 3) {
			status.setText(String.format("%s used %s!", currentAlly.getName(), move.getName()));
		} else if (option == 4) {
			status.setText(String.format("%s used %s!", currentEnemy.getName(), move.getName()));
		} else if (option == 5) {
			//for ally moves on enemy
			double effective = currentEnemy.compareType(move);
			if (effective == 0.5)
				status.setText("It's not very effective...");
			else if (effective == 2.0)
				status.setText("It's super effective!");
		} else if (option == 6) {
			//for enemy moves on ally
			double effective = currentAlly.compareType(move);
			if (effective == 0.5)
				status.setText("It's not very effective...");
			else if (effective == 2.0)
				status.setText("It's super effective!");
		}
	}
}