package com.xiaoyue.celestial_equipments.content.items.generic;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.library.ICelestialEquip;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpgradeableMelee extends Item implements ICelestialEquip {
    public final MeleeType type;

    public UpgradeableMelee(MeleeType type) {
        super(new Item.Properties().stacksTo(1).durability(8000));
        this.type = type;
    }

    @SubscribeTooltip(id = "sweep_range_bonus")
    public static TooltipEntry sweepBonusTooltip = TooltipEntry.define("Sweep range increased by %s");

    public float getAttack(int lv) {
        return 0f;
    }

    public float getSpeed(int lv) {
        return 0f;
    }

    @Override
    public Component getName(ItemStack pStack) {
        return this.getItemName(pStack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(this.type.getLang());
        this.addBaseTooltips(stack, list);
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBanTooltip.withGray());
        }
        if (stack.isEnchanted()) {
            list.add(Component.empty());
        }
    }

    @Override
    public boolean requiredShiftDown() {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modify = LinkedHashMultimap.create();
        int lv = EquipmentUtils.getLevel(stack);
        if (slot.equals(EquipmentSlot.MAINHAND)) {
            float attack = this.type.getActualAttack(lv) + this.type.getTypeAttack(this.getAttack(lv));
            float speed = this.type.getBaseSpeed() + this.getSpeed(lv);
            AttributeAdder.builder().uuid(BASE_ATTACK_DAMAGE_UUID).value(attack).toMap(modify)
                            .attr(Attributes.ATTACK_SPEED).uuid(BASE_ATTACK_SPEED_UUID).value(speed).toMap(modify);
        }
        this.modify(slot, stack, EquipmentUtils.getLevel(stack), slot.equals(EquipmentSlot.MAINHAND), modify);
        return modify;
    }

    protected void modify(EquipmentSlot slot, ItemStack stack, int lv, boolean selected, Multimap<Attribute, AttributeModifier> modify) {
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (!(pAttacker instanceof Player player) || EntityUtils.isFullCharged(player)) {
            this.attacked(pStack, pTarget, pAttacker, EquipmentUtils.getLevel(pStack));
        }
        pStack.hurtAndBreak(2, pAttacker, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        return true;
    }

    protected void attacked(ItemStack stack, LivingEntity target, LivingEntity attacker, int lv) {
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category.equals(EnchantmentCategory.WEAPON);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }

    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiningEntity) {
        if (pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
            pStack.hurtAndBreak(2, pMiningEntity, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return pState.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
        }
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState pBlock) {
        return pBlock.is(Blocks.COBWEB);
    }

    public int getEnchantmentValue(ItemStack stack) {
        return 15;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return this.getEquipmentRarity(EquipmentUtils.getLevel(pStack));
    }
}
