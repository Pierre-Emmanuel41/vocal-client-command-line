package fr.pederobien.vocal.commandline.client.impl;

import java.util.function.Supplier;

import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class SetNode extends VocalClientNode {
	private SetNameNode nameNode;
	private SetMuteNode muteNode;
	private SetDeafenNode deafenNode;
	private SetVolumeNode volumeNode;

	/**
	 * Creates a node in order to modify the properties of the players.
	 * 
	 * @param server The server associated to this node.
	 */
	protected SetNode(Supplier<IVocalServer> server) {
		super(server, "set", EVocalClientCode.VOCAL_CLIENT__SET__EXPLANATION, s -> s != null && s.isJoined());

		add(nameNode = new SetNameNode(server));
		add(muteNode = new SetMuteNode(server));
		add(deafenNode = new SetDeafenNode(server));
		add(volumeNode = new SetVolumeNode(server));
	}

	/**
	 * @return The node that updates the name of the server main player.
	 */
	public SetNameNode getNameNode() {
		return nameNode;
	}

	/**
	 * @return The node that modifies the mute status of the main player and/or the mute status of players registered on a server.
	 */
	public SetMuteNode getMuteNode() {
		return muteNode;
	}

	/**
	 * @return The node that modifies the deafen status of the main player.
	 */
	public SetDeafenNode getDeafenNode() {
		return deafenNode;
	}

	/**
	 * @return Set the audio volume of a player.
	 */
	public SetVolumeNode getVolumeNode() {
		return volumeNode;
	}
}
