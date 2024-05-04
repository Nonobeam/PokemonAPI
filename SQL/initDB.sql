CREATE DATABASE PokemonStrategy;

USE PokemonStrategy;

CREATE TABLE Player(
	playerId uniqueidentifier DEFAULT NEWID() PRIMARY KEY,
	name VARCHAR(25),
	turn VARCHAR(10),
	status VARCHAR(10),
    password VARCHAR(100),
    role VARCHAR(10)
)

INSERT INTO Player VALUES 
	(NEWID(), 'Player 1', 'Attack', 'ONLINE', '123', 'Admin'),
	(NEWID(), 'Player 2', 'Attack', 'OFFLINE', '456', 'User')

SELECT * FROM Player

---------------------------------------------------------

CREATE TABLE Pokemon(
	pokemonId uniqueidentifier DEFAULT NEWID() PRIMARY KEY,
	name VARCHAR(25),
	hp INT,
	atk INT,
	def INT,
	type VARCHAR(10)
)

INSERT INTO Pokemon VALUES 
	(NEWID(), 'Glastly', 30, 35, 30, 'Poison'),
	(NEWID(), 'Sceptile', 70, 85, 65, 'Grass'),
	(NEWID(), 'Abomasnow', 90, 92, 75, 'Ice'),
	(NEWID(), 'Bellossom', 75, 80, 95, 'Grass')

SELECT * FROM Pokemon


------------------------------------------

CREATE TABLE PokemonPlayer(
	playerId UNIQUEIDENTIFIER,
    pokemonId UNIQUEIDENTIFIER,
    PRIMARY KEY (playerId, pokemonId),
    FOREIGN KEY (playerId) REFERENCES Player(playerId),
    FOREIGN KEY (pokemonId) REFERENCES Pokemon(pokemonId)
)

SELECT * FROM PokemonPlayer