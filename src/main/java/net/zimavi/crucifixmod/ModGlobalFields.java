package net.zimavi.crucifixmod;

import net.minecraft.world.entity.LivingEntity;
import net.zimavi.crucifixmod.entity.custom.ChainsEntity;

import javax.swing.text.html.parser.Entity;

public class ModGlobalFields {
    public static class CrucifixFields {
        public static boolean IS_DIEING = false;
        public static LivingEntity DIEING_ENTITY = null;
        public static long TIME = 0;
        public static ChainsEntity CHAINS_ENTIY = null;
        public static double TIMER_SEC = 0;
        public static CrucifixKillState KILL_STATE = CrucifixKillState.EntityKill;
    }
    public static class CrucifixPlayerNBossFields {
        public static double BASE_MOVEMENT_SPEED = 0D;
    }
}
