package windowFrames;

import core.GameData;
import core.Main;
import globals.Colors;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("all")
public class GameWindow extends JDialog
{
    private static final float FADE_IN_RATE = 0.02f;
    private static final float FADE_OUT_RATE = 0.04f;
    private static final String PROJECT_TITLE = "JOC CULTURĂ GENERALĂ";
    private static final int PROJECT_TITLE_SIZE = 28;

    private static GameWindow dialog;
    private JPanel contentPane;
    private JPanel buttonsPane;
    private JPanel questionPane;
    private JPanel answersPane;
    private JPanel exitPane;
    private JLabel questionLabel;
    private JButton buttonExit;
    private JButton buttonAnswerA;
    private JButton buttonAnswerB;
    private JButton buttonAnswerC;
    private JButton buttonAnswerD;
    private int mouseX, mouseY;
    private static Timer fadeTimer;
    private static boolean canClick;

    public GameWindow()
    {
        setContentPane(contentPane);
        Font titleFont = new Font("Arial", Font.BOLD, PROJECT_TITLE_SIZE);
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.orange, Color.orange), PROJECT_TITLE, javax.swing.border.TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, titleFont, Color.orange));
        setModalityType(ModalityType.APPLICATION_MODAL);
        getRootPane().setDefaultButton(null);

        {
            buttonAnswerA.setOpaque(false);
            buttonAnswerA.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.orange, 1, false), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            buttonAnswerA.setBorderPainted(true);
            buttonAnswerA.setFocusPainted(false);
            buttonAnswerA.setContentAreaFilled(false);
            buttonAnswerA.setForeground(Color.white);
            buttonAnswerA.setBackground(Color.black);
            buttonAnswerA.setText(" ");

            buttonAnswerB.setOpaque(false);
            buttonAnswerB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.orange, 1, false), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            buttonAnswerB.setBorderPainted(true);
            buttonAnswerB.setFocusPainted(false);
            buttonAnswerB.setContentAreaFilled(false);
            buttonAnswerB.setForeground(Color.white);
            buttonAnswerB.setBackground(Color.black);
            buttonAnswerB.setText(" ");

            buttonAnswerC.setOpaque(false);
            buttonAnswerC.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.orange, 1, false), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            buttonAnswerC.setBorderPainted(true);
            buttonAnswerC.setFocusPainted(false);
            buttonAnswerC.setContentAreaFilled(false);
            buttonAnswerC.setForeground(Color.white);
            buttonAnswerC.setBackground(Color.black);
            buttonAnswerC.setText(" ");

            buttonAnswerD.setOpaque(false);
            buttonAnswerD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.orange, 1, false), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            buttonAnswerD.setBorderPainted(true);
            buttonAnswerD.setFocusPainted(false);
            buttonAnswerD.setContentAreaFilled(false);
            buttonAnswerD.setForeground(Color.white);
            buttonAnswerD.setBackground(Color.black);
            buttonAnswerD.setText(" ");

            buttonExit.setOpaque(false);
            buttonExit.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.orange, 1, false), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            buttonExit.setBorderPainted(true);
            buttonExit.setFocusPainted(false);
            buttonExit.setContentAreaFilled(false);
            buttonExit.setForeground(Color.white);
            buttonExit.setBackground(Color.black);
        }

        contentPane.setBackground(Color.black);
        buttonsPane.setBackground(Color.black);
        answersPane.setBackground(Color.black);
        exitPane.setBackground(Color.black);
        questionPane.setBackground(new Color(10, 10, 5));
        questionPane.setBorder(BorderFactory.createLineBorder(Color.orange, 1, false));
        questionLabel.setForeground(Color.white);
        Font questionFont = new Font("Font", Font.BOLD, 18);
        questionLabel.setFont(questionFont);
        questionLabel.setText("");

        contentPane.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent me)
            {
                mouseX = me.getX();
                mouseY = me.getY();
            }
        });

        contentPane.addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                setLocation(getLocation().x + e.getX() - mouseX, getLocation().y + e.getY() - mouseY);
            }
        });

        /*
        // Button events
        */
        {
            buttonAnswerA.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    onAnswer(GameData.ANSWER_A);
                    canClick = false;
                }

                @Override
                public void mousePressed(MouseEvent e)
                {

                }

                @Override
                public void mouseReleased(MouseEvent e)
                {

                }

                @Override
                public void mouseEntered(MouseEvent e)
                {
                    buttonAnswerA.setForeground(Color.orange);
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    buttonAnswerA.setForeground(Color.white);
                }
            });

            buttonAnswerB.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    onAnswer(GameData.ANSWER_B);
                    canClick = false;
                }

                @Override
                public void mousePressed(MouseEvent e)
                {

                }

                @Override
                public void mouseReleased(MouseEvent e)
                {

                }

                @Override
                public void mouseEntered(MouseEvent e)
                {
                    buttonAnswerB.setForeground(Color.orange);
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    buttonAnswerB.setForeground(Color.white);
                }
            });

            buttonAnswerC.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    onAnswer(GameData.ANSWER_C);
                    canClick = false;
                }

                @Override
                public void mousePressed(MouseEvent e)
                {

                }

                @Override
                public void mouseReleased(MouseEvent e)
                {

                }

                @Override
                public void mouseEntered(MouseEvent e)
                {
                    buttonAnswerC.setForeground(Color.orange);
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    buttonAnswerC.setForeground(Color.white);
                }
            });

            buttonAnswerD.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    onAnswer(GameData.ANSWER_D);
                    canClick = false;
                }

                @Override
                public void mousePressed(MouseEvent e)
                {

                }

                @Override
                public void mouseReleased(MouseEvent e)
                {

                }

                @Override
                public void mouseEntered(MouseEvent e)
                {
                    buttonAnswerD.setForeground(Color.orange);
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    buttonAnswerD.setForeground(Color.white);
                }
            });

            buttonExit.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {

                }

                @Override
                public void mousePressed(MouseEvent e)
                {

                }

                @Override
                public void mouseReleased(MouseEvent e)
                {

                }

                @Override
                public void mouseEntered(MouseEvent e)
                {
                    buttonExit.setForeground(Color.orange);
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    buttonExit.setForeground(Color.white);
                }
            });

            buttonExit.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    onExit();
                }
            });

            // call onCancel() when cross is clicked
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    onExit();
                }
            });

            // call onCancel() on ESCAPE
            contentPane.registerKeyboardAction(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    onExit();
                }
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        }
    }

    private void onAnswer(int answerID)
    {
        if(!canClick) return;
        if(GameData.getGameState(GameData.STATE_PLAYING))
        {
            GameData.playAudio(GameData.SFX_BUTTON_CLICK, false, 0.85f);
            switch(answerID)
            {
                case GameData.ANSWER_A ->
                {
                    if(GameData.getCorrectAnswer(GameData.getCurrentQuestion()) == GameData.ANSWER_A)
                    {
                        GameData.setCurrentScore(GameData.getCurrentScore() + 1);
                    }
                    else GameData.setCurrentScore(GameData.getCurrentScore());
                }
                case GameData.ANSWER_B ->
                {
                    if(GameData.getCorrectAnswer(GameData.getCurrentQuestion()) == GameData.ANSWER_B)
                    {
                        GameData.setCurrentScore(GameData.getCurrentScore() + 1);
                    }
                    else GameData.setCurrentScore(GameData.getCurrentScore());
                }
                case GameData.ANSWER_C ->
                {
                    if(GameData.getCorrectAnswer(GameData.getCurrentQuestion()) == GameData.ANSWER_C)
                    {
                        GameData.setCurrentScore(GameData.getCurrentScore() + 1);
                    }
                    else GameData.setCurrentScore(GameData.getCurrentScore());
                }
                case GameData.ANSWER_D ->
                {
                    if(GameData.getCorrectAnswer(GameData.getCurrentQuestion()) == GameData.ANSWER_D)
                    {
                        GameData.setCurrentScore(GameData.getCurrentScore() + 1);
                    }
                    else GameData.setCurrentScore(GameData.getCurrentScore());
                }
                default ->
                {

                }
            }
            GameData.setCurrentRound(GameData.getCurrentRound() + 1);
            clearButtonLabels();
            if(GameData.getGameState(GameData.STATE_END_GAME))
            {
                showEndGameResult();
            }
            else Main.startGame(false);
        }
    }

    private void onExit()
    {
        if(GameData.getGameState(GameData.STATE_INTRO) || GameData.getGameState(GameData.STATE_EXIT)) return;
        GameData.stopAllAudio();
//        GameData.playAudio(GameData.SFX_OUTRO, false, 0.9f);
        fadeOutWindow(dialog, 20);
    }

    private static void clearButtonLabels()
    {
        dialog.buttonAnswerA.setText(" ");
        dialog.buttonAnswerB.setText(" ");
        dialog.buttonAnswerC.setText(" ");
        dialog.buttonAnswerD.setText(" ");
    }

    private static String formatLabelText(String s)
    {
        return ("<html><div style='text-align: center;'>" + s.replaceAll("<","&lt;").
                replaceAll(">", "&gt;").replaceAll("\n", "<br/>").
                replaceAll("col=o", "<font color=ffc800>").
                replaceAll("col=/", "</font>") + "</div></html>");
    }

    public static GameWindow createGameWindow(int width, int height)
    {
        GameData.setGameState(GameData.STATE_INTRO);

        dialog = new GameWindow();
        dialog.setUndecorated(true);
        dialog.setSize(width, height);
        dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dialog.getWidth() / 2, (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dialog.getHeight() / 2);
        dialog.getRootPane().setOpaque(false);
        dialog.setOpacity(0.0f);
        fadeInWindow(dialog, 20);
//        GameData.playAudio(GameData.SFX_INTRO, false, 0.9f);
//        GameData.playAudio(GameData.SFX_MUSIC, true, 0.8f);
        dialog.setVisible(true);

        return dialog;
    }

    public void showEndGameResult()
    {
        if(GameData.getGameState(GameData.STATE_END_GAME))
        {
            GameData.playAudio(GameData.SFX_END, false, 0.9f);
            Timer timer = new Timer(1000, null);
            timer.addActionListener(new ActionListener()
            {
                int idx = 0;

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    questionLabel.setText(formatLabelText("\n\n\n\n\n" + "Ai răspuns corect la col=o" + GameData.getCurrentScore() + "col=/ din col=o" + GameData.MAX_ROUNDS + "col=/ întrebări!" + "\nO nouă rundă începe în col=o" + (GameData.NEW_GAME_TIMER - idx) + "col=/ secunde."));
                    if(idx % 2 == 1) GameData.playAudio(GameData.SFX_TIMER_1, false, 0.8f);
                    else GameData.playAudio(GameData.SFX_TIMER_2, false, 0.8f);
                    idx++;
                    if(idx > GameData.NEW_GAME_TIMER)
                    {
                        timer.stop();
                        Main.startGame(true);
                    }
                }
            });
            timer.start();
        }
    }

    public static void animateQuestionLabel(String question, int interval)
    {
        Timer timer = new Timer(interval, null);
        timer.addActionListener(new ActionListener()
        {
            int idx = 0;
            String strAnim = "";

            @Override
            public void actionPerformed(ActionEvent e)
            {
                strAnim = strAnim + String.valueOf(question.charAt(idx));
                dialog.questionLabel.setText(formatLabelText("\n\n\n\n\n"  + strAnim));
                GameData.playAudio(GameData.SFX_KEY, false, 0.8f);
                idx++;
                if(idx >= question.length())
                {
                    timer.stop();
                    animateAnswerLabel(question, 1000);
                }
            }
        });
        timer.start();
    }

    private static void animateAnswerLabel(String question, int interval)
    {
        Timer timer = new Timer(interval, null);
        timer.addActionListener(new ActionListener()
        {
            int idx = 1;
            int questionID;
            String answer;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                GameData.playAudio(GameData.SFX_KEY, false, 0.8f);
                questionID = GameData.getQuestionsAndAnswersList().indexOf(question) + idx;
                answer = "" + GameData.getQuestionsAndAnswersList().get(questionID);
                if(answer.contains("+"))
                {
                    answer = answer.replace("+", "");
                }
                switch(idx)
                {
                    case GameData.ANSWER_A ->
                    {
                        dialog.buttonAnswerA.setText(answer);
                    }
                    case GameData.ANSWER_B ->
                    {
                        dialog.buttonAnswerB.setText(answer);
                    }
                    case GameData.ANSWER_C ->
                    {
                        dialog.buttonAnswerC.setText(answer);
                    }
                    case GameData.ANSWER_D ->
                    {
                        dialog.buttonAnswerD.setText(answer);
                    }
                }
                idx++;
                if(idx > 4)
                {
                    timer.stop();
                    GameData.setGameState(GameData.STATE_PLAYING);
                    canClick = true;
                }
            }
        });
        timer.start();
    }

    private static void fadeInWindow(final Dialog win, int interval)
    {
        if(!win.isUndecorated()) return;

        fadeTimer = new Timer(interval, null);
        fadeTimer.setRepeats(true);
        fadeTimer.addActionListener(new ActionListener()
        {
            private float currentOpacity = 0.0f;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentOpacity += FADE_IN_RATE;
                win.setOpacity(Math.min(currentOpacity, 1.0f));
                if(currentOpacity >= 0.9)
                {
                    fadeTimer.stop();
                    Main.startGame(true);
                }
            }
        });
        fadeTimer.start();
    }

    private static void fadeOutWindow(final Dialog win, int interval)
    {
        if(!win.isUndecorated()) return;
        if(fadeTimer.isRunning()) return;

        fadeTimer = new Timer(interval, null);
        fadeTimer.setRepeats(true);
        fadeTimer.addActionListener(new ActionListener()
        {
            private float currentOpacity = win.getOpacity();

            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentOpacity -= FADE_OUT_RATE;
                win.setOpacity(Math.min(currentOpacity, 1.0f));
                if(currentOpacity <= FADE_OUT_RATE)
                {
                    fadeTimer.stop();
                    win.dispose();
                    System.exit(0);
                }
            }
        });
        fadeTimer.start();
        GameData.setGameState(GameData.STATE_EXIT);
    }
}
