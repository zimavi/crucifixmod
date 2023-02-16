package net.zimavi.crucifixmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zimavi.crucifixmod.CrucifixKillState;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.ModGlobalFields;
import net.zimavi.crucifixmod.entity.ModEntities;
import net.zimavi.crucifixmod.entity.custom.ChainsEntity;
import net.zimavi.crucifixmod.item.ModItems;
import net.zimavi.crucifixmod.sound.ModSounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ModEvents {
    @Mod.EventBusSubscriber(modid = CrucifixMod.MOD_ID)
    public static class DeathEventHandler {
        @SubscribeEvent
        public static void onEnitySpawn(LivingSpawnEvent event){
            if (event.getEntity() instanceof ChainsEntity) {
                event.getEntity().noPhysics = true;
                event.getEntity().setNoAi(true);
            }
        }
        @SubscribeEvent
        public static void onEntityDeath(LivingDeathEvent event){
            if(event.getEntity() instanceof Player){
                Player player = (Player) event.getEntity();
                if(player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.CRUCIFIX.get())){
                    player.getItemInHand(InteractionHand.MAIN_HAND).setCount(0);
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 100));
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
                    event.getEntity().getLevel().playSound((Player) event.getEntity(),
                            event.getEntity().position().x, event.getEntity().position().y, event.getEntity().position().z,
                            ModSounds.CRUCIFIX_REANIMATION.get(), SoundSource.PLAYERS, 1f, 1f);
                    player.setHealth(3F);
                    event.setCanceled(true);
                    AABB aabb = new AABB(
                            player.position().x - 5,
                            player.position().y - 5,
                            player.position().z - 5,
                            player.position().x + 5,
                            player.position().y + 5,
                            player.position().z + 5
                    );
                    List<LivingEntity> list = player.getLevel().getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, player, aabb);
                    for (LivingEntity entity: list) {
                        CompoundTag compoundtag = new CompoundTag();
                        compoundtag.putString("id", "minecraft:lightning_bolt");

                        Entity bolt = EntityType.loadEntityRecursive(compoundtag, entity.level, (e) -> {
                            e.moveTo(entity.position().x, entity.position().y, entity.position().z, 0, 0);
                            return e;
                        });
                        player.level.addFreshEntity(bolt);
                        entity.hurt(new DamageSource("crucifix"), 50F);
                    }
                }
            }
        }
    }
    @Mod.EventBusSubscriber(modid = CrucifixMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put(ModEntities.CHAINS.get(), ChainsEntity.setAttributes());
        }

    }
    @Mod.EventBusSubscriber(modid = CrucifixMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEventBusEvents {

        @SubscribeEvent
        public static void tick(TickEvent.LevelTickEvent event){
            if(ModGlobalFields.CrucifixFields.IS_DIEING) {
                if(ModGlobalFields.CrucifixFields.CHAINS_ENTIY.getLevel().getGameTime() - ModGlobalFields.CrucifixFields.TIME >= ModGlobalFields.CrucifixFields.TIMER_SEC * 20 ){
                    if(ModGlobalFields.CrucifixFields.KILL_STATE == CrucifixKillState.EntityKill){
                        ModGlobalFields.CrucifixFields.KILL_STATE = CrucifixKillState.ChainsKill;
                        ModGlobalFields.CrucifixFields.TIMER_SEC = 6;
                        ModGlobalFields.CrucifixFields.DIEING_ENTITY.hurt(new DamageSource("crucifix"), 1000000000);
                        ModGlobalFields.CrucifixFields.DIEING_ENTITY = null;
                    } else if(ModGlobalFields.CrucifixFields.KILL_STATE == CrucifixKillState.ChainsKill) {
                        ModGlobalFields.CrucifixFields.IS_DIEING = false;
                        ModGlobalFields.CrucifixFields.TIME = 0;
                        ModGlobalFields.CrucifixFields.TIMER_SEC = 0;
                        ModGlobalFields.CrucifixFields.KILL_STATE = CrucifixKillState.EntityKill;
                        ModGlobalFields.CrucifixFields.CHAINS_ENTIY.kill();
                        ModGlobalFields.CrucifixFields.CHAINS_ENTIY = null;
                    } else if(ModGlobalFields.CrucifixFields.KILL_STATE == CrucifixKillState.LeaveBoss) {
                        ModGlobalFields.CrucifixFields.DIEING_ENTITY.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ModGlobalFields.CrucifixPlayerNBossFields.BASE_MOVEMENT_SPEED);
                        ModGlobalFields.CrucifixFields.KILL_STATE = CrucifixKillState.ChainsKill;
                        ModGlobalFields.CrucifixFields.TIMER_SEC = 6;
                        ModGlobalFields.CrucifixFields.DIEING_ENTITY = null;
                    }
                }
            }
        }
    }
}
