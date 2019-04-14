package cn.mcmod.sakura.item;

import cn.mcmod.sakura.block.BlockLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.EnumMap;

import cn.mcmod.sakura.CommonProxy;
import cn.mcmod.sakura.SakuraMain;
import cn.mcmod.sakura.util.JSON_Creator;

public class ItemLoader {
    public static Item MAPLE_SYRUP = new ItemSeasoning(SakuraMain.MODID + "." + "maple_syrup", 15);
    public static ItemFoodBasic FOODSET = new ItemFoodBasic("foodset", 64,
            new int[]{3},
            new float[]{0.22F},
            new String[]{SakuraMain.MODID+"."+"maple_cookie"
            });
    public static ItemDoor BAMBOO_DOOR = new ItemDoor(BlockLoader.BAMBOODOOR);

    public ItemLoader(FMLPreInitializationEvent event) {
        register(BAMBOO_DOOR.setUnlocalizedName(SakuraMain.MODID + "." + "bamboo_door"));
        register(MAPLE_SYRUP.setUnlocalizedName(SakuraMain.MODID + "." + "maple_syrup"));
        register(FOODSET);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerRender(BAMBOO_DOOR);
        registerRender(MAPLE_SYRUP);
        registerRender(FOODSET);
    }

    private static void register(Item item) {
        item.setCreativeTab(CommonProxy.tab);
        ForgeRegistries.ITEMS.register(item.setRegistryName(item.getUnlocalizedName().substring(5 + SakuraMain.MODID.length() + 1)));
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemBase item) {
        registerRender(item, false);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemFoodBasic item) {
        registerRender(item, false);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemBase item, boolean json_create) {
        for (int i = 0; i < item.getSubNames().length; i++) {
            String name = item.getSubNames()[i].substring(SakuraMain.MODID.length() + 1);
            if (json_create) JSON_Creator.genItem(name, name, "json_create");
            ModelResourceLocation model = new ModelResourceLocation(new ResourceLocation(SakuraMain.MODID, name), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, i, model);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(ItemFoodBasic item, boolean json_create) {

        for (int i = 0; i < item.getSubNames().length; i++) {
            String name = item.getSubNames()[i].substring(SakuraMain.MODID.length() + 1);
            if (json_create) JSON_Creator.genItem(name, name, "json_create");
            ModelResourceLocation model = new ModelResourceLocation(new ResourceLocation(SakuraMain.MODID, name), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, i, model);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, int meta, String name) {
        ModelResourceLocation model = new ModelResourceLocation(name, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, int meta, String name, String textureName) {
        JSON_Creator.genItem(name, textureName, "json_create");
        ModelResourceLocation model = new ModelResourceLocation(name, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, String textureName) {
        JSON_Creator.genItem(item.getRegistryName().toString().substring(SakuraMain.MODID.length() + 1), textureName, "json_create");
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item) {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }
}
