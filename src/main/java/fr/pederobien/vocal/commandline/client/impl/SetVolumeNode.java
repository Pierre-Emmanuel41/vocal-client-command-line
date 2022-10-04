package fr.pederobien.vocal.commandline.client.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import fr.pederobien.vocal.client.interfaces.IVocalPlayer;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class SetVolumeNode extends VocalClientNode {

	/**
	 * Set the sound volume of a player.
	 * 
	 * @param server The server associated to this node.
	 */
	protected SetVolumeNode(Supplier<IVocalServer> server) {
		super(server, "volume", EVocalClientCode.VOCAL_CLIENT__SET__VOLUME__EXPLANATION, s -> s != null && s.isJoined());
	}

	@Override
	public List<String> onTabComplete(String[] args) {
		switch (args.length) {
		case 1:
			return filter(getServer().getPlayers().stream().map(player -> player.getName()), args);
		case 2:
			Predicate<String> isNameValid = name -> getServer().getPlayers().get(name).isPresent();
			return check(args[0], isNameValid, asList(getMessage(EVocalClientCode.VOCAL_CLIENT__VOLUME_COMPLETION)));
		default:
			return emptyList();
		}
	}

	@Override
	public boolean onCommand(String[] args) {
		IVocalPlayer player;
		try {
			Optional<IVocalPlayer> optPlayer = getServer().getPlayers().get(args[0]);
			if (!optPlayer.isPresent()) {
				send(EVocalClientCode.VOCAL_CLIENT__SET__VOLUME__PLAYER_NOT_FOUND, args[0]);
				return false;
			}

			player = optPlayer.get();
		} catch (IndexOutOfBoundsException e) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__VOLUME__NAME_IS_MISSING);
			return false;
		}

		float volume;
		try {
			volume = Float.parseFloat(args[1]);
			if (volume < 0 || 200 < volume) {
				send(EVocalClientCode.VOCAL_CLIENT__SET__VOLUME__VOLUME_OUT_OF_RANGE, player.getName());
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__VOLUME__VOLUME_IS_MISSING, player.getName());
			return false;
		} catch (NumberFormatException e) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__VOLUME__VOLUME_BAD_FORMAT, player.getName());
			return false;
		}

		player.setVolume((float) (volume / 100.0));
		send(EVocalClientCode.VOCAL_CLIENT__SET__VOLUME__VOLUME_UPDATED, player.getName(), volume);
		return true;
	}
}
