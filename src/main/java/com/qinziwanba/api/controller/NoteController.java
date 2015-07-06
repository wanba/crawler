package com.qinziwanba.api.controller;

import com.qinziwanba.api.domain.Note;
import com.qinziwanba.api.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@RestController
@RequestMapping(value = "/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "show.json", method = RequestMethod.GET)
    public Note showBlog(@RequestParam(value = "bid", required = true) String bid) {

        return noteService.getNote(bid);
    }

    @RequestMapping(value = "publish.json", method = RequestMethod.POST)
    public Note publishNote(@RequestParam(value = "title", required = true) String title,
                            @RequestParam(value = "address", required = true) String address,
                            @RequestParam(value = "content", required = true) String content) {

        // add note
        Note note = noteService.addNote(title,address);

        // add section
        List<String> contents = new ArrayList<String>();
        contents.add(content);
        List<Note.Section> sections = noteService.addSection(note.getNid(), contents);

        note.addSections(sections);
        return note;
    }

    @RequestMapping(value = "edit.json", method = RequestMethod.POST)
    public void editNote(@RequestParam(value = "nid", required = true) String nid,
                         @RequestParam(value = "title", required = false) String title,
                         @RequestParam(value = "address", required = false) String address) {

        // update note
        noteService.updateNote(nid, title, address);
    }

    @RequestMapping(value = "section/edit.json", method = RequestMethod.POST)
    public void editNoteSection(@RequestParam(value = "nid", required = true) String nid,
                         @RequestParam(value = "sid", required = false) String sid,
                         @RequestParam(value = "content", required = false) String content) {

        // update note
        noteService.updateNoteSection(nid,sid,content);
    }
}
