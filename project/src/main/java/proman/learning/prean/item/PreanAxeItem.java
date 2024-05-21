
package proman.learning.prean.item;

@PreanModElements.ModElement.Tag
public class PreanAxeItem extends PreanModElements.ModElement {

	@ObjectHolder("prean:prean_axe")
	public static final Item block = null;

	public PreanAxeItem(PreanModElements instance) {
		super(instance, 17);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 0;
			}

			public float getEfficiency() {
				return 10000f;
			}

			public float getAttackDamage() {
				return 998f;
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

		}.setRegistryName("prean_axe"));
	}

}
