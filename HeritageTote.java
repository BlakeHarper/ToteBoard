//Heritage Place Tote Board
//written by Blake Harper

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.border.TitledBorder;

public class HeritageTote {
    public static void main(String[] args) {
    	new HorseFrame();
    }
}

class HorseFrame extends JFrame {
	private HorseModel model;
    private HorsePanel panel;
    
    public HorseFrame() {
    	model = new HorseModel();
        panel = new HorsePanel(this);
		setSize(model.getScreenWidth(), model.getScreenHeight());
		setTitle("Heritage Place Tote");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
    }

    public HorseModel getModel() {
		return model;
    }
}

class HorsePanel extends JPanel {

    private HorseFrame frame;
    private HorseModel model;
    private JMenuItem twentyfiveSixty;
    private JMenuItem nineteenTwenty;
    private JMenuItem twelveEighty;
    private JLabel soldLabel;
    private JLabel horseNumber;
    private JTextField hipBox;
    private JButton hipUpdate;
    private JButton next;
    private JButton prev;
    private JButton sold;
    private JTextField miscBox;
    private JButton miscUpdate;
    private JButton clearMisc;
    private JLabel bidAmount;
    private JRadioButton open;
    private JRadioButton inFoal;
    private JRadioButton colt;
    private JRadioButton filly;
    private JRadioButton gelding;
    private JRadioButton cribber;
    private JRadioButton cryptorchid;
    private JLabel selectedAnomaly;
    private JButton clearAnomalies;
    private ButtonGroup anomalies;
    private JButton subZeroth;
    private JButton subFirst;
    private JButton subSecond;
    private JButton subThird;
    private JButton subFourth;
    private JButton subFifth;
    private JButton subSixth;
    private JButton subSeventh;
    private JButton subEighth;
    private JButton subNinth;
    private JButton subTenth;
    private JButton addZeroth;
    private JButton addFirst;
    private JButton addSecond;
    private JButton addThird;
    private JButton addFourth;
    private JButton addFifth;
    private JButton addSixth;
    private JButton addSeventh;
    private JButton addEighth;
    private JButton addNinth;
    private JButton addTenth;
    private JButton reset;
    private JLabel plus;
    private JLabel minus;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
    private Color lightGreen = new Color(153,255,173);
    private Color lightRed = new Color(255,170,170);
    private Color lightBlue = new Color(127,170,198);
    private GridBagConstraints gbc = new GridBagConstraints();
    private boolean soldSelected = false;
    private Font titleFonts;
    private TitledBorder anomalyBorder;
    private TitledBorder hipBorder;
    private TitledBorder miscBorder;
    private TitledBorder toteBorder;
    private JLabel miscLabel;
    
