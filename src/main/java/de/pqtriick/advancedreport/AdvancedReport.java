package de.pqtriick.advancedreport;

import de.pqtriick.advancedreport.commands.player.Cancelreporrt;
import de.pqtriick.advancedreport.commands.player.Report;
import de.pqtriick.advancedreport.commands.player.ReportChat;
import de.pqtriick.advancedreport.commands.team.Closereport;
import de.pqtriick.advancedreport.commands.team.Viewreports;
import de.pqtriick.advancedreport.files.*;
import de.pqtriick.advancedreport.files.configs.DiscordConfig;
import de.pqtriick.advancedreport.files.configs.MessageConfig;
import de.pqtriick.advancedreport.files.configs.ReportConfig;
import de.pqtriick.advancedreport.files.configs.ReportlogConfig;
import de.pqtriick.advancedreport.listener.inventory.ReportClickListener;
import de.pqtriick.advancedreport.listener.inventory.MainGUIListener;
import de.pqtriick.advancedreport.listener.report.QuitListener;
import de.pqtriick.advancedreport.util.Metrics;
import de.pqtriick.advancedreport.util.VersionCheck;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedReport extends JavaPlugin {

    public static AdvancedReport instance;
    public static boolean hasUpdate;
    public static boolean hasDCBot = false;
    public static String botToken = "MTA3OTc2NjUyNjM1MTEyNjYzOA.GbybQN.5jxUEDxQw5qvXud8uFW692uNdzergCwtS5-S5s";
    private static JDABuilder builder;
    private static JDA bot;

    @Override
    public void onEnable() {
        instance = this;
        initConfig();
        initBot();
        this.getCommand("report").setExecutor(new Report());
        this.getCommand("viewreports").setExecutor(new Viewreports());
        this.getCommand("cancelreport").setExecutor(new Cancelreporrt());
        this.getCommand("rc").setExecutor(new ReportChat());
        this.getCommand("closereport").setExecutor(new Closereport());
        Bukkit.getPluginManager().registerEvents(new MainGUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new ReportClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
        Metrics metrics = new Metrics(this, 20538);
        checkUpdate();



    }

    @Override
    public void onDisable() {
    }

    public static AdvancedReport getInstance() {
        return instance;
    }

    private void initConfig() {
        new Config();
        new MessageConfig();
        new ReportConfig();
        new ReportlogConfig();
        new DiscordConfig();
        //new DiscordConfig();

    }
    public boolean checkUpdate() {
        new VersionCheck(this, 114085).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                hasUpdate = false;
            } else {
                hasUpdate = true;
            }
        });
        return hasUpdate;
    }

    private void initBot() {
        if (DiscordConfig.DiscordConfiguration.getString("options.enabled").equalsIgnoreCase("true")) {
            botToken = DiscordConfig.DiscordConfiguration.getString("options.bottoken");
            builder = JDABuilder.createDefault(botToken);
            bot = builder.build();
            hasDCBot = true;

        }
    }

    public static JDA getJDA() {
        return bot;
    }



}
