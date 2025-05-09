package com.application.services;

import com.application.data.Purchase;
import com.application.data.PurchaseRepository;
import com.application.data.Updates;
import com.application.data.UpdatesRepository;
import com.application.security.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UpdatesRepository updatesRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, UpdatesRepository updatesRepository) {
        this.purchaseRepository = purchaseRepository;
        this.updatesRepository = updatesRepository;
    }

    public Optional<Purchase> get(Long id) {
        return purchaseRepository.findById(id);
    }

    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }

    public Page<Purchase> list(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    public Page<Purchase> list(Pageable pageable, Specification<Purchase> filter) {
        return purchaseRepository.findAll(filter, pageable);
    }

    public int count() {
        return (int) purchaseRepository.count();
    }

    @Transactional
    public Purchase update(Purchase purchase) {
        if (purchase.getCashier() == null || purchase.getCashier().isEmpty()) {
            purchase.setCashier(SecurityUtils.getLoggedInUsername());
        }
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // Add an update entry for the sale
        Updates update = new Updates();
        update.setImage("https://randomuser.me/api/portraits/women/42.jpg"); // Example image
        update.setRecordedBy(SecurityUtils.getLoggedInUsername());
        update.setDate(LocalDate.now().toString());
        update.setTitle("Recorded a Sale");
        update.setCategory("Sale");
        update.setAmount("MWK " + purchase.getPrice());
        updatesRepository.save(update);

        return savedPurchase;
    }

}
