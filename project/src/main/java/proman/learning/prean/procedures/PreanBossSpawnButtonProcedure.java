package proman.learning.prean.procedures;

public class PreanBossSpawnButtonProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				PreanMod.LOGGER.warn("Failed to load dependency x for procedure PreanBossSpawnButton!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				PreanMod.LOGGER.warn("Failed to load dependency y for procedure PreanBossSpawnButton!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				PreanMod.LOGGER.warn("Failed to load dependency z for procedure PreanBossSpawnButton!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				PreanMod.LOGGER.warn("Failed to load dependency world for procedure PreanBossSpawnButton!");
			return;
		}

		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");

		if (world instanceof ServerWorld) {
			Entity entityToSpawn = new PreanerBossEntity.CustomEntity(PreanerBossEntity.entity, (World) world);
			entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);

			if (entityToSpawn instanceof MobEntity)
				((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(entityToSpawn.getPosition()),
						SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);

			world.addEntity(entityToSpawn);
		}

	}

}
