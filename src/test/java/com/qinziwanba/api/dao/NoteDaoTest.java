package com.qinziwanba.api.dao;

import com.qinziwanba.AbstractTest;
import com.qinziwanba.api.domain.Note;
import com.qinziwanba.crawler.dao.AbstractDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangzhiguo on 15/7/9.
 */
public class NoteDaoTest extends AbstractTest {

    @Autowired
    private NoteDao noteDao;

    private String nid = "";
    private String sid = "";

    @Before
    public void setUp() throws Exception {
        testAddNote();

        testAddSections();
    }


    @Test
    public void testSelectNote() throws Exception {
        Note note = noteDao.selectNote(nid);
        Assert.assertNotNull(note);
        Assert.assertEquals(nid,note.getNid());
    }

    public void testAddNote() throws Exception {
        Note note = noteDao.addNote("title","content");
        nid = note.getNid();
        Assert.assertNotNull(note);
    }

    public void testAddSections() throws Exception {
        List<String> sections = new ArrayList<String>();
        sections.add("section content");

        List<Note.Section> sectionList = noteDao.addSections(nid, sections);
        sid = sectionList.get(0).getSid();
        Assert.assertNotNull(sectionList);
        Assert.assertEquals(1,sectionList.size());
    }

    @Test
    public void testUpdateNote() throws Exception {
        noteDao.updateNote(nid,"new title", "address");

        Note note = noteDao.selectNote(nid);
        Assert.assertNotNull(note);
        Assert.assertEquals("new title",note.getTitle());
    }

    @Test
    public void testUpdateNoteSection() throws Exception {
        noteDao.updateNoteSection(nid,sid,"new content");

        Note note = noteDao.selectNote(nid);
        Assert.assertNotNull(note);
        Assert.assertEquals("new content", note.getSectionList().get(0).getContent());
    }
}