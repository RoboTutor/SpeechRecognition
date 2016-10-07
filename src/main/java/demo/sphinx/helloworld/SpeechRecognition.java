
/*
 * Copyright 1999-2004 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 *
 */

package demo.sphinx.helloworld;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;


import pal.TECS.PALClient;
import pal.TECS.SpeechRecognitionCommand;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;



/**
 * A simple demo showing a simple speech application 
 * built using Sphinx-4. This application uses the Sphinx-4 endpointer,
 * which automatically segments incoming audio into utterances and silences.
 */

public class SpeechRecognition extends JFrame {

	private PALClient thePalClient;
	private JTextArea textWindow= new JTextArea();

	public static String SRCommand="";
	
	public String START=   "Start(btn)";
	public String PAUSE=   "Pause(btn)";
	public String GOTO=	   "GoTo(btn)";
	public String FINISH=  "Finish(btn)";
	public String CONTINUE="Continue(btn)";
	public String WAKEUP=  "WakeUp(btn)";
	
	
	public static String [] numbers = { 
			"zero",
			"one",
			"two",
			"three",
			"four",
			"five",
			"six",
			"seven",
			"eight",
			"nine",

			"ten", 
			"eleven",
			"twelve",
			"thirteen",
			"fourteen",
			"fifteen",
			"sixteen",
			"seventeen",
			"eighteen",
			"nineteen",

			"twenty",
			"twenty one",
			"twenty two",
			"twenty three",
			"twenty four",
			"twenty five",
			"twenty six",
			"twenty seven",
			"twenty eight",
			"twenty nine",

			"thirty",
			"thirty one",
			"thirty two",
			"thirty three",
			"thirty four",
			"thirty five",
			"thirty six",
			"thirty seven",
			"thirty eight",
			"thirty nine",

			"forty",
			"forty one",
			"forty two",
			"forty three",
			"forty four",
			"forty five",
			"forty six",
			"forty seven",
			"forty eight",
			"forty nine",

			"fifty",
			"fifty one",
			"fifty two",
			"fifty three",
			"fifty four",
			"fifty five",
			"fifty six",
			"fifty seven",
			"fifty eight",
			"fifty nine",

			"sixty",
			"sixty one",
			"sixty two",
			"sixty three",
			"sixty four",
			"sixty five",
			"sixty six",
			"sixty seven",
			"sixty eight",
			"sixty nine",

			"seventy",
			"seventy one",
			"seventy two",
			"seventy three",
			"seventy four",
			"seventy five",
			"seventy six",
			"seventy seven",
			"seventy eight",
			"seventy nine",

			"eighty",
			"eighty one",
			"eighty two",
			"eighty three",
			"eighty four",
			"eighty five",
			"eighty six",
			"eighty seven",
			"eighty eight",
			"eighty nine",

			"ninety",
			"ninety one",
			"ninety two",
			"ninety three",
			"ninety four",
			"ninety five",
			"ninety six",
			"ninety seven",
			"ninety eight",
			"ninety nine",
			"hundred" } ;
	
	
    static private final String newline = "\n";
 
	
    // Constructor 
	public SpeechRecognition(String clientName) {
		
        createAndShowGUI();
		
		setSize(600, 140);

	    setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		/*Initiate textWindow */
		setLayout(new BorderLayout());
		setSize(600, 500);
		
		//Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(textWindow);
        scroller.setPreferredSize(new Dimension(600,400));
        add(scroller, BorderLayout.SOUTH);
        
		
	    setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle(clientName);
		
		/*Create Connection and Subscription */
		connectClientandSubscribe(clientName);
		
	}
	
	  private void connectClientandSubscribe(final String clientName) {

		/*Manually assigned IP */
		String tecsserver = "127.0.0.1";
		//String tecsserver = System.getProperty(TECSSERVER_IP_PROP);
		thePalClient = new PALClient(tecsserver, clientName);
		    
		// Start Listening slideNumbers 
		thePalClient.startListening();
	  }
	  

	  // Sends SpeechRecognitionCommand to server
	    public void sendSRC(String command){

			thePalClient.send(new SpeechRecognitionCommand(102,command));
			textWindow.append( command  +  System.getProperty("line.separator"));

	    }
	    
