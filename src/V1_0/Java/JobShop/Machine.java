/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.JobShop;
import java.util.*;

/**
 * :name=V1_0.Java.JobShop;
 */
// line 3 "../umpleFile.ump"
public class Machine
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Machine Attributes
  private String name;
  private int id;

  //Machine Associations
  private List<Job> jobs;
  private List<Operator> operators;
  private Floor floor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Machine(String aName, int aId, Floor aFloor)
  {
    name = aName;
    id = aId;
    jobs = new ArrayList<Job>();
    operators = new ArrayList<Operator>();
    boolean didAddFloor = setFloor(aFloor);
    if (!didAddFloor)
    {
      throw new RuntimeException("Unable to create machine due to floor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetMany */
  public Job getJob(int index)
  {
    Job aJob = jobs.get(index);
    return aJob;
  }

  public List<Job> getJobs()
  {
    List<Job> newJobs = Collections.unmodifiableList(jobs);
    return newJobs;
  }

  public int numberOfJobs()
  {
    int number = jobs.size();
    return number;
  }

  public boolean hasJobs()
  {
    boolean has = jobs.size() > 0;
    return has;
  }

  public int indexOfJob(Job aJob)
  {
    int index = jobs.indexOf(aJob);
    return index;
  }
  /* Code from template association_GetMany */
  public Operator getOperator(int index)
  {
    Operator aOperator = operators.get(index);
    return aOperator;
  }

  public List<Operator> getOperators()
  {
    List<Operator> newOperators = Collections.unmodifiableList(operators);
    return newOperators;
  }

  public int numberOfOperators()
  {
    int number = operators.size();
    return number;
  }

  public boolean hasOperators()
  {
    boolean has = operators.size() > 0;
    return has;
  }

  public int indexOfOperator(Operator aOperator)
  {
    int index = operators.indexOf(aOperator);
    return index;
  }
  /* Code from template association_GetOne */
  public Floor getFloor()
  {
    return floor;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfJobs()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Job addJob(String aName, int aId, float aTimeToCompletion, float aChangeOverTime, int aPriority)
  {
    return new Job(aName, aId, aTimeToCompletion, aChangeOverTime, aPriority, this);
  }

  public boolean addJob(Job aJob)
  {
    boolean wasAdded = false;
    if (jobs.contains(aJob)) { return false; }
    Machine existingMachine = aJob.getMachine();
    boolean isNewMachine = existingMachine != null && !this.equals(existingMachine);
    if (isNewMachine)
    {
      aJob.setMachine(this);
    }
    else
    {
      jobs.add(aJob);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeJob(Job aJob)
  {
    boolean wasRemoved = false;
    //Unable to remove aJob, as it must always have a machine
    if (!this.equals(aJob.getMachine()))
    {
      jobs.remove(aJob);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addJobAt(Job aJob, int index)
  {  
    boolean wasAdded = false;
    if(addJob(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobAt(Job aJob, int index)
  {
    boolean wasAdded = false;
    if(jobs.contains(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobAt(aJob, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOperators()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addOperator(Operator aOperator)
  {
    boolean wasAdded = false;
    if (operators.contains(aOperator)) { return false; }
    operators.add(aOperator);
    if (aOperator.indexOfMachine(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOperator.addMachine(this);
      if (!wasAdded)
      {
        operators.remove(aOperator);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeOperator(Operator aOperator)
  {
    boolean wasRemoved = false;
    if (!operators.contains(aOperator))
    {
      return wasRemoved;
    }

    int oldIndex = operators.indexOf(aOperator);
    operators.remove(oldIndex);
    if (aOperator.indexOfMachine(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOperator.removeMachine(this);
      if (!wasRemoved)
      {
        operators.add(oldIndex,aOperator);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOperatorAt(Operator aOperator, int index)
  {  
    boolean wasAdded = false;
    if(addOperator(aOperator))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperators()) { index = numberOfOperators() - 1; }
      operators.remove(aOperator);
      operators.add(index, aOperator);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOperatorAt(Operator aOperator, int index)
  {
    boolean wasAdded = false;
    if(operators.contains(aOperator))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperators()) { index = numberOfOperators() - 1; }
      operators.remove(aOperator);
      operators.add(index, aOperator);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOperatorAt(aOperator, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setFloor(Floor aFloor)
  {
    boolean wasSet = false;
    if (aFloor == null)
    {
      return wasSet;
    }

    Floor existingFloor = floor;
    floor = aFloor;
    if (existingFloor != null && !existingFloor.equals(aFloor))
    {
      existingFloor.removeMachine(this);
    }
    floor.addMachine(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=jobs.size(); i > 0; i--)
    {
      Job aJob = jobs.get(i - 1);
      aJob.delete();
    }
    ArrayList<Operator> copyOfOperators = new ArrayList<Operator>(operators);
    operators.clear();
    for(Operator aOperator : copyOfOperators)
    {
      aOperator.removeMachine(this);
    }
    Floor placeholderFloor = floor;
    this.floor = null;
    if(placeholderFloor != null)
    {
      placeholderFloor.removeMachine(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "floor = "+(getFloor()!=null?Integer.toHexString(System.identityHashCode(getFloor())):"null");
  }
}