package fr.pederobien.vocal.commandline.client.impl;

import fr.pederobien.commandline.ICode;

public enum EVocalClientCode implements ICode {

	// Starting application -----------------------------------------------------------------------
	VOCAL_CLIENT__STARTING,

	// Code when the arguments are ignored
	VOCAL_CLIENT__STARTING__IGNORING_ARGUMENTS__NOT_ENOUGH_ARGUMENT,

	// Stopping application -----------------------------------------------------------------------
	VOCAL_CLIENT__STOPPING,

	// Common codes -------------------------------------------------------------------------------
	VOCAL_CLIENT__NAME__COMPLETION,

	// Code for the IP address completion
	VOCAL_CLIENT__ADDRESS_COMPLETION,

	// Code for the port number completion
	VOCAL_CLIENT__PORT_COMPLETION,

	// Code for the volume completion
	VOCAL_CLIENT__VOLUME_COMPLETION,

	// Code when the server returns a fail code
	VOCAL_CLIENT__REQUEST_FAILED,

	// Code for the "vocal" command ---------------------------------------------------------------
	VOCAL_CLIENT__ROOT__EXPLANATION,

	// Code for the "vocal connect" command -------------------------------------------------------
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

	// Code for the "vocal disconnect" command ----------------------------------------------------
	VOCAL_CLIENT__DISCONNECT__EXPLANATION,

	// Code when the connection is aborted
	VOCAL_CLIENT__DISCONNECT__CONNECTION_ABORTED,

	// Code for the "vocal join" command ----------------------------------------------------------
	VOCAL_CLIENT__JOIN__EXPLANATION,

	// Code when the player name is missing
	VOCAL_CLIENT__JOIN__PLAYER_NAME_IS_MISSING,

	// Code when trying to join the remote
	VOCAL_CLIENT__JOIN__ATTEMPTING_TO_JOIN,

	// Code when the player has joined the server
	VOCAL_CLIENT__JOIN__REQUEST_SUCCEED,

	// Code when the request failed
	VOCAL_CLIENT__JOIN__REQUEST_ABORT,

	// Code for the "vocal leave" command ---------------------------------------------------------
	VOCAL_CLIENT__LEAVE__EXPLANATION,

	// Code when trying to leave the remote
	VOCAL_CLIENT__LEAVE__ATTEMPTING_TO_LEAVE,

	// Code when the player has left the server
	VOCAL_CLIENT__LEAVE__REQUEST_SUCCEED,

	// Code for the "vocal set" command -----------------------------------------------------------
	VOCAL_CLIENT__SET__EXPLANATION,

	// Code for the "vocal set name" command ------------------------------------------------------
	VOCAL_CLIENT__SET__NAME__EXPLANATION,

	// Code when the new player name is missing
	VOCAL_CLIENT__SET__NAME__PLAYER_NAME_IS_MISSING,

	// Code when the new player name is already used
	VOCAL_CLIENT__SET__NAME__PLAYER_ALREADY_EXISTS,

	// Code when the player name has been updated on the server
	VOCAL_CLIENT__SET__NAME__REQUEST_SUCCEED,

	// Code for the "vocal set mute" command ------------------------------------------------------
	VOCAL_CLIENT__SET__MUTE__EXPLANATION,

	// Code when the player is not registered in a channel
	VOCAL_CLIENT__SET__MUTE__PLAYER_NOT_REGISTERED_IN_CHANNEL,

	// Code when the player name is missing
	VOCAL_CLIENT__SET__MUTE__PLAYER_NAME_OR_MUTE_STATUS_IS_MISSING,

	// Code when the player is mute
	VOCAL_CLIENT__SET__MUTE__MUTED_REQUEST_SUCCEED,

	// Code when the player is unmute
	VOCAL_CLIENT__SET__MUTE__UNMUTED_REQUEST_SUCCEED,

	// Code when the player does not exist
	VOCAL_CLIENT__SET__MUTE__PLAYER_NOT_FOUND,

	// Code when the mute status is missing
	VOCAL_CLIENT__SET__MUTE__MUTE_STATUS_IS_MISSING,

