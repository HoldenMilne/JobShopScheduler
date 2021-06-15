/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.JobShop;
import java.util.*;

// line 18 "../../../umpleFile.ump"
public class Operator
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Operator Attributes
  private String name;
  private int id;

  //Operator Associations
  private List<Machine> machines;
  private Floor floor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Operator(String aName, int aId, Floor aFloor)
  {
    name = aName;
    id = aId;
    machines = new ArrayList<Machine>();
    if (aFloor == null || aFloor.getOperator() != null)
    {
      throw new RuntimeException("Unable to create Operator due to aFloor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    floor = aFloor;
  }

  public Operator(String aName, int aId, String aNameForFloor, int aIdForFloor, Shop aShopForFloor)
  {
    name = aName;
    id = aId;
    machines = new ArrayList<Machine>();
    floor = new Floor(aNameForFloor, aIdForFloor, this, aShopForFloor);
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
  /* Code from template association_GetOne */
  public Floor getFloor()
  {
    return floor;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMachines()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMachine(Machine aMachine)
  {
    boolean wasAdded = false;
    if (machines.contains(aMachine)) { return false; }
    machines.add(aMachine);
    if (aMachine.indexOfOperator(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMachine.addOperator(this);
      if (!wasAdded)
      {
        machines.remove(aMachine);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeMachine(Machine aMachine)
  {
    boolean wasRemoved = false;
    if (!machines.contains(aMachine))
    {
      return wasRemoved;
    }

    int oldIndex = machines.indexOf(aMachine);
    machines.remove(oldIndex);
    if (aMachine.indexOfOperator(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMachine.removeOperator(this);
      if (!wasRemoved)
      {
        machines.add(oldIndex,aMachine);
      }
    }
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
    ArrayList<Machine> copyOfMachines = new ArrayList<Machine>(machines);
    machines.clear();
    for(Machine aMachine : copyOfMachines)
    {
      aMachine.removeOperator(this);
    }
    Floor existingFloor = floor;
    floor = null;
    if (existingFloor != null)
    {
      existingFloor.delete();
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