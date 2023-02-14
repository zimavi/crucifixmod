package net.zimavi.crucifixmod.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.zimavi.crucifixmod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import org.openjdk.nashorn.internal.runtime.Debug;

import java.util.List;

public class CrucifixItem extends Item {
    public CrucifixItem(Properties properties) {
        super(properties);
    }

    private Entity addChainsEntity(double x, double y, double z, Level level){
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString("id", "crucifixmod:chains");

        return EntityType.loadEntityRecursive(compoundTag, level, (e) -> {
            e.moveTo(x, y, z, 0f, 0f);
            return e;
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("tooltip.crucifixmod.crucifix_detail"));
        } else {
            components.add(Component.translatable("tooltip.crucifixmod.crucifix"));
        }


        super.appendHoverText(stack, level, components, flag);
    }


    private void crucifixTimerKill(Entity entity, Entity chains) {
        long time = chains.getLevel().getGameTime();
        long curr = time;
        while (time - curr != 5 * 20) {
            curr = chains.getLevel().getGameTime();
        }
        entity.hurt(new DamageSource("crucifix"), 1000000000);

        curr = time;
        while (time - curr != 8 * 20) {
            curr = chains.getLevel().getGameTime();
        }
        chains.kill();
    }


    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        //Item change

        //Entity interaction
        if(entity instanceof Warden == false && entity instanceof EnderDragon == false && entity instanceof WitherBoss == false && entity instanceof Player == false) {
            player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                    ModSounds.CRUCIFIX_USE.get(), SoundSource.PLAYERS, 1f, 1f);
            //entity.hurt(new DamageSource("crucifix"), 1000000000);
            Vec3 pos = new Vec3(0f, 0.2f, 0f);
            entity.move(MoverType.SELF, pos);
            entity.setNoGravity(true);
            entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0D);
            Entity chains = addChainsEntity(
                    entity.position().x,
                    entity.position().y,
                    entity.position().z,
                    entity.getLevel());
            player.getLevel().addFreshEntity(chains);
            crucifixTimerKill(entity, chains);
        }
        else if (entity instanceof Player
        ) {
            player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                    ModSounds.CRUCIFIX_USE.get(), SoundSource.PLAYERS, 1f, 1f);
            //entity.hurt(new DamageSource("crucifix"), 1000000000);
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
