//I worked on the homework assignment alone, using only course materials.

//javac --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls PokeBattle.java
//java --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls PokeBattleprivate static
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import java.util.Random;

/**
 * @author jchen824
 * @version 1.0
 */

public class PokeBattle extends Application {
    private static GridPane menu = new GridPane();
    private static ProgressBar enemyHealth = new ProgressBar();
    private static ProgressBar allyHealth = new ProgressBar();
    private static Move tackle = new Move("Tackle", 20, "NORMAL");
    private static Move ember = new Move("Ember", 20, "FIRE");
    private static Move fireBlast = new Move("Fire blast", 70, "FIRE");
    private static Move tickle = new Move("Tickle", 10, "NORMAL");
    private static Move vineWhip = new Move("Vine whip", 20, "GRASS");
    private static Move bubble = new Move("Bubble", 20, "WATER");
    private static Move bubblebeam = new Move("Bubblebeam", 50, "WATER");
    private static Move hydropump = new Move("Hydropump", 70, "WATER");
    private static Move razorLeaf = new Move("Razor leaf", 70, "GRASS");
    private static Move wingAttack = new Move("Wing Attack", 70, "FLYING");
    private static Move peck = new Move("Peck", 30, "FLYING");
    private static Move thunderbolt = new Move("Thunderbolt", 40, "ELECTRIC");
    private static Move thunder = new Move("Thunder", 70, "ELECTRIC");
    private static Move psychic = new Move("Psychic", 70, "PSYCHIC");
    private static Move psyshock = new Move("Psyshock", 20, "PSYCHIC");
    private static Move icefang = new Move("Ice Fang", 20, "ICE");
    private static Move blizzard = new Move("Blizzard", 20, "ICE");

    private static Move[] charmanderMoves = new Move[]{tackle, ember, fireBlast, tickle};
    private static Move[] bulbasaurMoves = new Move[]{tackle, vineWhip, razorLeaf, tickle};
    private static Move[] squirtleMoves = new Move[]{tackle, bubble, bubblebeam, hydropump};
    private static Move[] victreebellMoves = new Move[]{tackle, vineWhip, razorLeaf, tickle};
    private static Move[] pidgeyMoves = new Move[]{tackle, wingAttack, peck, tickle};
    private static Move[] pikachuMoves = new Move[]{tackle, thunderbolt, thunder, tickle};
    private static Move[] mrmimeMoves = new Move[]{tackle, psychic, psyshock, tickle};
    private static Move[] suicuneMoves = new Move[]{tackle, icefang, blizzard, tickle};

    private static Pokemon charmander = new Pokemon("Charmander", 50, 100, 80,
        "FIRE", "charmander.png", charmanderMoves);
    private static Pokemon bulbasaur = new Pokemon("Bulbasaur", 40, 80, 70, "GRASS", "bulbasaur.png", bulbasaurMoves);
    private static Pokemon squirtle = new Pokemon("Squirtle", 50, 100, 80,
        "WATER", "squirtle.png", squirtleMoves);
    private static Pokemon victreebell = new Pokemon("Victreebell", 50, 100, 80,
        "GRASS", "victreebell.png", victreebellMoves);
    private static Pokemon pidgey = new Pokemon("Pidgey", 50, 100, 80, "FLYING", "pidgey.png", pidgeyMoves);
    private static Pokemon pikachu = new Pokemon("Pikachu", 50, 100, 80, "ELECTRIC", "pikachu.png", pikachuMoves);
    private static Pokemon mrmime = new Pokemon("Mr. Mime", 50, 100, 80, "PSYCHIC", "mrmime.png", mrmimeMoves);
    private static Pokemon suicune = new Pokemon("Suicune", 50, 100, 80, "ICE", "suicune.png", suicuneMoves);
    private static Pokemon[] enemyList = {bulbasaur, squirtle, pidgey, suicune};
    private static Pokemon[] allyList = {charmander, victreebell, mrmime, pikachu};
    private static Pokemon currentAlly = allyList[0];
    private static Pokemon currentEnemy = enemyList[0];

    private static Image enemyImg;
    private static ImageView image;
    private static Image allyImg;
    private static ImageView image2;
    private static Button fight;
    private static Button bag;
    private static Button pokemon;
    private static Button run;
    private static Button potion;
    private static Button continueMenuButton;
    private static int potionTotal = 5;
    private static Label status;
    private static Label enemyName;
    private static Label enemyLvl;
    private static Label allyName;
    private static Label allyLvl;
    private static Button poke1 = new Button(allyList[0].getName());
    private static Button poke2 = new Button(allyList[1].getName());
    private static Button poke3 = new Button(allyList[2].getName());
    private static Button poke4 = new Button(allyList[3].getName());
    private static String addedToMain;
    private static Button exitButton = new Button("EXIT");
    private static Button exitButton2 = new Button("EXIT");
    private static VBox optionsView;
    private static Label win;
    private static Label lose;

