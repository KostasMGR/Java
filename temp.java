import java.awt.*;
import java.awt.event.*;

public class temp extends Frame
{
    private TextField celsiusField;
    private Label resultLabel;

    public temp()
    {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setLayout(null);
        setResizable(false);

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });

        Label celsiusLabel = new Label("Celsius: ");
        celsiusLabel.setBounds(50,50,60,20);
        add(celsiusLabel);

        celsiusField = new TextField();
        celsiusField.setBounds(120, 50, 100, 20);
        add(celsiusField);

        Button okButton = new Button("OK");
        okButton.setBounds(230, 50,60,20);
        add(okButton);

        Label resultTitle = new Label("Result: ");
        resultTitle.setBounds(50, 100, 60, 20);
        add(resultTitle);

        resultLabel = new Label(" ");
        resultLabel.setBounds(120, 100, 200, 20);
        add(resultLabel);

        okButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                convert();
            }    
        });

    }

private void convert()
{
    try
    {
        double celsius = Double.parseDouble(celsiusField.getText());
        double fahrenheit = (celsius * 9/5)+32;

        String result = String.format("%.2f F", fahrenheit);
        resultLabel.setText(result);
    }catch(NumberFormatException ex)
    {
        resultLabel.setText("Wrong input");
    }
}

public static void main(String[] args)
{
    temp converter = new temp();
    converter.setVisible(true);
}

}