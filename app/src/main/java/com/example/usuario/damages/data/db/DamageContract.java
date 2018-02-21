package com.example.usuario.damages.data.db;

import android.provider.BaseColumns;

/**
 * Contiene todas las sentencias SQL de Damages
 * @see DamageOpenHelper
 */
public class DamageContract {

    public static final String DATABASE_NAME = "damages";
    public static final int DATABASE_VERSION = 3;
    private DamageContract() { }

    public static class DamageEntries implements BaseColumns {
        public static final String TABLE = "damage";
        public static final String  COL_CODE = "code";
        public static final String  COL_CITY = "city";
        public static final String  COL_DATA = "data";
        public static final String  COL_DESCRIPTION = "description";

        public static final String ORDER = COL_CITY;

        public static final String REFERENCES_CITY = String.format(
                "FOREIGN KEY (%s) REFERENCES %s(%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                COL_CITY, CityEntries.TABLE, BaseColumns._ID
        );

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s)",
                TABLE,
                BaseColumns._ID,
                COL_CODE,
                COL_CITY,
                COL_DATA,
                COL_DESCRIPTION,
                REFERENCES_CITY
        );

        public static final String[] ALL_COLUMNS = {
                BaseColumns._ID,
                COL_CODE,
                COL_CITY,
                COL_DATA,
                COL_DESCRIPTION
        };

        public static final String INSERT_ENTRIES = String.format(
                "INSERT INTO %s (%s, %s, %s, %s) VALUES ",
                TABLE, COL_CODE, COL_CITY, COL_DATA, COL_DESCRIPTION
        ) + String.format(
                "('%s', '%s', '%s', '%s'),",
                "E",
                "7",
                "2018-02-21",
                "Fallo eléctrico Endesa"
        ) + String.format(
                "('%s', '%s', '%s', '%s'),",
                "F",
                "4",
                "2018-02-21",
                "Incendio en el Zoco"
        )+ String.format(
                "('%s', '%s', '%s', '%s'),",
                "A",
                "8",
                "2018-02-21",
                "Inundación. El agua en Sevilla ya no es una maravilla"
        )+ String.format(
                "('%s', '%s', '%s', '%s'),",
                "B",
                "5",
                "2018-02-20",
                "Mucho viento en la playa"
        )+ String.format(
                "('%s', '%s', '%s', '%s'),",
                "C",
                "2",
                "2018-02-21",
                "Carnaval desenfrenado"
        )+ String.format(
                "('%s', '%s', '%s', '%s'),",
                "J",
                "6",
                "2018-02-18",
                "Muncho aceite de oliva mutante"
        )+ String.format(
                "('%s', '%s', '%s', '%s'),",
                "G",
                "3",
                "2018-02-19",
                "Diesisei gaseosa"
        )+ String.format(
                "('%s', '%s', '%s', '%s')",
                "D",
                "1",
                "2018-02-22",
                "Demasiao caló y poca agua"
        );

        public static final String DROP_TABLE = String.format(
                "DROP TABLE IF EXISTS %s", TABLE
        );
    }

    public static class CityEntries implements BaseColumns {
        public static final String TABLE = "city";
        public static final String COL_NAME = "name";

        public static final String ORDER = COL_NAME;

        public static final String[] ALL_COLUMNS = {
                BaseColumns._ID,
                COL_NAME
        };

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL" +
                        ")",
                TABLE,
                BaseColumns._ID,
                COL_NAME
        );

        public static final String INSERT_ENTRIES = String.format(
                "INSERT INTO %s (%s) VALUES ",
                TABLE, COL_NAME
        ) + String.format(
                "('%s'),",
                "Almería"
        ) + String.format(
                "('%s'),",
                "Cádiz"
        ) + String.format(
                "('%s'),",
                "Córdoba"
        ) + String.format(
                "('%s'),",
                "Granada"
        ) + String.format(
                "('%s'),",
                "Huelva"
        ) + String.format(
                "('%s'),",
                "Jaén"
        ) + String.format(
                "('%s'),",
                "Málaga"
        ) + String.format(
                "('%s')",
                "Sevilla"
        );

        public static final String DROP_TABLE = String.format(
                "DROP TABLE IF EXISTS %s", TABLE
        );

    }
}
