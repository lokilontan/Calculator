package vplagov;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;

import java.net.URL;


public class Controller {

    //Apllying a sound
    URL path = getClass().getResource("button.wav");
    AudioClip bs = new AudioClip(path.toString());


    //Method for checking balanced braces
    public boolean areBracesBalanced() {
        String[] s = infixField.getText().split(" ");
        String braces = "";
        for (int i = 0; i < s.length; i++) {
            if ( s[i].equals("(") || s[i].equals(")") ) braces = braces + s[i];
        }

        Balance b = new Balance();
        if (b.isBalanced(braces)) return true; else return false;

    }

    //Method for checking if it`s +,-,*, ^, / or sqrt
    public boolean ifOperator(String o) {

        if (o.equals("+") || o.equals("-") || o.equals("*") || o.equals("/") || o.equals("^") || o.equals("sqrt") || o.equals("!")) return true;
        else return false;
    }

    //Method for printing digits, except 0
    public void printNumberEvent(String n) {

        String[] s = infixField.getText().split(" ");

        if (s != null && s.length > 0 && !ifOperator(s[s.length - 1]) && !s[s.length - 1].equals("(") && Double.parseDouble(s[s.length - 1]) == 0 && s[s.length - 1].charAt(s[s.length - 1].length() - 1) != '.') {
            s[s.length - 1] = n;
            infixField.setText(String.join(" ", s));
        }
        else if (ifOperator(s[s.length - 1]) || s[s.length - 1].equals("(")) infixField.setText(String.join(" ", s) + " " + n);
        else infixField.setText(String.join(" ", s) + n);

        bs.play();

    }

    //Method for printing operator +,-,*, ^ or /
    public void printOperatorEvent(String o) {
        String[] s = infixField.getText().split(" ");

        if (s != null && s.length > 0 && ifOperator(s[s.length - 1]) && !s[s.length -1].equals("sqrt") && !s[s.length -1].equals("(") && !s[s.length - 1].equals("!") && !s[s.length - 1].equals(")")) {s[s.length - 1] = o; infixField.setText(String.join(" ", s) + " ");}
        else if (!s[s.length -1].equals("sqrt") && !s[s.length - 1].equals("!")&& !s[s.length -1].equals("(") && !s[s.length - 1].equals(")")) infixField.setText(infixField.getText() + " " + o + " ");
        else if (s[s.length - 1].equals("!") || s[s.length - 1].equals(")")) infixField.setText(infixField.getText() + o + " ");

       bs.play();
    }


    //Creating text fields outside the "start" method, so it has access from the outside
    @FXML
    TextField infixField;
    @FXML
    TextField postfixField;
    @FXML
    TextField resultField;

    //Creating labels
    @FXML
    Label invalidInput;

    //Creating buttons
    @FXML Button calcButton;
    @FXML Button clearButton;
    @FXML Button ansButton;
    @FXML Button zeroButton;
    @FXML Button oneButton;
    @FXML Button twoButton;
    @FXML Button threeButton;
    @FXML Button fourButton;
    @FXML Button fiveButton;
    @FXML Button sixButton;
    @FXML Button sevenButton;
    @FXML Button eightButton;
    @FXML Button nineButton;
    @FXML Button pointButton;
    @FXML Button plusButton;
    @FXML Button negationButton;
    @FXML Button minusButton;
    @FXML Button factorialButton;
    @FXML Button multiplyButton;
    @FXML Button divisionButton;
    @FXML Button sqrtButton;
    @FXML Button exponentButton;
    @FXML Button openBracket;
    @FXML Button closedBracket;


    //Calculate Button event
    public void handleCalcButton(ActionEvent e){

        try
        {

            if (areBracesBalanced())  {
                //Create SY and Postfix object and insert string returned from SY class
                Postfix newPostfix = new Postfix (new SY(infixField.getText()).toPostfix());

                //Update postfix Field
                postfixField.setText(newPostfix.getPostfix());

                //Update result Field
                resultField.setText("" + newPostfix.eval());

                bs.play();
            }
            else invalidInput.setVisible(true);
        }
        catch (Exception e1)
        {
            invalidInput.setVisible(true);
        }

    }

    //Clear Button event
    public void handleClearButton(ActionEvent e){
        infixField.setText("" + "0");
        postfixField.clear();
        resultField.clear();
       //bs.play();
        invalidInput.setVisible(false);
    }

