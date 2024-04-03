package core;

import globals.Colors;

import javax.sound.sampled.*;
//import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class GameData
{
    public static final boolean DEBUG = true;

    public static final String PATH_QUESTIONS = "GameData/Questions.txt";

    public static final String SFX_INTRO = "GameData/Audio/sfx_intro.wav";
    public static final String SFX_OUTRO = "GameData/Audio/sfx_outro.wav";
    public static final String SFX_BUTTON_CLICK = "GameData/Audio/sfx_button_click.wav";
    public static final String SFX_KEY = "GameData/Audio/sfx_key.wav";
    public static final String SFX_TIMER_1 = "GameData/Audio/sfx_timer_1.wav";
    public static final String SFX_TIMER_2 = "GameData/Audio/sfx_timer_2.wav";
    public static final String SFX_MUSIC = "GameData/Audio/sfx_music.wav";
    public static final String SFX_END = "GameData/Audio/sfx_end.wav";

    public static final int STATE_EXIT = -1;
    public static final int STATE_INTRO = 0;
    public static final int STATE_PLAYING = 1;
    public static final int STATE_END_GAME = 2;

    public static final int ANSWER_A = 1;
    public static final int ANSWER_B = 2;
    public static final int ANSWER_C = 3;
    public static final int ANSWER_D = 4;

    public static final int MAX_ROUNDS = 5;
    public static final int NEW_GAME_TIMER = 20;

    private static List<Clip> audioList = new ArrayList<>();
    private static List<String> questionsAndAnswersList = new ArrayList<>();
    private static List<String> questionsList = new ArrayList<>();
    private static List<String> repeatingList = new ArrayList<>();

    private static int gameState;
    private static String currentQuestion;
    private static int currentRound;
    private static int currentScore;

    public static List getQuestionsAndAnswersList()
    {
        return questionsAndAnswersList;
    }

    public static boolean setGameState(int newGameState)
    {
        if(newGameState < STATE_EXIT || newGameState > STATE_END_GAME) return false;
        gameState = newGameState;
        return true;
    }

    public static boolean getGameState(int state)
    {
        if(gameState == state) return true;
        return false;
    }

    public static String getCurrentQuestion()
    {
        return currentQuestion;
    }

    public static int setCurrentRound(int newRound)
    {
        if(newRound > MAX_ROUNDS)
        {
            if(DEBUG)
            {
                System.out.println(Colors.RESET + "\n+---------------+");
                System.out.println(Colors.RESET + "|  Game ended!  |");
                System.out.println(Colors.RESET + "|_______________|");
                System.out.println(Colors.RESET + "|   Score: " + Colors.YELLOW + getCurrentScore() + Colors.RESET + "/" + Colors.YELLOW + MAX_ROUNDS + Colors.RESET + "  |");
                System.out.println(Colors.RESET + "+---------------+");
            }
            setGameState(STATE_END_GAME);
        }
        else
        {
            if(DEBUG) System.out.println(Colors.RESET + "Current round: " + Colors.YELLOW + newRound + Colors.RESET + "/" + Colors.YELLOW + MAX_ROUNDS);
        }
        return currentRound = newRound;
    }

    public static int getCurrentRound()
    {
        return currentRound;
    }

    public static int setCurrentScore(int newScore)
    {
        if(DEBUG)
        {
            if(newScore == (currentScore + 1))
            {
                System.out.println(Colors.RESET + "\n--Answer was " + Colors.GREEN_BOLD + "correct\n" + Colors.RESET + "Current score: " + Colors.YELLOW + newScore);
            }
            else System.out.println(Colors.RESET + "\n--Answer was " + Colors.RED + "incorrect\n" + Colors.RESET + "Current score: " + Colors.YELLOW + newScore);
        }
        return currentScore = newScore;
    }

    public static int getCurrentScore()
    {
        return currentScore;
    }

    public static void resetRoundData()
    {
        currentRound = 1;
        currentScore = 0;
        if(DEBUG)
        {
            System.out.println(Colors.RESET + "\nCurrent score: " + Colors.YELLOW + currentScore);
            System.out.println(Colors.RESET + "Current round: " + Colors.YELLOW + currentRound + Colors.RESET + "/" + Colors.YELLOW + MAX_ROUNDS);
        }
    }

    public static void loadQuestionsFromFile(String txtFile)
    {
        try
        {
            Path filePath = Paths.get(txtFile);
            questionsAndAnswersList.addAll(Files.readAllLines(filePath));

            for(int i = 0; i < questionsAndAnswersList.size(); i++)
            {
                if(questionsAndAnswersList.get(i).contains("?")) questionsList.add(questionsAndAnswersList.get(i));
            }
        }
        catch(Exception e)
        {
            System.out.println(Colors.RED + "Failed to read from file: " + Colors.RESET + e.getMessage());
        }
    }

    public static String getRandomQuestion()
    {
        Random rand = new Random();
        currentQuestion = questionsList.get(rand.nextInt(questionsList.size()));
        return currentQuestion;
    }

    public static int getCorrectAnswer(String question)
    {
        int questionID = questionsAndAnswersList.indexOf(question);

        if(questionsAndAnswersList.get(questionID + ANSWER_A).contains("+")) return ANSWER_A;
        else if(questionsAndAnswersList.get(questionID + ANSWER_B).contains("+")) return ANSWER_B;
        else if(questionsAndAnswersList.get(questionID + ANSWER_C).contains("+")) return ANSWER_C;
        else if(questionsAndAnswersList.get(questionID + ANSWER_D).contains("+")) return ANSWER_D;
        return -1;
    }

    public static void addRepeatingQuestion(String question)
    {
        repeatingList.add(question);
    }

    public static boolean isRepeatingQuestion(String question)
    {
        if(repeatingList.contains(question)) return true;
        return false;
    }

    public static void clearRepeatingList()
    {
        repeatingList.clear();
    }

    public static void playAudio(String soundFile, boolean loop, float volume)
    {
        try
        {
            if(volume < 0.0f || volume > 1.0f) throw new IllegalArgumentException("Volume not valid: " + volume);

            File f = new File(soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(event ->
            {
                if(event.getType() == LineEvent.Type.STOP)
                {
                    clip.close();
                    audioList.remove(clip);
                }
            });
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
            if(loop) clip.loop(-1);
            else clip.start();
            audioList.add(clip);
        }
        catch(Exception e)
        {
            System.out.println(Colors.RED + "Failed to play sound: " + Colors.RESET + e.getMessage());
        }
    }

    public static void stopAllAudio()
    {
        for(int i = 0; i < audioList.size(); i++)
        {
            if(audioList.get(i) != null && audioList.get(i).isRunning())
            {
                audioList.get(i).stop();
                audioList.get(i).close();
            }
        }

//        for(Clip sfx : audioList) // sometimes random Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
//        {
//            if(sfx.isRunning())
//            {
//                sfx.stop();
//                sfx.close();
//            }
//        }
    }
}
