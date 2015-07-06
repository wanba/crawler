package com.qinziwanba.api.service;

import com.qinziwanba.api.dao.NoteDao;
import com.qinziwanba.api.domain.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@Service
public class NoteService {


    @Autowired
    private NoteDao noteDao;

    public Note getNote(String nid) {
        return noteDao.selectNote(nid);
    }

    public Note addNote(String title,String address) {
        return noteDao.addNote(title, address);
    }

    public List<Note.Section> addSection(String nid, List<String> contents) {
        return noteDao.addSections(nid, contents);
    }


    public void updateNote(String nid, String title, String address) {
        noteDao.updateNote(nid, title, address);
    }

    public void updateNoteSection(String nid, String sid, String content) {
        noteDao.updateNoteSection(nid, sid, content);
    }
}