    public HorsePanel(HorseFrame f) {
        frame = f;
        model = frame.getModel();
        setLayout(new BorderLayout());
        currencyFormat.setMaximumFractionDigits(0);
        titleFonts = new Font(null, Font.BOLD, model.getTitleSize());

        JPanel topPanel = new JPanel();
            topPanel.setLayout(new BorderLayout());
            topPanel.setBackground(lightBlue);

            JPanel resetAllPadding = new JPanel();
                resetAllPadding.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 50));
                resetAllPadding.setBackground(lightBlue);
                JButton resetAll = new JButton("RESET ALL");
                resetAll.setVerticalAlignment(JButton.CENTER);
                resetAll.addActionListener(new resetAllClicked());
                resetAllPadding.add(resetAll);

            JPanel filePadding = new JPanel();
                filePadding.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 0));
                filePadding.setBackground(lightBlue);
                JMenuBar menuBar = new JMenuBar();
                    JMenu file = new JMenu(" File ");
                        JMenuItem changeDirectory = new JMenuItem("Change Directory");
                        JMenu resolution = new JMenu("Resolution");
                            twentyfiveSixty = new JMenuItem("2560x1440");
                            nineteenTwenty = new JMenuItem("1920x1080");
                            twelveEighty = new JMenuItem("1280x720");
                            twentyfiveSixty.addActionListener(new resolutionChanged());
                            nineteenTwenty.addActionListener(new resolutionChanged());
                            twelveEighty.addActionListener(new resolutionChanged());
                            resolution.add(twentyfiveSixty);
                            resolution.add(nineteenTwenty);
                            resolution.add(twelveEighty);
                        JMenuItem about = new JMenuItem("About");
                            about.addActionListener(new aboutClicked());
                        JMenuItem exit = new JMenuItem("Exit");
                            exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
                            exit.addActionListener(new exitClicked());
                        changeDirectory.addActionListener(new changeDirectoryClicked());
                        file.add(changeDirectory);
                        file.add(resolution);
                        file.addSeparator();
                        file.add(about);
                        file.addSeparator();
                        file.add(exit);
                    menuBar.add(file);
                filePadding.add(menuBar);
            topPanel.add(resetAllPadding, BorderLayout.CENTER);
            topPanel.add(filePadding, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new GridLayout(1, 3));

            JPanel leftPanel = new JPanel();
                anomalyBorder = BorderFactory.createTitledBorder(null, "Anomalies", TitledBorder.CENTER, TitledBorder.TOP, titleFonts);
                leftPanel.setBorder(anomalyBorder);
                leftPanel.setLayout(new GridBagLayout());

                JPanel leftPanelContainer = new JPanel();
                    leftPanelContainer.setLayout(new BoxLayout(leftPanelContainer, BoxLayout.Y_AXIS));

                    JPanel selectedAnomalyContainer = new JPanel();
                            selectedAnomaly = new JLabel(" ");
                            selectedAnomaly.setFont(selectedAnomaly.getFont().deriveFont(model.getNumberFontSize()));
                            selectedAnomalyContainer.add(selectedAnomaly);

                    JPanel anomalyPanelContainer = new JPanel();

                        JPanel anomalyPanel = new JPanel();
                            anomalyPanel.setLayout(new GridLayout(8, 1));
                            open = new JRadioButton("OPEN");
                            inFoal = new JRadioButton("IN FOAL");
                            colt = new JRadioButton("COLT");
                            filly = new JRadioButton("FILLY");
                            gelding = new JRadioButton("GELDING");
                            cribber = new JRadioButton("CRIBBER");
                            cryptorchid = new JRadioButton("CRYPTORCHID");
                            anomalies = new ButtonGroup();
                            anomalies.add(open);
                            anomalies.add(inFoal);
                            anomalies.add(colt);
                            anomalies.add(filly);
                            anomalies.add(gelding);
                            anomalies.add(cribber);
                            anomalies.add(cryptorchid);
                            clearAnomalies = new JButton("CLEAR");
                            clearAnomalies.addActionListener(new anomalySelected());
                            open.addActionListener(new anomalySelected());
                            inFoal.addActionListener(new anomalySelected());
                            colt.addActionListener(new anomalySelected());
                            filly.addActionListener(new anomalySelected());
                            gelding.addActionListener(new anomalySelected());
                            cribber.addActionListener(new anomalySelected());
                            cryptorchid.addActionListener(new anomalySelected());
                            clearAnomalies.setFont(clearAnomalies.getFont().deriveFont(model.getButtonFontSize()));
                            open.setFont(open.getFont().deriveFont(model.getButtonFontSize()));
                            inFoal.setFont(inFoal.getFont().deriveFont(model.getButtonFontSize()));
                            colt.setFont(colt.getFont().deriveFont(model.getButtonFontSize()));
                            filly.setFont(filly.getFont().deriveFont(model.getButtonFontSize()));
                            gelding.setFont(gelding.getFont().deriveFont(model.getButtonFontSize()));
                            cribber.setFont(cribber.getFont().deriveFont(model.getButtonFontSize()));
                            cryptorchid.setFont(cryptorchid.getFont().deriveFont(model.getButtonFontSize()));
                            anomalyPanel.add(open);
                            anomalyPanel.add(inFoal);
                            anomalyPanel.add(colt);
                            anomalyPanel.add(filly);
                            anomalyPanel.add(gelding);
                            anomalyPanel.add(cribber);
                            anomalyPanel.add(cryptorchid);
                            anomalyPanel.add(clearAnomalies);

                        anomalyPanelContainer.add(anomalyPanel);

                    leftPanelContainer.add(selectedAnomalyContainer);
                    leftPanelContainer.add(anomalyPanelContainer);

                leftPanel.add(leftPanelContainer, gbc);

            JPanel middlePanel = new JPanel();

                JPanel hipPanel = new JPanel();
                    hipBorder = BorderFactory.createTitledBorder(null, "HIP", TitledBorder.CENTER, TitledBorder.TOP, titleFonts);
                    hipPanel.setBorder(hipBorder);
                    hipPanel.setLayout(new GridBagLayout());

                    JPanel hipPanelContainer = new JPanel();
                        hipPanelContainer.setLayout(new BoxLayout(hipPanelContainer, BoxLayout.Y_AXIS));

                        JPanel soldLabelContainer = new JPanel();
                            soldLabel = new JLabel(" ");
                            soldLabel.setForeground(Color.RED);
                            soldLabel.setFont(soldLabel.getFont().deriveFont(model.getNumberFontSize()));
                            soldLabelContainer.add(soldLabel);

                        JPanel horseNumberContainer = new JPanel();
                            horseNumber = new JLabel("#" + Integer.toString(model.getNumber()));
                            horseNumber.setFont(horseNumber.getFont().deriveFont(model.getNumberFontSize()));
                            horseNumberContainer.add(horseNumber);

                        JPanel hipBoxContainer = new JPanel();
                            hipBox = new JTextField(Integer.toString(model.getNumber()), 10);
                            hipBox.addActionListener(new hipEntered());
                            hipBox.setFont(hipBox.getFont().deriveFont(model.getTextBoxFontSize()));
                            hipBox.getInputMap().put(KeyStroke.getKeyStroke("S"), "doNothing");
                            hipBox.getActionMap().put("doNothing", new doNothing());
                            hipBoxContainer.add(hipBox);

                        JPanel hipButtonContainer = new JPanel();
                            next = new JButton("NEXT");
                            prev = new JButton("PREV");
                            next.addActionListener(new hipEntered());
                            prev.addActionListener(new hipEntered());
                            next.setFont(next.getFont().deriveFont(model.getButtonFontSize()));
                            prev.setFont(prev.getFont().deriveFont(model.getButtonFontSize()));
                            hipButtonContainer.add(prev);
                            hipButtonContainer.add(next);

                        JPanel soldContainer = new JPanel();
                            sold = new JButton("SOLD");
                            sold.addActionListener(new soldTicked());
                            sold.setFont(sold.getFont().deriveFont(model.getButtonFontSize()));
                            sold.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "setSold");
                            sold.getActionMap().put("setSold", new soldKeyStroke());
                            soldContainer.add(sold);

                        JPanel hipUpdateContainer = new JPanel();
                            hipUpdate = new JButton("UPDATE");
                            hipUpdate.addActionListener(new hipEntered());
                            hipUpdate.setFont(hipUpdate.getFont().deriveFont(model.getButtonFontSize()));
                            hipUpdateContainer.add(hipUpdate);

                        hipPanelContainer.add(soldLabelContainer);
                        hipPanelContainer.add(horseNumberContainer);
                        hipPanelContainer.add(hipBoxContainer);
                        hipPanelContainer.add(hipButtonContainer);
                        hipPanelContainer.add(hipUpdateContainer);
                        hipPanelContainer.add(soldContainer);

                    hipPanel.add(hipPanelContainer, gbc);

                JPanel miscPanel = new JPanel();
                    miscBorder = BorderFactory.createTitledBorder(null, "Miscellaneous", TitledBorder.CENTER, TitledBorder.TOP, titleFonts);
                    miscPanel.setLayout(new GridBagLayout());
                    miscPanel.setBorder(miscBorder);

                    JPanel miscPanelContainer = new JPanel();
                        miscPanelContainer.setLayout(new BoxLayout(miscPanelContainer, BoxLayout.Y_AXIS));

                        JPanel miscLabelContainer = new JPanel();
                            miscLabel = new JLabel(" ");
                            miscLabel.setFont(miscLabel.getFont().deriveFont(model.getNumberFontSize()));
                            miscLabelContainer.add(miscLabel);

                        JPanel miscBoxContainer = new JPanel();
                            miscBox = new JTextField(10);
                            miscBox.addActionListener(new miscEntered());
                            miscBox.setFont(miscBox.getFont().deriveFont(model.getTextBoxFontSize()));
                            miscBox.getInputMap().put(KeyStroke.getKeyStroke("S"), "doNothing");
                            miscBox.getActionMap().put("doNothing", new doNothing());
                            miscBoxContainer.add(miscBox);

                        JPanel miscButtonContainer = new JPanel();
                            miscUpdate = new JButton("UPDATE");
                            clearMisc = new JButton("CLEAR");
                            miscUpdate.addActionListener(new miscEntered());
                            clearMisc.addActionListener(new miscEntered());
                            clearMisc.setFont(clearMisc.getFont().deriveFont(model.getButtonFontSize()));
                            miscUpdate.setFont(miscUpdate.getFont().deriveFont(model.getButtonFontSize()));
                            miscButtonContainer.add(miscUpdate);
                            miscButtonContainer.add(clearMisc);

                        miscPanelContainer.add(miscLabelContainer);
                        miscPanelContainer.add(miscBoxContainer);
                        miscPanelContainer.add(miscButtonContainer);

                    miscPanel.add(miscPanelContainer, gbc);

                middlePanel.setLayout(new GridLayout(2, 1));
                middlePanel.add(hipPanel);
                middlePanel.add(miscPanel);

            JPanel rightPanel = new JPanel();
                toteBorder = BorderFactory.createTitledBorder(null, "Tote Board", TitledBorder.CENTER, TitledBorder.TOP, titleFonts);
                rightPanel.setLayout(new GridBagLayout());
                rightPanel.setBorder(toteBorder);

                JPanel rightPanelContainer = new JPanel();
                    rightPanelContainer.setLayout(new BoxLayout(rightPanelContainer, BoxLayout.Y_AXIS));

                    JPanel bidAmountContainer = new JPanel();
                        bidAmount = new JLabel(currencyFormat.format(model.getBidNumber()));
                        bidAmount.setFont(bidAmount.getFont().deriveFont(model.getNumberFontSize()));
                        bidAmountContainer.add(bidAmount);

                    JPanel resetContainer = new JPanel();
                        reset = new JButton("RESET");
                        reset.addActionListener(new resetButtonClicked());
                        reset.setFont(reset.getFont().deriveFont(model.getButtonFontSize()));
                        resetContainer.add(reset);

                    JPanel toteBoardContainer = new JPanel();

                        JPanel toteBoard = new JPanel();

                            JPanel leftTote = new JPanel();
                                leftTote.setLayout(new GridLayout(12, 1, 8, 8));
                                minus = new JLabel("MINUS");
                                minus.setFont(minus.getFont().deriveFont(model.getButtonFontSize()));
                                subZeroth = new JButton("-$25");
                                subFirst = new JButton("-$50");
                                subSecond = new JButton("-$100");
                                subThird = new JButton("-$200");
                                subFourth = new JButton("-$300");
                                subFifth = new JButton("-$500");
                                subSixth = new JButton("-$1,000");
                                subSeventh = new JButton("-$5,000");
                                subEighth = new JButton("-$10,000");
                                subNinth = new JButton("-$25,000");
                                subTenth = new JButton("-$50,000");
                                addMinusAtributes(subZeroth);
                                addMinusAtributes(subFirst);
                                addMinusAtributes(subSecond);
                                addMinusAtributes(subThird);
                                addMinusAtributes(subFourth);
                                addMinusAtributes(subFifth);
                                addMinusAtributes(subSixth);
                                addMinusAtributes(subSeventh);
                                addMinusAtributes(subEighth);
                                addMinusAtributes(subNinth);
                                addMinusAtributes(subTenth);
                                leftTote.add(minus);
                                leftTote.add(subZeroth);
                                leftTote.add(subFirst);
                                leftTote.add(subSecond);
                                leftTote.add(subThird);
                                leftTote.add(subFourth);
                                leftTote.add(subFifth);
                                leftTote.add(subSixth);
                                leftTote.add(subSeventh);
                                leftTote.add(subEighth);
                                leftTote.add(subNinth);
                                leftTote.add(subTenth);

                            JPanel rightTote = new JPanel();
                                rightTote.setLayout(new GridLayout(12, 1, 8, 8));
                                plus = new JLabel("PLUS");
                                plus.setFont(plus.getFont().deriveFont(model.getButtonFontSize()));
                                addZeroth = new JButton("+$25");
                                addFirst = new JButton("+$50");
                                addSecond = new JButton("+$100");
                                addThird = new JButton("+$200");
                                addFourth = new JButton("+$300");
                                addFifth = new JButton("+$500");
                                addSixth = new JButton("+$1,000");
                                addSeventh = new JButton("+$5,000");
                                addEighth = new JButton("+$10,000");
                                addNinth = new JButton("+$25,000");
                                addTenth = new JButton("+$50,000");
                                addPlusAtributes(addZeroth);
                                addPlusAtributes(addFirst);
                                addPlusAtributes(addSecond);
                                addPlusAtributes(addThird);
                                addPlusAtributes(addFourth);
                                addPlusAtributes(addFifth);
                                addPlusAtributes(addSixth);
                                addPlusAtributes(addSeventh);
                                addPlusAtributes(addEighth);
                                addPlusAtributes(addNinth);
                                addPlusAtributes(addTenth);
                                rightTote.add(plus);
                                rightTote.add(addZeroth);
                                rightTote.add(addFirst);
                                rightTote.add(addSecond);
                                rightTote.add(addThird);
                                rightTote.add(addFourth);
                                rightTote.add(addFifth);
                                rightTote.add(addSixth);
                                rightTote.add(addSeventh);
                                rightTote.add(addEighth);
                                rightTote.add(addNinth);
                                rightTote.add(addTenth);

                            toteBoard.setLayout(new GridLayout(1, 2, 40, 40));
                            toteBoard.add(leftTote);
                            toteBoard.add(rightTote);

                        toteBoardContainer.add(toteBoard);

                    rightPanelContainer.add(bidAmountContainer);
                    rightPanelContainer.add(toteBoardContainer);
                    rightPanelContainer.add(resetContainer);

                rightPanel.add(rightPanelContainer);

            centerPanel.add(leftPanel);
            centerPanel.add(middlePanel);
            centerPanel.add(rightPanel);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private class exitClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class hipEntered implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String suffix = "";
            if (e.getSource() == prev) {
                model.decrementNumber();
            }
            else if (e.getSource() == next) {
                model.incrementNumber();
            }
            else {
                String tempHip = hipBox.getText();
                String numbers = "0123456789";

                if (!(tempHip.equals(""))) {
                    for (int i=0; i<tempHip.length(); i++) {
                        if (!(numbers.contains(Character.toString(tempHip.charAt(i))))) {
                            suffix = tempHip.substring(i, tempHip.length());
                            tempHip = tempHip.substring(0, i);
                            break;
                        }
                    }
                    model.setNumber(Integer.parseInt(tempHip));
                }
            }
            horseNumber.setText("#" + Integer.toString(model.getNumber()) + suffix);
            hipBox.setText(Integer.toString(model.getNumber()) + suffix);
            model.updateFiles("hip.txt", "#" + Integer.toString(model.getNumber()) + suffix);
        }
    }

    private class miscEntered implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearMisc) {
                model.updateFiles("misc.txt", "");
                miscBox.setText("");
                miscLabel.setText(" ");
            }
            else {
                model.updateFiles("misc.txt", miscBox.getText());
                if (miscBox.getText().length() > 10) {
                    miscLabel.setText(miscBox.getText().substring(0,10) + "...");
                }
                else {
                    miscLabel.setText(miscBox.getText());
                }
            }
        }
    }

    private class soldTicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (soldSelected == false) {
                model.updateFiles("sold.txt", "  *SOLD*  ");
                soldLabel.setText("SOLD");
                soldSelected = true;
            }
            else {
                model.updateFiles("sold.txt", "");
                soldLabel.setText(" ");
                soldSelected = false;
            }
        }
    }

    private class anomalySelected implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearAnomalies) {
                anomalies.clearSelection();
                model.updateFiles("anomaly.txt", "");
                model.setSelectedAnomaly("");
                selectedAnomaly.setText(" ");
            }
            else {
                JRadioButton anomaly = (JRadioButton)e.getSource();
                model.updateFiles("anomaly.txt", anomaly.getText());
                model.setSelectedAnomaly(anomaly.getText());
                selectedAnomaly.setText(model.getSelectedAnomaly());
            }
        }
    }

    private class plusButtonClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            long temp = model.getBidNumber();

            if (e.getSource() == addZeroth) {
                model.setBidNumber(temp+25);
            }
            else if (e.getSource() == addFirst) {
                model.setBidNumber(temp+50);
            }
            else if (e.getSource() == addSecond) {
                model.setBidNumber(temp+100);
            }
            else if (e.getSource() == addThird) {
                model.setBidNumber(temp+200);
            }
            else if (e.getSource() == addFourth) {
                model.setBidNumber(temp+300);
            }
            else if (e.getSource() == addFifth) {
                model.setBidNumber(temp+500);
            }
            else if (e.getSource() == addSixth) {
                model.setBidNumber(temp+1000);
            }
            else if (e.getSource() == addSeventh) {
                model.setBidNumber(temp+5000);
            }
            else if (e.getSource() == addEighth) {
                model.setBidNumber(temp+10000);
            }
            else if (e.getSource() == addNinth) {
                model.setBidNumber(temp+25000);
            }
            else if (e.getSource() == addTenth) {
                model.setBidNumber(temp+50000);
            }

            model.updateFiles("tote.txt", currencyFormat.format(model.getBidNumber()));
            bidAmount.setText(currencyFormat.format(model.getBidNumber()));

            if (model.getBidNumber() >= 9223372036853775807l) {
                bidAmount.setText("ERROR: MAXIMUM VALUE EXCEEDED");
                model.setBidNumber(0);
            }
        }
    }

    private class minusButtonClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            long temp = model.getBidNumber();

            if (e.getSource() == subZeroth) {
                model.setBidNumber(temp-25);
            }
            else if (e.getSource() == subFirst) {
                model.setBidNumber(temp-50);
            }
            else if (e.getSource() == subSecond) {
                model.setBidNumber(temp-100);
            }
            else if (e.getSource() == subThird) {
                model.setBidNumber(temp-200);
            }
            else if (e.getSource() == subFourth) {
                model.setBidNumber(temp-300);
            }
            else if (e.getSource() == subFifth) {
                model.setBidNumber(temp-500);
            }
            else if (e.getSource() == subSixth) {
                model.setBidNumber(temp-1000);
            }
            else if (e.getSource() == subSeventh) {
                model.setBidNumber(temp-5000);
            }
            else if (e.getSource() == subEighth) {
                model.setBidNumber(temp-10000);
            }
            else if (e.getSource() == subNinth) {
                model.setBidNumber(temp-25000);
            }
            else if (e.getSource() == subTenth) {
                model.setBidNumber(temp-50000);
            }

            if (model.getBidNumber() < 0) {
                model.setBidNumber(0);
            }

            model.updateFiles("tote.txt", currencyFormat.format(model.getBidNumber()));
            bidAmount.setText(currencyFormat.format(model.getBidNumber()));
        }
    }

    private class resetButtonClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setBidNumber(0);
            model.updateFiles("tote.txt", currencyFormat.format(model.getBidNumber()));
            bidAmount.setText(currencyFormat.format(model.getBidNumber()));
        }
    }

    private void addMinusAtributes(JButton button) {
        button.addActionListener(new minusButtonClicked());
        button.setBackground(lightRed);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setFont(button.getFont().deriveFont(model.getToteFontSize()));
    }

    private void addPlusAtributes(JButton button) {
        button.addActionListener(new plusButtonClicked());
        button.setBackground(lightGreen);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setFont(button.getFont().deriveFont(model.getToteFontSize()));
    }

    private void createFileExplorer() {
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new java.io.File("."));
        jfc.setDialogTitle("File Explorer");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showOpenDialog(frame);
        model.setDirectory(jfc.getSelectedFile().getAbsolutePath() + "/");
    }

    private class changeDirectoryClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createFileExplorer();
        }
    }

    private class resetAllClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setBidNumber(0);
            model.updateFiles("tote.txt", currencyFormat.format(model.getBidNumber()));
            bidAmount.setText(currencyFormat.format(model.getBidNumber()));

            model.updateFiles("misc.txt", "");
            miscBox.setText("");
            miscLabel.setText(" ");

            soldSelected = false;
            soldLabel.setText(" ");
            model.updateFiles("sold.txt", "");

            anomalies.clearSelection();
            selectedAnomaly.setText(" ");
            model.updateFiles("anomaly.txt", "");

            model.incrementNumber();
            horseNumber.setText("#" + Integer.toString(model.getNumber()));
            hipBox.setText(Integer.toString(model.getNumber()));
            model.updateFiles("hip.txt", "#" + Integer.toString(model.getNumber()));
        }
    }

    private class aboutClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "This program created by Blake Harper for Heritage Place \u00a9 2018.  For more information contact bman0128@gmail.com");
        }
    }

    private class resolutionChanged implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == twelveEighty) {
                model.setResolution(1);
                frame.setSize(1280, 720);
            }
            else if (e.getSource() == nineteenTwenty) {
                model.setResolution(2);
                frame.setSize(1920, 1080);
            }
            else if (e.getSource() == twentyfiveSixty) {
                model.setResolution(3);
                frame.setSize(2560, 1440);
            }

            selectedAnomaly.setFont(selectedAnomaly.getFont().deriveFont(model.getNumberFontSize()));
            clearAnomalies.setFont(clearAnomalies.getFont().deriveFont(model.getButtonFontSize()));
            open.setFont(open.getFont().deriveFont(model.getButtonFontSize()));
            inFoal.setFont(inFoal.getFont().deriveFont(model.getButtonFontSize()));
            colt.setFont(colt.getFont().deriveFont(model.getButtonFontSize()));
            filly.setFont(filly.getFont().deriveFont(model.getButtonFontSize()));
            gelding.setFont(gelding.getFont().deriveFont(model.getButtonFontSize()));
            cribber.setFont(cribber.getFont().deriveFont(model.getButtonFontSize()));
            cryptorchid.setFont(cryptorchid.getFont().deriveFont(model.getButtonFontSize()));
            soldLabel.setFont(soldLabel.getFont().deriveFont(model.getNumberFontSize()));
            horseNumber.setFont(horseNumber.getFont().deriveFont(model.getNumberFontSize()));
            hipBox.setFont(hipBox.getFont().deriveFont(model.getTextBoxFontSize()));
            next.setFont(next.getFont().deriveFont(model.getButtonFontSize()));
            prev.setFont(prev.getFont().deriveFont(model.getButtonFontSize()));
            hipUpdate.setFont(hipUpdate.getFont().deriveFont(model.getButtonFontSize()));
            sold.setFont(sold.getFont().deriveFont(model.getButtonFontSize()));
            miscBox.setFont(miscBox.getFont().deriveFont(model.getTextBoxFontSize()));
            clearMisc.setFont(clearMisc.getFont().deriveFont(model.getButtonFontSize()));
            miscUpdate.setFont(miscUpdate.getFont().deriveFont(model.getButtonFontSize()));
            bidAmount.setFont(bidAmount.getFont().deriveFont(model.getNumberFontSize()));
            reset.setFont(reset.getFont().deriveFont(model.getButtonFontSize()));
            minus.setFont(minus.getFont().deriveFont(model.getButtonFontSize()));
            subZeroth.setFont(subFirst.getFont().deriveFont(model.getToteFontSize()));
            subFirst.setFont(subZeroth.getFont().deriveFont(model.getToteFontSize()));
            subSecond.setFont(subSecond.getFont().deriveFont(model.getToteFontSize()));
            subThird.setFont(subThird.getFont().deriveFont(model.getToteFontSize()));
            subFourth.setFont(subFourth.getFont().deriveFont(model.getToteFontSize()));
            subFifth.setFont(subFifth.getFont().deriveFont(model.getToteFontSize()));
            subSixth.setFont(subSixth.getFont().deriveFont(model.getToteFontSize()));
            subSeventh.setFont(subSeventh.getFont().deriveFont(model.getToteFontSize()));
            subEighth.setFont(subEighth.getFont().deriveFont(model.getToteFontSize()));
            subNinth.setFont(subNinth.getFont().deriveFont(model.getToteFontSize()));
            subTenth.setFont(subTenth.getFont().deriveFont(model.getToteFontSize()));
            plus.setFont(plus.getFont().deriveFont(model.getButtonFontSize()));
            addZeroth.setFont(addZeroth.getFont().deriveFont(model.getToteFontSize()));
            addFirst.setFont(addFirst.getFont().deriveFont(model.getToteFontSize()));
            addSecond.setFont(addSecond.getFont().deriveFont(model.getToteFontSize()));
            addThird.setFont(addThird.getFont().deriveFont(model.getToteFontSize()));
            addFourth.setFont(addFourth.getFont().deriveFont(model.getToteFontSize()));
            addFifth.setFont(addFifth.getFont().deriveFont(model.getToteFontSize()));
            addSixth.setFont(addSixth.getFont().deriveFont(model.getToteFontSize()));
            addSeventh.setFont(addSeventh.getFont().deriveFont(model.getToteFontSize()));
            addEighth.setFont(addEighth.getFont().deriveFont(model.getToteFontSize()));
            addNinth.setFont(addNinth.getFont().deriveFont(model.getToteFontSize()));
            addTenth.setFont(addTenth.getFont().deriveFont(model.getToteFontSize()));
            miscLabel.setFont(miscLabel.getFont().deriveFont(model.getNumberFontSize()));
            titleFonts = new Font(null, Font.BOLD, model.getTitleSize());
            anomalyBorder.setTitleFont(titleFonts);
            hipBorder.setTitleFont(titleFonts);
            miscBorder.setTitleFont(titleFonts);
            toteBorder.setTitleFont(titleFonts);
        }
    }

    private class soldKeyStroke extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (soldSelected == true) {
                model.updateFiles("sold.txt", "");
                soldLabel.setText(" ");
                soldSelected = false;
            }
            else {
                model.updateFiles("sold.txt", "  *SOLD*  ");
                soldLabel.setText("SOLD");
                soldSelected = true;
            }
        }
    }

    private class doNothing extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            //This event is used to unbind the JTextFields
        }
    }
}