    public void handleAnsButton(ActionEvent e){

        if (resultField.getText().length()>0) {

            infixField.setText(resultField.getText());
            postfixField.clear();
            resultField.clear();

        }

        bs.play();

    }

    //"Zero" button event
    public void handleZeroButton(ActionEvent e){

        String[] s = infixField.getText().split(" ");

        if (s != null && s.length > 0 && ifOperator(s[s.length - 1])) infixField.setText(infixField.getText() + "0");
        else if (s != null && s.length > 0 && Double.parseDouble(s[s.length - 1]) != 0 ) infixField.setText(String.join(" ", s) + "0");

        bs.play();

    }

    //Handling EVENTS METHODS
    public void handleOneButton(ActionEvent e){

        printNumberEvent("1");

    }

    public void handleTwoButton(ActionEvent e){

        printNumberEvent("2");

    }

    public void handleThreeButton(ActionEvent e){

        printNumberEvent("3");

    }

    public void handleFourButton(ActionEvent e){

        printNumberEvent("4");

    }

    public void handleFiveButton(ActionEvent e){

        printNumberEvent("5");

    }

    public void handleSixButton(ActionEvent e){

        printNumberEvent("6");

    }

    public void handleSevenButton(ActionEvent e){

        printNumberEvent("7");

    }

    public void handleEightButton(ActionEvent e){

        printNumberEvent("8");

    }

    public void handleNineButton(ActionEvent e){

        printNumberEvent("9");

    }

    public void handlePointButton(ActionEvent e){

        String[] s = infixField.getText().split(" ");
        System.out.println(s[s.length - 1]);

        if (s != null && s.length > 0 && ifOperator(s[s.length - 1])) infixField.setText(infixField.getText() + "0.");
        else if (s != null && s.length > 0 && !s[s.length - 1].contains(".")) infixField.setText(infixField.getText() + ".");

        bs.play();
    }

    public void handlePlusButton(ActionEvent e){

        printOperatorEvent("+");

    }

    public void handleMinusButton(ActionEvent e){

        printOperatorEvent("-");

    }

    public void handleMultiplyButton(ActionEvent e){

        printOperatorEvent("*");

    }

    public void handleDivisionButton(ActionEvent e){

        printOperatorEvent("/");

    }

    public void handleExponentButton(ActionEvent e){

        printOperatorEvent("^");

    }

    public void handleSqrtButton(ActionEvent e) {

        String[] s = infixField.getText().split(" ");

        if (s.length == 1) infixField.setText("sqrt ");

        if (s[s.length - 1].equals("(")) infixField.setText(infixField.getText() + " sqrt ");
        else if (s != null && s.length > 0 && ifOperator(s[s.length - 1]) && !ifOperator(s[s.length - 2]) && !s[s.length - 1].equals("!")) {infixField.setText(infixField.getText() + "sqrt ");}

        bs.play();

    }

    public void handleNegationButton(ActionEvent e) {

        String[] s = infixField.getText().split(" ");

        if(!ifOperator(s[s.length - 1]) && !s[s.length - 1].equals("0")) {
            s[s.length-1] = "" + (-1 * Double.parseDouble(s[s.length - 1]));
            infixField.setText(String.join(" ", s));
        }

        bs.play();

    }

    public void handleFactorialButton(ActionEvent e) {

        String[] s = infixField.getText().split(" ");

        if (s[s.length - 1].contains("-")) {invalidInput.setVisible(true);}
        else if(!s[s.length - 1].equals("(") && !s[s.length - 1].equals(")") && !ifOperator(s[s.length - 1])) {infixField.setText(infixField.getText() + " ! ");}
        else if (s[s.length - 1].equals(")")) infixField.setText(infixField.getText() + "! ");

        bs.play();
    }

    public void handleOpenBracketButton(ActionEvent e) {

        String[] s = infixField.getText().split(" ");

        if(!ifOperator(s[s.length - 1])) {
            String temp = s[s.length - 1];
            s[s.length - 1 ] = "(";
            infixField.setText(String.join(" ", s) + " " + temp);}
        else  if (!s[s.length - 1].equals("!")) infixField.setText(infixField.getText() + "(");
        bs.play();
    }

    public void handleClosedBracketButton(ActionEvent e) {

        String[] s = infixField.getText().split(" ");

        if(s[s.length -1].equals("!") || s[s.length -1].equals(")")) infixField.setText(infixField.getText() + ") " );
        else if(!ifOperator(s[s.length -1])) infixField.setText(infixField.getText() + " ) " );

        bs.play();
    }


}
