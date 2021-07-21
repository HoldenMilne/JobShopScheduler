/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.JobShop;
import java.util.*;

// line 58 "../../../umpleFile.ump"
public class Shop
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shop Associations
  private List<Floor> floors;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shop()
  {
    floors = new ArrayList<Floor>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Floor getFloor(int index)
  {
    Floor aFloor = floors.get(index);
    return aFloor;
  }

  public List<Floor> getFloors()
  {
    List<Floor> newFloors = Collections.unmodifiableList(floors);
    return newFloors;
  }

  public int numberOfFloors()
  {
    int number = floors.size();
    return number;
  }

  public boolean hasFloors()
  {
    boolean has = floors.size() > 0;
    return has;
  }

  public int indexOfFloor(Floor aFloor)
  {
    int index = floors.indexOf(aFloor);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfFloorsValid()
  {
    boolean isValid = numberOfFloors() >= minimumNumberOfFloors();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFloors()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Floor addFloor(String aName, int aId, Operator aOperator)
  {
    Floor aNewFloor = new Floor(aName, aId, aOperator, this);
    return aNewFloor;
  }

  public boolean addFloor(Floor aFloor)
  {
    boolean wasAdded = false;
    if (floors.contains(aFloor)) { return false; }
    Shop existingShop = aFloor.getShop();
    boolean isNewShop = existingShop != null && !this.equals(existingShop);

    if (isNewShop && existingShop.numberOfFloors() <= minimumNumberOfFloors())
    {
      return wasAdded;
    }
    if (isNewShop)
    {
      aFloor.setShop(this);
    }
    else
    {
      floors.add(aFloor);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFloor(Floor aFloor)
  {
    boolean wasRemoved = false;
    //Unable to remove aFloor, as it must always have a shop
    if (this.equals(aFloor.getShop()))
    {
      return wasRemoved;
    }

    //shop already at minimum (1)
    if (numberOfFloors() <= minimumNumberOfFloors())
    {
      return wasRemoved;
    }

    floors.remove(aFloor);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFloorAt(Floor aFloor, int index)
  {  
    boolean wasAdded = false;
    if(addFloor(aFloor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFloors()) { index = numberOfFloors() - 1; }
      floors.remove(aFloor);
      floors.add(index, aFloor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFloorAt(Floor aFloor, int index)
  {
    boolean wasAdded = false;
    if(floors.contains(aFloor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFloors()) { index = numberOfFloors() - 1; }
      floors.remove(aFloor);
      floors.add(index, aFloor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFloorAt(aFloor, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=floors.size(); i > 0; i--)
    {
      Floor aFloor = floors.get(i - 1);
      aFloor.delete();
    }
  }

}