class HorseModel {
	private String directory = "";
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenHeight = (int)screenSize.getHeight();
    private int screenWidth = (int)screenSize.getWidth();
    private float buttonFontSize;
    private float toteFontSize;
    private float numberFontSize;
    private float textBoxFontSize;
    private int titleSize;
	private int number = 0;
    private long bidNumber = 0l;
    private String selectedAnomaly = "";

    public HorseModel() {
        if ((screenWidth <= 1280) && (screenHeight <= 720)) {
            setResolution(1);
        }
        else if ((screenWidth <= 1920) && (screenHeight <= 1080)) {
            setResolution(2);
        }
        else {
            setResolution(3);
        }
    }

    public void setResolution(int n) {
        if (n == 1) {
            setButtonFontSize(16.0f);
            setToteFontSize(22.0f);
            setNumberFontSize(45.0f);
            setTextBoxFontSize(16.0f);
            setTitleSize(16);
        }
        else if (n == 2) {
            setButtonFontSize(32.0f);
            setToteFontSize(40.0f);
            setNumberFontSize(65.0f);
            setTextBoxFontSize(32.0f);
            setTitleSize(32);
        }
        else if (n == 3) {
            setButtonFontSize(45.0f);
            setToteFontSize(50.0f);
            setNumberFontSize(100.0f);
            setTextBoxFontSize(45.0f);
            setTitleSize(45);
        }
    }

