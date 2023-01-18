package com.tc.nb.controller;

import com.tc.nb.controller.command.Command;

public class Controller {

	private final CommandProvider provider = new CommandProvider();
	private final char paramDelimeter = ' ';

	public String executeTask(String request) {
		String commandName;
		Command executionCommand;

		commandName = request.substring(0, request.indexOf(paramDelimeter));
		commandName = commandName.toUpperCase();

		executionCommand = provider.getCommand(commandName);
		String response;
		response = executionCommand.execute(request);

		return response;
	}
}
