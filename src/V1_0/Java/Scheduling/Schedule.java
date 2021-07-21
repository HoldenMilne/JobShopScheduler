/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.Scheduling;
import V1_0.Java.JobShop.Machine;
import java.util.*;
import V1_0.Java.JobShop.*;

// line 64 "../../../umpleFile.ump"
public class Schedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Schedule Associations
  private List<Machine> machines;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Schedule()
  {
    machines = new ArrayList<Machine>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Machine getMachine(int index)
  {
    Machine aMachine = machines.get(index);
    return aMachine;
  }

  public List<Machine> getMachines()
  {
    List<Machine> newMachines = Collections.unmodifiableList(machines);
    return newMachines;
  }

  public int numberOfMachines()
  {
    int number = machines.size();
    return number;
  }

  public boolean hasMachines()
  {
    boolean has = machines.size() > 0;
    return has;
  }

  public int indexOfMachine(Machine aMachine)
  {
    int index = machines.indexOf(aMachine);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfMachinesValid()
  {
    boolean isValid = numberOfMachines() >= minimumNumberOfMachines();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMachines()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Machine addMachine(Job aActiveJob, String aName, Floor aFloor)
  {
    Machine aNewMachine = new Machine(aActiveJob, aName, aFloor, this);
    return aNewMachine;
  }

  public boolean addMachine(Machine aMachine)
  {
    boolean wasAdded = false;
    if (machines.contains(aMachine)) { return false; }
    Schedule existingSchedule = aMachine.getSchedule();
    boolean isNewSchedule = existingSchedule != null && !this.equals(existingSchedule);

    if (isNewSchedule && existingSchedule.numberOfMachines() <= minimumNumberOfMachines())
    {
      return wasAdded;
    }
    if (isNewSchedule)
    {
      aMachine.setSchedule(this);
    }
    else
    {
      machines.add(aMachine);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMachine(Machine aMachine)
  {
    boolean wasRemoved = false;
    //Unable to remove aMachine, as it must always have a schedule
    if (this.equals(aMachine.getSchedule()))
    {
      return wasRemoved;
    }

    //schedule already at minimum (1)
    if (numberOfMachines() <= minimumNumberOfMachines())
    {
      return wasRemoved;
    }

    machines.remove(aMachine);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMachineAt(Machine aMachine, int index)
  {  
    boolean wasAdded = false;
    if(addMachine(aMachine))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMachines()) { index = numberOfMachines() - 1; }
      machines.remove(aMachine);
      machines.add(index, aMachine);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMachineAt(Machine aMachine, int index)
  {
    boolean wasAdded = false;
    if(machines.contains(aMachine))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMachines()) { index = numberOfMachines() - 1; }
      machines.remove(aMachine);
      machines.add(index, aMachine);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMachineAt(aMachine, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=machines.size(); i > 0; i--)
    {
      Machine aMachine = machines.get(i - 1);
      aMachine.delete();
    }
  }

}