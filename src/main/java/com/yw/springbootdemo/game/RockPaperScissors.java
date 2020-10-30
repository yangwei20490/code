package com.yw.springbootdemo.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yangwei
 * @date 2020-06-23 14:33
 */
public class RockPaperScissors extends JFrame {

    protected final JLabel messageLabel = new JLabel(INITIAL_MESSAGE);
    private final GameProcessor gameProcessor = new GameProcessor();
    private final List<JButton> buttons = new ArrayList<>();
    protected JPanel buttonsPanel;
    public static final String INITIAL_MESSAGE = "我准备好了，出招吧 XD";

    public RockPaperScissors() throws HeadlessException {
        initFrame();
        initControls();
        restartGame();
    }

    /**
     * 设置窗体
     */
    private void initFrame() {
        this.setSize(300, 120);
        this.setLocation(300, 300);
        this.setTitle("野球拳");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * 设置控件
     */
    private void initControls() {
        BorderLayout bl = new BorderLayout();
        bl.setVgap(5);
        getContentPane().setLayout(bl);

        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(messageLabel, BorderLayout.NORTH);

        buttonsPanel = new JPanel(new GridLayout(1, GameItem.values().length, 5, 0));
        getContentPane().add(buttonsPanel);

        createButtons();
        }

    /**
     * 创建按钮
     */
    private void createButtons() {
        for (GameItem item : GameItem.values()) {
            ItemButton button = new ItemButton(item);
            buttons.add(button);
            buttonsPanel.add(button);
        }
    }

    /**
     * 启用或禁用全部按钮
     * @param enabled 启用或禁用
     */
    private void setButtonsEnabled(boolean enabled) {
        for (JButton button : buttons) {
            button.setEnabled(enabled);
        }
    }

    /**
     * 处理并显示游戏结果
     * @param gameItem 玩家选择
     */
     private void processAndShowGameResult(GameItem gameItem) {
         String result = gameProcessor.process(gameItem);
         messageLabel.setText(result);
     }

    /**
     * 重置游戏界面
     */
    private void restartGame() {
         messageLabel.setText(INITIAL_MESSAGE);
         gameProcessor.reset();
     }

    /**
     * 游戏逻辑处理
      */
    private static class GameProcessor {

         private GameItem item;
         private Random r = new Random();

         /** 
          * 重置游戏，机器生成自己的选项 
          */
         public void reset() {
             int index = r.nextInt(GameItem.values().length);
             this.item = GameItem.values()[index];
         }

        /**
         * 处理玩家输入的选项，得出本局结果 
         * 
         * @param gameItem 玩家选项 
         * 
         * @return 本局结果 
         */
        public String process(GameItem gameItem) {
            int result = item.compareWith(gameItem);
            if (result > 0) {
                return "哈哈，你输了，我出的是" + item.getLabel();
            } else if (result < 0) {
                return "啊，我出了个" + item.getLabel() + "，不小心让你赢了一次……";
            } else {
                return "嘿，我出的也是" + item.getLabel() + "，打个平手！";
            }
        }
    }

    /**
     * 用户选择按钮，每个 ItemButton 对应一个 GameItem
     */
    private class ItemButton extends JButton {

        private GameItem item;

        public GameItem getGameItem() {
            return item;
        }

        private ItemButton(String text) {
            super(text);
        }

        private ItemButton(GameItem item) {
            this(item.getLabel());
            this.item = item;

            this.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    new Thread() {

                        @Override
                        public void run() {
                            ItemButton button = (ItemButton) e.getSource();

                            processAndShowGameResult(button.getGameItem());
                            sleepForAWhile();
                            restartGame();
                        }
                    }.start();
                }

                private void sleepForAWhile() {
                    setButtonsEnabled(false);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    setButtonsEnabled(true);
                }
            });
        }
    }

        /////////////////////////////////////////////////////////  

    private enum GameItem {

        Rock("石头"), Scissor("剪刀"), Paper("布");

        private String label;

        public String getLabel() {
            return label;
        }

        private GameItem(String label) {
            this.label = label;
        }

        public int compareWith(GameItem item) {
            if (this == item) {
                return 0;
            } else if ((this == Rock && item == Scissor) ||
                    (this == Scissor && item == Paper) ||
                    (this == Paper && item == Rock)) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new RockPaperScissors().setVisible(true);
    }
}
