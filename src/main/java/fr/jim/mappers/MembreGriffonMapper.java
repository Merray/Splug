package fr.jim.mappers;

import fr.jim.config.ConstantesDb;
import fr.jim.entites.MembreGriffon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembreGriffonMapper {


    public MembreGriffonMapper() {
    }

    public List<MembreGriffon> toMembreGriffonList(ResultSet set) {

        List<MembreGriffon> membreGriffonList = new ArrayList<>();
        MembreGriffon membreGriffon;
        try {

            while (set.next()) {
                membreGriffon = new MembreGriffon(set.getInt(ConstantesDb.COL_MEMBRE_GRIFFON_ID),
                        set.getLong(ConstantesDb.COL_MEMBRE_GRIFFON_ID_DISCORD),
                        set.getString(ConstantesDb.COL_MEMBRE_GRIFFON_NOM));

                membreGriffonList.add(membreGriffon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membreGriffonList;

    }

    public MembreGriffon toMembreGriffon(ResultSet set) {

        MembreGriffon membreGriffon = null;

        try {

            if (set.next()) {
                membreGriffon = new MembreGriffon(set.getInt(ConstantesDb.COL_MEMBRE_GRIFFON_ID),
                        set.getLong(ConstantesDb.COL_MEMBRE_GRIFFON_ID_DISCORD),
                        set.getString(ConstantesDb.COL_MEMBRE_GRIFFON_NOM));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membreGriffon;

    }
}
