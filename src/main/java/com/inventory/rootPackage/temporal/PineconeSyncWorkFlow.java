package com.inventory.rootPackage.temporal;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface PineconeSyncWorkFlow {
	
	@WorkflowMethod
	void syncAll(String mode);

}
