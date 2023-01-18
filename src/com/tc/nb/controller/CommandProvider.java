package com.tc.nb.controller;

import java.util.HashMap;

import com.tc.nb.controller.command.Command;
import com.tc.nb.controller.command.CommandName;
import com.tc.nb.controller.command.impl.AddNote;
import com.tc.nb.controller.command.impl.FindContent;
import com.tc.nb.controller.command.impl.FindDate;

final class CommandProvider {

	private final HashMap<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.FIND_CONTENT, new FindContent());
		repository.put(CommandName.FIND_DATE, new FindDate());
		repository.put(CommandName.ADD_NOTE, new AddNote());
	}

	Command getCommand(String name) {
		name = name.toUpperCase();

		CommandName commandName;
		Command command;

		commandName = CommandName.valueOf(name);
		System.out.println(commandName);
		command = repository.get(commandName);
		return command;

	}
}
