package fr.pederobien.vocal.commandline.client.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import fr.pederobien.commandtree.exceptions.BooleanParseException;
import fr.pederobien.vocal.client.interfaces.IResponse;
import fr.pederobien.vocal.client.interfaces.IVocalPlayer;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class SetMuteNode extends VocalClientNode {

	/**
	 * Creates a node in order to modify the mute status of the main player and/or the mute status of players registered on a server.
	 * 
	 * @param server The server associated to this node.
	 */
	protected SetMuteNode(Supplier<IVocalServer> server) {
		super(server, "mute", EVocalClientCode.VOCAL_CLIENT__SET__MUTE__EXPLANATION, s -> s != null && s.isJoined());
	}

	@Override
	public List<String> onTabComplete(String[] args) {
		switch (args.length) {
		case 1:
			List<String> trueFalseList = asList("true", "false");
			trueFalseList.addAll(getServer().getPlayers().stream().map(player -> player.getName()).collect(Collectors.toList()));
			return filter(trueFalseList.stream(), args);
		case 2:
			if (args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("false"))
				return emptyList();

			Predicate<String> nameValid = name -> getServer().getPlayers().get(name).isPresent();
			return check(args[0], nameValid, asList("true", "false"));
		default:
			return emptyList();
		}
	}

	@Override
	public boolean onCommand(String[] args) {
		switch (args.length) {
		case 1:
			return updateMainPlayerMuteStatus(args);
		case 2:
			return updatePlayerMuteStatus(args);
		default:
			send(EVocalClientCode.VOCAL_CLIENT__SET__MUTE__PLAYER_NAME_OR_MUTE_STATUS_IS_MISSING);
		}
		return false;
	}

	private boolean updateMainPlayerMuteStatus(String[] args) {
		boolean isMute;
		try {
			isMute = getBoolean(args[0]);
			Consumer<IResponse> update = response -> {
				if (response.hasFailed())
					send(EVocalClientCode.VOCAL_CLIENT__REQUEST_FAILED, response.getErrorCode().getMessage());
				else if (isMute)
					send(EVocalClientCode.VOCAL_CLIENT__SET__MUTE__MUTED_REQUEST_SUCCEED, getServer().getMainPlayer().getName());
				else
					send(EVocalClientCode.VOCAL_CLIENT__SET__MUTE__UNMUTED_REQUEST_SUCCEED, getServer().getMainPlayer().getName());
			};
			getServer().getMainPlayer().setMute(isMute, update);
			return true;
		} catch (BooleanParseException e) {
			return false;
		}
	}

	private boolean updatePlayerMuteStatus(String[] args) {
		IVocalPlayer player;
		Optional<IVocalPlayer> optPlayer = getServer().getPlayers().get(args[0]);
		if (!optPlayer.isPresent()) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__MUTE__PLAYER_NOT_FOUND, args[0]);
			return false;
		}

		player = optPlayer.get();

		boolean isMute;
		try {
			isMute = getBoolean(args[1]);
			Consumer<IResponse> update = response -> {
				if (response.hasFailed())
					send(EVocalClientCode.VOCAL_CLIENT__REQUEST_FAILED, response.getErrorCode().getMessage());
				else if (isMute)
					send(EVocalClientCode.VOCAL_CLIENT__SET__MUTE__PLAYER_MUTED_REQUEST_SUCCEED, player.getName(), getServer().getMainPlayer().getName());
				else
					send(EVocalClientCode.VOCAL_CLIENT__SET__MUTE__PLAYER_UNMUTED_REQUEST_SUCCEED, player.getName(), getServer().getMainPlayer().getName());
			};
			player.setMute(isMute, update);
		} catch (IndexOutOfBoundsException e) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__MUTE__MUTE_STATUS_IS_MISSING, player.getName());
			return false;
		} catch (BooleanParseException e) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__MUTE__MUTE_STATUS_BAD_FORMAT, args[1]);
			return false;
		}

		return true;
	}
}
