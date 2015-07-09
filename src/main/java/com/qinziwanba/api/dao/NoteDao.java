package com.qinziwanba.api.dao;

import com.qinziwanba.api.domain.Child;
import com.qinziwanba.api.domain.Note;
import com.qinziwanba.api.domain.User;
import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.dao.AbstractDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@Repository
public class NoteDao extends AbstractDao {

    private final String SQL_NOTE_INSERT = "insert into note(`nid`, `title`, `address`, `updated_at`) values(?,?,?,?)" +
                                " on duplicate key update `title` = ?, `address` = ?,`updated_at` = ?;";

    private final String SQL_NOTE_SELECT = "select `nid`, `title`, `outline`, `address`, `image_url` as imageUrl, `updated_at` as updatedAt from note where nid=?;";

    private final String SQL_NOTE_UPDATE = "update note set ";

    private final String SQL_NOTE_SECTION_INSERT = "insert into section(`sid`, `nid`, `content`, `updated_at`) values(?,?,?,?)" +
            " on duplicate key update `nid` = ?, `content` = ?, `updated_at` = ?;";

    private final String SQL_NOTE_SECTION_SELECT = "select `sid`, `content`, `updated_at` as updatedAt from section where nid=? ;";

    private final String SQL_NOTE_SECTION_UPDATE = "update section set `content` = ? where sid = ?; ";


    public Note selectNote(String nid) {
        WanbaLogger.debug("{} select note, nid={}", this.getClass(), nid);

        // select note

        Note note = jdbcTemplate.queryForObject(SQL_NOTE_SELECT, new Object[] { nid },
                ParameterizedBeanPropertyRowMapper.newInstance(Note.class));

        // select sections
        List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL_NOTE_SECTION_SELECT, nid);
        List<Note.Section> sections = new ArrayList<Note.Section>();
        for (Map<String, Object> item : list) {
            Note.Section section = new Note.Section(String.valueOf(item.get("sid")),String.valueOf(item.get("content")));
            sections.add(section);
        }
        if (sections!=null && !sections.isEmpty()) {
            note.setSectionList(sections);
        }
        return note;
    }

    public Note addNote(String title,String address) {
        WanbaLogger.debug("{} add note, title={} address={}", this.getClass(), title, address);

        String nid = java.util.UUID.randomUUID().toString();
        Long cur = System.currentTimeMillis();
        // add note
        jdbcTemplate.update(SQL_NOTE_INSERT,nid, title, address, cur, title, address, cur);

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

            jdbcTemplate.update(SQL_NOTE_SECTION_INSERT, sid, nid, content, cur, nid, content, cur);
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

            jdbcTemplate.update(sql, title, address, nid);
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
