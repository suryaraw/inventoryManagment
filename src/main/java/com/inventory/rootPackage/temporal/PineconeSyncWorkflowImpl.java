package com.inventory.rootPackage.temporal;

import java.time.Duration;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

public class PineconeSyncWorkflowImpl implements PineconeSyncWorkFlow{
	
	private final SyncActivities act = Workflow.newActivityStub(
			SyncActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(10))   // long-running
                    .setRetryOptions(
                            io.temporal.common.RetryOptions.newBuilder()
                                    .setMaximumAttempts(4)
                                    .build()
                    )
                    .build()
    );


	@Override
	public void syncAll(String mode) {
		act.log("Sync started with mode = " + mode);
        act.fetchItems();
        act.generateEmbeddings();
        act.uploadToPinecone();
        act.log("Sync completed successfully!");
	}

}
