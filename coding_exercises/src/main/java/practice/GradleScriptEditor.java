package practice;

import java.io.*;

public class GradleScriptEditor {

    public static void main(String[] args) throws IOException {
        String command_to_be_executed = "gradle -b ./build.gradle buildDependents -s -d";
        String directory_location = "C:\\23.2\\Code_Workspace\\assap";
//        gradle -b ./build.gradle buildDependents -s -d

        String[] command = {".gradle -b", "./build.gradle", "buildDependents", "-s", "-d"};
        ProcessBuilder builder = new ProcessBuilder(command);
        builder = builder.directory(new File(directory_location));

        Process p = builder.start();
        OutputStream outputStream = new FileOutputStream(new File(directory_location+ "/assap_build_log1.log"));
        outputStream.write(p.getInputStream().readAllBytes());
        outputStream.close();
    }
}
