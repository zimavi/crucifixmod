package net.zimavi.crucifixmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.zimavi.crucifixmod.CrucifixKillState;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.ModGlobalFields;
import net.zimavi.crucifixmod.effect.ModEffects;
import net.zimavi.crucifixmod.entity.ModEntities;
import net.zimavi.crucifixmod.entity.custom.ChainsEntity;
import net.zimavi.crucifixmod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import org.openjdk.nashorn.internal.runtime.Debug;

import java.util.List;

public class CrucifixItem extends Item {
    public CrucifixItem(Properties properties) {
        super(properties);
    }

    private Entity addOneEntity(double x, double y, double z, Level level) {
        ChainsEntity chains = new ChainsEntity(ModEntities.CHAINS.get(), level);
        chains.setPos(x, y, z);
        return chains;
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


    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        if(!ModGlobalFields.CrucifixFields.IS_DIEING) {
            //Item change

            if (entity instanceof ChainsEntity) return InteractionResult.SUCCESS;

            //Entity interaction
            if (entity instanceof Warden == false && entity instanceof EnderDragon == false && entity instanceof WitherBoss == false && entity instanceof Player == false) {
                if (entity.hasEffect(ModEffects.DEVILISH_RESILIENCE_EFFECT.get())) {
                    player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                            ModSounds.CRUCIFIX_FAIL.get(), SoundSource.PLAYERS, 1f, 1f);
                    ModGlobalFields.CrucifixPlayerNBossFields.BASE_MOVEMENT_SPEED = entity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue();
                    entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 70, 200, true, false));
                    Vec3 pos = new Vec3(0f, 0.2f, 0f);
                    entity.move(MoverType.SELF, pos);
                    entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0D);
                    ChainsEntity chains = (ChainsEntity) addOneEntity(
                            entity.position().x,
                            entity.position().y,
                            entity.position().z,
                            entity.getLevel());
                    player.getLevel().addFreshEntity(chains);
                    //Set fields for Tick event
                    ModGlobalFields.CrucifixFields.CHAINS_ENTIY = chains;
                    ModGlobalFields.CrucifixFields.TIMER_SEC = 3.5;
                    ModGlobalFields.CrucifixFields.DIEING_ENTITY = entity;
                    ModGlobalFields.CrucifixFields.TIME = entity.getLevel().getGameTime();
                    ModGlobalFields.CrucifixFields.KILL_STATE = CrucifixKillState.LeaveBoss;
                    ModGlobalFields.CrucifixFields.IS_DIEING = true;
                } else {
                    player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                            ModSounds.CRUCIFIX_USE.get(), SoundSource.PLAYERS, 1f, 1f);
                    //entity.hurt(new DamageSource("crucifix"), 1000000000);
                    Vec3 pos = new Vec3(0f, 0.2f, 0f);
                    entity.move(MoverType.SELF, pos);
                    entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0D);
                    ChainsEntity chains = (ChainsEntity) addOneEntity(
                            entity.position().x,
                            entity.position().y,
                            entity.position().z,
                            entity.getLevel());
                    if(chains == null){
                        player.sendSystemMessage(Component.literal("Chains entity is null!!!")
                                .withStyle(ChatFormatting.RED));
                    }
                    player.getLevel().addFreshEntity(chains);
                    //Set fields for Tick event
                    ModGlobalFields.CrucifixFields.CHAINS_ENTIY = chains;
                    ModGlobalFields.CrucifixFields.TIMER_SEC = 3.5;
                    ModGlobalFields.CrucifixFields.DIEING_ENTITY = entity;
                    ModGlobalFields.CrucifixFields.TIME = entity.getLevel().getGameTime();
                    ModGlobalFields.CrucifixFields.KILL_STATE = CrucifixKillState.EntityKill;
                    ModGlobalFields.CrucifixFields.IS_DIEING = true;
                }
            } else if (entity instanceof Player) {
                if (entity.hasEffect(ModEffects.DEVILISH_RESILIENCE_EFFECT.get())) {
                    player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                            ModSounds.CRUCIFIX_FAIL.get(), SoundSource.PLAYERS, 1f, 1f);
                    ModGlobalFields.CrucifixPlayerNBossFields.BASE_MOVEMENT_SPEED = entity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue();
                    Vec3 pos = new Vec3(0f, 0.2f, 0f);
                    entity.move(MoverType.SELF, pos);
                    entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0D);
                    ChainsEntity chains = (ChainsEntity) addOneEntity(
                            entity.position().x,
                            entity.position().y,
                            entity.position().z,
                            entity.getLevel());
                    player.getLevel().addFreshEntity(chains);
                    //Set fields for Tick event
                    ModGlobalFields.CrucifixFields.CHAINS_ENTIY = chains;
                    ModGlobalFields.CrucifixFields.TIMER_SEC = 3.5;
                    ModGlobalFields.CrucifixFields.DIEING_ENTITY = entity;
                    ModGlobalFields.CrucifixFields.TIME = entity.getLevel().getGameTime();
                    ModGlobalFields.CrucifixFields.KILL_STATE = CrucifixKillState.LeaveBoss;
                    ModGlobalFields.CrucifixFields.IS_DIEING = true;
                } else {
                    player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                            ModSounds.CRUCIFIX_USE.get(), SoundSource.PLAYERS, 1f, 1f);
                    Vec3 pos = new Vec3(0f, 0.2f, 0f);
                    ModGlobalFields.CrucifixPlayerNBossFields.BASE_MOVEMENT_SPEED = entity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue();
                    entity.move(MoverType.SELF, pos);
                    entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0D);
                    ChainsEntity chains = (ChainsEntity) addOneEntity(
                            entity.position().x,
                            entity.position().y,
                            entity.position().z,
                            entity.getLevel());
                    entity.getLevel().addFreshEntity(chains);
                    //Set fields for Tick event
                    ModGlobalFields.CrucifixFields.CHAINS_ENTIY = chains;
                    ModGlobalFields.CrucifixFields.TIMER_SEC = 3.5;
                    ModGlobalFields.CrucifixFields.DIEING_ENTITY = entity;
                    ModGlobalFields.CrucifixFields.TIME = entity.getLevel().getGameTime();
                    ModGlobalFields.CrucifixFields.KILL_STATE = CrucifixKillState.EntityKill;
                    ModGlobalFields.CrucifixFields.IS_DIEING = true;
                }
            } else {
                player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                        ModSounds.CRUCIFIX_FAIL.get(), SoundSource.PLAYERS, 1f, 1f);
                ModGlobalFields.CrucifixPlayerNBossFields.BASE_MOVEMENT_SPEED = entity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue();
                Vec3 pos = new Vec3(0f, 0.2f, 0f);
                entity.move(MoverType.SELF, pos);
                entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0D);
                ChainsEntity chains = (ChainsEntity) addOneEntity(
                        entity.position().x,
                        entity.position().y,
                        entity.position().z,
                        entity.getLevel());
                player.getLevel().addFreshEntity(chains);
                //Set fields for Tick event
                ModGlobalFields.CrucifixFields.CHAINS_ENTIY = chains;
                ModGlobalFields.CrucifixFields.TIMER_SEC = 3.5;
                ModGlobalFields.CrucifixFields.DIEING_ENTITY = entity;
                ModGlobalFields.CrucifixFields.TIME = entity.getLevel().getGameTime();
                ModGlobalFields.CrucifixFields.KILL_STATE = CrucifixKillState.LeaveBoss;
                ModGlobalFields.CrucifixFields.IS_DIEING = true;
            }
            player.getItemInHand(hand).setCount(0);
        }
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
