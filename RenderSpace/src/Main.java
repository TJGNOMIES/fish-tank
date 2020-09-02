
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main implements Runnable, KeyListener {
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public int tommyXpos = 0;
    public Image stickMan = Toolkit.getDefaultToolkit().getImage("stickFigure.png");
    public Image runningMan = Toolkit.getDefaultToolkit().getImage("runningStickMan.png");
    public Person tommy = new Person(20, 60, 200, 200, 1, 0);
    public Person sebby = new Person(20, 50, 200, 100, 4, 5);
    public static String[] names;
    public static Person[] people;
    public static Fishes[] fish;


    public static void main(String[] args) {
        Main ex = new Main();
        new Thread(ex).start();
    }

    public Main() {
        setUpGraphics();
        fish = new Fishes[100];
        for (int i = 0; i < fish.length; i++) {
            fish[i] = new Fishes(5, 5, 100, 100, (int) ((Math.random() - .5) * 12), (int) ((Math.random() - .5) * 12));
        }
    }

    public void run() {
        while (true) {
            moveThings();
            render();
            pause(20);


//            if (tommy.xpos == WIDTH){
//                tommy.xpos = 0;
//            }
//            if (tommy.ypos == HEIGHT){
//                tommy.ypos = 0;
//            }

        }
    }


    public void moveThings() {
        tommy.xpos += tommy.dx;
        tommy.ypos += tommy.dy;
        sebby.xpos += sebby.dx;
        sebby.ypos += sebby.dy;
        for (int i = 0; i < fish.length; i++) {
            fish[i].xpos += fish[i].dx;
            fish[i].ypos += fish[i].dy;
        }
        wrapCharacters();
    }

    public void wrapCharacters() {
        if (tommy.xpos == tommy.width || tommy.xpos == WIDTH - tommy.width) {
            tommy.dx = -tommy.dx;
        }
        if (tommy.ypos == tommy.height || tommy.ypos == HEIGHT - tommy.height) {
            tommy.dy = -tommy.dy;
        }
        if (sebby.xpos == sebby.width || sebby.xpos == WIDTH - sebby.width) {
            sebby.dx = -sebby.dx;
        }
        if (sebby.ypos == sebby.height || sebby.ypos == HEIGHT - sebby.height) {
            sebby.dy = -sebby.dy;
        }
        for (int i = 0; i < fish.length; i++) {
            if (fish[i].xpos <= fish[i].width || fish[i].xpos >= WIDTH - fish[i].width) {
                fish[i].dx = -fish[i].dx;
            }
            if (fish[i].ypos <= fish[i].height || fish[i].ypos >= HEIGHT - fish[i].height) {
                fish[i].dy = -fish[i].dy;
            }
        }
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    private void setUpGraphics() {
        frame = new JFrame("Application Template");
        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        canvas.addKeyListener(this);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                Component c = (Component) evt.getSource();
                canvas.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            }
        });

        System.out.println("DONE graphic setup");
    }


    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, frame.getWidth(), frame.getHeight());

        g.drawImage(stickMan, tommy.xpos, tommy.ypos, tommy.width, tommy.height, null);
        g.drawImage(runningMan, sebby.xpos, sebby.ypos, sebby.width, sebby.height, null);
        for (int i = 0; i < fish.length; i++) {
            g.drawImage(stickMan, fish[i].xpos, fish[i].ypos, fish[i].width, fish[i].height, null);
        }

        //g.fillRect(tommy.xpos, tommy.ypos, tommy.width, tommy.height);

        g.dispose();
        bufferStrategy.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 65) {
            sebby.dx = -1;
        } else if (key == 68) {
            sebby.dx = 1;
        } else if (key == 87) {
            sebby.dy = -1;
        } else if (key == 83) {
            sebby.dy = 1;
        }

//        switch(key) {
//            case 65:
//                sebby.dx =-1;
//                break;
//            case 68:
//
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 65) {
            sebby.dx = 0;
        } else if (key == 68) {
            sebby.dx = 0;
        } else if (key == 87) {
            sebby.dy = 0;
        } else if (key == 83) {
            sebby.dy = 0;
        }

    }
}