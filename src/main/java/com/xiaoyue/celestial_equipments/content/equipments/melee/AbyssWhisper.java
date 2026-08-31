package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.IAttackConfig;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AbyssWhisper extends UpgradeableMelee implements IAttackConfig {
    public AbyssWhisper() {
        super(MeleeType.GENERIC);
    }

    @ConfigHolderEntry(category = "melee")
    public static IntConfigEntry cooldownConfig = IntConfigEntry.define("Abyss Whisper Cooldown Time",
            5, 1, 1000000, "Abyss Whisper: Cooldown time");

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineSmallRange("Abyss Whisper Damage Factor",
            0.05, "Abyss Whisper: Additional damage factor");

    @SubscribeTooltip(id = "abyss_whisper")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "After attacking, deal an additional amount of Abyssal Damage equal to %s of original damage");

    @Override
    public float getAttack(int lv) {
        return 1f;
    }

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(dmgConfig.get() * lv)));
        list.add(cooldownTooltip.withGray(TooltipEntry.num(cooldownConfig.get())));
    }

    @Override
    public void onMeleeHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        LivingEntity target = cache.getAttackTarget();
        if (this.noCooldown(attacker) && lv > 0) {
            float extraDamage = cache.getPreDamage() * dmgConfig.floatValue() * (float) lv;
            GeneralEventHandler.schedule(() -> target.hurt(CCDamageTypes.abyss(attacker), extraDamage));
            this.addCooldown(attacker, cooldownConfig.get() * 20);
        }
    }
}
