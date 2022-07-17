package fr.pederobien.vocal.commandline.client.impl;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.pederobien.commandtree.exceptions.BooleanParseException;
import fr.pederobien.messenger.interfaces.IResponse;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class SetDeafenNode extends VocalClientNode {

	/**
	 * Creates a node in order to modify the deafen status of the main player.
	 * 
	 * @param server The server associated to this node.
	 */
	protected SetDeafenNode(Supplier<IVocalServer> server) {
		super(server, "deafen", EVocalClientCode.VOCAL_CLIENT__SET__DEAFEN__EXPLANATION, s -> s != null && s.isJoined());
	}

	@Override
	public List<String> onTabComplete(String[] args) {
		switch (args.length) {
		case 1:
			return filter(asList("true", "false").stream(), args);
		default:
			return emptyList();
		}
	}

	@Override
	public boolean onCommand(String[] args) {
		boolean isDeafen;
		try {
			isDeafen = getBoolean(args[0]);
		} catch (IndexOutOfBoundsException e) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__DEAFEN__DEAFEN_STATUS_IS_MISSING, getServer().getMainPlayer().getName());
			return false;
		} catch (BooleanParseException e) {
			send(EVocalClientCode.VOCAL_CLIENT__SET__DEAFEN__DEAFEN_STATUS_BAD_FORMAT, getServer().getMainPlayer().getName());
			return false;
		}

		Consumer<IResponse> update = response -> {
			if (response.hasFailed())
				send(EVocalClientCode.VOCAL_CLIENT__REQUEST_FAILED, response.getErrorCode().getMessage());
			else if (isDeafen)
				send(EVocalClientCode.VOCAL_CLIENT__SET__DEAFEN__DEAFEN_REQUEST_SUCCEED, getServer().getMainPlayer().getName());
			else
				send(EVocalClientCode.VOCAL_CLIENT__SET__DEAFEN__UNDEAFEN_REQUEST_SUCCEED, getServer().getMainPlayer().getName());
		};
		getServer().getMainPlayer().setDeafen(isDeafen, update);
		return true;
	}
}
