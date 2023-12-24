package de.pqtriick.advancedreport.util;

import de.pqtriick.advancedreport.AdvancedReport;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author pqtriick_
 * @created 17:12, 24.12.2023
 */

public class VersionCheck {

    private final JavaPlugin plugin;
    private final int resourceID;

    public VersionCheck(JavaPlugin plugin, int resourceID) {
        this.plugin = plugin;
        this.resourceID = resourceID;
    }

    public void getVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(AdvancedReport.getInstance(), () -> {
            try {
                InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceID).openStream();
                Scanner s = new Scanner(inputStream);
                if (s.hasNext()) {
                    consumer.accept(s.next());
                }
            } catch (IOException e) {
                AdvancedReport.getInstance().getLogger().info("Unable to check for update. " + e.getMessage());
            }
        });
    }
}
