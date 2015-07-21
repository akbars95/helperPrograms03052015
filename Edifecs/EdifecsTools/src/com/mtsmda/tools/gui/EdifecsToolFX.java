package com.mtsmda.tools.gui;

import com.mtsmda.tools.gui.logic.FileSystem;
import com.mtsmda.tools.gui.logic.WindowsServiceAndProcesses;
import com.mtsmda.tools.gui.ui.AlertMTSMDA;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by c-DMITMINZ on 17.07.2015.
 */
public class EdifecsToolFX extends Application {

    //
    private VBox vBoxMain;
    private Properties propertiesData = new Properties();

    private MenuBar menuBar = new MenuBar();
    private Menu menuMain = new Menu("Main");
    private MenuItem menuItemSetPaths = new MenuItem("Set Paths");
    private MenuItem menuItemExit = new MenuItem("Exit");

    private Menu menuTM = new Menu("TM");
    private MenuItem menuItemTMClearDatabase = new MenuItem("Clear database");
    private MenuItem menuItemTMInstall = new MenuItem("Install TM");
    private MenuItem menuItemTMUninstall = new MenuItem("Uninstall TM");
    private MenuItem menuItemTMUninstallAndInstall = new MenuItem("Uninstall TM & Install TM");

    private Menu menuRRM = new Menu("RRM");
    private MenuItem menuItemDeleteAll = new MenuItem("Delete all");
    private MenuItem menuItemDeleteRRM = new MenuItem("Delete RRM");

    private Menu menuEAM = new Menu("EAM");
    private MenuItem menuItemClearEAM = new MenuItem("Clear EAM");
    private MenuItem menuItemSetTracerForProfiles = new MenuItem("Set tracer for profiles");

    private Menu menuWindows = new Menu("Windows");
    private Menu menuItemMenuWindowsServices = new Menu("Services");
    private MenuItem menuItemMenuWindowsServicesStartStandardServices = new MenuItem("Start standard services");
    private MenuItem menuItemMenuWindowsServicesStopStandardServices = new MenuItem("Stop standard services");
    private Menu menuItemMenuWindowsProcesses = new Menu("Processes");
    private MenuItem menuItemMenuWindowsServicesStartStandardProcesses = new MenuItem("Start standard processes");
    private MenuItem menuItemMenuWindowsServicesStopStandardProcesses = new MenuItem("Stop standard processes");


    private List<String> services = new ArrayList<String>();

    @Override
    public void start(Stage stage) throws Exception {
        initApp();
        vBoxMain = new VBox();
        vBoxMain.setPrefSize(700, 350);

        Scene scene = new Scene(vBoxMain);
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);
        createMenuMain();


