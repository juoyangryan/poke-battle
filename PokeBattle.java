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

public class PokeBattle extends Application {
	GridPane menu = new GridPane();
	ProgressBar enemyHealth = new ProgressBar();
	ProgressBar allyHealth = new ProgressBar();
	Move tackle = new Move("Tackle", 20, "normal");
	Move ember = new Move("Ember", 20, "fire");
	Move fireBlast = new Move("Fire blast", 70, "fire");
	Move tickle = new Move("Tickle", 10, "normal");
	Move[] charmanderMoves = new Move[]{tackle, ember, fireBlast, tickle};

	Move vineWhip = new Move("Vine whip", 20, "grass");
	Move razorLeaf = new Move("Razor leaf", 70, "grass");
	Move[] bulbasaurMoves = new Move[]{tackle, vineWhip, razorLeaf, tickle};

	Pokemon allyPokemon = new Pokemon("Charmander", 50, 100, 80, "FIRE", charmanderMoves);
	Pokemon currentAlly = allyPokemon;
	Pokemon enemyPokemon = new Pokemon("Bulbasaur", 40, 80, 70, "GRASS", bulbasaurMoves);
	Pokemon currentEnemy = enemyPokemon;
	Button fight;
	Button bag;
	Button pokemon;
	Button run;
	Label status;

	public void start (Stage stage) throws FileNotFoundException {
		//first pokemon
		Image bulbasaur = new Image(new FileInputStream("bulbasaur.png"));
		ImageView image = new ImageView(bulbasaur);
		image.setFitHeight(150);
		image.setPreserveRatio(true);

		//second pokemon
		Image charmander = new Image(new FileInputStream("charmander.png"));
		ImageView image2 = new ImageView(charmander);
		image2.setFitHeight(250);
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

		//enemy health box
		HBox enemyHealthBox = new HBox();
		Label hp1 = new Label("HP: ");
		enemyHealthBox.getChildren().addAll(hp1, enemyHealth);


		//EnemyBox
		VBox enemyBox = new VBox();
		enemyBox.setSpacing(10);
		enemyBox.getChildren().addAll(enemyText, enemyHealthBox);

		//Enemy
		HBox enemy = new HBox();
		enemy.setSpacing(10);
		enemy.setAlignment(Pos.CENTER);
		enemy.getChildren().addAll(enemyBox, image);
		enemy.setId("enemy");

		//ally pokemon box items
		Label allyName = new Label(currentAlly.getName());
		Label allyLvl = new Label(String.format("lvl. %d", currentAlly.getLevel()));
		allyHealth.setProgress(currentAlly.getCurrentHP() / currentAlly.getMaxHP());
		allyHealth.setStyle("-fx-accent: green;");

		//ally Health box
		HBox allyHealthBox = new HBox();
		Label hp2 = new Label("HP: ");
		allyHealthBox.getChildren().addAll(hp2, allyHealth);

		//ally text
		HBox allyText = new HBox();
		allyText.setSpacing(10);
		allyText.getChildren().addAll(allyName, allyLvl);

		//allyBox
		VBox allyBox = new VBox();
		allyBox.setSpacing(10);
		allyBox.getChildren().addAll(allyText, allyHealthBox);

		//ally
		HBox ally = new HBox();
		ally.setAlignment(Pos.CENTER);
		ally.setSpacing(10);
		ally.getChildren().addAll(image2, allyBox);
		ally.setId("ally");

		//pokemon vbox
		VBox pokeBox = new VBox();
		pokeBox.setSpacing(40);
		pokeBox.getChildren().addAll(enemy, ally);
		pokeBox.setId("pokeBox");

		//menu gridPane
		menu.setMinSize(400, 200);
		menu.setPadding(new Insets(10, 10, 10, 10));
		menu.setVgap(5); 
		menu.setHgap(5);
		menu.setAlignment(Pos.CENTER);

		//Textbox
		status = new Label(String.format("What will %s do?", currentAlly.getName()));
		status.setId("status");
		menu.setId("menu");
		HBox bottomRow = new HBox();
		bottomRow.setSpacing(30);
		bottomRow.getChildren().addAll(status, menu);
		bottomRow.setId("bottomRow");

		//buttons
		fight = new Button("FIGHT");
		fight.setOnAction(e -> {
			goToMoves();
			changeStatus(2);
		});
		bag = new Button("BAG");
		pokemon = new Button("POKEMON");
		run = new Button("RUN");
		run.setOnAction(e -> {
			status.setText("Got away safely!");
			System.exit(0);
		});

		menu.add(fight, 0, 0);
		menu.add(bag, 1, 0);
		menu.add(pokemon, 0, 1);
		menu.add(run, 1, 1);

		//options view
		VBox optionsView = new VBox();
		optionsView.setSpacing(30);
		optionsView.getChildren().addAll(pokeBox, bottomRow);

		//sets scene to stage
		Scene scene = new Scene(optionsView, 800, 800);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Pokemon Battle");
		stage.setScene(scene);
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
		// Runnable allyAttackStatus = new Runnable() {
		// 	public void run() {
		// 		changeStatus(3, move);
		// 	}
		// };
		// scheduler.schedule(allyAttackStatus, 1, TimeUnit.SECONDS);
		changeStatus(3, move);
		changeStatus(5, move);
		if (currentEnemy.isFainted()) {
			status.setText(String.format("%s fainted!", currentEnemy.getName()));
			System.out.println(String.format("%s fainted!", currentEnemy.getName()));
			System.exit(0);
		}
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
			System.out.println(String.format("%s fainted!", currentAlly.getName()));
			System.exit(0);
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