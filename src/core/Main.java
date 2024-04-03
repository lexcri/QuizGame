package core;

import windowFrames.GameWindow;

@SuppressWarnings("all")
/*Joc cultură generală:
se citesc dintr-un fișier mai multe seturi de întrebări și răspunsuri, iar răspunsul corect va avea un plus (+) la sfârșit.
Se afișează 5 întrebări aleatorii din set împreună cu răspunsurile lor, iar jucătorul poate alege pentru fiecare un singur răspuns corect.
Dacă răspunde corect, primește un punct. La sfârșit se afișează punctajul.
*/

public class Main
{
    private static GameWindow mainWindow;

    private static void initGame()
    {
        GameData.loadQuestionsFromFile(GameData.PATH_QUESTIONS);
        mainWindow = GameWindow.createGameWindow(747, 420);
    }

    public static void startGame(boolean newGame)
    {
        GameData.setGameState(GameData.STATE_INTRO);
        String question = GameData.getRandomQuestion();
        if(newGame)
        {
            GameData.resetRoundData();
            GameData.clearRepeatingList();
            if(GameData.isRepeatingQuestion(question))
            {
                startGame(true);
                return;
            }
            else
            {
                mainWindow.animateQuestionLabel(question, 80);
                GameData.addRepeatingQuestion(question);
            }
        }
        else
        {
            if(GameData.isRepeatingQuestion(question))
            {
                startGame(false);
                return;
            }
            else
            {
                mainWindow.animateQuestionLabel(question, 80);
                GameData.addRepeatingQuestion(question);
            }
        }
    }

    public static void main(String[] args)
    {
        initGame();
    }
}
