/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/

package V1_0.Java.Scheduling;
import V1_0.Java.JobShop.*;
import java.util.stream.*;
import java.util.*;

// line 70 "../../../umpleFile.ump"
public class OutputSchedule extends Schedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OutputSchedule()
  {
    super();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 77 "../../../umpleFile.ump"
   public List<Machine> asMachineList(){
    return machines;
  }

  // line 81 "../../../umpleFile.ump"
   public List<Job> asJobList(){
    return machines.stream().map(machine -> machine.getActiveJob()).collect(Collectors.toList());
  }

}