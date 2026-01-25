package com.inventory.rootPackage.temporal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventory.rootPackage.rag.DatabaseSyncService;

@Component
public class SyncActivitiesImpl implements SyncActivities{
	
	@Autowired
	private DatabaseSyncService data;

	@Override
	public void log(String msg) {
		System.out.println(msg);
		
	}

	@Override
	public void fetchItems() {
		data.syncItems();
		data.syncWholesalers();
	}

	@Override
	public void generateEmbeddings() {
		data.syncShoperPaid();
		
	}

	@Override
	public void uploadToPinecone() {
		data.syncPayments();
		
	}

}