    /**
    * @param stage to be added
    **/

    public void start(Stage stage) throws FileNotFoundException {
        //first pokemon
        enemyImg = new Image(new FileInputStream(currentEnemy.getImage()));
        image = new ImageView(enemyImg);
        image.setFitHeight(150);
        image.setPreserveRatio(true);

        //second pokemon
        allyImg = new Image(new FileInputStream(currentAlly.getImage()));
        image2 = new ImageView(allyImg);
        image2.setFitHeight(250);
        image2.setPreserveRatio(true);

        //enemy pokemon box items
        enemyName = new Label(currentEnemy.getName());
        enemyLvl = new Label(String.format("lvl. %d", currentEnemy.getLevel()));
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
        allyName = new Label(currentAlly.getName());
        allyLvl = new Label(String.format("lvl. %d", currentAlly.getLevel()));
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
        fight.setMinWidth(100);
        bag = new Button("BAG");
        bag.setOnAction(e -> {
                itemView();
            });
        bag.setMinWidth(100);
        pokemon = new Button("POKEMON");
        pokemon.setOnAction(e -> {
                changeAllyPokemon();
            });
        pokemon.setMinWidth(100);
        run = new Button("RUN");
        run.setMinWidth(100);
        run.setOnAction(e -> {
                status.setText("Got away safely!");
                System.exit(0);
            });

        menu.add(fight, 0, 0);
        menu.add(bag, 1, 0);
        menu.add(pokemon, 0, 1);
        menu.add(run, 1, 1);

        //options view
        optionsView = new VBox();
        optionsView.setId("optionsView");
        optionsView.setSpacing(30);
        optionsView.getChildren().addAll(pokeBox, bottomRow);

        //sets scene to stage
        Scene scene = new Scene(optionsView, 800, 800);
        scene.getStylesheets().add("application.css");
        stage.setTitle("Pokemon Battle");
        stage.setScene(scene);
        stage.show();
    }

    /**
    */

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
        move1Button.setMinWidth(100);
        Button move2Button = new Button(move2.getName());
        move2Button.setOnAction(e -> {
                allyAttack(move2);
            });
        move2Button.setMinWidth(100);
        Button move3Button = new Button(move3.getName());
        move3Button.setOnAction(e -> {
                allyAttack(move3);
            });
        move3Button.setMinWidth(100);
        Button move4Button = new Button(move4.getName());
        move4Button.setOnAction(e -> {
                allyAttack(move4);
            });
        move4Button.setMinWidth(100);

