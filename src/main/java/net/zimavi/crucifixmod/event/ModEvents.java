package net.zimavi.crucifixmod.event;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.item.ModItems;
import net.zimavi.crucifixmod.sound.ModSounds;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = CrucifixMod.MOD_ID)
    public static class DeathEventHandler {
        @SubscribeEvent
        public static void onEntityDeath(LivingDeathEvent event){
            LivingEntity e = event.getEntity();

            if(e instanceof Player){
                Player player = (Player) e;
                if(player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.CRUCIFIX.get())){
                    player.getItemInHand(InteractionHand.MAIN_HAND).setCount(0);
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 100));
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
                    player.getLevel().playSound(player, player.position().x, player.position().y, player.position().z,
                            ModSounds.CRUCIFIX_REANIMATION.get(), SoundSource.PLAYERS, 1f, 1f);
                    player.setHealth(3F);
                    event.setCanceled(true);
                }
            }
        }
    }
}
