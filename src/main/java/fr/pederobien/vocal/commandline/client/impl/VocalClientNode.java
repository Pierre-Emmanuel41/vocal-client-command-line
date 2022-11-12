package fr.pederobien.vocal.commandline.client.impl;

import java.util.function.Function;
import java.util.function.Supplier;

import fr.pederobien.commandline.impl.CommandLineNode;
import fr.pederobien.dictionary.interfaces.ICode;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class VocalClientNode extends CommandLineNode {
	private Supplier<IVocalServer> server;

	/**
	 * Creates a node specified by the given parameters.
	 * 
	 * @param server      The server associated to this node.
	 * @param label       The primary node name.
	 * @param explanation The explanation associated to this node.
	 * @param isAvailable True if this node is available, false otherwise.
	 */
	protected VocalClientNode(Supplier<IVocalServer> server, String label, ICode explanation, Function<IVocalServer, Boolean> isAvailable) {
		super(label, explanation, () -> isAvailable.apply(server == null ? null : server.get()));
		this.server = server;
	}

	/**
	 * @return The server associated to this node.
	 */
	protected IVocalServer getServer() {
		return server.get() == null ? null : server.get();
	}
}
