package org.posapp.view.settings;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
// tuple
import javafx.util.Pair;
import org.posapp.controller.currency.CurrencyWrapper;


import java.io.File;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class plugin_settings extends Pane {
    // array of string path
    private List<String> path;
    // list of tuple ClassLoader and string path
    private List<String> PathPlugin;
    private List<Pair<URLClassLoader, String>> classLoaders;
    private VBox listBox;
    private ScrollPane scrollPane;
    private Label labelPlugin;
    private Button buttonLoadPlugin;
    private int weight;
    private  int x;
    private tab_settings parent;
    private CurrencyWrapper currencyWrapper;
    public plugin_settings(tab_settings parent) {
        this.path = new ArrayList<>();
        this.PathPlugin = new ArrayList<>();
        System.out.println("\nplugin_settings\n");
        // initialize singleton currencyWrapper
        this.currencyWrapper = CurrencyWrapper.getInstance();
        this.classLoaders = new ArrayList<>();
        this.parent = parent;
        // stroke border
        setStyle("-fx-background-color: #D9D9D966;");
        setStyle("-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 4px;");
        weight = 450;
        x = 160;
        listBox = new VBox(); // create a VBox to hold the list elements
        path = new ArrayList<>();
        listBox.setPrefSize(weight, 500);
        listBox.setLayoutX(400);
        listBox.setLayoutY(400);
        listBox.setStyle("-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 4px;");
        scrollPane = new ScrollPane(listBox); // create a ScrollPane and add the VBox to it
        scrollPane.setPrefSize(weight, 355);
        scrollPane.setLayoutX(x);
        scrollPane.setLayoutY(220);
        scrollPane.setStyle("-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 4px;");

        // rectangle box with text No plugin loaded
        labelPlugin = new Label("No plugin loaded");
        labelPlugin.setStyle("-fx-border-color: #666666; " +
                "-fx-border-width: 1px; " +
                "-fx-background-color: #eeeeee; " +
                "-fx-padding: 15px;" +
                "-fx-font-size: 20px;");

        labelPlugin.setPrefSize(weight, 50);
        labelPlugin.setLayoutX(x);
        labelPlugin.setLayoutY(35);
        labelPlugin.setAlignment(Pos.CENTER);
        getChildren().add(labelPlugin);

        // button box with text Load plugin
        buttonLoadPlugin = new Button("Load Plugin");
        buttonLoadPlugin.setStyle("-fx-border-color: #555555; " +
                "-fx-border-width: 1px; " +
                "-fx-background-color: #b9b9b9; " +
                "-fx-padding: 15px;" +
                "-fx-font-size: 20px;" +
                "-fx-text-fill: #ffffff;");

        buttonLoadPlugin.setPrefSize(weight, 50);
        buttonLoadPlugin.setLayoutX(x);
        buttonLoadPlugin.setLayoutY(130);
        buttonLoadPlugin.setAlignment(Pos.CENTER);
        buttonLoadPlugin.setOnAction(event -> {
            // set label text to indicate file selection
            labelPlugin.setText("Select a file");

            // set label text color to black
            labelPlugin.setTextFill(Color.BLACK);

            // create a new file chooser
            FileChooser fileChooser = new FileChooser();

            // set the title of the file chooser dialog
            fileChooser.setTitle("Open File");

            // only add file .jar
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JAR", "*.jar")
            );

            // show the file chooser dialog and wait for user selection
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            this.AddPlugin(selectedFile.getAbsolutePath());

        });
        getChildren().add(buttonLoadPlugin);

        // button box with text Load plugin
        listBox.setSpacing(10); // set the spacing between elements
        scrollPane.setFitToWidth(true); // set the width of the ScrollPane to match its parent
        getChildren().add(scrollPane); // add the elements to the root VBox

        this.ReadFromTXT();
        this.RestoreDataPlugin();
    }
    private void AddToTXT(String path) {
        try {
            // open existing file
            FileWriter myWriter = new FileWriter("src\\main\\java\\org\\posapp\\view\\settings\\StateGUIPlugin.txt", true);
            // write under the row that already exists
            // cek if first row is empty
            if (myWriter.equals("")) {
                myWriter.write(path);
            } else {
                myWriter.write("\n" + path);
            }
//            myWriter.write("\n" + path);
//            myWriter.write(path);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    private void ReadFromTXT() {
        System.out.println("ReadFromTXT");
        try {
            File myObj = new File("src\\main\\java\\org\\posapp\\view\\settings\\StateGUIPlugin.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                this.PathPlugin.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
    private void AddPlugin(String pathPlug){
        // change selectedFile to File
        File selectedFile = new File(pathPlug);
//        labelPlugin.setText(selectedFile.getName());
        // do something with the selected file
        // get the name of the selected file
        System.out.println(path);
        System.out.println(selectedFile.getName()); // a.jar
        System.out.println(selectedFile.getAbsolutePath()); // full path
        String labelText = selectedFile.getName(); // get the label text from the text field
        AtomicBoolean isLoaded = new AtomicBoolean(false);
        // file is exist
        System.out.println(selectedFile);
        if (selectedFile.exists()) {
            System.out.println("\nMASUKKK    pertama\n");
            // cek apa plugin sudah di load
            this.path.forEach((item) -> {
                if (item.equals(labelText)) {
                    labelPlugin.setText("Plugin already loaded");
                    isLoaded.set(true);
                }
            });
//            isLoaded.set(false);
            this.path.add(labelText);
            if (!isLoaded.get()){
                System.out.println("\nMASUKKK\n");
                Label label = new Label(labelText); // create a new label with the text
                label.setPrefSize(400, 40);
                Button closeButton = new Button("X"); // create a close button
                closeButton.setPrefSize(50, 40);
                closeButton.setOnAction(closeEvent -> {
                    Node node = closeButton.getParent(); // get the parent node of the close button
                    if (node instanceof HBox) {
                        this.path.remove(labelText); // remove the element from the list
                        System.out.println(path);
                        // unload plugin
                        // terminate plugin
                        // close class loader from classLoaders that have the same path in the second element of pair
                        for (Pair<URLClassLoader, String> classLoader : classLoaders) {
                            System.out.println(classLoader.getValue());
                            if (classLoader.getValue().equals("PluginCurrency.jar")) {
                                parent.deleteButtonAndTab("Currency Settings");
                                this.currencyWrapper.setCode("IDR");
                                this.currencyWrapper.setRate(1.0f);
                            }
//                                    if (classLoader.getValue().equals(selectedFile.getAbsolutePath())) {
//                                        // jika plugin yg diload PluginCurrency, delete tab currency settings
//                                        try {
//                                            classLoader.getKey().close();
//                                        } catch (IOException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
                        }
                        HBox element = (HBox) node; // cast the node to HBox
                        listBox.getChildren().remove(element); // remove the element from the list
                    }
                    // jika list kosong, tampilkan label no plugin loaded
                    if (listBox.getChildren().isEmpty()) {
                        labelPlugin.setText("No plugin loaded");
                    }
                });
                HBox element = new HBox(label, closeButton); // create an HBox to hold the label and close button
                HBox.setHgrow(label, Priority.ALWAYS);
                // set weight and height of the HBox
                element.setPrefSize(350, 50);
                element.setStyle("-fx-border-color: #666666; " +
                        "-fx-border-width: 1px; " +
                        "-fx-background-color: #eeeeee; " +
                        "-fx-padding: 6px;" +
                        "-fx-font-size: 17px;");
                element.setAlignment(Pos.CENTER_RIGHT); // set the alignment of the HBox
//                    element.setSpacing(15);
                listBox.setSpacing(0); // set the spacing between the label and close button
                listBox.getChildren().add(element); // add the new element to the list


                // EXECUTE PLUGIN
                // load the plugin JAR file
                URLClassLoader classLoader = null;
                try {
                    classLoader = new URLClassLoader(new URL[]{
                            new File(selectedFile.getAbsolutePath()).toURI().toURL()});
                    System.out.println("classLoader");
                    System.out.println(classLoader);    // URLClassLoader

                    // get a reference to the plugin class
                    Class<?> pluginClass = null;
                    // from path
                    pluginClass = classLoader.loadClass(
                            selectedFile.getName().replace(".jar", ""));
                    System.out.println("pluginClass");
                    System.out.println(selectedFile.getName().replace(".jar", ""));

                    // Add to txt File
                    AddToTXT(selectedFile.getAbsolutePath());

                    // call the main() method of the plugin class
                    //mainMethod = pluginClass.getMethod("main", String[].class);
                    //mainMethod.invoke(null, new Object[]{new String[]{}});
                    Method mainMethod = null;
                    // invoke method"RunPlugin(BorderPane borderPane)"
//                            mainMethod = pluginClass.getMethod("RunPlugin", Object.class, CurrencyWrapper.class);
//                            mainMethod.invoke(null, parent, currencyWrapper);
                    Method[] methods = pluginClass.getDeclaredMethods();
                    for (Method m: methods) {
                        if(m.getName().equals("RunPlugin")){
                            System.out.println("RunPlugin");
                            System.out.println(m);
                            m.setAccessible(true);
                            m.invoke(null, parent);
                        }
                    }
                    classLoaders.add(new Pair<>(classLoader, labelText));
                    System.out.println("classLoaders");
                    System.out.println(classLoaders);
                } catch (MalformedURLException | ClassNotFoundException |
                         IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }


            }
        }

    }

    private void RestoreDataPlugin() {
        System.out.println("RestoreDataPlugin");
        for (String item : this.PathPlugin) {
            System.out.println(item);
//            File selectedFile = new File(item);
//            System.out.println(selectedFile.getAbsolutePath());
            AddPlugin(item);
        }
    }
}
