package com.xiaoyue.celestial_equipments.content.library;

import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;

public enum BowType {
    SHORT_BOW(0.8f, 0.8f, 1.0f),
    LONG_BOW(1.1f, 1.0f, 1.2f);

    public final float attack;
    public final float arrowSpeed;
    public final float drawSpeed;

    BowType(float attack, float arrowSpeed, float drawSpeed) {
        this.attack = attack;
        this.arrowSpeed = arrowSpeed;
        this.drawSpeed = drawSpeed;
    }

    public float getArrowSpeed(float bow) {
        return this.arrowSpeed + bow;
    }

    public float getDrawSpeed(float bow) {
        return this.drawSpeed + bow;
    }

    public float getAttack(int level, float bow) {
        float typeAttack;
        switch (this) {
            case LONG_BOW -> typeAttack = level;
            case SHORT_BOW -> typeAttack = 0.5f * level;
            default -> throw new IncompatibleClassChangeError();
        }
        float attack = typeAttack;
        return bow + attack + this.attack;
    }

    @SubscribeTooltip(id = "bow_type_long")
    public static final TooltipEntry longBowTooltip = TooltipEntry.define("[Long Bow]");

    @SubscribeTooltip(id = "bow_type_short")
    public static final TooltipEntry shortBowTooltip = TooltipEntry.define("[Short Bow]");

    public MutableComponent getLang() {
        MutableComponent text;
        switch (this) {
            case LONG_BOW -> text = longBowTooltip.withColor(ChatFormatting.BLUE);
            case SHORT_BOW -> text = shortBowTooltip.withColor(ChatFormatting.BLUE);
            default -> throw new IncompatibleClassChangeError();
        }
        return text;
    }
}
