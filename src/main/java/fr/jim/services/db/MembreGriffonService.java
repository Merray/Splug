package fr.jim.services.db;

import fr.jim.entites.MembreGriffon;
import fr.jim.mappers.MembreGriffonMapper;
import fr.jim.repositories.MembresGriffonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MembreGriffonService {

    private final Logger LOGGER = LogManager.getLogger(MembreGriffonService.class);

    private MembresGriffonRepository membresGriffonRepository = new MembresGriffonRepository();

    private MembreGriffonMapper membreGriffonMapper = new MembreGriffonMapper();

    public MembreGriffonService() {
    }

    public void emptyTable() {
        membresGriffonRepository.emptyTable();
        LOGGER.info("La table membres_griffon a été réinitialisée");
    }

    public void create(MembreGriffon membreGriffon) {

        ResultSet rs = membresGriffonRepository.findByIdDiscord(membreGriffon.getIdDiscord());

        try {
            if (rs.next()) {
                LOGGER.info("Le membre griffon " + membreGriffon.getIdDiscord()
                        + " existe déjà en base. Utiliser la méthode update à la place.");
            } else {
                membresGriffonRepository.create(membreGriffon);
                LOGGER.info("MembreGriffon created : id_discord = " + membreGriffon.getIdDiscord());
            }
        } catch (SQLException e) {
            LOGGER.info("Erreur dans le create", e);
        }

    }

    public void deleteByIdDiscord(long idDiscord) {

        ResultSet rs = membresGriffonRepository.findByIdDiscord(idDiscord);

        try {
            if (rs.next()) {
                membresGriffonRepository.deleteByIdDiscord(idDiscord);
                LOGGER.info("MembreGriffon supprimé : idDiscord = " + idDiscord);
            } else {
                LOGGER.info("Pas de mate avec un idDiscord de " + idDiscord);
            }
        } catch (SQLException e) {
            LOGGER.info("Erreur dans le delete", e);
        }

    }

    public List<MembreGriffon> findAll() {
        ResultSet rs = membresGriffonRepository.findAll();

        return membreGriffonMapper.toMembreGriffonList(rs);
    }

    public MembreGriffon findByIdDiscord(long idDiscord) {

        ResultSet rs = membresGriffonRepository.findByIdDiscord(idDiscord);

        return membreGriffonMapper.toMembreGriffon(rs);
    }

    public MembreGriffon findByNom(String nom) {

        ResultSet rs = membresGriffonRepository.findByNom(nom);

        return membreGriffonMapper.toMembreGriffon(rs);
    }
}
