package com.github.sanctum.clans.construct.impl;

import com.github.sanctum.clans.construct.api.Clan;
import com.github.sanctum.clans.construct.api.ClanCooldown;
import com.github.sanctum.clans.construct.api.ClansAPI;
import com.github.sanctum.labyrinth.data.FileManager;
import com.github.sanctum.labyrinth.data.FileType;
import java.util.UUID;

public class CooldownCreate extends ClanCooldown {

	private final UUID Id;

	public CooldownCreate(UUID Id) {
		this.Id = Id;
	}

	@Override
	public String getId() {
		return Id.toString();
	}

	@Override
	public String getAction() {
		return "Clans:create-limit";
	}

	@Override
	public void setCooldown() {
		FileManager config = ClansAPI.getInstance().getFileList().find("cooldowns", "Configuration", FileType.JSON);
		config.write(t -> t.set("Data." + getAction().replace("Clans:", "") + ".Time-allotted", System.currentTimeMillis() + (ClansAPI.getData().getInt("Clans.creation.cooldown.time") * 1000)));
	}

	@Override
	public long getCooldown() {
		FileManager config = ClansAPI.getInstance().getFileList().find("cooldowns", "Configuration", FileType.JSON);
		return config.getRoot().getLong("Data." + getAction().replace("Clans:", "") + ".Time-allotted");
	}

	@Override
	public String fullTimeLeft() {
		return Clan.ACTION.format(Clan.ACTION.format(Clan.ACTION.format(Clan.ACTION.format(ClansAPI.getData().getMessageResponse("cooldown-active"), "%d", String.valueOf(getDaysLeft())), "%h", String.valueOf(getHoursLeft())), "%m", String.valueOf(getMinutesLeft())), "%s", String.valueOf(getSecondsLeft()));
	}

	@Override
	public ClanCooldown getInstance() {
		return this;
	}
}