	// Code when the mute status has a bad format
	VOCAL_CLIENT__SET__MUTE__MUTE_STATUS_BAD_FORMAT,

	// Code when another player is mute on the server
	VOCAL_CLIENT__SET__MUTE__PLAYER_MUTED_REQUEST_SUCCEED,

	// Code when another player is unmute on the server
	VOCAL_CLIENT__SET__MUTE__PLAYER_UNMUTED_REQUEST_SUCCEED,

	// Code for the "vocal set deafen" command ----------------------------------------------------
	VOCAL_CLIENT__SET__DEAFEN__EXPLANATION,

	// Code when the deafen status is missing
	VOCAL_CLIENT__SET__DEAFEN__DEAFEN_STATUS_IS_MISSING,

	// Code when the deafen status has a bad format
	VOCAL_CLIENT__SET__DEAFEN__DEAFEN_STATUS_BAD_FORMAT,

	// Code when the player is deafen
	VOCAL_CLIENT__SET__DEAFEN__DEAFEN_REQUEST_SUCCEED,

	// Code when the player is undeafen
	VOCAL_CLIENT__SET__DEAFEN__UNDEAFEN_REQUEST_SUCCEED,

	// Code for the "vocal set volume" command ----------------------------------------------------
	VOCAL_CLIENT__SET__VOLUME__EXPLANATION,

	// Code when the player name is missing
	VOCAL_CLIENT__SET__VOLUME__NAME_IS_MISSING,

	// Code when the player does not exist
	VOCAL_CLIENT__SET__VOLUME__PLAYER_NOT_FOUND,

	// Code when the volume is missing
	VOCAL_CLIENT__SET__VOLUME__VOLUME_IS_MISSING,

	// Code when the volume has a bad format
	VOCAL_CLIENT__SET__VOLUME__VOLUME_BAD_FORMAT,

	// Code when the volume is out of range
	VOCAL_CLIENT__SET__VOLUME__VOLUME_OUT_OF_RANGE,

	// Code when the volume has been updated
	VOCAL_CLIENT__SET__VOLUME__VOLUME_UPDATED,

	// Code for the "vocal details" command -------------------------------------------------------
	VOCAL_CLIENT__DETAILS__EXPLANATION,

	// Code for the vocal server name
	VOCAL_CLIENT__DETAILS__SERVER_NAME,

	// Code for the vocal server IP address
	VOCAL_CLIENT__DETAILS__SERVER_IP_ADDRESS,

	// Code when the vocal server is reachable
	VOCAL_CLIENT__DETAILS__SERVER_REACHABLE,

	// Code when the vocal server is not reachable
	VOCAL_CLIENT__DETAILS__SERVER_NOT_REACHABLE,

	// Code for the vocal server reachable status
	VOCAL_CLIENT__DETAILS__SERVER_REACHABLE_STATUS,

	// Code for the vocal server main player
	VOCAL_CLIENT__DETAILS__MAIN_PLAYER,

	// Code for the vocal server main player name
	VOCAL_CLIENT__DETAILS__PLAYER_NAME,

	// Code when the main player is mute
	VOCAL_CLIENT__DETAILS__PLAYER_MUTE,

	// Code when the main player is not mute
	VOCAL_CLIENT__DETAILS__PLAYER_NOT_MUTE,

	// Code for the main player mute status
	VOCAL_CLIENT__DETAILS__PLAYER_MUTE_STATUS,

	// Code when the main player is deafen
	VOCAL_CLIENT__DETAILS__PLAYER_DEAFEN,

	// Code when the main player is not deafen
	VOCAL_CLIENT__DETAILS__PLAYER_NOT_DEAFEN,

	// Code for the main player deafen status
	VOCAL_CLIENT__DETAILS__PLAYER_DEAFEN_STATUS,

	// Code for the list of secondary players
	VOCAL_CLIENT__DETAILS__SECONDARY_PLAYERS,

	// Code for the name of a secondary player
	VOCAL_CLIENT__DETAILS__SECONDARY_PLAYER_NAME,

	// Code to display the characteristics of a vocal server
	VOCAL_CLIENT__DETAILS__SERVER,

	;

	@Override
	public String getCode() {
		return name();
	}
}
