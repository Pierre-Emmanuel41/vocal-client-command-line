package fr.pederobien.vocal.commandline.client.impl;

import java.util.Locale;
import java.util.function.Consumer;

import fr.pederobien.commandline.CommandLineDictionaryContext;
import fr.pederobien.commandline.ICode;
import fr.pederobien.commandtree.impl.CommandRootNode;
import fr.pederobien.commandtree.interfaces.ICommandRootNode;
import fr.pederobien.commandtree.interfaces.INode;
import fr.pederobien.dictionary.impl.MessageEvent;
import fr.pederobien.utils.AsyncConsole;
import fr.pederobien.vocal.client.interfaces.IVocalServer;

public class VocalClientCommandTree {
	private IVocalServer server;
	private ICommandRootNode<ICode> root;

	public VocalClientCommandTree() {
		Consumer<INode<ICode>> displayer = node -> {
			String label = node.getLabel();
			String explanation = CommandLineDictionaryContext.instance().getMessage(new MessageEvent(Locale.getDefault(), node.getExplanation().toString()));
			AsyncConsole.println(String.format("%s - %s", label, explanation));
		};

		root = new CommandRootNode<ICode>("vocal", EVocalClientCode.VOCAL_CLIENT__ROOT__EXPLANATION, () -> true, displayer);
	}

	/**
	 * @return The underlying vocal server managed by this command tree.
	 */
	public IVocalServer getServer() {
		return server;
	}

	/**
	 * @return The root of this command tree.
	 */
	public ICommandRootNode<ICode> getRoot() {
		return root;
	}
}
