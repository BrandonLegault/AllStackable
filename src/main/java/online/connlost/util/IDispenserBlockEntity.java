package online.connlost.util;

import net.minecraft.item.ItemStack;

public interface IDispenserBlockEntity {
    boolean tryInsertAndStackItem(ItemStack itemStack);
}
