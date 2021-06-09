package selogger.logging.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import selogger.logging.IEventLogger;
import selogger.logging.util.FileNameGenerator;
import selogger.weaver.Weaver;

/**
 * This class is an implementation of IEventLogger that counts
 * the number of occurrences for each event (dataId). 
 */
public class EventFrequencyLogger implements IEventLogger {
	
	/**
	 * The name of a file created by this logger
	 */
	private static final String FILENAME = "eventfreq.txt";

	public static final String LOG_PREFIX = "eventfreq-";
	public static final String LOG_SUFFIX = ".txt";
	
	FileNameGenerator fng;
	Weaver weaver;
	/**
	 * Array of counter objects.  dataId is used as an index for this array.
	 */
	private ArrayList<AtomicInteger> counters;
	
	/**
	 * A directory where a resultant file is stored
	 */
	private File outputDir;
	
	/**
	 * A flag indicates whether the current interval is the specifed interval by option.
	 */
	private boolean isRecord;

	/**
	 * Create the logger object.
	 * @param outputDir specifies a directory where a resultant file is stored
	 */
	public EventFrequencyLogger(File outputDir, Weaver weaver) {
		this.outputDir = outputDir;
		counters = new ArrayList<>();
		fng = new FileNameGenerator(outputDir, LOG_PREFIX, LOG_SUFFIX);
		this.weaver = weaver;
		//TODO fix this assignment when incorrect weaveStart
		isRecord = weaver.getWeaveStart().equals(""); 
	}
	
	
	
	
	/**
	 * Count the event occurrence.
	 * @param dataId specifies an event to be counted.
	 * @param value is required for interface, but the value is discarded by this logger. 
	 */
	@Override
	public void recordEvent(int dataId, boolean value) {
		if(dataId == weaver.getFilteringStartDataId())  isRecord =true; 	
		if (isRecord) countOccurrence(dataId);
		if(dataId == weaver.getFilteringEndDataId()) {
			isRecord = false;
			this.close();
		}
	}
	
	/**
	 * Count the event occurrence.
	 * @param dataId specifies an event to be counted.
	 * @param value is required for interface, but the value is discarded by this logger. 
	 */
	@Override
	public void recordEvent(int dataId, byte value) {
		if(dataId == weaver.getFilteringStartDataId())  isRecord =true; 	
		if (isRecord) countOccurrence(dataId);
		if(dataId == weaver.getFilteringEndDataId()) {
			isRecord = false;
			this.close();
		}
	}
	
	/**
	 * Count the event occurrence.
	 * @param dataId specifies an event to be counted.
	 * @param value is required for interface, but the value is discarded by this logger. 
	 */
	@Override
	public void recordEvent(int dataId, char value) {
		if(dataId == weaver.getFilteringStartDataId())  isRecord =true; 	
		if (isRecord) countOccurrence(dataId);
		if(dataId == weaver.getFilteringEndDataId()) {
			isRecord = false;
			this.close();
		}
	}
	
	/**
	 * Count the event occurrence.
	 * @param dataId specifies an event to be counted.
	 * @param value is required for interface, but the value is discarded by this logger. 
	 */
	@Override
	public void recordEvent(int dataId, double value) {
		if(dataId == weaver.getFilteringStartDataId())  isRecord =true; 	
		if (isRecord) countOccurrence(dataId);
		if(dataId == weaver.getFilteringEndDataId()) {
			isRecord = false;
			this.close();
		}
	}
	
	/**
	 * Count the event occurrence.
	 * @param dataId specifies an event to be counted.
	 * @param value is required for interface, but the value is discarded by this logger. 
	 */
	@Override
	public void recordEvent(int dataId, float value) {
		if(dataId == weaver.getFilteringStartDataId())  isRecord =true; 	
		if (isRecord) countOccurrence(dataId);
		if(dataId == weaver.getFilteringEndDataId()) {
			isRecord = false;
			this.close();
		}
	}
	
	/**
	 * Count the event occurrence.
	 * @param dataId specifies an event to be counted.
	 * @param value is required for interface, but the value is discarded by this logger. 
	 */
	@Override
	public void recordEvent(int dataId, int value) {
		if(dataId == weaver.getFilteringStartDataId())  isRecord =true; 	
		if (isRecord) countOccurrence(dataId);
		if(dataId == weaver.getFilteringEndDataId()) {
			isRecord = false;
			this.close();
		}
	}
	
	/**
	 * Count the event occurrence.
	 * @param dataId specifies an event to be counted.
	 * @param value is required for interface, but the value is discarded by this logger. 
	 */
	@Override
	public void recordEvent(int dataId, long value) {
		if(dataId == weaver.getFilteringStartDataId())  isRecord =true; 	
		if (isRecord) countOccurrence(dataId);
		if(dataId == weaver.getFilteringEndDataId()) {
			isRecord = false;
			this.close();
		}
	}
	
	/**
	 * Count the event occurrence.
	 * @param dataId specifies an event to be counted.
	 * @param value is required for interface, but the value is discarded by this logger. 
	 */
	@Override
	public void recordEvent(int dataId, Object value) {
		if(dataId == weaver.getFilteringStartDataId())  isRecord =true; 	
		if (isRecord) countOccurrence(dataId);
		if(dataId == weaver.getFilteringEndDataId()) {
			isRecord = false;
			this.close();
		}
	}
	
	/**
	 * Count the event occurrence.
	 * @param dataId specifies an event to be counted.
	 * @param value is required for interface, but the value is discarded by this logger. 
	 */
	@Override
	public void recordEvent(int dataId, short value) {
		if(dataId == weaver.getFilteringStartDataId())  isRecord =true; 	
		if (isRecord) countOccurrence(dataId);
		if(dataId == weaver.getFilteringEndDataId()) {
			isRecord = false;
			this.close();
		}
	}
	
	/**
	 * Increment an event count.
	 * @param dataId specifies an event.
	 */
	private void countOccurrence(int dataId) {
		// Prepare a counter (if not exist)
		if (counters.size() <= dataId) {
			synchronized(counters) { 
				while (counters.size() <= dataId) {
					counters.add(new AtomicInteger());
				}
			}
		}
		// Increment the counter specified by dataId
		AtomicInteger c = counters.get(dataId);
		c.incrementAndGet();
	}
	
	/**
	 * Write the event count into files when terminated 
	 */
	@Override
	public synchronized void close() {
		File file = weaver.getIsFiltering() ? fng.getNextFile() : new File(outputDir, FILENAME);
		try (PrintWriter w = new PrintWriter(new FileWriter(file))) {
			for (int i=0; i<counters.size(); i++) {
				AtomicInteger c = counters.get(i);
				int count = c.get();
				if (count > 0) {
					w.println(i + "," + count);
					c.set(0);
				}
			}
		} catch (IOException e) {
			
		}
	}
	
	
}
