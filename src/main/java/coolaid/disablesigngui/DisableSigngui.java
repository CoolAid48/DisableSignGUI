package coolaid.disablesigngui;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisableSigngui implements ModInitializer {
	public static final String MOD_ID = "disablesigngui";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static KeyBinding toggleGuiKey;
	public static boolean isSignGuiEnabled = true;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.



		// REGISTER THE TOGGLE KEYBIND
		toggleGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.disablesigngui.toggle",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_G,
				"category.disablesigngui"
		));

		// KEYPRESS LISTENER LOGIC
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (toggleGuiKey.wasPressed()) {
				isSignGuiEnabled = !isSignGuiEnabled;
				// ACTIONBAR MESSAGE OR WHATEVER IT'S CALLED
				String messageKey = isSignGuiEnabled ? "message.toggleGuiKey.disabled" : "message.toggleGuiKey.enabled";
				Formatting color = isSignGuiEnabled ? Formatting.RED : Formatting.GREEN;
				// SEND ACTIONBAR MESSAGE
				client.player.sendMessage(
						Text.translatable(messageKey).formatted(color),
						true
				);
			}
		});

		LOGGER.info("The GUI is (not) Signing... get it?!");
		LOGGER.info("Check out my Hardcore World on Twitch!");
	}
}