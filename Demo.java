import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Demo implements ActionListener {
    JFrame f = new JFrame();

    JButton set = new JButton("set");
    JButton check = new JButton("check");
    JButton reset = new JButton("reset");

    String pswd="";         //password entered
    String patt="";         //original password

    JButton[] b = new JButton[9];
    Demo() throws Exception{

        for(int i=0;i<9;i++) {
            b[i] = new JButton();
            b[i].setActionCommand("".valueOf(i));
        }

        BufferedImage tmp;
        BufferedImage img = ImageIO.read(new File("D://sam2.jpg"));   //filepath here
        Image imgin = img.getScaledInstance(500,500,Image.SCALE_SMOOTH);
        img = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(imgin,0,0,null);
        g2d.dispose();

//        img is scaled to 500x500
        ImageIcon newico;
        int[] xpos={0,100,200,0,100,200,0,100,200};
        int[] ypos={0,0,0,100,100,100,200,200,200};
        for (int i=0;i<9;i++) {
            tmp = img.getSubimage(xpos[i], ypos[i], 100, 100);
            newico = new ImageIcon(tmp);
            b[i].setIcon(newico);
            b[i].setBounds(xpos[i], ypos[i], 100, 100);
        }


        JPanel p = new JPanel();
        p.setLayout(null);

        for(JButton jb:b){
            p.add(jb);
            jb.addActionListener(this);
        }

        set.setBounds(20,320,80,30);
        reset.setBounds(110,320,80,30);
        check.setBounds(200,320,80,30);

        set.addActionListener(this);
        reset.addActionListener(this);
        check.addActionListener(this);

        p.add(set);
        p.add(reset);
        p.add(check);




        f.add(p);
        f.setVisible(true);
        f.setSize(310,410);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == set) {
            patt = pswd;
            pswd = "";
            JOptionPane.showMessageDialog(null,"PASSWORD IS SET","SET",JOptionPane.PLAIN_MESSAGE);
            set.setEnabled(false);
            return;
        }else if (e.getSource() == reset) {
            pswd = "";
            patt = "";
            set.setEnabled(true);
            return;
        }else if (e.getSource() == check) {
            if (pswd.equals(patt)) {
                JOptionPane.showMessageDialog(null,"PASSWORD MATCHED","CHECK",JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"PASSWORD NOT MATCHED","CHECK",JOptionPane.ERROR_MESSAGE);
            }
            pswd = "";
            return;
        }
        pswd+=e.getActionCommand();
        return;
    }

    public static void main(String[] args) throws Exception{
        new Demo();
    }
}
