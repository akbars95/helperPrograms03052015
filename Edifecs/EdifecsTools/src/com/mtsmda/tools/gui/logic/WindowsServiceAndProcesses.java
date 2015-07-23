package com.mtsmda.tools.gui.logic;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by c-DMITMINZ on 17.07.2015.
 */
public class WindowsServiceAndProcesses {

    private static final String SERVICE = "SC";
    public static final String SERVICE_START = "start";
    public static final String SERVICE_STOP = "stop";

    private static final String PROCESS_KILL = "TASKKILL";
    public static final String PROCESS_KILL_BY_NAME = "/IM";
    public static final String PROCESS_KILL_BY_ID = "/PID";
    private static final String PROCESS_KILL_TERMINATION = "/T";
    private static final String PROCESS_KILL_FORCEFULLY_TERMINATION = "/F";

    public static boolean service(String command, String serviceName) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(new String[]{SERVICE, command, serviceName});
            int f = process.waitFor();
            System.out.println(f);
            int c;
            while ((c = process.getInputStream().read()) != -1) {
                System.out.print((char) c);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Properties process(String processName, String startOrStop){
        Properties propertiesResult = new Properties();
        propertiesResult.setProperty(ResultEnum.PROCESS_NAME.name(), processName);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec(new String[]{SERVICE, startOrStop, processName});
                int f = process.waitFor();
                System.out.println(f);
                int c;
                while ((c = process.getInputStream().read()) != -1) {
                    stringBuilder.append(((char) c));
                }
                propertiesResult.setProperty(ResultEnum.RESULT.name(), stringBuilder.toString());
            }
            catch (Exception e){
                propertiesResult.setProperty(ResultEnum.EXCEPTION.name(), e.getMessage());
            }
        return propertiesResult;
    }

    public static String runBatFile(File fileName) throws Exception {
        StringBuilder stringBuilderResult = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(fileName.getAbsolutePath().substring(0,2) +  " cd " + fileName.getParent() + /*SERVICE_START + " " + */fileName.getAbsolutePath());
        int f = process.waitFor();
        System.out.println(f);
        int c;
        while ((c = process.getInputStream().read()) != -1) {
            stringBuilderResult.append(((char) c));
        }
        return stringBuilderResult.toString();
    }

    public static void main(String[] args) {
        /*process("EAMService", SERVICE_STOP);
        process("EdifecsTMETLResubmission", SERVICE_STOP);
        process("EdifecsTMServiceManager", SERVICE_STOP);
        process("ActiveMQ", SERVICE_STOP);
        process("Tomcat7", SERVICE_STOP);*/
        //process("AdobeARMservice", SERVICE_START);
    }
}
