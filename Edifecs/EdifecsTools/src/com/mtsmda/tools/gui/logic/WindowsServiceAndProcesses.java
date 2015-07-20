package com.mtsmda.tools.gui.logic;

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

    public static boolean process(String processName, String startOrStop){
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(new String[]{SERVICE, startOrStop, processName});
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

    public static void main(String[] args) {
        process("EAMService", SERVICE_STOP);
        process("EdifecsTMETLResubmission", SERVICE_STOP);
        process("EdifecsTMServiceManager", SERVICE_STOP);
        process("ActiveMQ", SERVICE_STOP);
        process("Tomcat7", SERVICE_STOP);
        //process("AdobeARMservice", SERVICE_START);
    }
}
