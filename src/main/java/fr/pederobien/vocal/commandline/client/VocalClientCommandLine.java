package fr.pederobien.vocal.commandline.client;

import fr.pederobien.commandline.CommandLine;
import fr.pederobien.commandline.CommandLine.CommandLineBuilder;
import fr.pederobien.commandline.ICode;
import fr.pederobien.commandtree.events.NodeEvent;
import fr.pederobien.commandtree.impl.CommandRootNode;
import fr.pederobien.communication.event.ConnectionEvent;
import fr.pederobien.dictionary.event.DictionaryEvent;
import fr.pederobien.sound.event.SoundEvent;
import fr.pederobien.utils.event.EventLogger;

public class VocalClientCommandLine {
	private static final String DEV_DICTIONARY_FOLDER = "src/main/resources/dictionaries/";
	private static final String PROD_DICTIONARY_FOLDER = "resources/dictionaries/vocal/client/";
	private static CommandLine commandLine;

	public static void main(String[] args) {
		CommandLineBuilder builder = new CommandLineBuilder(root -> {
			EventLogger.instance().newLine(true).timeStamp(true).ignore(DictionaryEvent.class).ignore(ConnectionEvent.class);
			EventLogger.instance().ignore(NodeEvent.class).ignore(SoundEvent.class).register();

			String dictionaryFolder = commandLine.getEnvironment() == CommandLine.DEVELOPMENT_ENVIRONMENT ? DEV_DICTIONARY_FOLDER : PROD_DICTIONARY_FOLDER;
			commandLine.registerDictionaries(dictionaryFolder, new String[] { "English.xml", "French.xml" });
			return true;
		});

		builder.onStart(root -> {
			commandLine.send(EVocalClientCode.VOCAL_CLIENT__STARTING);
			return true;
		});

		builder.onStop(root -> commandLine.send(EVocalClientCode.VOCAL_CLIENT__STOPPING));

		commandLine = builder.build(new CommandRootNode<ICode>("root", EVocalClientCode.VOCAL_CLIENT__ROOT__EXPLANATION, () -> true));
		commandLine.start();
	}
}
