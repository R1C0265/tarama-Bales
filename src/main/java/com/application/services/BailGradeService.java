package com.application.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.application.data.Bail;
import com.application.data.BailGrade;
import com.application.data.BailGradeRepository;
import com.application.data.BailRepository;
import com.application.data.Updates;
import com.application.data.UpdatesRepository;
import com.application.security.SecurityUtils;
import jakarta.transaction.Transactional;

@Service
public class BailGradeService {

    private final BailRepository bailRepository;
    private final BailGradeRepository bailGradeRepository;

    public BailGradeService(BailGradeRepository bailGradeRepository, BailRepository bailRepository) {
        this.bailRepository = bailRepository;
        this.bailGradeRepository = bailGradeRepository;
    }

    public Optional<BailGrade> get(Long id) {
        return bailGradeRepository.findById(id);
    }

    public List<BailGrade> getBailGrades() {
        return bailGradeRepository.findAllGradeNumbers();
    }

    public int count() {
        return (int) bailGradeRepository.count();
    }

    public List<BailGrade> listAllBails() {
        return bailGradeRepository.findAll();
    }
    /*
     * @Transactional
     * public void delete(Long id) {
     * Optional<BailGrade> bailGrade = bailGradeRepository.findById(id);
     * if (bailGrade.isPresent()) {
     * // Add an update entry for the deletion
     * Updates update = new Updates();
     * update.setImage("https://randomuser.me/api/portraits/men/42.jpg"); // Example
     * image
     * update.setRecordedBy(SecurityUtils.getLoggedInUsername());
     * update.setDate(LocalDate.now().toString());
     * update.setTitle("Deleted a Bale");
     * update.setCategory("Delete");
     * update.setAmount("MWK " + bail.get().getBailPrice());
     * updatesRepository.save(update);
     * 
     * bailGradeRepository.deleteById(id);
     * }
     * }
     */

    /*
     * @Transactional
     * public Bail update(Bail bail) {
     * boolean isNew = (bail.getId() == null); // Check if this is a new Bail
     * if (bail.getRecordedBy() == null || bail.getRecordedBy().isEmpty()) {
     * bail.setRecordedBy(SecurityUtils.getLoggedInUsername());
     * }
     * Bail savedBail = bailGradeRepository.save(bail);
     * 
     * Updates update = new Updates();
     * // Add an update entry if this is a new Bail
     * if (isNew) {
     * update.setTitle("Added a new Bale");
     * update.setCategory("Bale Added");
     * update.setDate(LocalDate.now().toString());
     * update.setAmount("MWK " + bail.getBailPrice());
     * updatesRepository.save(update);
     * }
     * 
     * return savedBail;
     * }
     */

    /*
     * @Transactional
     * public void processSale(String bailName, int itemsSold) {
     * Bail bail = bailGradeRepository.findByBailName(bailName)
     * .orElseThrow(() -> new RuntimeException("Bail not found"));
     * if(bail.getAmounOfItems() < itemsSold) {
     * throw new RuntimeException("Not enough items in stock");
     * }
     * bail.setAmounOfItems(bail.getAmounOfItems() - itemsSold);
     * 
     * // Set the recordedBy field to the logged-in user
     * bail.setRecordedBy(SecurityUtils.getLoggedInUsername());
     * 
     * bailGradeRepository.save(bail);
     * }
     */
    @Transactional
    public void addGradeToBail(Long bailId, BailGrade grade) {
        Bail bail = bailRepository.findById(bailId)
            .orElseThrow(() -> new RuntimeException("Bail not found"));
        BailGrade newGrade = new BailGrade();
        newGrade.setBail(bail);
        newGrade.setGradeNumber(grade.getGradeNumber());
        newGrade.setInitialQuantity(grade.getInitialQuantity());
        newGrade.setCurrentQuantity(grade.getCurrentQuantity());
        newGrade.setPricePerItem(grade.getPricePerItem());

        newGrade.setRecordedBy(SecurityUtils.getLoggedInUsername());
        newGrade.setCreatedDate(LocalDate.now());

        bailGradeRepository.save(newGrade);
    }

}
