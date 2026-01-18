package coolaid.disablesigngui.neoforge;

import coolaid.disablesigngui.DisableSignGUI;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

@Mod(DisableSignGUINeoForge.MOD_ID)
public class DisableSignGUINeoForge {
    public static final String MOD_ID = "disablesigngui";

    private static final KeyMapping.Category DISABLE_CATEGORY =
            KeyMapping.Category.register(ResourceLocation.parse("disablesigngui"));

    public static KeyMapping toggleGuiKey;

    public DisableSignGUINeoForge(IEventBus modEventBus) {
        // Register mod event listeners
        modEventBus.addListener(this::onClientSetup);
        modEventBus.addListener(this::onRegisterKeyMappings);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        // Register game event listener for tick events
        NeoForge.EVENT_BUS.addListener(this::onClientTick);
    }

    private void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        toggleGuiKey = new KeyMapping(
                "key.disablesigngui.toggle",
                GLFW.GLFW_KEY_G,
                DISABLE_CATEGORY
        );
        event.register(toggleGuiKey);
    }

    private void onClientTick(ClientTickEvent.Post event) {
        if (toggleGuiKey != null && toggleGuiKey.consumeClick()) {
            DisableSignGUI.isSignGuiEnabled = !DisableSignGUI.isSignGuiEnabled;

            String messageKey = DisableSignGUI.isSignGuiEnabled ?
                    "message.toggleGuiKey.disabled" : "message.toggleGuiKey.enabled";
            ChatFormatting color = DisableSignGUI.isSignGuiEnabled ?
                    ChatFormatting.RED : ChatFormatting.GREEN;

            if (net.minecraft.client.Minecraft.getInstance().player != null) {
                net.minecraft.client.Minecraft.getInstance().player.displayClientMessage(
                        Component.translatable(messageKey).withStyle(color),
                        true
                );
            }
        }
    }
}