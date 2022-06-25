package fr.pederobien.vocal.commandline.client.impl;

import fr.pederobien.commandline.ICode;

public enum EVocalClientCode implements ICode {

	// Starting application ------------------------------------------------------
	VOCAL_CLIENT__STARTING,

	// Stopping application ------------------------------------------------------
	VOCAL_CLIENT__STOPPING,

	// Common codes --------------------------------------------------------------
	VOCAL_CLIENT__NAME__COMPLETION,

	// Code for the IP address completion
	VOCAL_CLIENT__ADDRESS_COMPLETION,

	// Code for the port number completion
	VOCAL_CLIENT__PORT_COMPLETION,

	// Code when the server returns a fail code
	VOCAL_CLIENT__REQUEST_FAILED,

	// Code for the "vocal" command ----------------------------------------------
	VOCAL_CLIENT__ROOT__EXPLANATION,

	// Code for the "vocal connect" command --------------------------------------
	VOCAL_CLIENT__CONNECT__EXPLANATION,

	// Code when the IP address is missing
	VOCAL_CLIENT__CONNECT__ADDRESS_IS_MISSING,

	// Code when no IP address for the host could be found
	VOCAL_CLIENT__CONNECT__ADDRESS_NOT_FOUND,

	// Code when the port number is missing
	VOCAL_CLIENT__CONNECT__PORT_NUMBER_IS_MISSING,

	// Code when the port number has an invalid format
	VOCAL_CLIENT__CONNECT__PORT_NUMBER_BAD_FORMAT,

	// Code when the port number has an invalid range
	VOCAL_CLIENT__CONNECT__PORT_NUMBER_BAD_RANGE,

	// Code when trying to connect to the remote
	VOCAL_CLIENT__CONNECT__ATTEMPTING_CONNECTION,

	// Code when the connection with the remote succeed
	VOCAL_CLIENT__CONNECT__CONNECTION_COMPLETE,

	// Code when the connection with the remote failed
	VOCAL_CLIENT__CONNECT__CONNECTION_ABORT,

	// Code for the "vocal join" command ----------------------------------------
	VOCAL_CLIENT__JOIN__EXPLANATION,

	// Code when the player name is missing
	VOCAL_CLIENT__JOIN__PLAYER_NAME_IS_MISSING,

	// Code when trying to join the remote
	VOCAL_CLIENT__JOIN__ATTEMPTING_TO_JOIN,

	// Code when the player has joined the server
	VOCAL_CLIENT__JOIN__REQUEST_SUCCEED,

	// Code when the request failed
	VOCAL_CLIENT__JOIN__REQUEST_ABORT,

	;

	@Override
	public String getCode() {
		return name();
	}
}
