package coolaid.disablesigngui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DisableSignGUI {
    public static final String MOD_ID = "assets/disablesigngui";

    public static boolean isSignGuiEnabled = true;

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.

    public static void init() {
        LOGGER.info("The sign is NOT GUI-ing... Check out my Hardcore World on Twitch!");
    }
}
