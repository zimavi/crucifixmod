package net.zimavi.crucifixmod.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.zimavi.crucifixmod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrucifixItem extends Item {
    public CrucifixItem(Properties properties) {
        super(properties);
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
    public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand hand) {
        BlockPos pos = new BlockPos(pPlayer.position().x, pPlayer.position().y, pPlayer.position().z);
        level.playSound(pPlayer, pPlayer.position().x, pPlayer.position().y, pPlayer.position().z,
                ModSounds.CRUCIFIX_USE.get(), SoundSource.PLAYERS, 1f, 1f);
        //pPlayer.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(1, pPlayer,(player -> player.broadcastBreakEvent(InteractionHand.MAIN_HAND)));
        pPlayer.getCooldowns().addCooldown(this, 600);

        return super.use(level, pPlayer, hand);
    }
}