	 // Create GUI
	 		private void createAndShowGUI() {
	 		
	 		// Turn off metal's use of bold fonts
	         UIManager.put("swing.boldMetal", Boolean.FALSE); 
	 			
	 		// Panel
	 		JPanel panel = new JPanel();
	 		getContentPane().add(panel, BorderLayout.CENTER);
	 		panel.setLayout(null);
	 		
	 	
	 		// WakeUp Button
	 		JButton wakeupButton = new JButton("Wake Up");
	 		wakeupButton.setBounds(10, 10, 85, 30);
	 		panel.add(wakeupButton);
	 		wakeupButton.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {
	 				
	 				thePalClient.send(new SpeechRecognitionCommand(102, WAKEUP ));
	 				textWindow.append(WAKEUP +  System.getProperty("line.separator"));
	 				System.out.println(WAKEUP);
	 				
	 			}
	 		});
	 		
	 		// Start Button
	 		JButton startButton = new JButton("Start");
	 		startButton.setBounds(105, 10, 60, 30);
	 		panel.add(startButton);
	 		startButton.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {
	 				
	 				thePalClient.send(new SpeechRecognitionCommand(102, START ));
	 				textWindow.append(START +  System.getProperty("line.separator"));
	 				System.out.println(START);
	 				
	 			}
	 		});

	 		
	 		// Pause Button
	 		JButton pauseButton = new JButton("Pause");
	 		pauseButton.setBounds(175, 10, 70, 30);
	 		panel.add(pauseButton);
	 		pauseButton.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {
	 				
	 				thePalClient.send(new SpeechRecognitionCommand(102, PAUSE ));
	 				textWindow.append(PAUSE +  System.getProperty("line.separator"));
	 				System.out.println(PAUSE);


	 			}
	 		});
	 		
	 		// Continue Button
	 		JButton continueButton = new JButton("Continue");
	 		continueButton.setBounds(255, 10, 85, 30);
	 		panel.add(continueButton);
	 		continueButton.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {
	 				
	 				thePalClient.send(new SpeechRecognitionCommand(102, CONTINUE ));
	 				textWindow.append(CONTINUE +  System.getProperty("line.separator"));
	 				System.out.println(CONTINUE);


	 			}
	 		});
	 		
	 		// GO TO SLIDE X Button
	 		JButton goToButton = new JButton("Go To Slide #[1-100]");
	 		goToButton.setBounds(350, 10, 147, 30);
	 		panel.add(goToButton);
	 		goToButton.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {

	 				// Random Slide Number between 0-100
	 				int X= (int) Math.floor( Math.random()*100);
	 				String GoToX= GOTO+"&"+X; 
	 				thePalClient.send(new SpeechRecognitionCommand(102, GoToX ));
	 				textWindow.append(GoToX +  System.getProperty("line.separator"));
	 				System.out.println(GoToX);

	 			}
	 		});
	 		
	 		
	 		// FINISH Button
	 		JButton FINISHButton = new JButton("Finish");
	 		FINISHButton.setBounds(507, 10, 70, 30);
	 		panel.add(FINISHButton);
	 		FINISHButton.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {
	 				
	 				thePalClient.send(new SpeechRecognitionCommand(102, FINISH));
	 				textWindow.append( FINISH +  System.getProperty("line.separator"));
	 				System.out.println(FINISH);

	 			}
	 		});

	 	
	 		}
	
	
    public static void main(String[] args) throws InstantiationException {
    	

    	
        try {
        	SpeechRecognition obj = new SpeechRecognition("SpeechRecognition");
        	
            URL url;
            if (args.length > 0) {
                url = new File(args[0]).toURI().toURL();
            } else {
                url = SpeechRecognition.class.getResource("helloworld.config.xml");
            }

            System.out.println("Loading...");

            ConfigurationManager cm = new ConfigurationManager(url);

	    Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
	    Microphone microphone = (Microphone) cm.lookup("microphone");


        /* allocate the resource necessary for the recognizer */
        recognizer.allocate();

        /* the microphone will keep recording until the program exits */
	    if (microphone.startRecording()) {

		System.out.println
		    ("Say: ( Hi | Hello | Please) " +
                     "( Nao | Start | Pause | Continue | Go to slide X | Finish )");

		while (true) {
				    System.out.println
					("Start speaking.\n");
			
			                /*
			                 * This method will return when the end of speech
			                 * is reached. Note that the end pointer will determine
			                 * the end of speech.
			                 */ 
				    Result result = recognizer.recognize();
				    
				    if (result != null) {
					String resultText = result.getBestFinalResultNoFiller();
					System.out.println("You said: " + resultText + "\n");		
					
					
					/* Result compare to pre-defined commands*/
					if 	    (resultText.equals("please start")){
							SRCommand="Start(src)";}
					else if (resultText.equals("hi noah")){
							SRCommand="WakeUp(src)";}
					else if (resultText.equals("hello noah")){
							SRCommand="WakeUp(src)";}
					else if (resultText.equals("please pause")){
							SRCommand="Pause(src)";}
					else if (resultText.equals("please continue")){
							SRCommand="Continue(src)";}
					else if (resultText.contains("please go to slide")){
						
							String SlideNo = resultText.replace("please go to slide ", "");
			
							int index = -1;
							for (int i=0;i<numbers.length;i++) {
							    if (numbers[i].equals(SlideNo)) {
							        index = i;
							        break;
							    }
							}
			
							System.out.println (index+"\n");
							SRCommand="GoTo(src)&"+index;}
					else if (resultText.equals("please finish")){
							SRCommand="Finish(src)";}		
					else {
							SRCommand="";
					}
			
					/*Send detected command to server*/
					if (!"".equals(SRCommand)){
					 obj.sendSRC(SRCommand);
					}		
			
		    } else {
			System.out.println("I can't hear what you said.\n");
		    }
		}
	    } else {
		System.out.println("Can not start microphone.");
		recognizer.deallocate();
		System.exit(1);
	    }
        } catch (IOException e) {
            System.err.println("Problem when loading SpeechRecognition: " + e);
            e.printStackTrace();
        } catch (PropertyException e) {
            System.err.println("Problem configuring SpeechRecognition: " + e);
            e.printStackTrace();
        }
    }
}
