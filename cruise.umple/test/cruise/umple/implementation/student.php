<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.16.0.2388 modeling language!*/

class student
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //student Attributes
  private $age;
  private $weight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aAge, $aWeight)
  {
    if ( !(!(18>$aAge)))
    {
      throw new RuntimeException("Please provide a valid age");
    }
    $this->age = $aAge;
    $this->weight = $aWeight;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setAge($aAge)
  {
    $wasSet = false;
    if (!(18>$aAge))
    {
    $this->age = $aAge;
    $wasSet = true;
    }
    return $wasSet;
  }

  public function setWeight($aWeight)
  {
    $wasSet = false;
    $this->weight = $aWeight;
    $wasSet = true;
    return $wasSet;
  }

  public function getAge()
  {
    return $this->age;
  }

  public function getWeight()
  {
    return $this->weight;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>