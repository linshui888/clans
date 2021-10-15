package com.github.sanctum.clans.construct.extra;

import com.github.sanctum.clans.construct.api.Clan;
import com.github.sanctum.clans.construct.api.ClansAPI;
import com.github.sanctum.labyrinth.data.FileManager;
import com.github.sanctum.labyrinth.library.StringUtils;
import com.github.sanctum.labyrinth.library.TextLib;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StringLibrary {

	public void sendMessage(Player p, String message) {
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			message = PlaceholderAPI.setPlaceholders(p, message);
		}
		p.sendMessage(color(getPrefix() + " " + message));
	}

	protected String[] color(String... text) {
		List<String> convert = new ArrayList<>();
		for (String t : text) {
			convert.add(StringUtils.use(t).translate());
		}
		return convert.toArray(new String[0]);
	}

	public String color(String text) {
		return StringUtils.use(text).translate();
	}

	public void sendComponent(CommandSender s, TextComponent text) {
		if (s instanceof Player) {
			((Player) s).spigot().sendMessage(text);
		}
	}

	public String getPrefix() {
		ClansAPI API = ClansAPI.getInstance();
		MessagePrefix prefix = API.getPrefix();
		return prefix.joined();
	}

	public String alreadyInClan() {
		return ClansAPI.getData().getMessageResponse("already-occupied");
	}

	public String notInClan() {
		return ClansAPI.getData().getMessageResponse("no-clan");
	}

	public String noClearance() {
		return ClansAPI.getData().getMessageResponse("no-clearance");
	}

	public String clanUnknown(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("clan-unknown"), name);
	}

	public String passwordInvalid() {
		return ClansAPI.getData().getMessageResponse("password-invalid");
	}

	public String playerUnknown(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("player-unknown"), name);
	}

	public String commandCreate() {
		return ClansAPI.getData().getMessageResponse("create");
	}

	public String commandPassword() {
		return ClansAPI.getData().getMessageResponse("password");
	}

	public String commandJoin() {
		return ClansAPI.getData().getMessageResponse("join");
	}

	public String commandPermit() {
		return ClansAPI.getData().getMessageResponse("permit");
	}

	public String commandKick() {
		return ClansAPI.getData().getMessageResponse("kick");
	}

	public String commandTag() {
		return ClansAPI.getData().getMessageResponse("tag");
	}

	public String commandPassowner() {
		return ClansAPI.getData().getMessageResponse("passowner");
	}

	public String commandNick() {
		return ClansAPI.getData().getMessageResponse("nick");
	}

	public String commandChat(String channel) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("chat"), channel);
	}

	public String commandPromote() {
		return ClansAPI.getData().getMessageResponse("promote");
	}

	public String commandDemote() {
		return ClansAPI.getData().getMessageResponse("demote");
	}

	public String commandAlly() {
		return ClansAPI.getData().getMessageResponse("ally");
	}

	public String commandEnemy() {
		return ClansAPI.getData().getMessageResponse("enemy");
	}

	public String commandColor() {
		return ClansAPI.getData().getMessageResponse("color");
	}

	public String commandMessage() {
		return ClansAPI.getData().getMessageResponse("message");
	}

	public String commandBase() {
		return ClansAPI.getData().getMessageResponse("base");
	}

	public String commandMode() {
		return ClansAPI.getData().getMessageResponse("mode");
	}

	public String commandSetbase() {
		return ClansAPI.getData().getMessageResponse("base-changed");
	}

	public String commandUnknown(String label) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("command-unknown"), label);
	}

	public String nameInvalid(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("name-invalid"), name);
	}

	public String nameTooLong(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("too-long"), name);
	}

	public String alreadyMade(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("already-made"), name);
	}

	public String alreadyEnemies(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("already-enemies"), name);
	}

	public String alreadyAllies(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("already-allies"), name);
	}

	public String alreadyNeutral(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("already-neutral"), name);
	}

	public String neutral(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("neutral"), name);
	}

	public String ally(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("allies"), name);
	}

	public String waiting(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("already-requested"), name);
	}

	public String enemy(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("enemies"), name);
	}

	public String noRemoval(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("no-removal"), name);
	}

	public String breach(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("claim-breach"), name);
	}

	public String higherpower(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("claim-higherpower"), name);
	}

	public String alreadyMax(String instert1, String insert2) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("already-max"), instert1, insert2);
	}

	public String claimed(int x, int z, String world) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("claim"), x, z, world);
	}

	public String unclaimed(int x, int z, String world) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("un-claim"), x, z, world);
	}

	public String overpowered(int x, int z, String world) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("claim-overpowered"), x, z, world);
	}

	public String alreadyWild() {
		return ClansAPI.getData().getMessageResponse("already-wild");
	}

	public String noClaims() {
		return ClansAPI.getData().getMessageResponse("no-claims");
	}

	public String unclaimedAll(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("un-claim-all"), name);
	}

	public String alreadyMaxClaims() {
		return ClansAPI.getData().getMessageResponse("already-max-claims");
	}

	public String shieldDeny() {
		return ClansAPI.getData().getMessageResponse("shield-deny");
	}

	public String tooWeak() {
		return ClansAPI.getData().getMessageResponse("too-weak");
	}

	public String selfDenial() {
		return ClansAPI.getData().getMessageResponse("self-denial");
	}

	public String peacefulDeny() {
		return ClansAPI.getData().getMessageResponse("peaceful-deny");
	}

	public String defaultMode() {
		return ClansAPI.getData().getMain().getConfig().getString("Clans.mode-change.default");
	}

	public String peacefulDenyOther(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("peaceful-deny-other"), name);
	}

	public String friendlyFire() {
		return ClansAPI.getData().getMessageResponse("friendly-fire");
	}

	public String friendlyFireOn(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("friendly-fire-on"), name);
	}

	public String friendlyFireOff(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("friendly-fire-off"), name);
	}

	public String peaceful() {
		return ClansAPI.getData().getMessageResponse("peace-mode");
	}

	public String modeAnnounce(String mode, String clanName) {
		String result = null;
		switch (mode.toLowerCase()) {
			case "war":
				result = MessageFormat.format(ClansAPI.getData().getMessageResponse("war"), clanName);
				break;

			case "peace":
				result = MessageFormat.format(ClansAPI.getData().getMessageResponse("peace"), clanName);
				break;
		}
		return result;
	}

	public String war() {
		return ClansAPI.getData().getMessageResponse("war-mode");
	}

	public String claimHint() {
		return ClansAPI.getData().getMessageResponse("claim-hint");
	}

	public String alreadyPeaceful() {
		return ClansAPI.getData().getMessageResponse("already-peace");
	}

	public String alreadyWar() {
		return ClansAPI.getData().getMessageResponse("already-war");
	}

	public String alreadyLastPage() {
		return ClansAPI.getData().getMessageResponse("already-last-page");
	}

	public String alreadyFirstPage() {
		return ClansAPI.getData().getMessageResponse("already-first-page");
	}

	public String allianceRequested() {
		return ClansAPI.getData().getMessageResponse("alliance-requested-in");
	}

	public String allianceRequestedOut(String insert1, String insert2) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("alliance-requested-out"), insert1, insert2);
	}

	public String allianceDenial() {
		return ClansAPI.getData().getMessageResponse("alliance-denial");
	}

	public String menuBorder() {
		return ClansAPI.getData().getPath("Border");
	}

	public int menuSize() {
		return Integer.parseInt(ClansAPI.getData().getPath("Lines"));
	}

	public String menuTitle() {
		return ClansAPI.getData().getPath("Title");
	}

	public String pageUnknown() {
		return ClansAPI.getData().getMessageResponse("page-unknown");
	}

	public String noPermission(String permission) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("no-permission"), permission);
	}

	public String alreadyOwnClaim() {
		return ClansAPI.getData().getMessageResponse("already-owned");
	}

	public String notEnemies(String name) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("not-enemies"), name);
	}

	public String notEnough(double needed) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("not-enough"), needed);
	}

	public String notClaimOwner(String actualOwner) {
		return MessageFormat.format(ClansAPI.getData().getMessageResponse("not-owner"), actualOwner);
	}

	public String wrongPassword() {
		return ClansAPI.getData().getMessageResponse("password-wrong");
	}

	public String getRankStyle() {
		FileManager main = ClansAPI.getData().getMain();
		String type = main.getRoot().getString("Formatting.Chat.rank-style");
		String result;
		if (type.equalsIgnoreCase("WORDLESS")) {
			result = "WORDLESS";
		} else {
			result = "FULL";
		}
		return result;
	}

	public String getWordlessStyle(String rank) {
		FileManager main = ClansAPI.getData().getMain();
		return main.getRoot().getString("Formatting.Chat.Styles.Wordless." + rank);
	}

	public String getFullStyle(String rank) {
		FileManager main = ClansAPI.getData().getMain();
		return main.getRoot().getString("Formatting.Chat.Styles.Full." + rank);
	}

	public String getChatFormat() {
		FileManager main = ClansAPI.getData().getMain();
		return main.getRoot().getString("Formatting.Chat.Channel.global");
	}

	public void getMemberboard(Player p, List<String> memberids, int page) {
		int totalPageCount = 1;
		if ((memberids.size() % 2) == 0) {
			if (memberids.size() > 0) {
				totalPageCount = memberids.size() / 2;
			}
		} else {
			totalPageCount = (memberids.size() / 2) + 1;
		}

		if (page <= totalPageCount) {

			if (memberids.isEmpty()) {
				p.sendMessage(color("&f- Just you."));
			} else {
				int i = 0, k = 0;
				page--;
				for (String entry : memberids) {
					k++;
					if ((((page * 2) + i + 1) == k) && (k != ((page * 2) + 2 + 1))) {
						i++;
						UUID id = UUID.fromString(entry);
						Clan.Associate associate = ClansAPI.getInstance().getAssociate(id).orElse(null);
						if (associate != null) {
							sendComponent(p, TextLib.getInstance().textRunnable("&f- ", "&b&l" + Bukkit.getOfflinePlayer(UUID.fromString(entry)).getName(), "", "&rRank: " + '"' + "&b" + associate.getRankTag() + "&r" + '"' + "\nK/D: &b&o" + associate.getKD(), "c i " + associate.getUser().getName()));
						}
					}
				}
				int point;
				point = page + 1;
				if (page >= 1 && page <= totalPageCount - 1) {
					int last;
					last = point - 1;
					point = point + 1;
					p.sendMessage(color("&f&m---------------------------"));
					sendComponent(p, TextLib.getInstance().textRunnable("", "&a&o«", " &7<&fNav&7> ", "&a&o»", "&b&oClick to go &d&oback a page", "&b&oClick to goto the &5&onext page", "c members" + " " + last, "c members" + " " + point));
				}
				if (page == totalPageCount) {
					int last;
					last = point - 1;
					p.sendMessage(color("&f&m---------------------------"));
					sendComponent(p, TextLib.getInstance().textRunnable("", "&a&o«", " &7<&fNav&7> ", "&c&o»", "&b&oClick to go &d&oback a page", "&c&oYou are already on the last page", "c members" + " " + last, "c i"));
				}
				if (memberids.size() > 2 && page == 0) {
					point = page + 1 + 1;
					p.sendMessage(color("&f&m---------------------------"));
					sendComponent(p, TextLib.getInstance().textRunnable("", "&c&o«", " &7<&fNav&7> ", "&a&o»", "&c&oYou are already on the first page", "&b&oClick to goto the &5&onext page", "c i", "c members" + " " + point));
				}
			}
		} else {
			p.sendMessage(color("&f&m---------------------------"));
			sendMessage(p, color("&eThere are only &f" + totalPageCount + " &epages of members."));
			sendComponent(p, TextLib.getInstance().textRunnable("", "&a&o«", " &7<&fNav&7> ", "&c&o»", "&b&oClick to go &d&oback a page", "&c&oYou are already on the last page", "c members" + " " + totalPageCount, "c i"));

		}
	}

	public String format(String string, String target, String replacement) {
		int targetLength = target.length();
		if (targetLength == 0) {
			return string;
		}
		int idx2 = string.indexOf(target);
		if (idx2 < 0) {
			return string;
		}
		StringBuilder buffer = new StringBuilder(targetLength > replacement.length() ? string.length() : string.length() * 2);
		int idx1 = 0;
		do {
			buffer.append(string, idx1, idx2);
			buffer.append(replacement);
			idx1 = idx2 + targetLength;
			idx2 = string.indexOf(target, idx1);
		} while (idx2 > 0);
		buffer.append(string, idx1, string.length());
		return buffer.toString();
	}

	public String format(String string, String target1, String replacement1, String target2, String replacement2) {
		int targetLength = target1.length();
		if (targetLength == 0) {
			return string;
		}
		int idx2 = string.indexOf(target1);
		if (idx2 < 0) {
			return string;
		}
		StringBuilder buffer = new StringBuilder(targetLength > replacement1.length() ? string.length() : string.length() * 2);
		int idx1 = 0;
		do {
			buffer.append(string, idx1, idx2);
			buffer.append(replacement1);
			idx1 = idx2 + targetLength;
			idx2 = string.indexOf(target1, idx1);
		} while (idx2 > 0);
		buffer.append(string, idx1, string.length());

		int targetLength2 = target2.length();
		if (targetLength2 == 0) {
			return buffer.toString();
		}
		int idx22 = buffer.toString().indexOf(target2);
		if (idx22 < 0) {
			return buffer.toString();
		}
		StringBuilder buffer2 = new StringBuilder(targetLength2 > replacement2.length() ? buffer.toString().length() : buffer.toString().length() * 2);
		int idx12 = 0;
		do {
			buffer2.append(buffer.toString(), idx12, idx22);
			buffer2.append(replacement2);
			idx12 = idx22 + targetLength2;
			idx22 = buffer.toString().indexOf(target2, idx12);
		} while (idx22 > 0);
		buffer2.append(buffer.toString(), idx12, buffer.toString().length());

		return buffer2.toString();
	}

	public void chunkBorderHint(Player p) {
		Random r = new Random();
		int send = r.nextInt(3);
		if (send == 2) {
			sendMessage(p, claimHint());
		}
	}


}