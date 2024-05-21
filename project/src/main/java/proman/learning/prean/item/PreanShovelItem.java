
package proman.learning.prean.item;

@PreanModElements.ModElement.Tag
public class PreanShovelItem extends PreanModElements.ModElement {

	@ObjectHolder("prean:prean_shovel")
	public static final Item block = null;

	public PreanShovelItem(PreanModElements instance) {
		super(instance, 19);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ShovelItem(new IItemTier() {
			public int getMaxUses() {
				return 0;
			}

			public float getEfficiency() {
				return 10000f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 0;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(PreanItem.block));
			}
		}, 1, -3f, new Item.Properties().group(ItemGroup.TOOLS)) {

		}.setRegistryName("prean_shovel"));
	}

}
