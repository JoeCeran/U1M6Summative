package com.example.U1M6Summative.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class ItemDaoJdbcTemplateImpl {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_ALBUM_SQL =
            "insert into album (title, artist_id, release_date, label_id, list_price) values (?, ?, ?, ?, ?)";

    private static final String SELECT_ALBUM_SQL =
            "select * from album where album_id = ?";

    private static final String SELECT_ALL_ALBUMS_SQL =
            "select * from album";

    private static final String UPDATE_ALBUM_SQL =
            "update album set title = ?, artist_id = ?, release_date = ?, label_id = ?, list_price = ? where album_id = ?";

    private static final String DELETE_ALBUM =
            "delete from album where album_id = ?";
}