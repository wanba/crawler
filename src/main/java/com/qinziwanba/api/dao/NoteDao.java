package com.qinziwanba.api.dao;

import com.qinziwanba.api.domain.Note;
import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.dao.AbstractDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@Repository
public class NoteDao extends AbstractDao {

    private final String SQL_NOTE_INSERT = "insert into note(`nid`, `title`, `address`, `updated_at`) values(?,?,?,?)";

    private final String SQL_NOTE_SELECT = "select `nid`, `title`, `outline`, `address`, `image_url` as imageUrl, `updated_at` as updatedAt from note where nid=?;";

    private final String SQL_NOTE_UPDATE = "update note set ";

    private final String SQL_NOTE_SECTION_INSERT = "insert into section(`sid`, `nid`, `content`, `updated_at`) values(?,?,?,?)";

    private final String SQL_NOTE_SECTION_SELECT = "select `sid`, `content`, `updated_at` as updatedAt from section where nid=? ;";

    private final String SQL_NOTE_SECTION_UPDATE = "update section set `content` = ? where sid = ?; ";


    public Note selectNote(String nid) {
        WanbaLogger.debug("{} select note, nid={}", this.getClass(), nid);

        // select note
        Note note = jdbcTemplate.queryForObject(SQL_NOTE_SELECT, new Object[] { nid }, Note.class);

        // select sections
        List<Note.Section> sections = jdbcTemplate.queryForList(SQL_NOTE_SECTION_SELECT,new Object[] { nid }, Note.Section.class);
        if (sections!=null && !sections.isEmpty()) {
            note.addSections(sections);
        }
        return note;
    }

    public Note addNote(String title,String address) {
        WanbaLogger.debug("{} add note, title={} address={}", this.getClass(), title, address);

        String nid = java.util.UUID.randomUUID().toString();
        Long cur = System.currentTimeMillis();
        // add note
        jdbcTemplate.update(SQL_NOTE_INSERT, new Object[]{nid, title, address, cur});

        Note note = new Note(nid, title, address);
        note.setUpdatedAt(cur);
        return note;
    }

    public List<Note.Section> addSections(String nid, List<String> contents) {
        WanbaLogger.debug("{} add section, nid={} content={}", this.getClass(), nid, contents);

        List<Note.Section> sections = new ArrayList<Note.Section>();
        for (String content : contents) {
            String sid = java.util.UUID.randomUUID().toString();
            Long cur = System.currentTimeMillis();

            jdbcTemplate.update(SQL_NOTE_SECTION_INSERT, sid, nid, content, cur);
            Note.Section section = new Note.Section(sid,content);
            sections.add(section);
        }

        return sections;
    }

    public void updateNote(String nid, String title, String address) {
        WanbaLogger.debug("{} update note, title={} address={}", this.getClass(), title, address);

        String sql = SQL_NOTE_UPDATE;
        if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(address)) {
            sql += " `title` = ? , `address` = ? where nid = ? ;";

            jdbcTemplate.update(sql, new Object[] {title, address, nid});
        }else {
            if (StringUtils.isNotBlank(title)) {
                sql += " `title` = ? where nid = ?; ";
                jdbcTemplate.update(sql, new Object[] {title, nid});
            }else if (StringUtils.isNotBlank(address)) {
                sql += " `address` = ? where nid = ?;" ;
                jdbcTemplate.update(sql, new Object[] {address, nid});
            }
        }
    }

    public void updateNoteSection(String nid, String sid, String content) {
        WanbaLogger.debug("{} update note section, nid={} sid={} content={}", this.getClass(), nid, sid, content );

        jdbcTemplate.update(SQL_NOTE_SECTION_UPDATE, content, sid);
    }
}
