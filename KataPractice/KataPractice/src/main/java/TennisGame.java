package main.java;

import java.util.ArrayList;
import java.util.List;

class GameOverException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	GameOverException(String gameOver) {
		super(gameOver);
	}
}

public class TennisGame {

	String playerAScore, playerBScore, playerA = "A", playerB = "B";
	boolean gameOver = false;
	List<String> liveScore = new ArrayList<String>();

	public StringBuilder detailedReport() {
		StringBuilder scoreBoard = new StringBuilder();
		for (int i = 0; i < liveScore.size(); i++) {
			scoreBoard.append(liveScore.get(i));
			//no need of a new line after the last entry
			if (i != liveScore.size() - 1) {
				scoreBoard.append("\n");
			}
		}
		return scoreBoard;
	}

	public void resetGame() {
		playerAScore = "love";
		playerBScore = "love";
		gameOver = false;
	}

	public void setPlayerNames(String playerA, String playerB) {
		this.playerA = playerA;
		this.playerB = playerB;
	}

	public String reportScore() {
		if (playerAScore.equals("game point")) {
			return playerA + " wins";
		} else if (playerBScore.equals("game point")) {
			return playerB + " wins";
		} else if (playerAScore.equals("deuce")) {
			return "deuce";
		} else if (playerAScore.equals("advantage")) {
			return "advantage " + playerA;
		} else if (playerBScore.equals("advantage")) {
			return "advantage " + playerB;
		} else
			return playerAScore + "-" + playerBScore;
	}

	public void playerScores(String player) {
		// Trying to advance the score after game is over
		if (gameOver) {
			throw new GameOverException("Game is over");
		}

		List<String> score = new ArrayList<String>();
		score.add("love");
		score.add("15");
		score.add("30");
		score.add("40");

		if (player.equals("playerA")) {
			playerAScores(score);
		}

		if (player.equals("playerB")) {
			playerBScores(score);
		}

		liveScore.add(reportScore());
	}

	private void playerAScores(List<String> score) {
		if (playerAScore.equals("advantage")) {
			playerAScore = "game point";
			gameOver = true;
		}

		// Player A scores after deuce to get advantage
		if (playerAScore.equals("deuce")) {
			playerAScore = "advantage";
			playerBScore = "40";
		}

		if (playerAScore.equals("40")) {
			// Player A scores while B has the advantage, the score returns to deuce
			if (playerBScore.equals("advantage")) {
				playerBScore = "deuce";
				playerAScore = "deuce";
			} else {
				// Player A wins if they score after advantage
				playerAScore = "game point";
				gameOver = true;
			}
		}

		for (int i = 0; i < score.size() - 1; i++) {
			if (playerAScore.equals(score.get(i))) {
				playerAScore = score.get(i + 1);
				if (playerBScore.equals("40") && playerAScore.equals("40")) {
					playerAScore = "deuce";
					playerBScore = "deuce";
				}
				break;
			}
		}
	}

	private void playerBScores(List<String> score) {
		if (playerBScore.equals("advantage")) {
			playerBScore = "game point";
			gameOver = true;
		}

		if (playerBScore.equals("deuce")) {
			playerBScore = "advantage";
			playerAScore = "40";
		}

		if (playerBScore.equals("40")) {
			if (playerAScore.equals("advantage")) {
				playerBScore = "deuce";
				playerAScore = "deuce";
			} else {
				playerBScore = "game point";
				gameOver = true;
			}
		}

		for (int i = 0; i < score.size() - 1; i++) {
			if (playerBScore.equals(score.get(i))) {
				playerBScore = score.get(i + 1);
				if (playerAScore.equals("40") && playerBScore.equals("40")) {
					playerAScore = "deuce";
					playerBScore = "deuce";
				}
				break;
			}
		}
	}

//	public static void main(String[] args) {
//		TennisGame tennisGame = new TennisGame();
//		tennisGame.resetGame();
//		tennisGame.setPlayerNames("Raag", "Shail");
//		tennisGame.playerScores("playerA");
//		tennisGame.playerScores("playerA");
//		tennisGame.playerScores("playerA");
//		tennisGame.playerScores("playerB");
//		tennisGame.playerScores("playerB");
//		tennisGame.playerScores("playerB");
//		tennisGame.playerScores("playerB");
//		String report = tennisGame.detailedReport().toString();
//		System.out.println(report);
//	}

}