        stage.setTitle("Edifecs tools");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initApp() {
        try {
            propertiesData.load(this.getClass().getResourceAsStream("data.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadServices() {
        if (!services.isEmpty()) {
            services.clear();
        }
        services.addAll(Arrays.asList(propertiesData.getProperty("tm.service").split(",")));
        services.add(propertiesData.getProperty("apacheMQ.service"));
        services.add(propertiesData.getProperty("tomcat7.service"));
    }

    private void createMenuMain() {
        menuBar.getMenus().addAll(menuMain, menuTM, menuRRM, menuEAM, menuWindows);
        menuMain.getItems().addAll(menuItemSetPaths, new SeparatorMenuItem(), menuItemExit);
        menuTM.getItems().addAll(menuItemTMClearDatabase, menuItemTMInstall, menuItemTMUninstall, menuItemTMUninstallAndInstall);
        menuRRM.getItems().addAll(menuItemDeleteAll, menuItemDeleteRRM);
        menuEAM.getItems().addAll(menuItemClearEAM, menuItemSetTracerForProfiles);
        menuWindows.getItems().addAll(menuItemMenuWindowsServices, menuItemMenuWindowsProcesses);
        menuItemMenuWindowsServices.getItems().addAll(menuItemMenuWindowsServicesStartStandardServices, menuItemMenuWindowsServicesStopStandardServices);
        menuItemMenuWindowsProcesses.getItems().addAll(menuItemMenuWindowsServicesStartStandardProcesses, menuItemMenuWindowsServicesStopStandardProcesses);
        menuItemsProperties();
    }

    private void switchMenu(Node node) {
        if (vBoxMain.getChildren().size() > 1) {
            for (int i = 0; i < vBoxMain.getChildren().size(); i++) {
                if (i != 0) {
                    vBoxMain.getChildren().remove(i);
                }
            }
        }
        vBoxMain.getChildren().add(node);
    }

    private void menuItemsProperties() {
        menuItemSetPaths.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setMenuItemSetPaths();
            }
        });
        menuItemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });


        menuItemTMClearDatabase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setMenuItemTMClearDatabase();
            }
        });

        menuItemTMInstall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadServices();
                for (String service : services) {
                    WindowsServiceAndProcesses.service(WindowsServiceAndProcesses.SERVICE_START, service);
                }
                System.out.println("ok");
            }
        });

        menuItemDeleteAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createMenuItemDeleteAll();
                AlertMTSMDA.getStandardAlert(Alert.AlertType.INFORMATION, "Information", "Delete folders in XEngine", "All folders with files success deleted!");
            }
        });

        menuItemDeleteRRM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createMenuItemDeleteRRM();
            }
        });

        menuItemClearEAM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuItemClearEAM();
            }
        });

        menuItemSetTracerForProfiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuItemSetTracerForProfiles();
            }
        });

    }

    private void menuItemClearEAM() {
        if (stringIsNotNULLOrEmpty(propertiesData.getProperty("edifecs.home")) && stringIsNotNULLOrEmpty(propertiesData.getProperty("eam.logs"))) {
            AlertMTSMDA.getConfirmationAlert("Confirmation", "Do you want delete logs?", "Clear logs folder");
        } else {
            AlertMTSMDA.getStandardAlert(Alert.AlertType.ERROR, "ERROR", "Not found properties", "Not found properties!");
        }
    }

    private void menuItemSetTracerForProfiles() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setVisible(true);

        if (stringIsNotNULLOrEmpty(propertiesData.getProperty("edifecs.home"))) {
            File fileProfiles = new File(propertiesData.getProperty("edifecs.home") + File.separator + "XEServer\\profiles\\");
            int count = 0;
            Set<SaveTracerProfile> saveTracerProfiles = new LinkedHashSet<SaveTracerProfile>();
            Properties propertiesEAMTracer = new Properties();
            if (fileProfiles.exists() && fileProfiles.isDirectory()) {
                if (fileProfiles.listFiles().length != 0) {
                    for (File currentProfile : fileProfiles.listFiles()) {
                        File currentProfileRRMTracerProperties = new File(currentProfile + File.separator + "config\\environment\\RRM_Tracer_Properties.properties");
                        if (currentProfileRRMTracerProperties.exists() && currentProfileRRMTracerProperties.isFile()) {
                            Label label = new Label(currentProfile.getName());
                            GridPane.setHalignment(label, HPos.LEFT);
                            gridPane.add(label, 0, count);

                            final ComboBox<String> stringComboBox = new ComboBox<>();
                            stringComboBox.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    propertiesEAMTracer.put("RRM_Global_Tracer_Enabler", stringComboBox.getValue());
                                    saveTracerProfiles.add(new SaveTracerProfile(currentProfileRRMTracerProperties, propertiesEAMTracer));
                                }
                            });
                            try {
                                stringComboBox.getItems().add("tracer.never");
                                stringComboBox.getItems().add("tracer.always");
                                propertiesEAMTracer.load(new FileInputStream(currentProfileRRMTracerProperties));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (stringIsNotNULLOrEmpty(propertiesEAMTracer.getProperty("RRM_Global_Tracer_Enabler"))) {
                                stringComboBox.setValue(propertiesEAMTracer.getProperty("RRM_Global_Tracer_Enabler"));
                            }

                            GridPane.setHalignment(stringComboBox, HPos.LEFT);
                            gridPane.add(stringComboBox, 1, count);

                            count++;
                        }
                    }
                }
            }
            Button buttonSave = new Button("Save");
            buttonSave.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        StringBuilder successSave = new StringBuilder();
                        if (!saveTracerProfiles.isEmpty()) {
                            for (SaveTracerProfile currentProfileRRMTracerProperties : saveTracerProfiles) {
                                FileOutputStream fileOutputStream = new FileOutputStream(currentProfileRRMTracerProperties.getFile());
                                currentProfileRRMTracerProperties.getProperties().store(fileOutputStream, new Date().toString());
                                successSave.append(currentProfileRRMTracerProperties.getFile().getAbsoluteFile()).append("\n");
                            }
                        }
                        AlertMTSMDA.getStandardAlert(Alert.AlertType.INFORMATION, "Information!", "Save Tracer Settings", successSave.length() == 0 ? "You nothing not changed!" : successSave.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        AlertMTSMDA.getStandardAlert(Alert.AlertType.ERROR, "Error!", "Error in Save Tracer Settings", "Error message - " + e.getMessage());
                    }
                    if(!saveTracerProfiles.isEmpty()){
                        saveTracerProfiles.clear();
                    }
                }
            });
            GridPane.setHalignment(buttonSave, HPos.LEFT);
            gridPane.add(buttonSave, 1, count + 2);
        }


        switchMenu(gridPane);
    }

    private void setMenuItemSetPaths() {
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setVisible(true);

        Label labelSetPaths = new Label("Edifecs home");
        TextField textFieldSetPaths = new TextField(propertiesData.get("edifecs.home").toString());
        textFieldSetPaths.setEditable(false);

        Label labelDeteleFolders = new Label("Profiles folder for delete");
        TextField textFieldDeteleFolders = new TextField(propertiesData.get("edifecs.XEServer.profiles.detele.folders").toString());

        GridPane.setHalignment(labelSetPaths, HPos.LEFT);
        gridpane.add(labelSetPaths, 0, 0);
        GridPane.setHalignment(textFieldSetPaths, HPos.LEFT);
        gridpane.add(textFieldSetPaths, 1, 0);
        GridPane.setHalignment(labelDeteleFolders, HPos.LEFT);
        gridpane.add(labelDeteleFolders, 0, 1);
        GridPane.setHalignment(textFieldDeteleFolders, HPos.LEFT);
        gridpane.add(textFieldDeteleFolders, 1, 1);

        switchMenu(gridpane);

    }

    private void setMenuItemTMClearDatabase() {
        if (propertiesData.get("edifecs.home").toString() != null && !propertiesData.get("edifecs.home").toString().trim().isEmpty()) {
            File batFile = new File(propertiesData.get("edifecs.home").toString() + File.separator + "TM\\ServiceManager\\tools\\gbd-cleanup\\cleanup-transactions-data.bat");
            if (batFile.exists()) {
                StringBuilder text = new StringBuilder();
                try {

                    int g;

                    Process process = Runtime.getRuntime().exec(batFile.getAbsolutePath());
                    int c = process.waitFor();
                    if (c == 0) {
                        System.out.println("ok");
                    }

                    while ((g = process.getInputStream().read()) != -1) {
                        text.append(((char) g));
                    }

                    while ((g = process.getErrorStream().read()) != -1) {
                        text.append(((char) g));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    text.append(e.getMessage());
                }
                /*AlertMTSMDA.getStandardAlert(Alert.AlertType.INFORMATION);*/
            }
        }
    }

    private void createMenuItemDeleteAll() {
        if (propertiesData.get("edifecs.home").toString() != null && !propertiesData.get("edifecs.home").toString().trim().isEmpty() && propertiesData.get("edifecs.XEServer.profiles.detele.folders").toString() != null && !propertiesData.get("edifecs.XEServer.profiles.detele.folders").toString().trim().isEmpty()) {
            List<File> files = availabeFolders(EnumDelete.DELETE_ALL);
            if (!files.isEmpty()) {
                for (File current : files) {
                    FileSystem.deleteFile(current);
                }
            }

        }
    }

    private List<File> availabeFolders(EnumDelete enumDelete) {
        List<File> files = new ArrayList<File>();
        File fileParent = null;
        String[] children = null;

        switch (enumDelete) {
            case DELETE_ALL:
                fileParent = new File(propertiesData.get("edifecs.home").toString() + File.separator + "XEServer\\profiles");
                children = propertiesData.getProperty("edifecs.XEServer.profiles.detele.folders").split(",");
                for (String current : fileParent.list()) {
                    for (String currentChildren : children) {
                        File currentFile = new File(fileParent.getAbsolutePath() + File.separator + current + File.separator + currentChildren);
                        if (currentFile.exists()) {
                            files.add(currentFile);
                        }
                    }
                }
                break;
            case DELETE_RRM: {
                fileParent = new File(propertiesData.get("edifecs.home").toString() + File.separator + "XEServer\\profiles");
                children = propertiesData.getProperty("rrm.delete").split(",");
                File fileRRM = new File(propertiesData.get("edifecs.home").toString() + File.separator + "RRM");
                for (String currentChildren : children) {
                    File currentFile = new File(fileParent.getAbsolutePath() + File.separator + currentChildren);
                    if (currentFile.exists()) {
                        files.add(currentFile);
                    }
                }
                if (fileRRM.exists()) {
                    files.add(fileRRM);
                }
            }
            break;
        }


        return files;
    }


    private void createMenuItemDeleteRRM() {
        if (propertiesData.get("edifecs.home").toString() != null && !propertiesData.get("edifecs.home").toString().trim().isEmpty() && propertiesData.get("rrm.delete").toString() != null && !propertiesData.get("rrm.delete").toString().trim().isEmpty()) {
            List<File> files = availabeFolders(EnumDelete.DELETE_RRM);
            if (!files.isEmpty()) {
                for (File current : files) {
                    FileSystem.deleteFile(current);
                }
            }

        }
    }

    private void edifecsStopServices(String processStopOrStart) {
        edifecsStopServicesParamPropertyName(processStopOrStart, "tm.service");
        edifecsStopServicesParamPropertyName(processStopOrStart, "apacheMQ.service");
        edifecsStopServicesParamPropertyName(processStopOrStart, "tomcat7.service");
    }

    private void edifecsStopServicesParamPropertyName(String processStopOrStart, String propertyName) {
        if (propertiesData.getProperty(propertyName) != null && !propertiesData.getProperty(propertyName).isEmpty()) {
            if (propertiesData.getProperty(propertyName).contains(",")) {
                String tmServices[] = propertiesData.getProperty(propertyName).split(",");
                for (String serviceCurrent : tmServices) {
                    WindowsServiceAndProcesses.process(serviceCurrent, processStopOrStart);
                }
            } else {
                WindowsServiceAndProcesses.process(propertiesData.getProperty(propertyName), processStopOrStart);
            }
        }
    }

    private void setMenuItemRRM() {
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setVisible(true);


        switchMenu(gridpane);
    }

    private static boolean stringIsNotNULLOrEmpty(String string) {
        if (string != null && !string.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

enum EnumDelete {
    DELETE_ALL(0),
    DELETE_RRM(1);

    private int type;

    private EnumDelete(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}

class SaveTracerProfile {
    private File file;
    private Properties properties;

    public SaveTracerProfile(File file, Properties properties) {
        this.file = file;
        this.properties = properties;
    }

    public File getFile() {
        return file;
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaveTracerProfile that = (SaveTracerProfile) o;

        if (file != null ? !file.equals(that.file) : that.file != null) return false;
        return !(properties != null ? !properties.equals(that.properties) : that.properties != null);

    }

    @Override
    public int hashCode() {
        int result = file != null ? file.hashCode() : 0;
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }
}