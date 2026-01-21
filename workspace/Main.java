//Name of Author: Anand Raj
//Date: 1/14/26
//Description:Make a successful game where you need to guess the country(Includes a timer) 
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Main 
{
  // array of 10 Country objects
  private Country[] countryArray = new Country[10];  
  private int index = 0;

  // GUI elements
  private JFrame jFrame;
  private ImageIcon img;
  private JLabel imageLabel;
  private JLabel outputLabel;
  private JTextArea userInput;

  // Next button (needed for timer enable/disable)
  private JButton nextButton;

  public static void main(String[] args) {
    new Main();
  }

  /* ===================== loadCountries ===================== */
  public void loadCountries() 
  {
    File file = new File("/workspaces/Countries/workspace/countries-data.csv");

    try {
      Scanner scan = new Scanner(file);
      int i = 0;

      while (scan.hasNextLine() && i < countryArray.length) {
        String line = scan.nextLine();
        String[] parts = line.split(",");

        countryArray[i] = new Country(
          parts[0], parts[1], parts[2], parts[3]
        );
        i++;
      }
      scan.close();
    } 
    catch (Exception e) {
      System.out.println("Error loading countries.");
    }
  }

  /* ===================== showCountry ===================== */
  public void showCountry() 
  {
    Country c = countryArray[index];
    img = new ImageIcon("/workspaces/Countries/workspace/" + c.getImageFile());
    imageLabel.setIcon(img);
  }
  
  /* ===================== nextButtonClick ===================== */
  public void nextButtonClick()
  {
    nextButton.setEnabled(false);

    index++;
    if (index > 9) {
      index = 0;
    }

    outputLabel.setText("");
    showCountry();

    startWaitTimer();
  }

  /* ===================== reviewButtonClick ===================== */
  public void reviewButtonClick()
  {
    Country c = countryArray[index];
    outputLabel.setText(c.toString());
  }

  /* ===================== quizButtonClick ===================== */
  public void quizButtonClick()
  {
    Country c = countryArray[index];
    String answer = userInput.getText().trim();

    if (answer.equalsIgnoreCase(c.getCapital())) {
      outputLabel.setText("Correct!");
    } else {
      outputLabel.setText("Incorrect. The capital is " + c.getCapital());
    }

    userInput.setText("");
  }

  /* ===================== 15 SECOND TIMER ===================== */
  private void startWaitTimer()
  {
    outputLabel.setText("Please wait 15 seconds...");

    javax.swing.Timer timer = new javax.swing.Timer(15000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        nextButton.setEnabled(true);
        outputLabel.setText("You may press Next now.");
      }
    });

    timer.setRepeats(false);
    timer.start();
  }

  /* ===================== CONSTRUCTOR ===================== */
  public Main() 
  {
    jFrame = new JFrame("Countries");
    jFrame.setLayout(new FlowLayout());
    jFrame.setSize(500, 360);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton reviewButton = new JButton("Review");
    JButton quizButton = new JButton("Quiz");
    nextButton = new JButton("Next");

    jFrame.add(reviewButton);
    jFrame.add(quizButton);
    jFrame.add(nextButton);

    img = new ImageIcon("worldmap.jpg");
    imageLabel = new JLabel(img);
    outputLabel = new JLabel();
    userInput = new JTextArea(1, 40);

    jFrame.add(imageLabel);
    jFrame.add(outputLabel);
    jFrame.add(userInput);

    reviewButton.addActionListener(e -> reviewButtonClick());
    quizButton.addActionListener(e -> quizButtonClick());
    nextButton.addActionListener(e -> nextButtonClick());

    loadCountries();
    showCountry();

    jFrame.setVisible(true);
  }
}
