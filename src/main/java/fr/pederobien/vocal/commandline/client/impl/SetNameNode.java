package fr.pederobien.vocal.commandline.client.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.pederobien.messenger.interfaces.IResponse;
import fr.pederobien.vocal.client.interfaces.IVocalPlayer;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class SetNameNode extends VocalClientNode {

	/**
	 * Creates a node in order to update the name of the server main player.
	 * 
	 * @param server The server associated to this node.
	 */
	protected SetNameNode(Supplier<IVocalServer> server) {
		super(server, "name", EVocalClientCode.VOCAL_CLIENT__SET__NAME__EXPLANATION, s -> s != null && s.isJoined());
	}

	@Override
	public List<String> onTabComplete(String[] args) {
		switch (args.length) {
		case 1:
			return asList(getMessage(EVocalClientCode.VOCAL_CLIENT__NAME__COMPLETION));
		default:
			return emptyList();
		}
	}

	@Override
	public boolean onCommand(String[] args) {
		String name;
		try {
			name = args[0];
		} catch (IndexOutOfBoundsException e) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__NAME__PLAYER_NAME_IS_MISSING);
			return false;
		}

		Optional<IVocalPlayer> optPlayer = getServer().getPlayers().get(name);
		if (optPlayer.isPresent()) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__NAME__PLAYER_ALREADY_EXISTS, getServer().getMainPlayer().getName(), name);
			return false;
		}

		String oldName = getServer().getMainPlayer().getName();
		Consumer<IResponse> update = response -> {
			if (response.hasFailed())
				send(EVocalClientCode.VOCAL_CLIENT__REQUEST_FAILED, response.getErrorCode().getMessage());
			else
				send(EVocalClientCode.VOCAL_CLIENT__SET__NAME__REQUEST_SUCCEED, oldName, name);
		};
		getServer().getMainPlayer().setName(name, update);
		return true;
	}
}