        menu.getChildren().clear();
        menu.add(move1Button, 0, 0);
        menu.add(move2Button, 1, 0);
        menu.add(move3Button, 0, 1);
        menu.add(move4Button, 1, 1);
    }

    /**
    * @param move to be added
    **/

    public void allyAttack(Move move) {
        int power = move.getPower();
        double dmg = currentEnemy.compareType(move) * power * (currentAlly.getLevel() / 100.0);
        currentEnemy.setCurrentHP((currentEnemy.getCurrentHP() - dmg));
        enemyHealth.setProgress(currentEnemy.getCurrentHP() / currentEnemy.getMaxHP());
        changeStatus(3, move);
        changeStatus(5, move);
        if (currentEnemy.isFainted()) {
            addedToMain += (String.format("\n%s fainted!", currentEnemy.getName()));
            //select next enemy
            boolean found = false;
            for (Pokemon eachPokemon: enemyList) {
                if (!eachPokemon.isFainted()) {
                    found = true;
                    currentEnemy = eachPokemon;
                    addedToMain += (String.format("\nEnemy sent out %s", currentEnemy.getName()));
                    try {
                        enemyImg = new Image(new FileInputStream(currentEnemy.getImage()));
                    } catch (Exception e) {
                        System.out.println("fat");
                    }
                    image.setImage(enemyImg);
                    enemyName.setText(currentEnemy.getName());
                    enemyLvl.setText(String.format("lvl. %d", currentEnemy.getLevel()));
                    enemyHealth.setProgress(currentEnemy.getCurrentHP() / currentEnemy.getMaxHP());
                    break;
                }
            }
            if (!found) {
                //win scene
                win = new Label("YOU WON!!!");
                exitButton.setOnAction(e -> {
                        System.exit(0);
                    });
                optionsView.getChildren().clear();
                optionsView.getChildren().addAll(win, exitButton);
            }
        } else {
            enemyAttack();
        }
        mainMenu();
    }

    /**
    */

    public void mainMenu() {
        menu.getChildren().clear();
        menu.add(fight, 0, 0);
        menu.add(bag, 1, 0);
        menu.add(pokemon, 0, 1);
        menu.add(run, 1, 1);
        changeStatus(1);
    }

    /**
    */
    public void itemView() {
        menu.getChildren().clear();
        potion = new Button(String.format("potion x%d", potionTotal));
        changeStatus(3);
        potion.setOnAction(e -> {
                if (potionTotal <= 0) {
                    addedToMain = "No potions left...";
                    mainMenu();
                } else {
                    currentAlly.setCurrentHP(currentAlly.getCurrentHP() + 20);
                    if (currentAlly.getCurrentHP() > currentAlly.getMaxHP()) {
                        currentAlly.setCurrentHP(currentAlly.getMaxHP());
                    }
                    allyHealth.setProgress(currentAlly.getCurrentHP() / currentAlly.getMaxHP());
                    addedToMain = "Used a potion!";
                    potionTotal--;
                    enemyAttack();
                    mainMenu();
                }
            });
        menu.add(potion, 0, 0);
    }

    /**
    */

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
            addedToMain += (String.format("\n%s fainted!", currentAlly.getName()));
            //select next ally
            boolean found = false;
            for (Pokemon eachPokemon: allyList) {
                if (!eachPokemon.isFainted()) {
                    found = true;
                    currentAlly = eachPokemon;
                    try {
                        allyImg = new Image(new FileInputStream(currentAlly.getImage()));
                    } catch (Exception e) {
                        System.out.println("fat");
                    }
                    image2.setImage(allyImg);
                    allyName.setText(currentAlly.getName());
                    allyLvl.setText(String.format("lvl. %d", currentAlly.getLevel()));
                    allyHealth.setProgress(currentAlly.getCurrentHP() / currentAlly.getMaxHP());
                    break;
                }
            }
            if (!found) {
                //lose scene
                lose = new Label("YOU LOST...");
                exitButton2.setOnAction(e -> {
                        System.exit(0);
                    });
                optionsView.getChildren().clear();
                optionsView.getChildren().addAll(lose, exitButton2);
            }
        }
    }

    /**
    */

    public void changeAllyPokemon() {
        menu.getChildren().clear();
        poke1.setOnAction(e -> {
                changePokemon(allyList[0]);
                mainMenu();
            });
        poke2.setOnAction(e -> {
                changePokemon(allyList[1]);
                mainMenu();
            });
        poke3.setOnAction(e -> {
                changePokemon(allyList[2]);
                mainMenu();
            });
        poke4.setOnAction(e -> {
                changePokemon(allyList[3]);
                mainMenu();
            });
        menu.add(poke1, 0, 0);
        menu.add(poke2, 1, 0);
        menu.add(poke3, 0, 1);
        menu.add(poke4, 1, 1);
    }

    /**
    *@param ally fat
    */

    public void changePokemon(Pokemon ally) {
        if (ally.isFainted()) {
            addedToMain = "This pokemon is fainted...";
        } else if (ally == currentAlly) {
            addedToMain = "Can not choose same pokemon";
        } else {
            currentAlly = ally;
            addedToMain = String.format("Switched to %s", currentAlly.getName());
            try {
                allyImg = new Image(new FileInputStream(currentAlly.getImage()));
            } catch (Exception e) {
                System.out.println("fat");
            }
            image2.setImage(allyImg);
            allyName.setText(currentAlly.getName());
            allyLvl.setText(String.format("lvl. %d", currentAlly.getLevel()));
            allyHealth.setProgress(currentAlly.getCurrentHP() / currentAlly.getMaxHP());
            enemyAttack();
        }
    }

    /**
    @param option fat
    */

    public void changeStatus(int option) {
        if (option == 1) {
            status.setText(String.format("%s\nWhat will %s do?", addedToMain, currentAlly.getName()));
        } else if (option == 2) {
            status.setText("Choose a move.");
        } else if (option == 3) {
            status.setText("Choose an item.");
        }
    }

    /**
    *@param option fat
    *@param move fat
     */

    public void changeStatus(int option, Move move) {
        if (option == 3) {
            addedToMain = (String.format("%s used %s!", currentAlly.getName(), move.getName()));
        } else if (option == 4) {
            addedToMain += (String.format("\n%s used %s!", currentEnemy.getName(), move.getName()));
        } else if (option == 5) {
            //for ally moves on enemy
            double effective = currentEnemy.compareType(move);
            if (effective == 0.5) {
                addedToMain += "\nIt's not very effective...";
            } else if (effective == 2.0) {
                addedToMain += "\nIt's super effective!";
            }
        } else if (option == 6) {
            //for enemy moves on ally
            double effective = currentAlly.compareType(move);
            if (effective == 0.5) {
                addedToMain += "\nIt's not very effective...";
            } else if (effective == 2.0) {
                addedToMain += "\nIt's super effective!";
            }
        }
    }
}