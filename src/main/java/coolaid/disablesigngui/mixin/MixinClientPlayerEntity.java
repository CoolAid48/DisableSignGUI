package coolaid.disablesigngui.mixin;

import coolaid.disablesigngui.DisableSigngui;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// I DON'T LIKE MIXINS
@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity {

	@Inject(method = "openEditSignScreen(Lnet/minecraft/block/entity/SignBlockEntity;Z)V", at = @At("HEAD"), cancellable = true)
	private void onOpenEditSignScreen(SignBlockEntity sign, boolean front, CallbackInfo info) {
		if (!DisableSigngui.isSignGuiEnabled) {
			info.cancel();
		}
	}
}