package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.exceptions.BierNietGevondenException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBierRepository implements BierRepository{
    private final JdbcTemplate template;
    private final RowMapper<Bier> bierMapper =
            (result, rowNum) -> new Bier(result.getInt("id"),
                    result.getString("naam"),
                    result.getInt("brouwerid"),
                    result.getInt("soortid"),
                    result.getBigDecimal("alcohol"),
                    result.getBigDecimal("prijs"),
                    result.getInt("besteld"));

    public JdbcBierRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public int countAll() {
        return template.queryForObject("select count(*) from bieren", int.class);
    }

    @Override
    public List<Bier> findByBrouwer(Brouwer brouwer) {
        String sql = "select id, naam, brouwerid, soortid, alcohol, prijs, besteld from bieren where brouwerid= ? order by naam";
        return template.query(sql, bierMapper, brouwer.getId());
    }

    @Override
    public Optional<Bier> findById(int id) {
        try {
            var sql = "select id, naam, brouwerid, soortid, alcohol, prijs, besteld from bieren where id = ?";
            return Optional.of(template.queryForObject(sql, bierMapper, id));
        }catch (IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public void updateBier(Bier bier) {
        var sql = "update bieren set besteld=? where id = ?";
        if (template.update(sql, bier.getBesteld(), bier.getId()) == 0){
            throw new BierNietGevondenException();
        }
    }
}
