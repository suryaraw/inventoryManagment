package com.inventory.rootPackage.temporal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;

@Configuration
public class TemporalWorkerConfig {
	
	@Autowired
    private WorkflowClient client;

    @Autowired
    private SyncActivitiesImpl activities;

    @PostConstruct
    public void startWorker() {

        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("SYNC-TASK-QUEUE");

        worker.registerWorkflowImplementationTypes(PineconeSyncWorkflowImpl.class);
        worker.registerActivitiesImplementations(activities);

        factory.start();

        System.out.println("Temporal Worker Started on SYNC-TASK-QUEUE");
    }

}
