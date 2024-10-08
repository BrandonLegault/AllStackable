package online.connlost.mixin;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import online.connlost.util.IItemMaxCount;
import net.minecraft.item.Item;
import online.connlost.util.ItemsHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class MixinItem implements IItemMaxCount {
    @Mutable
    @Final
    @Shadow private ComponentMap components;

    @Unique
    private int vanillaMaxCount;

    @Override
    public void revert() {
        setMaxCount(vanillaMaxCount);
    }

    @Override
    public void setMaxCount(int i) {
        ComponentMap.Builder builder = ComponentMap.builder().addAll(this.components);
        builder.add(DataComponentTypes.MAX_STACK_SIZE, i);
        this.components = builder.build();
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
        setVanillaMaxCount((Integer) this.components.getOrDefault(DataComponentTypes.MAX_STACK_SIZE, ItemsHelper.ItemMaxCount));
    }

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    private void injectGetMaxCount(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue((Integer) this.components.getOrDefault(DataComponentTypes.MAX_STACK_SIZE, ItemsHelper.ItemMaxCount));
    }

    @Redirect(method = "isEnchantable", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxCount()I"))
    private int redirectGetMaxCount(ItemStack instance) {
        return ((IItemMaxCount) instance.getItem()).getVanillaMaxCount();
    }
}
