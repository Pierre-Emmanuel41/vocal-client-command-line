package fr.pederobien.vocal.commandline.client.impl;

import java.util.StringJoiner;
import java.util.function.Supplier;

import fr.pederobien.vocal.client.interfaces.IVocalPlayer;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class DetailsNode extends VocalClientNode {

	/**
	 * Creates a node in order to display the characteristics of the given vocal server.
	 * 
	 * @param server The server associated to this node.
	 */
	protected DetailsNode(Supplier<IVocalServer> server) {
		super(server, "details", EVocalClientCode.VOCAL_CLIENT__DETAILS__EXPLANATION, s -> s != null);
	}

	@Override
	public boolean onCommand(String[] args) {
		String tabulations = "\t";
		StringJoiner serverJoiner = new StringJoiner("\n");

		// Server's name
		String serverName = getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__SERVER_NAME, getServer().getName());
		serverJoiner.add(serverName);

		// Server's IP address
		String ipAddress = getServer().getAddress().getAddress().getHostAddress();
		int port = getServer().getAddress().getPort();
		String serverAddress = getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__SERVER_IP_ADDRESS, ipAddress, port);
		serverJoiner.add(serverAddress);

		// Server's reachable status
		EVocalClientCode reachableCode;
		if (getServer().isReachable())
			reachableCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__SERVER_REACHABLE;
		else
			reachableCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__SERVER_NOT_REACHABLE;

		String serverReachable = getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__SERVER_REACHABLE_STATUS, getMessage(reachableCode));
		serverJoiner.add(serverReachable);

		if (getServer().isReachable()) {
			serverJoiner.add("");

			// Server's main player
			serverJoiner.add(getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__MAIN_PLAYER, getServer().getMainPlayer().getName()));

			// Player's mute status
			EVocalClientCode muteCode;
			if (getServer().getMainPlayer().isMute())
				muteCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_MUTE;
			else
				muteCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_NOT_MUTE;

			String playerMute = getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_MUTE_STATUS, getMessage(muteCode));
			serverJoiner.add(tabulations.concat(playerMute));

			// Player's deafen status
			EVocalClientCode deafenCode;
			if (getServer().getMainPlayer().isDeafen())
				deafenCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_DEAFEN;
			else
				deafenCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_NOT_DEAFEN;

			String playerDeafen = getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_DEAFEN_STATUS, getMessage(deafenCode));
			serverJoiner.add(tabulations.concat(playerDeafen));

			serverJoiner.add("");

			// Server's secondary players
			serverJoiner.add(getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__SECONDARY_PLAYERS));

			for (IVocalPlayer player : getServer().getPlayers()) {
				if (player.equals(getServer().getMainPlayer()))
					continue;

				// Player's name
				serverJoiner.add(getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__SECONDARY_PLAYER_NAME, player.getName()));

				// Player's mute status
				if (player.isMute())
					muteCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_MUTE;
				else
					muteCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_NOT_MUTE;

				playerMute = getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_MUTE_STATUS, getMessage(muteCode));
				serverJoiner.add(tabulations.concat(playerMute));

				// Player's deafen status
				if (player.isDeafen())
					deafenCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_DEAFEN;
				else
					deafenCode = EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_NOT_DEAFEN;

				playerDeafen = getMessage(EVocalClientCode.VOCAL_CLIENT__DETAILS__PLAYER_DEAFEN_STATUS, getMessage(deafenCode));
				serverJoiner.add(tabulations.concat(playerDeafen));
			}
		}

		send(EVocalClientCode.VOCAL_CLIENT__DETAILS__SERVER, serverJoiner);
		return true;
	}
}
