package me.connlost.allstackable.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MushroomStewItem.class)
public class MixinMushroomStewItem {

    @Redirect(method = "finishUsing", at=@At(value = "NEW", target = "net/minecraft/item/ItemStack"))
    public ItemStack decreaseStewAndInsertBowl(ItemConvertible itemConvertible, ItemStack itemStack, World world, LivingEntity user){
        if (user instanceof PlayerEntity){
            ((PlayerEntity)user).inventory.insertStack(new ItemStack(Items.BOWL));
        }
        return itemStack;
    }
}