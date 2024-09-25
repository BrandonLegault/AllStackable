package online.connlost.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net/minecraft/screen/HorseScreenHandler$1")
public class MixinHorseScreenHandler extends Slot {
    public MixinHorseScreenHandler(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
