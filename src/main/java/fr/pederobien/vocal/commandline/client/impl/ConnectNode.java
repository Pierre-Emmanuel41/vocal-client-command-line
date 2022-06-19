package fr.pederobien.vocal.commandline.client.impl;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.function.Predicate;

import fr.pederobien.vocal.client.impl.VocalServer;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class ConnectNode extends VocalClientNode {
	private VocalClientCommandTree tree;

	/**
	 * Creates a node in order to creates a new vocal server associated to a specific ip address and port number.
	 * 
	 * @param tree The tree associated to this node.
	 */
	protected ConnectNode(VocalClientCommandTree tree) {
		super(() -> tree.getServer(), "connect", EVocalClientCode.VOCAL_CLIENT__CONNECT__EXPLANATION, server -> true);
		this.tree = tree;
	}

	@Override
	public List<String> onTabComplete(String[] args) {
		switch (args.length) {
		case 1:
			return asList(getMessage(EVocalClientCode.VOCAL_CLIENT__ADDRESS_COMPLETION));
		case 2:
			Predicate<String> addressValid = address -> {
				try {
					InetAddress.getByName(address);
				} catch (UnknownHostException e) {
					return false;
				}
				return true;
			};
			return check(args[0], addressValid, asList(getMessage(EVocalClientCode.VOCAL_CLIENT__PORT_COMPLETION)));
		default:
			return emptyList();
		}
	}

	@Override
	public boolean onCommand(String[] args) {
		InetAddress ipAddress;
		try {
			ipAddress = InetAddress.getByName(args[0]);
		} catch (IndexOutOfBoundsException e) {
			send(EVocalClientCode.VOCAL_CLIENT__CONNECT__ADDRESS_IS_MISSING);
			return false;
		} catch (UnknownHostException e) {
			send(EVocalClientCode.VOCAL_CLIENT__CONNECT__ADDRESS_NOT_FOUND, args[0]);
			return false;
		}

		int port;
		try {
			port = Integer.parseInt(args[1]);
			if (port < 0 || 65535 < port) {
				send(EVocalClientCode.VOCAL_CLIENT__CONNECT__PORT_NUMBER_BAD_RANGE, port);
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			send(EVocalClientCode.VOCAL_CLIENT__CONNECT__PORT_NUMBER_IS_MISSING);
			return false;
		} catch (NumberFormatException e) {
			send(EVocalClientCode.VOCAL_CLIENT__CONNECT__PORT_NUMBER_BAD_FORMAT, args[1]);
			return false;
		}

		try {
			IVocalServer server = new VocalServer(String.format("VocalServer_%s:%s", ipAddress.getHostAddress(), port), new InetSocketAddress(ipAddress, port));
			send(EVocalClientCode.VOCAL_CLIENT__CONNECT__ATTEMPTING_CONNECTION, ipAddress, port);
			server.open();
			send(EVocalClientCode.VOCAL_CLIENT__CONNECT__CONNECTION_COMPLETE, ipAddress, port);
			tree.setServer(server);
		} catch (IllegalStateException e) {
			send(EVocalClientCode.VOCAL_CLIENT__CONNECT__CONNECTION_ABORT, ipAddress, port);
			return false;
		}
		return true;
	}
}
