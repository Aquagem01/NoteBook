package com.tc.nb.service;

import com.tc.nb.service.impl.NotesFindServiceImpl;

public class ServiceProviderFind {

	private static final ServiceProviderFind instance = new ServiceProviderFind();

	private NotesFindService findServ = new NotesFindServiceImpl();

	public NotesFindService getFindServ() {
		return findServ;
	}

	public static ServiceProviderFind getInstance() {
		return instance;
	}

	@Override
	public String toString() {
		return "ServiceProvider [findServ=" + findServ + "]";
	}
}
