package net.zimavi.crucifixmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VitaminsItem extends Item {
    public VitaminsItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND && !player.getCooldowns().isOnCooldown(this)) {
            //add effects and heal
            if(player.getMaxHealth() - player.getHealth() >= 2)
                player.heal(2F);
            if(20 - player.getFoodData().getFoodLevel() >= 2)
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + 2);
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 4, true, true));
            //add cooldown
            player.getCooldowns().addCooldown(this, 600);
            player.getItemInHand(hand).setCount(player.getItemInHand(hand).getCount()-1);
        }
        return super.use(level, player, hand);
    }
}
