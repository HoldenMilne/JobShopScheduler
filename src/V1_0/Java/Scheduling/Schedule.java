/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.Scheduling;
import java.util.*;
import V1_0.Java.JobShop.*;

// line 39 "../../../umpleFile.ump"
public class Schedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Schedule Associations
  private List<Job> j;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Schedule()
  {
    j = new ArrayList<Job>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Job getJ(int index)
  {
    Job aJ = j.get(index);
    return aJ;
  }

  public List<Job> getJ()
  {
    List<Job> newJ = Collections.unmodifiableList(j);
    return newJ;
  }

  public int numberOfJ()
  {
    int number = j.size();
    return number;
  }

  public boolean hasJ()
  {
    boolean has = j.size() > 0;
    return has;
  }

  public int indexOfJ(Job aJ)
  {
    int index = j.indexOf(aJ);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfJValid()
  {
    boolean isValid = numberOfJ() >= minimumNumberOfJ();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfJ()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addJ(Job aJ)
  {
    boolean wasAdded = false;
    if (j.contains(aJ)) { return false; }
    j.add(aJ);
    if (aJ.indexOfS(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aJ.addS(this);
      if (!wasAdded)
      {
        j.remove(aJ);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeJ(Job aJ)
  {
    boolean wasRemoved = false;
    if (!j.contains(aJ))
    {
      return wasRemoved;
    }

    if (numberOfJ() <= minimumNumberOfJ())
    {
      return wasRemoved;
    }

    int oldIndex = j.indexOf(aJ);
    j.remove(oldIndex);
    if (aJ.indexOfS(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aJ.removeS(this);
      if (!wasRemoved)
      {
        j.add(oldIndex,aJ);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setJ(Job... newJ)
  {
    boolean wasSet = false;
    ArrayList<Job> verifiedJ = new ArrayList<Job>();
    for (Job aJ : newJ)
    {
      if (verifiedJ.contains(aJ))
      {
        continue;
      }
      verifiedJ.add(aJ);
    }

    if (verifiedJ.size() != newJ.length || verifiedJ.size() < minimumNumberOfJ())
    {
      return wasSet;
    }

    ArrayList<Job> oldJ = new ArrayList<Job>(j);
    j.clear();
    for (Job aNewJ : verifiedJ)
    {
      j.add(aNewJ);
      if (oldJ.contains(aNewJ))
      {
        oldJ.remove(aNewJ);
      }
      else
      {
        aNewJ.addS(this);
      }
    }

    for (Job anOldJ : oldJ)
    {
      anOldJ.removeS(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addJAt(Job aJ, int index)
  {  
    boolean wasAdded = false;
    if(addJ(aJ))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJ()) { index = numberOfJ() - 1; }
      j.remove(aJ);
      j.add(index, aJ);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJAt(Job aJ, int index)
  {
    boolean wasAdded = false;
    if(j.contains(aJ))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJ()) { index = numberOfJ() - 1; }
      j.remove(aJ);
      j.add(index, aJ);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJAt(aJ, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (j.size() > 0)
    {
      Job aJ = j.get(j.size() - 1);
      aJ.delete();
      j.remove(aJ);
    }
    
  }

}