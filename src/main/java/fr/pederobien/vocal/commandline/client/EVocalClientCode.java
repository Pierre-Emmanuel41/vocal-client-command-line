package fr.pederobien.vocal.commandline.client;

import fr.pederobien.commandline.ICode;

public enum EVocalClientCode implements ICode {

	// Starting application ------------------------------------------------------
	VOCAL_CLIENT__STARTING,

	// Stopping application ------------------------------------------------------
	VOCAL_CLIENT__STOPPING,

	// Code for the "vocal" command ---------------------------------------------
	VOCAL_CLIENT__ROOT__EXPLANATION;

	@Override
	public String getCode() {
		return name();
	}
}
