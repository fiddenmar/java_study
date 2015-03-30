package com.jlabs.lab4;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JApplet;
import javax.swing.Timer;

public class AppletTimerAlarm extends JApplet implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2659313924228410032L;
	private Button start;
	private Button stop;
	private Button reset;
	private Label secField;
	private TextField timeField;
	private Timer timer;
	private Double time = null;
	private Double initialTime = time;

	/**
	 * @param args
	 */
	public void init() {
		start = new Button("start");
		stop = new Button("stop");
		reset = new Button("reset");
		secField = new Label();
		timeField = new TextField();
		setLayout(new FlowLayout());
		
		
		add(timeField);
		add(secField);
		add(start);
		add(stop);
		add(reset);
		secField.setText("                                   ");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String corrTime = sdf.format(cal.getTime());
		timeField.setText(corrTime);
		timeField.setColumns(10);
		start.addActionListener(this);
		stop.addActionListener(this);
		reset.addActionListener(this);
		timer = new Timer(2000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	time-=0.001;
	        	if (time > 0)
	        	{
	        		secField.setText(time.toString());
	        	}
	        	else
	        	{
	        		secField.setText("0");
	        		timer.stop();
	        		time = -1.0;
	        		secField.setText("DONE");
	        		Toolkit.getDefaultToolkit().beep();
	        	}
	        }
	    });

	    timer.setRepeats(true);
	    timer.setDelay(1);
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
				if (time.toString().length()>10)
        			secField.setText(time.toString().substring(0, 10));
        		else
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
