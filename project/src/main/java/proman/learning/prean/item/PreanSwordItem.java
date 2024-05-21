
package proman.learning.prean.item;

@PreanModElements.ModElement.Tag
public class PreanSwordItem extends PreanModElements.ModElement {

	@ObjectHolder("prean:prean_sword")
	public static final Item block = null;

	public PreanSwordItem(PreanModElements instance) {
		super(instance, 7);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 0;
			}

			public float getEfficiency() {
				return 0f;
			}

			public float getAttackDamage() {
				return 9998f;
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
		}, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)) {

		}.setRegistryName("prean_sword"));
	}

}
