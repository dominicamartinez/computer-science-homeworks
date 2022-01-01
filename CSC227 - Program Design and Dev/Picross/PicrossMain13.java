import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PicrossMain13 {
    public static void main(String[] args) {
        new PicrossFrame().show();
    }
}

class PicrossFrame extends JFrame implements ActionListener, MouseListener {
    private PicrossGame pg;
    private PicrossView pv;

    private JMenuItem openMenuItem;

    public PicrossFrame() {
        setTitle("Java Picross");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(30, 100);
        setResizable(false);

        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);
        JMenu fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(this);
        fileMenu.add(openMenuItem);
        jmb.add(fileMenu);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() != openMenuItem)
            return;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal != JFileChooser.APPROVE_OPTION)
            return;
        pg = new PicrossGame(chooser.getSelectedFile().getAbsolutePath());
        pv = new PicrossView(pg);
        pv.addMouseListener(this);
        this.getContentPane().removeAll();
        this.getContentPane().add(pv);
        this.setSize(pv.getWidth(), pv.getHeight());
        this.validate();
        pv.repaint();
    }

    public void mousePressed(MouseEvent me) {
        if (pg == null || pg.isWon() || pg.isLost())
            return;
        int x = me.getX();
        int y = me.getY();
        x -= 100;
        y -= 100;
        int[][] temp = pg.getAttempt();
        if (x > 0 && x < temp[0].length * 20)
            if (y > 0 && y < temp.length * 20)
                if (SwingUtilities.isLeftMouseButton(me))
                    pg.hit(y/20, x/20);
                else if (SwingUtilities.isRightMouseButton(me))
                    pg.flag(y/20, x/20);
        pv.repaint();
    }

    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}
    public void mouseClicked(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
}

class PicrossView extends JPanel {
    private PicrossGame pg;

    public PicrossView(PicrossGame pg) {
        this.pg = pg;
        int[][] temp = pg.getAttempt();

       this.setSize(temp[0].length * 20 + 220, temp.length * 20 + 270);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        RenderingHints AALIAS = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(AALIAS);

        int[][] temp = pg.getAttempt();
        for (int i = 0; i < temp.length; i++)
            for (int j = 0; j < temp[0].length; j++) {
                int sq = temp[i][j];
                if (sq == PicrossGame.EMPTY)
                    g2d.setColor(Color.white);
                else if (sq == PicrossGame.HIT)
                    g2d.setColor(Color.blue);
                else if (sq == PicrossGame.FLAG)
                    g2d.setColor(Color.green);
                else if (sq == PicrossGame.MISS)
                    g2d.setColor(Color.red);
                g2d.fillRect(100 + j * 20, 100 + i * 20, 20, 20);
            }
        g2d.setColor(Color.black);
        for (int i = 0; i < temp.length; i++)
            for (int j = 0; j < temp[0].length; j++)
                g2d.drawRect(100 + j * 20, 100 + i * 20, 20, 20);

        for (int i = 0; i < temp.length; i++) {
            Font f = new Font("Monospaced", Font.PLAIN, 12);
            g2d.setFont(f);
            int[] arr = pg.getRowCalculation(i);
            String s = "";
            for (int j = 0; j < arr.length - 1; j++)
                s += arr[j] + " ";
            s += arr[arr.length-1];
            int width = g.getFontMetrics().stringWidth(s);
            int height = g.getFontMetrics().getHeight();

            g2d.drawString(s, 100 - width, 100 + (i + 1) * 20 - 4);
        }

        for (int j = 0; j < temp[0].length; j++) {
            Font f = new Font("Monospaced", Font.PLAIN, 12);
            g2d.setFont(f);
            int[] arr = pg.getColCalculation(j);

            for (int i = 0; i < arr.length; i++) {
                String s = arr[arr.length - 1 - i] + "";
                int width = g.getFontMetrics().stringWidth(s);
                int height = g.getFontMetrics().getHeight() + 3;
                g2d.drawString(s, 100 + (j + 1) * 20 - width / 2 - 10, 95 - i * height / 2);
            }
        }

        Font f = new Font(null, Font.PLAIN, 12);
        g2d.setFont(f);
        String s = "Number of misses: " + pg.getMisses() + " (" + pg.getEndgame() + " loses)";
        int x = getWidth() / 2 - g.getFontMetrics().stringWidth(s) / 2;
        g2d.drawString(s, x, 100 + temp.length * 20 + 20);

        f = new Font("SansSerif", Font.BOLD, 45);
        g2d.setColor(new Color(50, 100, 50));
        g2d.setFont(f);
        String s3 = "";
        if (pg.isWon())
            s3 = "You win!";
        if (pg.isLost())
            s3 = "You lose!";
        int x2 = getWidth() / 2 - g.getFontMetrics().stringWidth(s3) / 2;
        int y2 = getHeight() / 2 - g.getFontMetrics().getHeight() / 2;
        g2d.drawString(s3, x2, y2);
    }
}
