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
import de.pqtriick.advancedreport.listener.inventory.AcceptReportListener;
import de.pqtriick.advancedreport.listener.inventory.MainGUIListener;
import de.pqtriick.advancedreport.listener.report.QuitListener;
import de.pqtriick.advancedreport.util.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedReport extends JavaPlugin {

    public static AdvancedReport instance;

    @Override
    public void onEnable() {
        instance = this;
        initConfig();
        this.getCommand("report").setExecutor(new Report());
        this.getCommand("viewreports").setExecutor(new Viewreports());
        this.getCommand("cancelreport").setExecutor(new Cancelreporrt());
        this.getCommand("rc").setExecutor(new ReportChat());
        this.getCommand("closereport").setExecutor(new Closereport());
        Bukkit.getPluginManager().registerEvents(new MainGUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new AcceptReportListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);

        Metrics metrics = new Metrics(this, 20538);

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
        //new DiscordConfig();

    }


}
