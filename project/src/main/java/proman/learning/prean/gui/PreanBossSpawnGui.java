
package proman.learning.prean.gui;

import proman.learning.prean.PreanMod;

@PreanModElements.ModElement.Tag
public class PreanBossSpawnGui extends PreanModElements.ModElement {

	public static HashMap guistate = new HashMap();

	private static ContainerType<GuiContainerMod> containerType = null;

	public PreanBossSpawnGui(PreanModElements instance) {
		super(instance, 35);

		elements.addNetworkMessage(ButtonPressedMessage.class, ButtonPressedMessage::buffer, ButtonPressedMessage::new,
				ButtonPressedMessage::handler);
		elements.addNetworkMessage(GUISlotChangedMessage.class, GUISlotChangedMessage::buffer, GUISlotChangedMessage::new,
				GUISlotChangedMessage::handler);

		containerType = new ContainerType<>(new GuiContainerModFactory());

		FMLJavaModLoadingContext.get().getModEventBus().register(new ContainerRegisterHandler());

	}

	private static class ContainerRegisterHandler {

		@SubscribeEvent
		public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
			event.getRegistry().register(containerType.setRegistryName("prean_boss_spawn"));
		}

	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, PreanBossSpawnGuiWindow::new));
	}

	public static class GuiContainerModFactory implements IContainerFactory {

		public GuiContainerMod create(int id, PlayerInventory inv, PacketBuffer extraData) {
			return new GuiContainerMod(id, inv, extraData);
		}

	}

	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {

		World world;
		PlayerEntity entity;
		int x, y, z;

		private IItemHandler internal;

		private Map<Integer, Slot> customSlots = new HashMap<>();

		private boolean bound = false;

		public GuiContainerMod(int id, PlayerInventory inv, PacketBuffer extraData) {
			super(containerType, id);

			this.entity = inv.player;
			this.world = inv.player.world;

			this.internal = new ItemStackHandler(0);

			BlockPos pos = null;
			if (extraData != null) {
				pos = extraData.readBlockPos();
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
			}

			if (pos != null) {
				if (extraData.readableBytes() == 1) { // bound to item
					byte hand = extraData.readByte();
					ItemStack itemstack;
					if (hand == 0)
						itemstack = this.entity.getHeldItemMainhand();
					else
						itemstack = this.entity.getHeldItemOffhand();
					itemstack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
				} else if (extraData.readableBytes() > 1) {
					extraData.readByte(); // drop padding
					Entity entity = world.getEntityByID(extraData.readVarInt());
					if (entity != null)
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							this.internal = capability;
							this.bound = true;
						});
				} else { // might be bound to block
					TileEntity ent = inv.player != null ? inv.player.world.getTileEntity(pos) : null;
					if (ent != null) {
						ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							this.internal = capability;
							this.bound = true;
						});
					}
				}
			}

			int si;
			int sj;

			for (si = 0; si < 3; ++si)
				for (sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 30 + 8 + sj * 18, 12 + 84 + si * 18));

			for (si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 30 + 8 + si * 18, 12 + 142));

		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(PlayerEntity player) {
			return true;
		}

		@Override
		public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = (Slot) this.inventorySlots.get(index);

			if (slot != null && slot.getHasStack()) {
				ItemStack itemstack1 = slot.getStack();
				itemstack = itemstack1.copy();

				if (index < 0) {
					if (!this.mergeItemStack(itemstack1, 0, this.inventorySlots.size(), true)) {
						return ItemStack.EMPTY;
					}
					slot.onSlotChange(itemstack1, itemstack);
				} else if (!this.mergeItemStack(itemstack1, 0, 0, false)) {
					if (index < 0 + 27) {
						if (!this.mergeItemStack(itemstack1, 0 + 27, this.inventorySlots.size(), true)) {
							return ItemStack.EMPTY;
						}
					} else {
						if (!this.mergeItemStack(itemstack1, 0, 0 + 27, false)) {
							return ItemStack.EMPTY;
						}
					}
					return ItemStack.EMPTY;
				}

				if (itemstack1.getCount() == 0) {
					slot.putStack(ItemStack.EMPTY);
				} else {
					slot.onSlotChanged();
				}

				if (itemstack1.getCount() == itemstack.getCount()) {
					return ItemStack.EMPTY;
				}

				slot.onTake(playerIn, itemstack1);
			}
			return itemstack;
		}

		@Override /* failed to load code for net.minecraft.inventory.container.Container */

		@Override
		public void onContainerClosed(PlayerEntity playerIn) {
			super.onContainerClosed(playerIn);

			if (!bound && (playerIn instanceof ServerPlayerEntity)) {
				if (!playerIn.isAlive() || playerIn instanceof ServerPlayerEntity && ((ServerPlayerEntity) playerIn).hasDisconnected()) {
					for (int j = 0; j < internal.getSlots(); ++j) {
						playerIn.dropItem(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
					}
				} else {
					for (int i = 0; i < internal.getSlots(); ++i) {
						playerIn.inventory.placeItemBackInInventory(playerIn.world,
								internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
					}
				}
			}
		}

		private void slotChanged(int slotid, int ctype, int meta) {
			if (this.world != null && this.world.isRemote()) {
				PreanMod.PACKET_HANDLER.sendToServer(new GUISlotChangedMessage(slotid, x, y, z, ctype, meta));
				handleSlotAction(entity, slotid, ctype, meta, x, y, z);
			}
		}

	}

	public static class ButtonPressedMessage {

		int buttonID, x, y, z;

		public ButtonPressedMessage(PacketBuffer buffer) {
			this.buttonID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
		}

		public ButtonPressedMessage(int buttonID, int x, int y, int z) {
			this.buttonID = buttonID;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public static void buffer(ButtonPressedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.buttonID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
		}

		public static void handler(ButtonPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;

				handleButtonAction(entity, buttonID, x, y, z);
			});
			context.setPacketHandled(true);
		}

	}

	public static class GUISlotChangedMessage {

		int slotID, x, y, z, changeType, meta;

		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		public GUISlotChangedMessage(PacketBuffer buffer) {
			this.slotID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.changeType = buffer.readInt();
			this.meta = buffer.readInt();
		}

		public static void buffer(GUISlotChangedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.slotID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			buffer.writeInt(message.changeType);
			buffer.writeInt(message.meta);
		}

		public static void handler(GUISlotChangedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;

				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			context.setPacketHandled(true);
		}

	}

	static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;

		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;

		if (buttonID == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();

				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);

				PreanBossSpawnButtonProcedure.executeProcedure($_dependencies);
			}
		}
	}

	private static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;

		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;

	}

}
