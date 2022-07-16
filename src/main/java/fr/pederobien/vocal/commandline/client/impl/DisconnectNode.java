package fr.pederobien.vocal.commandline.client.impl;

public class DisconnectNode extends VocalClientNode {
	private VocalClientCommandTree tree;

	/**
	 * Creates a node that abort the connection with the remote.
	 * 
	 * @param server The server associated to this node.
	 */
	protected DisconnectNode(VocalClientCommandTree tree) {
		super(() -> tree.getServer(), "disconnect", EVocalClientCode.VOCAL_CLIENT__DISCONNECT__EXPLANATION, s -> s != null);
		this.tree = tree;
	}

	@Override
	public boolean onCommand(String[] args) {
		send(EVocalClientCode.VOCAL_CLIENT__DISCONNECT__CONNECTION_ABORTED, getServer().getAddress().getAddress().getHostAddress(), getServer().getAddress().getPort());
		getServer().close();
		tree.setServer(null);
		return true;
	}
}
