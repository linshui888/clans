package com.github.sanctum.clans.construct.bank;

import com.github.sanctum.clans.construct.api.BanksAPI;
import com.github.sanctum.clans.construct.api.Clan;
import com.github.sanctum.clans.event.bank.BankPreTransactionEvent;
import com.github.sanctum.clans.event.bank.BankSetBalanceEvent;
import com.github.sanctum.clans.event.bank.BankTransactionEvent;
import com.github.sanctum.clans.event.bank.messaging.Messages;
import com.github.sanctum.labyrinth.data.EconomyProvision;
import com.github.sanctum.labyrinth.event.LabyrinthVentCall;
import com.github.sanctum.labyrinth.interfacing.Nameable;
import com.github.sanctum.panther.event.Subscribe;
import com.github.sanctum.panther.event.Vent;
import java.math.BigDecimal;
import java.util.Optional;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BankListener {

	private final JavaPlugin p = JavaPlugin.getProvidingPlugin(BankImpl.class);

	@Subscribe(priority = Vent.Priority.HIGHEST)
	public void onPreTransactionMonitor(BankPreTransactionEvent event) {
		switch (BanksAPI.getInstance().logToConsole()) {
			case SILENT:
				return;
			case QUIET:
				if (event.isCancelled()) p.getLogger().info(event.toString());
				return;
			case VERBOSE:
				p.getLogger().info(event.toString() + " " +
						Messages.TRANSACTION_VERBOSE_CLAN_ID.toString()
								.replace("{0}", event.getClan().getId())
				);
		}
	}

	@Subscribe(priority = Vent.Priority.HIGHEST)
	public void onTransaction(BankTransactionEvent e) {
		if (e instanceof BankPreTransactionEvent) return;
		switch (BanksAPI.getInstance().logToConsole()) {
			case SILENT:
				break;
			case QUIET:
				p.getLogger().info(e.toString());
				break;
			case VERBOSE:
				p.getLogger().info(e.toString() + " " +
						Messages.TRANSACTION_VERBOSE_CLAN_ID.toString()
								.replace("{0}", e.getClan().getId())
				);
		}
		if (!(e.getBank() instanceof BankImpl)) return; // Only react on our implementation
		final BanksAPI instance = BanksAPI.getInstance();
		if (instance instanceof DefaultBanksAPIImpl) {
			final BankLog.Transaction transaction = BankLog.Transaction.from(e);
			transaction.apply(e.getBank());
			((DefaultBanksAPIImpl) instance).getBackend().addTransaction(e.getClan(), transaction).join();
		}
	}

	@Subscribe(priority = Vent.Priority.HIGHEST)
	public void onDeposit(BankPreTransactionEvent event) {
		if (event.getTransactionType() != BankTransactionEvent.Type.DEPOSIT) return;
		if (!(event.getBank() instanceof BankImpl)) return; // Only react on our implementation
		if (!event.isSuccess()) {
			event.setCancelled(true);
			return; // The player didn't have enough money or is not allowed, no transaction
		}
		final BankImpl bank = (BankImpl) event.getBank();
		final BigDecimal maxBalance = BanksAPI.getInstance().maxBalance();
		if (maxBalance != null) {
			if (bank.getBalance().add(event.getAmount()).compareTo(maxBalance) > 0) {
				event.setCancelled(true);
				return;
			}
		}
		final Nameable entity = event.getEntity();
		if (entity instanceof Clan.Associate && ((Clan.Associate) entity).isPlayer()) {
			final Player player = ((Clan.Associate) entity).getAsPlayer().getPlayer();
			final BigDecimal amount = event.getAmount();
			final boolean success;

			//noinspection DataFlowIssue
			Optional<Boolean> opt = EconomyProvision.getInstance().withdraw(amount, player, player.getWorld().getName());

			success = opt.orElse(false);

			if (success) bank.setBalance(bank.getBalance().add(amount));
			if (!success) event.setSuccess(false);
		}
		new LabyrinthVentCall<>(new BankTransactionEvent(event)).run();
	}

	@Subscribe(priority = Vent.Priority.HIGHEST)
	public void onWithdrawal(BankPreTransactionEvent event) {
		if (event.getTransactionType() != BankTransactionEvent.Type.WITHDRAWAL) return;
		if (!(event.getBank() instanceof BankImpl)) return; // Only react on our implementation
		if (!event.isSuccess()) {
			event.setCancelled(true);
			return; // The bank didn't have enough money or is not allowed, no transaction
		}
		final BankImpl bank = (BankImpl) event.getBank();
		final Nameable entity = event.getEntity();
		if (entity instanceof Clan.Associate && ((Clan.Associate) entity).isPlayer()) {
			final Player player = ((Clan.Associate) entity).getAsPlayer().getPlayer();
			final BigDecimal amount = event.getAmount();
			final boolean success;
			//noinspection DataFlowIssue
			Optional<Boolean> opt = EconomyProvision.getInstance().deposit(amount, player, player.getWorld().getName());

			success = opt.orElse(false);
			if (success) bank.setBalance(bank.getBalance().subtract(amount));
			if (!success) event.setSuccess(false);
		}
		new LabyrinthVentCall<>(new BankTransactionEvent(event)).run();
	}

	@Subscribe(priority = Vent.Priority.MEDIUM)
	public void onSetBalance(BankSetBalanceEvent event) {
		if (!(event.getBank() instanceof BankImpl)) return; // Only react on our implementation
		final BigDecimal maxBalance = BanksAPI.getInstance().maxBalance();
		if (maxBalance != null && event.getNewBalance().compareTo(maxBalance) > 0) {
			event.setCancelled(true);
		}
	}

	@Subscribe(priority = Vent.Priority.READ_ONLY)
	public void onSetBalanceMonitor(BankSetBalanceEvent event) {
		if (!(event.getBank() instanceof BankImpl)) return; // Only react on our implementation
		final BankImpl bank = (BankImpl) event.getBank();
		bank.setBalance(event.getNewBalance());
	}

}