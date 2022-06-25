package fr.pederobien.vocal.commandline.client.impl;

import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.pederobien.vocal.client.interfaces.IResponse;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class ServerJoinNode extends VocalClientNode {

	/**
	 * Creates a node in order to let a player joining a mumble server.
	 * 
	 * @param server The server associated to this node.
	 */
	protected ServerJoinNode(Supplier<IVocalServer> server) {
		super(server, "join", EVocalClientCode.VOCAL_CLIENT__JOIN__EXPLANATION, s -> s != null && !s.isJoined());
	}

	@Override
	public boolean onCommand(String[] args) {
		String name;
		try {
			name = args[0];
		} catch (IndexOutOfBoundsException e) {
			send(EVocalClientCode.VOCAL_CLIENT__JOIN__PLAYER_NAME_IS_MISSING);
			return false;
		}

		Consumer<IResponse> update = response -> {
			if (response.hasFailed())
				send(EVocalClientCode.VOCAL_CLIENT__REQUEST_FAILED, response.getErrorCode().getMessage());
			else
				send(EVocalClientCode.VOCAL_CLIENT__JOIN__REQUEST_SUCCEED, getServer().getName());
		};

		try {
			send(EVocalClientCode.VOCAL_CLIENT__JOIN__ATTEMPTING_TO_JOIN, getServer().getName());
			getServer().join(name, update);
		} catch (IllegalStateException e) {
			send(EVocalClientCode.VOCAL_CLIENT__JOIN__REQUEST_ABORT, getServer().getName());
			return false;
		}
		return true;
	}
}
