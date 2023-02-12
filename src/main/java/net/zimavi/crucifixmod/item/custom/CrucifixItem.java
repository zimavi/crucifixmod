package net.zimavi.crucifixmod.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.zimavi.crucifixmod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrucifixItem extends Item {
    public CrucifixItem(Properties properties) {
        super(properties);
    }


    private Player player;


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("tooltip.crucifixmod.crucifix_detail"));
        } else {
            components.add(Component.translatable("tooltip.crucifixmod.crucifix"));
        }


        super.appendHoverText(stack, level, components, flag);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        this.player = player;
        //Item change

        //Entity interaction
        if(entity instanceof Warden == false && entity instanceof EnderDragon == false && entity instanceof WitherBoss == false && entity instanceof Player == false) {
            player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                    ModSounds.CRUCIFIX_USE.get(), SoundSource.PLAYERS, 1f, 1f);
            entity.hurt(new DamageSource("crucifix"), 1000000000);
        }
        else if (entity instanceof Player) {
            player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                    ModSounds.CRUCIFIX_USE.get(), SoundSource.PLAYERS, 1f, 1f);
            entity.hurt(new DamageSource("crucifix"), 1000000000);
        }
        else {
            player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                    ModSounds.CRUCIFIX_FAIL.get(), SoundSource.PLAYERS, 1f, 1f);
        }

        player.getItemInHand(hand).setCount(0);
        return super.interactLivingEntity(stack, player, entity, hand);
    }
    
    /*
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand hand) {
        level.playSound(pPlayer, pPlayer.position().x, pPlayer.position().y, pPlayer.position().z,
                ModSounds.CRUCIFIX_USE.get(), SoundSource.PLAYERS, entity.hurt(DamageSource.MAGIC, 200000);1f, 1f);
        //pPlayer.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(1, pPlayer,(player -> player.broadcastBreakEvent(InteractionHand.MAIN_HAND)));
        pPlayer.getCooldowns().addCooldown(this, 600);

        return super.use(level, pPlayer, hand);
    }
     */
}
