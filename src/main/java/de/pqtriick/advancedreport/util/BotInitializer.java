package de.pqtriick.advancedreport.util;

import de.pqtriick.advancedreport.AdvancedReport;
import de.pqtriick.advancedreport.files.configs.DiscordConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

/**
 * @author pqtriick_
 * @created 17:05, 01.01.2024
 */

public class BotInitializer {

    public static String botToken;
    private static JDABuilder builder;
    private static JDA bot;

    public static void initBot() throws LoginException {
        if (DiscordConfig.DiscordConfiguration.getString("options.enabled").equalsIgnoreCase("true")) {
            botToken = DiscordConfig.DiscordConfiguration.getString("options.bottoken");
            builder = JDABuilder.createDefault(botToken);
            bot = builder.build();
            AdvancedReport.hasDCBot = true;

        }
    }

    public static JDA getJDA() {
        return bot;
    }

}
