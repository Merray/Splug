package fr.jim.repositories;

import fr.jim.entites.MembreGriffon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MembresGriffonRepository extends BasicRepository {

    private final Logger LOGGER = LogManager.getLogger(MembresGriffonRepository.class);

    public void emptyTable() {
        Statement statement;
        try (Connection c = this.connecter();) {
            c.setAutoCommit(false);
            statement = c.createStatement();
            statement.executeUpdate("DELETE FROM membres_griffon");
            c.commit();

        } catch (SQLException e) {
            LOGGER.info("Erreur dans le emptyTable", e);
        }
    }

    public void create(MembreGriffon membreGriffon) {
        PreparedStatement statement;
        try (Connection c = this.connecter();) {
            c.setAutoCommit(false);
            statement = c.prepareStatement(
                    "INSERT INTO membres_griffon (id_discord, nom, classe, race) VALUES (?, ?, ?, ?)");
            statement.setLong(1, membreGriffon.getIdDiscord());
            statement.setString(2, membreGriffon.getNom());
            statement.setString(3, membreGriffon.getClasse());
            statement.setString(4, membreGriffon.getRace());
            statement.executeUpdate();
            c.commit();

        } catch (SQLException e) {
            LOGGER.info("Erreur dans le create", e);
        }
    }


    public void deleteByIdDiscord(long idDiscord) {
        PreparedStatement statement;
        try (Connection c = this.connecter();) {
            c.setAutoCommit(false);
            statement = c.prepareStatement("DELETE FROM membres_griffon WHERE id_discord = ?");
            statement.setLong(1, idDiscord);
            statement.executeUpdate();

            c.commit();

        } catch (SQLException e) {
            LOGGER.info("Erreur dans le delete", e);
        }

    }

    public ResultSet findAll() {
        Statement statement;
        try (Connection c = this.connecter()) {
            statement = c.createStatement();
            return statement.executeQuery("SELECT * from membres_griffon");

        } catch (SQLException e) {
            LOGGER.info("Erreur dans le findAll", e);
        }

        return null;
    }

    public ResultSet findByIdDiscord(long idDiscord) {
        PreparedStatement statement;
        try (Connection c = this.connecter()) {
            statement = c.prepareStatement("SELECT * from membres_griffon WHERE id_discord = ?");
            statement.setLong(1, idDiscord);
            return statement.executeQuery();

        } catch (SQLException e) {
            LOGGER.info("Erreur dans le findByName", e);
        }

        return null;
    }

    public ResultSet findByNom(String nom) {
        PreparedStatement statement;
        try (Connection c = this.connecter();) {
            statement = c.prepareStatement("SELECT * from membres_griffon WHERE nom = ?");
            statement.setString(1, nom);
            return statement.executeQuery();

        } catch (SQLException e) {
            LOGGER.info("Erreur dans le findByName", e);
        }

        return null;
    }


}
