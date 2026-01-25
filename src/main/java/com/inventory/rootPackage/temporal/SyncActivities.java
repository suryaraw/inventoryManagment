package com.inventory.rootPackage.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SyncActivities {
	
	@ActivityMethod
    void log(String msg);

    @ActivityMethod
    void fetchItems();

    @ActivityMethod
    void generateEmbeddings();

    @ActivityMethod
    void uploadToPinecone();

}
