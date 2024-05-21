// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class Modelcustom_model extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer tentacle1;
	private final ModelRenderer tentacle2;
	private final ModelRenderer tentacle3;
	private final ModelRenderer tentacle4;
	private final ModelRenderer tentacle5;
	private final ModelRenderer tentacle6;
	private final ModelRenderer tentacle7;
	private final ModelRenderer tentacle8;
	private final ModelRenderer tentacle9;

	public Modelcustom_model() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 4.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		tentacle1 = new ModelRenderer(this);
		tentacle1.setRotationPoint(3.7F, 11.0F, -5.0F);
		tentacle1.setTextureOffset(0, 0).addBox(-8.4F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		tentacle2 = new ModelRenderer(this);
		tentacle2.setRotationPoint(-1.3F, 11.0F, -5.0F);
		tentacle2.setTextureOffset(0, 0).addBox(6.6F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);

		tentacle3 = new ModelRenderer(this);
		tentacle3.setRotationPoint(-6.3F, 11.0F, -5.0F);
		tentacle3.setTextureOffset(0, 0).addBox(6.6F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F, 0.0F, false);

		tentacle4 = new ModelRenderer(this);
		tentacle4.setRotationPoint(6.3F, 11.0F, 0.0F);
		tentacle4.setTextureOffset(0, 0).addBox(-13.6F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

		tentacle5 = new ModelRenderer(this);
		tentacle5.setRotationPoint(1.3F, 11.0F, 0.0F);
		tentacle5.setTextureOffset(0, 0).addBox(-3.6F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

		tentacle6 = new ModelRenderer(this);
		tentacle6.setRotationPoint(-3.7F, 11.0F, 0.0F);
		tentacle6.setTextureOffset(0, 0).addBox(6.4F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		tentacle7 = new ModelRenderer(this);
		tentacle7.setRotationPoint(3.7F, 11.0F, 5.0F);
		tentacle7.setTextureOffset(0, 0).addBox(-8.4F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		tentacle8 = new ModelRenderer(this);
		tentacle8.setRotationPoint(-1.3F, 11.0F, 5.0F);
		tentacle8.setTextureOffset(0, 0).addBox(6.6F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		tentacle9 = new ModelRenderer(this);
		tentacle9.setRotationPoint(-6.3F, 11.0F, 5.0F);
		tentacle9.setTextureOffset(0, 0).addBox(6.6F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle1.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle2.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle3.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle4.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle5.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle6.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle7.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle8.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle9.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.tentacle1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.tentacle1.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tentacle8.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.tentacle8.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tentacle9.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.tentacle9.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tentacle6.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.tentacle6.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tentacle7.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.tentacle7.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tentacle4.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.tentacle4.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tentacle5.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.tentacle5.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tentacle2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.tentacle2.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tentacle3.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.tentacle3.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}