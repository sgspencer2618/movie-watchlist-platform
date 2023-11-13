package GUIPrototype;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ClickablePanelList extends JPanel {

    private JPanel panelList;

    public ClickablePanelList(Dimension dimension, String[] panelLabels) {
        setLayout(new BorderLayout());

        // Create a scroll pane to hold the panel list
        JScrollPane scrollPane = new JScrollPane(createPanelList(panelLabels));
        add(scrollPane, BorderLayout.CENTER);

        scrollPane.setPreferredSize(dimension);
    }

    public JPanel createPanelList(String[] panelLabels) {
        panelList = new JPanel(); // Initialize the panelList field
        panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));

        // Add some sample data

        for (int i = 0; i < panelLabels.length; i++) {
            panelList.add(createClickablePanel(panelLabels[i]));
        }

        return panelList;
    }

    public void addToPanelList(JPanel jPanel) {
        this.panelList.add(jPanel);
    }

    public JPanel createClickablePanel(String labelText) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(new EmptyBorder(0, 10, 0, 10));
        panel.setPreferredSize(new Dimension(200, 100));
        panel.setLayout(new BorderLayout(20,0));

        //placeholder url to test image path
        String path = "https://m.media-amazon.com/images/M/MV5BMGVmMWNiMDktYjQ0Mi00MWIxLTk0N2UtN2ZlYTdkN2IzNDNlXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_SX300.jpg";
        URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedImage image = ImageIO.read(url);
            if (image != null) {

                Image scaledimage = image.getScaledInstance(30, 45,
                        Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(scaledimage);
                JLabel imagelabel = new JLabel(imageIcon);

                panel.add(imagelabel, BorderLayout.LINE_START);

            } else {
                // Handle the case where the image couldn't be loaded
                JLabel errorLabel = new JLabel("Error loading image");
                panel.add(errorLabel, BorderLayout.LINE_START);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.CENTER);

        JPanel controlsubpanel = new JPanel();
        controlsubpanel.setLayout(new BorderLayout(20,0));
        JButton add = new JButton("+");
        controlsubpanel.setBorder(new EmptyBorder(30, 0, 30, 0));
        add.setPreferredSize(new Dimension(40,40));
        JLabel rating = new JLabel("1");

        controlsubpanel.add(add, BorderLayout.CENTER);
        controlsubpanel.add(rating, BorderLayout.LINE_END);
        panel.add(controlsubpanel, BorderLayout.LINE_END);

        // Add a click listener to the panel
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //PLACEHOLDER - to be replaced with showing the information pane of the movie
                JOptionPane.showMessageDialog(null, "Clicked on " + labelText);
            }
        });

        return panel;
    }

}