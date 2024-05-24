package fr.firstmegagame4.spitsplat.mixin.client;

import fr.firstmegagame4.spitsplat.duck.BipedEntityModelDuck;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin<T extends LivingEntity> implements BipedEntityModelDuck {

	@Unique
	private boolean splatterActionLeft = false;

	@Unique
	private boolean splatterActionRight = false;

	@Shadow
	@Final
	public ModelPart leftArm;

	@Shadow
	@Final
	public ModelPart head;

	@Shadow
	@Final
	public ModelPart rightArm;

	@Inject(method = "positionLeftArm", at = @At("HEAD"), cancellable = true)
	private void applyLeftSplatterPosition(T entity, CallbackInfo ci) {
		if (this.splatterActionLeft) {
			this.leftArm.pitch = this.head.pitch - 1.5f;
			this.leftArm.yaw = this.head.yaw;
			ci.cancel();
		}
	}

	@Inject(method = "positionRightArm", at = @At("HEAD"), cancellable = true)
	private void applyRightSplatterPosition(T entity, CallbackInfo ci) {
		if (this.splatterActionRight) {
			this.rightArm.pitch = this.head.pitch - 1.5f;
			this.rightArm.yaw = this.head.yaw;
			ci.cancel();
		}
	}

	@Override
	public void spit_splat$splatterActionLeft(boolean state) {
		this.splatterActionLeft = state;
	}

	@Override
	public void spit_splat$splatterActionRight(boolean state) {
		this.splatterActionRight = state;
	}
}
