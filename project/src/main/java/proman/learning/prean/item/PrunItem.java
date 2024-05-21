
package proman.learning.prean.item;

@PreanModElements.ModElement.Tag
public class PrunItem extends PreanModElements.ModElement {

	@ObjectHolder("prean:prun")
	public static final Item block = null;

	public PrunItem(PreanModElements instance) {
		super(instance, 30);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(100).saturation(100f).setAlwaysEdible()

							.build()));
			setRegistryName("prun");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 20;
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}

	}

}
