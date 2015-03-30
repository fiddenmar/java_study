package com.jlabs.lab3;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
 
public class TimerAlarm implements Runnable, ActionListener {
	
	private JButton start;
	private JButton stop;
	private JButton reset;
	private JLabel secField;
	private JTextField timeField;
	private Timer timer;
	private Double time = null;
	private Double initialTime = time;
 
    @Override
    public void run() {
        JFrame f = new JFrame("TimerAlarm");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());
        f.setVisible(true);
        
		start = new JButton("start");
		stop = new JButton("stop");
		reset = new JButton("reset");
		secField = new JLabel();
		secField.setPreferredSize(new Dimension(100, 100));
		timeField = new JTextField();
		
		
		f.add(timeField);
		f.add(secField);
		f.add(start);
		f.add(stop);
		f.add(reset);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String corrTime = sdf.format(cal.getTime());
		timeField.setText(corrTime);
		timeField.setColumns(10);
		f.pack();
		start.addActionListener(this);
		stop.addActionListener(this);
		reset.addActionListener(this);
		timer = new Timer(2000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	time-=0.001;
	        	if (time > 0)
	        	{
	        		if (time.toString().length()>10)
	        			secField.setText(time.toString().substring(0, 10));
	        		else
	        			secField.setText(time.toString());
	        	}
	        	else
	        	{
	        		secField.setText("0");
	        		timer.stop();
	        		time = null;
	        		secField.setText("DONE");
	        	}
	        }
	    });

	    timer.setRepeats(true);
	    timer.setDelay(1);
    }
 
    public static void main(String[] args) {
    	TimerAlarm se = new TimerAlarm();
        SwingUtilities.invokeLater(se);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String msg = arg0.getActionCommand();
		if (msg.equals("start"))
		{
			if (time == null)
			{
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String corrTime = sdf.format(cal.getTime());
				String targetTime = timeField.getText();
				time = ((Integer.parseInt(targetTime.substring(0, 2)) - Integer.parseInt(corrTime.substring(0, 2))) * 3600 +
						(Integer.parseInt(targetTime.substring(3, 5)) - Integer.parseInt(corrTime.substring(3, 5))) * 60 +
						(Integer.parseInt(targetTime.substring(6)) - Integer.parseInt(corrTime.substring(6))))/1.0;
				if (time < 0) time +=86400;
				initialTime = time;
				secField.setText(time.toString());
			}
			if (Double.parseDouble(secField.getText())!=time)
			{
				time = Double.parseDouble(secField.getText());
				if (time > 0)
					initialTime = time;
			}
			timer.start();
		}
		if (msg.equals("stop"))
		{
			timer.stop();
		}
		if (msg.equals("reset"))
		{
			timer.stop();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String corrTime = sdf.format(cal.getTime());
			String targetTime = timeField.getText();
			time = ((Integer.parseInt(targetTime.substring(0, 2)) - Integer.parseInt(corrTime.substring(0, 2))) * 3600 +
					(Integer.parseInt(targetTime.substring(3, 5)) - Integer.parseInt(corrTime.substring(3, 5))) * 60 +
					(Integer.parseInt(targetTime.substring(6)) - Integer.parseInt(corrTime.substring(6))))/1.0;
			if (time < 0) time +=86400;
			initialTime = time;
			secField.setText(time.toString());
			timer.start();
		}	
	}
 
}
