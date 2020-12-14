package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.domain.Soort;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcSoortRepository implements SoortRepository{
    private final JdbcTemplate template;
    private final RowMapper<Soort> soortMapper =
            (result, rowNum) -> new Soort(result.getInt("id"),
                    result.getString("naam"));

    public JdbcSoortRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Soort> findById(int id) {
        try {
            var sql = "select id, naam from soorten where id = ?";
            return Optional.of(template.queryForObject(sql, soortMapper, id));
        }catch (IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }
}
