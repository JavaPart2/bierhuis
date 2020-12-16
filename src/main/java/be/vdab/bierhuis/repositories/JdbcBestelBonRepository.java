package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class JdbcBestelBonRepository implements BestelBonRepository{
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final RowMapper<Bestelbon> pizzaMapper =
            (result, rowNum) -> new Bestelbon(
                    result.getInt("id"),
                    result.getString("naam"),
                    result.getString("straat"),
                    result.getString("huisNr"),
                    result.getInt("postcode"),
                    result.getString("gemeente"));

    public JdbcBestelBonRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template)
                .withTableName("bestelbonnen").usingGeneratedKeyColumns("id");

    }

    @Override
    public int insertBestelBon(Bestelbon bestelbon) {
        var kolomWaarden = Map.of("naam", bestelbon.getNaam(),
                "straat", bestelbon.getStraat(),
                "huisNr", bestelbon.getHuisNr(),
                "postcode", bestelbon.getPostcode(),
                "gemeente", bestelbon.getGemeente());
        var id = insert.executeAndReturnKey(kolomWaarden);
        return id.intValue();
    }
}
