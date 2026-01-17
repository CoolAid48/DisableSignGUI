package coolaid.disablesigngui.neoforge.mixin;

import coolaid.disablesigngui.DisableSignGUI;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// I DON'T LIKE MIXINS
@Mixin(LocalPlayer.class)
public abstract class MixinClientPlayerEntity {

    @Inject(method = "openTextEdit", at = @At("HEAD"), cancellable = true)
    private void onOpenEditSignScreen(SignBlockEntity sign, boolean front, CallbackInfo info) {
        if (!DisableSignGUI.isSignGuiEnabled) {
            info.cancel();
        }
    }
}