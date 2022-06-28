package fr.pederobien.vocal.commandline.client.impl;

import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.pederobien.vocal.client.interfaces.IResponse;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class ServerLeaveNode extends VocalClientNode {

	/**
	 * Creates a node in order to let a player leaving a mumble server.
	 * 
	 * @param server The server associated to this node.
	 */
	protected ServerLeaveNode(Supplier<IVocalServer> server) {
		super(server, "leave", EVocalClientCode.VOCAL_CLIENT__LEAVE__EXPLANATION, s -> s != null && s.isJoined());
	}

	@Override
	public boolean onCommand(String[] args) {
		Consumer<IResponse> update = response -> {
			if (response.hasFailed())
				send(EVocalClientCode.VOCAL_CLIENT__REQUEST_FAILED, response.getErrorCode().getMessage());
			else
				send(EVocalClientCode.VOCAL_CLIENT__LEAVE__REQUEST_SUCCEED, getServer().getName());
		};

		send(EVocalClientCode.VOCAL_CLIENT__LEAVE__ATTEMPTING_TO_LEAVE, getServer().getName());
		getServer().leave(update);
		return true;
	}
}
