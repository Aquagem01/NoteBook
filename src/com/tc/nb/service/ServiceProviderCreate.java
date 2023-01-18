package com.tc.nb.service;

import com.tc.nb.service.impl.NotesCreateServiceImpl;


public class ServiceProviderCreate {
	private static final ServiceProviderCreate instance = new ServiceProviderCreate();

	private NotesCreateService createServ = new NotesCreateServiceImpl();

	public NotesCreateService getCreateServ() {
		return createServ;
	}

	public static ServiceProviderCreate getInstance() {
		return instance;
	}

	@Override
	public String toString() {
		return "ServiceProviderCreate [createServ=" + createServ + "]";
	}

}
