package com.example.gccoffee.repository;

import com.example.gccoffee.model.Review;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.example.gccoffee.JdbcUtils.toLocalDateTime;
import static com.example.gccoffee.JdbcUtils.toUUID;

@Repository
public class ReviewJdbcRepository implements ReviewRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ReviewJdbcRepository(NamedParameterJdbcTemplate JdbcTemplate) {
        this.jdbcTemplate = JdbcTemplate;
    }

    @Override
    public List<Review> findAll() {
        return jdbcTemplate.query("select * from reviews", reviewRowMapper);
    }

    @Override
    public Review insert(Review review) {
        int update = jdbcTemplate.update("INSERT INTO reviews(review_id, product_id, title, content, created_at, updated_at)" +
                " VALUES (UUID_TO_BIN(:reviewId), UUID_TO_BIN(:productId), :title, :content, :createdAt, :updatedAt)", toParamMap(review));
        if (update != 1) {
            throw new RuntimeException("Noting was inserted");
        }
        return review;
    }

    @Override
    public Review update(Review review) {
        var update = jdbcTemplate.update(
                "UPDATE reviews SET product_id = :productId, title = :title, content = :content, created_at = :createdAt, updated_at = :updatedAt" +
                        " WHERE product_id = UUID_TO_BIN(:reviewId)",
                toParamMap(review)
        );
        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return review;
    }

    @Override
    public Optional<Review> findById(UUID reviewId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM reviews WHERE review_id = UUID_TO_BIN(:reviewId)",
                            Collections.singletonMap("reviewId", reviewId.toString().getBytes()), reviewRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Review> findByProductId(UUID productId)
    {
        return jdbcTemplate.query("SELECT * FROM reviews WHERE product_id = UUID_TO_BIN(:productId)",
                            Collections.singletonMap("productId", productId.toString().getBytes()), reviewRowMapper);

    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM reviews", Collections.emptyMap());
    }

    private static final RowMapper<Review> reviewRowMapper = (resultSet, i) -> {
        var reviewId = toUUID(resultSet.getBytes("review_id"));
        var productId = toUUID(resultSet.getBytes("product_id"));
        var title = resultSet.getString("title");
        var content = resultSet.getString("content");
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Review(reviewId, productId, title, content, createdAt, updatedAt);
    };

    private Map<String, Object> toParamMap(Review review) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("reviewId", review.getReviewId().toString().getBytes());
        paramMap.put("productId", review.getProductId().toString().getBytes());
        paramMap.put("title", review.getTitle());
        paramMap.put("content", review.getContent());
        paramMap.put("createdAt", review.getCreateAt());
        paramMap.put("updatedAt", review.getUpdateAt());
        return paramMap;
    }
}
