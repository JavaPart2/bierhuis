package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.BestelbonLijn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class JdbcBestelBonLijnRepository implements BestelBonLijnRepository{
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final RowMapper<BestelbonLijn> pizzaMapper =
            (result, rowNum) -> new BestelbonLijn(
                    result.getInt("bestelbonid"),
                    result.getInt("bierid"),
                    result.getInt("aantal"),
                    result.getBigDecimal("prijs"));

    public JdbcBestelBonLijnRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template)
                .withTableName("bestelbonlijnen");

    }

    @Override
    public void insertBestelBonLijn(BestelbonLijn bestelbonLijn) {
        var kolomWaarden = Map.of("bestelbonid", bestelbonLijn.getBestelbon(),
                "bierid", bestelbonLijn.getBier(),
                "aantal", bestelbonLijn.getAantal(),
                "prijs", bestelbonLijn.getPrijs());
        insert.execute(kolomWaarden);
    }
}
