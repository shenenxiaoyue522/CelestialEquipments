package com.xiaoyue.celestial_equipments.content.library;

import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;

public enum MeleeType {
    BROAD(1.5f, -2.8f),
    GENERIC(1.0f, -2.4f),
    SMALL(0.5f, -2.0f);

    private final float multiAttack;
    private final float baseSpeed;

    MeleeType(float multiAttack, float baseSpeed) {
        this.multiAttack = multiAttack;
        this.baseSpeed = baseSpeed;
    }

    public float getMultiAttack() {
        return this.multiAttack;
    }

    public float getBaseSpeed() {
        return this.baseSpeed;
    }

    public float getActualAttack(int level) {
        return this.multiAttack * level;
    }

    public float getTypeAttack(float base) {
        float typeAttack;
        switch (this) {
            case BROAD -> typeAttack = 10.0f;
            case GENERIC -> typeAttack = 8.0f;
            case SMALL -> typeAttack = 6.0f;
            default -> throw new IncompatibleClassChangeError();
        }
        return typeAttack + base;
    }

    @SubscribeTooltip(id = "melee_type_broad")
    public static final TooltipEntry meleeTypeBroadInfo = TooltipEntry.define("Sword type: broad");

    @SubscribeTooltip(id = "melee_type_generic")
    public static final TooltipEntry meleeTypeGenericInfo = TooltipEntry.define("Sword type: generic");

    @SubscribeTooltip(id = "melee_type_small")
    public static final TooltipEntry meleeTypeSmallInfo = TooltipEntry.define("Sword type: small");

    public MutableComponent getLang() {
        MutableComponent text;
        switch (this) {
            case BROAD -> text = meleeTypeBroadInfo.withColor(ChatFormatting.BLUE);
            case GENERIC -> text = meleeTypeGenericInfo.withColor(ChatFormatting.BLUE);
            case SMALL -> text = meleeTypeSmallInfo.withColor(ChatFormatting.BLUE);
            default -> throw new IncompatibleClassChangeError();
        }
        return text;
    }
}
