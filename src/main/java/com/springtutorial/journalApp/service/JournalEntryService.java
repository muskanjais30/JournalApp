package com.springtutorial.journalApp.service;

import com.springtutorial.journalApp.entity.JournalEntry;
import com.springtutorial.journalApp.entity.User;
import com.springtutorial.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        try{
            User user = userService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry newEntry = journalEntryRepository.save(journalEntry);

            user.getJournalEntries().add(newEntry);  // for linking the journalEntries to the respective user

            userService.saveEntry(user);
        } catch(Exception e){
            log.info("There is some error");
            throw new RuntimeException("An error occured while saving journal entry.", e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId myId){
        return journalEntryRepository.findById(myId);
    }

    @Transactional
    public boolean deleteById(ObjectId myId, String username){
        boolean removed = false;
        try{
            User user = userService.findByUserName(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
            if(removed){
                userService.saveEntry(user);
                journalEntryRepository.deleteById(myId);
            }
        } catch(Exception e){
            log.error("Error");
            throw new RuntimeException("An error occured while deleting te entry. ",e);
        }

        return removed;
    }
}



