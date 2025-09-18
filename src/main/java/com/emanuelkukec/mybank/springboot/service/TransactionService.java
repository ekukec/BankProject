package com.emanuelkukec.mybank.springboot.service;

import com.emanuelkukec.mybank.springboot.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static java.time.ZoneOffset.UTC;

@Component
public class TransactionService {

    private String bankSlogan;

    private JdbcTemplate jdbcTemplate;

    public TransactionService(@Value("${bank.slogan}")  String bankSlogan, JdbcTemplate jdbcTemplate) {
        this.bankSlogan = bankSlogan;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Transaction> findAll() {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        return jdbcTemplate.query("select id, amount, timestamp, reference, slogan, receiving_user from transactions", TransactionService::mapRow);
    }

    private static Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction tx = new Transaction();
        tx.setId(rs.getObject("id").toString());
        tx.setAmount(rs.getBigDecimal("amount"));
        tx.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime().atZone(UTC));
        tx.setReference(rs.getString("reference"));
        tx.setBankSlogan(rs.getString("slogan"));
        tx.setReceivingUserId(rs.getString("receiving_user"));
        return tx;
    }

    @Transactional
    public Transaction create(BigDecimal amount, String reference, String receivingUserId) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into transactions (amount, timestamp, reference, slogan, receiving_user) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, amount);
            ps.setTimestamp(2, Timestamp.from(ZonedDateTime.now().toInstant()));
            ps.setString(3, reference);
            ps.setString(4, bankSlogan);
            ps.setString(5, receivingUserId);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty()
                ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString()
                : null;

        Transaction transaction = new Transaction();
        transaction.setId(uuid);
        transaction.setAmount(amount);
        transaction.setTimestamp(ZonedDateTime.now());
        transaction.setReference(reference);
        transaction.setBankSlogan(bankSlogan);
        transaction.setReceivingUserId(receivingUserId);
        return transaction;
    }

    @Transactional
    public List<Transaction> findByUserId(String userId) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        String sql = "select id, amount, timestamp, reference, slogan, receiving_user from transactions WHERE id = ?";
        return jdbcTemplate.query(sql, TransactionService::mapRow, userId);
    }
}
