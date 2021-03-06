
package net.slothcraft.item;

import net.slothcraft.procedures.PackedGlassReceptacleFoodFoodEatenProcedure;
import net.slothcraft.itemgroup.SlothCraftOtherCreativeTabItemGroup;
import net.slothcraft.SlothcraftModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

@SlothcraftModElements.ModElement.Tag
public class PackedGlassReceptacleFoodItem extends SlothcraftModElements.ModElement {
	@ObjectHolder("slothcraft:packed_glass_receptacle_food")
	public static final Item block = null;
	public PackedGlassReceptacleFoodItem(SlothcraftModElements instance) {
		super(instance, 183);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(SlothCraftOtherCreativeTabItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(2).saturation(210f).setAlwaysEdible().build()));
			setRegistryName("packed_glass_receptacle_food");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 20;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.DRINK;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemStack, world, entity);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				PackedGlassReceptacleFoodFoodEatenProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
