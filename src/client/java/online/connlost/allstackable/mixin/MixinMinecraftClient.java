package online.connlost.allstackable.mixin;

import online.connlost.allstackable.ConfigSync;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {
    @Inject(method = "disconnect()V", at=@At("RETURN"))
    private void resetMaxCount(CallbackInfo ci){
        ConfigSync.resetConfig();
    }
}
