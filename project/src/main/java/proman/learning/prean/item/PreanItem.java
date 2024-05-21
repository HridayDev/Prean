
package proman.learning.prean.item;

@PreanModElements.ModElement.Tag
public class PreanItem extends PreanModElements.ModElement {

	@ObjectHolder("prean:prean")
	public static final Item block = null;

	public PreanItem(PreanModElements instance) {
		super(instance, 1);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MATERIALS).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("prean");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

	}

}