    public String getSelectedAnomaly() {
        return selectedAnomaly;
    }

    public void setSelectedAnomaly(String s) {
        selectedAnomaly = s;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int i) {
        titleSize = i;
    }

    public float getButtonFontSize() {
        return buttonFontSize;
    }

    public void setButtonFontSize(float f) {
        buttonFontSize = f;
    }

    public float getToteFontSize() {
        return toteFontSize;
    }

    public void setToteFontSize(float f) {
        toteFontSize = f;
    }

    public float getNumberFontSize() {
        return numberFontSize;
    }

    public void setNumberFontSize(float f) {
        numberFontSize = f;
    }

    public float getTextBoxFontSize() {
        return textBoxFontSize;
    }

    public void setTextBoxFontSize(float f) {
        textBoxFontSize = f;
    }

    public void updateFiles(String fileName, String input) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(directory + fileName));
            bufferedWriter.write(input);
            bufferedWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error writing to file '" + fileName + "'");
            e.printStackTrace();
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setDirectory(String d) {
        directory = d;
    }

    public void setNumber(int n) {
        number = n;
    }

    public void incrementNumber() {
    	number++;
    }

    public void decrementNumber() {
    	number--;
    }

    public int getNumber() {
    	return number;
    }

    public long getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(long n) {
        bidNumber = n;
    }
}