package com.inventory.rootPackage.rag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.Pinecone.PineconeIndexer;
import com.inventory.rootPackage.repository.ItemRepository;
import com.inventory.rootPackage.repository.OrderRepo;
import com.inventory.rootPackage.repository.PaymentRepository;
import com.inventory.rootPackage.repository.PaymentResponseRepository;
import com.inventory.rootPackage.repository.SupplierRepo;

@Service
public class DatabaseSyncService {
	
	@Autowired
	private  ItemRepository itemRepo;
	@Autowired
    private  SupplierRepo wholesalerRepo;
	@Autowired
    private  OrderRepo shoperPaidRepo;
	@Autowired
    private  PaymentRepository paymentEntityRepo;
	@Autowired
    private  PaymentResponseRepository paymentResponseRepo;
	
	@Autowired
    private  RecordFormatter formatter;
	@Autowired
    private  PineconeIndexer indexer;


    // 🔥 MAIN SYNC METHOD
    public void syncAll() throws Exception {

        syncItems();
        syncWholesalers();
        syncShoperPaid();
        syncPayments();
        syncPaymentResponses();
        
    }

    // 🟦 SYNC ITEMS
    public void syncItems() {
        itemRepo.findAll().forEach(item -> {
            try {
                String text = formatter.formatItem(item);
                indexer.indexRecord("ITEM-" + item.getId(), text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 🟩 SYNC WHOLESALERS
    public void syncWholesalers() {
        wholesalerRepo.findAll().forEach(supplier -> {
            try {
                String text = formatter.formatWholesaler(supplier);
                indexer.indexRecord("SUP-" + supplier.getId(), text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 🟧 SYNC SHOPERPaid (Purchased Records)
    public void syncShoperPaid() {
        shoperPaidRepo.findAll().forEach(sp -> {
            try {
                String text = formatter.formatShoperPaid(sp);
                indexer.indexRecord("PAID-" + sp.getS_no(), text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 🟥 SYNC PAYMENT ENTITY
    public void syncPayments() {
        paymentEntityRepo.findAll().forEach(pe -> {
            try {
                String text = formatter.formatPaymentEntity(pe);
                indexer.indexRecord("PAY-" + pe.getId(), text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 🟪 SYNC PAYMENT RESPONSES
    private void syncPaymentResponses() {
        paymentResponseRepo.findAll().forEach(pr -> {
            try {
                String text = formatter.formatPaymentResponse(pr);
                indexer.indexRecord("RESP-" + pr.getId(), text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
