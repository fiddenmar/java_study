package com.jlabs.lab7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

class ButtonRunner extends Thread  implements ActionListener {
    int speed;
    int fieldWidth;
    public JButton runner;
    int num;
    ButtonRunner(int _speed, int _fieldWidth, JButton but, int n) {
    	fieldWidth = _fieldWidth;
    	runner = but;
        speed = _speed;
        num = n;
    }

    public ButtonRunner() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
         try {
              for (;;) {
            	  if (Winner.haveWinner!=Winner.pause)
            	  {
            		  runner.setBounds(runner.getBounds().x+speed, runner.getBounds().y, runner.getBounds().width, runner.getBounds().height);
            	  if (runner.getBounds().x+runner.getBounds().width >= fieldWidth)
            	  {
            		  Winner.haveWinner = num;
            		  return;
            	  }
                  if (Winner.haveWinner!=Winner.noWinner)
                	  return;
            	  }
                  sleep(100);
              }
         } catch (InterruptedException e) {
              return;
         }
    }
    
    private void refresh()
    {
    	runner.setBounds(0, runner.getBounds().y, runner.getBounds().width, runner.getBounds().height);
    }
    
    public void ini() throws InterruptedException {
    	String restart="restart";
    	Vector<Integer> speeds = new Vector<Integer>();
    	Vector<JButton> buttons = new Vector<JButton>();
    		Winner.haveWinner = Winner.noWinner;
		    JFrame f = new JFrame();
		    f.setPreferredSize(new Dimension(500, 500));
		    f.setLayout(null);
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    int fieldWidth = 500;
		    Random rnd = new Random();
		    int num = rnd.nextInt(5)+3;
		    Vector <ButtonRunner> buttonRunners = new Vector<ButtonRunner>();
		    for (int i=0; i<num; i++)
		    {
		    	JButton butt = new JButton(""+i);
		    	f.add(butt);
		    	Insets insets = f.getInsets();
		    	butt.setBounds(0+insets.left, i*60 + insets.top,
		                 50, 20);
		    	buttons.add(butt);
		    	speeds.add(rnd.nextInt(10)+1);
		    }
		    f.pack();
		    f.setVisible(true);
	    	while (restart.equals("restart"))
	    	{
	    		for (int i=0; i<num; i++)
	    		{
	    			buttonRunners.add(new ButtonRunner(speeds.get(i), fieldWidth, buttons.get(i), i));
	    			buttonRunners.get(i).runner.addActionListener(this);
	    		}
			    for (int i=0; i<num; i++)
			    {
			    	buttonRunners.get(i).start();
			    }
			    for (int i=0; i<num; i++)
			    {
			    	buttonRunners.get(i).join();
			    }
			    f.setTitle("Winner is "+Winner.haveWinner);
			    f.getContentPane().setBackground(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
		    	Scanner scanner = new Scanner(System.in);
		    	restart = scanner.nextLine();
		    	if (restart.equals("restart"))
		    	{
		    		for (int i=0; i<num; i++)
				    {
				    	buttonRunners.get(i).refresh();
				    }
		    		buttonRunners.removeAllElements();
		    		f.setTitle("");
		    		Winner.haveWinner = Winner.noWinner;
		    	}
	    	}
    	
    }

    public static void main(String[] args) throws InterruptedException
    {
    	ButtonRunner br = new ButtonRunner();
    	br.ini();
    }
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String msg = arg0.getActionCommand();
		int num = Integer.parseInt(msg);
		if (Winner.haveWinner != Winner.pause)
			Winner.haveWinner = Winner.pause;
		else
			Winner.haveWinner = Winner.noWinner;
	}
}
