--
-- File generated with SQLiteStudio v3.1.1 on Fri Nov 10 13:36:36 2017
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: artists
CREATE TABLE artists (artistID INTEGER PRIMARY KEY AUTOINCREMENT, artistName STRING, genre STRING);

-- Table: childGenrePlays
CREATE TABLE childGenrePlays (childID REFERENCES childUsers (childID), artistID REFERENCES artists (artistID), timesPlayed INTEGER);

-- Table: childUsers
CREATE TABLE childUsers (childID INTEGER PRIMARY KEY AUTOINCREMENT, userID INTEGER REFERENCES parentUsers (userID), username CHECK (LENGTH(username) <= 20) NOT NULL, password NOT NULL CHECK (LENGTH(password) >= 8), permissionLevel INTEGER DEFAULT (0) CHECK (permissionLevel >= 0 & permissionLevel <= 2));

-- Table: parentGenrePlays
CREATE TABLE parentGenrePlays (userID REFERENCES parentUsers (userID), artistID REFERENCES artists (artistID), timesPlayed INTEGER);

-- Table: parentTrackPlays
CREATE TABLE parentTrackPlays (userID REFERENCES parentUsers (userID), trackID REFERENCES tracks (trackID), timesPlayed INTEGER);

-- Table: parentUsers
CREATE TABLE parentUsers (userID INTEGER PRIMARY KEY AUTOINCREMENT, username STRING CHECK (LENGTH(username) <= 20) NOT NULL, password STRING NOT NULL CHECK (LENGTH(password) >= 8), linkedAccount BOOLEAN DEFAULT FALSE);

-- Table: playlist
CREATE TABLE playlist (playlistID INTEGER PRIMARY KEY AUTOINCREMENT, userID INTEGER REFERENCES parentUsers (userID), playlistName STRING NOT NULL, timesPlayed INTEGER DEFAULT (0));

-- Table: playlistSongs
CREATE TABLE playlistSongs (playlistID REFERENCES playlist (playlistID), trackID REFERENCES tracks (trackID));

-- Table: tracks
CREATE TABLE tracks (trackID INTEGER PRIMARY KEY AUTOINCREMENT, artistID INTEGER REFERENCES artists (artistID), trackName STRING, length INTEGER);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
