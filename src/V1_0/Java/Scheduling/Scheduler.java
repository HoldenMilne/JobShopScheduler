/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.Scheduling;
import V1_0.Java.JobShop.*;
import java.util.*;

// line 107 "../../../umpleFile.ump"
public class Scheduler
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PivotSelection { LARGEST_TTC, LARGEST_TOT, SMALLEST_TTC, SMALLEST_TOT, NONE }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Scheduler Attributes
  private PivotSelection firstPivotSelectionMode;
  private PivotSelection secondPivotSelectionMode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Scheduler(PivotSelection aFirstPivotSelectionMode, PivotSelection aSecondPivotSelectionMode)
  {
    firstPivotSelectionMode = aFirstPivotSelectionMode;
    secondPivotSelectionMode = aSecondPivotSelectionMode;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFirstPivotSelectionMode(PivotSelection aFirstPivotSelectionMode)
  {
    boolean wasSet = false;
    // line 121 "../../../umpleFile.ump"
    if(aFirstPivotSelectionMode==PivotSelection.NONE)
                aFirstPivotSelectionMode = PivotSelection.SMALLEST_TOT;
    // END OF UMPLE BEFORE INJECTION
    firstPivotSelectionMode = aFirstPivotSelectionMode;
    wasSet = true;
    return wasSet;
  }

  public boolean setSecondPivotSelectionMode(PivotSelection aSecondPivotSelectionMode)
  {
    boolean wasSet = false;
    // line 126 "../../../umpleFile.ump"
    if(aSecondPivotSelectionMode==PivotSelection.NONE)
                aSecondPivotSelectionMode = firstPivotSelectionMode;
    // END OF UMPLE BEFORE INJECTION
    secondPivotSelectionMode = aSecondPivotSelectionMode;
    wasSet = true;
    return wasSet;
  }

  public PivotSelection getFirstPivotSelectionMode()
  {
    return firstPivotSelectionMode;
  }

  public PivotSelection getSecondPivotSelectionMode()
  {
    return secondPivotSelectionMode;
  }

  public void delete()
  {}


  /**
   * implement first alg here
   */
  // line 133 "../../../umpleFile.ump"
   private OutputSchedule GreedyPriorityScheduler(List<Job> activeJobs){
    List<Job> finalList = new ArrayList<Job>();
        return null; // return final list as output schedule
  }


  /**
   * implement second alg here
   */
  // line 140 "../../../umpleFile.ump"
   private List<Job> SchedulerRecursion(List<Job> activeJobs){
    return null;
  }

  // line 145 "../../../umpleFile.ump"
   public Machine NextMachineToService(InputSchedule s){
    return NextMachinesToService(s,1).asMachineList().get(0);
  }

  // line 150 "../../../umpleFile.ump"
   public OutputSchedule GetFullServiceOrder(InputSchedule s){
    return NextMachinesToService(s,s.getMachines().size());
  }

  // line 155 "../../../umpleFile.ump"
   public OutputSchedule NextMachinesToService(InputSchedule s, int nMachines){
    List<Job> activeJobs = GetActiveJobList(s); // extract the active jobs from the machines
        return GreedyPriorityScheduler(activeJobs);
  }

  // line 161 "../../../umpleFile.ump"
   private List<Job> GetActiveJobList(InputSchedule s){
    List<Job> activeJobs = new ArrayList<Job>();
        for(Machine m : s.getMachines())
        {
            activeJobs.add(m.getActiveJob());
        }
        return activeJobs;
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "firstPivotSelectionMode" + "=" + (getFirstPivotSelectionMode() != null ? !getFirstPivotSelectionMode().equals(this)  ? getFirstPivotSelectionMode().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "secondPivotSelectionMode" + "=" + (getSecondPivotSelectionMode() != null ? !getSecondPivotSelectionMode().equals(this)  ? getSecondPivotSelectionMode().toString().replaceAll("  ","    ") : "this" : "null");
  }
}