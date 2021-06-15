/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.JobShop;
import java.util.*;
import V1_0.Java.Scheduling.*;

// line 10 "../umpleFile.ump"
public class Job
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Job Attributes
  private String name;
  private int id;
  private float timeToCompletion;
  private float changeOverTime;
  private int priority;

  //Job Associations
  private Machine machine;
  private List<Schedule> s;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Job(String aName, int aId, float aTimeToCompletion, float aChangeOverTime, int aPriority, Machine aMachine)
  {
    name = aName;
    id = aId;
    timeToCompletion = aTimeToCompletion;
    changeOverTime = aChangeOverTime;
    priority = aPriority;
    boolean didAddMachine = setMachine(aMachine);
    if (!didAddMachine)
    {
      throw new RuntimeException("Unable to create job due to machine. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    s = new ArrayList<Schedule>();
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

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
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

  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
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
  /* Code from template association_GetOne */
  public Machine getMachine()
  {
    return machine;
  }
  /* Code from template association_GetMany */
  public Schedule getS(int index)
  {
    Schedule aS = s.get(index);
    return aS;
  }

  public List<Schedule> getS()
  {
    List<Schedule> newS = Collections.unmodifiableList(s);
    return newS;
  }

  public int numberOfS()
  {
    int number = s.size();
    return number;
  }

  public boolean hasS()
  {
    boolean has = s.size() > 0;
    return has;
  }

  public int indexOfS(Schedule aS)
  {
    int index = s.indexOf(aS);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMachine(Machine aMachine)
  {
    boolean wasSet = false;
    if (aMachine == null)
    {
      return wasSet;
    }

    Machine existingMachine = machine;
    machine = aMachine;
    if (existingMachine != null && !existingMachine.equals(aMachine))
    {
      existingMachine.removeJob(this);
    }
    machine.addJob(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfSValid()
  {
    boolean isValid = numberOfS() >= minimumNumberOfS();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfS()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addS(Schedule aS)
  {
    boolean wasAdded = false;
    if (s.contains(aS)) { return false; }
    s.add(aS);
    if (aS.indexOfJ(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aS.addJ(this);
      if (!wasAdded)
      {
        s.remove(aS);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeS(Schedule aS)
  {
    boolean wasRemoved = false;
    if (!s.contains(aS))
    {
      return wasRemoved;
    }

    if (numberOfS() <= minimumNumberOfS())
    {
      return wasRemoved;
    }

    int oldIndex = s.indexOf(aS);
    s.remove(oldIndex);
    if (aS.indexOfJ(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aS.removeJ(this);
      if (!wasRemoved)
      {
        s.add(oldIndex,aS);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setS(Schedule... newS)
  {
    boolean wasSet = false;
    ArrayList<Schedule> verifiedS = new ArrayList<Schedule>();
    for (Schedule aS : newS)
    {
      if (verifiedS.contains(aS))
      {
        continue;
      }
      verifiedS.add(aS);
    }

    if (verifiedS.size() != newS.length || verifiedS.size() < minimumNumberOfS())
    {
      return wasSet;
    }

    ArrayList<Schedule> oldS = new ArrayList<Schedule>(s);
    s.clear();
    for (Schedule aNewS : verifiedS)
    {
      s.add(aNewS);
      if (oldS.contains(aNewS))
      {
        oldS.remove(aNewS);
      }
      else
      {
        aNewS.addJ(this);
      }
    }

    for (Schedule anOldS : oldS)
    {
      anOldS.removeJ(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSAt(Schedule aS, int index)
  {  
    boolean wasAdded = false;
    if(addS(aS))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfS()) { index = numberOfS() - 1; }
      s.remove(aS);
      s.add(index, aS);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSAt(Schedule aS, int index)
  {
    boolean wasAdded = false;
    if(s.contains(aS))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfS()) { index = numberOfS() - 1; }
      s.remove(aS);
      s.add(index, aS);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSAt(aS, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Machine placeholderMachine = machine;
    this.machine = null;
    if(placeholderMachine != null)
    {
      placeholderMachine.removeJob(this);
    }
    ArrayList<Schedule> copyOfS = new ArrayList<Schedule>(s);
    s.clear();
    for(Schedule aS : copyOfS)
    {
      if (aS.numberOfJ() <= Schedule.minimumNumberOfJ())
      {
        aS.delete();
      }
      else
      {
        aS.removeJ(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "," +
            "timeToCompletion" + ":" + getTimeToCompletion()+ "," +
            "changeOverTime" + ":" + getChangeOverTime()+ "," +
            "priority" + ":" + getPriority()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "machine = "+(getMachine()!=null?Integer.toHexString(System.identityHashCode(getMachine())):"null");
  }
}