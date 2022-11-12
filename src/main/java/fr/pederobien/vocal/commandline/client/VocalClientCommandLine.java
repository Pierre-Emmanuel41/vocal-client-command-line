package fr.pederobien.vocal.commandline.client;

import fr.pederobien.commandline.impl.CommandLine;
import fr.pederobien.commandline.impl.CommandLine.CommandLineBuilder;
import fr.pederobien.commandtree.events.NodeEvent;
import fr.pederobien.communication.event.ConnectionEvent;
import fr.pederobien.dictionary.event.DictionaryEvent;
import fr.pederobien.sound.event.ProjectSoundEvent;
import fr.pederobien.utils.event.EventLogger;
import fr.pederobien.vocal.client.event.VocalPlayerSpeakPostEvent;
import fr.pederobien.vocal.client.event.VocalPlayerSpeakPreEvent;
import fr.pederobien.vocal.commandline.client.impl.EVocalClientCode;
import fr.pederobien.vocal.commandline.client.impl.VocalClientCommandTree;

public class VocalClientCommandLine {
	private static final String DEV_DICTIONARY_FOLDER = "src/main/resources/dictionaries/";
	private static final String PROD_DICTIONARY_FOLDER = "resources/dictionaries/vocal/client/";

	private static CommandLine commandLine;
	private static VocalClientCommandTree tree;

	public static void main(String[] args) {
		tree = new VocalClientCommandTree();

		CommandLineBuilder builder = new CommandLineBuilder(root -> {
			EventLogger.instance().newLine(true).timeStamp(true).ignore(DictionaryEvent.class).ignore(ConnectionEvent.class);
			EventLogger.instance().ignore(VocalPlayerSpeakPreEvent.class).ignore(VocalPlayerSpeakPostEvent.class);
			EventLogger.instance().ignore(NodeEvent.class).ignore(ProjectSoundEvent.class).register();

			String dictionaryFolder = commandLine.getEnvironment() == CommandLine.DEVELOPMENT_ENVIRONMENT ? DEV_DICTIONARY_FOLDER : PROD_DICTIONARY_FOLDER;
			commandLine.registerDictionaries(dictionaryFolder, new String[] { "English.xml", "French.xml" });
			return true;
		});

		builder.onStart((root, arguments) -> {
			commandLine.send(EVocalClientCode.VOCAL_CLIENT__STARTING);
			if (arguments.length == 0)
				return true;

			if (arguments.length < 2) {
				commandLine.send(EVocalClientCode.VOCAL_CLIENT__STARTING__IGNORING_ARGUMENTS__NOT_ENOUGH_ARGUMENT);
				return true;
			}

			String[] commands = new String[arguments.length + 1];
			commands[0] = "connect";
			for (int i = 0; i < arguments.length; i++)
				commands[i + 1] = arguments[i];

			root.onCommand(commands);
			return true;
		});

		builder.onStop(root -> {
			if (tree.getServer() != null)
				tree.getServer().close();
			commandLine.send(EVocalClientCode.VOCAL_CLIENT__STOPPING);
		});

		commandLine = builder.build(tree.getRoot(), args);
		commandLine.start();
	}
}
