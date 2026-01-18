package coolaid.disablesigngui.fabric;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

import static coolaid.disablesigngui.DisableSignGUI.isSignGuiEnabled;

public final class DisableSignGUIFabric implements ModInitializer {

    private static final KeyMapping.Category DISABLE_CATEGORY =
            KeyMapping.Category.register(Identifier.parse("disablesigngui"));

    public static KeyMapping toggleGuiKey;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // REGISTER THE TOGGLE KEYBIND
        toggleGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.disablesigngui.toggle",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                DISABLE_CATEGORY
        ));

        // KEYPRESS LISTENER LOGIC
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleGuiKey.consumeClick()) {
                isSignGuiEnabled = !isSignGuiEnabled;
                // ACTIONBAR MESSAGE OR WHATEVER IT'S CALLED
                String messageKey = isSignGuiEnabled ? "message.toggleGuiKey.disabled" : "message.toggleGuiKey.enabled";
                ChatFormatting color = isSignGuiEnabled ? ChatFormatting.RED : ChatFormatting.GREEN;
                // SEND ACTIONBAR MESSAGE
                client.player.displayClientMessage(
                        Component.translatable(messageKey).withStyle(color),
                        true
                );
            }
        });

    }
}
