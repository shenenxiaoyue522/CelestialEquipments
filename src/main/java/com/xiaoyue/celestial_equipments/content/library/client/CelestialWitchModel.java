package com.xiaoyue.celestial_equipments.content.library.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class CelestialWitchModel extends HumanoidModel<LivingEntity> {

    private final boolean check;
    private final EquipmentSlot slot;
    private final ModelPart hat;
    private final ModelPart body;
    private final ModelPart bone;
    private final ModelPart out;
    private final ModelPart inner;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart dress;
    private final ModelPart outer;
    private final ModelPart bone95;
    private final ModelPart bone96;
    private final ModelPart bone97;
    private final ModelPart bone98;
    private final ModelPart bone99;
    private final ModelPart bone100;
    private final ModelPart bone101;
    private final ModelPart bone102;
    private final ModelPart bone103;
    private final ModelPart bone104;
    private final ModelPart bone105;
    private final ModelPart bone106;
    private final ModelPart bone107;
    private final ModelPart bone108;
    private final ModelPart bone109;
    private final ModelPart hidden;
    private final ModelPart bone83;
    private final ModelPart bone84;
    private final ModelPart bone85;
    private final ModelPart bone86;
    private final ModelPart bone87;
    private final ModelPart bone88;
    private final ModelPart bone89;
    private final ModelPart bone90;
    private final ModelPart bone91;
    private final ModelPart bone92;
    private final ModelPart bone93;
    private final ModelPart bone94;
    private final ModelPart bone110;
    private final ModelPart bone111;
    private final ModelPart bone112;
    private final ModelPart bone113;
    private final ModelPart bone114;
    private final ModelPart bone115;
    private final ModelPart bone116;
    private final ModelPart bone117;
    private final ModelPart bone118;
    private final ModelPart head;

    public CelestialWitchModel(boolean check, EquipmentSlot slot, ModelPart root) {
        super(root);
        this.check = check;
        this.slot = slot;
        this.hat = root.getChild("hat");
        this.body = root.getChild("body");
        this.bone = this.body.getChild("bone");
        this.out = this.bone.getChild("out");
        this.inner = this.bone.getChild("inner");
        this.right_arm = root.getChild("right_arm");
        this.left_arm = root.getChild("left_arm");
        this.right_leg = root.getChild("right_leg");
        this.left_leg = root.getChild("left_leg");
        this.dress = root.getChild("dress");
        this.outer = this.dress.getChild("outer");
        this.bone95 = this.outer.getChild("bone95");
        this.bone96 = this.bone95.getChild("bone96");
        this.bone97 = this.bone96.getChild("bone97");
        this.bone98 = this.outer.getChild("bone98");
        this.bone99 = this.bone98.getChild("bone99");
        this.bone100 = this.bone99.getChild("bone100");
        this.bone101 = this.outer.getChild("bone101");
        this.bone102 = this.bone101.getChild("bone102");
        this.bone103 = this.bone102.getChild("bone103");
        this.bone104 = this.outer.getChild("bone104");
        this.bone105 = this.bone104.getChild("bone105");
        this.bone106 = this.bone105.getChild("bone106");
        this.bone107 = this.outer.getChild("bone107");
        this.bone108 = this.bone107.getChild("bone108");
        this.bone109 = this.bone108.getChild("bone109");
        this.hidden = root.getChild("hidden");
        this.bone83 = this.hidden.getChild("bone83");
        this.bone84 = this.bone83.getChild("bone84");
        this.bone85 = this.bone84.getChild("bone85");
        this.bone86 = this.hidden.getChild("bone86");
        this.bone87 = this.bone86.getChild("bone87");
        this.bone88 = this.bone87.getChild("bone88");
        this.bone89 = this.hidden.getChild("bone89");
        this.bone90 = this.bone89.getChild("bone90");
        this.bone91 = this.bone90.getChild("bone91");
        this.bone92 = this.hidden.getChild("bone92");
        this.bone93 = this.bone92.getChild("bone93");
        this.bone94 = this.bone93.getChild("bone94");
        this.bone110 = this.hidden.getChild("bone110");
        this.bone111 = this.bone110.getChild("bone111");
        this.bone112 = this.bone111.getChild("bone112");
        this.bone113 = this.hidden.getChild("bone113");
        this.bone114 = this.bone113.getChild("bone114");
        this.bone115 = this.bone114.getChild("bone115");
        this.bone116 = this.hidden.getChild("bone116");
        this.bone117 = this.bone116.getChild("bone117");
        this.bone118 = this.bone117.getChild("bone118");
        this.head = root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(117, 68).addBox(0.5F, -15.5F, -9.5F, -1.0F, 6.0F, 4.0F, new CubeDeformation(0.5F))
                .texOffs(120, 24).addBox(-1.0F, -12.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition Hat3_r1 = hat.addOrReplaceChild("Hat3_r1", CubeListBuilder.create().texOffs(116, 61).addBox(0.0F, -3.0F, -6.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-1.0F, -18.5F, -1.0F, 1.0908F, 0.0F, 0.0F));

        PartDefinition Hat2_r1 = hat.addOrReplaceChild("Hat2_r1", CubeListBuilder.create().texOffs(112, 59).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -13.5F, 0.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition Hat1Outline_r1 = hat.addOrReplaceChild("Hat1Outline_r1", CubeListBuilder.create().texOffs(94, 31).addBox(-4.5F, 1.5F, -5.5F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.2F))
                .texOffs(96, 46).addBox(-4.0F, -0.5F, -5.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -11.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition HatBase2_r1 = hat.addOrReplaceChild("HatBase2_r1", CubeListBuilder.create().texOffs(57, 78).addBox(-2.0F, -2.0F, -6.0F, 8.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9368F, -6.0875F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition HatBase1_r1 = hat.addOrReplaceChild("HatBase1_r1", CubeListBuilder.create().texOffs(57, 78).addBox(-2.0F, -2.0F, -6.0F, 8.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9235F, -7.6182F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.36F))
                .texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.66F))
                .texOffs(0, 64).addBox(-4.0F, 8.0F, -3.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.33F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Body_r1 = body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -7.0F, -0.5F, 8.0F, 3.0F, 2.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(0.0F, 6.85F, 0.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(4.5252F, 10.8179F, 2.3104F, -0.0873F, 0.0F, 0.0F));

        PartDefinition out = bone.addOrReplaceChild("out", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition out_r1 = out.addOrReplaceChild("out_r1", CubeListBuilder.create().texOffs(24, 30).addBox(-4.0F, -5.5F, -2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(-4.5252F, 6.0114F, -0.0536F, 0.3927F, 0.0F, 0.0F));

        PartDefinition out_r2 = out.addOrReplaceChild("out_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, -5.5F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.36F)), PartPose.offsetAndRotation(2.2696F, 4.9045F, -0.5121F, 0.3712F, 0.1313F, -0.3244F));

        PartDefinition out_r3 = out.addOrReplaceChild("out_r3", CubeListBuilder.create().texOffs(44, 44).mirror().addBox(0.35F, 0.35F, -4.35F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.35F)).mirror(false), PartPose.offsetAndRotation(-9.0505F, 0.0F, 0.0F, -0.0651F, -0.1313F, 0.3244F));

        PartDefinition out_r4 = out.addOrReplaceChild("out_r4", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(1.0F, -5.5F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.36F)).mirror(false), PartPose.offsetAndRotation(-11.3201F, 4.9045F, -0.5121F, 0.3712F, -0.1313F, 0.3244F));

        PartDefinition out_r5 = out.addOrReplaceChild("out_r5", CubeListBuilder.create().texOffs(44, 44).addBox(-3.35F, 0.35F, -4.35F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0651F, 0.1313F, -0.3244F));

        PartDefinition inner = bone.addOrReplaceChild("inner", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition inner_r1 = inner.addOrReplaceChild("inner_r1", CubeListBuilder.create().texOffs(24, 16).addBox(-4.0F, -5.5F, -2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.32F)), PartPose.offsetAndRotation(-4.5252F, 6.0114F, -0.0536F, 0.3927F, 0.0F, 0.0F));

        PartDefinition inner_r2 = inner.addOrReplaceChild("inner_r2", CubeListBuilder.create().texOffs(16, 44).addBox(-4.0F, -5.5F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.33F)), PartPose.offsetAndRotation(2.2696F, 4.9045F, -0.5121F, 0.3712F, 0.1313F, -0.3244F));

        PartDefinition inner_r3 = inner.addOrReplaceChild("inner_r3", CubeListBuilder.create().texOffs(30, 44).mirror().addBox(0.35F, 0.35F, -4.35F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.32F)).mirror(false), PartPose.offsetAndRotation(-9.0505F, 0.0F, 0.0F, -0.0651F, -0.1313F, 0.3244F));

        PartDefinition inner_r4 = inner.addOrReplaceChild("inner_r4", CubeListBuilder.create().texOffs(16, 44).mirror().addBox(1.0F, -5.5F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.33F)).mirror(false), PartPose.offsetAndRotation(-11.3201F, 4.9045F, -0.5121F, 0.3712F, -0.1313F, 0.3244F));

        PartDefinition inner_r5 = inner.addOrReplaceChild("inner_r5", CubeListBuilder.create().texOffs(30, 44).addBox(-3.35F, 0.35F, -4.35F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.32F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0651F, 0.1313F, -0.3244F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F))
                .texOffs(48, 5).addBox(-3.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition armorRightArm_r1 = right_arm.addOrReplaceChild("armorRightArm_r1", CubeListBuilder.create().texOffs(48, 11).addBox(0.35F, 0.35F, 0.35F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.34F)), PartPose.offsetAndRotation(-3.35F, 1.35F, -2.35F, 0.0F, 0.0F, 0.2182F));

        PartDefinition armorRightArm_r2 = right_arm.addOrReplaceChild("armorRightArm_r2", CubeListBuilder.create().texOffs(48, 21).addBox(1.0F, 3.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.64F)), PartPose.offsetAndRotation(-5.0143F, 2.5604F, 0.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F)).mirror(false)
                .texOffs(48, 5).mirror().addBox(-1.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition armorLeftArm_r1 = left_arm.addOrReplaceChild("armorLeftArm_r1", CubeListBuilder.create().texOffs(48, 11).mirror().addBox(-1.35F, 0.35F, 0.35F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.34F)).mirror(false), PartPose.offsetAndRotation(3.35F, 1.35F, -2.35F, 0.0F, 0.0F, -0.2182F));

        PartDefinition armorLeftArm_r2 = left_arm.addOrReplaceChild("armorLeftArm_r2", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-2.0F, 3.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.64F)).mirror(false), PartPose.offsetAndRotation(5.0143F, 2.5604F, 0.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(66, 51).addBox(-2.45F, 10.25F, -2.75F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(65, 58).addBox(-2.45F, 7.25F, -2.75F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(66, 51).addBox(-2.25F, 10.25F, -2.75F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(65, 58).addBox(-2.25F, 7.25F, -2.75F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition dress = partdefinition.addOrReplaceChild("dress", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition outer = dress.addOrReplaceChild("outer", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition bone95 = outer.addOrReplaceChild("bone95", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone96 = bone95.addOrReplaceChild("bone96", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone97 = bone96.addOrReplaceChild("bone97", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone98 = outer.addOrReplaceChild("bone98", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, -2.618F, 0.0F));

        PartDefinition bone99 = bone98.addOrReplaceChild("bone99", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone100 = bone99.addOrReplaceChild("bone100", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone101 = outer.addOrReplaceChild("bone101", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, -3.1416F, 0.0F));

        PartDefinition bone102 = bone101.addOrReplaceChild("bone102", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone103 = bone102.addOrReplaceChild("bone103", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone104 = outer.addOrReplaceChild("bone104", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, -3.6652F, 0.0F));

        PartDefinition bone105 = bone104.addOrReplaceChild("bone105", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone106 = bone105.addOrReplaceChild("bone106", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone107 = outer.addOrReplaceChild("bone107", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, -4.1888F, 0.0F));

        PartDefinition bone108 = bone107.addOrReplaceChild("bone108", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone109 = bone108.addOrReplaceChild("bone109", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition hidden = partdefinition.addOrReplaceChild("hidden", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 0.0F));

        PartDefinition bone83 = hidden.addOrReplaceChild("bone83", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone84 = bone83.addOrReplaceChild("bone84", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone85 = bone84.addOrReplaceChild("bone85", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone86 = hidden.addOrReplaceChild("bone86", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone87 = bone86.addOrReplaceChild("bone87", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone88 = bone87.addOrReplaceChild("bone88", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone89 = hidden.addOrReplaceChild("bone89", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone90 = bone89.addOrReplaceChild("bone90", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone91 = bone90.addOrReplaceChild("bone91", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone92 = hidden.addOrReplaceChild("bone92", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone93 = bone92.addOrReplaceChild("bone93", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone94 = bone93.addOrReplaceChild("bone94", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone110 = hidden.addOrReplaceChild("bone110", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -4.7124F, 0.0F));

        PartDefinition bone111 = bone110.addOrReplaceChild("bone111", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone112 = bone111.addOrReplaceChild("bone112", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone113 = hidden.addOrReplaceChild("bone113", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -5.236F, 0.0F));

        PartDefinition bone114 = bone113.addOrReplaceChild("bone114", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone115 = bone114.addOrReplaceChild("bone115", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone116 = hidden.addOrReplaceChild("bone116", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -5.7596F, 0.0F));

        PartDefinition bone117 = bone116.addOrReplaceChild("bone117", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.2779F, 0.4014F, 0.0F, 0.0F));

        PartDefinition bone118 = bone117.addOrReplaceChild("bone118", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2195F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        if (!slot.equals(EquipmentSlot.FEET)) {
            left_leg.visible = false;
            right_leg.visible = false;
        }
        if (!slot.equals(EquipmentSlot.LEGS)) {
            dress.visible = false;
        }
        if (!slot.equals(EquipmentSlot.CHEST)) {
            body.visible = false;
        }
        if (slot.equals(EquipmentSlot.LEGS) && !check) {
            hidden.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        }
        dress.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        super.renderToBuffer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
