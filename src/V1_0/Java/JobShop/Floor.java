/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.JobShop;
import V1_0.Java.Scheduling.*;
import java.util.*;

// line 50 "../../../umpleFile.ump"
public class Floor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Floor Attributes
  private String name;
  private int id;

  //Floor Associations
  private List<Machine> machines;
  private Operator operator;
  private Shop shop;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Floor(String aName, int aId, Operator aOperator, Shop aShop)
  {
    name = aName;
    id = aId;
    machines = new ArrayList<Machine>();
    if (aOperator == null || aOperator.getFloor() != null)
    {
      throw new RuntimeException("Unable to create Floor due to aOperator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    operator = aOperator;
    boolean didAddShop = setShop(aShop);
    if (!didAddShop)
    {
      throw new RuntimeException("Unable to create floor due to shop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Floor(String aName, int aId, String aNameForOperator, int aIdForOperator, Shop aShop)
  {
    name = aName;
    id = aId;
    machines = new ArrayList<Machine>();
    operator = new Operator(aNameForOperator, aIdForOperator, this);
    boolean didAddShop = setShop(aShop);
    if (!didAddShop)
    {
      throw new RuntimeException("Unable to create floor due to shop. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  public Operator getOperator()
  {
    return operator;
  }
  /* Code from template association_GetOne */
  public Shop getShop()
  {
    return shop;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMachines()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Machine addMachine(Job aActiveJob, String aName, Schedule aSchedule)
  {
    return new Machine(aActiveJob, aName, this, aSchedule);
  }

  public boolean addMachine(Machine aMachine)
  {
    boolean wasAdded = false;
    if (machines.contains(aMachine)) { return false; }
    Floor existingFloor = aMachine.getFloor();
    boolean isNewFloor = existingFloor != null && !this.equals(existingFloor);
    if (isNewFloor)
    {
      aMachine.setFloor(this);
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
    //Unable to remove aMachine, as it must always have a floor
    if (!this.equals(aMachine.getFloor()))
    {
      machines.remove(aMachine);
      wasRemoved = true;
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setShop(Shop aShop)
  {
    boolean wasSet = false;
    //Must provide shop to floor
    if (aShop == null)
    {
      return wasSet;
    }

    if (shop != null && shop.numberOfFloors() <= Shop.minimumNumberOfFloors())
    {
      return wasSet;
    }

    Shop existingShop = shop;
    shop = aShop;
    if (existingShop != null && !existingShop.equals(aShop))
    {
      boolean didRemove = existingShop.removeFloor(this);
      if (!didRemove)
      {
        shop = existingShop;
        return wasSet;
      }
    }
    shop.addFloor(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=machines.size(); i > 0; i--)
    {
      Machine aMachine = machines.get(i - 1);
      aMachine.delete();
    }
    Operator existingOperator = operator;
    operator = null;
    if (existingOperator != null)
    {
      existingOperator.delete();
    }
    Shop placeholderShop = shop;
    this.shop = null;
    if(placeholderShop != null)
    {
      placeholderShop.removeFloor(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "operator = "+(getOperator()!=null?Integer.toHexString(System.identityHashCode(getOperator())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "shop = "+(getShop()!=null?Integer.toHexString(System.identityHashCode(getShop())):"null");
  }
}