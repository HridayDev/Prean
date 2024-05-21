
package proman.learning.prean.item;

@PreanModElements.ModElement.Tag
public class PreanPickItem extends PreanModElements.ModElement {

	@ObjectHolder("prean:prean_pick")
	public static final Item block = null;

	public PreanPickItem(PreanModElements instance) {
		super(instance, 5);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 0;
			}

			public float getEfficiency() {
				return 10000f;
			}

			public float getAttackDamage() {
				return -2f;
			}

			public int getHarvestLevel() {
				return 5;
			}

			public int getEnchantability() {
				return 0;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(PreanItem.block));
			}
		}, 1, -4f, new Item.Properties().group(ItemGroup.TOOLS)) {

		}.setRegistryName("prean_pick"));
	}

}
