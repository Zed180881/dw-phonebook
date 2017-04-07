
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

import com.softserveinc.phonebook.api.Contact;
import com.softserveinc.phonebook.dao.ContactDAO;

@Repository("contactDao")
public class ContactDAOImpl implements ContactDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Contact getContactById(int id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM contacts WHERE id = :id", namedParameters,
                new ContactMapper());
    }

    @Override
    public int createContact(Contact contact) {
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(contact);
        return namedParameterJdbcTemplate.update(
                "INSERT INTO contacts (firstname, lastname, phone) VALUES (:firstName, :lastName, :phone)",
                namedParameters);
    }

    @Override
    public void updateContact(Contact contact) {
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(contact);
        namedParameterJdbcTemplate.update(
                "UPDATE contacts SET firstname = :firstName, lastname =:lastName, phone = :phone WHERE id=:id",
                namedParameters);
    }

    @Override
    public void deleteContact(int id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update("DELETE FROM contacts WHERE id = :id", namedParameters);
    }

    @Override
    public List<Contact> getAllContacts() {
        return namedParameterJdbcTemplate.query("SELECT * FROM contacts", new ContactMapper());
    }

    @Override
    public boolean hasNoDuplicates(Contact contact) {
        return getByFullName(contact.getFirstName(), contact.getLastName()).isEmpty();
    }

    @Override
    public List<Contact> getByFullName(String firstName, String lastName) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("firstName", firstName);
        namedParameters.addValue("lastName", lastName);
        return namedParameterJdbcTemplate.query(
                "SELECT * FROM contacts WHERE firstname = :firstName AND lastname = :lastName", namedParameters,
                new ContactMapper());
    }

    private static final class ContactMapper implements RowMapper<Contact> {
        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contact contact = new Contact();
            contact.setId(rs.getInt("id"));
            contact.setFirstName(rs.getString("firstname"));
            contact.setLastName(rs.getString("lastname"));
            contact.setPhone(rs.getString("phone"));
            return contact;
        }
    }

}
