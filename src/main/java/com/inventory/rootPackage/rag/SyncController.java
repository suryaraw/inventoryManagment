package com.inventory.rootPackage.rag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class SyncController {
	
	@Autowired
	private  DatabaseSyncService syncService;


    @GetMapping("/sync-db")
    public String syncNow() throws Exception {
        syncService.syncAll();
        return "DB Synced to Pinecone Successfully!";
    }

}
