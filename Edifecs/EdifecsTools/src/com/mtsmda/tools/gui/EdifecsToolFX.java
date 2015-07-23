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
import javafx.scene.image.Image;
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

    private MenuMain menuMainC = new MenuMain();
    private MenuTM menuTMC = new MenuTM();
    private MenuRRM menuRRMC = new MenuRRM();
    private MenuEAM menuEAMC = new MenuEAM();
    private MenuWindows menuWindowsC = new MenuWindows();

    //
    private VBox vBoxMain;
    private Properties propertiesData = new Properties();

    private MenuBar menuBar = new MenuBar();
    private Menu menuMain = new Menu("Main");
    //done
    private MenuItem menuItemSetPaths = new MenuItem("Set Paths");
    //done
    private MenuItem menuItemExit = new MenuItem("Exit");

    private Menu menuTM = new Menu("TM");
    //in progress
    private MenuItem menuItemTMClearDatabase = new MenuItem("Clear database");
    //in progress
    private MenuItem menuItemTMInstall = new MenuItem("Install TM");
    //in progress
    private MenuItem menuItemTMUninstall = new MenuItem("Uninstall TM");
    //in progress
    private MenuItem menuItemTMUninstallAndInstall = new MenuItem("Uninstall TM & Install TM");

    private Menu menuRRM = new Menu("RRM");
    //done
    private MenuItem menuItemDeleteAll = new MenuItem("Delete all");
    //done
    private MenuItem menuItemDeleteRRM = new MenuItem("Delete RRM");
    private MenuItem menuItemInstallRRM = new MenuItem("Install RRM");

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
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("logo.png")));
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

    private void createMenuMain() {
        menuBar.getMenus().addAll(menuMain, menuTM, menuRRM, menuEAM, menuWindows);
        menuMain.getItems().addAll(menuItemSetPaths, new SeparatorMenuItem(), menuItemExit);
        menuTM.getItems().addAll(menuItemTMClearDatabase, menuItemTMInstall, menuItemTMUninstall, menuItemTMUninstallAndInstall);
        menuRRM.getItems().addAll(menuItemDeleteAll, menuItemDeleteRRM, menuItemInstallRRM);
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
        //main
        menuItemSetPaths.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getMenuMainC().setMenuItemSetPaths();
            }
        });
        menuItemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        //TM
        menuItemTMClearDatabase.setDisable(true);
        menuItemTMClearDatabase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getMenuTMC().setMenuItemTMClearDatabase();
            }
        });

        menuItemTMInstall.setDisable(true);
        menuItemTMInstall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getMenuTMC().menuTMInstall();
            }
        });

        menuItemTMUninstall.setDisable(true);
        menuItemTMUninstall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        menuItemTMUninstall.setDisable(true);
        menuItemTMUninstall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        //RRM
        menuItemDeleteAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getMenuRRMC().createMenuItemDeleteAll();
            }
        });

        menuItemDeleteRRM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getMenuRRMC().createMenuItemDeleteRRM();
            }
        });

        menuItemTMUninstall.setDisable(true);
        menuItemInstallRRM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getMenuRRMC().createMenuItemInstallRRM();
            }
        });

        //EAM
        menuItemClearEAM.setDisable(true);
        menuItemClearEAM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getMenuEAMC().menuItemClearEAM();
            }
        });

        menuItemClearEAM.setDisable(true);
        menuItemSetTracerForProfiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getMenuEAMC().menuItemSetTracerForProfiles();
            }
        });

        //windows
        menuItemMenuWindowsServicesStartStandardServices.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//TODO
                getMenuWindowsC().menuStartStandardServices();
            }
        });

        menuItemMenuWindowsServicesStopStandardServices.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//TODO
                getMenuWindowsC().menuStopStandardServices();
            }
        });

        menuItemMenuWindowsServicesStartStandardProcesses.setDisable(true);
        menuItemMenuWindowsServicesStartStandardProcesses.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        menuItemMenuWindowsServicesStopStandardProcesses.setDisable(true);
        menuItemMenuWindowsServicesStopStandardProcesses.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

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
                File fileRRM = new File(propertiesData.getProperty("edifecs.home") + File.separator + "RRM");
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

    public MenuMain getMenuMainC() {
        return menuMainC;
    }

    public MenuTM getMenuTMC() {
        return menuTMC;
    }

    public MenuRRM getMenuRRMC() {
        return menuRRMC;
    }

    public MenuEAM getMenuEAMC() {
        return menuEAMC;
    }

    public MenuWindows getMenuWindowsC() {
        return menuWindowsC;
    }

    private class MenuMain {
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
            textFieldDeteleFolders.setEditable(false);

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
    }

    private class MenuTM {
        private void setMenuItemTMClearDatabase() {
            if (propertiesData.get("edifecs.home").toString() != null && !propertiesData.get("edifecs.home").toString().trim().isEmpty()) {
                File batFile = new File("C:\\Users\\c-DMITMINZ\\Desktop\\1.bat"/*propertiesData.get("edifecs.home").toString() + File.separator + "TM\\ServiceManager\\tools\\gbd-cleanup\\cleanup-transactions-data.bat"*/);
                if (batFile.exists()) {
                    StringBuilder text = new StringBuilder();
                    try {

                        int g;
                        Process process = Runtime.getRuntime().exec("cmd " + batFile.getAbsolutePath().substring(0, 2) + " cd " + batFile.getAbsolutePath() + "\n " + batFile.getName());
                        /*int c = process.waitFor();
                        if (c == 0) {
                            System.out.println("ok");
                        }*/
                        System.out.println(process.getInputStream().available());
                        while ((g = process.getInputStream().read()) != -1) {
                            text.append(((char) g));
                        }
                        System.out.println(text.toString());
                        while ((g = process.getErrorStream().read()) != -1) {
                            text.append(((char) g));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        text.append(e.getMessage());
                    }
                    AlertMTSMDA.getStandardInformationAlert("Clear database", text.toString());
                }
            }
        }

        private void menuTMInstall() {
            loadServices();
            for (String service : services) {
                WindowsServiceAndProcesses.service(WindowsServiceAndProcesses.SERVICE_START, service);
            }
            System.out.println("ok");
        }

        private void loadServices() {
            if (!services.isEmpty()) {
                services.clear();
            }
            services.addAll(Arrays.asList(propertiesData.getProperty("tm.service").split(",")));
            services.add(propertiesData.getProperty("apacheMQ.service"));
            services.add(propertiesData.getProperty("tomcat7.service"));
        }
    }

    private class MenuRRM {

        private void createMenuItemDeleteAll() {
            int count = -1;
            if (propertiesData.get("edifecs.home").toString() != null && !propertiesData.get("edifecs.home").toString().trim().isEmpty() && propertiesData.get("edifecs.XEServer.profiles.detele.folders").toString() != null && !propertiesData.get("edifecs.XEServer.profiles.detele.folders").toString().trim().isEmpty()) {
                List<File> files = availabeFolders(EnumDelete.DELETE_ALL);
                if (!files.isEmpty()) {
                    count = files.size();
                    for (File current : files) {
                        FileSystem.deleteFile(current);
                    }
                }

            }
            StringBuilder stringBuilderText = new StringBuilder("All folders with files success deleted!");
            if (count != -1) {
                stringBuilderText.append("\nFolder count is - ").append(count);
            }

            AlertMTSMDA.getStandardAlert(Alert.AlertType.INFORMATION, "Information", "Delete folders in XEngine", stringBuilderText.toString());
        }

        private void createMenuItemDeleteRRM() {
            StringBuilder report = new StringBuilder();
            if (stringIsNotNULLOrEmpty(propertiesData.getProperty("edifecs.home")) && stringIsNotNULLOrEmpty(propertiesData.getProperty("rrm.delete"))) {
                List<File> files = availabeFolders(EnumDelete.DELETE_RRM);
                report.append(files.size()).append(" file(s) available for delete").append("\n");
                if (!files.isEmpty()) {
                    for (File current : files) {
                        FileSystem.deleteFile(current);
                        report.append(current.getAbsoluteFile()).append(" deteled\n");
                    }
                }
                AlertMTSMDA.getStandardInformationAlert("Delete RRM", report.toString());
            }
        }

        private void createMenuItemInstallRRM() {
            String result = null;
            if (stringIsNotNULLOrEmpty(propertiesData.getProperty("rrm.installer.path"))) {
                File file = new File(propertiesData.getProperty("rrm.installer.path") + File.separator + "install.bat");
                if (file.exists() && file.isFile()) {
                    try {
                        result = WindowsServiceAndProcesses.runBatFile(file);
                    } catch (Exception e) {
                        AlertMTSMDA.getStandardErrorAlert("Error install RRM", "Error is - " + e.getMessage());
                    }
                    if(stringIsNotNULLOrEmpty(result)){
                        AlertMTSMDA.getStandardInformationAlert("Install RRM", "Install is success!\n" + result);
                        return;
                    }else{
                        AlertMTSMDA.getStandardErrorAlert("Error install RRM", "Error is - ");
                    }
                }
            }
        }

    }

    private class MenuEAM {
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
                        if (!saveTracerProfiles.isEmpty()) {
                            saveTracerProfiles.clear();
                        }
                    }
                });
                GridPane.setHalignment(buttonSave, HPos.LEFT);
                gridPane.add(buttonSave, 1, count + 2);
            }


            switchMenu(gridPane);
        }
    }

    private class MenuWindows {

        private StringBuilder convertPropertiesToStringReport(List<Properties> propertiesList){
            StringBuilder report = new StringBuilder();
            if(propertiesList != null && !propertiesList.isEmpty()){
for (Properties properties : propertiesList){
    for (Object key : properties.keySet()){
        report.append(key.toString());
    }
}
            }
            return report;
        }

        private void menuStartStandardServices() {
            List<Properties> propertiesList = null;
            String message = "Success";
            try {
                propertiesList = edifecsServices(WindowsServiceAndProcesses.SERVICE_START);
            } catch (Exception e) {
                message = "Error! \n " + e.getMessage();
            }
            message = convertPropertiesToStringReport(propertiesList).toString();
            AlertMTSMDA.getStandardInformationAlert("Start standard services Edifecs", message);
        }

        private void menuStopStandardServices() {
            String message = "Success";
            try {
                edifecsServices(WindowsServiceAndProcesses.SERVICE_STOP);
            } catch (Exception e) {
                message = "Error! \n " + e.getMessage();
            }
            AlertMTSMDA.getStandardInformationAlert("Stop standard services Edifecs", message);

        }

        private List<Properties> edifecsServices(String processStopOrStart) {
            List<Properties> properties = new ArrayList<Properties>();
            properties.addAll(edifecsStopServicesParamPropertyName(processStopOrStart, "tm.service"));
            properties.addAll(edifecsStopServicesParamPropertyName(processStopOrStart, "apacheMQ.service"));
            properties.addAll(edifecsStopServicesParamPropertyName(processStopOrStart, "tomcat7.service"));
            return properties;
        }

        private List<Properties> edifecsStopServicesParamPropertyName(String processStopOrStart, String propertyName) {
            List<Properties> propertiesList = new ArrayList<Properties>();
            if (propertiesData.getProperty(propertyName) != null && !propertiesData.getProperty(propertyName).isEmpty()) {
                if (propertiesData.getProperty(propertyName).contains(",")) {
                    String tmServices[] = propertiesData.getProperty(propertyName).split(",");
                    for (String serviceCurrent : tmServices) {
                        propertiesList.add(WindowsServiceAndProcesses.process(serviceCurrent, processStopOrStart));
                    }
                } else {
                    propertiesList.add(WindowsServiceAndProcesses.process(propertiesData.getProperty(propertyName), processStopOrStart));
                }
            }
            return propertiesList;
        }
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