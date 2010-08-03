//   Copyright 2010 Arnold B. Spence
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package net.psilent.psilentjacks;

import java.awt.Color;
import java.util.EventObject;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.psilent.cardgraphics.CardGraphic;
import net.psilent.cardgraphics.CardTypes;
import net.psilent.cardgraphics.CardTypes.Facing;
import net.psilent.pokerengine.Card;
import net.psilent.pokerengine.Game;
import org.jdesktop.application.Application;

public class View extends FrameView implements ActionListener, Application.ExitListener
{

    public View(SingleFrameApplication app)
    {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for(int i = 0; i < busyIcons.length; i++)
        {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {

            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                String propertyName = evt.getPropertyName();
                if("started".equals(propertyName))
                {
                    if(!busyIconTimer.isRunning())
                    {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                }
                else if("done".equals(propertyName))
                {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                }
                else if("message".equals(propertyName))
                {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                }
                else if("progress".equals(propertyName))
                {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

        this.initialize();
    }

    @Action
    public void showAboutBox()
    {
        if(aboutBox == null)
        {
            JFrame mainFrame = App.getApplication().getMainFrame();
            aboutBox = new AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        App.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        btn_deal = new javax.swing.JButton();
        label_message = new javax.swing.JLabel();
        label_money = new javax.swing.JLabel();
        cardGraphic1 = new net.psilent.cardgraphics.CardGraphic();
        cardGraphic2 = new net.psilent.cardgraphics.CardGraphic();
        cardGraphic3 = new net.psilent.cardgraphics.CardGraphic();
        cardGraphic4 = new net.psilent.cardgraphics.CardGraphic();
        cardGraphic5 = new net.psilent.cardgraphics.CardGraphic();
        checkboxMute = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(net.psilent.psilentjacks.App.class).getContext().getResourceMap(View.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setForeground(resourceMap.getColor("mainPanel.foreground")); // NOI18N
        mainPanel.setFocusable(false);
        mainPanel.setMaximumSize(new java.awt.Dimension(575, 300));
        mainPanel.setMinimumSize(new java.awt.Dimension(575, 300));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(575, 300));
        mainPanel.setLayout(new java.awt.GridBagLayout());

        btn_deal.setText(resourceMap.getString("btn_deal.text")); // NOI18N
        btn_deal.setMinimumSize(new java.awt.Dimension(100, 23));
        btn_deal.setName("btn_deal"); // NOI18N
        btn_deal.setPreferredSize(new java.awt.Dimension(100, 23));
        btn_deal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dealActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        mainPanel.add(btn_deal, gridBagConstraints);

        label_message.setFont(resourceMap.getFont("label_message.font")); // NOI18N
        label_message.setForeground(resourceMap.getColor("label_message.foreground")); // NOI18N
        label_message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_message.setText(resourceMap.getString("label_message.text")); // NOI18N
        label_message.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label_message.setName("label_message"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        mainPanel.add(label_message, gridBagConstraints);

        label_money.setFont(resourceMap.getFont("label_money.font")); // NOI18N
        label_money.setForeground(resourceMap.getColor("label_money.foreground")); // NOI18N
        label_money.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_money.setText(resourceMap.getString("label_money.text")); // NOI18N
        label_money.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        label_money.setName("label_money"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        mainPanel.add(label_money, gridBagConstraints);

        cardGraphic1.setName("cardGraphic1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        mainPanel.add(cardGraphic1, gridBagConstraints);

        cardGraphic2.setName("cardGraphic2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        mainPanel.add(cardGraphic2, gridBagConstraints);

        cardGraphic3.setName("cardGraphic3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        mainPanel.add(cardGraphic3, gridBagConstraints);

        cardGraphic4.setName("cardGraphic4"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        mainPanel.add(cardGraphic4, gridBagConstraints);

        cardGraphic5.setName("cardGraphic5"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        mainPanel.add(cardGraphic5, gridBagConstraints);

        checkboxMute.setBackground(resourceMap.getColor("checkboxMute.background")); // NOI18N
        checkboxMute.setFont(resourceMap.getFont("checkboxMute.font")); // NOI18N
        checkboxMute.setForeground(resourceMap.getColor("checkboxMute.foreground")); // NOI18N
        checkboxMute.setText(resourceMap.getString("checkboxMute.text")); // NOI18N
        checkboxMute.setFocusPainted(false);
        checkboxMute.setFocusable(false);
        checkboxMute.setName("checkboxMute"); // NOI18N
        checkboxMute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxMuteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 10, 0);
        mainPanel.add(checkboxMute, gridBagConstraints);

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(net.psilent.psilentjacks.App.class).getContext().getActionMap(View.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 486, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dealActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btn_dealActionPerformed
    {//GEN-HEADEREND:event_btn_dealActionPerformed
        if(this.game.getState() == Game.State.DEAL)
        {
            if(this.game.getBalance() == 0)
            {
                this.game.setBalance(100);
            }
            this.btn_deal.setEnabled(false);
            this.setCardGraphicsClickable(false);
            this.gameState = GameState.DEAL_HIDE_CARDS;
            this.animationTimer.start();
        }
        else
        {
            this.getDiscards();
            this.btn_deal.setEnabled(false);
            this.setCardGraphicsClickable(false);
            this.gameState = GameState.DRAW_HIDE_CARDS;
            this.animationTimer.start();
        }
    }//GEN-LAST:event_btn_dealActionPerformed

    private void checkboxMuteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_checkboxMuteActionPerformed
    {//GEN-HEADEREND:event_checkboxMuteActionPerformed
        SoundFX.setMuted(checkboxMute.isSelected());
        this.preferences.putBoolean(this.prefIdSoundMuted, this.checkboxMute.isSelected());
    }//GEN-LAST:event_checkboxMuteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_deal;
    private net.psilent.cardgraphics.CardGraphic cardGraphic1;
    private net.psilent.cardgraphics.CardGraphic cardGraphic2;
    private net.psilent.cardgraphics.CardGraphic cardGraphic3;
    private net.psilent.cardgraphics.CardGraphic cardGraphic4;
    private net.psilent.cardgraphics.CardGraphic cardGraphic5;
    private javax.swing.JCheckBox checkboxMute;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel label_message;
    private javax.swing.JLabel label_money;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;

    private Preferences preferences = Preferences.userNodeForPackage(View.class);
    private final String prefIdBalance = "balance";
    private final String prefIdSoundMuted = "sound_muted";

    private final Color GREEN = new Color(62, 214, 29);
    private final Color RED = new Color(255, 0, 0);
    private final Color BLUE = new Color(17, 150, 242);


    private enum GameState
    {

        IDLE, SHUFFLING1, SHUFFLING2, DEAL_HIDE_CARDS, DEAL_SHOW_CARDS, DRAW_HIDE_CARDS, DRAW_SHOW_CARDS
    }

    private Game game = new Game(null, null);
    private List<CardGraphic> cardGraphics = new ArrayList<CardGraphic>();
    private GameState gameState = GameState.DEAL_HIDE_CARDS;
    private int cardCounter = 0;
    private Timer animationTimer = new Timer(350, this);
    private List<Integer> discardIndexes = new ArrayList<Integer>();
    private List<Card> discards = new ArrayList<Card>();

    public boolean canExit(EventObject event)
    {
        if(this.game.getState() == Game.State.DRAW)
        {
            if(JOptionPane.showConfirmDialog(this.getFrame(), "Your current hand is not finished. Do you want to exit?", "Still playing..", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }

    public void willExit(EventObject event)
    {
        // Empty
    }

    private void initialize()
    {
        //this.getFrame().setResizable(false);
        ImageIcon imageIcon = new ImageIcon(View.class.getResource("/net/psilent/psilentjacks/resources/PsilentJacks_icon.png"));
        getFrame().setIconImage(imageIcon.getImage());

        getApplication().addExitListener(this);
        this.cardGraphics.add(this.cardGraphic1);
        this.cardGraphics.add(this.cardGraphic2);
        this.cardGraphics.add(this.cardGraphic3);
        this.cardGraphics.add(this.cardGraphic4);
        this.cardGraphics.add(this.cardGraphic5);

        for(CardGraphic card : this.cardGraphics)
        {
            card.setFacing(Facing.BACK);
        }

        int tempBalance = this.preferences.getInt(this.prefIdBalance, 0);
        if(tempBalance > 0)
        {
            this.game.setBalance(tempBalance);
            this.label_message.setForeground(this.BLUE);
            this.label_message.setText("Click 'DEAL' to continue your game.");
        }
        else
        {
            this.updateMessage();
        }

        checkboxMute.setSelected(this.preferences.getBoolean(this.prefIdSoundMuted, false));
        SoundFX.setMuted(checkboxMute.isSelected());
        
        this.updateDealButton();
        this.setCardGraphicsClickable(false);


        this.updateMoney();
    }

    private void updateDealButton()
    {
        if(this.game.getState() == Game.State.DEAL)
        {
            this.btn_deal.setText("DEAL");
        }
        else
        {
            this.btn_deal.setText("DRAW");
        }
    }

    private void updateMoney()
    {
        this.label_money.setText("$" + Integer.toString(this.game.getBalance()));
    }

    private void updateMessage()
    {
        if(this.game.getState() == Game.State.DEAL)
        {
            if(this.game.getBalance() == 0)
            {
                this.label_message.setForeground(this.BLUE);
                this.label_message.setText("GAME OVER. Click 'DEAL' to start a new game.");
            }
            else
            {
                if(this.game.getLastWinAmount() > 0)
                {
                    this.label_message.setForeground(this.GREEN);
                    this.label_message.setText(this.game.getLastWinRank().toString().replace('_', ' ') + " wins $" + Integer.toString(this.game.getLastWinAmount()));
                    SoundFX.play(SoundFX.Sound.WIN);
                }
                else
                {
                    this.label_message.setForeground(this.RED);
                    this.label_message.setText("NO WIN");
                    SoundFX.play(SoundFX.Sound.LOSE);
                }
            }
        }
        else
        {
            this.label_message.setForeground(this.BLUE);
            this.label_message.setText("Select cards to hold and click 'DRAW'.");
        }
    }

    private void clearMessage()
    {
        this.label_message.setText(" ");
    }

    private void getDiscards()
    {
        this.discards.clear();
        this.discardIndexes.clear();
        for(int i = 0; i < 5; i++)
        {
            if(!this.cardGraphics.get(i).isHeld())
            {
                this.discards.add(this.game.getPlayerCards().get(i));
                this.discardIndexes.add(i);
            }
        }
    }

    private void setCardGraphicsClickable(boolean clickable)
    {
        for(int i = 0; i < 5; i++)
        {
            this.cardGraphics.get(i).setClickable(clickable);
        }
    }

    private void highlightWinningCards()
    {
        for(int i = 0; i < 5; i++)
        {
            if(this.game.getLastWinAmount() > 0 && this.game.getLastWinHand().contains(this.game.getPlayerCards().get(i)))
            {
                this.cardGraphics.get(i).setEnabled(true);
            }
            else
            {
                this.cardGraphics.get(i).setEnabled(false);
            }
        }
    }

    public void clearHeldCards()
    {
        for(int i = 0; i < 5; i++)
        {
            this.cardGraphics.get(i).setHeld(false);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(this.gameState == GameState.DEAL_HIDE_CARDS)
        {
            if(this.cardCounter == 0)
            {
                this.clearMessage();
                this.game.deal();
                this.preferences.putInt(this.prefIdBalance, this.game.getBalance());

                this.updateMoney();
            }

            if(this.cardGraphics.get(this.cardCounter).getFacing() == Facing.BACK)
            {
                // This is the first hand following program launch, cards are already face down.
                cardCounter = 5;
            }
            else
            {
                this.cardGraphics.get(this.cardCounter).setFacing(Facing.BACK);
                this.cardGraphics.get(this.cardCounter).setHeld(false);
                this.cardGraphics.get(this.cardCounter).setEnabled(true);
                this.cardGraphics.get(this.cardCounter).setVisible(true);
                SoundFX.play(SoundFX.Sound.CARD_FLIP);
                this.cardCounter++;
            }

            if(this.cardCounter == 5)
            {
                this.cardCounter = 0;
                this.gameState = GameState.SHUFFLING1;
            }
        }
        else if(this.gameState == GameState.SHUFFLING1)
        {
            SoundFX.play(SoundFX.Sound.SHUFFLE);
            this.gameState = GameState.SHUFFLING2;
        }
        else if(this.gameState == GameState.SHUFFLING2)
        {
            this.gameState = GameState.DEAL_SHOW_CARDS;
        }

        else if(this.gameState == GameState.DEAL_SHOW_CARDS)
        {
            this.cardGraphics.get(this.cardCounter).setSuit(CardTypes.Suit.valueOf(this.game.getPlayerCards().get(this.cardCounter).getSuit().toString()));
            this.cardGraphics.get(this.cardCounter).setRank(CardTypes.Rank.valueOf(this.game.getPlayerCards().get(this.cardCounter).getRank().toString()));
            this.cardGraphics.get(this.cardCounter).setFacing(Facing.FRONT);
            SoundFX.play(SoundFX.Sound.CARD_FLIP);

            this.cardCounter++;
            if(this.cardCounter == 5)
            {
                this.cardCounter = 0;
                this.btn_deal.setText("DRAW");
                this.btn_deal.setEnabled(true);
                this.updateMessage();
                this.setCardGraphicsClickable(true);
                this.gameState = GameState.IDLE;
            }
        }
        else if(this.gameState == GameState.DRAW_HIDE_CARDS)
        {
            if(this.cardCounter == 0)
            {
                this.clearMessage();
            }

            if(this.cardCounter < this.discards.size())
            {
                int cardIndex = this.discardIndexes.get(this.cardCounter);
                this.cardGraphics.get(cardIndex).setFacing(Facing.BACK);
                this.cardGraphics.get(cardIndex).setHeld(false);
                SoundFX.play(SoundFX.Sound.CARD_FLIP);
                this.cardCounter++;
            }
            else
            {
                this.cardCounter = 0;
                this.game.draw(this.discards);
                this.preferences.putInt(this.prefIdBalance, this.game.getBalance());
                this.gameState = GameState.DRAW_SHOW_CARDS;
            }
        }
        else if(this.gameState == GameState.DRAW_SHOW_CARDS)
        {
            if(this.cardCounter < this.discards.size())
            {
                int cardIndex = discardIndexes.get(cardCounter);
                this.cardGraphics.get(cardIndex).setSuit(CardTypes.Suit.valueOf(this.game.getPlayerCards().get(cardIndex).getSuit().toString()));
                this.cardGraphics.get(cardIndex).setRank(CardTypes.Rank.valueOf(this.game.getPlayerCards().get(cardIndex).getRank().toString()));
                this.cardGraphics.get(cardIndex).setFacing(Facing.FRONT);
                SoundFX.play(SoundFX.Sound.CARD_FLIP);
                this.cardCounter++;
            }
            else
            {
                this.cardCounter = 0;
                this.clearHeldCards();
                this.highlightWinningCards();
                this.updateMessage();
                this.updateMoney();
                this.btn_deal.setText("DEAL");
                this.btn_deal.setEnabled(true);
                this.gameState = GameState.IDLE;
            }
        }
    }
}
