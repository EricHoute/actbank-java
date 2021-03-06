package atm.parts;

import static java.lang.Integer.parseInt;

import java.util.Scanner;

public class FingerPrintReader{
	
	private int status;
	private int fingerPrintSequenceRead;
	
	public static final int NO_FINGER = 0;
	public static final int UNREADABLE_FINGER = 1;
	public static final int FINGER_READ = 2;
	
	/* Constructor */
	
	public FingerPrintReader(){
		status = NO_FINGER;
	}
	
	/**
	 * Checks if there is a finger to scan and returns its status
	 * Check the private constants at the beginning of the file for more info about the satus codes
	 * 
	 * @return a status code
	 */
	public synchronized int checkForFinger(){
		try{
			wait(1000);
		}catch(InterruptedException e){}
		
		/* We don't have REAL fingerprint scanners so we ask the customer to enter his sequence number */
		Scanner input = new Scanner(System.in);
		String sequence = input.nextLine();
		
		if(sequence == null)
			status = UNREADABLE_FINGER;
		else{
			try{
				fingerPrintSequenceRead = parseInt(sequence);
				status = FINGER_READ;
			}catch(NumberFormatException e){
				status = UNREADABLE_FINGER;
			}
		}
		return status;
	}
	
	/**
	 * Returns the sequence number representing the finger print
	 * 
	 * @return sequence number of the finger print
	 */
	public int fingerPrintSequence(){
		return fingerPrintSequenceRead;
	}
}
