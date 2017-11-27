package main.gui.javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import main.util.Logging;
import main.util.timing.Timing;

import javax.swing.*;
import java.net.URL;

/**
 * Created by Sphiinx on 6/15/2017.
 */
public class GUI extends Application {

    /**
     * The URL for the FXML file.
     */
    private final URL FXML;

    /**
     * The URL for the style sheet file.
     */
    private final URL STYLE_SHEET;

    /**
     * The stage.
     */
    private Stage stage;

    /**
     * The scene.
     */
    private Scene scene;

    /**
     * The boolean controlling whether the GUI is decorated or not.
     * The decoration determines the default O/S application border.
     */
    private boolean decorated = true;

    /**
     * The boolean controlling whether the GUI is open or not.
     */
    private boolean is_open = false;

    public GUI(URL fxml) {
        this(fxml, null);
    }

    public GUI(URL fxml, boolean decorated) {
        this(fxml, null, decorated);
    }

    public GUI(URL fxml, URL stylesheet) {
        this(fxml, stylesheet, true);
    }

    public GUI(URL fxml, URL stylesheet, boolean decorated) {

        this.FXML = fxml;
        this.STYLE_SHEET = stylesheet;
        this.decorated = decorated;

        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            Platform.runLater(() -> {
                try {
                    final Stage STAGE = new Stage();

                    if (!this.decorated)
                        STAGE.initStyle(StageStyle.TRANSPARENT);

                    start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        waitForInitialization();
    }

    /**
     * Gets the scene.
     *
     * @return The scene.
     * */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Gets the stage.
     *
     * @return The stage.
     * */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Starts the specified stage after the initialization method has returned.
     *
     * @param stage The stage for the GUI.
     */
    @Override
    public void start(Stage stage) throws Exception {
        if (FXML == null) {
            Logging.error("The FXML is NULL.");
            return;
        }

        this.stage = stage;
        stage.setAlwaysOnTop(true);
        Platform.setImplicitExit(false);

        final FXMLLoader FXML_LOADER = new FXMLLoader(FXML);
        FXML_LOADER.setClassLoader(this.getClass().getClassLoader());

        final Parent PARENT_BOX = FXML_LOADER.load();
        final GUIController GUI_CONTROLLER = FXML_LOADER.getController();

        if (GUI_CONTROLLER == null) {
            Logging.error("The FXML does not have a controller.");
            return;
        }

        GUI_CONTROLLER.setGUI(this);

        scene = new Scene(PARENT_BOX);

        if (!this.decorated) {
            scene.setFill(Color.TRANSPARENT);
        }

        if (this.STYLE_SHEET != null)
            scene.getStylesheets().add(this.STYLE_SHEET.toExternalForm());

        stage.setScene(scene);

        stage.setResizable(false);
    }

    /**
     * Shows the GUI.
     */
    public void show() {
        if (stage == null)
            return;

        is_open = true;
        Platform.runLater(() -> stage.show());
    }

    /**
     * Closes the GUI.
     */
    public void close() {
        if (stage == null)
            return;

        is_open = false;
        Platform.runLater(() -> stage.close());
    }

    /**
     * Checks if the GUI is open or not.
     *
     * @return True if the GUI is open; false otherwise.
     */
    public boolean isIs_open() {
        return is_open;
    }


    /**
     * Waits 5 seconds for the GUI to initialize or until the stage is no longer null.
     */
    private void waitForInitialization() {
        Timing.waitCondition(() -> stage != null, 5000);
    }

}

