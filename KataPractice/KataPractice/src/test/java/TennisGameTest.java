package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.TennisGame;

class TennisGameTest {

	private TennisGame tennisGame;

	@BeforeEach
	public void instantiateObject() {
		tennisGame = new TennisGame();
		tennisGame.resetGame();
		tennisGame.setPlayerNames("Raag", "Shail");
	}

	@Test
	public void reportInitialScore() {
		assertEquals("love-love", tennisGame.reportScore());
	}

	@Test
	public void updateScoreAfterPlayerScoresARegularPoint() {
		tennisGame.playerScores("playerA");
		assertEquals("15-love", tennisGame.reportScore());
		tennisGame.playerScores("playerB");
		assertEquals("15-15", tennisGame.reportScore());
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		assertEquals("40-15", tennisGame.reportScore());
	}

	@Test
	public void announceVictorOnGamePointforA() {
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		assertEquals("Raag wins", tennisGame.reportScore());
	}

	@Test
	public void announceVictorOnGamePointforB() {
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		assertEquals("Shail wins", tennisGame.reportScore());
	}

	@Test
	public void reportingDeuce() {
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		assertEquals("deuce", tennisGame.reportScore());
	}

	@Test
	public void returnAdvantageIfPlayerScoresAfterDeuce() {
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerA");
		assertEquals("advantage Raag", tennisGame.reportScore());
		tennisGame.resetGame();
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		assertEquals("advantage Shail", tennisGame.reportScore());
	}

	@Test
	public void returningToDeuceAfterAdvantage() {
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		assertEquals("advantage Shail", tennisGame.reportScore());
		tennisGame.playerScores("playerA");
		assertEquals("deuce", tennisGame.reportScore());
		tennisGame.resetGame();
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		assertEquals("advantage Raag", tennisGame.reportScore());
		tennisGame.playerScores("playerB");
		assertEquals("deuce", tennisGame.reportScore());
	}

	@Test
	public void winningGameAfterAdvantage() {
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		assertEquals("Shail wins", tennisGame.reportScore());
		tennisGame.resetGame();
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		assertEquals("Raag wins", tennisGame.reportScore());
	}

	@Test
	public void throwExceptionIfScoredAfterGameOver() {
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		assertThrows(RuntimeException.class, () -> {
			tennisGame.playerScores("playerB");
		});
	}
	
	@Test
	public void useNamesForPlayers()
	{
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		assertEquals("Raag wins", tennisGame.reportScore());
	}
	
	@Test
	public void provideHistoryOfGame()
	{
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerA");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		tennisGame.playerScores("playerB");
		assertEquals("15-love\n" + 
				"30-love\n" + 
				"40-love\n" + 
				"40-15\n" + 
				"40-30\n" + 
				"deuce\n" + 
				"advantage Shail", tennisGame.detailedReport().toString());
	}

}
