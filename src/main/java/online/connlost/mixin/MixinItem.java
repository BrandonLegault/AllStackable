package online.connlost.mixin;

import online.connlost.util.IItemMaxCount;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class MixinItem implements IItemMaxCount {
    @Final
    @Mutable
    @Shadow
    private int maxCount;

    private int vanillaMaxCount;

    @Override
    public void revert() {
        this.maxCount = vanillaMaxCount;
    }


    @Override
    public void setMaxCount(int i) {
        this.maxCount = i;
    }

    @Override
    public int getVanillaMaxCount() {
        return vanillaMaxCount;
    }

    @Override
    public void setVanillaMaxCount(int vanillaMaxCount) {
        this.vanillaMaxCount = vanillaMaxCount;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void setVanillaMaxCount(Item.Settings settings, CallbackInfo ci) {
        setVanillaMaxCount(this.maxCount);
    }

    @Redirect(method = "isEnchantable", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getMaxCount()I"))
    private int isVanillaEnchantable(Item item) {
        return ((IItemMaxCount) item).getVanillaMaxCount();
    }

}
