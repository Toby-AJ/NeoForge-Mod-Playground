package net.tobyaj.playgroundmod.item.custom;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tobyaj.playgroundmod.item.ModItems;

public class SpiritRodItem extends Item {

    private static final float SPIRIT_DROP_CHANCE = 0.35f;

    public SpiritRodItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        if (!attacker.level().isClientSide && target instanceof Animal)
        {

            RandomSource random = attacker.level().getRandom();

            // 45% chance
            if (random.nextFloat() < SPIRIT_DROP_CHANCE) {

                // Random amount: 1–3
                int amount = 1 + random.nextInt(3);

                ItemStack drop = new ItemStack(ModItems.RAW_SPIRIT.get(), amount);
                target.spawnAtLocation(drop);
            }

            stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        }

        return super.hurtEnemy(stack, target, attacker);
    }
}