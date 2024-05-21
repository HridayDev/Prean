
package proman.learning.prean.item;

@PreanModElements.ModElement.Tag
public class PreanHoeItem extends PreanModElements.ModElement {

	@ObjectHolder("prean:prean_hoe")
	public static final Item block = null;

	public PreanHoeItem(PreanModElements instance) {
		super(instance, 21);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 0;
			}

			public float getEfficiency() {
				return 1000f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 0;
			}

			public int getEnchantability() {
				return 0;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(PreanItem.block));
			}
		}, 0, -3f, new Item.Properties().group(ItemGroup.TOOLS)) {

		}.setRegistryName("prean_hoe"));
	}

}
