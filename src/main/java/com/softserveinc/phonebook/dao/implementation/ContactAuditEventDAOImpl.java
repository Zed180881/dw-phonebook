
package com.softserveinc.phonebook.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softserveinc.phonebook.api.ContactAuditEvent;
import com.softserveinc.phonebook.dao.ContactAuditEventDAO;

@Repository("contactAuditEventDao")
public class ContactAuditEventDAOImpl implements ContactAuditEventDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int createContactAuditEvent(ContactAuditEvent contactAuditEvent) {
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(contactAuditEvent);
        return namedParameterJdbcTemplate.update(
                "INSERT INTO contactaudit (contact_id, change_type, origin_value, user_name) VALUES (:contactId, :changeType, :originValue, :userName)",
                namedParameters);
    }

    @Override
    public List<ContactAuditEvent> getAllContactAuditEvents() {
        return namedParameterJdbcTemplate.query("SELECT * FROM contactaudit", new ContactAuditEventMapper());
    }

    @Override
    public List<ContactAuditEvent> getContactAuditEventsByUsername(String username) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("username", username);
        return namedParameterJdbcTemplate.query("SELECT * FROM contactaudit WHERE user_name = :username",
                namedParameters, new ContactAuditEventMapper());
    }

    @Override
    public List<ContactAuditEvent> getContactAuditEventsByContactId(int contactId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("contactId", contactId);
        return namedParameterJdbcTemplate.query("SELECT * FROM contactaudit WHERE contact_id = :contactId",
                namedParameters, new ContactAuditEventMapper());
    }

    private static final class ContactAuditEventMapper implements RowMapper<ContactAuditEvent> {
        @Override
        public ContactAuditEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
            ContactAuditEvent contactAuditEvent = new ContactAuditEvent();
            contactAuditEvent.setId(rs.getInt("id"));
            contactAuditEvent.setContactId(rs.getInt("contact_id"));
            contactAuditEvent.setEventDate(rs.getString("event_date"));
            contactAuditEvent.setChangeType(rs.getString("change_type"));
            contactAuditEvent.setOriginValue(rs.getString("origin_value"));
            contactAuditEvent.setUserName(rs.getString("user_name"));
            return contactAuditEvent;
        }
    }

}
