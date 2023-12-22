package de.pqtriick.advancedreport.files.configs;

import de.pqtriick.advancedreport.files.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pqtriick_
 * @created 18:24, 07.12.2023
 */

public class ReportlogConfig {

    private static int line;


    public static File Reportdir = new File(Config.directory.getPath() + "/reportlog");



    public ReportlogConfig() {
        if (!Reportdir.exists()) {
            Reportdir.mkdir();
        }
    }

    public static void saveLog(File file, String message) {
        Config.createFile(file);
        if (file.length() == 0) {
            line = 0;
            Config.set(Config.getConfiguration(file), file, "log." + line, message);
            Config.saveFile(Config.getConfiguration(file), file);
            line++;
        } else {
            Config.set(Config.getConfiguration(file), file, "log." + line, message);
            Config.saveFile(Config.getConfiguration(file), file);
            line++;
        }

    }
}
