package net.bewitchmentplus.common.entity.living;

import net.minecraft.entity.EntityGroup;

public class VengefulGhostEntity {

	public EntityGroup getGroup() {
		return EntityGroup.UNDEAD;
	}

	@Override
	protected boolean hasShiny() {
		return true;
	}
}
