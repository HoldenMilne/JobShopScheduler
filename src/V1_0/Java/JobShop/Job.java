/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.JobShop;

// line 30 "../../../umpleFile.ump"
public class Job
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final Job DummyJob = new Job("dummy", Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,Integer.MIN_VALUE,null);
  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Job Attributes
  private String name;
  private float timeToCompletion;
  private float changeOverTime;
  private int priority;
  private Job nextJob;

  //Autounique Attributes
  private int id;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Job(String aName, float aTimeToCompletion, float aChangeOverTime, int aPriority, Job aNextJob)
  {
    name = aName;
    timeToCompletion = aTimeToCompletion;
    changeOverTime = aChangeOverTime;
    priority = aPriority;
    nextJob = aNextJob;
    id = nextId++;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setTimeToCompletion(float aTimeToCompletion)
  {
    boolean wasSet = false;
    timeToCompletion = aTimeToCompletion;
    wasSet = true;
    return wasSet;
  }

  public boolean setChangeOverTime(float aChangeOverTime)
  {
    boolean wasSet = false;
    changeOverTime = aChangeOverTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPriority(int aPriority)
  {
    boolean wasSet = false;
    priority = aPriority;
    wasSet = true;
    return wasSet;
  }

  public boolean setNextJob(Job aNextJob)
  {
    boolean wasSet = false;
    nextJob = aNextJob;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public float getTimeToCompletion()
  {
    return timeToCompletion;
  }

  public float getChangeOverTime()
  {
    return changeOverTime;
  }

  public int getPriority()
  {
    return priority;
  }

  public float getTotal()
  {
    return getChangeOverTime() + getTimeToCompletion();
  }

  public Job getNextJob()
  {
    return nextJob;
  }

  public int getId()
  {
    return id;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "," +
            "timeToCompletion" + ":" + getTimeToCompletion()+ "," +
            "changeOverTime" + ":" + getChangeOverTime()+ "," +
            "priority" + ":" + getPriority()+ "," +
            "total" + ":" + getTotal()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nextJob" + "=" + (getNextJob() != null ? !getNextJob().equals(this)  ? getNextJob().toString().replaceAll("  ","    ") : "this" : "null");
  }
}